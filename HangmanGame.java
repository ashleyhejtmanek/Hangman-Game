import java.io.*;
import java.awt.*;
import javax.swing.*;
import java.io.InputStream.*;
import java.util.*;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.image.BufferedImage;

/**
 * The HangmanGame class is the mechanics behind this game
 * 
 * @author Ashley Hejtmanek
 * @version May 9, 2013
 */
public class HangmanGame
{
    /**
     * Here are the variables required for the game
     */
    //This tracks how many guesses the user has left
    private int guessLeft;
    //This tracks the number of guesses made by the user
    private int numberGuesses;
    //This is the word that the user wants to get right and thus win the game
    private String word;
    //This array tracks the user's progress through the game
    private char[] currentGuess;
    //This is the list of words that the game has to chose from
    private ArrayList<String> words;
    //This ArrayList tracks what letters have been used by the user
    private ArrayList<Character> guesses;
    //This the list of letters that the user could possibly use which are valid
    private String letters;
    //This is the number of letters in the word
    private int numLetter;
    //This ArrayList contains all the guess used by the user
    private ArrayList<String> guessesGUI;
    //This boolean, declared later, says wether or not the guessed letter is in the word
    private boolean checkNewGuess;
    //This string is the string after each guess as the game gets updated
    private String updateGuess;

    //This is the constructor of the hangman game. It takes an arraylist so that the game may interact with the gui
    public HangmanGame(ArrayList<String> entries){
        guessesGUI = entries;
        //This loads the words into the game
        loadWords();
        //This sets up what the guessable characters are
        letters = "aAbBcCdDeEfFgGhHiIjJkKlLmMnNoOpPqQr  /RsStTuUvVwWxXyYzZ"; 
        //This makes the game start with the user not having guessed
        guesses = new ArrayList<Character>(); 
        //This sets up the game
        setupGame();
    }

    /**
     * Here are the getters and setters for the HangmanGame Class
     */

    /**
     * Method getGuessLeft this method returns how many incorrect guesses the user has left
     *
     * @return The return value is the value of how many incorrect guesses the user has left
     */
    public int getGuessLeft(){
        return guessLeft;
    }

    /**
     * Method getWord gives the word that the user must guess
     *
     * @return The return value is the word that the user has to guess as it is hidden
     */
    public String getWord(){
        return word;
    }

    /**
     * Method getCheckNewGuess gives the value of the boolean checkNewGuess
     *
     * @return     The value of the boolean checkNewGuess which is used later in the gamePlay method
     */
    public boolean getCheckNewGuess()
    {
        return checkNewGuess;
    }

    /**
     * Here are the methods used in the program. Most of these methods will be called individually in the GUI.
     */

    /**
     * Method playGame makes it so the game can be played by the user
     *
     */
    public void playGame(){
        //This game is now setup
        setupGame();

        //This loop is goes for as long as the user still has usable guesses
        for(;guessLeft >= 0;)
        {
            gamePlay();
            if(updateGuess.equals(word)){
                setupGame();
            }
        }

        //This is for if the player has lost the game, thus ending the game with:
        if(guessLeft == -1){
            //This sets up a new game
            setupGame();
            return;
        }
    }

    /**
     * Method loadWords uploads the words from the file
     *
     * @return The return value is the list of words used by the game
     */
    public ArrayList<String> loadWords()
    {
        //This loads the file into the game's memmory
        File file = new File("words1500.txt");

        //This is for if there is an error in the game
        try
        {
            //This scans the file
            Scanner scanner = new Scanner(file); 

            //This loads the words into the words ArrayList
            words = new ArrayList<String>();

            //This is a dummy string which is used to put the entries into the ArrayList
            String s;

            //This loads the words into the ArrayList so long as there are more words to load
            while(scanner.hasNextLine() == true){
                //This sets s as the next word in the file
                s = scanner.nextLine(); 

                //This adds the word s into the ArrayList
                words.add(s);
            }

            //This ends the scanning process
            scanner.close();
        }catch (IOException e)
        {
            System.out.println("Error loading file: " + e.getMessage());
            e.printStackTrace();
        }

        //Returning the ArrayList
        return words;
    }

