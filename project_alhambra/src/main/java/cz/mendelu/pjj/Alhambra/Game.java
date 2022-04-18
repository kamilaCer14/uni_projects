package cz.mendelu.pjj.Alhambra;

import java.io.Serializable;
import java.util.Collections;
import java.util.*;


public class Game implements Serializable {
    int numberOfPlayers;

    private final List<Player> players = new ArrayList<>();
    private final List<MoneyCard> MoneyInHand = new ArrayList<>();
    private final List<MoneyCard> moneyInBank = new ArrayList<>();
    private final List<MoneyCard> moneyInDeck = new ArrayList<>();

    public List<MoneyCard> moneyInHand() {
        return Collections.unmodifiableList(MoneyInHand);
    }
    public List<MoneyCard> getMoneyInDeck(){ return Collections.unmodifiableList(MoneyInHand); }
    public List<MoneyCard> moneyInBank(){
        return Collections.unmodifiableList(moneyInBank);
    }


    public Game(){

    }

    /**
     * Metóda sa spustí na začiatku hry.
     * 1. Najprv skontroluje, či zadaný počet hráčov neprekračuje max. povolený, čiže 3
     * 2. Potom zabezpečí, že každý  hráč dostane na začiatku hry penažné karty, ktoré musia mať v súčte hodnotu min. 20,
     * pričom nezáleží na mene ani na počte kariet. Keď dosiahnú karty súčet 20, prejde na ďalšieho hráča.
     * @author xlysova
     * @version etapa 3
     */

    public Game(int numberOfPlayers) {
        if(numberOfPlayers<3) {
            for (int i = 0; i < numberOfPlayers; i++) {
                this.setupPlayers(Player.getName(), Player.getColor());
                this.giveMoney(i, moneyInDeck.get(moneyInDeck.size()));
            }
        }else {
            System.out.println("Max pocet hracov je 3");
        }

        }


    /**
     * Metoda setupPlayers() umožní hráčom zadať ich meno a vybrať si farbu na začiatku hry.
     * @author xlysova
     * @version etapa 3
     */

    // TODO
    void setupPlayers(String name, Color color) {
        players.add(new Player());
    // na obrazovke sa ukazu mozne farby - red, green, blue


    }
    /**
     * "LIST"
     * práca s listom
     * Dáva hráčom penažné karty na začiatku hry, až kým súčet na ruke nebude 20
     * @author xlysova
     * @version etapa 3
     */
    void giveMoney(int playerOrder, MoneyCard money) {
        int sumOfMoney=0;
        Collections.shuffle(moneyInDeck);
        while(sumOfMoney<=20){
            moneyInDeck.remove(0);
            moneyInHand().get(moneyInDeck.size());
            sumOfMoney+=money.getValue();
        }
    }



    public Player getPlayer(int playerOrder) {
        return new Player();
    }

    public int getNumberOfPlayers() {
        return numberOfPlayers;
    }


    /**
     * @author xlysova
     * @version etapa 4
     * nacita uvodnu obrazovku hry s tlacitkom start game
     */

    private Board board;


    public static Game createNewGame(){
        final char maxRow = 'z';
        final int maxCol=50;
        var game = new Game();
        var board = game.board = new Board(game, maxRow, maxCol);
        for(char r = 'g'; r<='t'; r++) {
            for (int c = 11; c <= 41; c++) {
                Square s = board.getSquare(r, c);
            }
        }
        return game;
    }

}
