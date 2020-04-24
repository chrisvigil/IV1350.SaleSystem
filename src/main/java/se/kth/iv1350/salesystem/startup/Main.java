package se.kth.iv1350.salesystem.startup;

import se.kth.iv1350.salesystem.model.Store;
import se.kth.iv1350.salesystem.controller.Controller;
import se.kth.iv1350.salesystem.view.View;

/**
 * Contains the <code>main</code> method that performs 
 * startup of the application.
 * @author christopher.vigil
 */
public class Main {
    static private String storeName = "Some Store";
    static private String storeAddress = "Somestreet 42, 111111, SomeCity, SomeCounrty";
    /**
     * Starts the application 
     * 
     * @param args does not take any command line parameters.
     */
    public static void main(String[] args){
        Store store = new Store(storeName,storeAddress);
        Controller contr = new Controller(store);
        View view = new View(contr);
        view.runFakeExecution();
    }
    
}
