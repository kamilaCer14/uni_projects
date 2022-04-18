package cz.mendelu.pjj.Alhambra.greenfoot;

import cz.mendelu.pjj.Alhambra.Alhambra;
import cz.mendelu.pjj.Alhambra.Building;
import cz.mendelu.pjj.Alhambra.Game;
import cz.mendelu.pjj.Alhambra.Turn;
import greenfoot.Greenfoot;

/**
 * prepne na dalsieho hraca resp. na toho isteho, ak dal presne penazi za budovu
 * @xcervako
 * version etapa 4
 */

public class NextTurnButton extends ButtonActor {

    private Turn turn;


    public NextTurnButton(Turn turn) {
        super(" [ NEXT TURN ] ");
        this.turn = turn;
    }

    @Override
    protected void onClick() {
         //      var turn = Turn.nextTurn(....);

    }
}
