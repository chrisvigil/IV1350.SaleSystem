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
    public void testGetTotalPrice() {
        instance.addQuanity(new Quantity(QUANTITY));
        Quantity quantity = new Quantity(QUANTITY+QUANTITY);
        MonetaryValue price = new MonetaryValue(PRICE);
        
        MonetaryValue expected = price.multiplíedByQuantity(quantity);
        MonetaryValue actual = instance.getTotalPrice();
    }
    
    
    /* NOT COMPLEAT!
    @Test
    public void testChangeVATRate() {
        
    }

    
    @Test
    public void testAddQuanity() {
        System.out.println("addQuanity");
        Quantity increase = null;
        Item instance = null;
        instance.addQuanity(increase);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    @Test
    public void testDecreaseQuantity() {
        System.out.println("decreaseQuantity");
        Quantity decrease = null;
        Item instance = null;
        instance.decreaseQuantity(decrease);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    */
    private ItemDTO createItemDTO(ItemID itemID){
        MonetaryValue pricePerUnit = new MonetaryValue(PRICE);
        String itemDescription = DESCRIPTION;
        ItemDTO itemDTO = new ItemDTO(itemID,pricePerUnit,VATRATE, itemDescription);
        
        return itemDTO;
    }
    
    
}
