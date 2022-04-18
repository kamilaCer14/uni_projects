package cz.mendelu.pjj.Alhambra;

import java.util.ArrayList;
import java.util.List;

public class BagOfBuildings {

    private final List<Building> buildings = new ArrayList<>();

    int maxCards;
    int minCards;
    Color myColor;


    public BagOfBuildings(Color myColor) {
        this.maxCards = 54;
        this.minCards = 1;
        this.myColor = myColor;
    }


    /** práca s kolekciou
     *  @author xcervako
     * @version etapa 3
     * */
    public void addBuilding(){
        buildings.add(new Building());

    }

    /** práca s kolekciou
     *  @author xcervako
     * @version etapa 3
     * */
    public void numberOfBuildings(){
        System.out.println(buildings.size());
    }



    /**
     * Metóda vráti informáciu o tom, či je prázdny balík s budovami. Ak to bude true tak hra končí.
     * @return true/false
     *
     * @author xcervako
     * @version etapa 3
     */
    boolean getEmpty(BagOfBuildings objekt){

        if (buildings.size() == 0 ){
            return true;
        } else
            return false;
    }



}
