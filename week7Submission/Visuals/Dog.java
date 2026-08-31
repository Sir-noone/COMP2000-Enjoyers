package Visuals;
import java.awt.Color;
import java.awt.Polygon;
import java.util.ArrayList;

public class Dog extends Actor {
  public static final int dogMoves = 1;

  public Dog(Cell inLoc, boolean isBot) {
    super(inLoc, Color.YELLOW, isBot, dogMoves);
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
}
