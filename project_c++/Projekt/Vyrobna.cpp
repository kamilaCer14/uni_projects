//
// Created by uzivatel on 16.12.2019.
//

#include "Vyrobna.h"



Vyrobna::Vyrobna(){
    m_stroje.push_back(new Stroj(1000, "StrojZakladni"));
}

Vyrobna* Vyrobna::createVyrobna(){
    return new Vyrobna();
}

void Vyrobna::koupitStroj() {
    m_stroje.push_back(new Stroj(1000, "Stroj c. " + std::to_string(m_stroje.size())));
}

void Vyrobna::prodatStroj() {
    delete (m_stroje.at(m_stroje.size()));
}

void Vyrobna::printInfo() {
    std::cout << "Sklad --------------- " << std::endl;
    for (auto *stroj:m_stroje){
        stroj->printInfo();
    }
}

