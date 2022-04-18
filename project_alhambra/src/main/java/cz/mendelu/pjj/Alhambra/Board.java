package cz.mendelu.pjj.Alhambra;

import java.io.Serializable;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

public class Board implements Iterable<Square>, Serializable {

    static String key(char row, int col){
        return String.format("%s%d", row, col);
    }

    private final Game game;
    private final char maxRow;
    private final int maxCol;

    private final Map<String, Square> squares;


    /**
     * vytvorenie prazdnej hracej plochy pozadovanej velkosti
     */

    Board(Game game, char maxRow, int maxCol){
        this.game=game;
        this.maxRow=maxRow;
        this.maxCol=maxCol;

        squares = new LinkedHashMap<>();
        for(char r = 'a'; r<=maxRow; r++){
            for(int c = 1; c<=maxCol; c++){
                Square s = new Square(this, r, c);
                squares.put(key(r,c), s);
            }
        }
    }

    @Override
    public Iterator<Square> iterator() {
        return null;
    }

    Square getSquare(char row, int col) {
        String key = key(row,col);
        if (squares.containsKey(key)) {
            throw new IndexOutOfBoundsException(String.format("Square [%s%d] not found.", row, col));
        }
        return squares.get(key);
    }
}
