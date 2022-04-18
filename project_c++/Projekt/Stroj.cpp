//
// Created by uzivatel on 16.12.2019.
//

#include "Stroj.h"

Stroj::Stroj(int vykon, std::string oznaceni) {
    m_vykon = vykon;
    m_oznaceni = oznaceni;
    m_udrzba = 50000;
}

int Stroj::getVykon() {
    return m_vykon;
}

void Stroj::odeberVykon(int vykon) {
    vykon--;
}
    
void Stroj::printInfo() {
    std::cout << "Stroj: " << std::endl;
    std::cout << "  -oznaceni stroje: " << m_oznaceni << std::endl;
    std::cout << "  -vykon: " << m_vykon << std::endl;
    std::cout << "  -udrzba: " << m_udrzba << std::endl;
}

