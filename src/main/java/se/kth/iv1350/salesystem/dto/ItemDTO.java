/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.kth.iv1350.salesystem.dto;

import se.kth.iv1350.salesystem.datatypes.ItemID;
import se.kth.iv1350.salesystem.datatypes.MonetaryValue;
import se.kth.iv1350.salesystem.datatypes.VATRate;

/**
 * A Data transfer object for item data
 */
public class ItemDTO {
    private final ItemID itemID;
    private final MonetaryValue pricePerUnit;
    private final VATRate vatRate;
    private final String itemDescription;
    
    /**
     * Creates a new instance representing an item.
     * @param itemID The item types unique identifier. 
     * @param pricePerUnit The item's price per unit.
     * @param vatRate The VAT rate of the item.
     * @param itemDescription A short description of the item.
     */
    public ItemDTO(ItemID itemID, MonetaryValue pricePerUnit, VATRate vatRate,
            String itemDescription){
        this.itemID = itemID;
        this.pricePerUnit = pricePerUnit;
        this.vatRate = vatRate;
        this.itemDescription = itemDescription;
    }
    
    public ItemID getItemID(){
        return itemID;
    }
    
    public MonetaryValue getPricePerUnit(){
        return pricePerUnit;
    }
    
    public VATRate getVATRate(){
        return vatRate;
    }
    
    public String getItemDescription(){
        return itemDescription;
    }
    
    /**
     * Compares a  <code>ItemDTO</code> to another object to determine
     * if they are equal.
     * @param obj The object with which to compare.
     * @return <code>true</code> if equal, otherwise <code>false</code>.
     */
    @Override
    public boolean equals(Object obj){
        boolean isEqual = false;
        if (this.getClass() == obj.getClass())
        {
            ItemDTO itemDTO = (ItemDTO) obj;
            if (itemDTO.itemDescription.equals(this.itemDescription)
                    && itemDTO.itemID.equals(this.itemID)
                    && itemDTO.pricePerUnit.equals(this.pricePerUnit)
                    && itemDTO.vatRate.equals(this.vatRate))
                isEqual = true;
        }
        
        return isEqual;
    }
}
