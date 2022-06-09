import java.util.Stack;
import java.util.Scanner;
/**
 *  This class is the main class of the "Dragonwalde Castle" application. 
 * 
 *  To play this game, create an instance of this class and call the "play"
 *  method.
 * 
 *  This main class creates and initialises all the others: it creates all
 *  rooms, items, player, creates the parser and starts the game.  It also evaluates and
 *  executes the commands that the parser returns.
 * 
 * @author  Michael KÃ¶lling, David J. Barnes and Sayaka Bhandari
 * @version 2016.02.29
 */

public class Game 
{
    private Parser parser;
    String directionsList[] = {"north", "east", "south", "west", "southeast", "southwest", "northwest"};
    Room outside, Hall, Kitchen, Gatehouse, basement, Solar, Pantry, Boudoir, bedroom; 
    private Scanner reader;
    private Player player;
    private Room currentRoom;
    private int counter = 0;
    /**
     * Create the game and initialise its internal map.
     */
    public Game() 
    {
        parser = new Parser();
        reader = new Scanner(System.in);
        player = new Player();
    }

    /**
     * Create all the rooms and link their exits and items together.
     */
    private void createRooms()
    {
        MagicRoom transporter;
        RoomRandomiser roomRandomiser;

        // Create Item objects

        Items KitchenItems[] = {new Items ("sponge_cake"," There are too many, feel free to use 'eat' command to eat", 30),
                new Items ("pancake", "glazed with sugar syrup", 150),
                new Items ("chocolate","in the corner of the room", 30)};

        Items PantryItems[] = {new Items("Crisp_and_drink", "Wow the cupboard full of snacks", 100),
                new Items ("Rice","feel free to eat if you eat hungry", 24),
                new Items ("canned_soup","feel free to eat if you are hungry", 50)};

        Items GatehouseItems [] = {new Items("Sword","just for decoration", 250),
                new Items ("Towels and basic toiletries","to keep yourself hygine", 400),
                new Items ("bed", "feel free to take a short nap" , 600)};

        Items HallItems [] = {new Items("gun","inside an alarm glass", 250),
                new Items ("Telephone", "can call anyone" , 450)};

        Items basementItems [] = {new Items("Multivitamins tablets", "must be expired", 100),
                new Items ("Toys","anyone can play with it", 23)};

        Items BoudoirItems [] = {new Items("rifle", "for the show", 280),
                new Items ("Accessories","gives you a shiny apperance", 65),
                new Items ("Sewing_Kit","to sew the clothes", 45)};

        Items SolarItems [] = {new Items("Dragon_egg","!!YOU FOUND WHAT YOU ARE LOOKING FOR!!", 10),
                new Items ("Books","for people to read" , 56)};

        Items outsideItems [] = {new Items ("Bench"," for you to sit down", 8000)};

        Items bedroomItems [] = {new Items ("CCTV camera","there must be something which requires surveillance ", 8000)};

        // create the rooms
        outside = new Room("You are outside. You can see the giant Dragonwalde Castle standing right in front of you");
        Hall = new Room("You enter the Great Hall, you can see hall packed with thousands of people. You disguised yourself in the party so noone recognises you.");
        Boudoir = new Room("You manage to escape and ended up in a boudoir.");
        Solar = new Room("You are in solar room. You realise you are close to your plan being successful.");
        basement = new Room("You are in dark, damp basement with small ray of sunshine peeking through the hole in the wall.");
        Gatehouse = new Room("Now you are in a Gatehouse, you can see the main entrance of castle from here.");
        Pantry = new Room( "You are in Pantry, where it's filled with food.");
        Kitchen = new Room("Your strong sense of smell led you to the kitchen.");
        bedroom = new Room("This is a bedroom with high security, make sure you don't show up in this room with dragon's egg");

        // Adding array of Items into it's room
        outside = addRoomsItems(outside, outsideItems);
        Hall = addRoomsItems(Hall, HallItems);
        Boudoir = addRoomsItems(Boudoir, BoudoirItems);
        Solar = addRoomsItems(Solar, SolarItems);
        basement = addRoomsItems(basement, basementItems);
        Gatehouse = addRoomsItems(Gatehouse, GatehouseItems);
        Pantry = addRoomsItems(Pantry, PantryItems);
        Kitchen = addRoomsItems(Kitchen, KitchenItems);
        bedroom = addRoomsItems(bedroom, bedroomItems);

        // initialise room exits
        outside.setExit("east", Hall);
        outside.setExit("south", Gatehouse);

        Hall.setExit("east", Kitchen);
        Hall.setExit("south", Pantry);
        Hall.setExit("southeast", Boudoir);
        Hall.setExit("southwest", Gatehouse);

        Pantry.setExit("north", Hall);
        Pantry.setExit("east", Boudoir);
        Pantry.setExit("west", Gatehouse);
        Pantry.setExit("southwest", basement);

        Kitchen.setExit("west", Hall);
        Kitchen.setExit("south", Boudoir);
        Kitchen.setExit("southwest", Pantry);

        Gatehouse.setExit("east", Pantry);
        Gatehouse.setExit("southeast", basement);

        Boudoir.setExit("north", Kitchen);
        Boudoir.setExit("west", Pantry);
        Boudoir.setExit("south", bedroom);
        Boudoir.setExit("northwest", Hall);

        basement.setExit("east", bedroom);
        basement.setExit("southeast", Pantry);
        basement.setExit("northwest", Gatehouse);

        Solar.setExit("west", outside);
        Solar.setExit("north", bedroom);

        bedroom.setExit("west", basement);
        bedroom.setExit("north", Boudoir);
        bedroom.setExit("south", Solar);

        // define the RoomRandomizer() object
        roomRandomiser = new RoomRandomiser();
        roomRandomiser.addRoom(outside);
        roomRandomiser.addRoom(Hall);
        roomRandomiser.addRoom(Pantry);
        roomRandomiser.addRoom(Kitchen);
        roomRandomiser.addRoom(Gatehouse);
        roomRandomiser.addRoom(Boudoir);
        roomRandomiser.addRoom(basement);
        roomRandomiser.addRoom(Solar);
        roomRandomiser.addRoom(bedroom);

        //define the transporter object
        transporter = new MagicRoom("You are in the Magic Room", roomRandomiser);

        // initialising room exist to the transporter room.
        Kitchen.setExit("southeast", transporter);
        basement.setExit("west", transporter);

        player.setCurrentRoom(outside);     // set the player's current room outside
    }

