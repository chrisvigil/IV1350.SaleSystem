package se.kth.iv1350.salesystem.controller;

import java.util.Locale;
import se.kth.iv1350.salesystem.datatypes.MonetaryValue;
import se.kth.iv1350.salesystem.datatypes.Quantity;
import se.kth.iv1350.salesystem.dto.ItemDTO;

/**
 * Return message for <code>Controller</code> method <code>addItem</code>.
 * Contains data about the added item and the sales running total
 * @author christopher.vigil
 */
public class ReturnMessage {
    private final String itemDescription;
    private final String itemQuantity;
    private final String itemPrice;
    private final String runningTotal;
    
    /**
     * Creates a <code>ItemReturnMessage</code>
     * @param itemDTO Contains data describing an item
     * @param runningTotal Contains running total of sale.
     */
    ReturnMessage(ItemDTO itemDTO, Quantity quantity, MonetaryValue runningTotal, Locale locale){
        this.itemDescription = itemDTO.getItemDescription();
        this.itemQuantity = quantity.toString();
        this.itemPrice = itemDTO.getPricePerUnit().includingVAT(itemDTO.getVATRate()).currencyFormat(locale);
        this.runningTotal = runningTotal.currencyFormat(locale);
    }
    
    /**
     * @return The item description contained in the <code>ReturnMessage</code> .
     */
    public String getItemDescription(){
        return itemDescription;
    }
    
    /**
     * @return The item quantity contained in the <code>ReturnMessage</code> .
     */
    public String getQuantity(){
        return itemQuantity;
    }
    
    /**
     * @return The item price contained in the <code>ReturnMessage</code> .
     */
    public String getItemPrice(){
        return itemPrice;
    }
    /**
     * @return The running total contained in the <code>ReturnMessage</code> .
     */
    public String getRunningTotal(){
        return runningTotal;
    }
    
    @Override
    /**
     * Returns a <code>String</code> with format "Item: QUANTITY DESCRIPTION, 
     * Price: PRICE, Running Total: RUNNINGTOTAL"
     */
    public String toString(){
        String messageAsString = "Item: " + itemQuantity + " " + itemDescription +
                              ", Price: " + itemPrice +
                              ", Runnning Total: " + runningTotal;
        
        return messageAsString;
    }
}
