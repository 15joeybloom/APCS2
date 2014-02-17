package zoo.src;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.util.*;

/**
 This Actor doesn't score other Entities; it just spawns
 a new Entity of type T at a random location within 5 meters every 5
 seconds. Looks like the Entity that it spawns, with a swirly/portal thingy behind it.
 <p/>
 @author 151bloomj
 * @param <T> the type of Entity that the Spawner spawns
 */
public class Spawner<T extends Entity> implements Actor
{
    private Color color;
    private double size;
    private double x;
    private double y;
    private Image image;
    private static Map<Class<? extends Entity>, Image> imageCache = new HashMap<>();
    private double spawnDistance = 5.0; //radius of the circle within which to spawn
    private int spawnMillis = 5000; //number of milliseconds between spawns
    private Action action = new Action("spawn", null);
    private Class<T> tClass;

    /**
     Constructs an invisible, odorless Spawner
     <p>
     @param c   Color of the Spawner
     @param siz size of the Spawner
     @param x   x coordinate of the Spawner
     @param y   y coordinate of the Spawner
     @param cl  The Class of Entity that the Spawner should spawn, e.g.
                Ant.class, Rock.class
     */
    public Spawner(Color c, double siz, double x, double y, Class<T> cl)
    {
        color = c;
        size = siz;
        this.x = x;
        this.y = y;
        tClass = cl;
        if(imageCache.containsKey(tClass))
            image = imageCache.get(tClass);
        else
        //this is the code that superimposes an
        //Entity image over the portal/twisty/spiral thing
        {
            image = Util.loadImage("Spawner.png");
            Graphics2D g2 = (Graphics2D) image.getGraphics();
            g2.setComposite(AlphaComposite.SrcOver);
            g2.drawImage(Util.loadImage(tClass.getSimpleName() + ".png"), 0, 0, null);
            imageCache.put(tClass, image);
        }
        image = Util.tintImage(image, color);
        image = Util.resizeImage(image, (int) Math.rint(size * 10), (int) Math.rint(size
            * 10));
        action.start();
    }
    /**
     http://stackoverflow.com/questions/2801267/java-class-object-from-type-variable
     <p>
     @return The Class object that would be equal to T.class if such syntax
             existed
     */
    private Class<? extends Entity> getParameterClass()
    {
        return (Class<? extends Entity>) (((ParameterizedType) Spawner.class.getGenericSuperclass()).getActualTypeArguments()[0]);
    }

    @Override
    public void act(Set<Entity> entities, Set<Entity> changes)
    {
        if(action.getTimeElapsed() >= spawnMillis)
        {
            changes.add(spawn());
        }
    }
    public Entity spawn()
    {
        Constructor<?>[] constructors = tClass.getConstructors();
        Constructor<?> bestConstructor = null;
        int constructorRank = 4;
        //try to find the T(Color, double, double, double) Constructor, constructorRank 0.
        //next best is T(Color, double, double), constructorRank 1.
        //next best is T(double, double), constructorRank 2
        //next best is T() Constructor, constructorRank 3.
        findConstructor:
        for(Constructor<?> constructor : constructors)
        {
            Class<?>[] params = constructor.getParameterTypes();
            if(params.length == 4 && params[0].equals(Color.class) &&
                params[1].equals(double.class) && params[2].equals(double.class) &&
                params[3].equals(double.class))//if found best Constructor, immediately call it.
            {
                bestConstructor = constructor;
                break findConstructor;
            }
            else if(params.length == 3 && params[0].equals(Color.class) &&
                params[1].equals(double.class) && params[2].equals(double.class) &&
                constructorRank > 1)
            {
                bestConstructor = constructor;
                constructorRank = 1;
            }
            else if(params.length == 2 && params[0].equals(double.class) &&
                params[1].equals(double.class) && constructorRank > 2)
            {
                bestConstructor = constructor;
                constructorRank = 2;
            }
            else if(params.length == 0 && constructorRank > 3)
            {
                bestConstructor = constructor;
                constructorRank = 3;
            }
        }

        try
        {
            if(bestConstructor == null)
            {
                return tClass.newInstance();
            }
            else
            {
                double dist = Math.random() * spawnDistance;
                double angle = Math.random() * Math.PI * 2;
                double newX = x + dist * Math.cos(angle);
                double newY = y + dist * Math.sin(angle);
                switch(constructorRank)
                {
                    case 0:
                        return (Entity)bestConstructor.newInstance(color,size,newX,newY);
                    case 1:
                        return (Entity)bestConstructor.newInstance(color,newX,newY);
                    case 2:
                        return (Entity)bestConstructor.newInstance(newX,newY);
                    case 3:
                        return (Entity)bestConstructor.newInstance();
                }
            }
        }
        catch(InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex)
        {
            ex.printStackTrace();
        }
        return null;
    }
    /**
     Spawners are odorless
     <p>
     @return 0
     */
    @Override
    public int getSmell()
    {
        return 0;
    }

    @Override
    public Color getColor()
    {
        return color;
    }

    /**
     Spawners are invisible
     <p>
     @return 0
     */
    @Override
    public double getVisibility()
    {
        return 0.0;
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

    /**
     Gives an image of the Entity that is spawned by this Spawner
     superimposed over a portal-twisty-spiral type thing
     <p>
     @return image of this spawner
     */
    @Override
    public Image getImage()
    {
        return image;
    }
}
