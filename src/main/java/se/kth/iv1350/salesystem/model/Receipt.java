/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.kth.iv1350.salesystem.model;

import java.time.LocalTime;
import java.util.LinkedList;
import java.util.List;
import se.kth.iv1350.salesystem.datatypes.Address;
import se.kth.iv1350.salesystem.datatypes.MonetaryValue;
import se.kth.iv1350.salesystem.dto.SoldItemDTO;
import se.kth.iv1350.salesystem.integration.Printer;

/**
 *
 * @author christopher.vigil
 */
class Receipt {
    private String timeOfSale;
    private final String storeName;
    private String storeAddressLineOne;
    private String storeAddressLineTwo;
    private String storeAddressLineThree;
    private final List<String> items;
    private String subTotal;
    private String saleTotal;
    private String saleVAT;
    private String payment;
    private String change;
    
    Receipt(Store store){
        storeName = store.getName();
        setAddress(store.getAddress());
        items = new LinkedList<>();
    }
    
    private void setAddress(Address address){
        StringBuilder sb = new StringBuilder();
        sb.append(address.getStreet()).append(" ");
        sb.append(address.getNumber());
        storeAddressLineOne = sb.toString();
        
        sb = new StringBuilder();
        sb.append(address.getPostalCode()).append(", ");
        sb.append(address.getCity());
        storeAddressLineTwo = sb.toString();
        
        storeAddressLineThree = address.getCountry();
    }
    
    void setTimeOfSale(LocalTime timeOfSale){
        this.timeOfSale = timeOfSale.toString();
    }
    
    void setSubTotal(MonetaryValue subTotal){
        this.subTotal = subTotal.toString();
    }
    
    void setSaleTotal(MonetaryValue saleTotal){
        this.saleTotal = saleTotal.toString();
    }
    
    void setSaleVAT(MonetaryValue saleVAT){
        this.saleVAT = saleVAT.toString();
    }
    
    void setPayment(MonetaryValue payment){
        this.payment = payment.toString();
    }
    
    void setChange(MonetaryValue change){
        this.change = change.toString();
    }
    
    void addItems(List<SoldItemDTO> itemsInDTO){
        for(SoldItemDTO item : itemsInDTO){
            this.items.add(itemAsString(item));
        }
    }
    private String itemAsString(SoldItemDTO item){
        StringBuilder sb = new StringBuilder();
        sb.append(item.getQuantity()).append(" - ");
        sb.append(item.getItemDescription()).append(" - ");
        sb.append(item.getPricePerUnit()).append(" each");
        return sb.toString();
    }
    
    void print(Printer printer){
        printer.print(createStringForPrinting());
    }
    
    private String createStringForPrinting(){
        StringBuilder sb = new StringBuilder();
        sb.append("RECIEPT\n");
        sb.append(storeName).append("\n");
        sb.append(storeAddressLineOne).append("\n");
        sb.append(storeAddressLineTwo).append("\n");
        if (storeAddressLineThree != null)
            sb.append(storeAddressLineThree).append("\n");
        sb.append("\n\n");
        for(String item : items)
            sb.append(item).append("\n");
        sb.append("\nSubtotal: ").append(subTotal).append("\n");
        sb.append("VAT: ").append(saleVAT).append("\n\n");
        sb.append("\nTotal: ").append(saleTotal).append("\n");
        sb.append("Payed: ").append(payment).append("\n");
        sb.append("Change: ").append(change);
        
        return sb.toString();
    }
    
}