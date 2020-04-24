package se.kth.iv1350.salesystem.model;

import java.util.HashMap;
import java.util.Map;
import se.kth.iv1350.salesystem.datatypes.ItemID;
import se.kth.iv1350.salesystem.datatypes.Quantity;
import se.kth.iv1350.salesystem.dto.ItemDTO;

/**
 * Collects all items added to sale
 * 
 * @author christopher.vigil
 */
class Basket {
    private final Map<ItemID, Item> contents;
    
    /**
     * Creates a new basket
     */
    Basket(){
        contents = new HashMap<>();
    }
    
    /**
     * Adds a quantity of an item to the basket. If the item already exists it's
     * quantity is increased by the quantity specified.
     * @param itemDTO The item to be added
     * @param quantity The quantity of the item to add
     */
    void addItem(ItemDTO itemDTO, Quantity quantity){
        ItemID itemID = itemDTO.getItemID();
        
        if (itemIsInBasket(itemID))
            updateItemQuantity(itemID, quantity);
        else{
            Item item = new Item(itemDTO,quantity);
            contents.put(itemID, item);
        }
    }
    
    private boolean itemIsInBasket(ItemID itemID){
        boolean isItemInBasket = contents.containsKey(itemID);
        return isItemInBasket;
    }
    
    private void updateItemQuantity(ItemID itemID, Quantity quantity)
    {
        contents.get(itemID).addQuanity(quantity);
    }
}
