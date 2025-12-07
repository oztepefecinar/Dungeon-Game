public class Monster{
    public String name;
    public int health;
    public int damage;
    public boolean isDog;
    public boolean isCrocodile;
    public boolean isLion;
    public Player p1;
            public Monster(String name,int health, int damage, boolean isDog,boolean isCrocodile, boolean isLion ){
                this.name = name;
                this.health = health;
                this.damage = damage;
                this.isDog = isDog;
                this.isCrocodile = isCrocodile;
                this.isLion = isLion;
            }
        public void takeDamage(int damage){
            if(health>=damage){
            health -= damage;
            System.out.println("Monster is hurt "+damage+" HP\n"+name+" Health: "+health+"HP" );}
            else{
                health = 0;
            }
        }
        public boolean isDefeated(){
            if(health == 0){//I check if it is 0 bec. even the damage is greater than health it sets the health 0.
                return true;
            }else{
                return false;
            }
        }
        public void specialAction(Player p1){
            if(isDog == true){
                System.out.println(name+" uses its Sonic Howl ability! It hurts your ears.(+10 damage)");
                p1.takeDamage(damage+10);
            }
            else if(isCrocodile == true){
                this.health +=20;
                System.out.println(name+" uses its Regeneration Flying ability for getting away and restore health! Its hp become +20 :"+health+"HP");
            }
            else if(isLion == true){
                System.out.println(name+" uses its Uranium Roar ability! You caught on fire.(+30 damage)");
                p1.takeDamage(this.damage+30);
            }
        }


}