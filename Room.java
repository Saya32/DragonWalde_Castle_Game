import java.util.Set;
import java.util.HashMap;
import java.util.ArrayList;

/**
 * Class Room - a room in an adventure game.
 *
 * This class is part of the "Dragonwalde Castle" application. 
 * "Dragonwalde Castle" is a very simple, text based adventure game.  
 *
 * A "Room" represents one location in the scenery of the game.  It is 
 * connected to other rooms via exits.  For each existing exit, the room 
 * stores a reference to the neighboring room.
 * 
 * @author  Michael Kölling, David J. Barnes and Sayaka Bhandari
 * @version 2016.02.29
 */

public class Room 
{
    private String description;
    private HashMap<String, Room> exits;        // stores exits of this room.
    private ArrayList<Items>roomsItems;         // Array List of items in the room

    /**
     * Create a room described "description". Initially, it has
     * no exits. "description" is something like "a kitchen" or
     * "an open court yard".
     * @param description The room's description.
     */
    public Room(String description) 
    {
        this.description = description;
        roomsItems = new ArrayList<Items>();
        exits = new HashMap<>();
    }
    
    /**
     * Get the Items present in the room from the list
     */
    public ArrayList<Items> getRoomItems()
    {
        return roomsItems;
    }
    
    /**
     * Define an exit from this room.
     * @param direction The direction of the exit.
     * @param neighbor  The room to which the exit leads.
     */
    public void setExit(String direction, Room neighbor) 
    {
        exits.put(direction, neighbor);
    }

    /**
     * @return The short description of the room
     * (the one that was defined in the constructor).
     */
    public String getShortDescription()
    {
        return description;
    }

    /**
     * Return a description of the room in the form:
     *     You are in the kitchen.
     *     Exits: north west
     * @return A long description of this room
     */
    public String getLongDescription()
    {
        return "" + description + ".\n" +getRoomsItems() + "\n" + getExitString();
    }
    
    /**
     * Return a description of the items present in the room.
     * @return items description in the room
     */
    public String getRoomsItems()
    {
        String returnItems = "Items present in the room are: \n";
        for (Items item : roomsItems)
        {
            returnItems+= item.getItemDescription() + "\n";
        }
        return returnItems;
    }

    /**
     * Adds item to the room
     */
    public void addItem(Items item)
    {
        roomsItems.add(item);
    }
    
     /**
     * Return a string describing the room's exits, for example
     * "Exits: north west".
     * @return Details of the room's exits.
     */
    private String getExitString()
    {
        String returnString = "Exits:";
        Set<String> keys = exits.keySet();
        for(String exit : keys) {
            returnString += " " + exit;
        }
        return returnString;
    }

    /**
     * Return the room that is reached if we go from this room in direction
     * "direction". If there is no room in that direction, return null.
     * @param direction The exit's direction.
     * @return The room in the given direction.
     */
    public Room getExit(String direction) 
    {
        return exits.get(direction);
    }
    
    /**
     * Get Item object if the item is identified in the list of items present in the Room
     */
    public Items getItem (String itemName)
    {
        // loop - list of items present in the room
        for (int i = 0; i < roomsItems.size(); i++)
        {
            // check to see if the name of an item matches with any on the list
            if (roomsItems.get(i).getItemName().equalsIgnoreCase(itemName))
            {
                // if the name matches then display at index i
                return roomsItems.get(i);
            }
        }
        return null;
    }
    
    /**
     * Remove an item from the list of which the user has picked up
     */
    public void removeItem(Items items)
    {
        // loop - list of items present in the room
        for (int i = 0; i < roomsItems.size(); i++)
        {
            // check to see if the item matches with any on the list 
            if (roomsItems.get (i) == items)
            {
                // if it matches remove it from the List
                roomsItems.remove(items);
                break;
            }
        }
    }
}

