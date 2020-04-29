package se.kth.iv1350.salesystem.startup;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import se.kth.iv1350.salesystem.controller.Controller;
import se.kth.iv1350.salesystem.datatypes.Address;
import se.kth.iv1350.salesystem.model.Store;
import se.kth.iv1350.salesystem.view.View;

public class MainTest {
    
    private Main instanceToTest;
    private ByteArrayOutputStream printoutBuffer;
    private PrintStream originalSysOut;
    
    private static final String PLACEHOLDER_STORENAME = "Some Store";
    private static final Address PLACEHOLDER_ADDRESS = new Address("Somestreet",42,11122,"SomeCity", "SomeCountry");
    
    @BeforeEach
    public void setUp() {
        Store store = new Store(PLACEHOLDER_STORENAME,PLACEHOLDER_ADDRESS);
        Controller contr = new Controller(store);
        instanceToTest = new Main();
        
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

    /*
   @Test
    public void testUIHasStarted() {
        String[] args = null;
        Main.main(args);
        
        String printout = printoutBuffer.toString();
        String expectedOutput = "started";
        assertTrue(printout.contains(expectedOutput), "UI did not start correctly");
    }*/
    
}
