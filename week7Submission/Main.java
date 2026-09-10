import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.swing.*;

public class Main extends JFrame {
    private final JLabel population = new JLabel();

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Main::new);
    }

    class Canvas extends JPanel {
        Grid grid = new Grid();
        List<Human> humans = new ArrayList<>();
        int infected = 0;
        int healthy = 0;

        public Canvas() {
            setPreferredSize(new Dimension(
                    grid.cols * Cell.size + 10,
                    grid.rows * Cell.size + 10));


            List<Cell> availableCells = new ArrayList<>();
            for (int row = 0; row < grid.rows; row++) {
                for (int col = 0; col < grid.cols; col++) {
                    availableCells.add(grid.cells[row][col]);
                }
            }

            Collections.shuffle(availableCells);
            for (int i = 0; i < 100; i++) {
                humans.add(new Human(availableCells.get(i), false));
            }

            updatePopulationCounts();
            Disease disease = new Disease(3.0, 5, 14, 0.1, new DirectTransmission());
            Timer timer = new Timer(1000, event -> {
                for (Human human : humans) {
                    human.move(grid, humans);
                }
                for (Human human : humans) {
                    human.infectNearby(humans, disease);
                }
                updatePopulationCounts();
                repaint();
            });
            timer.start();
        }

        private void updatePopulationCounts() {
            infected = 0;
            healthy = 0;

            for (Human human : humans) {
                if (human.isInfected()) {
                    infected++;
                } else {
                    healthy++;
                }
            }

            population.setText("<html>Infected: " + infected
                    + "<br>Not infected: " + healthy + "</html>");
        }


        @Override
        public void paint(Graphics g) {
            super.paint(g);
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
            population.setPreferredSize(new Dimension(180, 100));
            population.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
            this.add(canvas, BorderLayout.CENTER);
            JPanel sidePanel = new JPanel(new BorderLayout());
            sidePanel.add(population, BorderLayout.NORTH);
            this.add(sidePanel, BorderLayout.EAST);
            this.pack();
            this.setVisible(true);
        }  

}