import java.awt.*; 
import javax.swing.*; 
import java.util.*;
import java.awt.image.BufferedImage;

/**
 * The Comments class is where all the commentary given by the Angel and the Grim Reaper is created
 * 
 * @author Ashley Hejtmanek
 * @version May 9, 2013
 */
public class Comments extends JPanel
{
    //These are the necessary vaiables for the comments to appear in the game window
    private BufferedImage image;
    private Graphics2D graphics;
    private final static int CANVAS_WIDTH = 325;
    private final static int CANVAS_HEIGHT = 100;
    private Color color;

    /**
     * This is the Constructor
     */
    public Comments(){
        //Invokes the super constructor
        super();

        //Creates the initial comment window
        //dim is the initial size of the window
        Dimension dim = new Dimension(CANVAS_WIDTH, CANVAS_HEIGHT);
        setPreferredSize(dim);

        //Initialized the image and graphics components of the comment window
        image = new BufferedImage(CANVAS_WIDTH, CANVAS_HEIGHT, BufferedImage.TYPE_INT_RGB);
        graphics = image.createGraphics();

        //Creates the black background
        graphics.setColor(Color.black);
        graphics.fillRect(0, 0, CANVAS_WIDTH ,CANVAS_HEIGHT);

        //Makes the comment window visible
        setVisible(true);
    }

    /**
     * allows user to draw image
     * @param Graphics g 
     */
    public void paintComponent(Graphics g){
        g.drawImage(image, 0, 0, null);
    }

    /**
     * eraseComment() resets the comment window back to black so that new comments will not appear on top of the old ones
     */
    public void eraseComment(){
        graphics.setColor(Color.black);
        graphics.fillRect(0, 0, CANVAS_WIDTH ,CANVAS_HEIGHT);
        repaint();
    }

    /**
     * These methods are the methods used to create the angel's commentary
     */

    /**
     * Method angelStartComment creates and displays the first angel comment of the game
     *
     */
    public void angelStartComment(){
        //This sets the color of the commentary to blue
        graphics.setColor(Color.blue);

        //This makes the graphics
        Graphics2D g2 = (Graphics2D)graphics;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
            RenderingHints.VALUE_ANTIALIAS_ON);

        //This sets the font of the words
        Font font = new Font("Harrington", Font.PLAIN, 20);
        g2.setFont(font);

        //This draws the words in the comment box
        g2.drawString("Welcome to the gallows!", 10, 30);
        g2.drawString("I pray you'll save this soul!", 10, 55);