    /**
     * Create player details.
     */
    private void createPlayer()
    {
        System.out.println ("Name of the player: ");        //Ask the user to type in their name
        String name = reader.nextLine();
        player.setPlayerName(name);                       
        createRooms();
    }

    /**
     * Add an array of an items to the rooms.
     * @return room items
     */
    private Room addRoomsItems (Room room, Items items[])
    {
        for (int i = 0; i < items.length; i++)
        { 
            room.addItem(items[i]);
        }
        return room;
    }

    /**
     *  Main play routine.  Loops until end of play.
     */
    public void play() 
    {            
        createPlayer();
        printWelcome();
        // Enter the main command loop.  Here we repeatedly read commands and
        // execute them until the game is over.
        boolean finished = false;
        while (! finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        System.out.println("Thank you for playing" +" "+player.getPlayerName()+" "+"Good bye.");
    }

    /**
     * Print out the opening message for the player.
     */
    private void printWelcome()
    {
        System.out.println("You heard a rumour that there is a rare Dragon's egg hidden away in the castle");
        System.out.println("After many years wandering around the globe, you finally found the name of the Castle - DRAGONWALDE CASTLE!! ");
        System.out.println("You decided to go on 21st December so you can easily disguise yourself at the Castle's Party.");
        System.out.println();
        System.out.println("Type 'help' if you need help.");
        System.out.println();
        System.out.println("Hello"+" " + player.getPlayerName());
        System.out.println(player.getPlayerDescription());
    }

    /**
     * Given a command, process (that is: execute) the command.
     * @param command The command to be processed.
     * @return true If the command ends the game, false otherwise.
     */
    private boolean processCommand(Command command) 
    {
        boolean wantToQuit = false;

        if(command.isUnknown()) {
            System.out.println("I don't know what you mean...");
            return false;
        }

        String commandWord = command.getCommandWord();
        if (commandWord.equals("help")) {
            printHelp();
        }
        else if (commandWord.equals("go")) {
            wantToQuit = goRoom(command);
        }
        else if (commandWord.equals("quit")) {
            wantToQuit = quit(command);
        }
        else if (commandWord.equals("look")) {
            look();
        }
        else if (commandWord.equals("eat")) {
            eat();
        }  
        else if (commandWord.equals("back")) {
            backRoom();
        }
        else if (commandWord.equals("pick")) {
            pickedUpItem(command);
        }
        else if (commandWord.equals("drop")) {
            dropTheItem(command);
        }
        else if (commandWord.equals("sit")) {
            Sit();
        }
        else if (commandWord.equals("nap")) {
            Nap();
        }
        else if (commandWord.equals("show")) {
            showCommandwordsFunction(command);
        }

        // else command not recognised.
        return wantToQuit;
    }

    private void showCommandwordsFunction (Command command)
    {
        //check if there is a second word
        if (!command.hasSecondWord())
        {
            System.out.println ("For this command to work, it requires three word");
            return;
        }
        else if(command.hasSecondWord() && !command.hasThirdWord())
        {
            System.out.println ("Could you please input the third word");
        }
        else
        {
            System.out.println("'eat' command will only work, if you are in the Kitchen or Pantry ");
            System.out.println("If you use 'eat' command you will get a hint as to where dragon egg is hidden");
            System.out.println();
            System.out.println("‘Sit’ command will only work if you are outside as you can only sit on the bench ");
            System.out.println("'nap' command only works in Gatehouse as there is a bed. ");
            System.out.println("'Look' command returns the description of the room ");
            System.out.println("'pick' allows you to pick up one item at a time from the room that weighs less than 200, for example 'pick dragon_egg' ");
            System.out.println("'drop' allows the player to drop one item at a time at the room, for example, 'drop chocolate..'");
            System.out.println("'back' command takes the player to the previous room");
            System.out.println("'Go' command allows you to move to different location, for example, 'go east'");
            System.out.println("'Quit' command will end the game");
            System.out.println("'Help' command displays the message which will assist you to win game");
        }
    }

    /**
     * Make the player to pick up an item from the current room
     */
    private void pickedUpItem (Command command)
    {
        //check if there is a second word
        if (!command.hasSecondWord())
        {
            System.out.println ("What do you want to pick up?");
            return;
       }
        String itemName = command.getSecondWord();
        player.pickUpItem(itemName);
    }

    /**
     * Make the player to drop an item from the current room
     */
    private void dropTheItem (Command command)
    {
        if (!command.hasSecondWord())
        {
            System.out.println ("What do you want to drop?");
            return;
        }
        String itemName = command.getSecondWord();
        player.dropItem(itemName);
    }

    /**
     * Print out some help information.
     * Here we print some stupid, cryptic message and a list of the 
     * command words.
     */
    private void printHelp() 
    {
        System.out.println("You need to find the Dragon's egg hidden in the castle and escape outside for you to win");
        System.out.println("Feel free to use the command words to see their function.");
        System.out.println();
        System.out.println("If you are not sure what the below command do then type - 'show command function' and it will display the function of a command");
        System.out.println("Your command words are:");
        String command = parser.showCommands();
        System.out.println(command);
    }

    /**
     * Prints out the current room description withit's exists.
     */
    private void look()
    {
        System.out.println(player.getPlayerDescription());
    }

    /**
     * Check to see if we are in the right room for us to eat.
     */
    private boolean eat()
    {
        if (player.getCurrentRoom () == Kitchen){
            System.out.println("The food was really good");
            System.out.println("Hint: the egg is in Solar room");
            return false;
        }
        else
        {
            if (player.getCurrentRoom () == Pantry) {
                System.out.println("The food was delicious!");
                System.out.println("The only way to go to solar room is to 'go south' from bedroom");
                return false;
            }
            System.out.println ("Please find Kitchen or Pantry to eat");
            return true;
        }
    }

    /**
     * Checks to see if we are in the right room to sleep.
     */
    private boolean Sit()
    {
        if (player.getCurrentRoom () == outside) {
            System.out.println("The bench is very comfortable to sit down");
            return false;
        }
        else
        {
            System.out.println ("Please find a place to sit, for example in bench");
            return true;
        }
    }

    /**
     * Checks to see if we are in the right room to nap.
     */
    private boolean Nap()
    {
        if (player.getCurrentRoom () == Gatehouse) {
            System.out.println("You have a riddle: When you go to solar room, it will only show two exist. One way leads to outside and other to bedroom");
            System.out.println("If you go to bedroom with an egg, you will loose the game.");
            System.out.println("However, there is still a way to go to bedroom with an egg without getting caught.");
            System.out.println();
            System.out.println("Can you figure out how that is possible? Hint: Look at the command words and their function");
            return false;
        }
        else
        {
            System.out.println ("Please find Gatehouse to take a nap");
            return true;
        }
    }

    /** 
     * Try to in to one direction. If there is an exit, enter the new
     * room, otherwise print an error message.
     */
    private boolean goRoom(Command command) 
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Go where?");
            return false;
        }

