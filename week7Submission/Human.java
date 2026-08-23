import java.util.Random;
 
class Human implements AgeGroup {
    private AgeGroup age;
    private double immunity;
    private boolean infected;
    private double health; // cannot exceed 0.5
    private double mortality = 0.1;
    Random rand = new Random();

    public Human() {

    }

    //public infect(); 
    public recover(); 
    public move(); // dependent on grid
    public double health(){
        this.health = 0.4;
        return this.health;
    }

    public static void main(String[] args) {
        Random rand = new Random();
        int any = rand.nextInt(100);
        System.out.println(any);
    }
}

