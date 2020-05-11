package se.kth.iv1350.salesystem.model;

import se.kth.iv1350.salesystem.datatypes.ItemID;

/**
 * A factory that generates a <code>Discount</code> for a specified 
 * <code>Customer</code>.
 */
class DiscountFactory {
    private static final DiscountFactory HANDLER = new DiscountFactory();
    
    private DiscountFactory(){};
    
    /**
     * Gets all available <code>Discount</code>s for a customer from a 
     * customer/discount database.
     * @param customer The customer who's discounts should be retrieved
     * @return The Customers aggregated discounts 
     */
    static Discount getDiscounts(Customer customer){
        CompositeDiscount discounts = new CompositeDiscount();
        
        // ---  This is a placeholder ---
        if(customer.getID().equals("1")){
            discounts.addDiscount(new DiscountOnItem(new ItemID("1"), "20"));
            discounts.addDiscount(new DiscountOnItem(new ItemID("2"), "50"));
            discounts.addDiscount(new DiscountOnTotal("5"));
        }
        // ------------------------------
        
        return discounts;
    }
    
    
    
    
}
