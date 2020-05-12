package se.kth.iv1350.salesystem.startup;

import java.io.IOException;
import se.kth.iv1350.salesystem.controller.Controller;
import se.kth.iv1350.salesystem.datatypes.Address;
import se.kth.iv1350.salesystem.view.View;

/**
 * Contains the <code>main</code> method that performs 
 * startup of the application.
 * @author christopher.vigil
 */
public class Main {
    
    /**
     * Starts the application 
     * 
     * @param args does not take any command line parameters.
     */
    public static void main(String[] args) throws IOException{
        Controller contr = new Controller();
        View view = new View(contr);
        view.fakeInteractiveSale();
    }
    
}
