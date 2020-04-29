/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.kth.iv1350.salesystem.datatypes;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Disabled;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author christopher.vigil
 */
public class ItemIDTest {
    private final String INIT_STRING = "123456789";
    private final String ANOTHER_STRING = "987654321";
    private final int INIT_INT = 123456789;
    private ItemID itemID;
    
    @BeforeEach
    public void setUp() {
        itemID = new ItemID(INIT_STRING);
    }
    
    @AfterEach
    public void tearDown() {
        itemID = null;
    }

    /**
     * Test of getID method, of class ItemID.
     */
    @Test
    public void testGetID() {
        String expected = INIT_STRING;
        String actual = itemID.getID();
        assertEquals(expected,actual,"getID does not return expected value");
    }

    /**
     * Test of toString method, of class ItemID.
     */
    @Test
    public void testToString() {
        String expected = INIT_STRING;
        String actual = itemID.getID();
        assertEquals(expected,actual,"getID does not return expected value");
    }

    /**
     * Test of equals method, of class ItemID.
     */
    @Test
    public void testEquals() {
        ItemID anotherID = new ItemID(INIT_STRING);
        boolean isEqual = itemID.equals(anotherID);
        assertTrue(isEqual, "Two items with the same value are not equal");
    }
    
    @Test
    public void testNotEqual(){
        ItemID anotherID = new ItemID(ANOTHER_STRING);
        boolean isEqual = itemID.equals(anotherID);
        assertFalse(isEqual, "Two different items appear equal");
    }
    
}
