/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.kth.iv1350.salesystem.datatypes;

import java.math.BigDecimal;

/**
 * Represents a quantity value
 * 
 * @author christopher.vigil
 */
public class Quantity {
    
    private double value;
    
    /**
     * creates a zero Quantity
     */
    public Quantity(){
        value = 0;
    }
    
    /**
     * Creates a new quantity from an int primitive data type 
     * 
     * @param value initial value of quality.
     */
    public Quantity(int value){
        this.value = value;
    }
    
    /**
     *  Creates a new quantity from a float primitive data type 
     * 
     * @param value initial value of quality.
     */
    public Quantity(double value){
        this.value = value;
    }
    
    /**
     * Creates a new Quantity from another Quantity
     * 
     * @param quantity the quantity which to duplicate
     */
    public Quantity(Quantity quantity){
        this.value = quantity.value;
    }
    
    /**
     * Returns the quantity as a float primitive data type
     * @return the quantity value as a float
     */
    public double getQuantityAsDouble(){
        return this.value;
    }
    
    /**
     * Increases the value of the quantity with another Quantity.
     * @param increase The value with which to increase the quantity.
     */
    public void addQuantity(Quantity increase){
        this.value += increase.value;
    }
    
    /**
     * Decreases the value of the quantity with another Quantity
     * @param decrease The value with which to decrease the quantity.
     */
    public void removeQuantity(Quantity decrease){
        this.value -= decrease.value;
    }
    
    /**
     * Converts quantity to BigDecimal
     * @return Quantity as BigDecimal
     */
    public BigDecimal toBigDecimal(){
        return new BigDecimal(value);
    }
    
    /**
     * Returns the value as a string
     * @return the value as a String
     */
    @Override
    public String toString(){
        return String.valueOf(value);
    }
    
    /**
     * Compares Quantity with another object to determine if they are equal
     * @param obj The Object with which to compare
     * @return Returns boolean value True equal, else False
     */
    @Override
    public boolean equals(Object obj){
        boolean isEqual = false;
        if (this.getClass() == obj.getClass())
        {
            Quantity quantity = (Quantity) obj;
            if (quantity.value == this.value)
                isEqual = true;
        }
        
        return isEqual;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + (int) (Double.doubleToLongBits(this.value) ^ (Double.doubleToLongBits(this.value) >>> 32));
        return hash;
    }
    
    
}
