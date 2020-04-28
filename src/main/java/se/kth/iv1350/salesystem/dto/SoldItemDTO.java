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
 * Contains information about one particular quantity of a sold item
 */
public class SoldItemDTO extends ItemDTO{
    private final Quantity quantity;
    
    /**
     * Creates a new instance representing a particular quantity of a sold item.
     * @param itemID The item types unique identifier. 
     * @param pricePerUnit The item's price per unit.
     * @param vatRate The VAT rate of the item.
     * @param itemDescription A short description of the item.
     * @param quantity The quantity of the sold item.
     */
    public SoldItemDTO(ItemID itemID, MonetaryValue pricePerUnit, VATRate vatRate, 
            String itemDescription, Quantity quantity){
        super(itemID, pricePerUnit, vatRate, itemDescription);
        this.quantity = new Quantity(quantity);
    }
    
    public Quantity getQuantity(){
        return new Quantity(quantity);
    }
    
    @Override
    public boolean equals(Object obj){
        boolean isEqual = false;
        if (this.getClass() == obj.getClass())
        {
            SoldItemDTO soldItemDTO = (SoldItemDTO) obj;
            if (!soldItemDTO.getItemDescription().equals(this.getItemDescription()))
                isEqual = false;
            else if (!soldItemDTO.getPricePerUnit().equals(this.getPricePerUnit()))
                isEqual = false;
            else if (!soldItemDTO.getVATRate().equals(this.getVATRate()))
                isEqual = false;
            else if (!soldItemDTO.getItemDescription().equals(this.getItemDescription()))
                isEqual = false;
            else if (!soldItemDTO.quantity.equals(this.quantity))
                isEqual = false;
            else
                isEqual = true;
        }
        
        return isEqual;
    }
}
