package cz.mendelu.pjj.domain;

import cz.mendelu.pjj.Alhambra.Market;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MarketTest {

    /**
     * @author xmoravc1
     * @version etapa 2
     */
    @Test
    void createMarket() {
        //when
        var market = new Market();
        //then
        assertNotNull(market.createMarket());
    }
}