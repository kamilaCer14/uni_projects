package cz.mendelu.pjj.Alhambra.greenfoot;

import cz.mendelu.pjj.Alhambra.Game;
import greenfoot.World;

/**
 * @author xlysova
 * @version etapa 4
 */
public class StartScreenWorld extends World {

    private final Game game;

    public StartScreenWorld(final Game game) {
        super(2000,1250,10);
        this.game=game;
        setBackground("images/alhambra.jpg");
    }

    public StartScreenWorld(){
        this(Game.createNewGame());
    }

}
