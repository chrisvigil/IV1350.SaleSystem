package se.kth.iv1350.salesystem.controller;

import java.math.BigDecimal;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import se.kth.iv1350.salesystem.datatypes.Address;
import se.kth.iv1350.salesystem.datatypes.ItemID;
import se.kth.iv1350.salesystem.datatypes.MonetaryValue;
import se.kth.iv1350.salesystem.datatypes.Quantity;
import se.kth.iv1350.salesystem.datatypes.VATRate;
import se.kth.iv1350.salesystem.model.Store;

public class ControllerTest {
    private Controller instance;
    private Store store;
    private static final String STORE_NAME = "Store Name";
    private static final Address ADDRESS = new Address("Somestreet",42,11122,"SomeCity", "SomeCountry");
    
    private static final String ITEMPRICE = "10.34";
    private static final String VAT = "25";
    private static final VATRate VATRATE = VATRate.TWENTYFIVE;
    private static final String DESCRIPTION = "This is a dummy item";
    private static final String ITEMID = "0";
    private static final int QUANTITY = 1;
    
    @BeforeEach
    public void setUp() {
        store = new Store(STORE_NAME,ADDRESS);
        instance = new Controller(store);
    }
    
    @AfterEach
    public void tearDown() {
        instance = null;
        store = null;
    }
    
    @Test
    public void testStartNewSale() {
        try{
            instance.startNewSale();
        }
        catch(Exception exception){
            fail("startNewSale causes " + exception);
        }
    }
    
    @Test
    public void testAddItemToSaleWithoutQuantity(){
        instance.startNewSale();
        
        AddItemReturnMessage message = instance.addItemToSale(new ItemID(ITEMID));
        
        if(message == null)
            fail("Did not create a valid item"); 
    }
    
     @Test
    public void testAddItemToSaleWithQuantity(){
        instance.startNewSale();
        
        AddItemReturnMessage message = instance.addItemToSale(new ItemID(ITEMID),new Quantity(QUANTITY));
        
        if(message == null)
            fail("Did not create a valid item"); 
    }
    
    
    @Test
    public void testMakeExactCashPayment(){
        instance.startNewSale();
        AddItemReturnMessage message = instance.addItemToSale(new ItemID(ITEMID),new Quantity(QUANTITY));
        
        char[] itemPriceArray = message.getItemPrice().toCharArray();
        StringBuilder sb = new StringBuilder();
        for(char c : itemPriceArray){
            if (Character.isDigit(c))
                sb.append(c);
            if((c == '.') || (c == ','))
            {
                sb.append('.');
            }
        }
        String itemPriceAsString = sb.toString();
        
        MonetaryValue payment = new MonetaryValue(itemPriceAsString);
        payment = payment.multiplíedByQuantity(new Quantity(QUANTITY));
        payment = payment.roundVaule();
        System.out.println("Payment: " +payment.toBigDecimal().toString());
        
        String actual = instance.makeCashPayment(payment).toString();
        System.out.println("Actual: " + actual);
        
        
        String expected = new MonetaryValue("0").toString();
        System.out.println("Expected: " +expected);
        
        assertEquals(expected, actual, "Making an exact payment did not return zero change");
    }
    
}
