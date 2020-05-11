package se.kth.iv1350.salesystem.model;

import se.kth.iv1350.salesystem.datatypes.MonetaryValue;
import se.kth.iv1350.salesystem.dto.CustomerIdDTO;

/**
 * Represents a customer
 */
class Customer {
    private String customerID;
    private Discount discount;
    
    /**
     * Creates a new customer instance
     * @param idDTO The customers unique ID
     */
    Customer(CustomerIdDTO idDTO){
        this.customerID = idDTO.getID();
    }
    
    /**
     * Calculates the discounts to apply to a sale.
     * @param sale The sale for which the discounts should be calculated.
     * @return The total discount to deduct from the sale after applying VAT.
     */
    MonetaryValue calculateDiscount(Sale sale){
        addDiscounts();
        MonetaryValue discountAmmount = discount.calculateDiscount(sale);
        
        return discountAmmount;
    }
    
    private void addDiscounts(){
        discount = DiscountFactory.getDiscounts(this);
    }
    
    String getID(){
        return customerID;
    }
}
