package cz.mendelu.pjj.domain;

import cz.mendelu.pjj.Alhambra.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TurnTest {
    private Turn turn;

    @BeforeEach
    void init(){
        turn = new Turn("fooo", Color.BLUE, Bank.createBank());
    }

    /**
     * testuje, situaciu, ked si vezme hrac viac kariet a sucet je vacsi ako 5
     * tato situacia je proti pravidlam
     * @author xlysova
     * @version etapa 1
     */
    @Test
    void takeMoney_more_notok() {

        var moneycard1 = new MoneyCard(4);
        var moneycard2 = new MoneyCard(9);
        var moneycard3 = new MoneyCard(2);
        var moneycard4 = new MoneyCard(2);
        int sumValue = 0;
        //when
        //turn.takeMoney(moneycard1, moneycard3);
        sumValue = moneycard1.getValue() + moneycard3.getValue();
        //then
        Assertions.assertTrue(sumValue > 5);
    }

    /**
     * testuje, situaciu, ked si vezme hrac viac kariet a sucet je mensi alebo rovny 5
     * tato situacia je v sulade s pravidlami
     * @author xlysova
     * @version etapa 1
     */
    @Test
    void takeMoney_more_ok() {
        var moneycard1 = new MoneyCard(4);
        var moneycard2 = new MoneyCard(9);
        var moneycard3 = new MoneyCard(2);
        var moneycard4 = new MoneyCard(2);
        int sumValue = 0;
        //when
        //turn.takeMoney(moneycard3, moneycard4);
        sumValue = moneycard3.getValue() + moneycard4.getValue();
        //then
        //  ????
    }

    /**
     * testuje ci chce hrac polozit stavbu na prazdne policko Alhambry
     * @author xlysova
     * @version etapa 1
     */
    @Test
    void placeBuilding() {
        char ch = 'a';
        int i = '5';
        var building = new Building();
        //turn.placeBuilding(building, ch, i);
        var alhambra = turn.getAlhambra(ch, i);
        //when+then
        assertNull(alhambra);
    }


    /**
     * testuje vymenu budovy na Reserve Fielde s budovou z Alhambry
     * @author xlysova
     * @version etapa 1
     */
    @Test
    void exchangeBuildings() {
        char ch = 'a';
        int i = '5';
        var building1 = new Building();
        //turn.placeBuilding(building1, ch, i);
        var building2 = new Building();
        //var building3 = turn.switchBuilding(building2, ch, i);

        //when
        //assertNotSame(building2, building3);
        //assertSame(building1, building3);
    }

    /**
     * @author moravc1
     * @version 2
     */
    @Test
    void buyBuilding() {
        //setup
        var building = new Building();
        var hand = new Hand();

        assertEquals(building.getColor(),hand.getHand());
    }

    /**
     * @author moravc1
     * @version 2
     */
    @Test
    void addBuildingToMarket() {
        //setup
        //var turn = new Turn();
        var market = Market.createMarket();
        int maxPocetBudov = 4;
        assertNotEquals(maxPocetBudov, market.getActualBuildings());
    }

    /**
     * @author moravc1
     * @version 2
     */
    @Test
    void addBuildingToMarket_IsPossible(){
        //var turn = new Turn();
        var market = Market.createMarket();
        int maxPocetBudov = 4;
        Assertions.assertTrue(5 < market.getActualBuildings());
    }

    /**
     * testuje či hráč zaplatí presne, menej alebo viac za budovu
     * @author xcervako
     * @version etapa 2
     */
    @Test
    void nextTurn_more() {
        //setup
        //var turn = new Turn();
        Building building1 = new Building();
        //when
        turn.buyBuilding(building1);
        int cenazaBudovu = building1.getPrice();
        int suma = turn.getZaplatenaSuma(10);
        //then
        assertNotEquals(suma, cenazaBudovu);
    }

    /**
     * testuje či sú v banke presne 4 peniaze
     * @author xcervako
     * @version etapa 2
     */
    @Test
    void addMoneyToBank_() {
        //setup
        //var turn = new Turn();
        var bank = Bank.createBank();
        int maxPocetPenazi = 4;
        assertNotEquals(maxPocetPenazi, bank.getActualMoney());
    }

    /**
     * testuje či hráč zaplatí presne, menej alebo viac za budovu
     * @author xcervako
     * @version etapa 2
     */
    @Test
    void nextTurn_less() {
        //setup
        //var turn = new Turn();
        Building building1 = new Building();
        //when
        turn.buyBuilding(building1);
        int cenazaBudovu = building1.getPrice();
        int suma = turn.getZaplatenaSuma(8);
        //then
        Assertions.assertTrue(cenazaBudovu < suma );
    }

    /**
     * testuje, či je možné pridať do banky peniaze
     * @author xcervako
     * @version etapa 2
     */
    @Test
    void addMoneyToBank_isPossible() {
        //setup
       // var turn = new Turn();
        var bank = Bank.createBank();
        int maxPocetPenazi = 4;
        Assertions.assertTrue(5 < bank.getActualMoney());

    }
}