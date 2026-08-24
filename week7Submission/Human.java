import java.util.Random;

class Human implements Infectable{
    private AgeGroup age;
    //private double age = 0.1; // placeholder for age group, will be set by the simulation
    private double immunity;
    private boolean infected;
    private double health; // cannot go outside range 0.99-->0.5
    private double mortality = 0.1; // placeholder for mortality rate

    //rng modifier to add randomness to health calculation
    Random rand = new Random();
    double rng = 0.3 + rand.nextDouble(0.2);

    int randomAge = rand.nextInt(4); // generates a random number between 0 and 3

    public Human() {
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

    
    public double Age() {
        return this.age.getAgeValue();
    }

    //public void recover(); // dependent on disease
    //public void move(); // dependent on grid

    public double health(){
        this.health = Age() + rng;
        return this.health;
    }

    @Override
    public void infect(){ 
        //if this cell has an uninfected neighbour --> range
        //then infect cell

    }

    @Override 
    public boolean isInfected() {
        return this.infected; //placeholder for infected status
    }

    public static void main(String[] args) {
        Random rand = new Random();
        Human human = new Human();
        double any = human.health();
        System.out.println(any);
    }


}

