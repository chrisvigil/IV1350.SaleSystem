/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.kth.iv1350.salesystem.datatypes;

import java.math.BigDecimal;
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
    private final double INIT_DOUBLE = 1.5;

    @BeforeEach
    public void setUp() {
        quantity = new Quantity(INIT_DOUBLE);
    }
    
    @AfterEach
    public void tearDown() {
        quantity = null;
    }

    @Test
    public void testGetQuantityAsDouble() {
        double expected = INIT_DOUBLE;
        double actual = quantity.getQuantityAsDouble();
        assertEquals(expected,actual,"Does not return valiue as a double correctly");
    }

    @Test
    public void testAddQuantity() {
        Quantity expected = new Quantity(INIT_DOUBLE + INIT_DOUBLE);
        quantity.addQuantity(quantity);
        assertEquals(expected,quantity, "quantity does not equal the expected quanity after adding");
    }
    
    @Test
    public void testRemoveQuantity(){
        double remove = 0.5;
        Quantity expected = new Quantity(INIT_DOUBLE - remove);
        quantity.removeQuantity(new Quantity(remove));
        assertEquals(expected,quantity, "quantity does not equal the expected quanity after removing");
    }

    @Test
    public void testToString() {
        String expected = Double.toString(INIT_DOUBLE);
        String actual = quantity.toString();
        assertEquals(expected,actual,"toString method doe snot return expected string");
    }
    
    @Test
    public void testToBigDecimal(){
        BigDecimal expected = new BigDecimal(INIT_DOUBLE);
        BigDecimal actual = quantity.toBigDecimal();
        assertEquals(expected,actual,"Does not correctly convert to BigDecimal");
    }
    
    @Test
    public void testEquals(){
        Quantity equalQuantity = new Quantity(INIT_DOUBLE);
        assertEquals(quantity,equalQuantity,"Quantities are not equal");
    }
    
    @Test
    public void testNotEqual(){
        Quantity nonequalQuantity = new Quantity(INIT_DOUBLE + INIT_DOUBLE);
        if(quantity.equals(nonequalQuantity))
            fail("Non-equal quntities appear equal");
    }
    
     @Test
    public void testHashCode() {
        Quantity duplicateQuantity = new Quantity(INIT_DOUBLE);
        if (quantity.hashCode() != duplicateQuantity.hashCode())
            fail("Hash codes of two identical Quantityies do not match");
    }
    
}
