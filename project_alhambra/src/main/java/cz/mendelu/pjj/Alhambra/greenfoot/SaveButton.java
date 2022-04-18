package cz.mendelu.pjj.Alhambra.greenfoot;

import cz.mendelu.pjj.Alhambra.Alhambra;
import greenfoot.Greenfoot;

/**
 * @xcervako
 * version etapa 4
 */
public class SaveButton extends ButtonActor {
    private final Alhambra alhambra;

    public SaveButton(Alhambra alhambra) {
        super(" [ Save ] ");
        this.alhambra = alhambra;
    }

    @Override
    protected void onClick() {
        String name = Greenfoot.ask("Name of game: ");
        Alhambra.save(alhambra, name);
    }
}