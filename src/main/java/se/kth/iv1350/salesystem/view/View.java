package se.kth.iv1350.salesystem.view;

import java.util.Locale;
import se.kth.iv1350.salesystem.controller.ReturnMessage;
import se.kth.iv1350.salesystem.controller.Controller;
import se.kth.iv1350.salesystem.controller.SystemOperationException;
import se.kth.iv1350.salesystem.integration.ItemNotFoundException;
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
    public View(Controller contr) {
        this.contr = contr;
    }

    /**
     * A simple placeholder CLI to test program
     */
    public void fakeInteractiveSale() {
        Locale locale = new Locale("sv", "SE");

        java.util.Scanner in;
        String input;

        String id;
        ItemID item;
        ReturnMessage returnMessage;

        boolean newSale = true;

        while (newSale) {
            in = new java.util.Scanner(System.in);
            in.useLocale(locale);
        
            contr.startNewSale(locale);
            boolean basketOpen = true;
            boolean paymentNeeded = true;

            System.out.println("A new sale has been started\n");

            System.out.println("--------------------------------------------");
            System.out.println("Type 'pay' to end sale make payment.");
            System.out.println("Type 'abort' to abort sale.\n");
            System.out.println("Enter a quantity after the id separated by\n "
                    + "a space to add more then one item at once.");
            System.out.println("--------------------------------------------\n");

            Quantity quantity;
            while (basketOpen) {
                System.out.print("Enter an item id to add to sale: ");
                input = in.nextLine();
                String[] parameters = input.split(" ");

                if (parameters[0].equals("pay")) {
                    basketOpen = false;
                    String finalTotal = contr.endSale();
                    System.out.println("Sale Total: " + finalTotal);
                } else if (parameters[0].equals("abort")) {
                    System.out.println("Sale canceled.\n");
                    basketOpen = false;
                    paymentNeeded = false;
                } else {
                    id = parameters[0];

                    if (parameters.length > 1) {
                        input = parameters[1];
                    } else {
                        input = "1";
                    }

                    try {
                        item = new ItemID(id);
                        quantity = new Quantity(input);
                        returnMessage = contr.addItemToSale(item, quantity);
                        System.out.println(returnMessage.toString());
                    } catch (IllegalArgumentException ex) {
                        System.out.println("Invalid input, try again");
                    } catch (ItemNotFoundException ex) {
                        System.out.println("Invalid item identifier, try again");
                    } catch (SystemOperationException ex) {
                        System.out.println("System error, try again");
                    }
                }

            }

            while (paymentNeeded) {
                System.out.print("Please enter payment: ");
                input = in.next();
                try {
                    MonetaryValue payment = new MonetaryValue(input);
                    try {
                        MonetaryValue change = contr.makeCashPaymentandLogSale(payment);
                        System.out.println("Change: " + change.currencyFormat(locale));
                        paymentNeeded = false;
                    } catch (Exception ex) {
                        System.out.println("Payment not sufficient, try again");
                    }
                } catch (IllegalArgumentException ex) {
                    System.out.println("Invalid value, try again");
                }
            }

            boolean choiceMade = false;
            while (!choiceMade) {
                System.out.print("Start new sale? (Y/N): ");
                input = in.next();
                if (input.equals("y") || input.equals("Y")) {
                    newSale = true;
                    choiceMade = true;
                } else if (input.equals("n") || input.equals("N")) {
                    newSale = false;
                    choiceMade = true;
                }
            }
        }

    }

}
