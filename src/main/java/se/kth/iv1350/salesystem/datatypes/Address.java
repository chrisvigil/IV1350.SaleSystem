package se.kth.iv1350.salesystem.datatypes;

/**
 * Stores an address
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
    
    /**
     * Constructor creates a new <code>Address</code> from another
     * <code>Address</code> object.
     * @param address The <code>Address</code> to duplicate
     */
    public Address(Address address){
        this.street = address.street;
        this.number = address.number;
        this.postalCode = address.postalCode;
        this.city = address.city;
        this.country = address.country;
    }
    
    /**
     * 
     * @return the <code>Address</code> street
     */
    public String getStreet(){
        return street;
    }
    
    /**
     * 
     * @return the <code>Address</code> street number
     */
    public int getNumber(){
        return number;
    }
    
    /**
     * 
     * @return the <code>Address</code> postal code
     */
    public int getPostalCode(){
        return postalCode;
    }
    
    /**
     * 
     * @return the <code>Address</code> city
     */
    public String getCity(){
        return city;
    }
    
    /**
     * 
     * @return the <code>Address</code> country
     */
    public String getCountry(){
        return country;
    }
    
    private void setAddress(String street, int number, int postalCode, String city, String country){
        this.street = street;
        this.number = number;
        this.postalCode = postalCode;
        this.city = city;
        this.country = country;
    }
    
    /**
     * Creates a comma separated <code>String</code> from <code>Address</code>
     * @return <code>Address</code> as <code>String</code>
     */
    @Override
    public String toString(){
        String addressAsString = street + ", " + number + ", " + postalCode + ", " + city;
        if (country != null)
            addressAsString = addressAsString + ", " + country;
        
        return addressAsString;
    }
    
    
}
