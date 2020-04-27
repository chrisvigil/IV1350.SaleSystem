/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.kth.iv1350.salesystem.integration;

import se.kth.iv1350.salesystem.datatypes.ItemID;
import se.kth.iv1350.salesystem.datatypes.MonetaryValue;
import se.kth.iv1350.salesystem.datatypes.VATRate;
import se.kth.iv1350.salesystem.dto.ItemDTO;
import se.kth.iv1350.salesystem.dto.SaleDTO;

/**
 * Handles calls to an external inventory system. This class is not yet
 * implemented and is only a placeholder.
 */
class InventorySystemHandler {
    private final String DUMMY_ID = "0";
    
    /**
     * Creates a new InventorySystemHandler instance.
     */
    InventorySystemHandler(){
        
    }
    
    /**
     * Searches an external inventory database for requested item. 
     * The <code>ItemID</code> representing a <code>String</code> value of 
     * 0 is reserved for testing purposes.
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
        VATRate vatRate = VATRate.TWENTYFIVE;
        String itemDescription = "This is a dummy item";
        ItemDTO itemDTO = new ItemDTO(itemID,pricePerUnit,vatRate, itemDescription);
        
        return itemDTO;
    }
    
    /**
     *  Sends sale data to external inventory system. This is a placeholder 
     *  method that does not do anything
     *  @param saleLog Log of sale
     */
    void updateInventory(SaleDTO saleLog){
        
    }
    
}
