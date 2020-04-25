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
 * 
 * @author christopher.vigil
 */
public class AddItemReturnMessage {
    private final boolean isValid;
    private final String itemDescription;
    private final String itemPrice;
    private final String runningTotal;
    
    /**
     * Creates a valid ItemReturnMessage, used when requested item was found
     * @param itemDTO Contains data describing an item
     * @param runningTotal Contains running total of sale.
     */
    AddItemReturnMessage(ItemDTO itemDTO, MonetaryValue runningTotal){
        this.isValid = true;
        this.itemDescription = itemDTO.getItemDescription();
        this.itemPrice = itemDTO.getPricePerUnit().toString();
        this.runningTotal = runningTotal.toString();
    }
    
    /**
     * Created and invalid ItemReturnMessage, used when requested item was not found
     */
    AddItemReturnMessage(){
        this.isValid = false;
        this.itemDescription = "Item ID is not valid";
        this.itemPrice = "";
        this.runningTotal = "";
    }
    
    public boolean itemIDisValid(){
        return isValid;
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
        String messageAsString;
        if (isValid){
            messageAsString = "Item: " + itemDescription +
                              ", Price: " + itemPrice +
                              ", Runnning Total: " + runningTotal;
        }
        else{
            messageAsString = itemDescription;
        }
        
        return messageAsString;
    }
}
