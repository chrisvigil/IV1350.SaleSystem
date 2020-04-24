/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.kth.iv1350.salesystem.integration;

import se.kth.iv1350.salesystem.datatypes.ItemID;
import se.kth.iv1350.salesystem.datatypes.MonetaryValue;
import se.kth.iv1350.salesystem.dto.ItemDTO;

/**
 * Handles calls to an external inventory system. This class is not yet
 * implemented and is only a placeholder. Class is package private.
 * @author christopher.vigil
 */
class InventorySystemHandler {
    private final String DUMMY_ID = "0";
    
    /**
     * Creates a new InventorySystemHandler
     */
    InventorySystemHandler(){
        
    }
    
    /**
     * Searches an external inventory database for requested item
     * @param itemID The item identifier of the requested item
     * @return If an item is found then an itemDTO with the items 
     * details is returned. If no item is found return value is null.
     */
    ItemDTO getItemData(ItemID itemID){
        ItemDTO foundItem;
        if (itemID.toString().equals(DUMMY_ID)){
            foundItem = createDummyItemDTO(new ItemID(DUMMY_ID));
        }
        else{
            /* TO DO: Here would be the place to call the external
               Inventory system to find item based on itemID */ 
            foundItem = null;
        }
        
        return foundItem;
    }
    
    private ItemDTO createDummyItemDTO(ItemID itemID){
        MonetaryValue pricePerUnit = new MonetaryValue("10.59");
        MonetaryValue vatRate = new MonetaryValue("25");
        String itemDescription = "This is a dummy item";
        ItemDTO itemDTO = new ItemDTO(itemID,pricePerUnit,vatRate, itemDescription);
        
        return itemDTO;
    }
    
}
