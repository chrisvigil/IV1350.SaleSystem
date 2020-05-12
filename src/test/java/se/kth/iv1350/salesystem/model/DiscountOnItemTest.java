package se.kth.iv1350.salesystem.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import se.kth.iv1350.salesystem.datatypes.Address;
import se.kth.iv1350.salesystem.datatypes.ItemID;
import se.kth.iv1350.salesystem.datatypes.MonetaryValue;
import se.kth.iv1350.salesystem.datatypes.Quantity;
import se.kth.iv1350.salesystem.datatypes.VATRate;
import se.kth.iv1350.salesystem.dto.ItemDTO;

public class DiscountOnItemTest {
    private DiscountOnItem instance;
    private final ItemID ITEMID = new ItemID("1");
    private final String RATE = "12";
    
    private Sale sale;
    private static final String PLACEHOLDER_STORENAME = "Some Store";
    private static final Address PLACEHOLDER_ADDRESS = new Address("Somestreet", 42, 11122, "SomeCity", "SomeCountry");
    
    private static final MonetaryValue PRICE = new MonetaryValue("10.59");
    private static final VATRate VATRATE = VATRate.TWENTYFIVE;
    private static final String DESC = "This is a dummy item";
    private static final Quantity QUANTITY = new Quantity("1");
    
    @BeforeEach
    public void setUp() {
        instance = new DiscountOnItem(ITEMID, RATE);
        
        Store store = new Store(PLACEHOLDER_STORENAME, PLACEHOLDER_ADDRESS);
        sale = new Sale(store);
    }
    
    @AfterEach
    public void tearDown() {
        instance = null;
    }

    @Test
    public void testCalculateDiscount() {
        ItemDTO itemDTO = new ItemDTO(ITEMID, PRICE, VATRATE, DESC);
        sale.addItemToBasket(itemDTO, QUANTITY);
        
        MonetaryValue totalItemPrice = PRICE.includingVAT(VATRATE).multiplíedByQuantity(QUANTITY);
        MonetaryValue expected = totalItemPrice.calculatePercentage(RATE);
        
        MonetaryValue actual = instance.calculateDiscount(sale);
        
        assertEquals(expected, actual, "Discount was not propperly calculated");
    }
    
}
