package cz.mendelu.pjj.Alhambra.greenfoot;


import greenfoot.Greenfoot;

/**
 * @author xlysova
 * @version etapa 4
 */
public class RulesButton extends ButtonActor{


    public RulesButton(String text) {
        super("[ Rules ]");
    }

    @Override
    protected void onClick() {
        if (Greenfoot.mouseClicked(this)) {
            onClick();
        }
    }
}
