package fract;

/**
 * @author Joey
 *
 * Degrees are used for all angle measures
 */
public class PolarPoint implements Comparable<PolarPoint>
{
    private final double r;
    private final double theta;

    public PolarPoint(double r, double theta)
    {
        this.r = r;
        this.theta = theta;
    }
    public PolarPoint()
    {
        this(0,0);
    }

    public double getR()
    {
        return r;
    }

    public double getTheta()
    {
        return theta;
    }

    @Override
    public boolean equals(Object o)
    {
        return o instanceof PolarPoint && hashCode() == o.hashCode();
    }

    @Override
    public int hashCode()
    {
        double rad = r;
        double thet = theta%360;
        if(rad < 0)
        {
            rad = -rad;
            thet = (thet+180)%360;
        }
        int hash = 3;
        hash = 53 * hash + (int) (Double.doubleToLongBits(rad) ^ (Double.doubleToLongBits(rad) >>> 32));
        hash = 53 * hash + (int) (Double.doubleToLongBits(thet) ^ (Double.doubleToLongBits(thet) >>> 32));
        return hash;
    }

    /**
     * Consistent with equals. Otherwise, compares them based on theta, then r.
     * @param o
     * @return
     */
    @Override
    public int compareTo(PolarPoint o)
    {
        if(equals(o))
        {
            return 0;
        }
        double x = getTheta()%360 - o.getTheta()%360;
        if(x == 0)
        {
            return (int)(getR() - o.getR());
        }
        return (int)x;
    }

    @Override
    public String toString()
    {
        return "(" + r + ", " + theta + ")";
    }
}
