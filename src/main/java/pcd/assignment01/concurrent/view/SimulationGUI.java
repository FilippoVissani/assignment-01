package pcd.assignment01.concurrent.view;

import pcd.assignment01.concurrent.util.Boundary;
import pcd.assignment01.concurrent.util.Point2D;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

/**
 * Graphical user interface of the simulation
 */
public class SimulationGUI extends JFrame{

    private final SimulationPanel simulationPanel;
    private static GraphicalView graphicalView;

    public SimulationGUI(final int width, final int height, final GraphicalView graphicalView){
        this.graphicalView = graphicalView;
        setTitle("Bodies Simulation");
        setSize(width, height);
        setResizable(false);
        JPanel mainPanel = new JPanel(new BorderLayout());
        this.simulationPanel = new SimulationPanel(width, height);
        this.simulationPanel.getInputMap().put(KeyStroke.getKeyStroke("s"), "DOWN");
        this.simulationPanel.getInputMap().put(KeyStroke.getKeyStroke("w"), "UP");
        this.simulationPanel.getActionMap().put("UP", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                simulationPanel.zoomIn();
                System.out.println("Activated UP");
            }
        });
        this.simulationPanel.getActionMap().put("DOWN", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                simulationPanel.zoomOut();
                System.out.println("Activated DOWN");
            }
        });
        ButtonsPanel buttonsPanel = new ButtonsPanel();
        mainPanel.add(this.simulationPanel, BorderLayout.CENTER);
        mainPanel.add(buttonsPanel, BorderLayout.PAGE_START);
        getContentPane().add(mainPanel);
        addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent ev){
                System.exit(-1);
            }
            public void windowClosed(WindowEvent ev){
                System.exit(-1);
            }
        });
        this.setVisible(true);
    }

    /**
     * @param bodiesPositions current position of the bodies
     * @param virtualTime actual virtual time
     * @param currentIteration current iteration
     * @param bounds of the simulation
     * Display an iteration of the simulation
     */
    public void display(final List<Point2D> bodiesPositions,
                        final double virtualTime,
                        final long currentIteration,
                        final Boundary bounds){
        try {
            SwingUtilities.invokeAndWait(() -> {
                simulationPanel.display(bodiesPositions, virtualTime, currentIteration, bounds);
                repaint();
            });
        } catch (Exception ignored) {}
    }

    public void updateScale(final double k) {
        simulationPanel.updateScale(k);
    }

    public static class ButtonsPanel extends JPanel {
        private final JButton buttonStart;
        private final JButton buttonStop;

        public ButtonsPanel() {
            this.buttonStart = new JButton("Start");
            this.buttonStop = new JButton("Stop");
            this.buttonStop.setEnabled(false);
            this.buttonStart.addActionListener(e -> {
                System.out.println("buttonStart pressed");
                ((JButton) e.getSource()).setEnabled(false);
                this.buttonStop.setEnabled(true);
                graphicalView.startSimulation();
            });
            this.buttonStop.addActionListener(e -> {
                System.out.println("buttonStop pressed");
                this.buttonStop.setEnabled(false);
            });
            this.add(buttonStart);
            this.add(buttonStop);
        }
    }

    public static class SimulationPanel extends JPanel {

        private List<Point2D> bodiesPositions;
        private Boundary bounds;
        private long currentIteration;
        private double virtualTime;
        private double scale = 1;
        private final long dx;
        private final long dy;

        public SimulationPanel(final int width, final int height){
            setSize(width,height);
            this.dx = width / 2 - 20;
            this.dy = height / 2 - 20;
            setFocusable(true);
            setFocusTraversalKeysEnabled(false);
            requestFocusInWindow();
        }

        @Override
        public void paint(Graphics g){
            if (bodiesPositions != null) {
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                        RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setRenderingHint(RenderingHints.KEY_RENDERING,
                        RenderingHints.VALUE_RENDER_QUALITY);
                g2.clearRect(0, 0, this.getWidth(), this.getHeight());


                int x0 = getXcoord(bounds.getX0());
                int y0 = getYcoord(bounds.getY0());

                int wd = getXcoord(bounds.getX1()) - x0;
                int ht = y0 - getYcoord(bounds.getY1());

                g2.drawRect(x0, y0 - ht, wd, ht);

                for (Point2D position : bodiesPositions){
                    int radius = (int) (10*scale);
                    if (radius < 1) {
                        radius = 1;
                    }
                    g2.drawOval(getXcoord(position.getX()),getYcoord(position.getY()), radius, radius);

                }
                String time = String.format("%.2f", virtualTime);
                g2.drawString("Bodies: " + bodiesPositions.size() + " - vt: " + time + " - nIter: " + currentIteration + " (UP for zoom in, DOWN for zoom out)", 2, 20);
            }
        }

        private int getXcoord(double x) {
            return (int)(dx + x * dx * scale);
        }

        private int getYcoord(double y) {
            return (int)(dy - y * dy * scale);
        }

        public void display(List<Point2D> bodiesPositions, double vt, long iter, Boundary bounds){
            this.bodiesPositions = bodiesPositions;
            this.bounds = bounds;
            this.virtualTime = vt;
            this.currentIteration = iter;
        }

        public void updateScale(double k) {
            scale *= k;
        }

        public void zoomIn(){
            scale *= 1.1;
        }

        public void zoomOut(){
            scale *= 0.9;
        }
    }
}
