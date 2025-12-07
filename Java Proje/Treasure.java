public class Treasure extends RoomContent{
    public String itemFound;
    public Treasure(String itemFound){
        this.itemFound = itemFound;
        this.type = "TREASURE";
    }
    
}