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
