package se.kth.iv1350.salesystem.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
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
import se.kth.iv1350.salesystem.dto.SoldItemDTO;
import se.kth.iv1350.salesystem.integration.Printer;

public class SaleTest {
    private static final String PLACEHOLDER_STORENAME = "Some Store";
    private static final Address PLACEHOLDER_ADDRESS = new Address("Somestreet",42,11122,"SomeCity", "SomeCountry");
    
    private static final ItemID ITEMID = new ItemID("0");
    private static final MonetaryValue PRICE = new MonetaryValue("10.59");
    private static final VATRate VATRATE = VATRate.TWENTYFIVE;
    private static final String DESC = "This is a dummy item";
    
    private static final Quantity QUANTITY = new Quantity("1");
    
    private Sale instance;

    
    
    @BeforeEach
    public void setUp() {
        Store store = new Store(PLACEHOLDER_STORENAME, PLACEHOLDER_ADDRESS);
        instance = new Sale(store);
    }
    
    @AfterEach
    public void tearDown() {
        instance = null;
    }

    @Test
    public void testAddItemToBasket() {
        ItemDTO itemDTO = new ItemDTO(ITEMID,PRICE,VATRATE, DESC);
        instance.addItemToBasket(itemDTO, QUANTITY);
        
        instance.makeCashPayment(PRICE.includingVAT(VATRATE));
        
        SaleDTO saleDTO = instance.logSale();
        List<SoldItemDTO> list = saleDTO.getItems();
        
        SoldItemDTO expected = new SoldItemDTO(ITEMID,PRICE,VATRATE, DESC, QUANTITY);
        
        SoldItemDTO actual = list.get(0);
        
        assertEquals(expected, actual, "Item was not correctly added to the basket");
    }
    
    @Test
    public void testAddItemToBasketUpdatesSubTotal() {
        ItemDTO itemDTO = new ItemDTO(ITEMID,PRICE,VATRATE, DESC);
        instance.addItemToBasket(itemDTO, QUANTITY);
        
        instance.makeCashPayment(PRICE.includingVAT(VATRATE));
        
        SaleDTO saleDTO = instance.logSale();
        
        MonetaryValue expected = new MonetaryValue(PRICE).multiplíedByQuantity(QUANTITY);
        
        MonetaryValue actual = saleDTO.getSaleSubTotal();
        
        assertEquals(expected, actual, "Subtotal not correctly updated");
    }
    
    @Test
    public void testAddItemToBasketUpdatesSaleVat() {
        ItemDTO itemDTO = new ItemDTO(ITEMID,PRICE,VATRATE, DESC);
        instance.addItemToBasket(itemDTO, QUANTITY);
        
        instance.makeCashPayment(PRICE.includingVAT(VATRATE));
        
        SaleDTO saleDTO = instance.logSale();
        
        MonetaryValue expected = new MonetaryValue(PRICE).multiplíedByQuantity(QUANTITY).calculateVAT(VATRATE);
        
        MonetaryValue actual = saleDTO.getSaleVAT();
        
        assertEquals(expected, actual, "Sale VAT not correctly updated");
    }
    
    @Test
    public void testAddItemToBasketReturnValue() {
        ItemDTO itemDTO = new ItemDTO(ITEMID,PRICE,VATRATE, DESC);
        MonetaryValue actual = instance.addItemToBasket(itemDTO, QUANTITY);
        MonetaryValue expected = PRICE.multiplíedByQuantity(QUANTITY).includingVAT(VATRATE);
        
        assertEquals(expected, actual, "Correct running total not returned");
    }

    @Test
    public void testMakeCashPayment() {
        ItemDTO itemDTO = new ItemDTO(ITEMID,PRICE,VATRATE, DESC);
        instance.addItemToBasket(itemDTO, QUANTITY);
        
        MonetaryValue actual = instance.makeCashPayment(PRICE.includingVAT(VATRATE));
        
        BigDecimal PriceAsBigDecimal = PRICE.toBigDecimal();
        MonetaryValue expected = new MonetaryValue(PriceAsBigDecimal.subtract(PriceAsBigDecimal)).includingVAT(VATRATE);
        
        assertEquals(expected, actual, "CashPayment did not retuirn corrrect change");
    }
    
    @Test
    public void testMakeInsufficientCashPayment() {
        ItemDTO itemDTO = new ItemDTO(ITEMID,PRICE,VATRATE, DESC);
        instance.addItemToBasket(itemDTO, QUANTITY);
        
        boolean causedException = false;
        try{
            instance.makeCashPayment(PRICE);
        }
        catch(Exception ex){
            causedException = true;
            if (!ex.getMessage().startsWith("Payment"))
                fail("Unexpected exception thrown");
        }
        
        assertTrue(causedException, "Did not cuase exception");
        
    }
    
    
    @Test
    public void testEndSaleLogsTime() {
        ItemDTO itemDTO = new ItemDTO(ITEMID,PRICE,VATRATE, DESC);
        instance.addItemToBasket(itemDTO, QUANTITY);
        
        instance.makeCashPayment(PRICE.includingVAT(VATRATE));
        
        LocalDateTime expected = LocalDateTime.now();
        SaleDTO saleDTO = instance.logSale();
        LocalDateTime actual = saleDTO.getTimeOfSale();
        assertEquals(expected,actual, "Did not log correct localtime");
    }
    
    @Test
    public void testEndSaleGeneratesSaleLog() {
        ItemDTO itemDTO = new ItemDTO(ITEMID,PRICE,VATRATE, DESC);
        instance.addItemToBasket(itemDTO, QUANTITY);
        
        instance.makeCashPayment(PRICE.includingVAT(VATRATE));
        
        SaleDTO saleDTO = instance.logSale();
        
        MonetaryValue expected = PRICE;
        MonetaryValue actual = saleDTO.getSaleSubTotal();
        
        assertEquals(expected, actual, "Did not generate expected sale log");
    }

    
    @Test
    public void testPrintRepeict() {
        ItemDTO itemDTO = new ItemDTO(ITEMID,PRICE,VATRATE, DESC);
        instance.addItemToBasket(itemDTO, QUANTITY);
        
        instance.makeCashPayment(PRICE.includingVAT(VATRATE));
        
        SaleDTO saleDTO = instance.logSale();
        
        Printer printer = new Printer();
        
        try{
            instance.printRepeict(printer);
        }
        catch(Exception ex){
            fail("cuased exception");
        }
        
    }
    
}
