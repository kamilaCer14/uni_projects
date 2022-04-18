//
// Created by uzivatel on 26.01.2020.
//

#include "TypProduktu.h"

std::vector<std::string> TypProduktu::s_typyProduktu = {"VANKUS", "PERINA"};

void TypProduktu::printProdukty() {
    std::cout << std::endl;
    std::cout << "Typy produktu:" << std::endl;
    for(int i=0; i < s_typyProduktu.size(); i++){
        std::cout << i << ". " << s_typyProduktu[i] << std::endl;
    }
    std::cout << std::endl;
}