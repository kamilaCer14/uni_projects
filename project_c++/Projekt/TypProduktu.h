//
// Created by uzivatel on 26.01.2020.
//

#ifndef PROJEKT_TYPPRODUKTU_H
#define PROJEKT_TYPPRODUKTU_H

#include <iostream>
#include <vector>

class TypProduktu {
public:
    static std::vector<std::string> s_typyProduktu;
    static void printProdukty();
private:
    TypProduktu() = default;
    ~TypProduktu() = default;
};


#endif //PROJEKT_TYPPRODUKTU_H
