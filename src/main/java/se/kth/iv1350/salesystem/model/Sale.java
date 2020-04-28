/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.kth.iv1350.salesystem.model;

import java.time.LocalDateTime;
import java.util.List;
import se.kth.iv1350.salesystem.datatypes.MonetaryValue;
import se.kth.iv1350.salesystem.datatypes.Quantity;
import se.kth.iv1350.salesystem.datatypes.VATRate;
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
    private Payment payment;
    private Receipt receipt;
    private LocalDateTime timeOfSale;
    
    /**
     * Creates a new instance representing one sale.
     * @param store The store at which the sale takes place 
     */
    public Sale(Store store){
        this.store = store;
        this.basket = new Basket();
        this.subTotal = new MonetaryValue();
        this.saleVAT = new MonetaryValue();
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
     * Makes a payment of the sale with cash amount
     * @param payment The amount payed
     * @return payment The amount to return in change
     */
    public MonetaryValue makeCashPayment(MonetaryValue payment){
        MonetaryValue saleTotalWithVAT = calculateSaleTotalWithVAT();
        try{
            payment.subtract(saleTotalWithVAT);
        }
        catch(IllegalArgumentException ex){
            throw new IllegalArgumentException("Payment value insufficient");
        }
        this.payment = new Payment(payment,CASH);
        
        MonetaryValue change = this.payment.calculateChange(saleTotalWithVAT);
        
        return change;
    }
    
    /**
     * Ends the current sale and returns a sale log.
     * 
     * @return The sale log
     */
    public SaleDTO endSale(){
        logTimeOfSale();
        SaleDTO saleLog = generateSaleLog();
        createReceipt(saleLog);
        
        return saleLog;
    }
    
    /**
     * Prints a receipt
     * @param printer The printer where the receipt is sent.
     */
    public void printRepeict(Printer printer){
        receipt.print(printer);
    }
    
    private void addItemsVATtoSaleVAT(MonetaryValue itemTotal , VATRate vatRate){
        saleVAT = saleVAT.add(itemTotal.calculateVAT(vatRate));
    }
    
    private MonetaryValue calculateItemTotal(ItemDTO itemDTO, Quantity quantity){
        MonetaryValue itemTotal = itemDTO.getPricePerUnit().multiplíedByQuantity(quantity);
        return itemTotal;
    }
    
    private MonetaryValue calculateSaleTotalWithVAT(){
        MonetaryValue saleTotalwithVAT = subTotal.add(saleVAT);
        
        return saleTotalwithVAT;
    }
    
    private void logTimeOfSale(){
        timeOfSale = LocalDateTime.now();
    }
    
    private SaleDTO generateSaleLog(){
        List<SoldItemDTO> soldItems = basket.getSoldItems();
        MonetaryValue saleTotalWithVAT = calculateSaleTotalWithVAT();
        
        SaleDTO saleLog = new SaleDTO(soldItems, subTotal, saleVAT,
                saleTotalWithVAT, timeOfSale,store.getName(), store.getAddress(), 
                payment.getAmmount(), payment.getChange(), payment.getType().name());
        
        return saleLog;
    }
    
    private void createReceipt(SaleDTO saleLog){
        receipt = new Receipt(saleLog);
    }
}
