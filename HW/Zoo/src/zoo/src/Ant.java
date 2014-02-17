package zoo.src;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;

/**
 <p>
 @author Joey Bloom
 */
public class Ant extends AbstractAnimal
{
    public static final int SMELL = 23;

    /**
     Constructs an Ant with size 2.0 because Ants are small
     <p>
     @param smell smell
     @param c     Color of the Ant
     @param vis   visibility
     @param x     horizontal coordinate
     @param y     vertical coordinate
     */
    public Ant(int smell, Color c, double vis, double x, double y)
    {
        super(smell, c, vis, 2.0, x, y, "Ant.png");
    }

    /**
     Constructs a fully visible Ant with size 2.0 that smells like
     an Ant.
     <p>
     @param c Color
     @param x horizontal coordinate
     @param y vertical coordinate
     */
    public Ant(Color c, double x, double y)
    {
        this(SMELL, c, 1.0, x, y);
    }

    /**
     Ants can only detect Entities within 1 meter. For Entities within 1 meter,
     distance is not used for scoring.
     <p>
     {@inheritDoc}
     <p>
     @param e {@inheritDoc}
     @return {@inheritDoc}
     */
    @Override
    protected int score(Entity e)
    {
        double eDistance = e.getDistanceFrom(getX(), getY());
        if(eDistance <= 1.0)
        {
            return (int) Math.rint(super.score(e) * eDistance);//multiply by distance to
            //negate the division by distance in the superclass implementation
        }
        return 0;
    }

    /*
     Testing!!! Testing!!! TESTING TESTING TESTING!!!!!!!!!!!!!!!!!
     */
    public static void main(String[] args)
    {
        Ant a = new Ant(0, Color.black, 1.0, 0, 0);
//
//        Rock r1 = new Rock(Color.orange, 1.0, 200, 1, 0);
//        int r1Score = a.score(r1);
//        System.out.println("r1Score (baseline) = " + r1Score);
//
//        Rock r2 = new Rock(Color.orange, 1.0, 200, 1.0, 0.001);
//        int r2Score = a.score(r2);
//        System.out.println("r2Score (slightly outside 1 meter away) = " + r2Score);
//
//        Rock r3 = new Rock(Color.black, 1.0, 200, 1, 0);
//        int r3Score = a.score(r3);
//        System.out.println("r3Score (lower color) = " + r3Score);
//
//        Rock r4 = new Rock(Color.orange, .1, 200, 1, 0);
//        int r4Score = a.score(r4);
//        System.out.println("r4Score (mostly hidden) = " + r4Score);
//
//        Rock r5 = new Rock(Color.orange, 1.0, 1, 1, 0);
//        int r5Score = a.score(r5);
//        System.out.println("r5Score (tiny pebble) = " + r5Score);
//
//        Ant a1 = new Ant(-9001, Color.black, 1.0, 1, 0);
//        int a1Score = a.score(a1);
////        System.out.println("a1Score (repulsively smelly Ant) = " + a1Score);
//
//        //testing to make sure Spawners are scored at 0
//        int score = a.score(new Spawner<>(Color.cyan,10,0,0,Ant.class));
//        System.out.println("score = " + score);
    }
}

/*
Output:
r1Score (baseline) = 400
r2Score (slightly outside 1 meter away) = 0
r3Score (lower color) = 200
r4Score (mostly hidden) = 40
r5Score (tiny pebble) = 2
a1Score (repulsively smelly Ant) = -9001
*/
