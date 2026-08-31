package Visuals;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;
import java.util.List;

public abstract class Actor{
  Color baseColor, color;
  Cell loc;
  public List<Polygon> display;
  boolean bot;
  int moves;
  int turns;
  MoveStrategy mover;

  protected Actor(Cell inLoc, Color inColor, boolean isBot) {
    loc = inLoc;
    baseColor = inColor;
    color = inColor;
    bot = isBot;
    turns = 1;
    setPoly();
  }

  public void paint(Graphics g) {
    for(Polygon p: display) {
      g.setColor(color);
      g.fillPolygon(p);
      g.setColor(Color.GRAY);
      g.drawPolygon(p);
    }
  }

  protected abstract void setPoly();

  public boolean isBot() {
    return bot;
  }

  public void setLocation(Cell inLoc) {
    loc = inLoc;
    if(loc.row % 2 == 0) {
      mover = new MoveRandomly();
    } else {
      mover = new MoveLeft();
    }
    setPoly();
  }

}
