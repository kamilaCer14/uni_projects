//
//  main.cpp
//  Projekt
//
//  Created by Kamila Červáková on 16/12/2019.
//  Copyright © 2019 Kamila Červáková. All rights reserved.
//

#include <iostream>
#include "Engine.hpp"

int main(){
    Engine* engine = NULL;
    engine ->createEngine();
    engine ->start();
    
    delete engine;
    return 0;
}
