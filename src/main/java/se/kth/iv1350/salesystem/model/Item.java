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

/**
 * 
 * 
 * @author christopher.vigil
 */
class Item {
    private final ItemID itemID;
    private MonetaryValue pricePerUnit;
    //private MonetaryValue vatRate;
    private final Quantity quantity;
    private VATRate vatRate;
    
    Item(ItemDTO itemDTO, Quantity quantity){
        this.itemID = itemDTO.getItemID();
        this.pricePerUnit = itemDTO.getPricePerUnit();
        //this.vatRate = itemDTO.getVATRate();
        vatRate = itemDTO.getVATRate();
        this.quantity = quantity;
    }
    
    MonetaryValue getPricePerUnit(){
        return new MonetaryValue(pricePerUnit);
    }
    
    /**
     * Returns the Items price per unit multiplied by the item quantity.
     * @return total price as MonetaryValue
     */
    MonetaryValue getTotalPrice(){
        return pricePerUnit.multiplíedByQuantity(quantity);
    }
    
    /**
     * Replaces the items price with a new price
     * @param newPrice The items new price
     */
    void setNewPrice(MonetaryValue newPrice){
        pricePerUnit = new MonetaryValue(newPrice);
    }
    
    /**
     * Set at new VAT rate for the item
     * @param newVATRate  The items new VAT rate
     */
    void changeVATRate(VATRate newVATRate){
        vatRate = newVATRate;
    }
    
    /**
     * Increases the quantity of an item
     * @param increase The value with which to increase the quantity
     */
    void addQuanity(Quantity increase){
        quantity.addQuantity(increase);
    }
    
    
    /**
     * Decreases the quantity of the item
     * 
     * @param decrease The value with which to increase the quantity
     */
    void decreaseQuantity(Quantity decrease){
        quantity.removeQuantity(decrease);
    }
    
    
}
