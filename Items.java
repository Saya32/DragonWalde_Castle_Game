
/**
 * Items - items are stored in a different location in the game.
 *
 * This item is part of the "Dragonwalde Castle" application. 
 * "Dragonwalde Castle" is a very simple, text based adventure game.  
 *
 * A "Items" represents an objects in the scenery of the game.
 * Every room contains differnt items which will aid you to complete this game.
 *
 * @author Sayaka Bhandari
 * @version 2021.11.23
 */
public class Items
{
    private String name;                   // Name of an items
    private String itemDescription;     // The decription of an item in a room.
    private int itemWeight;                 // The weight of an item.

    /**
     * Create an item described "description". Initially, it has
     * no description. "description" is something like "a gun" or
     * "a sword".
     * @param description The item's description.
     * @param weight The item's weight.
     */
    public Items(String name,String description, int weight)
    {
        this.name = name;
        itemDescription = description;
        itemWeight = weight; 
    }

    /**
     * @return description of item
     */
    public String getItemDescription()
    {
        String itemString = "Name:"+ this.name;
        itemString += "\n\tDescription:";
        itemString += this.itemDescription + "\n";
        itemString += "\tWeight:" +this.itemWeight +"\n";
        return itemString;
    }
    
    /**
     * Get the name of the item
     */
    public String getItemName()
    {
        return name;
    }
    
    /**
     * Get the description of an item
     */
    public String getItemsDescriptions()
    {
        return itemDescription;
    }
    
    /**
     * Get the weight of an item
     */
    public int getWeight ()
    {
        return itemWeight;
    }
}