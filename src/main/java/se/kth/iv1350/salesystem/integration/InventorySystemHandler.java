package se.kth.iv1350.salesystem.integration;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import se.kth.iv1350.salesystem.datatypes.ItemID;
import se.kth.iv1350.salesystem.datatypes.MonetaryValue;
import se.kth.iv1350.salesystem.datatypes.VATRate;
import se.kth.iv1350.salesystem.dto.ItemDTO;
import se.kth.iv1350.salesystem.dto.SaleDTO;

/**
 * Handles calls to an external inventory system. This class is not yet
 * implemented and is only a placeholder.
 */
class InventorySystemHandler {

    private BufferedReader bufferedReader;
    private final String DUMMY_ID = "0";
    private String dummyDB = "dummy_invDB.txt";

    /**
     * Creates a new InventorySystemHandler instance.
     */
    InventorySystemHandler() {
    }

    /**
     * Creates a new InventorySystemHandler instance with a specified database
     * file.
     *
     * @param inventoryDB The name or path to the inventory database file
     */
    InventorySystemHandler(String inventoryDB) {
        dummyDB = inventoryDB;
    }

    /**
     * Searches an external inventory database for requested item. The
     * <code>ItemID</code> representing a <code>String</code>.
     *
     * @param itemID The item identifier of the requested item
     * @return If an item is found then an itemDTO with the items details is
     * returned.
     * @throws ItemNotFoundException if the requested item was not found 
     * in the database
     * @throws InventoryDBException if there was an issue connecting to the
     * external inventory system that could not be resolved. 
     */
    ItemDTO getItemData(ItemID itemID) throws ItemNotFoundException {
        ItemDTO foundItem;
        if (itemID.getID().equals("noDB")) {
            throw new InventoryDBException("Unable to connect to external inventory system");
        } else {
            String[] itemArray;
            try {
                itemArray = findItemInBuffer(itemID.toString());
            } catch (FileNotFoundException ex) {
                throw new InventoryDBException("Unable to find DB file");
            }

            if (itemArray == null) {
                throw new ItemNotFoundException(itemID);
            } else {
                foundItem = convertItemAsStringArrayToItemDTO(itemArray);
            }
        }

        return foundItem;
    }

    /**
     * Sends sale data to external inventory system. This is a placeholder
     * method that does not do anything
     *
     * @param saleLog Log of sale
     */
    void updateInventory(SaleDTO saleLog) {

    }

    /**
     * Reads an inventory database from a file
     *
     * @param filename The path or name to the file
     */
    private BufferedReader readDBFromFile(String filename) throws FileNotFoundException {
        FileReader fileReader = new FileReader(filename);

        return bufferedReader = new BufferedReader(fileReader);
    }

    private String[] findItemInBuffer(String id) throws FileNotFoundException {
        bufferedReader = readDBFromFile(dummyDB);

        String line = null;

        try {
            while ((line = bufferedReader.readLine()) != null) {
                String[] item = line.split("###");

                if (item[0].equals(id)) {
                    if (item.length == 4) {
                        return item;
                    }
                }
            }
        } catch (IOException ex) {

        }

        return null;
    }

    private ItemDTO convertItemAsStringArrayToItemDTO(String[] itemArray) {
        ItemID itemID = new ItemID(itemArray[0]);
        MonetaryValue pricePerUnit = new MonetaryValue(itemArray[1]);

        VATRate vatRate;
        switch (itemArray[2]) {
            case "25":
                vatRate = VATRate.TWENTYFIVE;
                break;
            case "12":
                vatRate = VATRate.TWELVE;
                break;
            case "6":
                vatRate = VATRate.SIX;
                break;
            default:
                vatRate = null;
                break;
        }

        String itemDescription = itemArray[3];

        ItemDTO itemDTO = new ItemDTO(itemID, pricePerUnit, vatRate, itemDescription);

        return itemDTO;
    }

}
