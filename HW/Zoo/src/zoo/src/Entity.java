package zoo.src;

import java.awt.Color;
import java.awt.Image;

/**
 An Entity is some object or thing in the Zoo. They have certain properties that
 can be fetched by these methods.
 <p/>
 @author Joey Bloom
 */
public interface Entity
{
    /**
    Returns this Entity's smell as an int. negative smells are offensive
    or threatening, positive smells are pleasant or inviting. Zero smells are
    odorless and ignored.
    @return
    */
    int getSmell();

    /**
    Returns the Entity's Color
    @return the Color
    */
    Color getColor();

    /**
    Returns the visibility of the Entity. 0 means totally hidden,
    1 means completely visible.
    @return visibility, which should be on the interval {@literal [0,1]}
    */
    double getVisibility();

    /**
    Returns the size of the Entity
    @return size
    */
    double getSize();

    /**
    Returns the horizontal coordinate of the Entity
    @return x
    */
    double getX();

    /**
    Returns the vertical coordinate of the Entity
    @return y
    */
    double getY();

    /**
     Returns the distance in meters from this Entity to the specified coordinates
     <p/>
     @param x horizontal coordinate in meters
     @param y vertical coordinate in meters
     <p/>
     @return Math.hypot(getX()-x,getY()-y)
     */
    double getDistanceFrom(double x, double y);

    /**
     Returns an Image of this Entity
     @return an image of this Entity
    */
    Image getImage();
}