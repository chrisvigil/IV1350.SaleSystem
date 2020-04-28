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
    
    @Test
    public void testConstructorWithNegativeStringValue(){
        boolean causedException = false;
        try{
            instance = new MonetaryValue("-1");
        }
        catch(IllegalArgumentException ex){
            causedException = true;
        }
        catch(Exception ex){
            causedException = true;
        }
        
        assertTrue(causedException, "Creating with negative vaule did not cuase exception");
    }
    @Test
    public void testConstructorWithNegativeMonetaryValueValue(){
        boolean causedException = false;
        try{
            instance = new MonetaryValue("-1");
        }
        catch(IllegalArgumentException ex){
            causedException = true;
        }
        catch(Exception ex){
            causedException = true;
        }
        
        assertTrue(causedException, "Creating with negative value did not cuase exception");
    }
    
    @Test
    public void testConstructorWithNegativeBigDecimalValue(){
        boolean causedException = false;
        try{
            instance = new MonetaryValue(new BigDecimal(-1));
        }
        catch(IllegalArgumentException ex){
            causedException = true;
        }
        catch(Exception ex){
            causedException = true;
        }
        
        assertTrue(causedException, "Creating with negative value did not cuase exception");
    }
    
    @Test
    public void testAdd() {
        BigDecimal expected = new BigDecimal(INIT_VALUE);
        expected = expected.add(expected);
        
        BigDecimal actual = instance.add(instance).toBigDecimal();
        
        assertEquals(expected, actual, "Wrong value after adding another MonetaryValue");
    }
    
    @Test
    public void testSubtractResultZero(){
        MonetaryValue result = null;
        try{
            result = instance.subtract(instance);
        }
        catch(Exception ex){
            fail("Subtracing by itself caused " + ex.toString());
        }
        
        if (result != null){
            if (result.toBigDecimal().compareTo(BigDecimal.ZERO) != 0)
                fail("Subtracing by itself did not result in 0");
        }
    }
    
    @Test
    public void testSubtractWithNegativeResult(){
        boolean casuedException = false;
        MonetaryValue largerThenInstance = instance.add(instance);
        try{
            MonetaryValue result = instance.subtract(largerThenInstance);
        }
        catch(Exception ex){
            casuedException = true;
            if (!ex.getMessage().startsWith("subtractor"))
                fail("Subtracting with a larger value did not cause expected exception");
        }
        
        assertTrue(casuedException, "Subtracting with a larger value did not cause exception");
    }
    
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
   
   @Test
   public void testRoundVaule(){
       MonetaryValue actual = new MonetaryValue("1000000.000005").roundVaule();
       MonetaryValue expected = new MonetaryValue("1000000.01");
       assertEquals(expected, actual, "Rounding not performed correctly");
   }
    
}
