package cz.mendelu.pjj.Alhambra;

import java.io.Serializable;

/**
 * @author xlysova
 * policka, po ktorych sa budu hybat panaciky
 */
public class Square implements Serializable {

    private final Board board;
    private final char row;
    private final int col;
    private Figure figure;


    Square(Board board, char row, int col){
        this.board=board;
        this.row=row;
        this.col=col;
    }

}
