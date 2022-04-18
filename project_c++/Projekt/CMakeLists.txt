cmake_minimum_required(VERSION 3.15)
project(Projekt)

set(CMAKE_CXX_STANDARD 14)

add_executable(Projekt main.cpp Engine.cpp Engine.hpp HotoveProdukty.cpp HotoveProdukty.hpp Material.h Material.cpp
        Objednavka.hpp Objednavka.cpp Sklad.cpp Sklad.hpp Stroj.h Stroj.cpp
        Vyrobna.cpp Vyrobna.h Zakaznik.cpp Zakaznik.hpp HotoveProduktyTovarna.h HotoveProduktyTovarna.cpp
        VankusTovarna.cpp VankusTovarna.h PerinaTovarna.cpp PerinaTovarna.h TypProduktu.h TypProduktu.cpp
        TypMaterialu.h TypMaterialu.cpp)