package cz.mendelu.pjj.Alhambra;

import cz.mendelu.pjj.Alhambra.MoneyCard;

import java.util.HashMap;
import java.util.Map;

public class BagOfMoneyCards {

    private final Map<Integer, MoneyCard> peniaze = new HashMap<>();


    /** práca s kolekciou
     *  @author xcervako
     * @version etapa 3
     * */
    public void addCard(MoneyCard a, int howMuch){
        peniaze.put(howMuch, a);

    }





}
