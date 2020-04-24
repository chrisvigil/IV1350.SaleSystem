package se.kth.iv1350.salesystem.view;

import se.kth.iv1350.salesystem.controller.Controller;

/**
 * Placeholder view class
 * 
 * @author christopher.vigil
 */
public class View {
   
    private Controller contr;
    
   /**
    * Create a new controller
    * 
    * @param contr The controller which the View layer should communicate with
    * the other application layers.
    */
   public View(Controller contr){
        this.contr = contr;
    }
   
   /**
    * Simulates a sale by executing all methods in the controller 
    */
   public void runFakeExecution(){
       contr.startNewSale();
       System.out.println("A new sale has been started");
   }
}
