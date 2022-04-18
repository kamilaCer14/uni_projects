//
// Created by uzivatel on 16.12.2019.
//

#ifndef PROJEKT_VYROBNA_H
#define PROJEKT_VYROBNA_H

#include <iostream>
#include "Stroj.h"
#include <vector>
#include "Material.h"
#include "Sklad.hpp"
#include "HotoveProdukty.hpp"

class Vyrobna {
private:
    std::vector<Stroj*> m_stroje;
    Vyrobna();

public:
    static Vyrobna * createVyrobna();
    
    void koupitStroj();

    void prodatStroj();

    void printInfo();
    
    
};


#endif //PROJEKT_VYROBNA_H
