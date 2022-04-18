<?php

use \Psr\Http\Message\ServerRequestInterface as Request;
use \Psr\Http\Message\ResponseInterface as Response;

include 'location.php';

define('PASSWORD_SALT', 'xlahi2afk');


function validateToken($token, $db) {
  $stmt = $db->prepare('SELECT * FROM auth_users WHERE token = :token');
  $stmt->bindValue(':token', $token);
  $stmt->execute();
  $auth = $stmt->fetch();
  # Vráti mi to true ak je tokan platný (ak existuje v DB)
  return !empty($auth['token']);

}



$app->get('/', function (Request $request, Response $response, $args) {
    // Render index view
    return $this->view->render($response, 'index.latte');
})->setName('index');

$app->post('/test', function (Request $request, Response $response, $args) {
    //read POST data
    $input = $request->getParsedBody();

    //log
    $this->logger->info('Your name: ' . $input['person']);

    return $response->withHeader('Location', $this->router->pathFor('index'));
})->setName('redir');

# Nacitanie registracneho formulara
$app->get('/register', function (Request $request, Response $response, $args){
    $tplVars = [
       'formData' => [
          'email' => '',
          'password' => ''
        ],
          'title' => 'Register'
    ];    
    return $this->view->render($response, 'register.latte', $tplVars);
})->setName('register');

# Registrace uzivatele
$app->post('/register', function (Request $request, Response $response, $args){
  $formData = $request->getParsedBody();
  # Overtime ci uzivatel už nahodou neexistuje
  $stmt = $this->db->prepare('SELECT * FROM auth_users WHERE email = :email');
  $stmt->bindValue(':email', $formData['email']);
  $stmt->execute();
  $user = $stmt->fetch();

  if(!empty($user['email']) ) {
    $tplVars['message'] = 'Sorry, this email is already used';
    return $this->view->render($response, 'register.latte', $tplVars);
  } else {
      try {
          $this->db->beginTransaction();
          $password = md5(PASSWORD_SALT . $formData['password']);
          $token = bin2hex(openssl_random_pseudo_bytes(20));

          $stmt = $this->db->prepare('INSERT INTO auth_users (email, password, token) VALUES (:email, :password, :token)');
          $stmt->bindValue(':email', $formData['email']);
          $stmt->bindValue(':password', $password );
          $stmt->bindValue(':token', $token);
          $stmt->execute();
          $this->db->commit();
          setcookie('token', $token, 0);  # 0->životnosť cookie (nekonečnosť)
          return $response->withHeader('Location', $this->router->pathFor('persons'));
      } catch (PDOexception $e) {
          $this->logger->error($e);
          $this->db->rollback();
          return $this->view->render($response, 'register.latte', $tplVars);

      }  
  }

 }); 

/* login */
$app->get('/login', function(Request $request, Response $response, $args) {
    $tplVars = [
       'formData' => [
          'email' => '',
          'password' => ''
        ],
          'title' => 'Login'
    ];      
    return $this->view->render($response, 'register.latte', $tplVars);
})->setName('login');

/* login */
$app->post('/login', function(Request $request, Response $response, $args) {
  $formData = $request->getParsedBody();
    $tplVars = [
       'formData' => $formData,
       'title' => 'Login'
    ];   

    # Overime si existenciu uzivatela
    $stmt = $this->db->prepare('SELECT * FROM auth_users WHERE email = :email AND password = :password');
    $stmt->bindValue(':email', $formData['email']);
    $stmt->bindValue('password', md5(PASSWORD_SALT . $formData['password']));
    $stmt->execute();
    $user = $stmt->fetch();
    if(empty($user['email'])) {
      $tplVars['message'] = 'Email or password is incorrect';
      return $this->view->render($response, 'login.latte', $tplVars);
    } else {
        $token = bin2hex(openssl_random_pseudo_bytes(20));
        $stmt = $this->db->prepare('UPDATE auth_users SET token = :token WHERE id_users = :id_users');
        $stmt->bindValue(':token', $token);
        $stmt->bindValue(':id_users', $user['id_users']);
        $stmt->execute();

        setcookie('token', $token, 0);
        return $response->withHeader('Location', $this->router->pathFor('persons'));

    }
});


