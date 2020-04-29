/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.kth.iv1350.salesystem.datatypes;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.Objects;

/**
 * Represents a monetary value as an immutable object.
 */
public class MonetaryValue {
    private final BigDecimal value;
    
    private final BigDecimal ONEHUNDRED = new BigDecimal("100");
    
    
    /**
     * Creates a <code>MonetaryValue</code> with the value of 0.
     */
    public MonetaryValue(){
        this.value = BigDecimal.ZERO;
    }
    
    /**
     * Creates a new <code>MonetaryValue</code> form a string.
     * 
     * @param value The <code>MonetaryValue</code>'s initial value as a string.
     */
    public MonetaryValue(String value) throws IllegalArgumentException{
        if (Double.parseDouble(value) < 0){
            throw new IllegalArgumentException("Monetary Values may not be less then 0");
        }
        this.value = new BigDecimal(value);
    }
    
    /**
     * Creates a new <code>MonetaryValue</code> from a BigDecimal value.
     * 
     * @param value The <code>MonetaryValue</code>'s initial value as a BigDecimal.
     */
    public MonetaryValue(BigDecimal value) throws IllegalArgumentException{
        if (value.compareTo(BigDecimal.ZERO) < 0){
            throw new IllegalArgumentException("Monetary Values may not be less then 0");
        }
        this.value = value;
    }
    
    /**
     * Creates a new <code>MonetaryValue</code> from existing <code>MonetaryValue</code>.
     * 
     * @param monetaryValue The <code>MonetaryValue</code> to duplicate.
     */
    public MonetaryValue(MonetaryValue monetaryValue){
        this.value = monetaryValue.value;
    }
    
     /**
     * Creates <code>BigDecimal</code> value from <code>MonetaryValue</code>.
     * @return <code>MonetaryValue</code> as <code>BigDecimal</code>.
     */
    public BigDecimal toBigDecimal(){
        return value;
    }  
    
    /**
     * Creates a new <code>MonetaryValue</code> by adding another 
     * <code>MonetaryValue</code>.
     * @param increase the <code>MonetaryValue</code> which to increase with.
     * @return The sum of the two values
     */
    public MonetaryValue add(MonetaryValue increase){
        BigDecimal newValue = this.value.add(increase.value);
        
        return new MonetaryValue(newValue);
    }
    
    /**
     * Returns the subtract between the <code>MonetaryValue</code> and another.
     * @param subtractor The <code>MonetaryValue</code> to subtract with.
     * @return The subtract.
     */
    public MonetaryValue subtract(MonetaryValue subtractor){
        if(this.value.compareTo(subtractor.value) < 0){
            throw new IllegalArgumentException("subtractor may not be larger then value");
        }
        BigDecimal difference = this.value.subtract(subtractor.toBigDecimal());
        return new MonetaryValue(difference);
    }
    
    /**
     * Calculates the <code>MonetaryValue</code> multiplied by a <code>Quantity</code>
     * and returns it as a new <code>MonetaryValue</code>.
     * @param quantity The quantity with which to multiply with.
     * @return the product of the MonetaryValue and Quantity.
     */
    public MonetaryValue multiplíedByQuantity(Quantity quantity){
        BigDecimal total = this.value.multiply(quantity.toBigDecimal());
        return new MonetaryValue(total);
    }
    
    /**
     * Calculates VAT from  <code>MonetaryValue</code>.
     * @param vatRate The VAT rate.
     * @return The calculated VAT.
     */
    public MonetaryValue calculateVAT(VATRate vatRate){
        BigDecimal vatRateAsADecimal = new BigDecimal(vatRate.getValue()).divide(ONEHUNDRED);
        BigDecimal vat = this.value.multiply(vatRateAsADecimal);
        return new MonetaryValue(vat);
    }

    /**
     * Calculates value plus <code>VATRate</code>
     * @param vatRate The VAT rate
     * @return the MonetaryValue with a <code>VATRate</code> applied.
     */
    public MonetaryValue includingVAT(VATRate vatRate){
        MonetaryValue vat = calculateVAT(vatRate);
        MonetaryValue valueWithVAT = this.add(vat);
        
        return valueWithVAT;
    }
    
    /**
     * Rounds the <code>MonetaryValue</code> to 2 decimal places, rounding up.
     * @return The rounded value.
     */
    public MonetaryValue roundVaule(){
        BigDecimal rounded = this.value.setScale(2, RoundingMode.HALF_UP);
        return new MonetaryValue(rounded);
    }
    
    /**
     * Creates <code>String</code> value from <code>MonetaryValue</code>.
     * @return <code>MonetaryValue</code> as <code>String</code>.
     */
    @Override
    public String toString(){
        return value.toString();
    }
    
    /**
     * Compares a  <code>MonetaryValue</code> to another object to determine
     * if they are equal.
     * @param obj The object with which to compare.
     * @return <code>true</code> if equal, otherwise <code>false</code>.
     */
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
