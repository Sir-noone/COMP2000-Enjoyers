
import java.util.Random;
import java.awt.Color;
import java.awt.Point;
import java.awt.Polygon;
import java.util.ArrayList;

//Add patient Zero to start of array list of individuals

public class Human extends Actor{
    private AgeGroup age;
    //private double age = 0.1; // placeholder for age group, will be set by the simulation
    private double immunity;
    public boolean infected = false;
    private double health; // cannot go outside range 0.99-->0.5
    private double mortality = 0.1; // placeholder for mortality rate

    //rng modifier to add randomness to health calculation
    Random rand = new Random();
    double rng = 0.3 + rand.nextDouble(0.2);

    int randomAge = rand.nextInt(4);
    int randomNum = rand.nextInt(2); // generates a random number between 0 and 1

    public Human(Cell inLoc, boolean isBot) {

        super(inLoc, Color.GREEN);

        if (randomNum == 0) {
            this.infected = true;
        } else {
            this.infected = false;
        }

        if (infected) {this.color = Color.RED;}
        
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
        face.addPoint(loc.x + 10, loc.y + 3);
        face.addPoint(loc.x + 17, loc.y + 10);
        face.addPoint(loc.x + 10, loc.y + 17);
        face.addPoint(loc.x + 3, loc.y + 10);
        display.add(face);

  }

    
    public double Age() {
        return this.age.getAgeValue();
    }

    //getAgeGroup method to return the age group of the human for Stage.java to display in the side panel
    public AgeGroup getAgeGroup() {
        return this.age;
    }

    public boolean contains(Point point) {
        for (Polygon polygon : display) {
            if (polygon.contains(point)) {
                return true;
            }
        }
        return false;
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


}

