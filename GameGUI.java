import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.util.ArrayList;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.*;

/**
 * GameGUI is the class that creates, runs, and controls the game. It is designed to allow the player to intuitively play the game.
 * 
 * @author Ashley Hejtmanek
 * @version May 9, 2013
 */
public class GameGUI extends JFrame implements ActionListener
{
    //This creates the Array containing all the buttons used in the game
    private JButton[] alphabet;
    //This Array is used to help keep track of the buttons in the game
    private int[] index;
    //These panels will hold all the pictures, and buttons used in the game
    private JPanel panel1, panel2, panel3, panel4, panel5;
    //These are the comment windows in the game
    private Comments angelComments, deathComments;
    //This is the graphical display of the gallows and probably hungman
    private Gallows gallows;
    //This is the mechanical part of the hangman game
    private HangmanGame hgGame;
    //This is the grim reaper who has come to came the man's soul
    private Executioner grimReaper;
    //This is his angel there to encourage the player
    private Angel gabriel;
    //This is the arraylist that will hold all the guesses made by the user
    private ArrayList<String> entries;
    //This counter is used to see if a new game has started
    private int clickCounter;

    /**
     * GameGUI Constructor creates the initial settings of the game
     *
     */
    public GameGUI(){
        //This makes the game close when the [x] in the upper right corner is clicked, and forces the game window to stay the same size
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);     
        setResizable(false);

        //This begins the game
        showStart();

        //This is where the panels which are used in the GUI are created
        new JFrame();
        panel1 = new JPanel();
        panel2 = new JPanel();
        panel3 = new JPanel();
        panel4 = new JPanel();
        panel5 = new JPanel();

        //This initializes all the graphics
        gallows = new Gallows();
        grimReaper = new Executioner();
        gabriel = new Angel();
        angelComments = new Comments();
        deathComments = new Comments();

        //This is where the mechanics of the GUI are initialized
        entries = new ArrayList<String>();
        clickCounter = 0;

        //This is where the mechanics of the game are initialized
        hgGame = new HangmanGame(entries);

        //This is where the keyboard is added
        panel2.setLayout(new GridLayout(4,7));
        makeAlphabet();

        //This creates the game window, and lays out where the buttons and graphics will go 
        setLayout(new BorderLayout());
        add(panel1, BorderLayout.CENTER);
        add(panel2,BorderLayout.SOUTH);

        //This is where the spaces for the graphics are added to the game window
        panel1.add(panel3, BorderLayout.EAST);
        panel1.add(panel5, BorderLayout.NORTH);
        panel1.add(panel4, BorderLayout.WEST);

        //This is where the angel, executioner, and their commentary are coupled together and layed out in the game window
        panel3.setLayout(new BorderLayout());
        panel3.add(angelComments, BorderLayout.CENTER);
        panel3.add(gabriel, BorderLayout.SOUTH);
        panel4.setLayout(new BorderLayout());
        panel4.add(deathComments,BorderLayout.CENTER);
        panel4.add(grimReaper,BorderLayout.SOUTH);
        panel5.setLayout(new BorderLayout());
        panel5.add(gallows, BorderLayout.CENTER);

        //This packs the panels together in the game window
        pack();

        //These methods setup the beginning of the game for the player
        hgGame.setupGame();
        gallows.printWord(hgGame.currentGuessString());
        deathComments.deathStartComment();
        angelComments.angelStartComment();

