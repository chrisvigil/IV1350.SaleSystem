package se.kth.iv1350.salesystem.controller;

import java.util.ArrayList;
import java.util.List;
import se.kth.iv1350.salesystem.integration.ItemNotFoundException;
import java.util.Locale;
import se.kth.iv1350.salesystem.datatypes.ItemID;
import se.kth.iv1350.salesystem.datatypes.MonetaryValue;
import se.kth.iv1350.salesystem.datatypes.Quantity;
import se.kth.iv1350.salesystem.dto.ItemDTO;
import se.kth.iv1350.salesystem.dto.SaleDTO;
import se.kth.iv1350.salesystem.model.Store;
import se.kth.iv1350.salesystem.integration.ExternalDBHandler;
import se.kth.iv1350.salesystem.integration.InventoryDBException;
import se.kth.iv1350.salesystem.integration.Printer;
import se.kth.iv1350.salesystem.model.Sale;
import se.kth.iv1350.salesystem.model.RevenueObserver;

/**
 * This is the application's controller class, all calls from view to the model
 * and database handler go through here
 */
public class Controller {
    private final ExternalDBHandler dbhandler;
    private final Store store;
    private Locale locale;
    private Printer printer;
    private Sale sale;
    private List<RevenueObserver> revenueObservers = new ArrayList<>();
    
    /**
     * Creates a new controller
     * 
     * @param store Contains information about the store in which the sale
     * takes place.
     */
    public Controller(Store store){
        this.store = store;
        dbhandler = new ExternalDBHandler();
        printer = new Printer();
    }
    
    /**
     * The specified observer will be notified of the sale total including VAT
     * when the sale is logged.
     * @param revenueObserver The observer to notify.
     */
    public void addTotalRevenueObserver(RevenueObserver revenueObserver){
        revenueObservers.add(revenueObserver);
    }
    
    /**
     * Starts a new sale instance
     * @param locale <code>Locale</code> for currency formatting.
     */
    public void startNewSale(Locale locale){
        this.locale = locale;
        sale = new Sale(store);
        sale.addRevenueObservers(revenueObservers);
    }
    
    /**
     * Attempts to add an item to the sale
     * @param itemID The item identifier of the item to be added
     * @param quantity the <code>Quantity</code> of the item to be added
     * @return A <code>ReturnMessage</code> containing sales data or
     * <code>null</code> if item was not found.
     * @throws se.kth.iv1350.salesystem.integration.ItemNotFoundException If
     * the requested item was not found in database.
     * 
     */
    public ReturnMessage addItemToSale(ItemID itemID, Quantity quantity) 
            throws ItemNotFoundException, SystemOperationException{
        return internalAddItem(itemID, quantity);
    }
    
    /**
     * Attempts to add an item to the sale.Quantity is assumed to be 1.
     * @param itemID The item identifier of the item to be added
     * @return A <code>ReturnMessage</code> containing sales data or
     * <code>null</code> if item was not found.
     * @throws se.kth.iv1350.salesystem.integration.ItemNotFoundException If
     * the requested item was not found in database.
     */
    public ReturnMessage addItemToSale(ItemID itemID) 
            throws ItemNotFoundException, SystemOperationException{
        Quantity quantity = new Quantity(1);
        return internalAddItem(itemID, quantity); 
    }
    
    private ReturnMessage internalAddItem(ItemID itemID, Quantity quantity) 
            throws ItemNotFoundException, SystemOperationException{
        ItemDTO foundItem;
        
        try{
            foundItem = dbhandler.getItemData(itemID);
        }catch(InventoryDBException ex){
            throw new SystemOperationException("Unable to connect to item database",ex);
            
        }
        
        ReturnMessage returnMessage;
        if (foundItem == null){
            returnMessage = null;
        }
        else{
            MonetaryValue runningTotal = sale.addItemToBasket(foundItem, quantity);
            returnMessage = new ReturnMessage(foundItem, quantity, runningTotal, locale);
        }
        
        return returnMessage;
    }
    
    /**
     * Signals that no more items will be added and 
     * presents total price of sale including VAT
     * @return The Total price of sale including VAT    
     */
    public String endSale(){
        return sale.endSale().currencyFormat(locale);
    }
    
    /**
     * Makes a cash payment and logs sale.
     * @param payment The cash amount made as payment
     * @return The amount in change
     */
    public MonetaryValue makeCashPaymentandLogSale(MonetaryValue payment){
        MonetaryValue cashBack = sale.makeCashPayment(payment);
        logSale();
        printRecipt();
        
        return cashBack;
    }
    
    private void logSale(){
        SaleDTO saleLog = sale.logSale(locale);
        dbhandler.logSale(saleLog);
    }
    
    private void printRecipt(){
        sale.printRepeict(printer);
    }
            
}
