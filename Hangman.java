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
	System.out.println("\n\n\n\n\n\n\n\n");

	
	int mistakes = 0; //initialize mistakes
	String display = "";
	String newDisplay = "_";
	
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
				System.out.println("Wow, you're so cool. You're probably me. You got it!");
				break;
			}
			else {
				mistakes++;
				System.out.println("You're so fudging dumb");
			}
		}
		guess = guess.substring(0,1);
	
		int position = word.indexOf(guess);
		
		if (position == -1) {  //counting mistakes
			mistakes++;
			System.out.println("Wrong!");
		}
		else {
			System.out.println("Right!");
			guessed[position] = guess.charAt(0); //adds guess to array
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
