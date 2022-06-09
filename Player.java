import java.util.Stack;
import java.util.*;
/**
 * Class Player - represents the player of the game. The player class 
 * stores a reference of the player's Name, items, weight and locations.
 * 
 *@author Sayaka Bhandari
 *@version 2021.12.01
 */
public class Player
{
    private String playerName;    // instance variable to hold player's name
    private Room currentRoom;      // Holds player's current room
    private int maximumWeight;
    private Stack<Room> roomStack; //store player visted room
    private Items itemPlayerHolds;       // holds the items picked up by player

    /**
     * Defines and parameterise the Player class constructor.
     * @param  Player Name - the Name of the player
     */
    public Player()
    {
        playerName = "";
        maximumWeight = 200;
        roomStack = new Stack<Room>();
        itemPlayerHolds = null;
    }

    /**
     * To set the player's name
     */
     public void setPlayerName (String uName)
    {
        this.playerName = uName;
    }

    /**
     * Get the player's name
     */
    public String getPlayerName()
    {
        return this.playerName;
    }

    /**
     * Set the current location of the player
     */
    public void setCurrentRoom(Room currRoom)
    {
        this.currentRoom = currRoom;
    }

    /**
     * Get the current room
     */
    public Room getCurrentRoom()
    {
        return this.currentRoom;
    }

    /**
     * @return Maximum weight
     */
    public int getMaximumWeight ()
    {
        return maximumWeight;
    }

    /**
     * Set items in the player's hand
     */
    public void setItemPlayerHolds(Items itempicked)
    {
        itemPlayerHolds = itempicked;
    }

    /**
     * Get the items in the player's hand
     */
    public Items getItemPlayerHolds()
    {
        return itemPlayerHolds;
    }

    /**
     * @return the name of the item carried by player
     */
    public String getitemPlayerHolds()
    {
        return itemPlayerHolds.getItemName();
    }

    /**
     * @return the description of the player
     */
    public String getPlayerDescription()

    {
        String result = "\nPlayer " + playerName + "\n";
        if (itemPlayerHolds != null)

        {
            result += "You are carrying"+" " +itemPlayerHolds.getItemName()+" " +" item in hand. \n\n";
        }
        result += currentRoom.getLongDescription();
        return result;
    }

    public Room getPlayersExit (String direction)
    {
        return currentRoom.getExit (direction);
    }

    /**
     * Players Entering the room
     */
    public void setPlayersEnteringRoom (Room nextRoom)
    {
        roomStack.push(currentRoom);
        currentRoom = nextRoom;
    }

    /**
     * Moving player to the previous room
     */
    public void toPreviousRoom()
    {
        if (roomStack.empty())
        {
            System.out.println ("Sorry, you are already outside the Castle" + "you cannot go back any futher");
        }
        else
        {
            currentRoom = roomStack.pop();
            System.out.println("Player " + getPlayerName());

            if (itemPlayerHolds != null)
            {
                System.out.println("You are currently carrying" +" " + itemPlayerHolds.getItemName() + " "+"in your hand. \n");
            }
            System.out.println(currentRoom.getLongDescription());
        }
    }

    public boolean canBePickedUp (String itemName)
    {
        // Get the items object
        Items item = currentRoom.getItem(itemName);
        if (item == null)           //check to see if the item is null
        {
            return false;       // If condition is satisfied then return false
        }
        // If the condition is not satisfied then have another condition.
        if (item.getWeight() < maximumWeight && !alreadyItemExistsInHand())
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public boolean canBeDropped (String itemName)
    {
        Items item = currentRoom.getItem(itemName);
        if (item != null)           //check to see if the item is null
        {
            return false;       // If condition is satisfied then return false
        }
        // If the condition is not satisfied then have another condition.
        if (alreadyItemExistsInHand())
        {
            return true;
        }
        else
        {
            return false;
        }

    }

    /**
     * Check to see if player holds any item in their's hand or not.
     */
    public boolean alreadyItemExistsInHand()
    {
        if (itemPlayerHolds != null)
        {
            return true;
        }
        else
        {
            return false;
        }
     }

    /**
     * This method allows players to pick up an item in the room
     */
    public void pickUpItem ( String itemName)
    {
        if (canBePickedUp(itemName))
        {
            Items item = currentRoom.getItem(itemName);
            setItemPlayerHolds(item);     
            currentRoom.removeItem (item);      //remove item from the room
            System.out.println ("Player picked the item successfully");
        }
        else
        {
            if (alreadyItemExistsInHand())
            {
                System.out.println ("You can only carry one item at a time");
            }
            else
            {
                System.out.println ("The" +" "+ itemName +" "+"is not found in the room or the item is too heavy for you");
                System.out.println ("**Remember you can only pick up an item upto 200 weight present in the room**");
            }
            return;
        }
    }

    public void dropItem(String itemName)
    {
        if (canBeDropped(itemName))
        {
            currentRoom.addItem(itemPlayerHolds);
            itemPlayerHolds = null;
            System.out.println ("The player successfully dropped the item");
        }
        else
        {
            if (alreadyItemExistsInHand())
            {
                System.out.println ("You don't have the item with you");
            }
            else
            {
                System.out.println ("Sorry there is no item to drop");
            }
            return;
        }
    }

}
