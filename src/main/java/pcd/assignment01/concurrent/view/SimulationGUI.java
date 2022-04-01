package pcd.assignment01.concurrent.view;

import pcd.assignment01.concurrent.util.Boundary;
import pcd.assignment01.concurrent.util.Point2D;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

/**
 * Graphical user interface of the simulation
 */
public class SimulationGUI extends JFrame{

    private final SimulationPanel panel;

    public SimulationGUI(int w, int h){
        setTitle("Bodies Simulation");
        setSize(w,h);
        setResizable(false);
        panel = new SimulationPanel(w,h);
        getContentPane().add(panel);
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
     * @param vt actual virtual time
     * @param iter current iteration
     * @param bounds of the simulation
     * Display an iteration of the simulation
     */
    public void display(List<Point2D> bodiesPositions, double vt, long iter, Boundary bounds){
        try {
            SwingUtilities.invokeAndWait(() -> {
                panel.display(bodiesPositions, vt, iter, bounds);
                repaint();
            });
        } catch (Exception ignored) {}
    }

    public void updateScale(double k) {
        panel.updateScale(k);
    }

    public static class SimulationPanel extends JPanel implements KeyListener {

        private List<Point2D> bodiesPositions;
        private Boundary bounds;
        private long nIter;
        private double vt;
        private double scale = 1;
        private final long dx;
        private final long dy;

        public SimulationPanel(int w, int h){
            setSize(w,h);
            dx = w/2 - 20;
            dy = h/2 - 20;
            this.addKeyListener(this);
            setFocusable(true);
            setFocusTraversalKeysEnabled(false);
            requestFocusInWindow();
        }

        public void paint(Graphics g){
            if (bodiesPositions != null) {
                Graphics2D g2 = (Graphics2D) g;

                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                        RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setRenderingHint(RenderingHints.KEY_RENDERING,
                        RenderingHints.VALUE_RENDER_QUALITY);
                g2.clearRect(0,0,this.getWidth(),this.getHeight());


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
                String time = String.format("%.2f", vt);
                g2.drawString("Bodies: " + bodiesPositions.size() + " - vt: " + time + " - nIter: " + nIter + " (UP for zoom in, DOWN for zoom out)", 2, 20);
            }
        }

        private int getXcoord(double x) {
            return (int)(dx + x*dx*scale);
        }

        private int getYcoord(double y) {
            return (int)(dy - y*dy*scale);
        }

        public void display(List<Point2D> bodiesPositions, double vt, long iter, Boundary bounds){
            this.bodiesPositions = bodiesPositions;
            this.bounds = bounds;
            this.vt = vt;
            this.nIter = iter;
        }

        public void updateScale(double k) {
            scale *= k;
        }

        @Override
        public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == 38){  		/* KEY UP */
                scale *= 1.1;
            } else if (e.getKeyCode() == 40){  	/* KEY DOWN */
                scale *= 0.9;
            }
        }

        public void keyReleased(KeyEvent e) {}
        public void keyTyped(KeyEvent e) {}
    }
}
