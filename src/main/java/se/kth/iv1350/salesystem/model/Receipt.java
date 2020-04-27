/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.kth.iv1350.salesystem.model;

import java.util.LinkedList;
import java.util.List;
import se.kth.iv1350.salesystem.datatypes.Address;
import se.kth.iv1350.salesystem.dto.SaleDTO;
import se.kth.iv1350.salesystem.dto.SoldItemDTO;
import se.kth.iv1350.salesystem.integration.Printer;

/**
 * Contains all data needed to print a receipt.
 */
class Receipt {
    private final String timeOfSale;
    private final String storeName;
    private String storeAddressLineOne;
    private String storeAddressLineTwo;
    private String storeAddressLineThree;
    private final List<String> items;
    private final String subTotal;
    private final String saleVAT;
    private final String saleTotal;
    private final String payment;
    private final String change;
    
    /**
     * Creates a new instance of a receipt.
     * @param saleDTO The log of completed sale.
     */
    Receipt(SaleDTO saleDTO){
        timeOfSale = saleDTO.getTimeOfSale().toString();
        storeName = saleDTO.getStoreName();
        setAddress(saleDTO.getStoreAddess());
        items = new LinkedList<>();
        addItems(saleDTO.getItems());
        subTotal = saleDTO.getSaleSubTotal().toString();
        saleVAT = saleDTO.getSaleVAT().toString();
        saleTotal = saleDTO.getSaleTotal().toString();
        payment = saleDTO.getPayment().toString();
        change = saleDTO.getChange().toString();
    }
    
    /**
     * Sends the receipt as a formed <code>String</code> to a printer.
     * @param printer The printer to print to
     */
    void print(Printer printer){
        printer.print(createStringForPrinting());
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
    
    private void addItems(List<SoldItemDTO> itemsInDTO){
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
    
    private String createStringForPrinting(){
        StringBuilder sb = new StringBuilder();
        sb.append("RECIEPT\n");
        sb.append(storeName).append("\n");
        sb.append(storeAddressLineOne).append("\n");
        sb.append(storeAddressLineTwo).append("\n");
        if (storeAddressLineThree != null)
            sb.append(storeAddressLineThree).append("\n");
        sb.append("Sale Date: ").append(timeOfSale).append("\n");
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
