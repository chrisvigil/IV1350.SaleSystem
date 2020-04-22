/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.kth.iv1350.salesystem.datatypes;

/**
 * Represents a quantity value
 * 
 * @author christopher.vigil
 */
public class Quantity {
    
    private float value;
    
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
    public Quantity(float value){
        this.value = value;
    }
    
    /**
     * Returns the quantity as a float primitive data type
     * 
     * @return the quantity value as a float
     */
    public float getFloatQuantity(){
        return this.value;
    }
    
    /**
     * Increases the value of the quantity with another Quantity.
     * 
     * @param increase The value which to increase the quantity.
     */
    public void addQuantity(Quantity increase){
        this.value += increase.getFloatQuantity();
    }
    
    /**
     * Returns the value as a string
     * 
     * @return the value as a String
     */
    @Override
    public String toString(){
        return String.valueOf(value);
    }
    
    
}
