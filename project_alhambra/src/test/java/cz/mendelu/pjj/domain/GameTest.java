package cz.mendelu.pjj.domain;

import cz.mendelu.pjj.Alhambra.Game;
import cz.mendelu.pjj.Alhambra.Player;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {

    @Test
    /**testuje ci ma hrac rozdanych aspon 20 penaznych jednotiek
    (sucet hodnot na penaznych kartach >=20)
     * @author xlysova
     * @version etapa 1
     */
    void setupPlayers() {
        //1. vyrobime hru, nainicializujeme ju (rozdame peniaze)
        Game game = new Game(3);
        //2. vyberieme hraca
        Player hrac1 = game.getPlayer(1);
        //3. skontrolujeme jeho peniaze
        int count1 = hrac1.countMoney();
        assertTrue(count1 >= 20);
    }

    //assertThrows(IndexOutOfBoundsException.class, ()->); --- ci su len 3 hraci
    @Test
    /**testuje ci sedi zadany pocet hracov
     * @author xlysova
     * @version etapa 1
     */
    void setupPlayers_2() {
        //1. vyrobime hru, nainicializujeme ju (rozdame peniaze)
        Game game = new Game(3);
        //2. vyberieme hraca
        Player hrac4 = game.getPlayer(4);
        //4. stvrty hrac nemoze byt, kedze mame len 3 hracov
        assertThrows(IndexOutOfBoundsException.class, ()->game.getNumberOfPlayers());
    }

    @Test
    /** otestuje, ci ma hrac aspon 1 kartu
     * @author xlysova
     * @version etapa 1
     */
    void setupPlayers_3() {
        //1. vyrobime hru, nainicializujeme ju (rozdame peniaze)
        Game game = new Game(3);
        //2. vyberieme hraca
        Player hrac1 = game.getPlayer(1);
        //3. zistime pocet penaznych kariet
        int n = hrac1.getNumberOfMoneyCards();
        assertTrue(n>=1);
    }


}