        String direction = command.getSecondWord();

        // Try to leave current room.
        Room nextRoom = player.getPlayersExit(direction);

        if (nextRoom == null) {
            System.out.println("There is no door!");
        }
        else {
            if (nextRoom.getShortDescription().contains ("Magic Room"))
            {
                System.out.println();
                System.out.println( nextRoom.getShortDescription());
                // get exit from transporter room
                nextRoom = nextRoom.getExit(directionsList [(int)Math.random()*7]);
                System.out.println( "You are transported");
            }
            player.setPlayersEnteringRoom(nextRoom);
            System.out.println(player.getPlayerDescription());
            if (player.getPlayerDescription().contains("Dragon_egg") && player.getCurrentRoom () == outside){
                System.out.println ("Well done, you won!");
                return true;
            }
            else
            {
                if (player.getPlayerDescription().contains("Dragon_egg") && player.getCurrentRoom () == bedroom){
                    System.out.println ("Looks like the security guard saw you with the egg");
                    System.out.println ("You couldn't escape - you LOST the game");
                    return true;
                }
                if (counter == 35)
                {
                    System.out.println("Opps! It took you too long, the game is over");
                    return true;
                }
                counter++;
            }
        }
        return false;
    }

    private void backRoom()
    {
        player.toPreviousRoom();
    }

    /** 
     * "Quit" was entered. Check the rest of the command to see
     * whether we really quit the game.
     * @return true, if this command quits the game, false otherwise.
     */
    private boolean quit(Command command) 
    {
        if(command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        }
        else {
            return true;  // signal that we want to quit
        }
    }
}
