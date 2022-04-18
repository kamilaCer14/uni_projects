package cz.mendelu.pjj.Alhambra.greenfoot;


import cz.mendelu.pjj.Alhambra.Game;
import greenfoot.Greenfoot;

/**
 * bude na uvodnej obrazovke
 */

/**
 * @author xlysova
 * @version etapa 4
 */

public class StartGameButton extends ButtonActor{
    private Game game;

    public StartGameButton(Game game){
        super("START GAME");
        this.game = game;
    }

    @Override
    protected void onClick() {
        String numberOfPlayers = Greenfoot.ask("Enter number of players");

    }

}

