package se.kth.iv1350.salesystem.controller;

import java.io.IOException;
import se.kth.iv1350.salesystem.integration.ItemNotFoundException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import se.kth.iv1350.salesystem.datatypes.Address;
import se.kth.iv1350.salesystem.datatypes.ItemID;
import se.kth.iv1350.salesystem.datatypes.MonetaryValue;
import se.kth.iv1350.salesystem.datatypes.Quantity;
import se.kth.iv1350.salesystem.datatypes.VATRate;
import se.kth.iv1350.salesystem.integration.InventoryDBException;
import se.kth.iv1350.salesystem.model.RevenueObserver;
import se.kth.iv1350.salesystem.model.Store;
import se.kth.iv1350.salesystem.view.TotalRevenueView;

public class ControllerTest {
    private Controller instance;

    private static final String ITEMPRICE = "10.34";
    private static final String VAT = "25";
    private static final VATRate VATRATE = VATRate.TWENTYFIVE;
    private static final String DESCRIPTION = "This is a dummy item";
    private static final String ITEMID = "0";
    private static final int QUANTITY = 1;

    private static final Locale LOCALE = new Locale("sv", "SE");

    @BeforeEach
    public void setUp() throws IOException {
        instance = new Controller();
    }

    @AfterEach
    public void tearDown() {
        instance = null;
    }

    @Test
    public void TestAddTotalRevenueObserver(){
        RevenueObserver revenueObserver = new TotalRevenueView(LOCALE);
        try {
            instance.addTotalRevenueObserver(revenueObserver);
        } catch (Exception ex) {
            fail("Adding revenueObserver caused " + ex.toString());
        }
    }

    @Test
    public void testStartNewSale() {
        try{
            instance.startNewSale(LOCALE);
        }
        catch(Exception exception){
            fail("startNewSale causes " + exception);
        }
    }

    @Test
    public void testAddItemToSaleWithoutQuantity(){
        instance.startNewSale(LOCALE);
        ReturnMessage message = null;
        try{
            message = instance.addItemToSale(new ItemID(ITEMID));
        }
        catch(Exception ex){
            fail("Attempting to add item without quantity caused " + ex.getMessage());
        }

        if(message == null)
            fail("Return message is null");
        else if (!message.getQuantity().equals("1")){
            fail("Quantity of item added not 1");
        }
    }

    @Test
    public void testAddItemToSaleWithQuantity(){
        instance.startNewSale(LOCALE);
        ReturnMessage message = null;
        try{
            message = instance.addItemToSale(new ItemID(ITEMID),new Quantity(QUANTITY));
        }
        catch(Exception ex){
            fail("Attempting to add item with quantity caused " + ex.getMessage());
        }

        if(message == null)
            fail("Return message is null");
        else if (!message.getQuantity().equals(Integer.toString(QUANTITY))){
            fail("Quantity of item added not 1");
        }
    }

    @Test
    public void testAddItemDatabaseFailure(){
        boolean expectedExceptionThrown = false;
        instance.startNewSale(LOCALE);
        try{
            instance.addItemToSale(new ItemID("noDB"),new Quantity(QUANTITY));
        }
        catch(FatalSystemOperationException ex){
           expectedExceptionThrown = true;
        } catch (Exception ex) {
           fail("Unexpected exception " + ex.toString() + " thrown");
        }
        assertTrue(expectedExceptionThrown, "The expected exception was not thrown");
    }

    @Test
    public void testAddItemThatAllreadyExists(){
        //TODO: needs better testing
        ReturnMessage message = null;

        instance.startNewSale(LOCALE);
        ItemID itemID = new ItemID(ITEMID);
        try{
            instance.addItemToSale(itemID);
        }catch(ItemNotFoundException ex){
            fail("Caused " + ex.toString() + "When adding first item");
        }

        itemID = new ItemID(ITEMID);
        try{
            message = instance.addItemToSale(itemID);
        }catch(ItemNotFoundException ex){
            fail("Caused " + ex.toString() + "When adding second item");
        }


        assertNotNull(message, "Returned null");
    }

    @Test
    public void testAddItemThatDoesNotExist(){
        instance.startNewSale(LOCALE);
        boolean correctExceptionThrown = false;

        try{
            ReturnMessage message = instance.addItemToSale(new ItemID("-1"),new Quantity(QUANTITY));
        }
        catch (ItemNotFoundException ex){
            correctExceptionThrown = true;
        }

        assertTrue(correctExceptionThrown, "A correct exception was not thrown when "
                + "adding nonexistet item");
    }


    @Test
    public void testMakeExactCashPayment() throws ItemNotFoundException{
        instance.startNewSale(LOCALE);
        ReturnMessage message = instance.addItemToSale(new ItemID(ITEMID),new Quantity(QUANTITY));

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

        String actual = instance.makeCashPaymentandLogSale(payment).currencyFormat(LOCALE);
        System.out.println("Actual: " + actual);


        String expected = new MonetaryValue("0").currencyFormat(LOCALE);
        System.out.println("Expected: " +expected);

        assertEquals(expected, actual, "Making an exact payment did not return zero change");
    }

    @Test
    public void testEndSale() throws ItemNotFoundException{
        instance.startNewSale(LOCALE);
        ReturnMessage message = instance.addItemToSale(new ItemID(ITEMID),new Quantity(QUANTITY));

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

        String expected = new MonetaryValue(itemPriceAsString).multiplíedByQuantity(new Quantity(QUANTITY)).currencyFormat(LOCALE);
        String actual = instance.endSale();

        assertEquals(expected, actual, "Did not return expected saleTotal");
    }

}
