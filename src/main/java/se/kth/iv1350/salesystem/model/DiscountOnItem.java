package se.kth.iv1350.salesystem.model;

import java.util.List;
import se.kth.iv1350.salesystem.datatypes.ItemID;
import se.kth.iv1350.salesystem.datatypes.MonetaryValue;
import se.kth.iv1350.salesystem.dto.SoldItemDTO;

/**
 * Defines a discount on a specific item
 */
class DiscountOnItem implements Discount{
    private final ItemID itemID;
    private final String discountRate;
    
    /**
     * A new instance of the discount.
     * @param itemID The item to with the discount should be applied.
     * @param discountRate The percentage (0 to 100) to discount from the price.
     */
    DiscountOnItem(ItemID itemID, String discountRate){
        this.itemID = itemID;
        this.discountRate = discountRate;
    }
    
    /**
     * Applies the discount to the specified <code>Sale</code>
     * @param sale The sale which to calculate the discount for.
     * @return The amount to deduct from the sale after applying discount
     */
    @Override
    public MonetaryValue calculateDiscount(Sale sale) {
        MonetaryValue discountAmount;
        
        List<SoldItemDTO> soldItems = sale.getSoldItems();
        for (SoldItemDTO item : soldItems){
            if (item.getItemID().equals(itemID)){
                discountAmount = calculateDiscountAmount(item);
                return discountAmount;
            }
        }
        
        return null;
    }
    
    private MonetaryValue calculateDiscountAmount(SoldItemDTO item){
        MonetaryValue itemPricePerUnit = item.getPricePerUnit().includingVAT(item.getVATRate());
        return itemPricePerUnit.calculatePercentage(discountRate).multiplíedByQuantity(item.getQuantity());
    }
    
}
