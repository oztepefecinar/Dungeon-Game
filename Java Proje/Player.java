public class Player{
    public String name;
    public int currentHealth;
    public int maxHealth;
    public int attackPower;
    public String[] inventory;
    public boolean isDefending;
            public Player(String name,int maxHealth,int currentHealth,int attackPower,int inventorySize){
                if(currentHealth<=maxHealth){
                this.name = name;
                this.maxHealth = maxHealth;
                this.currentHealth = currentHealth;
                this.attackPower = attackPower;
                this.inventory = new String[inventorySize];
                for (int i = 0; i< inventory.length; i++){
                    inventory[i] = "EMPTY";
                }
                }
                else{
                    System.out.println("You can't set your current health bigger than max health!");
                }


            }
        public void takeDamage(int damage){
            if(currentHealth>=damage){
            currentHealth -= damage;
             System.out.println("\n"+name.toUpperCase()+" is hurt "+damage+" HP\nNew Player Health:"+currentHealth);}
            else{
                currentHealth = 0;
            }
        }
        public void heal(int amount){
            if(currentHealth+amount<=maxHealth){
            currentHealth += amount;}
            else{
                currentHealth = maxHealth;
            }
        }
        public void printStatus(){
            System.out.println("\n>===STATUS===<\n\nHP: "+currentHealth+"\nInventory: ");
            boolean isInvEmpty = true;//If inventory is empty I will print another thing
            for(int i = 0; i<inventory.length; i++){
                if(inventory[i] != null && !inventory[i].equals("EMPTY")){
                    System.out.println(" ["+inventory[i]+"] ");
                    isInvEmpty = false;
                }
            }
            if(isInvEmpty){
                System.out.println("There is nothing in your inventory!");
            }
        }
        public static Player createCharacter(String name1){
            Player p1 = new Player(name1,100,100,15,5);
            return p1;
        }
        public void addItemToInventory(String item){
            boolean isAdded = false;
            for (int i= 0; i<inventory.length; i++){
                if(inventory[i]== null ||inventory[i].equals("EMPTY")){
                    inventory[i] = item;
                    System.out.println("The nuclear tank opens and an item found: "+item+"\n"+item+" added to your inventory");
                    isAdded = true;
                    break;
                }
            }
            if(!isAdded){
                System.out.println("Your inventory is full item dropped...");
            }
        }
}