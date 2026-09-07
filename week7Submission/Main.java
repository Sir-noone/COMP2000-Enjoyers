import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.swing.*;

public class Main extends JFrame {
    private final JLabel details = new JLabel("Hover over a human to see details");

    public static void main(String[] args) {
        Main frame = new Main();
        frame.run();
    }

    class Canvas extends JPanel {
        Grid grid = new Grid();
        List<Human> humans = new ArrayList<>();

        public Canvas() {
            setPreferredSize(new Dimension(
                    grid.cols * Cell.size + 10,
                    grid.rows * Cell.size + 10));

            addMouseMotionListener(new MouseMotionAdapter() {
                @Override
                public void mouseMoved(MouseEvent event) {
                    showHumanDetails(event.getPoint());
                }
            });

            List<Cell> availableCells = new ArrayList<>();
            for (int row = 0; row < grid.rows; row++) {
                for (int col = 0; col < grid.cols; col++) {
                    availableCells.add(grid.cells[row][col]);
                }
            }

            Collections.shuffle(availableCells);
            for (int i = 0; i < 20; i++) {
                humans.add(new Human(availableCells.get(i), false));
            }
        }

        private void showHumanDetails(Point mousePosition) {
            for (Human human : humans) {
                if (human.contains(mousePosition)) {
                    details.setText("<html>Age: " + human.getAgeGroup()
                            + "<br>Infected: " + human.isInfected()
                            + "<br>Health: " + String.format("%.2f", human.health())
                            + "</html>");
                    return;
                }
            }
            details.setText("Hover over a human to see details");
        }

        @Override
        public void paint(Graphics g) {
            
            grid.paint(g, getMousePosition());
            for (Human human : humans) {
                human.paint(g);
            }
        }
    }
        private Main(){
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            this.setLayout(new BorderLayout());
            Canvas canvas = new Canvas();
            details.setPreferredSize(new Dimension(180, 100));
            details.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
            this.add(canvas, BorderLayout.CENTER);
            this.add(details, BorderLayout.EAST);
            this.pack();
            this.setVisible(true);
        }  

        public void run() {
            while (true) {
                repaint();
            }
        }
    
}