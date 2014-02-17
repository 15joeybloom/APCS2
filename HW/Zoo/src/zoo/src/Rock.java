package zoo.src;

import java.awt.Color;
import java.awt.Image;

/**
 A Rock is the most basic implementation of <code>Entity</code>; it does nothing,
 (because it is a rock) and it has a neutral smell.
 @author Joey Bloom
 */
public class Rock implements Entity
{
    private Color color;
    private double visibility;
    private double size;
    private double x;
    private double y;
    private Image image;

    /**
    Constructs a black, invisible, infinitesimally small Rock at (0,0)
    */
    public Rock()
    {
        this(Color.black, 0.0, 0.0, 0.0, 0.0);
    }
    /**
    Constructs a Rock
    @param c Color of the Rock
    @param vis visibility
    @param siz size
    @param x horizontal coordinate
    @param y vertical coordinate
    */
    public Rock(Color c, double vis, double siz, double x, double y)
    {
        color = c;
        visibility = vis;
        size = siz;
        this.x = x;
        this.y = y;
        image = Util.loadImage("Rock.png");
//        Util.showImage(image);
        image = Util.tintImage(image,color);
//        Util.showImage(image);
        image = Util.resizeImage(image, (int)(size * 10), (int)(size * 10));
//        Util.showImage(image);
    }
    /**
    Returns 0; Rocks are odorless
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
}
