import java.awt.*; 
import javax.swing.*; 
import java.awt.event.*;
import java.awt.image.BufferedImage;

/**
 * The Gallows class creates the image of the Gallows, the man to be hung, and the word to be guessed
 * 
 * @author Ashley Hejtmanek
 * @version May 9, 2013
 */
public class Gallows extends JPanel
{
    //These are the variables used by the Gallows
    private BufferedImage image;
    private Graphics2D graphics;
    private final static int CANVAS_WIDTH = 550;
    private final static int CANVAS_HEIGHT = 550;

    /**
     * Constructor
     */
    public Gallows(){
        //invokes the super constructor
        super();

        //dim is the initial size of the gallows
        Dimension dim = new Dimension(CANVAS_WIDTH, CANVAS_HEIGHT); 
        //initializes the window to the dimensions of dim
        setPreferredSize(dim);

        //initializes the graphics of this class
        image = new BufferedImage(CANVAS_WIDTH, CANVAS_HEIGHT, BufferedImage.TYPE_INT_RGB);
        graphics = image.createGraphics();

        //Creates background white color
        graphics.setColor(Color.white); 
        graphics.fillRect(0, 0, CANVAS_WIDTH ,CANVAS_HEIGHT);

        //This makes the gallows immediately visible
        tree();
        //This makes the man in the background and also makes him invisible
        man();
    }

    /**
     * allows user to draw image
     * @param Graphics g 
     */
    public void paintComponent(Graphics g){
        g.drawImage(image, 0, 0, null);
    }

    /**
     * Method tree creates the tree that the man swings from
     *
     */
    public void tree(){
        //Sets the color to black
        graphics.setColor(Color.black);

        //creates the base
        graphics.fillRect(25,445,500,10);

        //creates the verticle pole
        graphics.fillRect(120,50,10,405);

        //creates the horizontal pole
        graphics.fillRect(120,50,250,10);

        //creates the rope
        graphics.fillRect(370,50,10,75);
    }

    /**
     * Method man creates the invisible dead man
     *
     */
    public void man(){
        //creates the head
        head(Color.white);

        //creates the body
        body(Color.white);

        //creates the left arm
        lArm(Color.white);

        //creates the right arm
        rArm(Color.white);

        //creates the left leg
        lLeg(Color.white);

        //creates the right leg
        rLeg(Color.white);
    }

    /**
     * Method head creates the man's head
     *
     * @param c A parameter that declares the color of the head
     */
    public void head(Color c){
        //sets the color
        graphics.setColor(c);
        //creates the head
        graphics.fillOval(350,125,50,50);
    }

    /**
     * Method body creates the man's body
     *
     * @param c A parameter that declares the color of the body
     */
    public void body(Color c){
        //sets the color
        graphics.setColor(c);
        //creates the body
        graphics.fillRect(372,175,5,125);
    }

    /**
     * Method lArm creates the man's left arm
     *
     * @param c A parameter that declares the color of the arm
     */
    public void lArm(Color c){
        //sets the color
        graphics.setColor(c);
        //makes the lines wide enough to match the body
        graphics.setStroke(new BasicStroke(5));
        //draws the left arm
        graphics.drawLine(372,200,322,225);
        graphics.drawLine(322,225,322,275);
    }

    /**
     * Method rArm creates the man's right arm
     *
     * @param c A parameter that declares the color of the arm
     */
    public void rArm(Color c){
        //sets the color
        graphics.setColor(c);
        //makes the lines wide enough to match the body
        graphics.setStroke(new BasicStroke(5));
        //draws the right arm
        graphics.drawLine(377,200,427,225);
        graphics.drawLine(427,225,427,275);
    }

    /**
     * Method lLeg creates the man's left leg
     *
     * @param c A parameter that declares the color of the leg
     */
    public void lLeg(Color c){
        //sets the color
        graphics.setColor(c);
        //makes the lines wide enough to match the body
        graphics.setStroke(new BasicStroke(5));
        //draws the left leg
        graphics.drawLine(372,300,342,400);
    }

    /**
     * Method rLeg creates the man's right leg
     *
     * @param c A parameter that declares the color of the leg
     */
    public void rLeg(Color c){
        //sets the color
        graphics.setColor(c);
        //makes the lines wide enough to match the body
        graphics.setStroke(new BasicStroke(5));
        //draws the right leg
        graphics.drawLine(377,300,407,400);
    }

    /**
     * Method revealMan reveals each piece of the man as the play makes an incorrect guess
     *
     * @param i A parameter that indicates the number of wrong guesses the player has left
     * @param c A parameter that says what color the man will be 
     */
    public void revealMan(int i, Color c){
        //These nested ifs check how many incorrect guesses the play has left and then calls up the piece of the man which corresponds to the number
        if(i == 5){//if the number is 5
            //Reveals the head in the color given by the parameter
            head(c);
        }
        else{
            if(i == 4){//if the number is 4
                //Reveals the body in the color given by the parameter
                body(c);
            }
            else{
                if(i == 3){//if the number is 3
                    //Reveals the right arm in the color given by the parameter
                    rArm(c);
                }
                else{
                    if(i == 2){//if the number is 2
                        //Reveals the left arm in the color given by the parameter
                        lArm(c);
                    }
                    else{
                        if(i == 1){//if the number is 1
                            //Reveals the right leg in the color given by the parameter
                            rLeg(c);
                        }
                        else{
                            if(i == 0){//if the number is 0
                                //Reveals the left leg in the color given by the parameter
                                lLeg(c);
                            }
                        }
                    }
                }
            }
        }
        //This repaints the man
        repaint();
    }

    /**
     * Method eraseMan erases the man
     *
     */
    public void eraseMan(){
        //sets the color
        graphics.setColor(Color.white);
        //creates a rectangle to go over the man thereby erasing him
        graphics.fillRect(250,125,300,315);
    }

    /**
     * Method eraseWord erases the word that the player is guessing
     *
     */
    public void eraseWord(){
        //sets the color
        graphics.setColor(Color.white);
        //creates a rectangle to go over the word thereby erasing it
        graphics.fillRect(0, 455, 550, 150);
    }

    /**
     * Method printWord prints out the word that the player is guessing
     *
     * @param s A parameter that declares the word that the player is guessing
     */
    public void printWord(String s){
        //This sets the color of the word to red
        graphics.setColor(Color.red);
        
        //This makes the graphics
        Graphics2D g2 = (Graphics2D)graphics;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
            RenderingHints.VALUE_ANTIALIAS_ON);
            
        //This sets the font of the words
        Font font = new Font("Serif", Font.PLAIN, 80);
        g2.setFont(font);

        //This draws the word underneath the gallows
        g2.drawString(s, 25, 530);
        
        //This repaints the word
        repaint();
    }
}