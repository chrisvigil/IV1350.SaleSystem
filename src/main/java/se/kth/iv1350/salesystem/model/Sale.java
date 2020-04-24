/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.kth.iv1350.salesystem.model;

import se.kth.iv1350.salesystem.datatypes.MonetaryValue;
import se.kth.iv1350.salesystem.datatypes.Quantity;
import se.kth.iv1350.salesystem.dto.ItemDTO;

/**
 * Represents a compleat sale
 * 
 * @author christopher.vigil
 */
public class Sale {
    private final Store store;
    private final Basket basket;
    
    /**
     * Creates a new store object
     * @param store The store at which the sale takes place 
     */
    public Sale(Store store){
        this.store = store;
        this.basket = new Basket();
    }
    
    /**
     * Adds an item to the sale
     * @param itemDTO The item to add.
     * @param quantity The item's quantity.
     * @return saleTotal The current running total of the sale.
     */
    public MonetaryValue addItemToBasket(ItemDTO itemDTO, Quantity quantity){
        basket.addItem(itemDTO, quantity);
        MonetaryValue saleTotal = new MonetaryValue(); // TODO: implement sale total calculation
        return saleTotal;
    }
}
