/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.kth.iv1350.salesystem.model;

import se.kth.iv1350.salesystem.datatypes.Address;

/**
 * Container for store name and address.
 */
public class Store {
    private final String name;
    private final Address address;
   /**
    * Creates and instance of a store.
    * 
    * @param storeName The store's name.
    * @param storeAddress The store's address.
    */
    public Store(String storeName, Address storeAddress){
        this.name = storeName;
        this.address = new Address(storeAddress);
    }
    
    public String getName(){
        return name;
    }
    
    public Address getAddress(){
        return new Address(address);
    }
    
}
