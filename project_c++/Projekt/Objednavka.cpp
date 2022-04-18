//
//  Objednavka.cpp
//  Projekt
//
//  Created by Kamila Červáková on 16/12/2019.
//  Copyright © 2019 Kamila Červáková. All rights reserved.
//

#include "Objednavka.hpp"

Objednavka::Objednavka(int pocetProduktu){
    m_pocetProduktu = pocetProduktu;
    m_penize = 0.0;
}


void Objednavka::printInfo(){
    std::cout << "Pocet produktu: " << m_pocetProduktu << std::endl;
}
