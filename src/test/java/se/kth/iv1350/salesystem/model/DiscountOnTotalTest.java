package se.kth.iv1350.salesystem.model;

import java.util.Locale;
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
import se.kth.iv1350.salesystem.dto.SaleDTO;

public class DiscountOnTotalTest {
    private DiscountOnTotal instance;
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
        instance = new DiscountOnTotal(RATE);
        
        sale = new Sale();
    }
    
    @AfterEach
    public void tearDown() {
        instance = null;
    }

    @Test
    public void testCalculateDiscount() {
        ItemDTO itemDTO = new ItemDTO(ITEMID, PRICE, VATRATE, DESC);
        sale.addItemToBasket(itemDTO, QUANTITY);
        
        MonetaryValue totalBeforDiscount = sale.calculateSaleTotalWithVAT();
        MonetaryValue expected = totalBeforDiscount.calculatePercentage(RATE);
        
        MonetaryValue actual = instance.calculateDiscount(generateSaleDTO());
        
        assertEquals(expected, actual, "Discount was not propperly calculated");
    }
    
    private SaleDTO generateSaleDTO(){
        MonetaryValue zero = new MonetaryValue();
        String storeName = "";
        Address address = new Address("",0,0,"");
        SaleDTO saleDTO = new SaleDTO(sale.getSoldItems(), zero, zero, 
                sale.calculateSaleTotalWithVAT(),storeName,address);
        return saleDTO;
    }
}
