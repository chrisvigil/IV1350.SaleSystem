/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.kth.iv1350.salesystem.dto;

import java.time.LocalTime;
import java.util.LinkedList;
import java.util.List;
import se.kth.iv1350.salesystem.datatypes.Address;
import se.kth.iv1350.salesystem.datatypes.MonetaryValue;

/**
 * Contains information about a single sale.
 */
public class SaleDTO {
    private final List<SoldItemDTO> items;
    private final MonetaryValue subTotal;
    private final MonetaryValue saleVAT;
    private final MonetaryValue saleTotal;
    private final LocalTime timeOfSale;
    private final String storeName;
    private final Address storeAddress;
    private final MonetaryValue payment;
    private final MonetaryValue change;
    
    /**
     * Creates a new instance representing a particular sale.
     * @param items A list of sold items.
     * @param subTotal The total cost of the sale excluding VAT.
     * @param saleVAT The total VAT of the sale.
     * @param saleTotal The sale total including VAT.
     * @param timeOfSale The time the sale was completed.
     * @param storeName The name of the store where the sale occurred.
     * @param storeAddress The address of the store where the sale occurred.
     * @param payment The amount payed
     * @param change The change customer received
     */
    public SaleDTO(List<SoldItemDTO> items, MonetaryValue subTotal, MonetaryValue saleVAT,
            MonetaryValue saleTotal, LocalTime timeOfSale, String storeName, Address storeAddress,
            MonetaryValue payment, MonetaryValue change){
        this.items = new LinkedList<>(items);
        this.subTotal = subTotal;
        this.saleVAT = saleVAT;
        this.saleTotal = saleTotal;
        this.timeOfSale = timeOfSale;
        this.storeName = storeName;
        this.storeAddress = new Address(storeAddress);
        this.payment = payment;
        this.change = change;
    }
    
    public List<SoldItemDTO>getItems(){
        return new LinkedList<>(items);
    }
    
    public MonetaryValue getSaleSubTotal(){
        return subTotal;
    } 
    
    public MonetaryValue getSaleVAT(){
        return saleVAT;
    }
    
    public MonetaryValue getSaleTotal(){
        return saleTotal;
    }
    
    public LocalTime getTimeOfSale(){
        return timeOfSale;
    }
    
    public String getStoreName(){
        return storeName;
    }
    
    public Address getStoreAddess(){
        return new Address(storeAddress);
    }
    
    public MonetaryValue getPayment(){
        return payment;
    }
    
    public MonetaryValue getChange(){
        return change;
    }
}
