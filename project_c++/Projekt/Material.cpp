//
// Created by uzivatel on 16.12.2019.
//

#include "Material.h"


Material::Material(float mnozstvi, float cena, int typ){
    m_mnozstvi = mnozstvi;
    m_cena = cena;
    m_typ = typ;
    
}

float Material::getMnozstvi() {
    return m_mnozstvi;
}

void Material::zvysMnozstvi(int mnozstvi){
    m_mnozstvi++;
}

void Material::snizMnozstvi(int mnozstvi){
    m_mnozstvi--;
}

float Material::getCenaZaMaterial() {
    return m_cena;
}

int Material::getTyp() {
    return m_typ;
}

void Material::printInfo() {
    std::cout << "Mnozstvi materialu: " << m_mnozstvi << std::endl;
    std::cout << "Cena materialu: " << m_cena << std::endl;
}
