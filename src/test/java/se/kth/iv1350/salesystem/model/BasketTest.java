package se.kth.iv1350.salesystem.model;

import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import se.kth.iv1350.salesystem.datatypes.ItemID;
import se.kth.iv1350.salesystem.datatypes.MonetaryValue;
import se.kth.iv1350.salesystem.datatypes.Quantity;
import se.kth.iv1350.salesystem.datatypes.VATRate;
import se.kth.iv1350.salesystem.dto.ItemDTO;
import se.kth.iv1350.salesystem.dto.SoldItemDTO;
import java.util.List;

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
       
       try{
       instance.addItem(itemDTO, quantity);
       }
       catch(Exception exception){
           fail("Attempting to add item produced" + exception);
       }
       
       List<SoldItemDTO> itemList = instance.getSoldItems();
       
       SoldItemDTO expected = new SoldItemDTO(itemID, itemDTO.getPricePerUnit(),
                              itemDTO.getVATRate(), itemDTO.getItemDescription(), quantity);
       
       SoldItemDTO actual = itemList.get(0);
       
       assertEquals(expected, actual, "Item was not correctly added");
    }
    
    @Test
    public void testAddItemAllreadyInBasket(){
        ItemID itemID = new ItemID("0");
        ItemDTO itemDTO = createDummyItemDTO(itemID);
        
        ItemID anotherItemID = new ItemID("0");
        ItemDTO anotherItemDTO = createDummyItemDTO(anotherItemID);
        
        Quantity quantity = new Quantity("1");
        
        try{
        instance.addItem(itemDTO, quantity);
        instance.addItem(anotherItemDTO, quantity);
        }
        catch(Exception exception){
           fail("Attempting to add duplicate item produced" + exception);
       }
        
       List<SoldItemDTO> itemList = instance.getSoldItems();
       
       Quantity expectedQuantity = new Quantity("2");
       SoldItemDTO expected = new SoldItemDTO(itemID, itemDTO.getPricePerUnit(),
                              itemDTO.getVATRate(), itemDTO.getItemDescription(), expectedQuantity);
       
       SoldItemDTO actual = itemList.get(0);
       
       assertEquals(expected, actual, "Adding item did not add to existing item");
    }
 
    private ItemDTO createDummyItemDTO(ItemID itemID){
        MonetaryValue pricePerUnit = new MonetaryValue("10.59");
        VATRate vatRate = VATRate.TWENTYFIVE;
        String itemDescription = "This is a dummy item";
        ItemDTO itemDTO = new ItemDTO(itemID,pricePerUnit,vatRate, itemDescription);
        
        return itemDTO;
    }
    
    @Test
    public void testGetSoldItems(){
       ItemID itemID = new ItemID("0");
       ItemDTO itemDTO = createDummyItemDTO(itemID);
       Quantity quantity = new Quantity("1");
       instance.addItem(itemDTO, quantity);
       
       itemID = new ItemID("1");
       itemDTO = createDummyItemDTO(itemID);
       quantity = new Quantity("2");
       instance.addItem(itemDTO, quantity);
       
       List<SoldItemDTO> dtoList = instance.getSoldItems();
       
       int expectedLength = 2;
       int actualLength = dtoList.size();
       assertEquals(expectedLength, actualLength, "List lengths did not match");
       
       ItemID expectedItemID = itemID;
       ItemID actualItemID = dtoList.get(1).getItemID();
       assertEquals(expectedItemID, actualItemID, "Did not get expected itemID");
    }
    
}
