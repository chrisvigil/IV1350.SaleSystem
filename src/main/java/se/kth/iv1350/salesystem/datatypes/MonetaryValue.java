/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.kth.iv1350.salesystem.datatypes;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.Objects;

/**
 * Represents a monetary value
 */
public class MonetaryValue {
    private BigDecimal value;
    
    private final BigDecimal ONEHUNDRED = new BigDecimal("100");
    private final BigDecimal ZERO = new BigDecimal("0");
    
    
    /**
     * Creates a <code>MonetaryValue</code> with the value of 0.
     */
    public MonetaryValue(){
        this.value = ZERO;
    }
    
    /**
     * Creates a new <code>MonetaryValue</code> form a string.
     * 
     * @param value The <code>MonetaryValue</code>'s initial value as a string.
     */
    public MonetaryValue(String value){
        this.value = new BigDecimal(value);
    }
    
    /**
     * Creates a new <code>MonetaryValue</code> from a BigDecimal value.
     * 
     * @param value The <code>MonetaryValue</code>'s initial value as a BigDecimal.
     */
    public MonetaryValue(BigDecimal value){
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
     * Increases the <code>MonetaryValue</code> by another MonetaryValue.
     * 
     * @param increase the <code>MonetaryValue</code> which to increase with.
     */
    public void add(MonetaryValue increase){
        this.value = this.value.add(increase.value);
    }
    
    /**
     * Decreases the <code>MonetaryValue</code> by another MonetaryValue.
     * 
     * @param decrease the <code>MonetaryValue</code> which to decrease with.
     */
    public void subtract(MonetaryValue decrease){
        this.value = this.value.subtract(decrease.value);
    }
    
    /**
     * Returns the difference between the <code>MonetaryValue</code> and another.
     * @param subtractor The <code>MonetaryValue</code> to subtract with.
     * @return The difference.
     */
    public MonetaryValue difference(MonetaryValue subtractor){
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
     * @return the MonetaryValue with a <code>VATRate</code> applied.
     */
    public MonetaryValue calculateVAT(VATRate vatRate){
        BigDecimal vatRateAsADecimal = new BigDecimal(vatRate.getValue()).divide(ONEHUNDRED);
        BigDecimal vat = this.value.multiply(vatRateAsADecimal);
        return new MonetaryValue(vat);
    }
    
    /**
     * Creates <code>BigDecimal</code> value from <code>MonetaryValue</code>.
     * @return <code>MonetaryValue</code> as <code>BigDecimal</code>.
     */
    public BigDecimal toBigDecimal(){
        return value;
    }            
    
    /**
     * Creates <code>String</code> value from <code>MonetaryValue</code>.
     * @return <code>MonetaryValue</code> as <code>String</code>.
     */
    @Override
    public String toString(){
        Locale swedish = new Locale("sv", "SE");
        //NumberFormat CostFormat = NumberFormat.getCurrencyInstance(Locale.getDefault());
        
        NumberFormat CostFormat = NumberFormat.getCurrencyInstance(swedish);
        CostFormat.setMinimumFractionDigits( 1 );
        CostFormat.setMaximumFractionDigits( 2 );
        String formatedValue  = CostFormat.format(value.doubleValue());
        return formatedValue;
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
