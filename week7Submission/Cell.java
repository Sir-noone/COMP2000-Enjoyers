import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;


public class Cell extends Rectangle {
    //fields
    int x;
    int y;
    static int size = 20;

    //constructor
    public Cell(int inX, int inY) {
        x = inX;
        y = inY;
    }

    //methods
    public void paint(Graphics g, Point mousePos) {
        if (contains(mousePos)) {
            g.setColor(Color.GREEN);
        } else {
            g.setColor(Color.WHITE);
        }
        g.fillRect(x,y,size,size);
        g.setColor(Color.BLACK);
        g.drawRect(x,y,size,size);
    }

    public boolean contains(Point p) {
        if (p != null) {
            return x < p.x && p.x < x + size && y < p.y && p.y < y + size;
        } else {
            return false;
        }
    }
}
