package store;

/**
 Assignment #13
 An Item has an ID number and keeps track of how many Items are left in
 inventory.
 <p/>
 The natural ordering for Items compares by ascending ID number and does not
 take into account inventory.
 <p/>
 @author Joey Bloom
 */
public class Item implements Comparable<Item>
{
    private int myId;
    private int myInv;

    /**
     Constructs an Item with an id number and an inventory
     <p/>
     @param id  the unique ID number of this Item
     @param inv the number of this Item in inventory
     */
    public Item(int id, int inv)
    {
        myId = id;
        myInv = inv;
    }

    /**
     Returns this Item's ID number
     <p/>
     @return ID number
     */
    public int getId()
    {
        return myId;
    }

    /**
     Returns the number of this Item in inventory
     <p/>
     @return inventory
     */
    public int getInv()
    {
        return myInv;
    }

    @Override
    public int compareTo(Item other)
    {
        return getId() - other.getId();
    }

    @Override
    public boolean equals(Object otherObject)
    {
        if(otherObject instanceof Item)
            return compareTo((Item) otherObject) == 0;
        return false;
    }

    @Override
    public int hashCode()
    {
        int hash = 5;
        hash = 37 * hash + this.myId;
        hash = 37 * hash + this.myInv;
        return hash;
    }

    @Override
    public String toString()
    {
        return "ID:  " + getId() + "\nInv: " + getInv();

    }
}
