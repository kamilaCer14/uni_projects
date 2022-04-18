//
//  Zakaznik.hpp
//  Projekt
//
//  Created by Kamila Červáková on 18/12/2019.
//  Copyright © 2019 Kamila Červáková. All rights reserved.
//

#ifndef Zakaznik_hpp
#define Zakaznik_hpp

#include "Objednavka.hpp"
#include <vector>

class Zakaznik{
    std::string m_meno;
    std::string m_datum;
    std::vector<Objednavka*> m_poleObjednavek;
    
public:
    Zakaznik(std::string meno, std::string datum);
    
    void printInfo();
    
    void printObjednavky();
    
};

#include <stdio.h>

#endif /* Zakaznik_hpp */
