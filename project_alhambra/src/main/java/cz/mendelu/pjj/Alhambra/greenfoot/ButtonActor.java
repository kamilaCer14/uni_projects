package cz.mendelu.pjj.Alhambra.greenfoot;

import greenfoot.Greenfoot;

/**
 * @xcervako
 * version etapa 4
 */

public abstract class ButtonActor extends LabelActor {

    public ButtonActor(String text) {
        super(text);
    }

    protected abstract void onClick();

    @Override
    public void act() {
        if (Greenfoot.mouseClicked(this)) {
            onClick();
        }
    }
}

