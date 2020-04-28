/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.kth.iv1350.salesystem.datatypes;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author christopher.vigil
 */
public class VATRateTest {
    
    private VATRate instance;
    private static final int TESTINT = 25;
    
    @BeforeEach
    public void setUp() {
        instance = VATRate.TWENTYFIVE;
    }
    
    @AfterEach
    public void tearDown() {
        instance = null;
    }

    @Test
    public void testGetTwentyFive() {
        assertEquals(25,VATRate.TWENTYFIVE.getValue(),"TWENTYFIVE does not return expected value");
    }
    
    @Test
    public void testGetTwelve() {
        assertEquals(12,VATRate.TWELVE.getValue(),"TWELVE does not return expected value");
    }
    
    @Test
    public void testGetSix() {
        assertEquals(6,VATRate.SIX.getValue(),"SIX does not return expected value");
    }

    @Test
    public void testToTwentyFiveString() {
        String expected = "25%";
        String actual = VATRate.TWENTYFIVE.toString();
        assertEquals(expected,actual,"TWENTYFIVE.toString() does not return as expected");
    }
    
    @Test
    public void testToTwelveString() {
        String expected = "12%";
        String actual = VATRate.TWELVE.toString();
        assertEquals(expected,actual,"TWELVE.toString() does not return as expected");
    }
    
    @Test
    public void testToSixString() {
        String expected = "6%";
        String actual = VATRate.SIX.toString();
        assertEquals(expected,actual,"SIX.toString() does not return as expected");
    }
}
