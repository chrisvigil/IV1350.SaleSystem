package se.kth.iv1350.salesystem.datatypes;

/**
 * Defines VAT rate, there are 3 possible values <code>TWENTYFIVE</code>,
 * <code>TWELVE</code>, or <code>SIX</code>. Each represents a percentage
 * value as an integer (not a fraction of 100).
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
