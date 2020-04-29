package se.kth.iv1350.salesystem.datatypes;

import java.util.Objects;

/**
 * Represents a unique itemID, the itemID is immutable.
 */
public class ItemID {
    private final String id;
    
    public ItemID(int id){
        this.id = Integer.toString(id);
    }
    
    public ItemID(String id){
        this.id = id;
    }
    
    public String getID(){
        return id;
    }
    
    @Override
    public String toString(){
        return id;
    }
    
     /**
     * Compares a  <code>itemID</code> to another object to determine
     * if they are equal.
     * @param obj The object with which to compare.
     * @return <code>true</code> if equal, otherwise <code>false</code>.
     */
    @Override
    public boolean equals(Object obj){
        boolean isEqual = false;
        if (this.getClass() == obj.getClass())
        {
            ItemID itemID = (ItemID) obj;
            if (itemID.id.equals(this.id))
                isEqual = true;
        }
        
        return isEqual;
    }
}
