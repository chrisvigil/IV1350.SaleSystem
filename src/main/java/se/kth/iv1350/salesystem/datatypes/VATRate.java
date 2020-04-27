/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.kth.iv1350.salesystem.datatypes;

/**
 * Defines VAT rate, there are 3 possible values <code>TWENTYFIVE</code>,
 * <code>TWELVE</code>, or <code>SIX</code>
 * 
 * @author christopher.vigil
 */
public enum VATRate {
    TWENTYFIVE(25),TWELVE(12),SIX(6);
    
    private final int value;
    
    /**
     * Gets VAT rate as int
     * @return 
     */
    public int getValue(){
        return value;
    }
   
    private VATRate(int value){
        this.value = value;
    }
    
    /**
     * Returns Vat rate as string with % appended.
     * @return vat rate
     */
    @Override
    public String toString(){
        return value + "%";
    }
}