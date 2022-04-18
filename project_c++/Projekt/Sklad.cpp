//
//  Sklad.cpp
//  Projekt
//
//  Created by Kamila Červáková on 16/12/2019.
//  Copyright © 2019 Kamila Červáková. All rights reserved.
//

#include "Sklad.hpp"



Sklad* Sklad::createSklad(){
    return new Sklad();
}


void Sklad::pridajMaterial(int typ, int pocet){
         for(int i =0; i < m_seznamMaterialu.size(); i++){
           if(m_seznamMaterialu.at(i)->getTyp() == typ){
             m_seznamMaterialu.at(i)->zvysMnozstvi(pocet);
           }
         }
}


void Sklad::odeberMaterial(int typ, int pocet){
         for(int i =0; i < m_seznamMaterialu.size(); i--){
           if(m_seznamMaterialu.at(i)->getTyp() == typ){
              m_seznamMaterialu.at(i)->snizMnozstvi(pocet);
           }
         }
}


void Sklad::printInfo(){
    std::cout << "------Sklad materialu-----" << std::endl;
    for (auto *material:m_seznamMaterialu){
        material->printInfo();
    }
    for (auto *produkt:m_seznamProduktu){
        produkt->printInfo();
    }
}



