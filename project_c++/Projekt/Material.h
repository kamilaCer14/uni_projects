//
// Created by uzivatel on 16.12.2019.
//

#ifndef PROJEKT_MATERIAL_H
#define PROJEKT_MATERIAL_H

#include <iostream>
#include "TypMaterialu.h"



class Material {
private:
    float m_mnozstvi;
    float m_cena;
    int m_typ;

public:
    Material(float mnozstvi, float cena, int typ);

    float getMnozstvi();
    
    void zvysMnozstvi(int mnozstvi);
    
    void snizMnozstvi(int mnozstvi);

    float getCenaZaMaterial();

    int getTyp();

    void printInfo();
};


#endif //PROJEKT_MATERIAL_H
