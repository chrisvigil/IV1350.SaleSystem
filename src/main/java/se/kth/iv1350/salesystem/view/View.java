package se.kth.iv1350.salesystem.view;

import java.util.Locale;
import se.kth.iv1350.salesystem.controller.AddItemReturnMessage;
import se.kth.iv1350.salesystem.controller.Controller;
import se.kth.iv1350.salesystem.datatypes.ItemID;
import se.kth.iv1350.salesystem.datatypes.MonetaryValue;
import se.kth.iv1350.salesystem.datatypes.Quantity;

/**
 * Placeholder view class
 * 
 * @author christopher.vigil
 */
public class View {
   
    private final Controller contr;
    
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
       Locale locale = new Locale("sv", "SE");
       contr.startNewSale(locale);
       
       ItemID dummyItem;
       AddItemReturnMessage returnMessage;
       
       System.out.println("A new sale has been started\n");
       
       
       System.out.println("Item is added with id 0:");
       dummyItem = new ItemID("0");
       returnMessage = contr.addItemToSale(dummyItem);
       printReturnMessage(returnMessage);
       System.out.println();
       
       System.out.println("3 items is added with id 3:");
       dummyItem = new ItemID("3");
       Quantity quantity = new Quantity("3");
       returnMessage = contr.addItemToSale(dummyItem, quantity);
       printReturnMessage(returnMessage);
       System.out.println();
       
       
       System.out.println("Attempt to add item with non existent ID:");
       dummyItem = new ItemID("-1");
       returnMessage = contr.addItemToSale(dummyItem);
       printReturnMessage(returnMessage);
       System.out.println();
       
       System.out.println("Cashier ends sale:");
       String finalTotal = contr.endSale();
       System.out.println("Sale Total: " + finalTotal);
       System.out.println();
       
       System.out.println("a cash payment of 15 kr is made and recipt is printed: ");
       MonetaryValue payment = new MonetaryValue("150");
       contr.makeCashPayment(payment);
   }
   
   void printReturnMessage(AddItemReturnMessage message){
       if (message != null){
           System.out.println(message.toString());
           //StringBuilder sb = new StringBuilder();
           //sb.append("Item: ").append(message.)
       }
       else{
           System.out.println("Item id invalid");
       }
   }
   
}
