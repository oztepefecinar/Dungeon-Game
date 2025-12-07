public class MonsterEncounter extends RoomContent{
    public Monster monster;
    public MonsterEncounter(Monster monster){
        this.monster = monster;
        this.type = "MONSTER";
    }
}