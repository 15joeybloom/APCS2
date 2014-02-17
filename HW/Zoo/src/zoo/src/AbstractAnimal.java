package zoo.src;

import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

/**
 AbstactAnimal is the superclass for all zoo animals. It provides
 implementations
 <p/>
 @author Joey Bloom
 */
public abstract class AbstractAnimal implements Actor
{
    private int smell;
    private Color color;
    private double visibility;
    private double size;
    private double x;
    private double y;

    protected double moveSpeed = 1.0;//number of meters per step

    protected PriorityQueue<Entity> entities;
    protected Map<Entity, Integer> scores;

    protected Action currentAction;

    private Image image;

    /**
     Constructs an odorless, black, fully visible
     AbstractAnimal at (0,0) with size 1
     */
    public AbstractAnimal()
    {
        this(0, Color.black, 1.0, 1.0, 0.0, 0.0);
    }

    /**
     Constructs an odorless, fully visible AbstractAnimal
     <p>
     @param c   Color of the AbstractAnimal
     @param siz size
     @param x   horizontal coordinate
     @param y   vertical coordinate
     */
    public AbstractAnimal(Color c, double siz, double x, double y)
    {
        this(0, c, 1.0, siz, x, y);
    }

    /**
     Constructs an AbstractAnimal
     <p>
     @param smell smell
     @param c     Color of the AbstractAnimal
     @param vis   visibility
     @param siz   size
     @param x     horizontal coordinate
     @param y     vertical coordinate
     */
    public AbstractAnimal(int smell, Color c, double vis, double siz, double x, double y)
    {
        this(smell, c, vis, siz, x, y, "AbstractAnimal.png");
    }

    /**
     Constructs an AbstractAnimal<p>
     @param smell     smell
     @param c         Color of the AbstractAnimal
     @param vis       visibility
     @param siz       size
     @param x         horizontal coordinate
     @param y         vertical coordinate
     @param imageName the name of the image, e.g. "AbstractAnimal.png"
     */
    public AbstractAnimal(int smell, Color c, double vis, double siz, double x, double y, String imageName)
    {
        this.smell = smell;
        color = c;
        visibility = vis;
        size = siz;
        this.x = x;
        this.y = y;
        scores = new HashMap<>();
        entities = new PriorityQueue<>(10, new Comparator<Entity>()
        {
            @Override
            public int compare(Entity e1, Entity e2)
            {
                return Integer.compare(scores.get(e2), scores.get(e1));
                //descending order, highest scores first
            }
        });
        image = Util.loadImage(imageName);
        image = Util.tintImage(image, color);
        image = Util.resizeImage(image, (int) (size * 10), (int) (size * 10));
    }

    /**
     Animals evaluate entities by “scoring” them based on their properties.
     Small, distant, entities with a low smell value and a low visibility
     value (meaning they are well camouflaged, or otherwise hidden from view)
     and a {@link java.awt.Color Color} with a low saturation value
     will be scored relatively low, and large, close, smelly, obviously
     visible entities with a highly saturated Color will be scored high.
     Low scored entities will be
     ignored by the animal, while high scored entities will cause the animal
     to take some action, whether it is running, hiding, eating, playing,
     or other action.
     <p>
     @param e {@literal Entity} to be scored
     @return an integer value representing this animal's attraction or repulsion
             to e
     */
    protected int score(Entity e)
    {
        int eSmell = e.getSmell();
        Color eColor = e.getColor();
        double eSaturation = Color.RGBtoHSB(eColor.getRed(), eColor.getGreen(), eColor.getBlue(), null)[1]; //get saturation value
        eSaturation++;  //if the saturation is 0,
        //then the score will be multiplied by 1 and so
        //saturation will be ignored. if saturation is high,
        //score will be multiplied by 2.
        double eVisibility = e.getVisibility();
        double eSize = e.getSize() / (size == 0 ? 1 : size); //divide by animal's own size so that Entity's relative size is used in calculation.
        double eDistance = e.getDistanceFrom(x, y);

        int score = (int) Math.ceil((eSmell == 0 ? 1 : eSmell) * eSaturation
            * eVisibility * eSize / eDistance);
        //divide by distance so that more distant Entities lower the score because they are less important

        //smell is the only one
        //of these that can be negative, so it determines the sign of the score and
        //thus determines whether e is attractive or repulsive. if smell is 0,
        //make it 1 so that it has no effect on the multiplication and is thus
        //ignored. This means that if an animal sees an Entity but can't smell it,
        //it will be naturally curious and be attracted to it.
        scores.put(e, score);
        return score;
    }