        //This makes the words visible
        setVisible(true);
    }

    /**
     * Method angelExtraComment creates the comments seen as the game is played
     *
     * @param i A parameter that indicates the number of wrong guesses the player has left
     * @param b A parameter that indicates if the guess was valid
     */
    public void angelExtraComment(int i, boolean b){
        //These ints say which of the comments are shown after each guess
        int r1 = (int)(Math.random()*2);
        int r2 = (int)(Math.random()*2);

        //This makes the color of the words blue
        graphics.setColor(Color.blue);
        
        //This sets up the graphics
        Graphics2D g2 = (Graphics2D)graphics;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
            RenderingHints.VALUE_ANTIALIAS_ON);
            
        //This creates the font for the comments
        Font font = new Font("Harrington", Font.PLAIN, 18);
        g2.setFont(font);
        
        //The outermost if-else statement says whether or not the guess was correct or not
        if(b == false){//if the guess was incorrect
            //The player has five guesses left
            if(i ==5){
                //There are two possible comments shown
                if(r1 == 0){
                    g2.drawString("Oh no!", 10, 30);
                    g2.drawString("He is one step closer to death!", 10, 55);
                }
                if(r1 == 1){
                    g2.drawString("Why did you do that?", 10, 30);
                    g2.drawString("That poor soul wants you to save him!", 10, 55);
                }
            }
            //The player has four guesses left
            if(i == 4){
                //There are two possible comments shown
                if(r1 ==0){
                    g2.drawString("Please try harder!", 10, 30);
                    g2.drawString("His soul rests on your shoulders.", 10, 55);
                }
                if(r1 == 1){
                    g2.drawString("In heaven's name", 10, 30);
                    g2.drawString("Please help him live!", 10, 55);
                }
            }
            //The player has three guesses left
            if(i == 3){
                //There are two possible comments shown
                if(r1 == 0){
                    g2.drawString("Please my dear,", 10, 30);
                    g2.drawString("this soul rests in the balance.", 10, 55);
                }
                if(r1 == 1){
                    g2.drawString("Oh my dear one,", 10, 30);
                    g2.drawString("You may yet save this man!", 10, 55);
                }
            }
            //The player has two guesses left
            if(i ==2){
                //There are two possible comments shown
                if(r1 == 0){
                    g2.drawString("Please, please, please,", 10, 30);
                    g2.drawString("do not let this man slip into death.", 10, 55);
                }
                if(r1 == 1){
                    g2.drawString("Oh please, by God,", 10, 30);
                    g2.drawString("Help to redeem this man!", 10, 55);
                }
            }
            //The player has one guess left
            if(i == 1){
                //There are two possible comments shown
                if(r1 ==0){
                    g2.drawString("You are one step,", 10, 30);
                    g2.drawString("from condemning this man to hell.", 10, 55);
                }
                if(r1 == 1){
                    g2.drawString("This man is in your hands,", 10, 30);
                    g2.drawString("and you have almost killed him.", 10, 55);
                }
            }
            //The player has zero guesses left, ie. they've lost
            if(i == 0){
                //There are two possible comments shown
                if(r1 == 0){
                    g2.drawString("And there he goes,", 10, 30);
                    g2.drawString("his soul into hell, by your might.", 10, 55);
                }
                if(r1 == 1){
                    g2.drawString("You have damned him.", 10, 30);
                    g2.drawString("Your soul is damned as well.", 10, 55);
                }
            }
        }
        else{//if the guess was correct
            //There are four possible comments shown
            if(r2 == 0){
                g2.drawString("Heaven rejoices!", 10, 30);
                g2.drawString("You are saving this man!", 10, 55);
            }
            if(r2 == 1){
                g2.drawString("You are heaven sent!", 10, 30);
                g2.drawString("This poor soul will be redeemed.", 10, 55);
            }
            if(r2 == 2){
                g2.drawString("Many blessings upon you,", 10, 30);
                g2.drawString("You walk the path of the righteous.", 10, 55);
            }
            if(r2 == 3){
                g2.drawString("By all the heavenly choirs,", 10, 30);
                g2.drawString("You will save this poor man!", 10, 55);   
            }
        }
        
        //This causes the comments to be repainted
        repaint();
        
        //Makes the text visible
        setVisible(true);
    }

    /**
     * Method angelWinComment creates the comments shown above the angel when the player wins
     *
     */
    public void angelWinComment(){
        //This sets the color of the commentary to blue
        graphics.setColor(Color.blue);

        //This makes the graphics
        Graphics2D g2 = (Graphics2D)graphics;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
            RenderingHints.VALUE_ANTIALIAS_ON);

        //This sets the font of the words
        Font font = new Font("Harrington", Font.PLAIN, 24);
        g2.setFont(font);

        //This draws the words in the comment box
        g2.drawString("Well Done!", 10, 30);
        g2.drawString("You have saved his soul!", 10, 55);

        //This makes the words visible
        setVisible(true);
    }
    
    /**
     * These methods are the methods used to create the grim reaper's commentary
     */

    /**
     * Method deathStartComment creates the first comment seen in the comment window over the grim reaper
     *
     */
    public void deathStartComment(){
        //This sets the color of the commentary to red
        graphics.setColor(Color.red);

        //This makes the graphics
        Graphics2D g2 = (Graphics2D)graphics;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
            RenderingHints.VALUE_ANTIALIAS_ON);

        //This sets the font of the words
        Font font = new Font("Chiller", Font.PLAIN, 24);
        g2.setFont(font);

        //This draws the words in the comment box
        g2.drawString("Welcome to the gallows!", 10, 30);
        g2.drawString("I'm sure you'll give me another soul!", 10, 55);

        //This makes the words visible
        setVisible(true);
    }

    /**
     * Method deathExtraComment creates the comments seen as the game is played
     *
     * @param i A parameter that indicates the number of wrong guesses the player has left
     * @param b A parameter that indicates if the guess was valid
     */
    public void deathExtraComment(int i, boolean b){
        //These ints say which of the comments are shown after each guess
        int r1 = (int)(Math.random()*2);
        int r2 = (int)(Math.random()*2);

        //This makes the color of the words red
        graphics.setColor(Color.red);
        
        //This sets up the graphics
        Graphics2D g2 = (Graphics2D)graphics;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
            RenderingHints.VALUE_ANTIALIAS_ON);
            
        //This creates the font for the comments
        Font font = new Font("Chiller", Font.PLAIN, 24);
        g2.setFont(font);
        
        //The outermost if-else statement says whether or not the guess was correct or not
        if(b == false){//if the guess was incorrect
            //The player has five guesses left
            if(i ==5){
                //There are two possible comments shown
                if(r1 == 0){
                    g2.drawString("Wonderful!", 10, 30);
                    g2.drawString("He is one step closer to me!", 10, 55);
                }
                if(r1 == 1){
                    g2.drawString("Ha ha!", 10, 30);
                    g2.drawString("I see you actually are working for me!", 10, 55);
                }
            }
            //The player has four guesses left
            if(i == 4){
                //There are two possible comments shown
                if(r1 ==0){
                    g2.drawString("So,", 10, 30);
                    g2.drawString("Even you want to see him swing?", 10, 55);
                }
                if(r1 == 1){
                    g2.drawString("Ouch", 10, 30);
                    g2.drawString("I think that hurts, maybe a litte.", 10, 55);
                }
            }
            //The player has three guesses left
            if(i == 3){
                //There are two possible comments shown
                if(r1 == 0){
                    g2.drawString("Hmmm", 10, 30);
                    g2.drawString("I think he's squirming. How amusing!", 10, 55);
                }
                if(r1 == 1){
                    g2.drawString("Ahahaha", 10, 30);
                    g2.drawString("Listen to him beg!", 10, 55);
                }
            }
            //The player has two guesses left
            if(i ==2){
                //There are two possible comments shown
                if(r1 == 0){
                    g2.drawString("Oh dear!", 10, 30);
                    g2.drawString("You're killing him too fast! The show's almost over.", 10, 55);
                }
                if(r1 == 1){
                    g2.drawString("Quickly now!", 10, 30);
                    g2.drawString("I want to move on to the next soul!", 10, 55);
                }
            }
            //The player has one guess left
            if(i == 1){
                //There are two possible comments shown
                if(r1 ==0){
                    g2.drawString("Yes, Yes!", 10, 30);
                    g2.drawString("One more misstep and he is mine!", 10, 55);
                }
                if(r1 == 1){
                    g2.drawString("I almost feel for him.", 10, 30);
                    g2.drawString("He knows you're going to kill him.", 10, 55);
                }
            }
            //The player has zero guesses left, ie. they've lost
            if(i == 0){
                //There are two possible comments shown
                if(r1 == 0){
                    g2.drawString("Wonderful!", 10, 30);
                    g2.drawString("I knew today was a good day for death!", 10, 55);
                }
                if(r1 == 1){
                    g2.drawString("I wonder if I can hire you,", 10, 30);
                    g2.drawString("you practice death almost as well as I!", 10, 55);
                }
            }
        }
        else{//if the guess was correct
            //There are foure possible comments shown
            if(r2 == 0){
                g2.drawString("Damn!!", 10, 30);
                g2.drawString("You think you'll save him!?!", 10, 55);
            }
            if(r2 == 1){
                g2.drawString("What are you doing?", 10, 30);
                g2.drawString("Do really think you can save him from me?", 10, 55);
            }
            if(r2 == 2){
                g2.drawString("You will lose,", 10, 30);
                g2.drawString("I come for you all!!!", 10, 55);
            }
            if(r2 == 3){
                g2.drawString("Bah!", 10, 30);
                g2.drawString("You will make a mistake eventually!", 10, 55);
            }    
        }
        
        //This causes the comments to be repainted
        repaint();
        
        //Makes the text visible
        setVisible(true);
    }

    /**
     * Method deathWinComment creates the comments shown above the angel when the player wins
     *
     */
    public void deathWinComment(){
        //This sets the color of the commentary to red
        graphics.setColor(Color.red);

        //This makes the graphics
        Graphics2D g2 = (Graphics2D)graphics;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
            RenderingHints.VALUE_ANTIALIAS_ON);

        //This sets the font of the words
        Font font = new Font("Chiller", Font.PLAIN, 24);
        g2.setFont(font);

        //This draws the words in the comment box
        g2.drawString("I will come for you!", 10, 30);
        g2.drawString("I will get you eventually!", 10, 55);

        //This makes the words visible
        setVisible(true);
    }
}