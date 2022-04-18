package cz.mendelu.pjj.Alhambra.greenfoot;

import cz.mendelu.pjj.Alhambra.Game;
import cz.mendelu.pjj.Alhambra.MoneyCard;
import cz.mendelu.pjj.Alhambra.Turn;
import greenfoot.Greenfoot;

/**
 * @xcervako
 * version etapa 4
 */

public abstract class TakeMoneyButton extends ButtonActor {

        private Turn turn;

    public TakeMoneyButton(Turn turn) {
            super(" [ TAKE MONEY ] ");
            this.turn = turn;
        }


        @Override
       protected void onClick() {
            String name = Greenfoot.ask("How much money you want to take?: ");
           // var turn = Turn.takeMoney();


     }

}
