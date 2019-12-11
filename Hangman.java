/**
 * Hangman --- cool Hangman game
 * @author Zachary Niles Peretz
 * @version 2.3
*/
//import java.util.Scanner;
import java.util.ArrayList;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;

/*
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
*/

public class Hangman extends JPanel implements ActionListener {
	private static final long serialVersionUID = 1L; //IDE gets mad w/o this if I have extension
	
	public static String newDisplay = "";
	public static String guess = "";
	public static int mistakes = 0;
	
	private static int _mistakes = 0;
	private static boolean _winCondition = false;
	private static String _word = "";
	private static boolean _isChoosingWord = true;
	
	public static ArrayList<Integer> indices = new ArrayList<Integer>(); //creates list of indices of the guessed letters in the word
	public static ArrayList<String> _guessed = new ArrayList<String>(); //creates correct guess array


	
	public void paint(Graphics g) {
		/**
		 * This method is used to paint the GUI onto the frame
		 * 
		 * @Override
		 * @param g the Graphics context on which to paint
		*/
		
		super.paint(g);
		g.setColor(Color.black);
		g.setFont(new Font("TimesRoman", Font.PLAIN, 20));
		
		g.drawLine(50, 250, 200, 250); //draw base
		g.drawLine(100, 250, 100, 50); //draw pole
		g.drawLine(100, 50, 200, 50); //draw beam
		g.drawLine(100, 100, 150, 50); //draw support
		g.drawLine(200, 50, 200, 50); //draw rope
		
		if (_mistakes == 6) { 
			g.drawLine(200, 175, 250, 225); //draw right leg
			g.drawLine(198, 80, 195, 85); //left eye /
			g.drawLine(195, 80, 198, 85); //left eye \
			g.drawLine(205, 80, 202, 85); //right eye /
			g.drawLine(202, 80, 205, 85); //right eye \
			g.drawString("Haha, you lose", 10, 300);
			g.drawString("The word was: " + _word, 10, 350);
		}
		else if (_winCondition == false) {
			g.drawString(newDisplay, 10, 300);
		}
		else {
			g.drawString("You win!", 10, 300);
			g.drawString("The word was: " + _word, 10, 350);
		}
		
		if (_mistakes >= 5) {
			g.drawLine(200, 175, 150, 225); //draw left leg
		}
		if (_mistakes >= 4) {
			g.drawLine(200, 100, 250, 150); //draw right arm
		}
		if (_mistakes >= 3) {
			g.drawLine(200, 100, 150, 150); //draw left arm
		}
		if (_mistakes >= 2) {
			g.drawLine(200, 100, 200, 175); //draw body
		}
		if (_mistakes >= 1) {
			g.drawOval(177, 50, 50, 50); //draw head
		}
	}
	
	public void actionPerformed(ActionEvent e) {
		/**
		 * This method is used to perform actions after a button is clicked
		 * 
		 * @override
		 * @param e the event to be processed, which is the click of a button
		*/
		
		JButton source = (JButton)(e.getSource());
		if (_isChoosingWord == true) {
			if (source.getText().contains("Enter")) {
				_isChoosingWord = false;
				source.setVisible(false);
				repaint();
			}
			else {
				_word = _word.concat(source.getText()).toLowerCase();
				newDisplay = newDisplay.concat("_ ");
			}
		}
		else {
			if (_mistakes < 6 && _winCondition == false) { 
				guess = source.getText().toLowerCase();
				source.setVisible(false);
				
				for (int i = 0; i < _word.length(); i++) { //checks if each index of the word contains the guess, then adds to the indices ArrayList
					if (_word.substring(i, i + 1).contains(guess)) {
						indices.add(i);
					}
				}
				
				for (int i = 0; i < indices.size() || indices.size() == 0; i++) {
					if (indices.size() == 0) {  //if indices is empty, meaning word does not contain the guess, then adds mistake
						_mistakes++;
						break;
					}
					else {
						_guessed.add(indices.get(i), guess); //adds guess to _guessed
					}
				}
				
				newDisplay = "";
				
				for (int i = 0; i < _word.length(); i++) {
					try {
					if (!(_guessed.get(i) == null)) { //checks if _guessed contains a letter
						newDisplay = newDisplay.concat(_guessed.get(i)); //adds the letter, _which I know guessed contains, to newDisplay
						newDisplay = newDisplay.concat(" ");
					}
					else {
						newDisplay = newDisplay.concat("_ "); //adds blank space
					}
					}
					catch(Exception el) {
						System.out.println("Caught exception" + el);
					}
				}
			}
			repaint();
		}
	}
	
