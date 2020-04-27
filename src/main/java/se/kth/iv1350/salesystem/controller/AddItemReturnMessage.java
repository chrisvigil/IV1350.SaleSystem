/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.kth.iv1350.salesystem.controller;

import se.kth.iv1350.salesystem.datatypes.MonetaryValue;
import se.kth.iv1350.salesystem.dto.ItemDTO;

/**
 * Return message for <code>Controller</code> method <code>addItem</code>.
 * Contains data about the added item and the sales running total
 * @author christopher.vigil
 */
public class AddItemReturnMessage {
    private final String itemDescription;
    private final String itemPrice;
    private final String runningTotal;
    
    /**
     * Creates a <code>ItemReturnMessage</code>
     * @param itemDTO Contains data describing an item
     * @param runningTotal Contains running total of sale.
     */
    AddItemReturnMessage(ItemDTO itemDTO, MonetaryValue runningTotal){
        this.itemDescription = itemDTO.getItemDescription();
        this.itemPrice = itemDTO.getPricePerUnit().toString();
        this.runningTotal = runningTotal.toString();
    }
    
    public String getItemDescription(){
        return itemDescription;
    }
    
    public String getItemPrice(){
        return itemPrice;
    }
    
    public String getRunningTotal(){
        return runningTotal;
    }
    
    @Override
    public String toString(){
        String messageAsString = "Item: " + itemDescription +
                              ", Price: " + itemPrice +
                              ", Runnning Total: " + runningTotal;
        
        return messageAsString;
    }
}
