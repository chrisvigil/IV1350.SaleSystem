package se.kth.iv1350.salesystem.model;

import java.util.LinkedList;
import java.util.List;
import se.kth.iv1350.salesystem.datatypes.MonetaryValue;

/**
 * A <code>Discount</code> that can apply multiple discounts. Any discount 
 * added to this composite will attempt to be calculated when applying this
 * discount.
 */
class CompositeDiscount implements Discount {
    private List<Discount> discounts = new LinkedList<>();
    
    CompositeDiscount(){
    }

    /**
     * Calculates all added discounts to a sale in the order they where added. 
     * @param sale The Sale for which to calculate discounts.
     * @return The total discounted amount to deduct from the sale.
     */
    @Override
    public MonetaryValue calculateDiscount(Sale sale) {
        MonetaryValue totalDiscountAmmount = new MonetaryValue();
        MonetaryValue discountAmmount;
        
        for (Discount discount : discounts){
            discountAmmount = discount.calculateDiscount(sale);
            if (discountAmmount != null){
                totalDiscountAmmount = totalDiscountAmmount.add(discount.calculateDiscount(sale));
            }
        }
        
        return totalDiscountAmmount;
    }
    
    /**
     * Adds a object that implements <code>Discount</code> to the composite.
     * @param discount The discount to add
     */
    void addDiscount(Discount discount){
        discounts.add(discount);
    }
}
