package zoo.src;

import java.awt.Color;
import java.util.*;

/**
 <p/>
 @author 151bloomj
 */
public class Action
{
    /**
     {@code startTime &lt 0} before {@link zoo.Action#start() start()} is called
     */
    private long startTime = -1L;
    private final String type;
//    private final AbstractAnimal actor;
    private final Entity target;


//<editor-fold defaultstate="collapsed" desc="Constructor with actor argument">
//    /**
//    Constructs an Action object.
//    @param actor the actor that will perform this action
//    @param type the type of this action
//    @param target the target of this action
//    */
//    public Action(final AbstractAnimal actor, final String type, final Entity target)
//    {
//        this.actor = actor;
//        this.type = type;
//        this.target = target;
//    }
//</editor-fold>

    /**
     Constructs an Action object.
     <p>
     @param type
     @param target
     */
    public Action(final String type, final Entity target)
    {
//        this.actor = actor;
        this.type = type;
        this.target = target;
    }

    /**
     Begins the Action. This method is only effective the first time it is
     called on an Action; subsequent method calls have no effect.
     */
    public void start()
    {
        if(startTime < 0)
            startTime = System.currentTimeMillis();
    }

    /**
     Returns the time elapsed in milliseconds since this Action began
     <p>
     @return System.currentTimeMillis() - startTime
     */
    public long getTimeElapsed()
    {
        return startTime;
    }

    /**
     Returns the type of this Action
     <p>
     @return
     */
    public String getType()
    {
        return type;
    }

//    /**
//    Returns the actor of this Action
//    @return the AbstractAnimal that is acting
//    */
//    public AbstractAnimal getActor()
//    {
//        return actor;
//    }
    /**
     Returns the target of this Action
     <p>
     @return the Entity which is being Acted upon
     */
    public Entity getTarget()
    {
        return target;
    }

    /**
     {@inheritDoc }
     <p>
     @return {@inheritDoc}
     */
    @Override
    public int hashCode()
    {
        int hash = 3;
        hash = 83 * hash + Objects.hashCode(this.type);
//        hash = 83 * hash + Objects.hashCode(this.actor);
        hash = 83 * hash + Objects.hashCode(this.target);
        return hash;
    }

    /**
     Returns true if {@code obj} is of the same action type as this
     object and if this Action has the same actor and target as obj
     <p>
     @param obj
     @return
     */
    @Override
    public boolean equals(Object obj)
    {
        if(obj == null)
        {
            return false;
        }
        if(getClass() != obj.getClass())
        {
            return false;
        }
        final Action other = (Action) obj;
        return type.equals(other.type) && this.target == other.target;
    }

