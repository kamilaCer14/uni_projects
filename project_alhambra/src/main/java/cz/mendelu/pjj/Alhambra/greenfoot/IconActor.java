package cz.mendelu.pjj.Alhambra.greenfoot;

import greenfoot.Actor;
import greenfoot.GreenfootImage;

import java.util.Objects;

/**
 * ikony "panacikov", kt sa budu hybat po boarde
 */

public abstract class IconActor extends Actor {

    private GreenfootImage image;

    /**
     * Uprava metetody tak, aby v případě požadavku na nastevní již zobrazené ikony se neic nedělo.
     */
    @Override
    public void setImage(GreenfootImage image) {
        if (!Objects.equals(this.image, image)) {
            this.image = image;
            super.setImage(image);
        }
    }

    protected abstract GreenfootImage updateIcon();

    @Override
    public void act() {
        var icon = updateIcon();
        if (icon != null) {
            setImage(icon);
        }
    }
}
