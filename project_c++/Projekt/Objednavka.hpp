//
//  Objednavka.hpp
//  Projekt
//
//  Created by Kamila Červáková on 16/12/2019.
//  Copyright © 2019 Kamila Červáková. All rights reserved.
//


#ifndef Objednavka_hpp
#define Objednavka_hpp

#include <iostream>
#include "Engine.hpp"


class Objednavka{
    int m_pocetProduktu;
    float m_penize;
    
public:
    Objednavka(int pocetProduktu);
    
    void prijmiPenize(float penize);
    
    void printInfo();
    
};

#endif /* Objednavka_hpp */
