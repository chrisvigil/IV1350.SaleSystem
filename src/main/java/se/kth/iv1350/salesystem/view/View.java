package se.kth.iv1350.salesystem.view;

import java.util.Locale;
import se.kth.iv1350.salesystem.controller.ReturnMessage;
import se.kth.iv1350.salesystem.controller.Controller;
import se.kth.iv1350.salesystem.controller.ItemNotFoundException;
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
   public void runFakeExecution() throws ItemNotFoundException{
       Locale locale = new Locale("sv", "SE");
       contr.startNewSale(locale);
       
       ItemID dummyItem;
       ReturnMessage returnMessage;
       
       System.out.println("A new sale has been started\n");
       
       
       System.out.println("Item is added with id 0:");
       dummyItem = new ItemID("0");
       returnMessage = contr.addItemToSale(dummyItem);
       System.out.println(checkReturnMessage(returnMessage));
       System.out.println();
       
       
       System.out.println("3 items is added with id 3:");
       dummyItem = new ItemID("3");
       Quantity quantity = new Quantity("3");
       returnMessage = contr.addItemToSale(dummyItem, quantity);
       System.out.println(checkReturnMessage(returnMessage));
       System.out.println();
       
       System.out.println("Item is added with id 0:");
       dummyItem = new ItemID("0");
       returnMessage = contr.addItemToSale(dummyItem);
       System.out.println(checkReturnMessage(returnMessage));
       System.out.println();
       
       
       System.out.println("Attempt to add item with non existent ID:");
       dummyItem = new ItemID("-1");
       returnMessage = contr.addItemToSale(dummyItem);
       System.out.println(checkReturnMessage(returnMessage));
       System.out.println();
       
       System.out.println("Cashier ends sale:");
       String finalTotal = contr.endSale();
       System.out.println("Sale Total: " + finalTotal);
       System.out.println();
       
       System.out.println("a cash payment of 15 kr is made and recipt is printed: ");
       MonetaryValue payment = new MonetaryValue("150");
       contr.makeCashPaymentandLogSale(payment);
   }
   
    private String checkReturnMessage(ReturnMessage message){
       if (message != null){
           return message.toString();
       }
       else{
           return "Item id invalid";
       }
    }
   
    /**
     * A simple placeholder CLI to test program
     */
    public void fakeInteractiveSale(){
       Locale locale = new Locale("sv", "SE");
       contr.startNewSale(locale);
       
       java.util.Scanner in = new java.util.Scanner (System.in);
       in.useLocale (locale);
       
       ItemID item;
       ReturnMessage returnMessage;
       
       System.out.println("A new sale has been started\n");
       boolean saleOpen = true;
       System.out.println("Type 'end' to end sale");
       
       String input;
       String id;
       Quantity quantity;
       while (saleOpen){
           System.out.print("Enter an item id to add to sale: ");
           input = in.next();
           
           if (input.equals("end")){
               saleOpen = false;
               String finalTotal = contr.endSale();
               System.out.println("Sale Total: " + finalTotal);
           }
           else{
               id = input;
               System.out.print("Enter a quantity: ");
               input = in.next();
               try{
                   item = new ItemID(id);
                   quantity = new Quantity(input);
                   returnMessage = contr.addItemToSale(item, quantity);
                   System.out.println(returnMessage.toString());
               }
               catch(IllegalArgumentException ex){
                   System.out.println("Invalid input, try again");
               }
               catch (ItemNotFoundException ex){
                   System.out.println("Invalid item identifier, try again");
               }
           }
           
       }
       
       boolean paymentMade = false;
       while (!paymentMade){
            System.out.print("Please enter payment: ");
            input = in.next();
            try{
                MonetaryValue payment = new MonetaryValue(input);
                try{
                    contr.makeCashPaymentandLogSale(payment);
                    paymentMade = true;
                }
                catch(Exception ex){
                    System.out.println("Payment not sufficient, try again");
                }
            }
            catch(IllegalArgumentException ex){
                System.out.println("Invalid value, try again");
            }
       }
       
       
       
   }
   
}
