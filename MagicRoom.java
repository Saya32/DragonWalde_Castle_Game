/**
 * This is a MagicRoom class, extended class of Room class. Every time the character enter this room it will transport them to a random room in the game.
 *
 * @aurthor Sayaka Bhandari
 * @version 2021.12.01
 */
public class MagicRoom extends Room
{
    private RoomRandomiser randomRoom;

    public MagicRoom (String description, RoomRandomiser randomRoom)
    {
        super (description);
        this.randomRoom = randomRoom;
    }

    /**
     * @param uses direction if it cannot find the random room to exit from.
     * @return a random room 
     */
    public Room getExit (String direction)
    {
        return randomRoom.findRandomRoom();
    }
}
