//
// Created by uzivatel on 26.01.2020.
//

#ifndef PROJEKT_PERINATOVARNA_H
#define PROJEKT_PERINATOVARNA_H

#include "HotoveProduktyTovarna.h"

class PerinaTovarna : public HotoveProduktyTovarna {
public:
    HotoveProdukty* createHotoveProdukty(std::string latka);
};


#endif //PROJEKT_PERINATOVARNA_H
