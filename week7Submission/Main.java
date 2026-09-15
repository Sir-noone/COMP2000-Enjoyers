import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.swing.*;

public class Main extends JFrame {
    private final JLabel population = new JLabel();
    private final DefaultListModel<Human> humanListModel = new DefaultListModel<>();
    private final JList<Human> humanList = new JList<>(humanListModel);

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Main::new);
    }

    class Canvas extends JPanel {
        Grid grid = new Grid();
        List<Human> humans = new ArrayList<>();
        int infected = 0;
        int healthy = 0;
        int daysPassed = 0;

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
                humans.add(new Human(availableCells.get(i)));
            }

            updatePopulationCounts();
            Disease disease = new Disease(0.5, 5, 14, new DirectTransmission());
            Timer timer = new Timer(200, event -> {
                for (Human human : humans) {
                    human.move(grid, humans);
                }
                for (Human human : humans) {
                    human.infectionChecker(disease);
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
            daysPassed++;
            for (Human human : humans) {
                if (human.isInfected()) {
                    infected++;
                } else {
                    healthy++;
                }
            }

            population.setText("<html>Infected: " + infected
                    + "<br>Not infected: " + healthy + 
                    "<br>Days passed: " + daysPassed + "</html>");

                humanListModel.clear();
                for (int index = 0; index < humans.size(); index++) {
                Human human = humans.get(index);
                humanListModel.addElement(human);
                }
        }

        @Override
        public void paint(Graphics g) {
            super.paint(g);
            grid.paint(g);
            for (Human human : humans) {
                human.paint(g);
                if (human.prevLoc != null) {
                    g.setColor(Color.GRAY);
                    g.drawLine(human.prevLoc.x + Cell.size / 2, human.prevLoc.y + Cell.size / 2,
                        human.loc.x + Cell.size / 2, human.loc.y + Cell.size / 2);
                    
                    if ((human.prevLoc.x < human.loc.x) && (human.prevLoc.y == human.loc.y)) { //right
                        g.drawLine(human.loc.x + Cell.size / 4, human.loc.y + Cell.size / 4,
                        human.loc.x + Cell.size / 2, human.loc.y + Cell.size / 2);
                        g.drawLine(human.loc.x + Cell.size/ 4, human.loc.y + Cell.size - Cell.size / 4,
                        human.loc.x + Cell.size / 2, human.loc.y + Cell.size / 2);
                    }

                    if ((human.prevLoc.x == human.loc.x) && (human.prevLoc.y < human.loc.y)) { // down
                        g.drawLine(human.loc.x + Cell.size / 4, human.loc.y + Cell.size / 4,
                        human.loc.x + Cell.size / 2, human.loc.y + Cell.size / 2);
                        g.drawLine(human.loc.x + Cell.size - Cell.size / 4, human.loc.y + Cell.size / 4,
                        human.loc.x + Cell.size / 2, human.loc.y + Cell.size / 2);
                    }

                    if ((human.prevLoc.x > human.loc.x) && (human.prevLoc.y == human.loc.y)) { // left
                        g.drawLine(human.loc.x + Cell.size - Cell.size / 4, human.loc.y + Cell.size / 4,
                        human.loc.x + Cell.size / 2, human.loc.y + Cell.size / 2);
                        g.drawLine(human.loc.x + Cell.size - Cell.size / 4, human.loc.y + Cell.size - Cell.size / 4,
                        human.loc.x + Cell.size / 2, human.loc.y + Cell.size / 2);
                    }

                    if ((human.prevLoc.x == human.loc.x) && (human.prevLoc.y > human.loc.y)) { // up
                        g.drawLine(human.loc.x + Cell.size / 4, human.loc.y + Cell.size - Cell.size / 4,
                        human.loc.x + Cell.size / 2, human.loc.y + Cell.size / 2);
                        g.drawLine(human.loc.x + Cell.size - Cell.size / 4, human.loc.y + Cell.size - Cell.size / 4,
                        human.loc.x + Cell.size / 2, human.loc.y + Cell.size / 2);
                    }
                    
                    if ((human.prevLoc.x < human.loc.x) && (human.prevLoc.y > human.loc.y)){// diagonal right up
                        g.drawLine(human.loc.x + Cell.size / 4, human.loc.y + Cell.size / 2,
                        human.loc.x + Cell.size / 2, human.loc.y + Cell.size / 2);
                        g.drawLine(human.loc.x + Cell.size / 2, human.loc.y + Cell.size / 2,
                        human.loc.x + Cell.size / 2, human.loc.y + Cell.size / 2 + Cell.size / 4);
                    }
                    
                    if ((human.prevLoc.x > human.loc.x) && (human.prevLoc.y < human.loc.y)){// diagonal left down
                        g.drawLine(human.loc.x + Cell.size / 2, human.loc.y + Cell.size / 2 - Cell.size / 4,
                        human.loc.x + Cell.size / 2, human.loc.y + Cell.size / 2);
                        g.drawLine(human.loc.x + Cell.size - Cell.size / 4, human.loc.y + Cell.size / 2,
                        human.loc.x + Cell.size / 2, human.loc.y + Cell.size / 2);
                    }

                    if ((human.prevLoc.x < human.loc.x) && (human.prevLoc.y < human.loc.y)){// diagonal right down
                        g.drawLine(human.loc.x + Cell.size / 4, human.loc.y + Cell.size / 2,
                        human.loc.x + Cell.size / 2, human.loc.y + Cell.size / 2);
                        g.drawLine(human.loc.x + Cell.size / 2, human.loc.y + Cell.size / 2,
                        human.loc.x + Cell.size / 2, human.loc.y + Cell.size / 2 - Cell.size / 4);
                    }

                    if ((human.prevLoc.x > human.loc.x) && (human.prevLoc.y > human.loc.y)){// diagonal left up
                        g.drawLine(human.loc.x + Cell.size / 2, human.loc.y + Cell.size / 2 + Cell.size / 4,
                        human.loc.x + Cell.size / 2, human.loc.y + Cell.size / 2);
                        g.drawLine(human.loc.x + Cell.size - Cell.size / 4, human.loc.y + Cell.size / 2,
                        human.loc.x + Cell.size / 2, human.loc.y + Cell.size / 2); 
                    }
                }
                
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
            humanList.setCellRenderer(new HumanListCellRenderer());
            humanList.setVisibleRowCount(20);
            sidePanel.add(new JScrollPane(humanList), BorderLayout.CENTER);
            this.add(sidePanel, BorderLayout.EAST);
            this.pack();
            this.setVisible(true);
        }  

    private static class HumanListCellRenderer extends DefaultListCellRenderer {
        @Override
        public Component getListCellRendererComponent(JList<?> list, Object value,
                int index, boolean isSelected, boolean cellHasFocus) {
            Human human = (Human) value;
            String status = human.isInfected() ? "Infected" : "Healthy";
            JLabel label = (JLabel) super.getListCellRendererComponent(list,
                    "Human " + (index + 1) + " - " + human.getAgeGroup()
                            + " - " + status,
                    index, isSelected, cellHasFocus);
            label.setIcon(new ColourIcon(human.color));
            label.setIconTextGap(8);
            return label;
        }
    }

    private static class ColourIcon implements Icon {
        private final Color colour;

        ColourIcon(Color colour) {
            this.colour = colour;
        }

        @Override
        public void paintIcon(Component component, Graphics graphics, int x, int y) {
            graphics.setColor(colour);
            graphics.fillRect(x, y, getIconWidth(), getIconHeight());
            graphics.setColor(Color.DARK_GRAY);
            graphics.drawRect(x, y, getIconWidth() - 1, getIconHeight() - 1);
        }

        @Override
        public int getIconWidth() {
            return 14;
        }

        @Override
        public int getIconHeight() {
            return 14;
        }
    }

}