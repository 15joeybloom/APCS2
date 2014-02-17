package fract;

import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

/**
 *
 * @author Joey Bloom
 */
public class Fractal extends JPanel
{
    private boolean drawn;
    
    public void renderFractal()
    {
        drawn = true;
    }
    
    public void clear()
    {
        drawn = false;
        Graphics2D g2 = (Graphics2D) getGraphics();
        g2.drawRect(0, 0, getWidth(), getHeight());
    }

    @Override
    public void paintComponent(Graphics g) 
    {
        if (drawn) 
        {
            renderFractal();
        }
    }
}
