package cz.mendelu.pjj.Alhambra.greenfoot;

import greenfoot.Actor;
import greenfoot.GreenfootImage;

import java.awt.*;
import java.util.Objects;

/**
 * @author xlysova
 * @version etapa 4
 */

public class LabelActor extends Actor {

    private String text;

    public LabelActor(String text){
        this.setText(text);
    }

    public LabelActor(){
        setText(getClass().getSimpleName());
    }

    private void setText(String text) {
        if(!Objects.equals(this.text, text)){
            this.text = text;
            var image = new GreenfootImage(text, 24, Color.GRAY, new Color(0,0,0));
            setImage(image);
        }
    }

    protected String updateText(){
        return null;
    }

    @Override
    public void act(){
        var newText = updateText();
        if(newText != null){
            setText(newText);
        }
    }

}
