package se.kth.iv1350.salesystem.controller;

import se.kth.iv1350.salesystem.datatypes.ItemID;

/**
 * Thrown when searching database for an ItemID that is not found
 */
public class ItemNotFoundException extends Exception{
    private ItemID itemID;
    
    /**
     * Creates a new instance with a message specifying which <code>ItemID</code>
     * could not be found
     * @param itemID The itemID that could not be found
     */
    public ItemNotFoundException(ItemID itemID){
        super("Unable to find " + itemID.getID() + " in inventory database");
        this.itemID = itemID;
    }
    
    /**
     * @return The <code>ItemID</code> that could not be found.
     */
    public ItemID getItemIDNotFound(){
        return itemID;
    }
}
