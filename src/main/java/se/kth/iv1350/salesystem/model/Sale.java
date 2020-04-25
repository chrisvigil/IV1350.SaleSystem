/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.kth.iv1350.salesystem.model;

import se.kth.iv1350.salesystem.datatypes.MonetaryValue;
import se.kth.iv1350.salesystem.datatypes.Quantity;
import se.kth.iv1350.salesystem.datatypes.VATRate;
import se.kth.iv1350.salesystem.dto.ItemDTO;

/**
 * Represents a compleat sale
 * 
 * @author christopher.vigil
 */
public class Sale {
    private final Store store;
    private final Basket basket;
    private MonetaryValue saleTotal;
    private MonetaryValue saleVAT;
    
    /**
     * Creates a new store object
     * @param store The store at which the sale takes place 
     */
    public Sale(Store store){
        this.store = store;
        this.basket = new Basket();
        saleTotal = new MonetaryValue();
        saleVAT = new MonetaryValue();
    }
    
    /**
     * Adds an item to the sale basket
     * @param itemDTO The item to add.
     * @param quantity The item's quantity.
     * @return saleTotal The current running total of the sale.
     */
    public MonetaryValue addItemToBasket(ItemDTO itemDTO, Quantity quantity){
        basket.addItem(itemDTO, quantity);
        MonetaryValue itemTotal = calculateItemTotal(itemDTO, quantity);
        saleTotal.add(itemTotal);
        addItemsVATtoSaleVAT(itemTotal, itemDTO.getVATRate());
        
        MonetaryValue saleTotalwithVAT = calculateSaleTotalWithVAT();
        
        return saleTotalwithVAT;
    }
    
    private void addItemsVATtoSaleVAT(MonetaryValue itemTotal , VATRate vatRate){
        saleVAT.add(itemTotal.calculateVAT(vatRate));
    }
    
    private MonetaryValue calculateItemTotal(ItemDTO itemDTO, Quantity quantity){
        MonetaryValue itemTotal = itemDTO.getPricePerUnit().multiplíedByQuantity(quantity);
        return itemTotal;
    }
    
    private MonetaryValue calculateSaleTotalWithVAT(){
        MonetaryValue saleTotalwithVAT = new MonetaryValue(saleTotal);
        saleTotalwithVAT.add(saleVAT);
        
        return saleTotalwithVAT;
    }
}
