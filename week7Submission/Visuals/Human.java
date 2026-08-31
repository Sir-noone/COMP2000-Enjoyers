package Visuals;
import java.util.Random;
import java.awt.Color;
import java.awt.Polygon;
import java.util.ArrayList;

class Human extends Actor{
    private AgeGroup age;
    //private double age = 0.1; // placeholder for age group, will be set by the simulation
    private double immunity;
    public boolean infected;
    private double health; // cannot go outside range 0.99-->0.5
    private double mortality = 0.1; // placeholder for mortality rate

    //rng modifier to add randomness to health calculation
    Random rand = new Random();
    double rng = 0.3 + rand.nextDouble(0.2);

    int randomAge = rand.nextInt(4); // generates a random number between 0 and 3

    public Human(Cell inLoc, boolean isBot) {

        super(inLoc, Color.RED, isBot);

        if (randomAge == 0) {
            this.age = AgeGroup.CHILD;
        } else if (randomAge == 1) {
            this.age = AgeGroup.YOUTH;
        } else if (randomAge == 2) {
            this.age = AgeGroup.ADULT;
        } else {
            this.age = AgeGroup.ELDERLY;
        }

        
    }

    protected void setPoly() {
        display = new ArrayList<Polygon>();
        Polygon face = new Polygon();
        face.addPoint(loc.x + 8, loc.y + 7);
        face.addPoint(loc.x + 27, loc.y + 7);
        face.addPoint(loc.x + 27, loc.y + 25);
        face.addPoint(loc.x + 8, loc.y + 25);
        display.add(face);

  }

    
    public double Age() {
        return this.age.getAgeValue();
    }

    //public void recover(); // dependent on disease
    //public void move(); // dependent on grid

    public double health(){
        this.health = Age() + rng;
        return this.health;
    }

    public void infect(){ 
        if (this.infected){
            return;
        }
        
        //if this cell has an uninfected neighbour --> range
        //then infect cell
    }

    public boolean isInfected() {
        return this.infected; //placeholder for infected status
    }

    public static void main(String[] args) {
        Random rand = new Random();
        Stage stage = new Stage();
        Human human = new Human(stage.grid.cellAtColRow(0, 15).get(), true);
        double any = human.health();
        System.out.println(any);
    }


}

