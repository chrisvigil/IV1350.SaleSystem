package se.kth.iv1350.salesystem.model;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import se.kth.iv1350.salesystem.datatypes.ItemID;
import se.kth.iv1350.salesystem.datatypes.Quantity;
import se.kth.iv1350.salesystem.dto.ItemDTO;
import se.kth.iv1350.salesystem.dto.SoldItemDTO;

/**
 * Collects all items added to sale
 */
class Basket {
    private final Map<ItemID, Item> contents;
    
    /**
     * Creates a new basket instance.
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
            addNewItemToBasket(itemDTO, quantity);
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
    
    private void addNewItemToBasket(ItemDTO itemDTO, Quantity quantity){
        Item item = new Item(itemDTO,quantity);
        contents.put(itemDTO.getItemID(), item);
    }
    
    /**
     * Returns a list of items in the <code>Basket</code>.
     * @return  Items in the basket.
     */
    List<SoldItemDTO> getSoldItems(){
        
        List soldItems = new LinkedList<SoldItemDTO>();
        for(Map.Entry<ItemID, Item> entry : contents.entrySet()){
            Item item = entry.getValue();
            soldItems.add(item.generateSoldItemDTO());
        }
        
        return soldItems;
    }
}
