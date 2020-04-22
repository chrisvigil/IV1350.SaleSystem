/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.kth.iv1350.salesystem.model;

/**
 * Represents a compleat sale
 * 
 * @author christopher.vigil
 */
public class Sale {
    private final Store store;
    
    /**
     * Creates a new store object
     * @param store 
     */
    public Sale(Store store){
        this.store = store;
    }
}
