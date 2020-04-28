/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.kth.iv1350.salesystem.model;

import se.kth.iv1350.salesystem.datatypes.ItemID;
import se.kth.iv1350.salesystem.datatypes.MonetaryValue;
import se.kth.iv1350.salesystem.datatypes.Quantity;
import se.kth.iv1350.salesystem.datatypes.VATRate;
import se.kth.iv1350.salesystem.dto.ItemDTO;
import se.kth.iv1350.salesystem.dto.SoldItemDTO;

/**
 * Represents a quantity of a single type of item available for purchase
 */
class Item {
    private final ItemID itemID;
    private final MonetaryValue pricePerUnit;
    private final VATRate vatRate;
    private final String itemDescription;
    private final Quantity quantity;
    
    
    /**
     * Creates an instance of a quantity of a purchased item.
     * @param itemDTO Describes the item.
     * @param quantity The quantity of the item.
     */
    Item(ItemDTO itemDTO, Quantity quantity){
        this.itemID = itemDTO.getItemID();
        this.pricePerUnit = itemDTO.getPricePerUnit();
        this.vatRate = itemDTO.getVATRate();
        this.itemDescription = itemDTO.getItemDescription();
        this.quantity = quantity;
    }
    
    MonetaryValue getPricePerUnit(){
        return new MonetaryValue(pricePerUnit);
    }
    
    /**
     * Returns the Item's price per unit multiplied by the item quantity.
     * @return total price as MonetaryValue
     */
    MonetaryValue getTotalPrice(){
        return pricePerUnit.multiplíedByQuantity(quantity);
    }
    
    /**
     * Returns the Item's quantity.
     * @return current <code>Item</code> quantity
     */
    Quantity getQuantity(){
        return quantity;
    }
    
    /**
     * Increases the quantity of an item
     * @param increase The value with which to increase the quantity
     */
    void addQuanity(Quantity increase){
        quantity.addQuantity(increase);
    }
    
    
    /**
     * Generates a <code>SoldItemDTO</code> from the <code>Item</code>.
     * @return A <code>SoldItemDTO</code> representing the item.
     */
    SoldItemDTO generateSoldItemDTO(){
        return new SoldItemDTO(itemID, pricePerUnit, vatRate, itemDescription, quantity);
    }
    
    
}
