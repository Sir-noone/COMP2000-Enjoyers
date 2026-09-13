
import java.util.Random;
import java.awt.Color;
import java.awt.Point;
import java.awt.Polygon;
import java.util.ArrayList;
import java.util.List;

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

    int randomAge = rand.nextInt(4); // generates a random number between 0 and 3 to detemine AgeGroup
    int randomNum = rand.nextInt(2); // generates a random number between 0 and 1 to determine randomly whether humann
                                           // is infected or not at the start of the simulation

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

    public void move(Grid grid, List<Human> humans) {
        int currentRow = -1;
        int currentCol = -1;

        // Find the row and column of this humans current cell in the grid
        for (int row = 0; row < grid.rows; row++) {
            for (int col = 0; col < grid.cols; col++) {
                if (grid.cells[row][col] == loc) {
                    currentRow = row;
                    currentCol = col;
                    break;
                }
            }
            if (currentRow != -1) {
                break;
            }
        }

        if (currentRow == -1) {
            return;
        }

        // Collect each valid neighboring cell that is not occupied by another human
        List<Cell> possibleCells = new ArrayList<>();
        for (int row = currentRow - 1; row <= currentRow + 1; row++) {
            for (int col = currentCol - 1; col <= currentCol + 1; col++) {
                if (row < 0 || row >= grid.rows || col < 0 || col >= grid.cols) {
                    continue;
                }
                if (row == currentRow && col == currentCol) {
                    continue;
                }

                Cell candidate = grid.cells[row][col];
                boolean occupied = false;
                for (Human human : humans) {
                    if (human != this && human.loc == candidate) {
                        occupied = true;
                        break;
                    }
                }

                if (!occupied) {
                    possibleCells.add(candidate);
                }
            }
        }

        // Choose one of the available cells at random, if any exist
        if (!possibleCells.isEmpty()) {
            setLocation(possibleCells.get(rand.nextInt(possibleCells.size())));
        }
    }

    public void infectNearby(List<Human> humans, Disease disease) {
        if (!infected) {
            return;
        }

        for (Human human : humans) {
            if (human != this && !human.isInfected()) {
                disease.infect(this, human);
            }
        }
    }

    public double health(){
        this.health = Age() + rng;
        return this.health;
    }

    public void infect(Disease disease){ 
        if (this.infected){
            return;
        }
        
        double rng = Math.random();
        if(rng < disease.getlethalityRate()){
        this.infected = true;
        this.color = Color.RED;
        }
    }

    public boolean isInfected() {
        return this.infected; //placeholder for infected status
    }


}

