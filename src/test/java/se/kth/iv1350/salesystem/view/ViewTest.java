package se.kth.iv1350.salesystem.view;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Locale;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import se.kth.iv1350.salesystem.controller.Controller;
import se.kth.iv1350.salesystem.integration.ItemNotFoundException;
import se.kth.iv1350.salesystem.datatypes.Address;
import se.kth.iv1350.salesystem.model.Store;


public class ViewTest {
    
    private View instanceToTest;
    private ByteArrayOutputStream printoutBuffer;
    private PrintStream originalSysOut;
    
    private static final String PLACEHOLDER_STORENAME = "Some Store";
    private static final Address PLACEHOLDER_ADDRESS = new Address("Somestreet",42,11122,"SomeCity", "SomeCountry");
    
    @BeforeEach
    public void setUp() {
        Store store = new Store(PLACEHOLDER_STORENAME,PLACEHOLDER_ADDRESS);
        Controller contr = new Controller(store);
        instanceToTest = new View(contr);
        
        printoutBuffer = new ByteArrayOutputStream();
        PrintStream inMemSysOut = new PrintStream(printoutBuffer);
        originalSysOut = System.out;
        System.setOut(inMemSysOut);
    }
    
    @AfterEach
    public void tearDown() {
        instanceToTest = null;
        
        printoutBuffer = null;
        System.setOut(originalSysOut);
    }

    /**
     * Test of runFakeExecution method, of class View.
     
    @Test
    public void testRunFakeExecution() throws ItemNotFoundException {
        instanceToTest.runFakeExecution();
        String printout = printoutBuffer.toString();
        String expectedOutput = "started";
        
        assertTrue(printout.contains(expectedOutput), "UI did not start correctly");
    } */
    
    @Test
    public void testLocale(){
        Locale locale = new Locale("sv", "SE");
        assertNotNull(locale, "locale is null");
    }
    
}
