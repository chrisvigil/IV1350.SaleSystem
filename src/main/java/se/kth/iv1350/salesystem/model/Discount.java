package se.kth.iv1350.salesystem.model;

import se.kth.iv1350.salesystem.datatypes.MonetaryValue;

/**
 * Defines the a to calculate a discount applied to a sale and return the 
 * amount to deduct from the sale total including VAT.
 */
interface Discount {
    
    /**
     * Calculates a discount and returns the amount to 
     * deduct from the sale total including VAT.
     * @param sale The sale which to calculate the discount for
     * @return The amount to deduct from the sale.
     */
    MonetaryValue calculateDiscount(Sale sale);
}
