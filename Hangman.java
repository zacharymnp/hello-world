/*
 * Created by Zachary Niles Peretz
 * Last Updated 10/09/2019
 * The actual hangman game
*/
import java.util.Scanner;
import java.util.ArrayList;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JFrame;

/*
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
*/

public class Hangman extends JFrame{
	private static final long serialVersionUID = 1L; //IDE gets mad w/o this
	
	static String newDisplay; //need to declare this early since I need it for the method
	
	//methods for graphics
    public static void paintWord(Graphics g) {
    	g.drawString(newDisplay, 200, 50);
    }
    public static void paintGallowsOne(Graphics g) { //draw base, shaft, and cross beam, and standing location
    	g.drawLine(300, 300, 350, 300);
    	g.drawLine(325, 300, 325, 200);
    	g.drawLine(325, 200, 375, 200);
    	g.setColor(Color.black);
    }
	public static void paintGallowsTwo(Graphics g) { //draw head
		g.drawLine(100, 100, 200, 200);
		g.setColor(Color.black);
	}
	public static void paintGallowsThree(Graphics g) { //draw body
		g.drawLine(100, 100, 200, 200);
		g.setColor(Color.black);
	}
	public static void paintGallowsFour(Graphics g) { //draw arms
		g.drawLine(100, 100, 200, 200);
		g.setColor(Color.black);
	}
	public static void paintGallowsFive(Graphics g) { //draw legs
		g.drawLine(100, 100, 200, 200);
		g.setColor(Color.black);
	}
	public static void paintGallowsSix(Graphics g) { //open trap door under feet
		g.drawLine(100, 100, 200, 200);
		g.setColor(Color.black);
	}
	
	public static void main(String[] args) {
		
	//creates the frame and stuff
	JFrame frame = new JFrame("Hangman"); //creates the frame
	Canvas canvas = new Canvas(); //creates drawing (which is the canvas)
	canvas.setSize(400, 400);
	frame.add(canvas);
	frame.pack(); //fits canvas to screen
	frame.setVisible(true);
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
	    
	Scanner myWord = new Scanner(System.in); //ask for initial word
	System.out.println("What is your word?");
	String word = myWord.nextLine().toLowerCase();
	System.out.println("\n\n\n\n\n\n\n\n");
	
	/*
	if (word == "rwg") {
		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpGet getRandomWord = new HttpGet("https://www.textfixer.com/tools/random-words.php");
		CloseableHttpResponse response = httpclient.execute(getRandomWord);
		word = response.toString();
	}
	*/
	
	int mistakes = 0; //initialize mistakes
	String display = "";
	newDisplay = "_";
	
	for (int i = 1; i <= word.length(); i++) { //print blank spaces
		display += "_ ";
	}
	System.out.println(display);
	
	char[] guessed = new char[word.length()]; //creates array
	
	Scanner yourGuess = new Scanner(System.in); //ask for a guess
	while (mistakes < 6 && newDisplay.contains("_")) { 
		System.out.println("What is your guess?");
		String guess = yourGuess.nextLine(); //receives guess input
		if (guess.length() == word.length()) {
			if (guess.equals(word)) {
				System.out.println("Wow, you're so cool. You got it!");
				break;
			}
			else {
				mistakes++;
				System.out.println("Incorrect");
			}
		}
		guess = guess.substring(0,1);
		
		ArrayList<Integer> indices = new ArrayList<Integer>(); //creates list of indices of the guessed letters in the word
		for (int i = 0; i < word.length(); i++) {
			if (word.substring(i, i + 1).contains(guess)) {
				indices.add(i);
			}
		}
		
		for (int i = 0; i < indices.size() || indices.size() == 0; i++) {
			if (indices.size() == 0) {  //counting mistakes
				mistakes++;
				System.out.println("\n\n\n\nWrong!\n\n");
				break;
			}
			else {
				System.out.println("\n\n\n\nRight!\n\n");
				guessed[indices.get(i)] = guess.charAt(0); //adds guess to array
			}
		}
		
		newDisplay = "";
		for (int i = 0; i < word.length(); i++) {
			if (!(guessed[i] == 0)) {
				newDisplay += guessed[i];
				newDisplay += " ";
			}
			else {
				newDisplay += "_ ";
			}
		}
		System.out.println(newDisplay);
	}
	
	if (!(newDisplay.contains("_"))) {
		System.out.println("You win!");
	}
	if (mistakes == 6) {
		System.out.println("You lose!");
		System.out.println("The word was: " + word);
	}
	
	yourGuess.close();
	myWord.close();

	}
}