	public static void main(String[] args) {

		Hangman hangman = new Hangman();
		//creates the frame and stuff
		JFrame frame = new JFrame("Hangman");
		frame.getContentPane().add(hangman);
		frame.setSize(516, 400);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		hangman.setLayout(null); //need this to use absolute positioning for buttons
		
		Hangman buttonClick = new Hangman();
		JButton aButton = new JButton("A");
		aButton.addActionListener(buttonClick);
		hangman.add(aButton);
		aButton.setBounds(300, 0, 50, 50);
		JButton bButton = new JButton("B");
		bButton.addActionListener(buttonClick);
		hangman.add(bButton);
		bButton.setBounds(350, 0, 50, 50);
		JButton cButton = new JButton("C");
		cButton.addActionListener(buttonClick);
		hangman.add(cButton);
		cButton.setBounds(400, 0, 50, 50);
		JButton dButton = new JButton("D");
		dButton.addActionListener(buttonClick);
		hangman.add(dButton);
		dButton.setBounds(450, 0, 50, 50);
		JButton eButton = new JButton("E");
		eButton.addActionListener(buttonClick);
		hangman.add(eButton);
		eButton.setBounds(300, 50, 50, 50);
		JButton fButton = new JButton("F");
		fButton.addActionListener(buttonClick);
		hangman.add(fButton);
		fButton.setBounds(350, 50, 50, 50);
		JButton gButton = new JButton("G");
		gButton.addActionListener(buttonClick);
		hangman.add(gButton);
		gButton.setBounds(400, 50, 50, 50);
		JButton hButton = new JButton("H");
		hButton.addActionListener(buttonClick);
		hangman.add(hButton);
		hButton.setBounds(450, 50, 50, 50);
		JButton iButton = new JButton("I");
		iButton.addActionListener(buttonClick);
		hangman.add(iButton);
		iButton.setBounds(300, 100, 50, 50);
		JButton jButton = new JButton("J");
		jButton.addActionListener(buttonClick);
		hangman.add(jButton);
		jButton.setBounds(350, 100, 50, 50);
		JButton kButton = new JButton("K");
		kButton.addActionListener(buttonClick);
		hangman.add(kButton);
		kButton.setBounds(400, 100, 50, 50);
		JButton lButton = new JButton("L");
		lButton.addActionListener(buttonClick);
		hangman.add(lButton);
		lButton.setBounds(450, 100, 50, 50);
		JButton mButton = new JButton("M");
		mButton.addActionListener(buttonClick);
		hangman.add(mButton);
		mButton.setBounds(300, 150, 50, 50);
		JButton nButton = new JButton("N");
		nButton.addActionListener(buttonClick);
		hangman.add(nButton);
		nButton.setBounds(350, 150, 50, 50);
		JButton oButton = new JButton("O");
		oButton.addActionListener(buttonClick);
		hangman.add(oButton);
		oButton.setBounds(400, 150, 50, 50);
		JButton pButton = new JButton("P");
		pButton.addActionListener(buttonClick);
		hangman.add(pButton);
		pButton.setBounds(450, 150, 50, 50);
		JButton qButton = new JButton("Q");
		qButton.addActionListener(buttonClick);
		hangman.add(qButton);
		qButton.setBounds(300, 200, 50, 50);
		JButton rButton = new JButton("R");
		rButton.addActionListener(buttonClick);
		hangman.add(rButton);
		rButton.setBounds(350, 200, 50, 50);
		JButton sButton = new JButton("S");
		sButton.addActionListener(buttonClick);
		hangman.add(sButton);
		sButton.setBounds(400, 200, 50, 50);
		JButton tButton = new JButton("T");
		tButton.addActionListener(buttonClick);
		hangman.add(tButton);
		tButton.setBounds(450, 200, 50, 50);
		JButton uButton = new JButton("U");
		uButton.addActionListener(buttonClick);
		hangman.add(uButton);
		uButton.setBounds(300, 250, 50, 50);
		JButton vButton = new JButton("V");
		vButton.addActionListener(buttonClick);
		hangman.add(vButton);
		vButton.setBounds(350, 250, 50, 50);
		JButton wButton = new JButton("W");
		wButton.addActionListener(buttonClick);
		hangman.add(wButton);
		wButton.setBounds(400, 250, 50, 50);
		JButton xButton = new JButton("X");
		xButton.addActionListener(buttonClick);
		hangman.add(xButton);
		xButton.setBounds(450, 250, 50, 50);
		JButton yButton = new JButton("Y");
		yButton.addActionListener(buttonClick);
		hangman.add(yButton);
		yButton.setBounds(300, 300, 50, 50);
		JButton zButton = new JButton("Z");
		zButton.addActionListener(buttonClick);
		hangman.add(zButton);
		zButton.setBounds(350, 300, 50, 50);
		JButton enterButton = new JButton("Enter");
		enterButton.addActionListener(buttonClick);
		hangman.add(enterButton);
		enterButton.setBounds(400, 300, 100, 50);
		hangman.repaint();
				
		/*
		
		Scanner myWord = new Scanner(System.in); //ask for initial word
		System.out.println("What is your word?");
		String word = myWord.nextLine().toLowerCase();
		_word = word;
		System.out.println("\n\n\n\n\n\n\n\n");
		
		/*
		if (word == "rwg") {
			CloseableHttpClient httpclient = HttpClients.createDefault();
			HttpGet getRandomWord = new HttpGet("https://www.textfixer.com/tools/random-words.php");
			CloseableHttpResponse response = httpclient.execute(getRandomWord);
			word = response.toString();
		}
		//if adding back in need to stop this bit above
		
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
		
		hangman.repaint();
		
		String[] guessed = new String[word.length()]; //creates correct guess array
		ArrayList<String> guessedWrong = new ArrayList<String>(); //creates incorrect guess ArrayList
		
		Scanner yourGuess = new Scanner(System.in); //ask for a guess
		while (mistakes < 6 && newDisplay.contains("_")) { 
			System.out.println("What is your guess?");
			guess = yourGuess.nextLine().toLowerCase(); //receives guess input
			
			if (guess.length() == word.length()) { //guessing word outright
				if (guess.equals(word)) {
					System.out.println("Wow, you're so cool. You got it!");
					break;
				}
				else {
					mistakes++;
					_mistakes++;
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
			
			for (int i = 0; i < word.length(); i++) { //checks if each index of the word contains the guess, then adds to the indices ArrayList
				if (word.substring(i, i + 1).contains(guess)) {
					indices.add(i);
				}
			}
			
			for (int i = 0; i < indices.size() || indices.size() == 0; i++) {
				if (indices.size() == 0) {  //if indices is empty, meaning word does not contain the guess, then adds mistake
					mistakes++;
					_mistakes++;
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
				if (!(guessed[i] == null)) { //checks if guessed[] contains a letter //I think this throws an exception now, but I'll probably delete it so I won't bother fixing
					newDisplay += guessed[i]; //adds the letter, which I know guessed[] contains, to newDisplay
					newDisplay += " ";
				}
				else {
					newDisplay += "_ "; //adds blank space
				}
			}
			System.out.println(newDisplay);
			
			hangman.repaint();
		}
		
		if (!(newDisplay.contains("_"))) {
			System.out.println("You win!");
			_winCondition = true;
		}
		if (mistakes == 6) {
			System.out.println("You lose!");
			System.out.println("The word was: " + word);
		}
		
		yourGuess.close();
		myWord.close();
		*/
	}
}

/*
 * Things to add:
 * fix graphics not working with multi-word things
 * fix bug that complete guesses that aren't length of actual word just take in first letters
 * spaces show up automatically
 * proper documentation
 * https call
 * make it better with methods (maybe do the Scanner in a method)
 * find a way to get rid of the indices ArrayList perhaps
 * general optimization
 */
