import java.util.Scanner;

public class Hangman {

	public static void main(String[] args) {
		
	Scanner myWord = new Scanner(System.in); //ask for initial word
	System.out.println("What is your word?");
	String word = myWord.nextLine();
	
	int mistakes = 0; //initialize mistakes
	int correctGuesses = 0; //initializes correct guesses
	String display = "";
	
	for (int i = 1; i < word.length(); i++) { //print black spaces
		display += "_ "; }
	System.out.println(display);
	
	//while (yourGuess.hasNextLine()) { //have multiple while loops, or move print before Scanner
//https://stackoverflow.com/questions/7209110/java-util-nosuchelementexception-no-line-found
	Scanner yourGuess = new Scanner(System.in); //ask for a guess
	System.out.println("What is your guess?");	
	String guess = yourGuess.nextLine();
	
	int position = word.indexOf(guess); //counting mistakes
	if (position == -1) {
		mistakes++;	}
	else
		correctGuesses++; //counting correct guesses
	
	String newDisplay = "";
	
	for (int i = 0; i < word.length(); i++) //I think this bit is the problem
		if (i == position) {
			newDisplay += word.charAt(i);
			newDisplay += display.charAt(1); } //print guessed letter
		else {
			newDisplay += display.charAt(0); //print '_'
			newDisplay += display.charAt(1); }
	
	System.out.println(newDisplay);
	
	yourGuess.close();
	//}
	
	/*while (mistakes < 6 && correctGuesses < word.length());
	if (mistakes == 6) {
		System.out.println("You lose!"); }
	if (correctGuesses == word.length()) {
		System.out.println("You win!"); }
	*/
	myWord.close();
	}
}