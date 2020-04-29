package se.kth.iv1350.salesystem.model;

import se.kth.iv1350.salesystem.datatypes.MonetaryValue;

/**
 * Represents the cash register at the point of sale 
 * where physical cash is kept. This class has not been implemented yet and
 * is only a placeholder.
 */
public class CashRegister {
    private MonetaryValue registerCash;
    private final MonetaryValue PLACEHOLDER_CASH = new MonetaryValue("100");
    
    /**
     * creates a new CashRegister instance
     */
    public CashRegister(){
        registerCash = PLACEHOLDER_CASH;
    }
    
    /**
     * Adds cash in register.
     * @param netCashToAdd The amount added to register.
     */
    public void addToBalance(MonetaryValue netCashToAdd){
        registerCash = registerCash.add(netCashToAdd);
    }
    
    /**
     * Removes cash from register.
     * @param netCashToRemove The amount removed from register.
     */
    public void removedFromBalance(MonetaryValue netCashToRemove){
        registerCash = registerCash.subtract(netCashToRemove);
    }
    
    /**
     * @return The <code>CashRegister</code> balance.
     */
    public MonetaryValue getBalance(){
        return registerCash;
    }
}
