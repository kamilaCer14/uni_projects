//
// Created by uzivatel on 26.01.2020.
//

#ifndef PROJEKT_TYPMATERIALU_H
#define PROJEKT_TYPMATERIALU_H

#include <iostream>
#include <vector>

class TypMaterialu {
public:
    static std::vector<std::string> s_typyMaterialu;
    static void printMaterialy();
private:
    TypMaterialu() = default;
    ~TypMaterialu() = default;
};


#endif //PROJEKT_TYPMATERIALU_H
