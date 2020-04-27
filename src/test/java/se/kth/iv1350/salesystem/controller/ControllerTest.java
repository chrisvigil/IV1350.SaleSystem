package se.kth.iv1350.salesystem.controller;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import se.kth.iv1350.salesystem.datatypes.Address;
import se.kth.iv1350.salesystem.datatypes.ItemID;
import se.kth.iv1350.salesystem.datatypes.Quantity;
import se.kth.iv1350.salesystem.model.Store;

public class ControllerTest {
    private Controller instance;
    private Store store;
    private static final String STORE_NAME = "Store Name";
    private static final Address ADDRESS = new Address("Somestreet",42,11122,"SomeCity", "SomeCountry");
    private static final Quantity QUANTITY = new Quantity(10);
    private static final ItemID DUMMY_ITEM = new ItemID("0");
    
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
        
        AddItemReturnMessage message = instance.addItemToSale(DUMMY_ITEM);
        
        if(message == null)
            fail("Did not create a valid item"); 
    }
    
     @Test
    public void testAddItemToSaleWithQuantity(){
        instance.startNewSale();
        
        AddItemReturnMessage message = instance.addItemToSale(DUMMY_ITEM,QUANTITY);
        
        if(message == null)
            fail("Did not create a valid item"); 
    }
    
    
    
}
