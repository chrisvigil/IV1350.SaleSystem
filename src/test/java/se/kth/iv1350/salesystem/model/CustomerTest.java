package se.kth.iv1350.salesystem.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import se.kth.iv1350.salesystem.dto.CustomerIdDTO;

public class CustomerTest {
    private Customer instance;
    private final CustomerIdDTO ID_DTO = new CustomerIdDTO("1");
    
    @BeforeEach
    public void setUp() {
        instance = new Customer(ID_DTO);
    }
    
    @AfterEach
    public void tearDown() {
        instance = null;
    }
    
    /*
    @Test
    public void testCalculateDiscount() {
    }

    @Test
    public void testGetID() {
    } */
    
}
