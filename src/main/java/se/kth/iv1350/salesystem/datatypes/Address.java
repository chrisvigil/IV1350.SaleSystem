/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.kth.iv1350.salesystem.datatypes;

/**
 * Stores and address
 *
 * @author christopher.vigil
 */
public class Address {
    private String street;
    private int number;
    private int postalCode;
    private String city;
    private String country;
    
    /**
     * Constructor with country. 
     * @param street String with street name
     * @param number Integer with street number
     * @param postalCode Integer with postal code
     * @param city String with city name
     * @param country String with country name
     */
    public Address(String street, int number, int postalCode, String city, String country){
        setAddress(street, number, postalCode, city, country);
    }
    
    /**
     * Constructor without country. 
     * @param street String with street name
     * @param number Integer with street number
     * @param postalCode Integer with postal code
     * @param city String with city name      
     */
    public Address(String street, int number, int postalCode, String city){
        String nullCountry = null;
        setAddress(street, number, postalCode, city, nullCountry);
    }
    
    public Address(Address address){
        this.street = address.street;
        this.number = address.number;
        this.postalCode = address.postalCode;
        this.city = address.city;
        this.country = address.country;
    }
    
    private void setAddress(String street, int number, int postalCode, String city, String country){
        this.street = street;
        this.number = number;
        this.postalCode = postalCode;
        this.city = city;
        this.country = country;
    }
    
    @Override
    public String toString(){
        String addressAsString = street + ", " + number + ", " + postalCode + ", " + city;
        if (country != null)
            addressAsString = addressAsString + ", " + country;
        
        return addressAsString;
    }
    
    
}
