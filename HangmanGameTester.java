import java.util.*;
/**
 * Write a description of class HangmanGameTester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class HangmanGameTester
{
    public static void main(String[] args){
        HangmanGame gameObject = new HangmanGame();
        String response = "y";
        Scanner sc = new Scanner(System.in);
        while(response.equalsIgnoreCase("y")){
            gameObject.playGame();
            System.out.println("");
            System.out.println("Do you want to play again (y/n)?: ");
            if(!(response.equals("y")) || !(response.equals("n"))){
                System.out.println("Please enter a 'y' or an 'n' to say whether or not you want another game");
            }
            response = sc.next();
        }
    }
}
