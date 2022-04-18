//
// Created by uzivatel on 26.01.2020.
//

#ifndef PROJEKT_HOTOVEPRODUKTYTOVARNA_H
#define PROJEKT_HOTOVEPRODUKTYTOVARNA_H

#include "HotoveProdukty.hpp"
#include "TypProduktu.h"
#include "TypMaterialu.h"

class HotoveProduktyTovarna {
public:
    virtual HotoveProdukty* createHotovePrudukty(std::string latka) = 0;

    virtual ~HotoveProduktyTovarna(){};
};


#endif //PROJEKT_HOTOVEPRODUKTYTOVARNA_H
