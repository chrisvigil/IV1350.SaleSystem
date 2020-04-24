/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.kth.iv1350.salesystem.datatypes;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * Represents a monetary value
 *
 * @author christopher.vigil
 */
public class MonetaryValue {
    private BigDecimal value;
    
    private final BigDecimal ONEHUNDRED = new BigDecimal("100");
    
    
    /**
     * Creates a MonetaryValue with the value of 0
     */
    public MonetaryValue(){
        this.value = new BigDecimal("0");
    }
    
    /**
     * Creates a new MonetaryValue form a string
     * 
     * @param value The MonetaryValue's initial value as a string
     */
    public MonetaryValue(String value){
        this.value = new BigDecimal(value);
    }
    
    /**
     * Creates a new MonetaryValue from a BigDecimal value
     * @param value 
     */
    public MonetaryValue(BigDecimal value){
        this.value = value;
    }
    
    /**
     * Creates a new MonetaryValue from existing MonetaryValue
     * 
     * @param monetaryValue The MonetaryValue to duplicate
     */
    public MonetaryValue(MonetaryValue monetaryValue){
        this.value = monetaryValue.value;
    }
    
    /**
     * Increases the MonetaryValue by another MonetaryValue.
     * 
     * @param increase the MonetaryValue which to increase with
     */
    public void add(MonetaryValue increase){
        this.value = this.value.add(increase.value);
    }
    
    public MonetaryValue multiplíedByQuantity(Quantity quantity){
        BigDecimal total = this.value.multiply(quantity.toBigDecimal());
        return new MonetaryValue(total);
    }
    
    /**
     * Returns the MonetaryValue with a VATRate applied
     * @param vatRate VAT rate as a percentage, ie 50%
     * @return the MonetaryValue with a VATRate applied
     */
    public MonetaryValue valueWithVAT(MonetaryValue vatRate){
        BigDecimal vatRateAsADecimal = vatRate.value.divide(ONEHUNDRED);
        BigDecimal valueWithVAT = this.value.multiply(vatRateAsADecimal);
        return new MonetaryValue(valueWithVAT);
    }
    
    public BigDecimal toBigDecimal(){
        return value;
    }            
    
 
    @Override
    public String toString(){
        return value.toString();
    }
    
    @Override
    public boolean equals(Object obj){
        boolean isEqual = false;
        if (this.getClass() == obj.getClass())
        {
            MonetaryValue monetaryValue = (MonetaryValue) obj;
            if ((monetaryValue.value).equals(this.value))
                isEqual = true;
        }
        
        return isEqual;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + Objects.hashCode(this.value);
        return hash;
    }
}
