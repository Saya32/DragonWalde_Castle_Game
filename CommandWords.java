/**
 * This class is part of the "Dragonwalde Castle" application. 
 * "Dragonwalde Castle" is a very simple, text based adventure game.
 * 
 * This class holds an enumeration of all command words known to the game.
 * It is used to recognise commands as they are typed in.
 *
 * @author  Michael Kölling, David J. Barnes and Sayaka Bhandari
 * @version 2016.02.29
 */

public class CommandWords
{
    // a constant array that holds all valid command words
    private static final String[] validCommands = {
        "go", "quit", "help", "look", "eat", "back", "pick", "drop", "sit", "nap", "show"
        
    };
    
    /**
     * Returns all valid commands in the string format
     */
    public String getCommandList()
    {
        String returnString= "";
        for(String command: validCommands) {
            returnString = returnString + command + "   ";
        }
        return returnString;
    }

    
    /**
     * Constructor - initialise the command words.
     */
    public CommandWords()
    {
        // nothing to do at the moment...
    }

    /**
     * Check whether a given String is a valid command word. 
     * @return true if it is, false if it isn't.
     */
    public boolean isCommand(String aString)
    {
        for(int i = 0; i < validCommands.length; i++) {
            if(validCommands[i].equals(aString))
                return true;
        }
        // if we get here, the string was not found in the commands
        return false;
    }


}
