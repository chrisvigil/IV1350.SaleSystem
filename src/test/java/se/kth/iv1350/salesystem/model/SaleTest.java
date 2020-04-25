/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.kth.iv1350.salesystem.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import se.kth.iv1350.salesystem.datatypes.Address;
import se.kth.iv1350.salesystem.datatypes.MonetaryValue;
import se.kth.iv1350.salesystem.datatypes.Quantity;
import se.kth.iv1350.salesystem.dto.ItemDTO;

/**
 *
 * @author christopher.vigil
 */
public class SaleTest {
    private static final String PLACEHOLDER_STORENAME = "Some Store";
    private static final Address PLACEHOLDER_ADDRESS = new Address("Somestreet",42,11122,"SomeCity", "SomeCountry");
    
    private Sale instance;
    
    
    @BeforeEach
    public void setUp() {
        Store store = new Store(PLACEHOLDER_STORENAME, PLACEHOLDER_ADDRESS);
        instance = new Sale(store);
    }
    
    @AfterEach
    public void tearDown() {
    }
/*
    @Test
    public void testAddItemToBasket() {
        System.out.println("addItemToBasket");
        ItemDTO itemDTO = null;
        Quantity quantity = null;
        Sale instance = null;
        MonetaryValue expResult = null;
        MonetaryValue result = instance.addItemToBasket(itemDTO, quantity);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }*/
    
}
