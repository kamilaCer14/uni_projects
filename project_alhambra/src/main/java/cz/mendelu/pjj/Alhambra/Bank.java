package cz.mendelu.pjj.Alhambra;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * tu budu ulozene peniaze, ktore mame na vyber "na stole"
 */
public class Bank {

    private static Bank bank;

    private final List<MoneyCard> moneyInBank = new ArrayList<>();

    private final List<MoneyCard> moneyInDeck = new ArrayList<>();

    public List<MoneyCard> moneyInBank(){
        return Collections.unmodifiableList(moneyInBank);
    }

    private Bank() {
        // TODO deck, nacitanie peniazi
        moneyInDeck.add(new MoneyCard(2));
        moneyInDeck.add(new MoneyCard(1));
        moneyInDeck.add(new MoneyCard(3));
        moneyInDeck.add(new MoneyCard(9));
     //   moneyInDeck.add(new MoneyCard(5));     // lebo v Banke maju byť len 4 karty
     //   moneyInDeck.add(new MoneyCard(6));
        Collections.shuffle(moneyInDeck);
    }




    /**
     * Metóda addMoneyToBank() sa zavolá v prípade ak na hracej doske nebudú práve 4 karty penazí. Táto metóda zoberie
     * karty z balička a doplní ich do banky.
     * @return prida peniaze do banky ak je to možné
     * @throws IndexOutOfBoundsException
     *
     * @author xcervako
     * @version etapa 2
     */
    public void addMoneyToBank() {
        //FIXME otacanie balicka kariet??
        while(moneyInBank.size()<4 && moneyInDeck.size()>0){
            moneyInBank.add(moneyInDeck.remove(moneyInDeck.size()));
        }
    }


    /**
     * Továrenská metóda createBank() zoberie peniaze z balíčka a vytvorí banku na hracej doske so
     * 4 kartičkami penazí.
     *
     * @return Bank
     * @author xcervako
     * @version etapa 2
     */
    public static Bank createBank() {
        if(Bank.bank==null) {
            Bank.bank = new Bank();
        }
        return bank;
    }
    //TODO aj na markete



    /**
     * Metoda, ktorá zistí počet penazí v Banke
     * @return vrátí počet penazí v Banke
     */
    public int getActualMoney() {
        throw new UnsupportedOperationException("Not implemented yet");
    }


    public MoneyCard chooseCard(int i){
        return moneyInBank.remove(i);
    }



}



