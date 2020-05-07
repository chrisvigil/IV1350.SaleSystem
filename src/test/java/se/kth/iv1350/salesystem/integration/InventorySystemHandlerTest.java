/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.kth.iv1350.salesystem.integration;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeAll;
import se.kth.iv1350.salesystem.datatypes.ItemID;
import se.kth.iv1350.salesystem.datatypes.MonetaryValue;
import se.kth.iv1350.salesystem.datatypes.VATRate;
import se.kth.iv1350.salesystem.dto.ItemDTO;

/**
 *
 * @author christopher.vigil
 */
public class InventorySystemHandlerTest {
    private static final String DBNAME = "db.txt";
    
    private static final String ITEMID = "0";
    private static final String ITEMPRICE = "10.34";
    private static final String VAT = "25";
    private static final VATRate VATRATE = VATRate.TWENTYFIVE;
    private static final String DESC = "Item description";
    private static final String DBSTRING = ITEMID + "###" + ITEMPRICE + "###"
            + VAT + "###" + DESC;
    
    InventorySystemHandler instance;
    
    @BeforeAll
    public static void init(){
        createDB(DBSTRING);
    }
    
    @BeforeEach
    public void setUp() {
        instance = new InventorySystemHandler(DBNAME);
        //instance.readDBFromFile(DBNAME);
    }
    
    @AfterEach
    public void tearDown() {
        instance = null;
    }
    
    @AfterAll
    public static void cleanUp(){
        File file = new File(DBNAME);
        file.delete();
    }

    @Test
    public void testGetItemDataFound() {
        ItemDTO actual;
        try{
            actual = instance.getItemData(new ItemID(ITEMID));
        }
        catch(ItemNotFoundException ex){
            actual = null;
            fail("Cause ItemNotFoundException");
        }
        
        ItemDTO expected = new ItemDTO(new ItemID(ITEMID), new MonetaryValue(ITEMPRICE),
        VATRATE, DESC);
        assertEquals(expected, actual, "Expected item not found");
    }
    
    @Test
    public void testGetItemDataNotFound(){
        boolean correctExceptionThrown = false;
        try{
            ItemDTO actual = instance.getItemData(new ItemID(ITEMID+"3"));
        }
        catch(ItemNotFoundException ex){
            correctExceptionThrown = true;
        }
        assertTrue(correctExceptionThrown, "A correct exception was not thrown when "
                + "adding nonexistet item");
    }
    
    @Test
    public void requestingSameDataTwice(){
        try{
            ItemID itemID = new ItemID(ITEMID);
            ItemDTO itemDTO = instance.getItemData(itemID);
        
            ItemID anotheritemID = new ItemID(ITEMID);
            ItemDTO actual = instance.getItemData(anotheritemID);
        
        assertNotNull(actual, "requesting the same item twice returns null");
        }
        catch(ItemNotFoundException ex){
            fail("Caused ItemNotFoundException");
        }
    }
    
    @Test
    public void testUnableToAccessDatabase(){
        boolean expectedExcpetionThrown = false;
        try{
            ItemID itemID = new ItemID("noDB");
            ItemDTO itemDTO = instance.getItemData(itemID);
        }
        catch(InventoryDBException ex){
            expectedExcpetionThrown = true;
        }
        catch(Exception ex){
            fail("Unexpected exception thrown");
        }
        
        assertTrue(expectedExcpetionThrown, "Expected excpetion was not thrown");
        
    }
    
    @Test
    public void testUpdateInventory() {
        // nothing yet to test
    }
    
    private static void createDB(String dbtext){
        
        try{
            FileWriter fileWriter = new FileWriter(DBNAME);
            
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(dbtext);
            bufferedWriter.close();
            
        }
        catch(IOException ex){  
        }
    }
}
