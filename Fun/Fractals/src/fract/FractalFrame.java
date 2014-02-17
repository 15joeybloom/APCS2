package fract;

import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.lang.reflect.InvocationTargetException;
import java.util.*;
import javax.swing.*;

/**
 * @author Joey
 *
 */
public class FractalFrame extends JFrame
{
    public static void main(String[] args) throws InvocationTargetException, InterruptedException
    {
        SwingUtilities.invokeAndWait(new Runnable(){
            @Override
            public void run()
            {
                new FractalFrame();
            }
        });
    }
    private ArrayList<Point2D.Double> stairPoints;
    private JMenuBar bar;
    private JMenu fractal;
        private JRadioButtonMenuItem stair;
        private JRadioButtonMenuItem crossedSquare;
        private JRadioButtonMenuItem lineTree;
        private ButtonGroup group;
    private JMenu options;
        private JMenuItem clear;
        private JMenuItem draw;
    private Fractal panel;

    public FractalFrame()
    {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600,600);
        setResizable(false);
        setTitle("Click to drop points!");
        setLayout(null);

        bar = new JMenuBar();
        setJMenuBar(bar);
        setUpFractalMenu();
        setUpOptionsMenu();

        setVisible(true);
    }

    private void setUpFractalMenu()
    {
        fractal = new JMenu("Fractal");
            group = new ButtonGroup();

            stair = new JRadioButtonMenuItem("Stair Spiral");
            group.add(stair);
            stair.addActionListener(new ActionListener(){

                @Override
                public void actionPerformed(ActionEvent e)
                {
                    stairPoints = new ArrayList<>();
                    stairPoints.add(new Point2D.Double(0,200));
                    stairPoints.add(new Point2D.Double(200,0));
                    stairPoints.add(new Point2D.Double(400,200));
                    stairPoints.add(new Point2D.Double(200,400));

                    if(panel != null)remove(panel);
                    panel = new StairSpiralPanel(stairPoints);
                    add(panel);

                    panel.addMouseListener(new MouseListener(){
                        @Override
                        public void mouseClicked(MouseEvent e)
                        {
                            int x = e.getX();
                            int y = e.getY();
                            stairPoints.add(new Point2D.Double(x,e.getY()));
                            Graphics2D g = (Graphics2D) panel.getGraphics();
                            g.draw(new Ellipse2D.Double(x-2,y-2,4,4));
                        }
                        @Override public void mousePressed(MouseEvent e){}
                        @Override public void mouseReleased(MouseEvent e){}
                        @Override public void mouseEntered(MouseEvent e){}
                        @Override public void mouseExited(MouseEvent e){}
                    });
                }
            });

            crossedSquare = new JRadioButtonMenuItem("Crossed Square");
            group.add(crossedSquare);
            crossedSquare.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    if(panel != null) remove(panel);
                    panel = new CrossedSquarePanel();
                    panel.setSize(getContentPane().getWidth(),getContentPane().getHeight());
                    add(panel);
                    repaint();
                }
            });

            lineTree = new JRadioButtonMenuItem("Line Tree");
            group.add(lineTree);
            lineTree.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    if(panel != null) remove(panel);
                    panel = new LineTreePanel();
                    panel.setSize(getContentPane().getWidth(),getContentPane().getHeight());
                    add(panel);
                    repaint();
                }
            });

            fractal.add(stair);
            fractal.add(crossedSquare);
            bar.add(fractal);
    }

    private void setUpOptionsMenu()
    {
        options = new JMenu("Options");
        bar.add(options);
        clear = new JMenuItem("Clear");
        clear.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ev)
            {
                if(panel instanceof StairSpiralPanel) stairPoints.clear();
                panel.getGraphics().clearRect(0, 0, panel.getWidth(), panel.getHeight());
            }
        });
        options.add(clear);
        draw = new JMenuItem("Draw");
        draw.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ev)
            {
                panel.renderFractal();
            }
        });
        options.add(draw);
    }
}
