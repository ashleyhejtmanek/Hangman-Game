import java.awt.*;

/**
 * An abstract class representing a Shape that can be drawn.
 * 
 * @author Joel Ross
 * @version Jan 2013
 */
public abstract class Shapes
{
    public abstract void draw(Graphics2D g2d);

    protected Color getColorFromString(String c)
    {
        if(c.equals("blue"))
            return Color.BLUE;
        if(c.equals("cyan"))
            return Color.CYAN;
        if(c.equals("gray"))
            return Color.GRAY;
        if(c.equals("green"))
            return Color.GREEN;
        if(c.equals("magenta"))
            return Color.MAGENTA;
        if(c.equals("orange"))
            return Color.ORANGE;
        if(c.equals("pink"))
            return Color.PINK;
        if(c.equals("red"))
            return Color.RED;
        if(c.equals("white"))
            return Color.WHITE;
        if(c.equals("yellow"))
            return Color.YELLOW;
        return Color.BLACK; //return black otherwise
    }

}
