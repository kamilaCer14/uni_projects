//
//  HotoveProdukty.cpp
//  Projekt
//
//  Created by Kamila Červáková on 16/12/2019.
//  Copyright © 2019 Kamila Červáková. All rights reserved.
//

#include "HotoveProdukty.hpp"

HotoveProdukty::HotoveProdukty(int pocet, std::string latka, float cena){
    m_pocet = pocet;
    m_latka = latka;
    m_cenaZaHotovyProdukt = cena;
}

HotoveProdukty* HotoveProdukty::createHotovePrudukty(std::string latka, int typ) {
    HotoveProdukty* newHotoveProdukty = nullptr;
    
    if(typ >= 0 && typ <= TypProduktu::s_typyProduktu.size()){
        switch (typ) {
            case 0:
                newHotoveProdukty = new HotoveProdukty(1, latka, 250);
                 break;
            case 1:
                 newHotoveProdukty = new HotoveProdukty(1, latka, 500);
                 break;
            default:
                 break;
       }
    } else {
        std::cout << "Nezname typ produktu." << std::endl;
    }
        
    return newHotoveProdukty;
}

int HotoveProdukty::getPocet(){
    return m_pocet;
}

std::string HotoveProdukty::getLatka(){
    return m_latka;
}

float HotoveProdukty::getcena(){
    return m_cenaZaHotovyProdukt;
}

void HotoveProdukty::printInfo(){
    std::cout << "Pocet: " << m_pocet << std::endl;
    std::cout << "Latka: " << m_latka << std::endl;
}

