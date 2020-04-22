/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.kth.iv1350.salesystem.controller;

import se.kth.iv1350.salesystem.model.Store;
import se.kth.iv1350.salesystem.integration.ExternalDBHandler;
import se.kth.iv1350.salesystem.model.CashRegister;
import se.kth.iv1350.salesystem.integration.Printer;
import se.kth.iv1350.salesystem.model.Sale;

/**
 * This is the application's controller class, all calls from view to the model
 * and database handler go through here
 * @author christopher.vigil
 */
public class Controller {
    private final ExternalDBHandler dbhandler;
    private final CashRegister register;
    private final Store store;
    private Printer printer;
    private Sale sale;
    /**
     * Creates a new controller
     * 
     * @param store Contains information about the store in which the sale
     * takes place.
     */
    public Controller(Store store){
        this.store = store;
        dbhandler = new ExternalDBHandler();
        register = new CashRegister(); 
        printer = new Printer();
    }
    
    /**
     * Starts a new sale instance
     */
    public void startNewSale(){
        sale = new Sale(store);
    }
}
