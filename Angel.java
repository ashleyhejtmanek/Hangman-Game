import java.awt.*; 
import javax.swing.*; 
import java.awt.image.BufferedImage;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.*;
import javax.imageio.ImageIO;
import javax.imageio.ImageReader;

/**
 * The picture used for the "angel" was originally found at http://www.geocities.com/spunk1111/religion.htm
 * I then used print screen and Microsoft Paint to create the png used here.
 * 
 * @author Ashley Hejtmanek
 * @version May 9, 2013
 */
public class Angel extends JPanel
{
    //These variables allows the image of the angel to appear
    private BufferedImage image;
    private Graphics2D graphics;
    private final static int CANVAS_WIDTH = 325;
    private final static int CANVAS_HEIGHT = 450;
    private BufferedImage theImage;

    /**
     * Constructor
     */
    public Angel(){
        // invoke super constructor
        super(); 

        //This creates the canvas for the image
        //The dimension is the initial size for the window
        Dimension dim = new Dimension(CANVAS_WIDTH, CANVAS_HEIGHT);
        //Makes the size of the window equal to dim
        setPreferredSize(dim);
        //Initializes the image, and graphics
        image = new BufferedImage(CANVAS_WIDTH, CANVAS_HEIGHT, BufferedImage.TYPE_INT_RGB);
        graphics = image.createGraphics();

        //Loads in the png file
        loadImage();
        
        //Shows the picture
        showImage(theImage);
    }

    /**
     * overrides the paintComponent of JPanel
     */
    public void paintComponent(Graphics g)
    {
        g.drawImage(image, 0, 0, null);
    }

    /** 
     * retrieves a BufferedImage from a disk file
     * @return the BufferedImage, null if user cancels
     * 
     */
    public BufferedImage loadImage()
    {
        //This initializes the image
        theImage = null;

        //This loads the file into the game's memmory
        File file = new File("angel.png");

        //This is for if there is an error in reading the image
        try
        {
            theImage = ImageIO.read(file); 
        }
        catch (IOException e)
        {
            System.out.println("Error loading file: " + e.getMessage());
            e.printStackTrace();
        }

        //Returning the ArrayList
        return theImage;
    }

    /** 
     * shows the image
     * @param b the buffered image to show
     */
    public void showImage(BufferedImage b)
    {
        image = b;
        image.flush();
        repaint();
    }
}