/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.kth.iv1350.salesystem.dto;

import se.kth.iv1350.salesystem.datatypes.ItemID;
import se.kth.iv1350.salesystem.datatypes.MonetaryValue;

/**
 * A Data transfer object for item data
 *
 * @author christopher.vigil
 */
public class ItemDTO {
    private final ItemID itemID;
    private final MonetaryValue pricePerUnit;
    private final MonetaryValue vatRate;
    private final String itemDescription;
    
    public ItemDTO(ItemID itemID, MonetaryValue pricePerUnit, MonetaryValue vatRate,
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
        return new MonetaryValue(pricePerUnit);
    }
    
    public MonetaryValue getVATRate(){
        return new MonetaryValue(vatRate);
    }
    
    public String getItemDescription(){
        return itemDescription;
    }
}