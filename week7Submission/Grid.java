import java.awt.Graphics;


public class Grid {
    //fields
    int rows = 30;
    int cols = 30;
    Cell[][] cells = new Cell[rows][cols];

    //constructor
    public Grid() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                cells[i][j] = new Cell(10+j * Cell.size, 10+i * Cell.size);
            }
        }  
    }

    //methods
    public void paint(Graphics g) {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                cells[i][j].paint(g);
            }
        }
    }    
}
