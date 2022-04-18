//
//  Engine.cpp
//  Projekt
//
//  Created by Kamila Červáková on 18/12/2019.
//  Copyright © 2019 Kamila Červáková. All rights reserved.
//

#include "Engine.hpp"

Engine::Engine(float penize){
    m_penize = penize;
}

Engine* Engine::createEngine(){
    Sklad::createSklad();
    Vyrobna::createVyrobna();
    return new Engine(1000000);
}


float Engine::getPenize(){
    return m_penize;
}


void Engine::start(){
        int pom;
    std::cout << " _       __________    __________  __  _________   __" << std::endl;
    std::cout << "| |     / / ____/ /   / ____/ __ \\/  |/  / ____/  / /" << std::endl;
    std::cout << "| | /| / / __/ / /   / /   / / / / /|_/ / __/    / / " << std::endl;
    std::cout << "| |/ |/ / /___/ /___/ /___/ /_/ / /  / / /___   /_/  " << std::endl;
    std::cout << "|__/|__/_____/_____/\\____/\\____/_/  /_/_____/  (_)   " << std::endl;
    std::cout << std::endl;
        std::cout << "Tvuj pocatecni kapital je 1 000 000" << std::endl;
        std::cout << std::endl;
        std::cout << "1 ... Vytvorit objednavku" << std::endl;
        std::cout << "2 ... Koupit material" << std::endl;
        std::cout << "3 ... Vytvorit produkt" << std::endl;
        std::cout << "4 ... Vypis informace" << std::endl;
        std::cin >> pom;
       

    while (pom != 0){
        switch (pom){
                int pocet, material, typ;
                Objednavka* newObjednavka;
                
            case 1 :
                
                std::cout << "Zadajte počet produktov: " << std::endl;
                std::cin >> pocet;
                newObjednavka = new Objednavka(pocet);
                for (int i=0; i<pocet; i++){
                
                    std::cout << "Aky si prosite typ produktu ?" << std::endl;
                    TypProduktu::printProdukty();
                    std::cout << "Zvolte: " ;
                    std::cin >> typ;
                    
                    std::cout << "Aky si prosite material ?" << std::endl;
                    TypMaterialu::printMaterialy();
                    std::cout << "Zvolte: " ;
                    std::cin >> material;
            
                }
                std::cout << "--------------------" << std::endl;
               break;
           
            case 2 :
                
                break;
            case 3 :
                
                std::cout << "Vytvorene produkty: " << std::endl;
                for(HotoveProdukty *hotoveProdukty:m_hotoveProdukty){
                    hotoveProdukty->getPocet();
                }
                break;
            case 4 :
                    for (auto *sklad:m_sklad){
                        sklad->printInfo();
                    }
                    for (auto *vyrobna:m_vyrobna){
                        vyrobna->printInfo();
                    }
                    break;

        }
        std::cout << std::endl;
        std::cout << "1 ... Vytvorit objednavku" << std::endl;
        std::cout << "2 ... Koupit material" << std::endl;
        std::cout << "3 ... Vytvorit produkt" << std::endl;
        std::cout << "4 ... Vypis informace" << std::endl;
        std::cin >> pom;

    }

 }
