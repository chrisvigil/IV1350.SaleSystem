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
        this.pricePerUnit = new MonetaryValue(pricePerUnit);
        this.vatRate = vatRate;
        this.itemDescription = itemDescription;
    }
    
    public ItemID getItemID(){
        return itemID;
    }
    
    public MonetaryValue getPricePerUnit(){
        return new MonetaryValue(pricePerUnit);
    }
    
    public VATRate getVATRate(){
        return vatRate;
    }
    
    public String getItemDescription(){
        return itemDescription;
    }
}
