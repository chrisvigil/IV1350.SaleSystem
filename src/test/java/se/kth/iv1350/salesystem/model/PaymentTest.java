/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.kth.iv1350.salesystem.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import se.kth.iv1350.salesystem.datatypes.MonetaryValue;
import se.kth.iv1350.salesystem.model.Payment.Type;

/**
 *
 * @author christopher.vigil
 */
public class PaymentTest {
    private final MonetaryValue AMMOUNT = new MonetaryValue("100");
    private final  MonetaryValue LESSTHENAMMOUNT = new MonetaryValue("50");
    private Payment instance;
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
        instance = null;
    }

    @Test
    public void testCalculateChange() {
        instance = new Payment(AMMOUNT, Type.CASH);
        MonetaryValue actual = instance.calculateChange(LESSTHENAMMOUNT);
        MonetaryValue expected = AMMOUNT.subtract(LESSTHENAMMOUNT).roundVaule();
        
        assertEquals(expected, actual, "Change not correctly calculated");
    }
    
    @Test
    public void testCalculateChangeForInsufficientPayment(){
        instance = new Payment(AMMOUNT, Type.CASH);
        boolean causedException = false;
        try{
            MonetaryValue actual = instance.calculateChange(AMMOUNT.add(AMMOUNT));
        }
        catch(Exception ex){
            causedException = true;
            if (!ex.getMessage().startsWith("saleTotal")){
                fail("Did not cause expected exception");
            }
        }
        
        assertTrue(causedException, "Did not cause exception");
    }
}
