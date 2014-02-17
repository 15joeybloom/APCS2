package zoo.src;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.FilteredImageSource;
import java.awt.image.RGBImageFilter;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 Various utility methods
 <p>
 @author Joey Bloom
 */
public abstract class Util
{
    private static final BufferedImage NO_IMAGE_FOUND; //TODO initialize this

    static
    {
        BufferedImage temp;
        try
        {
            temp = ImageIO.read(Util.class.getResource("NO_IMAGE_FOUND.png"));
        }
        catch(IOException ex)
        {
            ex.printStackTrace();
            temp = new BufferedImage(32, 32, BufferedImage.TYPE_4BYTE_ABGR_PRE);
        }
        NO_IMAGE_FOUND = temp;
    }

    /**
     Doesn't make sense to construct a Util object
     */
    private Util()
    {
    }

//<editor-fold defaultstate="collapsed" desc="imageOpColor(Color)">
//    /**
//    Returns a BufferedImageOp that tints each pixel of a BufferedImage
//    @param color Color with which to tint the BufferedImage
//    @return BufferedImageOp that tints a BufferedImage
//    */
//    public static BufferedImageOp imageOpColor(Color color)
//    {
//        return new RescaleOp(
//                new float[]{
//                    0.5f * color.getRed() / 256.0f,
//                    0.5f * color.getGreen() / 256.0f,
//                    0.5f * color.getBlue() / 256.0f,
//                    0.5f * color.getAlpha() / 256.0f,
//                },
//                emptyFloats,
//                null);
//    }
//    private static final float[] emptyFloats = new float[]{};
//</editor-fold>
    /**
     Equivalent to {@code ImageIO.read(Util.class.getResource(name)) }
     <p>
     @param name The name of the image to load, e.g. "Rock.png"
     @return an image
     */
    public static BufferedImage loadImage(String name)
    {
        try
        {
            return ImageIO.read(Util.class.getResource(name));
        }
        catch(IOException ex)
        {
            ex.printStackTrace();
            return NO_IMAGE_FOUND;
        }
    }

    /**
     Also stolen from gridworld
     <p>
     @author Julie Zelenski
     @author Cay Horstmann
     @param image image to tint
     @param color color to tint with
     @return tinted image
     */
    public static Image tintImage(Image image, Color color)
    {
        FilteredImageSource src
            = new FilteredImageSource(image.getSource(), new TintFilter(color));
        return emptyComponent.createImage(src);
    }
    private static final Component emptyComponent = new Panel();

    /**
     An image filter class that tints colors based on the tint provided to the
     constructor (the color of an object). This class is stolen from gridworld.
     <p/>
     @author Julie Zelenski
     @author Cay Horstmann
     */
    public static class TintFilter extends RGBImageFilter
    {
        private int tintR, tintG, tintB;

        /**
         Constructs an image filter for tinting colors in an image.
         <p>
         @param color the tint color
         */
        public TintFilter(Color color)
        {
            canFilterIndexColorModel = true;
            int rgb = color.getRGB();
            tintR = (rgb >> 16) & 0xff;
            tintG = (rgb >> 8) & 0xff;
            tintB = rgb & 0xff;
        }

        @Override
        public int filterRGB(int x, int y, int argb)
        {
            // Separate pixel into its RGB coomponents.
            int alpha = (argb >> 24) & 0xff;
            int red = (argb >> 16) & 0xff;
            int green = (argb >> 8) & 0xff;
            int blue = argb & 0xff;
            // Use NTSC/PAL algorithm to convert RGB to gray level
            double lum = (0.2989 * red + 0.5866 * green + 0.1144 * blue) / 255;

            // interpolate between tint and pixel color. Pixels with
            // gray level 0.5 are colored with the tint color,
            // white and black pixels stay unchanged.
            // We use a quadratic interpolation function
            // f(x) = 1 - 4 * (x - 0.5)^2 that has
            // the property f(0) = f(1) = 0, f(0.5) = 1
            // Note: Julie's algorithm used a linear interpolation
            // function f(x) = min(2 - 2 * x, 2 * x);
            // and it interpolated between tint and
            // (lum < 0.5 ? black : white)
            double scale = 1 - (4 * ((lum - 0.5) * (lum - 0.5)));

            red = (int) (tintR * scale + red * (1 - scale));
            green = (int) (tintG * scale + green * (1 - scale));
            blue = (int) (tintB * scale + blue * (1 - scale));
            return (alpha << 24) | (red << 16) | (green << 8) | blue;
        }
    }

    /**
     This function resize the image file and returns the BufferedImage object
     that can be saved to file system.
     Taken from
     http://www.journaldev.com/615/java-image-resize-program-using-graphics2d-example
     <p>
     @param image  Image to resize
     @param width  desired width
     @param height desired height
     @return
     */
    public static BufferedImage resizeImage(final Image image, int width, int height)
    {
        final BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        final Graphics2D graphics2D = bufferedImage.createGraphics();
        graphics2D.setColor(Color.white);
        graphics2D.setBackground(Color.white);
        graphics2D.setComposite(AlphaComposite.Src.derive(1.0f));
        graphics2D.clearRect(0, 0, width, height);
        //below three lines are for RenderingHints for better image quality at cost of higher processing time
        graphics2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION,RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        graphics2D.setRenderingHint(RenderingHints.KEY_RENDERING,RenderingHints.VALUE_RENDER_QUALITY);
        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        graphics2D.drawImage(image, 0, 0, width, height, null);
        graphics2D.dispose();
        return bufferedImage;
    }

    /**
    Testing method - displays an Image in a JFrame so that I can see
    what it looks like
    @param img
    */
    public static void showImage(final Image img)
    {
        JFrame frame = new JFrame();
        JPanel panel = new JPanel(){
            @Override
            public void paintComponent(Graphics g)
            {
                g.drawImage(img, 0, 0, null);
            }
        };
        frame.add(panel);
        frame.setVisible(true);
    }
}
