/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.kth.iv1350.salesystem.integration;

import se.kth.iv1350.salesystem.datatypes.ItemID;
import se.kth.iv1350.salesystem.dto.ItemDTO;

/**
 * This class is responsible for coordinating communication to and from 
 * external databases, 
 * 
 * @author christopher.vigil
 */
public class ExternalDBHandler {
    private AccountingSystemHandler accountingDBHandler;
    private InventorySystemHandler inventoryDBHandler;
    private SaleLogHandler saleLogHandler;
    
    /**
     * Creates a new ExternalDBHandler
     */
    public ExternalDBHandler(){
        accountingDBHandler = new AccountingSystemHandler();
        inventoryDBHandler = new InventorySystemHandler();
        saleLogHandler = new SaleLogHandler();
    }
    
    /**
     * Searches an external database for a requested itemID
     * @param itemID The item identifier of the requested item
     * @return If item is found an itemDTO is returned representing the
     * requested item. If item is not found null is returned.
     */
    public ItemDTO getItemData(ItemID itemID){
        return inventoryDBHandler.getItemData(itemID);
    }
}

