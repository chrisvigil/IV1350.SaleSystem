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
import se.kth.iv1350.salesystem.dto.SoldItemDTO;

public class ItemTest {
    private Item instance;
    private static final String PRICE = "10.59";
    private static final VATRate VATRATE = VATRate.TWENTYFIVE;
    private static final String DESCRIPTION = "This is a dummy item";
    private static final String ITEMID = "0";
    private static final int QUANTITY = 1;
    
    
    @BeforeEach
    public void setUp() {
        ItemID itemID = new ItemID(ITEMID);
        ItemDTO itemDTO = createItemDTO(itemID);
        Quantity quantity = new Quantity(QUANTITY);
        instance = new Item(itemDTO, quantity);
    }
    
    @AfterEach
    public void tearDown() {
        instance = null;
    }

    @Test
    public void testGetPricePerUnit() {
        MonetaryValue expected = new MonetaryValue(PRICE);
        MonetaryValue actual = instance.getPricePerUnit();
        assertEquals(expected,actual, "Does not return expected price");
    }

    @Test
    public void testGetTotalPriceMuliple() {
        instance.addQuanity(new Quantity(QUANTITY));
        Quantity quantity = new Quantity(QUANTITY+QUANTITY);
        MonetaryValue price = new MonetaryValue(PRICE);
        
        MonetaryValue expected = price.multiplíedByQuantity(quantity);
        MonetaryValue actual = instance.getTotalPrice();
        
        assertEquals(expected, actual, "Total price for multiple quantities not returned as expected");
    }
    
    @Test
    public void testGetTotalPriceSingle() {
        MonetaryValue expected = instance.getPricePerUnit();
        MonetaryValue actual = instance.getTotalPrice();
        
        if (instance.getQuantity().getQuantityAsDouble() == (double) 1)
            assertEquals(expected, actual, "Total price not returned as expected when quantity is 1");
        else
            fail("Testing constant assumed to equal 1 is no longer one");
    }
    
    @Test
    public void testAddQuanity() {
        instance.addQuanity(new Quantity(QUANTITY));
        Quantity actual = instance.getQuantity();
        Quantity expected = new Quantity(QUANTITY+QUANTITY);
        assertEquals(expected, actual, "Adding quantity does not work as expected");
    }
    
    
    @Test
    public void testgenerateSoldItemDTO(){
        SoldItemDTO actual = instance.generateSoldItemDTO();
        SoldItemDTO expected = new SoldItemDTO(new ItemID(ITEMID), new MonetaryValue(PRICE),
                               VATRATE, DESCRIPTION, new Quantity(QUANTITY));
        assertEquals(expected, actual, "SoldItemDTO not generated correctly");
    }
    
    private ItemDTO createItemDTO(ItemID itemID){
        MonetaryValue pricePerUnit = new MonetaryValue(PRICE);
        String itemDescription = DESCRIPTION;
        ItemDTO itemDTO = new ItemDTO(itemID,pricePerUnit,VATRATE, itemDescription);
        
        return itemDTO;
    }
    
    
}
