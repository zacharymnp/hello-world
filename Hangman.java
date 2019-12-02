/**
 * Hangman --- cool Hangman game
 * @author Zachary Niles Peretz
 * @version 2.1
*/
import java.util.Scanner;
import java.util.ArrayList;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;

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
	private boolean _winCondition = false;
	private String _word;
	
	public void paint(Graphics g) {
		/**
		 * This method is used to paint the GUI onto the frame
		 * 
		 * @Override
		 * @param g the Graphics context on which to paint
		*/
		
		super.paint(g);
		g.setColor(Color.black);
		g.setFont(new Font("TimesRoman", Font.PLAIN, 40));
		
		g.drawLine(50, 250, 200, 250); //draw base
		g.drawLine(125, 250, 125, 50); //draw pole
		g.drawLine(125, 50, 200, 50); //draw beam
		g.drawLine(125, 100, 150, 50); //draw support
		g.drawLine(200, 50, 200, 75); //draw rope
		if (_mistakes == 6) { 
			g.drawLine(200, 175, 225, 200); //draw right leg
			g.drawLine(198, 80, 195, 85); //left eye /
			g.drawLine(195, 80, 198, 85); //left eye \
			g.drawLine(205, 80, 202, 85); //right eye /
			g.drawLine(202, 80, 205, 85); //right eye \
			g.drawString("Haha, you lose", 10, 300);
			g.drawString("The word was: " + _word, 10, 350);
		}
		else if (_winCondition == false) {
			g.drawString(_display, 10, 300);
		}
		else {
			g.drawString("You win!", 10, 300);
			g.drawString("The word was: " + _display, 10, 350);
		}
		if (_mistakes >= 5) {
			g.drawLine(200, 175, 175, 200); //draw left leg
		}
		if (_mistakes >= 4) {
			g.drawLine(200, 125, 225, 150); //draw right arm
		}
		if (_mistakes >= 3) {
			g.drawLine(200, 125, 175, 150); //draw left arm
		}
		if (_mistakes >= 2) {
			g.drawLine(200, 100, 200, 175); //draw body
		}
		if (_mistakes >= 1) {
			g.drawOval(187, 75, 25, 25); //draw head
		}
	}
	
	public static void main(String[] args) {

		Hangman lines = new Hangman();
		//creates the frame and stuff
		JFrame frame = new JFrame("Hangman"); //creates the frame
		JPanel linesPanel = new JPanel();
		linesPanel.add(lines);
		frame.setSize(500, 400);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		
		GridLayout grid = new GridLayout(7, 4);
		frame.setLayout(grid);
		frame.getContentPane().add(linesPanel);

		JButton aButton = new JButton("A");
		frame.add(aButton);
		JButton bButton = new JButton("B");
		frame.add(bButton);
		JButton cButton = new JButton("C");
		frame.add(cButton);
		JButton dButton = new JButton("D");
		frame.add(dButton);
		JButton eButton = new JButton("E");
		frame.add(eButton);
		JButton fButton = new JButton("F");
		frame.add(fButton);
		JButton gButton = new JButton("G");
		frame.add(gButton);
		JButton hButton = new JButton("H");
		frame.add(hButton);
		JButton iButton = new JButton("I");
		frame.add(iButton);
		JButton jButton = new JButton("J");
		frame.add(jButton);
		JButton kButton = new JButton("K");
		frame.add(kButton);
		JButton lButton = new JButton("L");
		frame.add(lButton);
		JButton mButton = new JButton("M");
		frame.add(mButton);
		JButton nButton = new JButton("N");
		frame.add(nButton);
		JButton oButton = new JButton("O");
		frame.add(oButton);
		JButton pButton = new JButton("P");
		frame.add(pButton);
		JButton qButton = new JButton("Q");
		frame.add(qButton);
		JButton rButton = new JButton("R");
		frame.add(rButton);
		JButton sButton = new JButton("S");
		frame.add(sButton);
		JButton tButton = new JButton("T");
		frame.add(tButton);
		JButton uButton = new JButton("U");
		frame.add(uButton);
		JButton vButton = new JButton("V");
		frame.add(vButton);
		JButton wButton = new JButton("W");
		frame.add(wButton);
		JButton xButton = new JButton("X");
		frame.add(xButton);
		JButton yButton = new JButton("Y");
		frame.add(yButton);
		JButton zButton = new JButton("Z");
		frame.add(zButton);

		Scanner myWord = new Scanner(System.in); //ask for initial word
		System.out.println("What is your word?");
		String word = myWord.nextLine().toLowerCase();
		lines._word = word;
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
		
		lines._display = display;
		lines.repaint();
		
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
					lines._mistakes++;
					lines.repaint();
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
					lines._mistakes++;
					lines.repaint();
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
			
			lines._display = newDisplay;
			lines.repaint();
		}
		
		if (!(newDisplay.contains("_"))) {
			System.out.println("You win!");
			lines._winCondition = true;
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
 * https call
 * make it better with methods (maybe do the Scanner in a method)
 * find a way to get rid of the indices ArrayList perhaps
 * general optimization
 */
