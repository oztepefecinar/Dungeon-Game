import java.util.Random;
import java.util.Scanner;
public class ChernoblyGame{
    public static void startCombat(Player player,Monster monster){
        Scanner s = new Scanner(System.in);
        System.out.println(monster.name+" is in the room! Prepare to fight... ");
            while(player.currentHealth>0 && monster.health>0){
                //first the player starts
                player.isDefending = false;
                System.out.println("\n<<<"+player.name.toUpperCase()+" TURN>>>");
                System.out.print("Select your action (A)ttack, (H)eal, (D)efend, (S)pecial Action: ");
                String action = s.nextLine().trim().toUpperCase();
                if(action.equals("A")){
                    double randomHitMultiplier = 0.8 +(Math.random()*0.4);
                    int damage = (int)(player.attackPower*randomHitMultiplier);
                        if(Math.random()<0.1){// 10 percent chance of critical hit (damage*2)
                            damage *= 2 ;
                            System.out.println("\n==>Critical Hit<==\nDamage dealt:-"+damage);
                        }
                        monster.takeDamage(damage);
                }
                else if (action.equals("S")){
                    System.out.println(">>>>URANIUM PUNCH IS ACTIVATED<<<<");
                    int specialDamage = 30;
                    monster.takeDamage(specialDamage);
                    System.out.println("You dealt massive damage (-30 HP) but radiation burned you (-15 HP)");
                    player.takeDamage(15);
                }
                else if(action.equals("H")){
                    boolean healerFound = false;
                    for (int i = 0; i<player.inventory.length; i++){
                        if(player.inventory[i]!= null && !player.inventory[i].equals("EMPTY")){
                        healerFound = true;}
                        if(healerFound){
                            int slot = i+1;
                        if(player.inventory[i]!= null && !player.inventory[i].equals("EMPTY")){
                        System.out.print(slot+". "+player.inventory[i]+" | ");}
                        else if(player.inventory[i]== null || player.inventory[i].equals("EMPTY")){
                            System.out.print(" "+slot+". EMPTY");
                        }
                        }}
                        int choice = 0; 
                        if(healerFound){
                            System.out.print("\nEnter the slot that you want to use: ");
                            choice = s.nextInt();
                            s.nextLine();
                            choice -= 1;
                            if(choice >= 0 && choice<player.inventory.length){
                            if(player.inventory[choice].equals("Heal")){
                                int healAmount = 25;
                            player.heal(healAmount);
                            System.out.println("The item "+player.inventory[choice]+" is used. New health:"+player.currentHealth);
                            player.inventory[choice] = "EMPTY";}
                            else if(player.inventory[choice].equals("Radiation Protector")){
                                 int healAmount = 10;
                            player.heal(healAmount);
                            System.out.println("The item "+player.inventory[choice]+" is used. New health:"+player.currentHealth);
                            player.inventory[choice] = "EMPTY";
                            }
                            else if(( player.inventory[choice] == null ||player.inventory[choice].equals("EMPTY"))){
                                System.out.println("There is nothing in the slot you loose your chance to attack...");
                            }}}
                    if(healerFound == false){
                        System.out.println("There is no heal item in your inventory! You loose your chance to attack...");
                    }
                    else if(choice < 0 || choice >4){
                        System.out.println("There is only 5 slots in your inventory! You loose your chance to attack!");
                    }
                }
                else if(action.equals("D")){
                    player.isDefending = true;
                    System.out.println("You defend yourself the damage that you take will be half!");
                }
                //Monster will attack now
                if(monster.health>0){
                    int dmg = monster.damage;
                    if(Math.random() <= 0.2){
                        monster.specialAction(player);//
                    }else{
                    System.out.println("\n>>>"+monster.name+" is attacking!<<<");
                    if(player.isDefending){
                        dmg/=2;
                        System.out.println("Your defence made the attack weaker!");}
                    player.takeDamage(dmg);
                    }
                }
            }
            if(player.currentHealth>0){
                System.out.println("\n===VICTORY===\n"+monster.name+" is defeated!");
            }else{
                System.out.println(">>>DEFEAT<<<\n"+monster.name+" turned you into a radioactive mutant!");
            }
    }
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("====WELCOME====\n\n====CHERNOBLY====\n\nYou are a soldier. Your commander gave you a solo task. You have to clear a building in Chernobly.\nBut be careful there are some mutants in there. You have to kill all the mutants in the building.\nGood Luck!\nCreating Character...\nPlease set your name: ");
        String cname = input.nextLine();
        Player p1 = Player.createCharacter(cname);
        int monsterCount = 0;
        int monstersDefeated = 0;
          RoomContent[][] dungeonMap = new RoomContent[4][4];
    int len = dungeonMap.length;
    Random r = new Random();
    for(int i= 0; i<len; i++){
        for(int j= 0; j<len; j++){
            if(i == 0 && j == 0){
                 dungeonMap[i][j] = new EmptyRoom();//The starting point always safe
                 continue;
            }
            int ranRoom = (int)(Math.random()*100);
            if(ranRoom>60){
            String k = "dog";
            int a = r.nextInt(1,11);
            if(a>9){
                k = "lion";
            }
            else if(a<=9 && a>6){
                k = "crocodile";
            }
            dungeonMap[i][j]= new MonsterEncounter(MonsterFactory.createMonster(k));
            monsterCount++;}
            else if(ranRoom<=60 && ranRoom>30){
                int tr = r.nextInt(1,3);
                String treasure = "default";
                switch (tr){
                    case 1:
                        treasure = "Heal";//+25 heal
                        break;
                    case 2:
                        treasure = "Radiation Protector";// giving heal but less than heal potion (+10 heal)
                        break;
                    
                }
                dungeonMap[i][j] = new Treasure(treasure);
            }
            else if(ranRoom<=50 && ranRoom>=0){
                dungeonMap[i][j] = new EmptyRoom();
            }
        }
    }
    //Map is set and now I will code the player movement
    int playerRow = 0;//starting points
    int playerCol = 0;// starting from left top  (0,0)
        while(p1.currentHealth > 0){
            
            System.out.println("\n____________________________________");
            System.out.println("The current location ["+playerRow+"]["+playerCol+"]");
            System.out.println("\n=====MAP=====");
            for (int i = 0; i< dungeonMap.length; i++){
                for (int j = 0; j< dungeonMap.length; j++){
                if(i == playerRow && j== playerCol){
                    System.out.print("[P]");
                }
                else {
                    System.out.print("[?]");
                }}
                System.out.println();
            }
            System.out.println("=============");
            System.out.print("Which way would you like to go? (N)orth, (S)outh, (E)ast, (W)est (If you want to look at your status enter (H)) \nEnter The Way: ");
            String move = input.nextLine().trim().toUpperCase();
            if(move.equals("H")){
                p1.printStatus();
                continue;
            }
            if(move.equals("N")){
                if(playerRow-1 >= 0){
                    playerRow--;
                } else{
                    System.out.println("Hey! You cannot go through a wall! Are you some kind of mutant?");
                    continue;
                }
            }
                else if(move.equals("S")){
                    if(playerRow + 1 < 4){
                        playerRow++;
                    }else{
                        System.out.println("According to physics laws this kind of act is impossible!");
                    continue;
                    }
                }
                else if(move.equals("E")){
                    if(playerCol+1<4){
                        playerCol++;
                    }else{
                        System.out.println("Sorry I can't afford a bigger map :(");
                    }
                }
                else if(move.equals("W")){
                    if(playerCol-1>= 0){
                        playerCol--;
                    }else{
                        System.out.println("Please don not try to walk through the walls!");
                    }
                }
                RoomContent currentRoom = dungeonMap[playerRow][playerCol];
                if(currentRoom.type.equals("MONSTER")){
                    MonsterEncounter m = (MonsterEncounter) currentRoom;//We are changing the room content to monster(I searched this from internet)
                    System.out.println("There is a mutant in the room!");
                    startCombat(p1, m.monster);
                    if(p1.currentHealth>0){
                        dungeonMap[playerRow][playerCol] = new EmptyRoom();
                        System.out.println("Room is cleared!");
                        monstersDefeated++;
                        if(monstersDefeated == monsterCount){//if they are equal this means all the monsters are defeated 
                        System.out.println("___________________________________________\n\n===>CONGRATS!!! THE BUILDING IS SAFE NOW<===\nTHANK YOU FOR YOUR SERVICES SOLDIER!");
                        p1.printStatus();
                        System.out.println("\nTotal Monsters Defeated: "+monsterCount);
                        break;
                        }
                    }
                }
                else if(currentRoom.type.equals("TREASURE")){
                    Treasure t = (Treasure) currentRoom;
                    p1.addItemToInventory(t.itemFound);
                    dungeonMap[playerRow][playerCol] = new EmptyRoom();
                }
                else{
                    System.out.println("The room is empty. There is nothing to see here...");
                }
        }
    }
}