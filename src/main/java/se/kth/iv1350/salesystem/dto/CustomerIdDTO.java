package se.kth.iv1350.salesystem.dto;

/**
 * A data transfer object for a unique customer identifier
 */
public class CustomerIdDTO {
    private String id;
    
    /**
     * Creates a new instance representing a unique customer identifier
     * @param id The unique customer identifier
     */
    public CustomerIdDTO(String id){
        this.id = id;
    }
    
    /**
     * @return The unique customer identifier
     */
    public String getID(){
        return id;
    }
}
