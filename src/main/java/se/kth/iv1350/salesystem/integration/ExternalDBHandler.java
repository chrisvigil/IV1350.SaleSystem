/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.kth.iv1350.salesystem.integration;

/**
 * This class is responsible for coordinating communication to and from 
 * external databases, 
 * 
 * @author christopher.vigil
 */
public class ExternalDBHandler {
    private AccountingSystemHandler accountingDBHandler;
    private InventorySystemHandler inventoryDBHandler;
    private SaleLogHandler saleLogHandler;
    
    /**
     * Creates a new ExternalDBHandler
     */
    public ExternalDBHandler(){
        accountingDBHandler = new AccountingSystemHandler();
        inventoryDBHandler = new InventorySystemHandler();
        saleLogHandler = new SaleLogHandler();
    }
}

