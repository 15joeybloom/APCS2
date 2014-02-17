package zoo.src;

import java.awt.Color;
import java.util.*;

/**
Looks like a pac-man. Eats Ants. Overrides only a few of the AbstractAnimal methods.
@author Joey Bloom
*/
public class AntEater extends AbstractAnimal
{
    public static final int SMELL = -23;
    private Entity eatThis;

    /**
    Constructs a black, fully visible, AntEater at (0,0) of size 3 that smells
    like an AntEater.
    */
    public AntEater()
    {
        this(Color.black,3.0, 0.0, 0.0);
    }

    /**
    Constructs a fully visible AntEater that smells like an AntEater
    @param c Color
    @param siz size
    @param x horizontal coordinate
    @param y vertical coordinate
    */
    public AntEater(Color c, double siz, double x, double y)
    {
        this(SMELL, c, 1.0, siz, x, y);
    }

    /**
    Constructs an AntEater with the default AntEater image
    @param smell smell
    @param c Color
    @param vis visibility on the interval {@literal [0.0,1.0]}
    @param siz size
    @param x horizontal coordinate
    @param y vertical coordinate
    */
    public AntEater(int smell, Color c, double vis, double siz, double x, double y)
    {
        super(smell, c, vis, siz, x, y, "AntEater.png");
        moveSpeed = 1.1;//move speed is higher so AntEaters don't infinitely chase Ants
    }

    @Override
    public void act(Set<Entity> zoo, Set<Entity> changes)
    {
        super.act(zoo, changes);
        if(currentAction.getType().equals("eat"))
            changes.add(eatThis);
    }

    /**
    If there is a positively scored Entity
        if it is within 1.0
            eat it
        else move towards it
    Else idle
    @return {@inheritDoc }
    */
    @Override
    protected Action determineAction()
    {
        if(entities.isEmpty())
        {
            return new Action("idle", null);
        }
        Entity highestEntity = entities.peek();
        int highestScore = scores.get(highestEntity);
        if(highestScore > 0)
        {
            if(highestEntity.getDistanceFrom(getX(), getY()) <= 1.0)
            {
                return new Action("eat", highestEntity);
            }
            else
            {
                return new Action("move toward", highestEntity);
            }
        }
        else
        {
            return new Action("idle", null);
        }
    }

    /**
    The target of currentAction should be eaten
    */
    public void eat()
    {
        eatThis = currentAction.getTarget();
    }

    /**
    If it smells like an Ant, score it based on distance (up to 50 meters away) and visibility. Else score it 0.
    @param e {@inheritDoc}
    @return {@inheritDoc}
    */
    @Override
    protected int score(Entity e)
    {
        double eDistance = e.getDistanceFrom(getX(), getY());
        if(e.getSmell() == Ant.SMELL && eDistance <= 50.0)
        {
            return (int)(e.getVisibility() * Integer.MAX_VALUE / eDistance);
        }
        return 0;
    }

    /**
    TESTING TESTIGN TESTING
    */
    public static void main(String[] args)
    {
        AntEater antEater = new AntEater();
        Ant ant1 = new Ant(Color.black,25,0);
        Ant ant2 = new Ant(Color.black,24.999,0);//slightly closer, higher score
        Ant ant3 = new Ant(Color.black,50.0,0); //still within range
        Ant ant4 = new Ant(Color.black,50.00001,0); //just out of range, 0 score
        Ant ant5 = new Ant(Color.black,0.0,0);// Integer.MAX_VALUE

        int ant1Score = antEater.score(ant1);
        System.out.println("ant1Score = " + ant1Score);

        int ant2Score = antEater.score(ant2);
        System.out.println("ant2Score = " + ant2Score);

        int ant3Score = antEater.score(ant3);
        System.out.println("ant3Score = " + ant3Score);

        int ant4Score = antEater.score(ant4);
        System.out.println("ant4Score = " + ant4Score);

        int ant5Score = antEater.score(ant5);
        System.out.println("ant5Score = " + ant5Score);
    }
}