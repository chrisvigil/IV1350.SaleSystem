/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.kth.iv1350.salesystem.controller;

/**
 *
 * @author christopher.vigil
 */
public class SystemOperationException extends Exception{
    
    SystemOperationException(String message, Exception cause){
        super(message, cause);
    }
}
