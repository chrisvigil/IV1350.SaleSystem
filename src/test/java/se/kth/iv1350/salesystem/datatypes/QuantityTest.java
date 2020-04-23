/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.kth.iv1350.salesystem.datatypes;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author christopher.vigil
 */
public class QuantityTest {
    private Quantity quantity;
    private final double INIT_DOUBLE = 1.0;

    @BeforeEach
    public void setUp() {
        quantity = new Quantity(INIT_DOUBLE);
    }
    
    @AfterEach
    public void tearDown() {
        quantity = null;
    }

    /**
     * Test of getQuantityAsDouble method, of class Quantity.
     */
    @Test
    public void testGetQuantityAsDouble() {
        double expected = INIT_DOUBLE;
        double actual = quantity.getQuantityAsDouble();
        assertEquals(expected,actual,"Does not return valiue as a double correctly");
    }

    /**
     * Test of addQuantity method, of class Quantity.
     */
    @Test
    public void testAddQuantity() {
        Quantity expected = new Quantity(INIT_DOUBLE + INIT_DOUBLE);
        quantity.addQuantity(quantity);
        assertEquals(expected,quantity, "quantity does not equal the expected quanity after adding");
    }

    /**
     * Test of toString method, of class Quantity.
     */
    @Test
    public void testToString() {
        String expected = Double.toString(INIT_DOUBLE);
        String actual = quantity.toString();
        assertEquals(expected,actual,"toString method doe snot return expected string");
    }
    
    /**
     * Tests equals method of class Quantity
     */
    public void testEquals(){
        Quantity equalQuantity = new Quantity(INIT_DOUBLE);
        assertEquals(quantity,equalQuantity,"Quantities are not equal");
    }
    
    /**
     * Tests that non equal quantities do not appear equal
     */
    public void testNotEqual(){
        Quantity nonequalQuantity = new Quantity(INIT_DOUBLE + INIT_DOUBLE);
        if(quantity.equals(nonequalQuantity))
            fail("Non-equal quntities appear equal");
    }
    
}
