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
    public void testGetValue() {
        assertEquals(TESTINT,instance.getValue(),"Does not return expected value");
    }

    @Test
    public void testToString() {
        String expected = "25%";
        String actual = instance.toString();
        assertEquals(expected,actual,"toString does not return as expected");
    }
    
}
