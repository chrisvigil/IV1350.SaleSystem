/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.kth.iv1350.salesystem.dto;

import se.kth.iv1350.salesystem.datatypes.ItemID;
import se.kth.iv1350.salesystem.datatypes.MonetaryValue;
import se.kth.iv1350.salesystem.datatypes.Quantity;
import se.kth.iv1350.salesystem.datatypes.VATRate;

/**
 *
 * @author christopher.vigil
 */
public class SoldItemDTO extends ItemDTO{
    private final Quantity quantity;
    
    public SoldItemDTO(ItemID itemID, MonetaryValue pricePerUnit, VATRate vatRate, 
            String itemDescription, Quantity quantity){
        super(itemID, pricePerUnit, vatRate, itemDescription);
        this.quantity = new Quantity(quantity);
    }
    
    public Quantity getQuantity(){
        return new Quantity(quantity);
    }
}