    /**
     Determines what Action this animal should take given its surroundings. This
     basic implementation determines that the animal should idle if
     there are no other Entities, or else the animal will move towards
     the entity with the highest score if there exists an entity with positive
     score or move away from the entity with the lowest score if there are no
     entities
     with positive scores
     the lowest score
     <p>
     @return the Action this animal should take
     */
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
            return new Action("move toward", highestEntity);
        }
        else
        {
            //TODO find more efficient way of getting lowest element. Idea: two priority queues with opposite comparators.
            while(entities.size() > 1)
            {
                entities.remove();
            }
            Entity lowestEntity = entities.remove();
            if(scores.get(lowestEntity) < 0)
            {
                return new Action("move away", lowestEntity);
            }
            else //if all of the scores are 0
            {
                return new Action("idle", null);
            }
        }
    }

    /**
     Causes the animal to take a step in its action.

     Subclasses which provide methods to execute actions need not override this
     method if the methods that execute actions have the same name as their
    respective action types and take no arguments.
     */
    protected void takeAction()
    {
//        Entity target = currentAction.getTarget();
        String type = currentAction.getType();
        switch(type)
        {
            case "move toward":
                moveToward();
                break;
            case "move away":
                moveAway();
                break;
            default:
                //if the action type is some other action, then try
                //to call the method with the same name as the
                //action type
                Method actionMethod;
                try
                {
                    actionMethod = getClass().getMethod(type);
                    actionMethod.invoke(this);
                }
                catch(NoSuchMethodException | SecurityException |
                      IllegalAccessException | IllegalArgumentException |
                      InvocationTargetException ex)
                {
                    ex.printStackTrace();
                }

        }
    }

    /**
     Moves up to moveSpeed meters in the direction of the target of the current action
     <p>
     */
    protected void moveToward()
    {
        Entity target = currentAction.getTarget();
        //calculate the angle at which to move
        double deltaX = target.getX() - x;
        double deltaY = target.getY() - y;
        double theta
            = Math.atan2(deltaY, deltaX);
        if(Double.isNaN(theta))//if you're already in a good spot, don't move
            return;
        double distanceToMove = Math.min(target.getDistanceFrom(x, y), moveSpeed);//move up to 1 meter
        x += distanceToMove * Math.cos(theta);
        y += distanceToMove * Math.sin(theta);
    }

    /**
     Moves 1 meter in the opposite direction of the target of the current action
     <p>
     */
    protected void moveAway()
    {
        Entity target = currentAction.getTarget();
        //calculate the angle at which to move
        double deltaX = target.getX() - x;
        double deltaY = target.getY() - y;
        double theta
            = Math.atan2(deltaX, deltaY);
        if(Double.isNaN(theta))//if you're already in a good spot, don't move
            return;
        x -= Math.cos(theta);
        y -= Math.sin(theta);
    }

    /**
     Causes this animal to move a random distance up to 1 meter in a random
     direction
     */
    public void idle()
    {
        double distance = Math.random();
        double direction = Math.random() * Math.PI * 2; // 0 <= direction < 2pi
        x += distance * Math.cos(direction);
        y += distance * Math.sin(direction);
    }

    /**
     Sets {@code currentAction} to {@code action} if
     {@code action} has a different action type, actor,
     or target than {@code currentAction}.
     <p>
     @param action Action that this animal should be taking
     */
    protected void setAction(Action action)
    {
        if(currentAction == null || !currentAction.equals(action))
        //if need to take a different Action
        {
            currentAction = action;
            currentAction.start();
        }
    }

    @Override
    public void act(final Set<Entity> zoo, Set<Entity> changes)
    {
        scores.clear();
        entities.clear();
        synchronized(zoo)
        {
            for(Entity e : zoo)
            {
                if(e != this)//don't score yourself
                {
                    scores.put(e, score(e));
                    entities.add(e);
                }
            }
        }
        setAction(determineAction());
        takeAction();
    }

    @Override
    public int getSmell()
    {
        return smell;
    }

    @Override
    public Color getColor()
    {
        return color;
    }

    @Override
    public double getVisibility()
    {
        return visibility;
    }

    @Override
    public double getSize()
    {
        return size;
    }

    @Override
    public double getX()
    {
        return x;
    }

    @Override
    public double getY()
    {
        return y;
    }

    @Override
    public double getDistanceFrom(double x, double y)
    {
        return Math.hypot(this.x - x, this.y - y);
    }

    @Override
    public Image getImage()
    {
        return image;
    }

    protected void setImage(BufferedImage img)
    {
        image = img;
    }
}
