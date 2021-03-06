package se.kth.iv1350.salesystem.controller;

import java.util.Locale;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import se.kth.iv1350.salesystem.datatypes.ItemID;
import se.kth.iv1350.salesystem.datatypes.MonetaryValue;
import se.kth.iv1350.salesystem.datatypes.Quantity;
import se.kth.iv1350.salesystem.datatypes.VATRate;
import se.kth.iv1350.salesystem.dto.ItemDTO;

public class AddItemReturnMessageTest {
    private ItemDTO itemDTO;
    private ReturnMessage instance;
    
    private final int ITEMID = 123456;
    private final MonetaryValue ITEMPRICE = new MonetaryValue("15");
    private final VATRate VAT = VATRate.TWELVE;
    private final String DESCRIPTION = "Item Description";
    private final Quantity QUANTITY = new Quantity(1);
    
    private static final Locale LOCALE = new Locale("sv", "SE");
    
    private final MonetaryValue TOTAL = new MonetaryValue("123");
    
    @BeforeEach
    public void setUp() {
        itemDTO = new ItemDTO(new ItemID(ITEMID),ITEMPRICE, VAT,DESCRIPTION);
        instance = new ReturnMessage(itemDTO, QUANTITY, TOTAL, LOCALE);
    }
    
    @AfterEach
    public void tearDown() {
        itemDTO = null;
        instance = null;
    }
    
    
    @Test
    public void testGetItemDescription() {
        String actual = instance.getItemDescription();
        String expected = DESCRIPTION;
        assertEquals(expected, actual, "Item description not as expected");
    }

    @Test
    public void testGetItemPrice() {
        String actual = instance.getItemPrice();
        String expected = ITEMPRICE.includingVAT(VAT).currencyFormat(LOCALE);
        assertEquals(expected, actual, "Item price not as expected");
    }
    
    @Test
    public void testGetRunningTotal() {
        String actual = instance.getRunningTotal();
        String expected = TOTAL.currencyFormat(LOCALE);
        assertEquals(expected, actual, "Running total not as expected");
    }
    
    @Test
    public void testToString() {
        String actual = instance.toString();
        String expected = "Item: " + QUANTITY + " " + DESCRIPTION +
                              ", Price: " + ITEMPRICE.includingVAT(VAT).currencyFormat(LOCALE) +
                              ", Runnning Total: " + TOTAL.currencyFormat(LOCALE);
        assertEquals(expected, actual, "String not returned as expected");
    }
    
}
