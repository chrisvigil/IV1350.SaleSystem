package se.kth.iv1350.salesystem.startup;

import se.kth.iv1350.salesystem.model.Store;
import se.kth.iv1350.salesystem.controller.Controller;
import se.kth.iv1350.salesystem.datatypes.Address;
import se.kth.iv1350.salesystem.view.View;

/**
 * Contains the <code>main</code> method that performs 
 * startup of the application.
 * @author christopher.vigil
 */
public class Main {
    private static final String PLACEHOLDER_STORENAME = "Some Store";
    private static final Address PLACEHOLDER_ADDRESS = new Address("Somestreet",42,11122,"SomeCity", "SomeCountry");
    
    /**
     * Starts the application 
     * 
     * @param args does not take any command line parameters.
     */
    public static void main(String[] args){
        Store store = setupStore();
        Controller contr = new Controller(store);
        View view = new View(contr);
        //view.runFakeExecution();
        view.fakeInteractiveSale();
    }
    
    /**
     * This is a placeholder for store setup, ideally the store name and address
     * should defined in a config file or similar.
     * 
     * @return a object of class Store
     */
    private static Store setupStore(){
        return new Store(PLACEHOLDER_STORENAME,PLACEHOLDER_ADDRESS);
    }
    
}
