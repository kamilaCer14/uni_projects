package cz.mendelu.pjj.Alhambra;

import java.util.*;

public class ReserveField {

    int index;

    /**
     * "MAP"
     * praca s mapou
     * @author xlysova
     */
    public Map<Integer, Building> reserveField = new HashMap<>();

    public void putOnReserveField(int index, Building building){
        reserveField.put(index, building);
    }

    public int getIndex(){
        index=reserveField.size()+1;
        return index;
    }
}
