package cz.mendelu.pjj.domain;

import cz.mendelu.pjj.Alhambra.Alhambra;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AlhambraTest {

    /**
     * @author xmoravc1
     * @version etapa 2
     */
    @Test
    void createAlhambra() {
        //when
        var alhambra = Alhambra.createAlhambra();
        //then
        Assertions.assertTrue(alhambra.getPlace('e',6).hasFountain());
        Assertions.assertFalse(alhambra.getPlace('j',1).hasFountain());
    }
    /**
     * @author xmoravc1
     * @version etapa 2
     */
    @Test
    void createAlhambra_identical() {
        //when
        var alhambra_1 = Alhambra.createAlhambra();
        var alhambra_2 = Alhambra.createAlhambra();
        //then
        assertSame(alhambra_1,alhambra_2);

    }

    /**
     * @author xmoravc1
     * @version etapa 2
     */

    @Test
    void getPlace() {
        //setup
        var alhambra = Alhambra.createAlhambra();

        assertThrows(IndexOutOfBoundsException.class,() -> alhambra.getPlace('z',20));
    }

}