package irs;

/**
 * Assignment #3
 * A TaxFile represents the taxes paid to the IRS in one year. A TaxFile
 * instance keeps track of the taxable income and filing status.
 * @author Joey Bloom
 */
public class TaxFile 
{
    private final boolean married;
    private final double income;
    
    /**
     * Constructs a TaxFile with filing status and taxable income
     * @param m true iff married filing jointly
     * @param in taxable income in dollars
     */
    public TaxFile(boolean m, double in)
    {
        married = m;
        income = in;
    }
    
    /**
     * Returns the income tax owed to the IRS
     * @return income tax according to 2001 laws
     */
    public double getTax()
    {
        double tax = 0;
        double[] percents = {0, .15, .275, .305, .355, .391};;
        double[] amts;
        if(!married)
        {
            amts = new double[]{0, 27050, 65550, 136750, 297350, Double.POSITIVE_INFINITY};
        }
        else
        {
            amts = new double[]{0, 45200, 109250, 166500, 297350, Double.POSITIVE_INFINITY};
        }
        for(int i = 1;; i++)
        {
            if(income < amts[i])
            {
                tax += (income - amts[i-1]) * percents[i];
                return tax;
            }
            else
            {
                tax += (amts[i]-amts[i-1]) * percents[i];
            }
        } 
    }
}