    /**
     TESTING TESTING TESTING TESTING
     */
    public static void main(String[] args)
    {
//        //Test #1
//        Ant ant1 = new Ant(0,Color.black, 1, 0, 0);
//        Rock rock1 = new Rock(Color.black, .1, 5, 20, 30);
//        Rock rock2 = new Rock(Color.cyan, .6, 25, -20, -30);
//        //the ant is equidistant from the 2 rocks, but they're both out of range

//        //Test #2
//        Ant ant1 = new Ant(0,Color.black, 1, 0, 0);
//        Rock rock1 = new Rock(Color.black, .1, 5, 1.0, 0);
//        Rock rock2 = new Rock(Color.cyan, .6, 25, 2.0, 0);

        //Test #3
        Ant ant1 = new Ant(0,Color.black, 1, 0, 0);
        Rock rock1 = new Rock(Color.black, .1, 5, 1.0, 0);
        Rock rock2 = new Rock(Color.cyan, .6, 25, -1.0, 0);


        System.out.println("before act: ");
        System.out.println("ant1.getX() = " + ant1.getX());
        System.out.println("ant1.getY() = " + ant1.getY());
        System.out.println("rock1Score = " + ant1.score(rock1));
        System.out.println("rock2Score = " + ant1.score(rock2));

        Set<Entity> zoo = new HashSet<>();
        zoo.add(ant1);
        zoo.add(rock1);
        zoo.add(rock2);

        for(int i = 1; i <= 3; i++)
        {
            ant1.act(zoo, null);
            System.out.println("after act" + i + ": ");
            System.out.println("ant1.getX() = " + ant1.getX());
            System.out.println("ant1.getY() = " + ant1.getY());
            System.out.println("ant1.score(rock1) = " + ant1.score(rock1));
            System.out.println("ant1.score(rock2) = " + ant1.score(rock2));
        }
    }
}
/*
Testing with 2 Rocks and an Ant

Test #1:
2 Rocks are further than 1 meter away from Ant so the Ant can't detect them.
The Ant idles, moving randomly up to 1 meter in any direction.

Output:
before act:
ant1.getX() = 0.0
ant1.getY() = 0.0
rock1Score = 0
rock2Score = 0
after act1:
ant1.getX() = -0.7776678243384619
ant1.getY() = -0.4781420793054321
ant1.score(rock1) = 0
ant1.score(rock2) = 0
after act2:
ant1.getX() = -0.8392308843606274
ant1.getY() = 0.0626860260636361
ant1.score(rock1) = 0
ant1.score(rock2) = 0
after act3:
ant1.getX() = -0.7688970908192743
ant1.getY() = -0.41638308184814526
ant1.score(rock1) = 0
ant1.score(rock2) = 0
after act4:
ant1.getX() = -0.7228521827815528
ant1.getY() = 0.4285275877822397
ant1.score(rock1) = 0
ant1.score(rock2) = 0
after act5:
ant1.getX() = -0.7943821518016158
ant1.getY() = 0.5756828856277598
ant1.score(rock1) = 0
ant1.score(rock2) = 0

Test #2:
rock1 is exactly 1 meter away from ant1. ant1 is able
to detect and score rock1, but not rock2. ant1 moves toward
rock1, and once it moves all the way toward rock1, then rock2
is within range but ant1 stays moving toward rock1 because
ant1 is so close to rock1 that rock1's score is very high

Output:
before act:
ant1.getX() = 0.0
ant1.getY() = 0.0
rock1Score = 1
rock2Score = 0
after act1:
ant1.getX() = 1.0
ant1.getY() = 0.0
ant1.score(rock1) = 2147483647
ant1.score(rock2) = 30
after act2:
ant1.getX() = 1.0
ant1.getY() = 0.0
ant1.score(rock1) = 2147483647
ant1.score(rock2) = 30
after act3:
ant1.getX() = 1.0
ant1.getY() = 0.0
ant1.score(rock1) = 2147483647
ant1.score(rock2) = 30
after act4:
ant1.getX() = 1.0
ant1.getY() = 0.0
ant1.score(rock1) = 2147483647
ant1.score(rock2) = 30
after act5:
ant1.getX() = 1.0
ant1.getY() = 0.0
ant1.score(rock1) = 2147483647
ant1.score(rock2) = 30
after act6:
ant1.getX() = 1.0
ant1.getY() = 0.0
ant1.score(rock1) = 2147483647
ant1.score(rock2) = 30
after act7:
ant1.getX() = 1.0
ant1.getY() = 0.0
ant1.score(rock1) = 2147483647
ant1.score(rock2) = 30
after act8:
ant1.getX() = 1.0
ant1.getY() = 0.0
ant1.score(rock1) = 2147483647
ant1.score(rock2) = 30
after act9:
ant1.getX() = 1.0
ant1.getY() = 0.0
ant1.score(rock1) = 2147483647
ant1.score(rock2) = 30
after act10:
ant1.getX() = 1.0
ant1.getY() = 0.0
ant1.score(rock1) = 2147483647
ant1.score(rock2) = 30

Test #3:
Once again the ant is exactly halfway between the rocks, but
this time both rocks are within range. the ant chooses rock2 because
its score is higher.

Output:
before act:
ant1.getX() = 0.0
ant1.getY() = 0.0
rock1Score = 1
rock2Score = 30
after act1:
ant1.getX() = -1.0
ant1.getY() = 1.2246467991473532E-16
ant1.score(rock1) = 0
ant1.score(rock2) = 2147483647
after act2:
ant1.getX() = -1.0
ant1.getY() = 0.0
ant1.score(rock1) = 0
ant1.score(rock2) = 2147483647
after act3:
ant1.getX() = -1.0
ant1.getY() = 0.0
ant1.score(rock1) = 0
ant1.score(rock2) = 2147483647
*/
