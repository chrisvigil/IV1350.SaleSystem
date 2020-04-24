/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.kth.iv1350.salesystem.datatypes;

import java.util.Objects;

/**
 * Represents a unique itemID
 * 
 * @author christopher.vigil
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

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + Objects.hashCode(this.id);
        return hash;
    }
}
