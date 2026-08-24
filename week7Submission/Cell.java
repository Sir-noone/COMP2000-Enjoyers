package Projectbook.src;
import java.awt.Color;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Graphics;

public class Cell extends Rectangle {
    // this part represent the size
    static int size = 35;
    char col;
    int row;

    public Cell(char inCol, int inRow, int x, int y, int alt){
        super(x, y, size, size);
        col = inCol;
        row = inRow;
    }

    // this part manages the mouse position. if mouse position over cell then background is black

    public void paint(Graphics g, Point mousePos) {
        if(contains(mousePos)) {
            g.setColor(Color.black);
        } 
        g.fillRect(x, y, size, size);
        g.setColor(Color.black);
        g.fillRect(x, y, size, size);
    }

    @Override
    public boolean contains(Point p){
        if(p != null){
            return super.contains(p);
        
        }else {
            return false;
        }
    }

    public int leftOfComparison(Cell c){
        return Integer.compare(col, c.col);
    }

    public int AboveOfComparison(Cell c){
        return Integer.compare(row, c.row);
    }
}
