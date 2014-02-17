package fract;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

/**
 * @author Joey
 *
 */
public class CrossedSquareFrame extends JFrame
{
    public static void main(String[] args)
    {
        SwingUtilities.invokeLater(new Runnable(){
            @Override
            public void run()
            {
                new CrossedSquareFrame();
            }
        });
    }
    public CrossedSquareFrame()
    {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600,600);

        add(new CrossedSquarePanel());
        setVisible(true);
    }
}
