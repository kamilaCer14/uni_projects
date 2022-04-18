//
//  Engine.hpp
//  Projekt
//
//  Created by Kamila Červáková on 18/12/2019.
//  Copyright © 2019 Kamila Červáková. All rights reserved.
//

#ifndef Engine_hpp
#define Engine_hpp

#include <iostream>
#include <iomanip>
#include <vector>
#include "Sklad.hpp"
#include "Vyrobna.h"
#include "Material.h"
#include "HotoveProdukty.hpp"
#include "Objednavka.hpp"
#include "HotoveProduktyTovarna.h"


class Engine{
private:
    float m_penize;
    std::vector<Sklad *> m_sklad;
    std::vector<Vyrobna *> m_vyrobna;
    std::vector<HotoveProdukty *> m_hotoveProdukty;
    Engine(float penize);
    
public:

    static Engine* createEngine();
    
    float getPenize();
    
    void printSklad();
    
    void printVyrobna();
    
    void start();
    

    
};


#endif
