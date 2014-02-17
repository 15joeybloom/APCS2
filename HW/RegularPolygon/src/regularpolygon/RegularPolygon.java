package regularpolygon;

import TurtleGraphics.Pen;
import TurtleGraphics.RainbowPen;

/**
 * Assignment #2
 * A RegularPolygon has a side length and a number of sides. Each side
 * is the same length and each internal vertex angle has the same measure.
 * @author Joey Bloom
 */
public class RegularPolygon
{
    private int myNumSides;
    private double mySideLength;
    private double myR;
    private double myr;


    /**
     * Constructs a regular triangle with side length 1
     */
    public RegularPolygon()
    {
        this(3,1);
    }

    /**
     * Constructs a regular polygon with a number of sides and a side length
     * @param numSides number of sides
     * @param sideLength side length
     */
    public RegularPolygon(int numSides, double sideLength)
    {
        myNumSides = numSides;
        mySideLength = sideLength;
        calcr();
        calcR();
    }

    private void calcr()
    {
        myr = 0.5 * mySideLength / Math.tan(Math.PI/myNumSides);
    }
    private void calcR()
    {
        myR = 0.5 * mySideLength / Math.sin(Math.PI/myNumSides);
    }

    /**
     * Returns the measure of the internal angle of each vertex of the regular polygon
     * @return the angle measure in degrees
     */
    public double vertexAngle()
    {
        return (myNumSides - 2) / (double) myNumSides * 180;
    }
    /**
     * Returns the perimeter of the regular polygon
     * @return the sum of the length of all the sides
     */
    public double Perimeter()
    {
        return myNumSides * mySideLength;
    }
    /**
     * Returns the area of the regular polygon
     * @return area enclosed by the polygon
     */
    public double Area()
    {
        return 0.5 * myNumSides * myR * myR * Math.sin(2 * Math.PI /myNumSides);
    }
    /**
     * Returns the number of sides on the polygon
     * @return
     */
    public int getNumSide()
    {
        return myNumSides;
    }
    /**
     * Returns the side length of each side of the polygon
     * @return
     */
    public double getSideLength()
    {
        return mySideLength;
    }
    /**
     * Returns the circumference of the circumscribing circle of the polygon
     * @return R
     */
    public double getR()
    {
        return myR;
    }
    /**
     * Returns the circumference of the inscribing circle of the polygon
     * @return r
     */
    public double getr()
    {
        return myr;
    }

    /**
     * Returns a String representation of the RegularPolygon
     * @return a String containing the number of sides and the side length
     */
    @Override
    public String toString()
    {
        return "Polygon" +
               "\nNumber of Sides: " + getNumSide() +
               "\nSide Length: " + getSideLength();
    }

    /**
     * Uses TurtleGraphics to draw the polygon with a RainbowPen.
     */
    public void draw()
    {
        Pen pen = new RainbowPen();

        pen.down();
        pen.home();

        double angle = 180 - vertexAngle();
        double move = mySideLength * 5;

        for(int i = 0; i < myNumSides; i++)
        {
            pen.move(move);
            pen.turn(angle);
        }
    }
}
