/*
 * Created by Zachary Niles Peretz
 * Last Updated 10/09/2019
 */
import java.util.Scanner;

public class Hangman {

	public static void main(String[] args) {
		
	Scanner myWord = new Scanner(System.in); //ask for initial word
	System.out.println("What is your word?");
	String word = myWord.nextLine().toLowerCase();
	
	int mistakes = 0; //initialize mistakes
	int correctGuesses = 0; //initializes correct guesses
	String display = "";
	
	for (int i = 1; i <= word.length(); i++) { //print blank spaces
		display += "_ ";
	}
	System.out.println(display);
	
	char[] guessed = new char[word.length()]; //creates array
	
	Scanner yourGuess = new Scanner(System.in); //ask for a guess
	while (mistakes < 6 && correctGuesses < word.length()) { 
		System.out.println("What is your guess?");	
		String guess = yourGuess.nextLine().substring(0, 1); //receives guess input
	
		int position = word.indexOf(guess);
		
		if (position == -1) {  //counting mistakes
			mistakes++;
			System.out.println("Wrong!");
		}
		else {
			correctGuesses++; //counting correct guesses
			System.out.println("Right!");
			guessed[position] = guess.charAt(0); //adds guess to array
		}
		
		String newDisplay = "";
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
	
	if (correctGuesses == word.length()) {
		System.out.println("You win!");
	}
	else {
		System.out.println("You lose!");
	}
	
	yourGuess.close();
	myWord.close();

	}
}
