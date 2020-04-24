/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.kth.iv1350.salesystem.model;

import se.kth.iv1350.salesystem.datatypes.Address;

/**
 *
 * @author christopher.vigil
 */
public class Store {
    private String name;
    private Address address;
   /**
    * Container for store name and address.
    * @param storeName The store's name.
    * @param storeAddress The store's address.
    */
    public Store(String storeName, Address storeAddress){
        this.name = storeName;
        this.address = new Address(storeAddress);
    }
    
}
