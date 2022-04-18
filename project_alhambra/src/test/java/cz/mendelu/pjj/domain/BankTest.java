package cz.mendelu.pjj.domain;

import cz.mendelu.pjj.Alhambra.Bank;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BankTest {

    /**
     * @author xcervako
     * @version etapa 2
     */
    @Test
    void createBank() {
        //when
        //var bank_a = Bank.createBank();
        //var bank_b = Bank.createBank();
        //then
        //assertSame(bank_a, bank_b);
    }

    /**
     * @author xcervako
     * @version etapa 2
     */
    @Test
    void testCreateBank() {
        //when
        var banka = Bank.createBank();
        //then
        assertNotNull(banka.createBank());
    }

}