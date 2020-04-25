package se.kth.iv1350.salesystem.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import se.kth.iv1350.salesystem.datatypes.ItemID;
import se.kth.iv1350.salesystem.datatypes.MonetaryValue;
import se.kth.iv1350.salesystem.datatypes.Quantity;
import se.kth.iv1350.salesystem.datatypes.VATRate;
import se.kth.iv1350.salesystem.dto.ItemDTO;

public class BasketTest {
    
    private Basket instance;
    
    @BeforeEach
    public void setUp() {
        instance = new Basket();
    }
    
    @AfterEach
    public void tearDown() {
        instance = null;
    }

    @Test
    public void testAddNewItem() {
       ItemID itemID = new ItemID("0");
       ItemDTO itemDTO = createDummyItemDTO(itemID);
       Quantity quantity = new Quantity("1");
       
       // BETER TESTING NEEDED
       try{
       instance.addItem(itemDTO, quantity);
       }
       catch(Exception exception){
           fail("Attempting to add item produced" + exception);
       }
    }
    
    @Test
    public void testAddItemAllreadyInBasket(){
        ItemID itemID = new ItemID("0");
        ItemDTO itemDTO = createDummyItemDTO(itemID);
        Quantity quantity = new Quantity("1");
        instance.addItem(itemDTO, quantity);
        
        // BETER TESTING NEEDED
        try{
        instance.addItem(itemDTO, quantity);
        }
        catch(Exception exception){
           fail("Attempting to add duplicate item produced" + exception);
       }
    }
    
    private ItemDTO createDummyItemDTO(ItemID itemID){
        MonetaryValue pricePerUnit = new MonetaryValue("10.59");
        VATRate vatRate = VATRate.TWENTYFIVE;
        String itemDescription = "This is a dummy item";
        ItemDTO itemDTO = new ItemDTO(itemID,pricePerUnit,vatRate, itemDescription);
        
        return itemDTO;
    }
    
}
