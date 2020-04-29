package se.kth.iv1350.salesystem.datatypes;

import java.math.BigDecimal;

/**
 * Represents a quantity value
 */
public class Quantity {
    
    private double value;
    
    /**
     * creates a single Quantity
     */
    public Quantity(){
        value = 1;
    }
    
    /**
     * Creates a new quantity from an int primitive data type.
     * @param value Initial value of quantity
     * .
     */
    public Quantity(int value) throws IllegalArgumentException{
        if (value <= 0){
            throw new IllegalArgumentException("Quantities must be greater then 0");
        }
        this.value = value;
    }
    
    /**
     *  Creates a new quantity from a float primitive data type.
     * @param value Initial value of quantity
     */
    public Quantity(double value) throws IllegalArgumentException{
        if (value <= 0){
            throw new IllegalArgumentException("Quantities must be greater then 0");
        }
        this.value = value;
    }
    
    /**
     *  Creates a new quantity from a String
     * @param value Initial value of quantity
     */
    public Quantity(String value)throws IllegalArgumentException{
        double asDouble;
        try{
            asDouble = Double.parseDouble(value);
        }
        catch(NumberFormatException ex){
            throw new IllegalArgumentException("String must represent a number");
        }
        if (asDouble <= 0){
            throw new IllegalArgumentException("Quantities must be greater then 0");
        }
        
        this.value = asDouble;
    }
    
    /**
     * Creates a new Quantity from another Quantity
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
        String ValueAsString;
        if (value % 1 == 0)
            ValueAsString = String.valueOf((int)value);
        else
            ValueAsString = String.valueOf(value);
        
        return ValueAsString;
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
}
