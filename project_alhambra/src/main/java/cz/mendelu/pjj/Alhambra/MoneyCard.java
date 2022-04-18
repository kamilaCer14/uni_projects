package cz.mendelu.pjj.Alhambra;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MoneyCard {
    MoneyCurrency currency;
    int value;

    private final List<MoneyCard> moneyInHand = new ArrayList<>();
    private final List<MoneyCard> moneyInBank = new ArrayList<>();
    private final List<MoneyCard> moneyInDeck = new ArrayList<>();
    public List<MoneyCard> moneyInHand() {
        return Collections.unmodifiableList(moneyInHand);
    }
    public List<MoneyCard> getMoneyInDeck(){ return Collections.unmodifiableList(moneyInHand); }
    public List<MoneyCard> moneyInBank(){
        return Collections.unmodifiableList(moneyInBank);
    }

    /**
     * @author xlysova
     * ulozim si vsetky penazne karty, ktoré existujú
     * 4 meny (farby), z kazdej hodnoty 1-9
     * dokopy 108 kariet (kazda karta je tam 3 krat)
     */

    public static final MoneyCard[] MONEYS = {           //S na konci len pre lepsiu prehladnost
            new MoneyCard(MoneyCurrency.Blue, 1),
            new MoneyCard(MoneyCurrency.Blue, 2),
            new MoneyCard(MoneyCurrency.Blue, 3),
            new MoneyCard(MoneyCurrency.Blue, 4),
            //...
            new MoneyCard(MoneyCurrency.Green, 1),
            new MoneyCard(MoneyCurrency.Green, 3),
            new MoneyCard(MoneyCurrency.Green, 5),
            //...
            new MoneyCard(MoneyCurrency.Orange, 7),
            new MoneyCard(MoneyCurrency.Orange, 8),
            new MoneyCard(MoneyCurrency.Orange, 9),
            //...
            new MoneyCard(MoneyCurrency.Yellow, 1),
            new MoneyCard(MoneyCurrency.Yellow, 6),
            new MoneyCard(MoneyCurrency.Yellow, 9),
            //...
    };

    public MoneyCard(MoneyCurrency currency, int value) {
        this.currency = currency;
        this.value = value;
    }

    public MoneyCard(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
