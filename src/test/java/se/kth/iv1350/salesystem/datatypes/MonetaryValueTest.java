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
    private static final String INIT_VALUE = "10";
    private static final Quantity QUANTITY = new Quantity(INIT_VALUE);
    private MonetaryValue instance;
    
    @BeforeEach
    public void setUp() {
        instance = new MonetaryValue(INIT_VALUE);
    }
    
    @AfterEach
    public void tearDown() {
        instance = null;
    }
    
    @Test
    public void testConstructorWithoutParameters(){
        MonetaryValue impliedZero = new MonetaryValue();
        MonetaryValue expicitZero = new MonetaryValue("0");
        
        assertEquals(impliedZero,expicitZero, "Constructing without parameter does "
                                            + "not produce zero MonetaryValue");
    }
    
    /*
    @Test
    public void testAdd() {
        MonetaryValue anotherValue = new MonetaryValue(INIT_VALUE);
        instance.add(anotherValue);
        
        BigDecimal expected = new BigDecimal("20");
        BigDecimal actual = instance.toBigDecimal();
        assertEquals(expected, actual, "Wrong value after adding another MonetaryValue");
    }*/
    
    @Test
    public void testMultiplíedByQuantity(){
        MonetaryValue multiple = instance.multiplíedByQuantity(QUANTITY);
        MonetaryValue expected = new MonetaryValue("100");
        assertEquals(multiple,expected,"Does not produce correct value when "
                                     + "multiplying by quantity");
    }
    
    @Test
    public void testCalculateVAT(){
        VATRate vatRate = VATRate.TWENTYFIVE;
        MonetaryValue calculatedVAT = instance.calculateVAT(vatRate);
        
        BigDecimal expectedValue = new BigDecimal(INIT_VALUE).multiply(new BigDecimal("0.25"));
        MonetaryValue expected = new MonetaryValue(expectedValue);
        
        assertEquals(expected,calculatedVAT,"Does not apply VAT correctly");
    }
    
    @Test
    public void testToBigDecimal() {
        BigDecimal expected = new BigDecimal(INIT_VALUE);
        BigDecimal actual = instance.toBigDecimal();
        assertEquals(expected, actual, "Wrong toBigDecimal value");
    }

   @Test
    public void testToString() {
        String expected = INIT_VALUE +",0 kr";
        String actual = instance.toString();
        assertEquals(expected, actual, "Wrong toString value");
    }
    
   @Test
   public void testEquals(){
       MonetaryValue equalValue = new MonetaryValue(INIT_VALUE);
       boolean isEqual = instance.equals(equalValue);
       assertTrue(isEqual, "Equal MonetaryValues do not appear equal");
   }
   
   @Test
   public void testNotEqual(){
       MonetaryValue equalValue = new MonetaryValue("100");
       boolean isEqual = instance.equals(equalValue);
       assertFalse(isEqual, "Unequal MonetaryValues appear equal");
   }
    
}
