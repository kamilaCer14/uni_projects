//
// Created by uzivatel on 26.01.2020.
//

#include "TypMaterialu.h"

std::vector<std::string> TypMaterialu::s_typyMaterialu = {"BAVLNA", "FLANEL", "BAMBUS", "SATEN"};

void TypMaterialu::printMaterialy() {
    std::cout << std::endl;
    std::cout << "Typy materialu:" << std::endl;
    for(int i=0; i < s_typyMaterialu.size(); i++){
        std::cout << i << ". " << s_typyMaterialu[i] << std::endl;
    }
    std::cout << std::endl;
}