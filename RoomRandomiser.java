import java.util.ArrayList;
import java.util.Random;

/**
 * Class RoomRandomiser - has a method to add and return the random room from the arrayList of Room.
 * 
 * @author Sayaka Bhandari
 * @version 2021.12.01
 */

public class RoomRandomiser
{
    private ArrayList<Room> listRoom;
    private Random randNumberGenerator;

    /**
     * Constructor - intialise the RoomRandomiser class variables
     */
    public RoomRandomiser()
    {
        listRoom = new ArrayList<Room> ();
        randNumberGenerator = new Random();
    }

    /**
     * Adds the room to the roomList
     * @param Add room
     */
    public void addRoom (Room room)
    {
        listRoom.add(room);
    }
    
    /**
     * @returns a random room from roomList.
     */
    public Room findRandomRoom()
    {
        if (listRoom.size() == 0)
        {
            return null;
        }
        int randIndex = randNumberGenerator.nextInt (listRoom.size());
        return listRoom.get(randIndex);
    }
}


