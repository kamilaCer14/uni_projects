package cz.mendelu.pjj;


import bh.greenfoot.runner.GreenfootRunner;
import cz.mendelu.pjj.Alhambra.greenfoot.AlhambraWorld;
import greenfoot.World;

public class AlhambraRunner extends GreenfootRunner{


    static {
        bootstrap(AlhambraRunner.class,
                Configuration.forWorld(AlhambraWorld.class)
                        .projectName("Hra Alhambra")
                        .hideControls(true)
        );
    }

    public static void main(String[] args) {
        GreenfootRunner.main(args);
    }
}

