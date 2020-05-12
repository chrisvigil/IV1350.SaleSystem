package se.kth.iv1350.salesystem.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import se.kth.iv1350.salesystem.datatypes.MonetaryValue;
import se.kth.iv1350.salesystem.datatypes.Quantity;
import se.kth.iv1350.salesystem.datatypes.VATRate;
import se.kth.iv1350.salesystem.dto.CustomerIdDTO;
import se.kth.iv1350.salesystem.dto.ItemDTO;
import se.kth.iv1350.salesystem.dto.SaleDTO;
import se.kth.iv1350.salesystem.dto.SoldItemDTO;
import se.kth.iv1350.salesystem.integration.Printer;
import static se.kth.iv1350.salesystem.model.Payment.Type.*;

/**
 * Represents a compleat sale
 */
public class Sale {
    private final Store store;
    private final Basket basket;
    private MonetaryValue subTotal;
    private MonetaryValue saleVAT;
    private MonetaryValue discountAmount;
    private Payment payment;
    private Receipt receipt;
    private LocalDateTime timeOfSale;
    private Customer customer;
    private boolean saleOpen;
    private List<RevenueObserver> totalRevenueObservers = new ArrayList<>();
    
    /**
     * Creates a new instance representing one sale.
     * @param store The store at which the sale takes place
     */
    public Sale(Store store){
        this.store = store;
        this.basket = new Basket();
        this.subTotal = new MonetaryValue();
        this.saleVAT = new MonetaryValue();
        this.discountAmount = new MonetaryValue();
        
        saleOpen = true;
    }
    
    /**
     * All the specified observers will be notified of the sale total 
     * including VAT when this sale is logged
     * 
     * @param observers The observers to notify.
     */
    public void addRevenueObservers(List<RevenueObserver> observers){
        totalRevenueObservers.addAll(observers);
    }
    
    
    /**
     * Adds an item to the sale basket and updates sale total.
     * @param itemDTO The item to add.
     * @param quantity The item's quantity.
     * @return subTotal The current running subtotal of the sale.
     */
    public MonetaryValue addItemToBasket(ItemDTO itemDTO, Quantity quantity){
        basket.addItem(itemDTO, quantity);
        MonetaryValue itemTotal = calculateItemTotal(itemDTO, quantity);
        subTotal = subTotal.add(itemTotal);
        addItemsVATtoSaleVAT(itemTotal, itemDTO.getVATRate());
        
        MonetaryValue saleTotalwithVAT = calculateSaleTotalWithVAT();
        
        return saleTotalwithVAT;
    }
    
    /**
     * closes the sale and return sale total with VAT
     * @return The sale total with VAT.
     */
    public MonetaryValue endSale(){
        saleOpen = false;
        MonetaryValue saleTotalWithVAT = calculateSaleTotalWithVAT();
        return saleTotalWithVAT;
    }
    
    /**
     * Makes a payment of the sale with cash amount
     * @param cashPayment The amount payed
     * @return payment The amount to return in change
     */
    public MonetaryValue makeCashPayment(MonetaryValue cashPayment){
        MonetaryValue saleTotalWithVAT = calculateSaleTotalWithVAT();
        
        try{
            cashPayment.subtract(saleTotalWithVAT);
        }
        catch(IllegalArgumentException ex){
            throw new IllegalArgumentException("Payment value insufficient");
        }
        
        this.payment = new Payment(cashPayment,CASH);
        
        MonetaryValue change = this.payment.makePayment(saleTotalWithVAT);
        
        return change;
    }
    
    /**
     * Generates sale logs
     * 
     * @param locale Locale for currency formatting.
     * @return The sale log
     */
    public SaleDTO logSale(Locale locale){
        logTimeOfSale();
        SaleDTO saleLog = generateSaleLog();
        createReceipt(saleLog, locale);
        
        notifyRevenueObservers(saleLog.getSaleTotal());
        
        return saleLog;
    }
    
    /**
     * @return A list of sold items
     */
     List<SoldItemDTO> getSoldItems(){
        return basket.getSoldItems();
    }
    
    /**
     * Prints a receipt
     * @param printer The printer where the receipt is sent.
     */
    public void printReceipt(Printer printer){
        receipt.print(printer);
    }
    
    /**
     * Adds a customer to the sale
     * @param customerID The ID of the customer to add.
     */
    public void addCustomer(CustomerIdDTO customerID){
        this.customer = new Customer(customerID);
    }
    
    /**
     * Apply discounts, if any, to the sale.
     * @return The sale total including VAT after discounts have been applied
     */
    public MonetaryValue applyDiscounts(){
        if (customer != null){
            SaleDTO saleDTO= generateSaleLog();
            discountAmount = customer.calculateDiscount(saleDTO);
        }
        
        return calculateSaleTotalWithVAT();
    }
    
    /**
     * @return The sale total including VAT and discounts (if any).
     */
    MonetaryValue calculateSaleTotalWithVAT(){
        MonetaryValue saleTotalwithVAT = subTotal.add(saleVAT).subtract(discountAmount);
        
        return saleTotalwithVAT;
    }
    
    private void addItemsVATtoSaleVAT(MonetaryValue itemTotal , VATRate vatRate){
        saleVAT = saleVAT.add(itemTotal.calculateVAT(vatRate));
    }
    
    private MonetaryValue calculateItemTotal(ItemDTO itemDTO, Quantity quantity){
        MonetaryValue itemTotal = itemDTO.getPricePerUnit().multiplíedByQuantity(quantity);
        return itemTotal;
    }
    
    private void logTimeOfSale(){
        timeOfSale = LocalDateTime.now();
    }
    
    private SaleDTO generateSaleLog(){
        List<SoldItemDTO> soldItems = basket.getSoldItems();
        MonetaryValue saleTotalWithVAT = calculateSaleTotalWithVAT();
        SaleDTO saleLog;
        
        if(payment == null){
            saleLog = new SaleDTO(soldItems, subTotal, saleVAT, saleTotalWithVAT,
                    store.getName(), store.getAddress());
        }
        else if (discountAmount.equals(new MonetaryValue())){
            saleLog = new SaleDTO(soldItems, subTotal, saleVAT,
                saleTotalWithVAT, timeOfSale,store.getName(), store.getAddress(), 
                payment.getAmount(), payment.getChange(), payment.getType().name());
        }
        else{
            saleLog = new SaleDTO(soldItems, subTotal, saleVAT,
                saleTotalWithVAT, timeOfSale,store.getName(), store.getAddress(), 
                payment.getAmount(), payment.getChange(), payment.getType().name()
                ,discountAmount);
        }
        return saleLog;
    }
    
    private void createReceipt(SaleDTO saleLog, Locale locale){
        receipt = new Receipt(saleLog, locale);
    }
    
    private void notifyRevenueObservers(MonetaryValue saleTotal){
        for (RevenueObserver obs : totalRevenueObservers){
            obs.newSale(saleTotal);
        }
    }
}
