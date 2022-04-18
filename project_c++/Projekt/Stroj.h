//
// Created by uzivatel on 16.12.2019.
//

#ifndef PROJEKT_STROJ_H
#define PROJEKT_STROJ_H

#include <iostream>
#

class Stroj {
    int m_vykon;
    std::string m_oznaceni;
    float m_udrzba;

    
public:
    Stroj(int vykon, std::string oznaceni);

    int getVykon();

    void odeberVykon(int vykon);

    void printInfo();
    
};


#endif //PROJEKT_STROJ_H
