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
    
    /**
     * @return The <code>Store</code> name
     */
    public String getName(){
        return name;
    }
    
    /**
     * @return The <code>Store</code> address
     */
    public Address getAddress(){
        return new Address(address);
    }
    
}
