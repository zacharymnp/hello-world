/*
 * Created by Zachary Niles Peretz
 * Last Updated 10/09/2019
 */
import java.util.Scanner;
import java.util.ArrayList;

public class Hangman {

	public static void main(String[] args) {
		
	Scanner myWord = new Scanner(System.in); //ask for initial word
	System.out.println("What is your word?");
	String word = myWord.nextLine();
	
	int mistakes = 0; //initialize mistakes
	int correctGuesses = 0; //initializes correct guesses
	String display = "";
	
	for (int i = 1; i < word.length(); i++) { //print blank spaces
		display += "_ ";
	}
	System.out.println(display);
	
	ArrayList<String> guessedLetters = new ArrayList<String>(); //create list of guessed letters
	
	Scanner yourGuess = new Scanner(System.in); //ask for a guess
	while (mistakes < 6 && correctGuesses < word.length()) { 
		System.out.println("What is your guess?");	
		String guess = yourGuess.nextLine();
		
		if (guessedLetters.contains(guess)) { //checks to see if guess is on list, but doesn't actually stop it from being checked
			System.out.println("You've already guessed that");
		}
	
		int position = word.indexOf(guess);
		int positionAlreadyGuessed = -1;
		if (!(guessedLetters.isEmpty())) {
			positionAlreadyGuessed = word.indexOf(guessedLetters.get(0));
		}
		
		if (position == -1) {  //counting mistakes
			mistakes++;
		}
		else {
			correctGuesses++; //counting correct guesses
		}
		
		String newDisplay = "";
	
		for (int i = 0; i < word.length(); i++) {
			if (i == position) {
				newDisplay += word.charAt(i); //print guessed letter
				newDisplay += " ";
			} 
			if (i == positionAlreadyGuessed) {
				newDisplay +=word.charAt(i); //print letters already guessed
				newDisplay += " ";
			}
			else {
				newDisplay += "_ ";
			}
		}
		System.out.println(newDisplay);
		guessedLetters.add(guess); //adds guess to list
	}
	yourGuess.close();
	myWord.close();

	}
}
