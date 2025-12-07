public class MonsterFactory{
    public static Monster createMonster(String type){
        if(type.equals("dog")){
            Monster m1 = new Monster("Mutant Howler Dog",30,5,true,false,false);
            return m1;
        }
        else if(type.equals("crocodile")){
            Monster m2 = new Monster("Mutant Flying Crocodile",45,15,false,true,false);
            return m2;
        }
        else if(type.equals("lion")){
             Monster m3 = new Monster("Mutant Fire Lion",60,25,false,false,true);
             return m3;
        }
        else{
            return null;
        }
    }
}