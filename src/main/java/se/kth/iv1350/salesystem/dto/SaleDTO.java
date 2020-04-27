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
 *
 * @author christopher.vigil
 */
public class SaleDTO {
    private final List<SoldItemDTO> items;
    private final MonetaryValue saleTotal;
    private final MonetaryValue saleVAT;
    private final LocalTime timeOfSale;
    private final String storeName;
    private final Address storeAddress;
    
    public SaleDTO(List<SoldItemDTO> items, MonetaryValue saleTotal, MonetaryValue saleVAT,
            LocalTime timeOfSale, String storeName, Address storeAddress){
        this.items = new LinkedList<>(items);
        this.saleTotal = new MonetaryValue(saleTotal);
        this.saleVAT = new MonetaryValue(saleVAT);
        this.timeOfSale = timeOfSale;
        this.storeName = storeName;
        this.storeAddress = new Address(storeAddress);
    }
    
    public List<SoldItemDTO>getItems(){
        return new LinkedList<>(items);
    }
    
    public MonetaryValue getSaleTotal(){
        return new MonetaryValue(saleTotal);
    } 
    
    public MonetaryValue getSaleVAT(){
        return new MonetaryValue(saleVAT);
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
}