    /**
     * Method setupGame clears all the previous guesses, sets up the new game, and loads the new word
     *
     */
    public void setupGame(){
        //This random object is used because the size of the words ArrayList is unknown
        Random random = new Random();

        //This is the index of the random word used by the program
        int r1 = random.nextInt(words.size());

        //This sets up the user for a new game
        numberGuesses = 0;
        guessLeft = 6;

        //This sets up a new game
        word = words.get(r1);
        guesses = new ArrayList<Character>();

        //for convenience, set variable for the number of letters in the word
        numLetter = word.length(); 
        currentGuess = new char[numLetter];

        //This obscures the word so that the player cannot see it
        for(int i = 0; i < numLetter; i++){
            currentGuess[i] = '#'; 
        }

        //This clears out the memmory for this ArrayList so previous guesses are not kept
        guesses.clear();
    }

    /**
     * Method getGuess this takes the user's guess, checks if it is valid, and starts the next turn
     *
     * @return The return value is the lower case version of the user's guess
     */
    public char getGuess(){
        //The newest entry in guesses will be the guess used later but must be stored as the variable guess
        String guess = null;

        //This takes the character that the user inputs
        for(String s : guessesGUI){
            if (!guessesGUI.isEmpty()) {
                guess = guessesGUI.get(guessesGUI.size()-1);
            }
        }

        //This sets the character to lower case
        String lowercase = guess.toLowerCase();
        char lcGuess = lowercase.charAt(0);

        //This checks the guess
        for(boolean validGuess = false; validGuess == false;){
            //This makes the loop go through each character
            validGuess = true;

            //The user already used this character and must guess again
            for(;guesses.contains(lcGuess) == true;){
                //This restarts the guessing process
                for(String s : guessesGUI){
                    if (!guesses.isEmpty()) {
                        guess = guessesGUI.get(guessesGUI.size()-1);
                    }
                }
                lowercase = guess.toLowerCase();
                lcGuess = lowercase.charAt(0);
                validGuess = false;
            }

            //This guess is invalid and so the user must guess again
            for(;isValid(guess) == false;){
                //This restarts the guessing process
                for(String s : guessesGUI){
                    if (!guesses.isEmpty()) {
                        guess = guessesGUI.get(guessesGUI.size()-1);
                    }
                }
                lowercase = guess.toLowerCase();
                lcGuess = lowercase.charAt(0);
                validGuess = false;
            }
        }
        //adds the guess to guesses
        guesses.add(lcGuess);
        guess = null;
        return lcGuess;
    }

    /**
     * Method isValid this method checks to see if the guess is valid
     *
     * @param guessInput A parameter that is a string just in case the user puts in more than one character
     * @return The return value is a true or false
     */
    public boolean isValid(String guessInput){
        //This says that the guess is valid unless it sets off the ifs
        boolean valid = true; 
        //for if the guess is more than one character
        if(guessInput.length() != 1){
            valid = false;
        }

        //for if the guess is 1 letter, then it checks the letter against the letters
        char[] charInput = guessInput.toCharArray();
        int index = letters.indexOf(guessInput); 
        //for if the input is not a letter
        if(index == -1){
            valid = false;
        }

        //this returns if the guess is valid
        return valid;
    }

    /**
     * Method checkGuess checks if character given by user is in the word
     * 
     * @param The character input by the user
     * @return The return value is a boolean, true or false, of whether or not the character is in the word
     */
    public boolean checkGuess(char letter){
        //This says that the character is not in the word unless it fulfills the loop's conditions
        boolean match = false;

        //This loop is for all the characters in the word
        for(int i = 0; i < numLetter; i++){
            //If the letter is in the word
            if(letter == word.charAt(i)){ 
                //This updates the current guess array
                currentGuess[i] = letter;
                //and sets match to true
                match = true;
            }
        }
        //This updates the number of guesses made by the user
        numberGuesses++;

        //This returns whether the match is true or false
        return match;
    }

    /**
     * Method currentGuessString shows the word as a mix of letters and # after each guess
     *
     * @return The return value is the string of the word after each guess
     */
    public String currentGuessString(){
        String currentStringGuess = String.valueOf(currentGuess);
        return currentStringGuess;
    }

    /**
     * Method gamePlay is the actual mechanics of the hangman game
     *
     */
    public void gamePlay(){
        char newGuess = getGuess(); //variable for character returned by getGuess
        checkNewGuess = checkGuess(newGuess); //checks if newGuess is in word
        //if the guess is in the word then:
        if(checkNewGuess == true){ 
            //This converts the current guess into a string
            updateGuess = currentGuessString(); 
            //If the guess finishes the word, and the user wins the game, then:
        } 
        //if the new character is not in the word then
        else{
            guessLeft--;
        } 
    }
}   