/* logout */
$app->get('/logout', function(Request $request, Response $response, $args) {
    setcookie('token', '', time() - 3600);  # nastaviť cookie do minulosti, aby bola neplatná a už sa odosielať nebude
  return $response->withHeader('Location', $this->router->pathFor('index'));
})->setName('logout');

/* Vsetky routy v tejto skupine budu dostupne LEN s platnym tokenom */

$app->group('/auth', function() use($app) { 


  /*seznam všech osob v DB*/
  $app->get('/persons', function (Request $request, Response $response, $args){
  	$params = $request->getQueryParams();
  	if (empty($params['limit'])) {
  		$params['limit'] = 10;
  	};
  	if (empty($params['page'])) {
  		$params['page'] = 0;
  	}



  	$stmt = $this->db->query('SELECT count(*) pocet FROM person'); 
  	$total_pages = $stmt->fetch()['pocet'];

  	$stmt = $this->db->prepare('SELECT * FROM person ORDER BY first_name LIMIT :limit OFFSET :offset'); #toto vrátí jen DB objekt, ne výsledek!
  	$stmt->bindValue(':limit', $params['limit']);
  	$stmt->bindValue(':offset', $params['page'] * $params['limit']);
  	$stmt->execute();

  	$tplVars = [
  	 	'persons_list' => $stmt->fetchall(), # [['id_person' => 1, 'first_name' => 'Alice'... ], ['id_person' => 2, 'first_name' => 'Bob'...].]
  	 	'total_pages' => $total_pages / $params['limit'], # delene limit aby tam bolo len tolko stranok kolko potrebujeme
  	 	'page' => $params['page'],
  	 	'limit' => $params['limit']
  	 ];

  	return $this->view->render($response, 'persons.latte', $tplVars);
  })->setName('persons');


    $app->get('/search', function (Request $request, Response $response, $args){
    $queryParams = $request->getQueryParams(); #[klic => hodnota]
    if(!empty($queryParams) ){
      $stmt = $this->db->prepare("SELECT * FROM person WHERE lower(first_name) = lower(:fname) OR lower(last_name) = lower(:lname)");
      $stmt->bindParam(':fname', $queryParams['q']);
      $stmt->bindParam(':lname', $queryParams['q']);
      $stmt->execute();
      $tplVars['persons_list'] = $stmt->fetchall();
      return $this->view->render($response, 'persons.latte', $tplVars);
    }
  })->setName('search');


  /* nacitanie formularu */
  $app->get('/person', function (Request $request, Response $response, $args) {
      $tplVars['formData'] = [
         'first_name' => '',
         'last_name' => '',
         'nickname' => '',
         'gender' => '',
         'height' => '',
         'birth_day' => '',
         'street_name' => '',
         'street_number' => '',
         'zip' => '',
         'city' => '',


      ];
      return $this->view->render($response, 'newPerson.latte', $tplVars);
  })->setName('newPerson');


  /* spracovanie formularu po odoslani  */
  $app->post('/person', function (Request $request, Response $response, $args) {
        $formData = $request->getParsedBody();
        $tplVars = [];
        if (empty($formData['first_name']) || empty($formData['last_name']) || empty($formData['nickname'])){
          $tplVars['message'] = 'Please fill required fields';
        } else {
            try {
                $this->db->beginTransaction();  
   
                #Kontrolujeme že či bola aspon jedna cast adresy vyplnená
                if ( !empty($formData['street_name']) || !empty($formData['street_number']) || !empty($formData['city']) || !empty($formData['zip']) ) {
                     ## Osoba nema adresu (id_location NULL)
                     $id_location = newLocation($this, $formData);
               }

                 $stmt = $this->db->prepare("INSERT INTO person (nickname, first_name, last_name, id_location, birth_day, height, gender) VALUES (:nickname, :first_name, :last_name, :id_location, :birth_day, 
                  :height, :gender)");
                 $stmt->bindValue(':nickname', $formData['nickname']);
                 $stmt->bindValue(':first_name', $formData['first_name']);
                 $stmt->bindValue(':last_name', $formData['last_name']);
                 $stmt->bindValue(':id_location', $id_location ? $id_location : null );
                 $stmt->bindValue(':gender', empty($formData['gender'])  ? null : $formData['gender'] );
                 $stmt->bindValue(':birth_day', empty($formData['birth_day']) ? null : $formData['birth_day'] );
                 $stmt->bindValue(':height', empty($formData['height']) ? null : $formData['height'] );
                 $stmt->execute();
                 $tplVars['message'] = 'Person succefully added';
                 $this->db->commit();
               
            } catch (PDOexception $e) {
                $tplVars['message'] = 'Error occured, sorry ako';
                $this->logger->error($e->getMessage());
                $tplVars['formData'] = $formData;
                $this->db->rollback();
            }
        }

        return $this->view->render($response, 'newPerson.latte', $tplVars);
  });


  /* nacitanie formularu */
  $app->get('/person/update', function (Request $request, Response $response, $args) {
    # Skontrolujeme ci je uzivatel autorizovany na danu akciu
     $params = $request->getQueryParams();  # $params = [id_person => 1232, firstname => aaa]
     if (! empty($params['id_person'])) {
        $stmt = $this->db->prepare('SELECT * FROM person 
                                    LEFT JOIN location USING (id_location)
                                    WHERE id_person = :id_person');
        $stmt->bindValue(':id_person', $params['id_person']);
        $stmt->execute();
        $tplVars['formData'] = $stmt->fetch();   # nie fetchall, lebo ziskavame udaje o jednej osobe, nechceme list osob
        if (empty($tplVars['formData'])) {
             exit('person not found');
        } else {
          return $this->view->render($response, 'updatePerson.latte', $tplVars);
        }
      }
   })->setName('updatePerson');

  $app->post('/person/update', function (Request $request, Response $response, $args) {
        $id_person = $request->getQueryParam('id_person'); 
        $formData = $request->getParsedBody();
        $tplVars = [];
        if (empty($formData['first_name']) || empty($formData['last_name']) || empty($formData['nickname'])){
          $tplVars['message'] = 'Please fill required fields';
        } else {
          try {

            #Kontrolujeme že či bola aspon jedna cast adresy vyplnená
            if (!empty($formData['street_name']) || !empty($formData['street_number']) || !empty($formData['city']) || !empty($formData['zip']) ) {
               $stmt = $this->db->prepare('SELECT id_location FROM person WHERE id_person = :id_person');
               $stmt->bindValue(':id_person', $id_person);
               $stmt->execute();
               $id_location = $stmt->fetch()['id_location'];   # {'id_location' => 123}

               if ($id_location) {
                   # Osoba má adresu (id_location IS NOT NULL)
                   editLocation($this, $id_location, $formData);
               } else {
                   #Osobsa nema adresu (id_location NULL)
                   $id_location = newLocation($this, $formData);
               }


            }
            $stmt = $this->db->prepare("UPDATE person SET first_name = :first_name, 
                                                           last_name = :last_name,
                                                           nickname = :nickname,
                                                           birth_day = :birth_day,
                                                           gender = :gender,
                                                           height = :height,
                                                           id_location = :id_location
                                                  WHERE id_person = :id_person");        
                                                                    # vždy WHERE pri UPDATE/DELETE !!!!!
            $stmt->bindValue(':nickname', $formData['nickname']);
            $stmt->bindValue(':first_name', $formData['first_name']);
            $stmt->bindValue(':last_name', $formData['last_name']);
            $stmt->bindValue(':id_location', $id_location ? $id_location : null );
            $stmt->bindValue(':gender', empty($formData['gender'])  ? null : $formData['gender'] );
            $stmt->bindValue(':birth_day', empty($formData['birth_day']) ? null : $formData['birth_day'] );
            $stmt->bindValue(':height', empty($formData['height']) ? null : $formData['height'] );
            $stmt->bindValue(':id_person', $id_person);
            $stmt->execute();

          } catch (PDOexception $e)  {
              $tplVars['message'] = 'Error occured, sorry jako';
              $this->logger->error($e->getMessage());

          }
        }
        $tplVars['formData'] = $formData;
        return $this->view->render($response, 'updatePerson.latte', $tplVars);
  });


  /* Delete osob */
  $app->post('/persons/delete', function(Request $request, Response $response, $args) {
    $id_person = $request->getQueryParam('id_person');
    if (!empty($id_person)) {
      try {
    
        $stmt = $this->db->prepare('DELETE FROM person WHERE id_person = :id_person');
        $stmt->bindValue(':id_person', $id_person);
            $stmt->execute();
          } catch (PDOexception $e) {
            $this->logger->error($e->getMessage());
            exit('error occured');
          }
    } else {
      exit('this person is missing');
    }
    return $response->withHeader('Location', $this->router->pathFor('persons'));
  })->setName('person_delete');


  $app->get('/persons/info', function (Request $request, Response $response, $args) {
    $params = $request->getQueryParams(); //ziskaj vsetky parametre z url
    if (empty($params['id_person'])) {
        exit('Error in ID of person');
    } else {
        $stmtPerson = $this->db->prepare("SELECT * FROM person LEFT JOIN location on person.id_location = location.id_location WHERE id_person = :id_person ");
        $stmtContact = $this->db->prepare("SELECT person.id_person, contact.contact, contact_type.name FROM person JOIN contact ON person.id_person = contact.id_person 
                                            JOIN contact_type ON contact.id_contact_type = contact_type.id_contact_type WHERE person.id_person = :id_person");
        $stmtPerson->bindValue(':id_person', $params['id_person']);
        $stmtContact->bindValue(':id_person', $params['id_person']);
        $stmtPerson->execute();
        $stmtContact->execute();
        $tplVars['formData'] = $stmtPerson->fetch();
        $tplVars['contactData'] = $stmtContact->fetchAll();
        if (empty($tplVars['formData'])) {
            exit("Person not found.");
        } else {
            return $this->view->render($response, 'person-info.latte', $tplVars);
        }
    }
})->setName('person-info');


  //locations
  $app->get('/locations', function (Request $request, Response $response, $args) {
        $params = $request->getQueryParams();
     if (empty($params['limit'])) {
        $params['limit'] = 10;
     };
     if (empty($params['page'])) {
        $params['page'] = 0;
     }

        $stmt = $this->db->query('SELECT count(*) pocet FROM location'); 
        $total_pages = $stmt->fetch()['pocet'];

        $stmt = $this->db->prepare('SELECT * FROM location ORDER BY id_location LIMIT :limit OFFSET :offset'); #toto vrátí jen DB objekt, ne výsledek!
        $stmt->bindValue(':limit', $params['limit']);
        $stmt->bindValue(':offset', $params['page'] * $params['limit']);
        $stmt->execute();

    $tplVars = [
      'locations_list' => $stmt->fetchall(), 
      'total_pages' => $total_pages / $params['limit'], # delene limit aby tam bolo len tolko stranok kolko potrebujeme
      'page' => $params['page'],
      'limit' => $params['limit']
     ];

    return $this->view->render($response, 'locations.latte', $tplVars);
  })->setName('locations'); 

      
  $app->get('/searchLocations', function (Request $request, Response $response, $args){
    $queryParams = $request->getQueryParams(); #[klic => hodnota]
    if(!empty($queryParams) ){
      $stmt = $this->db->prepare("SELECT * FROM location WHERE lower(city) = lower(:city) OR lower(street_name) = lower(:stname)");
      $stmt->bindParam(':city', $queryParams['q']);
      $stmt->bindParam(':stname', $queryParams['q']);
      $stmt->execute();
      $tplVars['locations_list'] = $stmt->fetchall();
      return $this->view->render($response, 'locations.latte', $tplVars);
    }
  })->setName('searchLocations');

  // meetings
  $app->get('/meetings', function (Request $request, Response $response, $args) {
     $params = $request->getQueryParams();
     if (empty($params['limit'])) {
        $params['limit'] = 10;
     };
     if (empty($params['page'])) {
        $params['page'] = 0;
     }
        $stmt = $this->db->query('SELECT count(*) pocet FROM meeting');
        $total_pages = $stmt->fetch()['pocet'];

          $stmt = $this->db->prepare('SELECT id_meeting, start, description, duration, city , street_name, street_number FROM meeting LEFT JOIN location ON meeting.id_location = location.id_location ORDER BY start, duration LIMIT :limit OFFSET :offset');

        $stmt->bindValue(':limit', $params['limit']);
        $stmt->bindValue(':offset', $params['page'] * $params['limit']);
        $stmt->execute();
  

       $tplVars = [
        'meetings_list' => $stmt->fetchall(), 
        'total_pages' => $total_pages / $params['limit'], # delene limit aby tam bolo len tolko stranok kolko potrebujeme
        'page' => $params['page'],
        'limit' => $params['limit']
      ];

    return $this->view->render($response, 'meetings.latte', $tplVars);
  })->setName('meetings');


      $app->get('/searchMeetings', function (Request $request, Response $response, $args){
    $queryParams = $request->getQueryParams(); #[klic => hodnota]
    if(!empty($queryParams) ){
      $stmt = $this->db->prepare("SELECT * FROM meeting JOIN location ON meeting.id_location = location.id_location 
                                  WHERE lower(city) = lower(:city) OR lower(description) = lower(:description)");
      $stmt->bindParam(':city', $queryParams['q']);
      $stmt->bindParam(':description', $queryParams['q']);
      $stmt->execute();
      $tplVars['meetings_list'] = $stmt->fetchall();
      return $this->view->render($response, 'meetings.latte', $tplVars);
    }
  })->setName('searchMeetings');


  //detail meeting
  $app->get('/detail-meeting', function (Request $request, Response $response, $args) {
    $id = $request->getQueryParam('id');
    $tplVars['id'] = $id;
    try {
        $stmt = $this->db->prepare('SELECT id_meeting, start FROM meeting
                                    WHERE id_meeting=:id_meeting');
        $stmt->bindValue(':id_meeting', $id);
        $stmt->execute();
    } catch (Exception $e) {
        $this->logger->error($e->getMessage());
        die($ex->getMessage());
    }
    $meeting = $stmt->fetch();
    $tplVars['meeting'] = $meeting['start'];
    try {
        $stmt = $this->db->prepare('SELECT * FROM person_meeting
                                    JOIN (SELECT id_person, first_name, last_name FROM person) 
                                    as joined USING (id_person)
                                    WHERE id_meeting=:id_meeting');
        $stmt->bindValue(':id_meeting', $id);
        $stmt->execute();
    } catch (Exception $e) {
        $this->logger->error($e->getMessage());
        die($ex->getMessage());
    }
    $tplVars['joined'] = $stmt->fetchAll();
    return $this->view->render($response, 'detail-meeting.latte', $tplVars);
  })->setName('detail-meeting');


    // Delete person from meeting
    $app->post('/persons/delete-meeting', function(Request $request, Response $response, $args) {
    $id_person = $request->getQueryParam('id_person');

    if (!empty($id_person)) {
      try {
    
        $stmt = $this->db->prepare('DELETE FROM person_meeting WHERE id_person = :id_person');
        $stmt->bindValue(':id_person', $id_person);
        $stmt->execute();
      } catch (PDOexception $e) {
            $this->logger->error($e->getMessage());
            exit('error occured');
      }
    } else {
          exit('this person is missing');
    }
    return $response->withHeader('Location', $this->router->pathFor('detail-meeting'));
  })->setName('person_delete_meeting');


  $app->get('/newMeeting', function (Request $request, Response $response, $args) {
      $tplVars['formData'] = [
         'start' => '',
         'description' => '',
         'duration' => '',
         'city' => '',
         'street_name' => '',
         'street_number' => '',
         'zip' => '',

      ];
      return $this->view->render($response, 'newMeeting.latte', $tplVars);
  })->setName('newMeeting');


  /* spracovanie formularu po odoslani  */
  $app->post('/newMeeting', function (Request $request, Response $response, $args) {
        $formData = $request->getParsedBody();
        $tplVars = [];
        if (empty($formData['start']) || empty($formData['duration']) ){
          $tplVars['message'] = 'Please fill required fields';
        } else {
            try {
                $this->db->beginTransaction();  

                    if ( !empty($formData['street_name']) || !empty($formData['street_number']) || !empty($formData['city']) || !empty($formData['zip']) ) {
                     ## Osoba nema adresu (id_location NULL)
                     $id_location = newLocation($this, $formData);
               }
  
                 $stmt = $this->db->prepare("INSERT INTO meeting (start, description, duration, id_location) VALUES (:start, :description, :duration, :id_location)");
                 $stmt->bindValue(':start', $formData['start']);
                 $stmt->bindValue(':description', empty($formData['description'])  ? null : $formData['description']);
                 $stmt->bindValue(':duration', $formData['duration']);
                 $stmt->bindValue(':id_location', $id_location ? $id_location : null);
                 $stmt->execute();
                 $tplVars['message'] = 'Meeting succefully created';
                 $this->db->commit();
               
            } catch (PDOexception $e) {
                $tplVars['message'] = 'Error occured, sorry ako';
                $this->logger->error($e->getMessage());
                $tplVars['formData'] = $formData;
                $this->db->rollback();
            }
        }

        return $this->view->render($response, 'newMeeting.latte', $tplVars);
  });


    $app->get('/person/contact', function (Request $request, Response $response, $args) {
    $params = $request->getQueryParams(); //ziskaj vsetky parametre z url
    if (empty($params['id_person'])) {
        exit('Error in ID of person');
    } else {
        $stmtPerson = $this->db->prepare("SELECT * FROM person LEFT JOIN location on person.id_location = location.id_location WHERE id_person = :id_person ");
        $stmtContact = $this->db->prepare("SELECT person.id_person, contact.contact, contact_type.name FROM person JOIN contact ON person.id_person = contact.id_person 
                                            JOIN contact_type ON contact.id_contact_type = contact_type.id_contact_type WHERE person.id_person = :id_person");
        $stmtPerson->bindValue(':id_person', $params['id_person']);
        $stmtContact->bindValue(':id_person', $params['id_person']);
        $stmtPerson->execute();
        $stmtContact->execute();
        $tplVars['formData'] = $stmtPerson->fetch();
        $tplVars['contactData'] = $stmtContact->fetchAll();
        if (empty($tplVars['formData'])) {
            exit("Person not found.");
        } else {
        return $this->view->render($response, 'contacts.latte', $tplVars);
        }
      } 
      })->setName('contacts');


  //new contact
  $app->get('/newContact', function (Request $request, Response $response, $args) {
    $params = $request->getQueryParams();
    if (empty($params['id_person'])) {
        exit('Missing ID ');
    }
    else {
        try {
            $stmt = $this->db->prepare("SELECT name FROM contact_type");
            $stmt->execute();
        } catch (PDOException $e) {
            $this->logger->info($e);
        }
        $tplVars['contact'] = $stmt->fetchAll();
    }
    return $this->view->render($response, 'newContact.latte', $tplVars);
  })->setName('newContact');


  $app->post('/newContact', function (Request $request, Response $response, $args) {
    $formData = $request->getParsedBody();
    $params = $request->getQueryParams();
    if (empty($params['id_person'])) {
        exit('Error in ID of person');
    }
    else {
        try {
            $stmt = $this->db->prepare("INSERT INTO contact (id_person, id_contact_type, contact)
                                             VALUES (:id_person, :id_contact_type, :contact)");
            $stmt->bindValue(":id_person", $params['id_person']);
            $stmt->bindValue(":contact", $formData['contact']);
            $stmt->bindValue(":id_contact_type", $formData['contact_type']);
            $stmt->execute();
        } catch (PDOException $e) {
            $this->logger->info($e);
        }
    }
    return $response->withHeader('Location', ($this->router->pathFor('contacts'))."?id_person=".$params['id_person']);
})->setName('newContact');

 /* Delete contact */
  $app->post('/persons/info/delete', function(Request $request, Response $response, $args) {
    $id_contact = $request->getQueryParam('id_contact');
    if (!empty($id_contact)) {
      try {
    
        $stmt = $this->db->prepare('DELETE FROM contact WHERE id_contact = :id_contact');
        $stmt->bindValue(':id_contact', $id_contact);
        $stmt->execute();
          } catch (PDOexception $e) {
            $this->logger->error($e->getMessage());
            exit('error occured');
          }
    } else {
      exit('this contact is missing');
    }
    return $response->withHeader('Location', $this->router->pathFor('person-info'));
  })->setName('delete_contact');


    // relations
    $app->get('/relations', function (Request $request, Response $response, $args) {
     $params = $request->getQueryParams();
     if (empty($params['limit'])) {
        $params['limit'] = 10;
     };
     if (empty($params['page'])) {
        $params['page'] = 0;
     }
        $stmt = $this->db->query('SELECT count(*) pocet FROM relation');
        $total_pages = $stmt->fetch()['pocet'];

          $stmt = $this->db->prepare('SELECT * FROM relation JOIN relation_type  on relation.id_relation_type = relation_type.id_relation_type 
                                                             JOIN (SELECT id_person, first_name , last_name  FROM person) AS person ON relation.id_person1 = person.id_person
                                                             JOIN (SELECT id_person AS id_person2, first_name AS first_name2, last_name AS last_name2 FROM person) 
                                                                                    AS person2 ON relation.id_person2 = person2.id_person2 
                                                                                    ORDER BY first_name LIMIT :limit OFFSET :offset');

        $stmt->bindValue(':limit', $params['limit']);
        $stmt->bindValue(':offset', $params['page'] * $params['limit']);
        $stmt->execute();
  

       $tplVars = [
        'relations_list' => $stmt->fetchall(), 
        'total_pages' => $total_pages / $params['limit'], # delene limit aby tam bolo len tolko stranok kolko potrebujeme
        'page' => $params['page'],
        'limit' => $params['limit']
      ];

    return $this->view->render($response, 'relations.latte', $tplVars);
  })->setName('relations');

    $app->get('/searchRelations', function (Request $request, Response $response, $args){
    $queryParams = $request->getQueryParams(); #[klic => hodnota]
    if(!empty($queryParams) ){
      $stmt = $this->db->prepare("SELECT * FROM relation JOIN relation_type ON relation.id_relation_type = relation_type.id_relation_type 
                                                         JOIN (SELECT id_person, first_name , last_name  FROM person) AS person ON relation.id_person1 = person.id_person
                                                         JOIN (SELECT id_person AS id_person2, first_name AS first_name2, last_name AS last_name2 FROM person) 
                                                                                AS person2 ON relation.id_person2 = person2.id_person2           
                                   WHERE lower(first_name) = lower(:first_name) OR lower(first_name2) = lower(:first_name2) OR lower(last_name) = lower(:last_name) OR lower(last_name2) = lower(:last_name2)" );

      $stmt->bindParam(':first_name', $queryParams['q']);
      $stmt->bindParam(':first_name2', $queryParams['q']);
      $stmt->bindParam(':last_name', $queryParams['q']);
      $stmt->bindParam(':last_name2', $queryParams['q']);
      $stmt->execute();
      $tplVars['relations_list'] = $stmt->fetchall();
      return $this->view->render($response, 'relations.latte', $tplVars);
    }
  })->setName('searchRelations');


  $app->post('/relations/delete', function(Request $request, Response $response, $args) {
    $id_relation = $request->getQueryParam('id_relation');
    if (!empty($id_relation)) {
      try {
    
        $stmt = $this->db->prepare('DELETE FROM relation WHERE id_relation = :id_relation');
        $stmt->bindValue(':id_relation', $id_relation);
        $stmt->execute();
          } catch (PDOexception $e) {
            $this->logger->error($e->getMessage());
            exit('error occured');
          }
    } else {
      exit('this relation is missing');
    }
    return $response->withHeader('Location', $this->router->pathFor('relations'));
  })->setName('delete_relations');


# Kontrolovanie cookie
})->add(function($request, $response, $next) {
    # Vynutime si autentizaciu
    if (empty($_COOKIE['token']) || !validateToken($_COOKIE['token'], $this->db) ) {
        return $response->withHeader('Location', $this->router->pathFor('login'));
    } else {
        return $next($request, $response);
      }  
});




