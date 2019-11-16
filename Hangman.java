/*
 * Hangman --- cool Hangman game
 * @author Zachary Niles Peretz
*/
import java.util.Scanner;
import java.util.ArrayList;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JPanel;

/*
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
*/

public class Hangman extends JPanel{
	private static final long serialVersionUID = 1L; //IDE gets mad w/o this if I have extension
	
	public static String newDisplay; //need to declare this early since I need it for the method
	
	private String _display = "";
	private int _mistakes = 0;
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		g.setColor(Color.black);
		g.setFont(new Font("TimesRoman", Font.PLAIN, 40));
		g.drawString(_display, 10, 300);
		
		g.drawLine(75, 250, 225, 250); //draw base
		g.drawLine(150, 250, 150, 50); //draw pole
		g.drawLine(150, 50, 225, 50); //draw beam
		g.drawLine(150, 100, 175, 50); //draw support
		g.drawLine(225, 50, 225, 75); //draw rope
		if (_mistakes == 6) { 
			g.drawLine(225, 175, 250, 200); //draw right leg
			g.drawLine(223, 80, 220, 85); //left eye /
			g.drawLine(220, 80, 223, 85); //left eye \
			g.drawLine(230, 80, 227, 85); //right eye /
			g.drawLine(227, 80, 230, 85); //right eye \
		}
		if (_mistakes >= 5) {
			g.drawLine(225, 175, 200, 200); //draw left leg
		}
		if (_mistakes >= 4) {
			g.drawLine(225, 125, 250, 150); //draw right arm
		}
		if (_mistakes >= 3) {
			g.drawLine(225, 125, 200, 150); //draw left arm
		}
		if (_mistakes >= 2) {
			g.drawLine(225, 100, 225, 175); //draw body
		}
		if (_mistakes >= 1) {
			g.drawOval(212, 75, 25, 25); //draw head
		}
	}
	
	public static void main(String[] args) {

		Hangman hangman = new Hangman();
		//creates the frame and stuff
		JFrame frame = new JFrame("Hangman"); //creates the frame
		frame.getContentPane().add(hangman);
		frame.setSize(400, 400);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);

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
		
		int mistakes = 0;
		String display = "";
		newDisplay = "_";
		
		for (int i = 0; i < word.length(); i++) { //print blank spaces
			if (word.substring(i, i + 1) == " ") { //print space if there is a space, but doesn't work
				display += "   ";				//possibly because it doesn't count as equal b/c its a space
			}
			else {
				display += "_ ";
			}
		}
		System.out.println(display);
		
		hangman._display = display;
		hangman.repaint();
		
		String[] guessed = new String[word.length()]; //creates correct guess array
		ArrayList<String> guessedWrong = new ArrayList<String>(); //creates incorrect guess ArrayList
		
		Scanner yourGuess = new Scanner(System.in); //ask for a guess
		while (mistakes < 6 && newDisplay.contains("_")) { 
			System.out.println("What is your guess?");
			String guess = yourGuess.nextLine().toLowerCase(); //receives guess input
			
			if (guess.length() == word.length()) { //guessing word outright
				if (guess.equals(word)) {
					System.out.println("Wow, you're so cool. You got it!");
					break;
				}
				else {
					mistakes++;
					hangman._mistakes++;
					hangman.repaint();
					System.out.println("Incorrect");
				}
			}
			guess = guess.substring(0,1); //ensures guess is one word
			
			if (guessedWrong.contains(guess)) {
				System.out.println("You already guessed that, so I won't count it against you");
				mistakes--; //subtracts from mistakes b/c it will add to them later
			}
			else {
				System.out.println("Good job, you didn't already guess that");
			}
			
			ArrayList<Integer> indices = new ArrayList<Integer>(); //creates list of indices of the guessed letters in the word
			for (int i = 0; i < word.length(); i++) { //checks if each index of the word contains the guess, then adds to the indices ArrayList
				if (word.substring(i, i + 1).contains(guess)) {
					indices.add(i);
				}
			}
			
			for (int i = 0; i < indices.size() || indices.size() == 0; i++) {
				if (indices.size() == 0) {  //if indices is empty, meaning word does not contain the guess, then adds mistake
					mistakes++;
					hangman._mistakes++;
					hangman.repaint();
					System.out.println("\n\n\n\nWrong!\n\n");
					guessedWrong.add(guess);
					break;
				}
				else {
					System.out.println("\n\n\n\nRight!\n\n");
					guessed[indices.get(i)] = guess; //adds guess to guessed[]
				}
			}
			
			newDisplay = "";
			for (int i = 0; i < word.length(); i++) {
				if (!(guessed[i] == null)) { //checks if guessed[] contains a letter
					newDisplay += guessed[i]; //adds the letter, which I know guessed[] contains, to newDisplay
					newDisplay += " ";
				}
				else {
					newDisplay += "_ "; //adds blank space
				}
			}
			System.out.println(newDisplay);
			
			hangman._display = newDisplay;
			hangman.repaint();
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

/*
 * Things to add:
 * proper documentation
 * list of already guessed or not guessed letters
 * eventually I'll just have buttons hopefully so it won't be a problem
 * https call
 * graphics
 * make it better with methods (maybe do the Scanner in a method)
 * maybe find a way to get rid of the indices ArrayList
 * other general optimization
 */
