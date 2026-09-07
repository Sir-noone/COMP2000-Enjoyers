
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;
import java.util.List;

public abstract class Actor{
  Color color;
  Cell loc;
  public List<Polygon> display;

  protected Actor(Cell inLoc, Color inColor) {
    loc = inLoc;
    color = inColor;
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


  /*public void setLocation(Cell inLoc) {
    loc = inLoc;
    if(loc.y % 2 == 0) {
      
    } else {
      
    }
    setPoly();
  } */

}
