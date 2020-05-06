package se.kth.iv1350.salesystem.integration;

import se.kth.iv1350.salesystem.datatypes.ItemID;
import se.kth.iv1350.salesystem.dto.ItemDTO;
import se.kth.iv1350.salesystem.dto.SaleDTO;

/**
 * This class is responsible for coordinating communication to and from 
 * external databases. This method is a placeholder and is not meant to be a 
 * finished implementation.
 */
public class ExternalDBHandler {
    private final AccountingSystemHandler accountingDBHandler;
    private final InventorySystemHandler inventoryDBHandler;
    private final SaleLogHandler saleLogHandler;
    
    /**
     * Creates a new ExternalDBHandler instance.
     */
    public ExternalDBHandler(){
        accountingDBHandler = new AccountingSystemHandler();
        inventoryDBHandler = new InventorySystemHandler();
        saleLogHandler = new SaleLogHandler();
    }
    
    /**
     * Creates a new ExternalDBHandler instance with a specified
     * inventory database file
     * @param inventoryDB The path or name of the Inventory database file
     */
    public ExternalDBHandler(String inventoryDB){
        accountingDBHandler = new AccountingSystemHandler();
        inventoryDBHandler = new InventorySystemHandler(inventoryDB);
        saleLogHandler = new SaleLogHandler();
    }
    
    /**
     * Searches an external database for a requested itemID
     * @param itemID The item identifier of the requested item
     * @return If item is found an itemDTO is returned representing the
     * requested item. If item is not found null is returned.
     */
    public ItemDTO getItemData(ItemID itemID) throws InventoryDBException{
        return inventoryDBHandler.getItemData(itemID);
    }
    
    /**
     * Logs a completed sale. 
     * @param saleLog Contains record of completed sale.
     */
    public void logSale(SaleDTO saleLog){
        saleLogHandler.LogSale(saleLog);
        accountingDBHandler.LogSale(saleLog);
        inventoryDBHandler.updateInventory(saleLog);
    }
    /**
     * Reads a inventory database from a file
     * @param filename 
     */
    void readInventoryDBfromFile(String filename){
        inventoryDBHandler.readDBFromFile(filename);
    }
}

