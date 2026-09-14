import java.awt.Color;
import java.awt.Graphics;
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
    public void paint(Graphics g) {

        g.setColor(Color.WHITE);
        g.fillRect(x,y,size,size);
        g.setColor(Color.BLACK);
        g.drawRect(x,y,size,size);
    }


}
