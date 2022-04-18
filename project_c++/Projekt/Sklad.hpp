//
//  Sklad.hpp
//  Projekt
//
//  Created by Kamila Červáková on 16/12/2019.
//  Copyright © 2019 Kamila Červáková. All rights reserved.
//

#ifndef Sklad_hpp
#define Sklad_hpp

#include <iostream>
#include <vector>
#include "TypMaterialu.h"
#include "Material.h"
#include "HotoveProdukty.hpp"

class Sklad{
private:
    std::vector<Material*> m_seznamMaterialu;
    std::vector<HotoveProdukty*> m_seznamProduktu;
    Sklad() = default;
    
public:
    
    static Sklad* createSklad();
    
    void pridajMaterial(int typ, int pocet);
    
    void odeberMaterial(int typ, int pocet);
    
    void printInfo();
    
    void odeberHotovyProdukt(HotoveProdukty* produkt);
    
    
};


#endif /* Sklad_hpp */
