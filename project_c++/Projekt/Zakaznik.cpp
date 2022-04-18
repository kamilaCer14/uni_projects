//
//  Zakaznik.cpp
//  Projekt
//
//  Created by Kamila Červáková on 18/12/2019.
//  Copyright © 2019 Kamila Červáková. All rights reserved.
//

#include "Zakaznik.hpp"

Zakaznik::Zakaznik(std::string meno, std::string datum){
    m_meno = meno;
    m_datum = datum;
    m_poleObjednavek.push_back(new Objednavka(1));
}

void Zakaznik::printInfo(){
    std::cout << "-----Zakaznik-----" << std::endl;
    std::cout << "    -meno zakaznika: " << m_meno << std::endl;
    std::cout << "    -datum: " << m_datum << std::endl;
}


void Zakaznik::printObjednavky(){
    for (auto *objednavka:m_poleObjednavek){
    objednavka->printInfo();
    }
    
}
