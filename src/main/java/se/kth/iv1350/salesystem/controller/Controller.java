package se.kth.iv1350.salesystem.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import se.kth.iv1350.salesystem.integration.ItemNotFoundException;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import se.kth.iv1350.salesystem.datatypes.ItemID;
import se.kth.iv1350.salesystem.datatypes.MonetaryValue;
import se.kth.iv1350.salesystem.datatypes.Quantity;
import se.kth.iv1350.salesystem.dto.CustomerIdDTO;
import se.kth.iv1350.salesystem.dto.ItemDTO;
import se.kth.iv1350.salesystem.dto.SaleDTO;
import se.kth.iv1350.salesystem.model.Store;
import se.kth.iv1350.salesystem.integration.ExternalDBHandler;
import se.kth.iv1350.salesystem.integration.InventoryDBException;
import se.kth.iv1350.salesystem.integration.Printer;
import se.kth.iv1350.salesystem.model.Sale;
import se.kth.iv1350.salesystem.model.RevenueObserver;
import se.kth.iv1350.salesystem.util.ErrorLogger;

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
    private ErrorLogger logger;

    /**
     * Creates a new controller
     *
     * @param store Contains information about the store in which the sale takes
     * place.
     * @throws java.io.IOException if log file could not be found or created.
     */
    public Controller(Store store) throws IOException {
        this.store = store;
        dbhandler = new ExternalDBHandler();
        printer = new Printer();
        logger = new ErrorLogger("controllerErrorLog.txt");
    }

    /**
     * The specified observer will be notified of the sale total including VAT
     * when the sale is logged.
     *
     * @param revenueObserver The observer to notify.
     */
    public void addTotalRevenueObserver(RevenueObserver revenueObserver) {
        revenueObservers.add(revenueObserver);
    }

    /**
     * Starts a new sale instance
     *
     * @param locale <code>Locale</code> for currency formatting.
     */
    public void startNewSale(Locale locale) {
        this.locale = locale;
        sale = new Sale(store);
        sale.addRevenueObservers(revenueObservers);
    }

    /**
     * Attempts to add an item to the sale
     *
     * @param itemID The item identifier of the item to be added
     * @param quantity the <code>Quantity</code> of the item to be added
     * @return A <code>ReturnMessage</code> containing sales data or
     * <code>null</code> if item was not found.
     * @throws ItemNotFoundException If the requested item was not found in the
     * database.
     * @throws FatalSystemOperationException if a non recoverable database error
     * occurred.
     */
    public ReturnMessage addItemToSale(ItemID itemID, Quantity quantity)
            throws ItemNotFoundException {
        return internalAddItem(itemID, quantity);
    }

    /**
     * Attempts to add an item to the sale.Quantity is assumed to be 1.
     *
     * @param itemID The item identifier of the item to be added
     * @return A <code>ReturnMessage</code> containing sale data.
     * @throws ItemNotFoundException If the requested item was not found in the
     * database.
     * @throws FatalSystemOperationException if a non recoverable database error
     * occurred.
     */
    public ReturnMessage addItemToSale(ItemID itemID)
            throws ItemNotFoundException {
        Quantity quantity = new Quantity(1);
        return internalAddItem(itemID, quantity);
    }

    private ReturnMessage internalAddItem(ItemID itemID, Quantity quantity)
            throws ItemNotFoundException {
        ItemDTO foundItem;

        try {
            foundItem = dbhandler.getItemData(itemID);
        } catch (InventoryDBException ex) {

            try {
                logger.logException(ex);
            } catch (IOException ioEx) {
                ioEx.printStackTrace();
            }

            throw new FatalSystemOperationException("Unable to connect to item database", ex);
        }

        MonetaryValue runningTotal = sale.addItemToBasket(foundItem, quantity);
        ReturnMessage returnMessage = new ReturnMessage(foundItem, quantity, runningTotal, locale);

        return returnMessage;
    }

    /**
     * Signals that no more items will be added and presents total price of sale
     * including VAT
     *
     * @return The Total price of sale including VAT
     */
    public String endSale() {
        return sale.endSale().currencyFormat(locale);
    }

    /**
     * Makes a cash payment and logs sale.
     *
     * @param payment The cash amount made as payment
     * @return The amount in change
     */
    public MonetaryValue makeCashPaymentandLogSale(MonetaryValue payment) {
        MonetaryValue cashBack = sale.makeCashPayment(payment);
        logSale();
        printReceipt();

        return cashBack;
    }

    /**
     * Adds a new customer to the SalenoDB
     *
     * @param customerID the customer id of the customer to be added
     */
    public void addCusomer(CustomerIdDTO customerID) {
        sale.addCustomer(customerID);
    }

    /**
     * Applies discount, if any, to the sale total.
     *
     * @return The sale total including VAT after discounts have been applied
     */
    public MonetaryValue applyDiscounts() {
        return sale.applyDiscounts();
    }

    private void logSale() {
        SaleDTO saleLog = sale.logSale(locale);
        dbhandler.logSale(saleLog);
    }

    private void printReceipt() {
        sale.printReceipt(printer);
    }

}
