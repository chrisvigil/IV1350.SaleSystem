package se.kth.iv1350.salesystem.model;

import se.kth.iv1350.salesystem.datatypes.MonetaryValue;
import se.kth.iv1350.salesystem.dto.SaleDTO;

/**
 * Defines a discount on the sale total
 */
class DiscountOnTotal implements Discount{
    private final String rate;
    
    /**
     * A new instance of the discount
     * @param rate The percentage (0 to 100) to discount from the sale total.
     */
    DiscountOnTotal (String rate){
        this.rate = rate;
    }
    
     /**
     * Applies the discount to the specified <code>Sale</code>
     * @param sale The sale which to calculate the discount for.
     * @return The amount to deduct from the sale after applying discount
     */
    @Override
    public MonetaryValue calculateDiscount(SaleDTO saleDTO) {
        MonetaryValue discountAmount = saleDTO.getSaleTotal().calculatePercentage(rate);
        
        return discountAmount;
    }
    
}
