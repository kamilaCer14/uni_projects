package cz.mendelu.pjj.Alhambra.greenfoot;
import cz.mendelu.pjj.Alhambra.Game;
import greenfoot.World;

/**
 * @author xlysova
 * @version etapa 4
 * cela hracia plocha
 */

public class AlhambraWorld extends World {

    private final Game game;

    public AlhambraWorld(final Game game){
        super(1400,1000,10);
        this.game=game;
        setBackground("images/obrazovka_hry.jpg");
    }

    public AlhambraWorld(){
        this(Game.createNewGame());
    }
}
