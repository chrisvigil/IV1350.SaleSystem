package se.kth.iv1350.salesystem.view;

import se.kth.iv1350.salesystem.controller.AddItemReturnMessage;
import se.kth.iv1350.salesystem.controller.Controller;
import se.kth.iv1350.salesystem.datatypes.ItemID;

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
       
       ItemID dummyItem = new ItemID("0");
       AddItemReturnMessage returnMessage = contr.addItemToSale(dummyItem);
       System.out.println(formatedReturnMessage(returnMessage));
   }
   
   public String formatedReturnMessage(AddItemReturnMessage message){
       String messageAsString = "Item: " + message.getItemDescription() +
                                ", Price: " + message.getItemPrice() +
                                ", Runnning Total: " + message.getRunningTotal();
       
       return messageAsString;
   }
}
