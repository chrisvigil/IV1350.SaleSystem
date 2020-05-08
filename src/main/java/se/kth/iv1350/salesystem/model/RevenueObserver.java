package se.kth.iv1350.salesystem.model;

import se.kth.iv1350.salesystem.datatypes.MonetaryValue;

/**
 * A listener interface for receiving notifications of the generated 
 * revenue of a completed sale. A class that implements this interface will
 * be notified of the sale total including VAT when a sale is logged.
 */
public interface RevenueObserver {
    
    /**
     * Invoked when a sale has been logged
     * 
     * @param saleTotal The sale total including VAT of the completed sale.
     */
    void newSale(MonetaryValue saleTotal);
    
}
