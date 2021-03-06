package se.kth.iv1350.salesystem.view;

import java.io.IOException;
import java.util.Locale;
import se.kth.iv1350.salesystem.controller.ReturnMessage;
import se.kth.iv1350.salesystem.controller.Controller;
import se.kth.iv1350.salesystem.controller.FatalSystemOperationException;
import se.kth.iv1350.salesystem.integration.ItemNotFoundException;
import se.kth.iv1350.salesystem.datatypes.ItemID;
import se.kth.iv1350.salesystem.datatypes.MonetaryValue;
import se.kth.iv1350.salesystem.datatypes.Quantity;
import se.kth.iv1350.salesystem.dto.CustomerIdDTO;
import se.kth.iv1350.salesystem.util.ErrorLogger;

/**
 * Placeholder view class
 */
public class View {

    private final Controller contr;
    private Locale locale = new Locale("sv", "SE");
    private ErrorLogger logger;

    /**
     * Create a new controller
     *
     * @param contr The controller which the View layer should communicate with
     * the other application layers.
     * @throws java.io.IOException if error log file could not be found or created.
     */
    public View(Controller contr) throws IOException {
        this.logger = new ErrorLogger();
        this.contr = contr;
        this.contr.addTotalRevenueObserver(new TotalRevenueView(locale));
    }

    /**
     * A simple placeholder CLI to test program
     */
    public void fakeInteractiveSale() {
        try {
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
                            printReturnMessage(returnMessage);
                        } catch (IllegalArgumentException ex) {
                            handleException("Invalid input, try again", ex);
                        } catch (ItemNotFoundException ex) {
                            handleException(ex.getItemIDNotFound().toString()
                                    + " is an invalid item identifier, try again", ex);
                        }
                    }

                }

                boolean choiceMade = false;
                while (!choiceMade) {
                    System.out.print("Would you like to apply a discount? (Y/N): ");
                    input = in.next();
                    if (input.equals("y") || input.equals("Y")) {
                        System.out.print("Enter customer ID: ");
                        input = in.next();
                        contr.addCusomer(new CustomerIdDTO(input));
                        MonetaryValue totalAfterDiscount = contr.applyDiscounts();
                        System.out.println("Sale total after discounts have been applied: " + totalAfterDiscount.currencyFormat(locale));
                        choiceMade = true;
                    } else if (input.equals("n") || input.equals("N")) {
                        choiceMade = true;
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
                        } catch (IllegalArgumentException ex) {
                            handleException("Payment not sufficient, try again", ex);
                        }
                    } catch (IllegalArgumentException ex) {
                        handleException("Invalid value, try again", ex);
                    }
                }

                choiceMade = false;
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
        } catch (FatalSystemOperationException ex) {
            handleException("Nonrecoverable exception occured: " + ex.getMessage(), ex);
        } catch (Exception ex) {
            handleException("Nonrecoverable exception occured", ex);
        }
    }

    private void printReturnMessage(ReturnMessage message) {
        StringBuilder sb = new StringBuilder();
        sb.append("Item: ");
        sb.append(message.getQuantity()).append(" ");
        sb.append(message.getItemDescription());
        sb.append(", Price; ");
        sb.append(message.getItemPrice());
        sb.append(", Running Total: ");
        sb.append(message.getRunningTotal());

        System.out.println(sb);
    }

    private void handleException(String message, Exception ex) {
        System.out.println(message);
        if (!(ex instanceof IllegalArgumentException)
                && !(ex instanceof ItemNotFoundException)) {
            try {
                logger.logException(ex);
            } catch (IOException ioEx) {
                System.out.println("Could not write an error to the log file");
            }
        }

    }

}
