package se.kth.iv1350.salesystem.dto;

import java.time.LocalDateTime;
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
    private final LocalDateTime timeOfSale;
    private final String storeName;
    private final Address storeAddress;
    private final MonetaryValue payment;
    private final MonetaryValue change;
    private final String paymentType;
    private final MonetaryValue discountAmount;
    
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
     * @param paymentType The type of payment
     */
    public SaleDTO(List<SoldItemDTO> items, MonetaryValue subTotal, MonetaryValue saleVAT,
            MonetaryValue saleTotal, LocalDateTime timeOfSale, String storeName, Address storeAddress,
            MonetaryValue payment, MonetaryValue change, String paymentType){
        this.items = new LinkedList<>(items);
        this.subTotal = subTotal;
        this.saleVAT = saleVAT;
        this.saleTotal = saleTotal;
        this.timeOfSale = timeOfSale;
        this.storeName = storeName;
        this.storeAddress = new Address(storeAddress);
        this.payment = payment;
        this.change = change;
        this.paymentType = paymentType;
        this.discountAmount = new MonetaryValue();
    }
    
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
     * @param paymentType The type of payment
     * @param discountAmount The discount applied
     */
    public SaleDTO(List<SoldItemDTO> items, MonetaryValue subTotal, MonetaryValue saleVAT,
            MonetaryValue saleTotal, LocalDateTime timeOfSale, String storeName, Address storeAddress,
            MonetaryValue payment, MonetaryValue change, String paymentType, MonetaryValue discountAmount){
        this.items = new LinkedList<>(items);
        this.subTotal = subTotal;
        this.saleVAT = saleVAT;
        this.saleTotal = saleTotal;
        this.timeOfSale = timeOfSale;
        this.storeName = storeName;
        this.storeAddress = new Address(storeAddress);
        this.payment = payment;
        this.change = change;
        this.paymentType = paymentType;
        this.discountAmount = discountAmount;
    }
    
    /**
     * @return The list of sold items in the <code>SaleDTO</code>
     */
    public List<SoldItemDTO>getItems(){
        return new LinkedList<>(items);
    }
    
    /**
     * @return The <code>SaleDTO</code> sale subtotal
     */
    public MonetaryValue getSaleSubTotal(){
        return subTotal;
    } 
    
     /**
     * @return The <code>SaleDTO</code> sale VAT
     */
    public MonetaryValue getSaleVAT(){
        return saleVAT;
    }
    
     /**
     * @return The <code>SaleDTO</code> sale sale total with vat
     */
    public MonetaryValue getSaleTotal(){
        return saleTotal;
    }
    
     /**
     * @return The <code>SaleDTO</code> time of sale
     */
    public LocalDateTime getTimeOfSale(){
        return timeOfSale;
    }
    
     /**
     * @return The <code>SaleDTO</code> store name
     */
    public String getStoreName(){
        return storeName;
    }
    
     /**
     * @return The <code>SaleDTO</code> store address
     */
    public Address getStoreAddess(){
        return new Address(storeAddress);
    }
    
     /**
     * @return The <code>SaleDTO</code> payment value
     */
    public MonetaryValue getPayment(){
        return payment;
    }
    
     /**
     * @return The <code>SaleDTO</code> sale change
     */
    public MonetaryValue getChange(){
        return change;
    }
    
     /**
     * @return The <code>SaleDTO</code> payment type
     */
    public String getPaymentType(){
        return paymentType;
    }
    
    /**
     * @return The <code>SaleDTO</code>  discounted amount
     */
    public MonetaryValue getDiscountAmount(){
        return discountAmount;
    }
}
