package se.kth.iv1350.salesystem.integration;

/**
 * Thrown when an unexpected error occurs while calling 
 * the <code>InventorySystemHandler</code>
 */
public class InventoryDBException extends RuntimeException{
    
    /**
     * Creates a new instance representing the condition described in the
     * message 
     * @param message A message describing what went wrong
     */
    public InventoryDBException(String message){
        super(message);
    }
}
