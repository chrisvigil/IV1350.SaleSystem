/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.kth.iv1350.salesystem.controller;

import java.util.Locale;
import se.kth.iv1350.salesystem.datatypes.ItemID;
import se.kth.iv1350.salesystem.datatypes.MonetaryValue;
import se.kth.iv1350.salesystem.datatypes.Quantity;
import se.kth.iv1350.salesystem.dto.ItemDTO;
import se.kth.iv1350.salesystem.dto.SaleDTO;
import se.kth.iv1350.salesystem.model.Store;
import se.kth.iv1350.salesystem.integration.ExternalDBHandler;
import se.kth.iv1350.salesystem.model.CashRegister;
import se.kth.iv1350.salesystem.integration.Printer;
import se.kth.iv1350.salesystem.model.Sale;

/**
 * This is the application's controller class, all calls from view to the model
 * and database handler go through here
 */
public class Controller {
    private final ExternalDBHandler dbhandler;
    private final CashRegister register;
    private final Store store;
    private Locale locale;
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
     * @param locale Locale for currency formatting.
     */
    public void startNewSale(Locale locale){
        this.locale = locale;
        sale = new Sale(store);
    }
    
    /**
     * Attempts to add an item to the sale
     * @param itemID The item identifier of the item to be added
     * @param quantity the Quantity of the item to be added
     * @return A <code>AddItemReturnMessage</code> containing sales data or
     * <code>null</code> if item was not found.
     */
    public AddItemReturnMessage addItemToSale(ItemID itemID, Quantity quantity){
        return internalAddItem(itemID, quantity);
    }
    
    /**
     * Attempts to add an item to the sale. Quantity is assumed to be 1.
     * @param itemID The item identifier of the item to be added
     * @return A <code>AddItemReturnMessage</code> containing sales data or
     * <code>null</code> if item was not found.
     */
    public AddItemReturnMessage addItemToSale(ItemID itemID){
        Quantity quantity = new Quantity(1);
        return internalAddItem(itemID, quantity); 
    }
    
    private AddItemReturnMessage internalAddItem(ItemID itemID, Quantity quantity){
        ItemDTO foundItem = dbhandler.getItemData(itemID);
        
        AddItemReturnMessage returnMessage;
        if (foundItem == null){
            returnMessage = null;
        }
        else{
            MonetaryValue runningTotal = sale.addItemToBasket(foundItem, quantity);
            returnMessage = new AddItemReturnMessage(foundItem, quantity, runningTotal, locale);
        }
        
        return returnMessage;
    }
    
    /**
     * Presents total price of sale including VAT
     * @return 
     */
    public String endSale(){
        return sale.endSale().currencyFormat(locale);
    }
    
    /**
     * Makes a cash payment of sale.
     * @param payment The cash amount made as payment
     * @return The amount in change
     */
    public MonetaryValue makeCashPayment(MonetaryValue payment){
        MonetaryValue cashBack = sale.makeCashPayment(payment);
        logSale();
        updateCashRegister(payment, cashBack);
        printRecipt();
        
        return cashBack;
    }
    
    private void logSale(){
        SaleDTO saleLog = sale.logSale(locale);
        dbhandler.logSale(saleLog);
    }
    
    private void updateCashRegister(MonetaryValue payment, MonetaryValue cashBack){
        MonetaryValue netCashToRegister = new MonetaryValue(payment.subtract(cashBack));
        register.addToBalance(netCashToRegister);
    }
    
    private void printRecipt(){
        sale.printRepeict(printer);
    }
            
}
