/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.kth.iv1350.salesystem.datatypes;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author christopher.vigil
 */
public class AddressTest {
    private Address addressToTest;
    
    private final String STREET = "Somestreet";
    private final int NUMBER = 11;
    private final int POSTALCODE = 11122;
    private final String CITY = "Somecity";
    private final String COUNTRY = "Somecountry";
    private final String SEPARATOR = ", ";
    
    public AddressTest() {
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
        addressToTest = null;
    }

    @Test
    public void testToStringWithCountry() {
        addressToTest = new Address(STREET,NUMBER,POSTALCODE,CITY,COUNTRY);
        String actual = addressToTest.toString();
        
        String expectedString = STREET + SEPARATOR + NUMBER + SEPARATOR +
                POSTALCODE + SEPARATOR + CITY + SEPARATOR + COUNTRY;
        
        assertEquals(expectedString, actual, "toString method does not produce expected string for address with country");
    }
    
    @Test
    public void testToStringWithoutCountry() {
        addressToTest = new Address(STREET,NUMBER,POSTALCODE,CITY);
        String actual = addressToTest.toString();
        
        String expectedString = STREET + SEPARATOR + NUMBER + SEPARATOR +
                POSTALCODE + SEPARATOR + CITY;
        
        assertEquals(expectedString, actual, "toString method does not produce expected string for address without country");
    }
    
}
