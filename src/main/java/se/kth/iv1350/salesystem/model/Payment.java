package se.kth.iv1350.salesystem.model;

import se.kth.iv1350.salesystem.datatypes.MonetaryValue;

/**
 * Represents a payment
 */
class Payment {
    enum Type{CASH}
    
    private final MonetaryValue amount;
    private final Type type;
    private MonetaryValue change;
    
    /**
     * Creates a new instance of payment.
     * @param payment The amount payed.
     * @param type The type of payment.
     */
    Payment(MonetaryValue payment, Type type){
        this.amount = payment;
        this.type = type;
    }
    
    /**
     * Calculates change and updates the <code>CashRegister</code>
     * @param saleTotal The sale total including VAT to pay
     * @return The change to return to the customer.
     */
    MonetaryValue makePayment(MonetaryValue saleTotal){
        if (type.equals(Type.CASH)){
            calculateChange(saleTotal);
            updateCashRegister(amount.subtract(change));
        }
        else{
            change = null;
        }
        
        return change;
    }
    
    private void updateCashRegister(MonetaryValue netCashToRegister ){
        CashRegister.getCashRegister().addToBalance(netCashToRegister);
    }
    
    private void calculateChange(MonetaryValue saleTotal){
        try{
            this.change = amount.subtract(saleTotal).roundVaule();
        }
        catch(IllegalArgumentException ex){
            throw new IllegalArgumentException("saleTotal must be less then or equal to payment ammount");
        }
    }
    
    /** 
     * @return The <code>Payment</code> amount
     */
    MonetaryValue getAmount(){
        return amount;
    }
    
    /** 
     * @return The <code>Payment</code> change
     */
    MonetaryValue getChange(){
        return change;
    }
    
    /** 
     * @return The <code>Payment</code> type
     */
    Type getType(){
        return type;
    }
}
