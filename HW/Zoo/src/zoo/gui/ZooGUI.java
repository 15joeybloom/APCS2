package zoo.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.util.*;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JPanel;
import zoo.src.*;

/**
 Displays the Graphical User Interface for the zoo, showing
 all of the animals.
 <p/>
 @author Joey Bloom
 */
public class ZooGUI extends JPanel
{
    private final Set<Entity> entities;
    private double scale = 20;
    /**
     the x offset from the top left corner of the panel of the
     origin of the zoo.
     */
    private int originX = 250;
    /**
     the y offset from the top left corner of the panel of the
    origin of the zoo
    */
    private int originY = 250;

    /**
     Constructs a ZooGUI. This is where I have hardcoded the initial Entities.
     */
    public ZooGUI()
    {
        entities = Collections.synchronizedSet(new HashSet<Entity>());
//        entities.add(new Ant(23, Color.blue, 1.0, 3.0, -3.0));
//        entities.add(new Ant(23, Color.red, 1.0, -3.0, 3.0));
//        entities.add(new Ant(23, Color.green, 1.0, -3.0, -3.0));
//        entities.add(new Ant(23, Color.yellow, 1.0, 3.0, 3.0));
        entities.add(new Rock(Color.magenta, 1.0, 20, 0, 0));
        entities.add(new Spawner<>(Color.cyan,10,5,5,Ant.class));
        entities.add(new AntEater());
    }

    /**
     Steps in the acting of the zoo
     */
    public void step()
    {
        Set<Entity> changes = Collections.synchronizedSet(new HashSet<Entity>());
        synchronized(entities)
        {
            for(Entity e : entities)
            {
                if(e instanceof Actor)
                {
                    ((Actor) e).act(entities, changes);
                }
            }
            /*
            Because you can't add or remove while iterating over a set,
            we need to add and remove here.
            */
            for(Entity e : changes)
            {
                if(entities.contains(e))
                {
                    entities.remove(e);
                }
                else
                {
                    entities.add(e);
                }
            }
            repaint();
        }
    }

    /**
     Draws all of the Entities in {@code entities}
     <p>
     @param g
     */
    @Override
    protected void paintComponent(Graphics g)
    {
        Graphics2D panelG2 = (Graphics2D) g;
        panelG2.clearRect(0, 0, getWidth(), getHeight());
        synchronized(entities)
        {
            for(Entity e : entities)
            {
                Image eImage = e.getImage();
                panelG2.drawImage(
                    eImage,
                    //the following math makes the coordinates of
                    //Entities at the center of their images
                    (int) Math.rint(e.getX() * scale + originX - eImage.getWidth(null)/2),
                    (int) Math.rint(-e.getY() * scale + originY - eImage.getHeight(null)/2),
                    null);
            }
        }
    }

    public static void main(String[] args)
    {
//        SwingUtilities.invokeLater(new Runnable(){
//            @Override
//            public void run()
//            {
                try
                {
                    JFrame frame = new JFrame();

                    //prompt for name?
                    frame.setTitle("Zoo");
                    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    frame.setSize(500, 500);

                    ZooGUI panel = new ZooGUI();
                    frame.add(panel);

                    frame.setVisible(true);

                    Thread.sleep(1000);
                    while(true)
                    {
                        panel.step();
                        //Thread.sleep(5);
                    }
                }
                catch(InterruptedException ex)
                {
                    Logger.getLogger(ZooGUI.class.getName()).log(Level.SEVERE, null, ex);
                }
//            }
//        });
    }
}
