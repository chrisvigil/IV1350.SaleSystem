package se.kth.iv1350.salesystem.model;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import se.kth.iv1350.salesystem.datatypes.Address;
import se.kth.iv1350.salesystem.util.ErrorLogger;

/**
 * Container for store name and address.
 */
class Store {

    private static final Store STORE = new Store();
    private final String name;
    private final Address address;
    private final String CONFIG_FILENAME = "salesystem.cfg";
    private final String STORENAMEID = "StoreName";
    private final String ADDRESSID = "StoreAddress";
    private BufferedReader reader;
    private ErrorLogger logger;

    private Store(){
        try{
        logger = new ErrorLogger();
        }catch(IOException ex){
            ex.printStackTrace();
        }
        this.name = readStoreNameFromConfig();
        this.address = readStoreAddressFromConfig();
    }
    
    /**
     * @return The only instance of the store
     */
    static Store getStore() {
        return STORE;
    }

    /**
     * @return The <code>Store</code> name
     */
    String getName() {
        return name;
    }

    /**
     * @return The <code>Store</code> address
     */
    Address getAddress() {
        return new Address(address);
    }

    private String readStoreNameFromConfig() {
        String[] storeName = readLineFromConfig(STORENAMEID);
        if (storeName != null) {
            try{
                return storeName[1];
            }catch(Exception ex){
               try{
                logger.logException(ex);
                }
                catch(IOException ioEx){
                    ioEx.printStackTrace();
                }
            }
        }
        return "";
    }

    private Address readStoreAddressFromConfig() {
        String[] line = readLineFromConfig(ADDRESSID);
        Address address = new Address("", 0, 0, "");
        if (line != null) {
            try {
                Integer.parseInt(line[2]);
                if (line.length == 5) {
                    address = new Address(line[1], Integer.parseInt(line[2]),
                            Integer.parseInt(line[3]), line[4]);
                } else if (line.length == 6) {
                    address = new Address(line[1], Integer.parseInt(line[2]),
                            Integer.parseInt(line[3]), line[4], line[5]);
                }
            } catch (Exception ex) {
                try{
                logger.logException(ex);
                }
                catch(IOException ioEx){
                    ioEx.printStackTrace();
                }
            }
        }

        return address;
    }

    private String[] readLineFromConfig(String key) {
        String currentLine;
        String[] splitLine;
        try {
            reader = new BufferedReader(new FileReader(CONFIG_FILENAME));
            while ((currentLine = reader.readLine()) != null) {
                splitLine = currentLine.split("###");
                if (splitLine[0].equals(key)) {
                    return splitLine;
                }
            }
        } catch (FileNotFoundException ex) {
            try{
                logger.logException(ex);
                }
                catch(IOException ex1){
                    ex1.printStackTrace();
                }
            return null;
        } catch (IOException ex) {
            try{
                logger.logException(ex);
                }
                catch(IOException ioEx){
                    ioEx.printStackTrace();
                }
            return null;
        }
        return null;
    }

}
