/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.kth.iv1350.salesystem.model;

import se.kth.iv1350.salesystem.datatypes.MonetaryValue;

/**
 * Represents a payment
 */
class Payment {
    enum Type{CASH}
    
    private final MonetaryValue ammount;
    private final Type type;
    private MonetaryValue change;
    
    /**
     * Creates a new instance of payment.
     * @param payment The amount payed.
     * @param type The type of payment.
     */
    Payment(MonetaryValue payment, Type type){
        this.ammount = payment;
        this.type = type;
    }
    
    /**
     * Calculates change for cash payment
     * @param saleTotal
     * @return 
     */
    MonetaryValue calculateChange(MonetaryValue saleTotal){
        this.change = saleTotal.subtract(ammount);
        return change;
    }
    
    MonetaryValue getAmmount(){
        return ammount;
    }
    
    MonetaryValue getChange(){
        return change;
    }
    
    Type getType(){
        return type;
    }
}
