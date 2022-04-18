package cz.mendelu.pjj.Alhambra;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Market {

    private static Market market;
    private final List<Building> buildingInMarket = new ArrayList<>();

    /**
     * Metoda vytvoří market, kde jsou umístěny budovy.
     * @return Market je připraven ke hře
     *
     * @author xmoravc1
     * @version etapa 1
     */

    public static Market createMarket(){
        if(Market.market==null){
            Market.market = new Market();
        }
        return market;
    }

    /**
     * @author xmoravc1
     * @version etapa 3
     */
    public Market() {
        buildingInMarket.add(new Building("blue", 10, 2));
        buildingInMarket.add(new Building("red", 14, 5));
        buildingInMarket.add(new Building("green", 9, 3));
        buildingInMarket.add(new Building("black", 2, 6));
        Collections.shuffle(buildingInMarket);
    }


    /**
     * Metoda, která zjistí součastný počet budov na Marketu
     * @return vrátí počet budov na marketu
     */
    public int getActualBuildings(){
        throw new UnsupportedOperationException("Not implemented yet");
    }
}
