package exp;

/**
Tests the Operator class
Checkpoint 3
@author Joey Bloom
 */
public class OperatorTester
{
    public static void main(String[] args)
    {
        System.out.println(Operator.symbolMap.get("+").eval(3,4)); //7.0

        Operator plus1 = Operator.defineOperator("++",1,"- * 2.0 a 1");
        System.out.println(plus1.eval(8)); //15.0

        Operator reciprocal = Operator.defineOperator("1/",1,"/ 1 a");
        System.out.println(reciprocal.eval(0.25)); //4.0

        System.out.println(Operator.symbolMap.get("^").eval(4,3)); //64.0

        Operator rt2 = Operator.defineOperator("rt2",0,"sqrt 2");
        System.out.println(rt2.eval()); //1.414...

        System.out.println(reciprocal.eval(rt2.eval())); // 1/rt2 = 0.707...
    }
}