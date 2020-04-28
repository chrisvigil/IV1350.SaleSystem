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

/**
 *
 * @author christopher.vigil
 */
public class CashRegisterTest {
    
    private CashRegister instance;
    private static final MonetaryValue CASH = new MonetaryValue("10");
    
    @BeforeEach
    public void setUp() {
        instance = new CashRegister();
    }
    
    @AfterEach
    public void tearDown() {
        instance = null;
    }

    @Test
    public void testAddToBalance() {
        MonetaryValue expected = instance.getBalance().add(CASH);
        instance.addToBalance(CASH);
        MonetaryValue actual = instance.getBalance();
        
        assertEquals(expected, actual, "Cash not updated correctly");
    }

    @Test
    public void testRemovedFromBalance() {
        MonetaryValue expected = instance.getBalance().subtract(CASH);
        instance.removedFromBalance(CASH);
        MonetaryValue actual = instance.getBalance();
        
        assertEquals(expected, actual, "Cash not updated correctly");
    }
    
}
