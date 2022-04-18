//
// Created by uzivatel on 26.01.2020.
//

#ifndef PROJEKT_VANKUSTOVARNA_H
#define PROJEKT_VANKUSTOVARNA_H

#include "HotoveProduktyTovarna.h"

class VankusTovarna : public HotoveProduktyTovarna {
public:
    HotoveProdukty* createHotoveProdukty(std::string latka);
};


#endif //PROJEKT_VANKUSTOVARNA_H
