

#ifndef HotoveProdukty_hpp
#define HotoveProdukty_hpp

#include <iostream>
#include "TypProduktu.h"

class HotoveProdukty{
    
int m_pocet;
std::string m_latka;
float m_cenaZaHotovyProdukt;

public:
     HotoveProdukty(int pocet, std::string latka, float cena);

    static HotoveProdukty* createHotovePrudukty(std::string latka, int typ);
    
     int getPocet();
    
     std::string getLatka();
    
    float getcena();

     void printInfo();
};

#endif /* HotoveProdukty_hpp */
