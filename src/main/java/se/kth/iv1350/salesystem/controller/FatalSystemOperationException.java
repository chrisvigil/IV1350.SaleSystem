package se.kth.iv1350.salesystem.controller;

/**
 * Thrown when a unrecoverable exception has been caught and logged 
 */
public class FatalSystemOperationException extends RuntimeException{
    
    FatalSystemOperationException(String message, Exception cause){
        super(message, cause);
    }
}
