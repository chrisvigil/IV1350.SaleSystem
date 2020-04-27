/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.kth.iv1350.salesystem.model;

import java.time.LocalTime;
import java.util.List;
import se.kth.iv1350.salesystem.datatypes.MonetaryValue;
import se.kth.iv1350.salesystem.datatypes.Quantity;
import se.kth.iv1350.salesystem.datatypes.VATRate;
import se.kth.iv1350.salesystem.dto.ItemDTO;
import se.kth.iv1350.salesystem.dto.SaleDTO;
import se.kth.iv1350.salesystem.dto.SoldItemDTO;
import se.kth.iv1350.salesystem.integration.Printer;

/**
 * Represents a compleat sale
 * 
 * @author christopher.vigil
 */
public class Sale {
    private final Store store;
    private final Basket basket;
    private Receipt receipt;
    private final MonetaryValue subTotal;
    private final MonetaryValue saleVAT;
    private LocalTime timeOfSale;
    private MonetaryValue payment;
    private MonetaryValue change;
    
    /**
     * Creates a new store object
     * @param store The store at which the sale takes place 
     */
    public Sale(Store store){
        this.store = store;
        this.basket = new Basket();
        subTotal = new MonetaryValue();
        saleVAT = new MonetaryValue();
    }
    
    
    /**
     * Adds an item to the sale basket
     * @param itemDTO The item to add.
     * @param quantity The item's quantity.
     * @return subTotal The current running subtotal of the sale.
     */
    public MonetaryValue addItemToBasket(ItemDTO itemDTO, Quantity quantity){
        basket.addItem(itemDTO, quantity);
        MonetaryValue itemTotal = calculateItemTotal(itemDTO, quantity);
        subTotal.add(itemTotal);
        addItemsVATtoSaleVAT(itemTotal, itemDTO.getVATRate());
        
        MonetaryValue saleTotalwithVAT = calculateSaleTotalWithVAT();
        
        return saleTotalwithVAT;
    }
    
    private void addItemsVATtoSaleVAT(MonetaryValue itemTotal , VATRate vatRate){
        saleVAT.add(itemTotal.calculateVAT(vatRate));
    }
    
    private MonetaryValue calculateItemTotal(ItemDTO itemDTO, Quantity quantity){
        MonetaryValue itemTotal = itemDTO.getPricePerUnit().multiplíedByQuantity(quantity);
        return itemTotal;
    }
    
    private MonetaryValue calculateSaleTotalWithVAT(){
        MonetaryValue saleTotalwithVAT = new MonetaryValue(subTotal);
        saleTotalwithVAT.add(saleVAT);
        
        return saleTotalwithVAT;
    }
    
    public MonetaryValue makeCashPayment(MonetaryValue payment){
        MonetaryValue saleTotalWithVAT = calculateSaleTotalWithVAT();
        this.change = saleTotalWithVAT.difference(payment);
        
        this.payment = payment;
        
        return change;
    }
    
    private void logTimeOfSale(){
        timeOfSale = LocalTime.now();
    }
    
    public SaleDTO endSale(){
        logTimeOfSale();
        SaleDTO saleLog = generateSaleLog();
        createReceipt(saleLog);
        
        return saleLog;
    }
    
    private SaleDTO generateSaleLog(){
        List<SoldItemDTO> soldItems = basket.getSoldItems();
        MonetaryValue saleTotalWithVAT = calculateSaleTotalWithVAT();
        
        SaleDTO saleLog = new SaleDTO(soldItems, subTotal, saleVAT,
                saleTotalWithVAT, timeOfSale,store.getName(), store.getAddress(), 
                payment, change);
        
        return saleLog;
    }
    
    private void createReceipt(SaleDTO saleLog){
        receipt = new Receipt(saleLog);
    }
    
    public void printRepeict(Printer printer){
        receipt.print(printer);
    }
}
