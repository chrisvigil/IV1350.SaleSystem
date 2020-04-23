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

public class MonetaryValueTest {
    private final String INIT_VALUE = "10";
    private MonetaryValue monetaryValue;
    
    @BeforeEach
    public void setUp() {
        monetaryValue = new MonetaryValue(INIT_VALUE);
    }
    
    @AfterEach
    public void tearDown() {
        monetaryValue = null;
    }

    /**
     * Test of add method, of class MonetaryValue.
     */
    @org.junit.jupiter.api.Test
    public void testAdd() {
        MonetaryValue anotherValue = new MonetaryValue(INIT_VALUE);
        monetaryValue.add(anotherValue);
        
        String expected = "20";
        String actual = monetaryValue.toString();
        assertEquals(expected, actual, "Wrong value after adding another MonetaryValue");
    }

    /**
     * Test of toBigDecimal method, of class MonetaryValue.
     */
    @org.junit.jupiter.api.Test
    public void testToBigDecimal() {
        BigDecimal expected = new BigDecimal(INIT_VALUE);
        BigDecimal actual = monetaryValue.toBigDecimal();
        assertEquals(expected, actual, "Wrong toBigDecimal value");
    }

    /**
     * Test of toString method, of class MonetaryValue.
     */
    @org.junit.jupiter.api.Test
    public void testToString() {
        String expected = INIT_VALUE;
        String actual = monetaryValue.toString();
        assertEquals(expected, actual, "Wrong toString value");
    }
    
    /**
     * Test constructor without parameters
     */
    @org.junit.jupiter.api.Test
    public void testConstructorWithoutParameters(){
        MonetaryValue impliedZero = new MonetaryValue();
        MonetaryValue expicitZero = new MonetaryValue("0");
        
        assertEquals(impliedZero,expicitZero, "Constructing without zero does not work");
    }
    
}