        //This makes the game visible for the player
        setVisible(true);
    }

    /**
     * Method actionPerformed is where the GUI takes input and then processes it all into the actual game experience
     *
     * @param e A parameter which takes an event, ie. the clicking of the mouse, and then uses the properties of the event for the GIU
     */
    public void actionPerformed(ActionEvent e){
        //This if else code is used for when the game has just begun, ie. there have been no previous clicks
        if(clickCounter == 0){
            //This is where the game translates the clicked button into the letter input
            for(int i = 0; i < 26; i++){
                //This gets the source of the click
                if (e.getSource() == alphabet[index[i]]){
                    //This gets the text of the button so that it may be fed into the game mechanics
                    entries.add(alphabet[index[i]].getText());
                    //This renders the button clicked inoperable so the player will not try to click it again
                    alphabet[index[i]].setEnabled(false);
                }
            }
            //This feeds the guess through the game mechanics
            hgGame.gamePlay();

            //This is where the commentary made by the angel and grim reaper is updated
            angelComments.eraseComment();
            angelComments.angelExtraComment(hgGame.getGuessLeft(), hgGame.getCheckNewGuess());
            deathComments.eraseComment();
            deathComments.deathExtraComment(hgGame.getGuessLeft(), hgGame.getCheckNewGuess());

            //This if-else statement checks if the guess is correct or not
            if(hgGame.getCheckNewGuess() == true){//if the guess is correct
                //This remoces the precious string of #'s and replaces it with the new mixture of #'s and letters
                gallows.eraseWord();
                gallows.printWord(hgGame.currentGuessString());
            }
            else{// if the guess is incorrect
                //This shows a piece of the man to be hung
                gallows.revealMan(hgGame.getGuessLeft(), Color.blue);
            }

            //This ensures that the game continues past the first guess
            clickCounter++;
        } 
        else{
            //This is where the game translates the clicked button into the letter input
            for(int i = 0; i < 26; i++){
                //This gets the source of the click
                if (e.getSource() == alphabet[index[i]]){
                    //This gets the text of the button so that it may be fed into the game mechanics
                    entries.add(alphabet[index[i]].getText());
                    //This renders the button clicked inoperable so the player will not try to click it again
                    alphabet[index[i]].setEnabled(false);
                }
            }
            //This feeds the guess through the game mechanics
            hgGame.gamePlay();

            //This is where the commentary made by the angel and grim reaper is updated
            angelComments.eraseComment();
            deathComments.eraseComment();
            angelComments.angelExtraComment(hgGame.getGuessLeft(), hgGame.getCheckNewGuess());
            deathComments.deathExtraComment(hgGame.getGuessLeft(), hgGame.getCheckNewGuess());

            //This if-else statement checks if the guess is correct or not
            if(hgGame.getCheckNewGuess() == true){//if the guess is correct
                //This remoces the precious string of #'s and replaces it with the new mixture of #'s and letters
                gallows.eraseWord();
                gallows.printWord(hgGame.currentGuessString());
            }
            else{// if the guess is incorrect
                //This shows a piece of the man to be hung
                gallows.revealMan(hgGame.getGuessLeft(), Color.blue);
            }

            //This ensures that the game continues past the first guess
            clickCounter++;
        }

        //Creates the ending for if theplayer wins
        if(hgGame.currentGuessString().contains("#") == false){
            //This calls up the comments that are shown when the player wins
            angelComments.eraseComment();
            deathComments.eraseComment();
            angelComments.angelWinComment();
            deathComments.deathWinComment();

            //This int gives the result of the player's choice; to play or not to play, that is the answer
            int result = showEndWin();

            //This restarts the game if the player chooses to continue
            if (result == JOptionPane.YES_OPTION){
                //This resets the game mechanics so the player gets a new game
                hgGame.setupGame();

                //This resets the gallows window for the new game
                gallows.eraseMan();
                gallows.eraseWord();
                gallows.printWord(hgGame.currentGuessString());

                //This resets the commentary from the grim reaper and angel
                angelComments.eraseComment();
                deathComments.eraseComment();
                angelComments.angelStartComment();
                deathComments.deathStartComment();

                //This reenables all the buttons in the game
                for(int i = 0; i < 26; i++){
                    alphabet[index[i]].setEnabled(true);
                }
            }
            else{
                //This makes the game invisible
                setVisible(false);
                //Destroys the Game
                dispose();
                //This is the exit message
                JOptionPane.showMessageDialog(null, "Thank you for you valiant effort!", "Goodbye", JOptionPane.INFORMATION_MESSAGE);
            }
        }

        //This creates the ending for if the player loses
        if(hgGame.getGuessLeft() == 0){
            //This int gives the result of the player's choice; to play or not to play, that is the answer
            int result = showEndLoss();

            //This restarts the game if the play chooses to continue
            if (result == JOptionPane.YES_OPTION){
                //This resets the hangman game
                hgGame.setupGame();

                //This resets the gallows window for the new game
                gallows.eraseMan();
                gallows.eraseWord();
                gallows.printWord(hgGame.currentGuessString());

                //This resets the commentary from the grim reaper and angel
                angelComments.eraseComment();
                deathComments.eraseComment();
                angelComments.angelStartComment();
                deathComments.deathStartComment();

                //This reenables all the buttons in the game
                for(int i = 0; i < 26; i++){
                    alphabet[index[i]].setEnabled(true);
                }
            }

            //This ends the game if the player chooses to leave
            if(result == JOptionPane.NO_OPTION){
                //Makes the game invisible
                setVisible(false); 
                //Destroys the JFrame object
                dispose(); 
                //This is the exit message
                JOptionPane.showMessageDialog(null, "Thank you for you valiant effort!", "Goodbye", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

    /**
     * Method makeAlphabet creates all the buttons needed for the GUI to enter the letters
     *
     */
    public void makeAlphabet(){
        //This initializes the two arrays used by the game
        alphabet = new JButton[28];
        index  = new int[26]; 

        //This creates all the buttons used in the game and makes them ready to be pressed
        for(int i = 0; i < 28; i++){
            alphabet[i] = new JButton();
            alphabet[i].addActionListener(this);
            panel2.add(alphabet[i]);
        }
        //This makes it so there aren't two extra buttons which do nothing, and also allows for the game window to have some symmetry
        alphabet[27].removeActionListener(this);
        alphabet[21].removeActionListener(this);
        alphabet[27].setVisible(false);
        alphabet[21].setVisible(false);

        //This sets the text of all the buttons in the game
        alphabet[0].setText("A");
        alphabet[1].setText("B");
        alphabet[2].setText("C");
        alphabet[3].setText("D");
        alphabet[4].setText("E");
        alphabet[5].setText("F");
        alphabet[6].setText("G");
        alphabet[7].setText("H");
        alphabet[8].setText("I");
        alphabet[9].setText("J");
        alphabet[10].setText("K");
        alphabet[11].setText("L");
        alphabet[12].setText("M");
        alphabet[13].setText("N");
        alphabet[14].setText("O");
        alphabet[15].setText("P");
        alphabet[16].setText("Q");
        alphabet[17].setText("R");
        alphabet[18].setText("S");
        alphabet[19].setText("T");
        alphabet[20].setText("U");
        alphabet[22].setText("V");
        alphabet[23].setText("W");
        alphabet[24].setText("X");
        alphabet[25].setText("Y");
        alphabet[26].setText("Z");

        //This connects the indices to the number of each button, thereby easing the game's access to the buttons
        index[0] = 0;
        index[1] = 1;
        index[2] = 2;
        index[3] = 3;
        index[4] = 4;
        index[5] = 5;
        index[6] = 6;
        index[7] = 7;
        index[8] = 8;
        index[9] = 9;
        index[10] = 10;
        index[11] = 11;
        index[12] = 12;
        index[13] = 13;
        index[14] = 14;
        index[15] = 15;
        index[16] = 16;
        index[17] = 17;
        index[18] = 18;
        index[19] = 19;
        index[20] = 20;
        index[21] = 22;
        index[22] = 23;
        index[23] = 24;
        index[24] = 25;
        index[25] = 26;
    }

    /** 
     * Method showStart shows the introduction
     */
    private void showStart()
    {
        //Displays welcome message using a message dialog box
        JOptionPane.showMessageDialog(null, "Welcome to the Gallows. Shall we proceed?", "Welcome", JOptionPane.INFORMATION_MESSAGE);
        //Shows the rules of the game, and how to play
        JOptionPane.showMessageDialog(null, "The rules are simple. \n To save this poor fool's life you must guess all the letters in the word. \n Pick your letters by pressing the buttons at the bottom of the screen. \n\n Good luck player, lives depend on you.", "The rules",JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Method showEndWin shows the end window if the player wins
     *
     * @return The return value is the int which declares if the user picked yes or no
     */
    private int showEndWin()
    {
        //This is the window that appears when the player wins
        return JOptionPane.showConfirmDialog(null, "Well done player! \n You have saved this poor man from death. \n(I'm certain Death's not happy about that!) \n Would you like to try to save another?", "YOU'VE DONE IT!", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE);
    }

    /**
     * Method showEndLoss shows the end window if the player loses
     *
     * @return The return value is the int which declares if the user picked yes or no
     */
    private int showEndLoss()
    {
        //This is the window that appears when the player loses
        return JOptionPane.showConfirmDialog(null, "Well, player, I'm afraid he's dead. \n The word you sought was '" + hgGame.getWord() + "'.\n Your skills were not enough to save him from death. \n (I'm certain Death is rejoicing as he reieves his due.) \n Would you like to try to save another?", "You have lost.", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE);
    }
}