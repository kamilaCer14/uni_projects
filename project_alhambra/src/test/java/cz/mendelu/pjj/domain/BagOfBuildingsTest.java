package cz.mendelu.pjj.domain;

import cz.mendelu.pjj.Alhambra.Bank;
import cz.mendelu.pjj.Alhambra.Building;
import cz.mendelu.pjj.Alhambra.Color;
import cz.mendelu.pjj.Alhambra.Turn;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BagOfBuildingsTest {

    /**
     * testuje či je balík prázdny
     * @author xcervako
     * @version etapa 2
     */
    @Test
    void getEmpty() {
        //setup
        var turn = new Turn("Foo", Color.BLUE, Bank.createBank());
        var building1 = new Building();
        var building2 = new Building();
        var building3 = new Building();
        int numberOfBuildings = 3;
        //when
        for (int i=0; i<numberOfBuildings; i++) {
            if (turn.buyBuilding(building1) || turn.buyBuilding(building2) || turn.buyBuilding(building3)) {
                numberOfBuildings--;
            }
        }
        int expected = 0;
        //then
        assertEquals(expected, numberOfBuildings);
    }
}