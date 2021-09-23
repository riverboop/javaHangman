//Hangman Game by River Dyke, 2021. For AP Computer Science.
import java.util.Scanner;
public class hangman {
    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);
        String guess = null;
        String secret = null;
        String hidden = "";
        String misses = "";
        int missedNum = 0;
        boolean win = false;
        boolean lose = false;
        boolean hit = false;
        //start
        clearScreen();
        System.out.println("Hangman by River Dyke");
        System.out.println("--------------------------");
        System.out.println("Input secret word to guess: ");
        //get secret word
        secret = scan.nextLine();
        final String answer = secret;
        //create * version of secret (hidden)
        for (int i = 0; i < secret.length(); i++){
            if (secret.substring(i, i+1).equals(" ")){
                hidden += " ";
            }
            else{
                hidden += "*";
            }
        }
        clearScreen();
        makeMan(0);
        while (win == false && lose == false){
            System.out.println("Word: " + hidden);
            System.out.println("--------------------------");
            //get the player's guess
            System.out.println("Guess: ");
            guess = scan.nextLine();
            //check if guess is valid
            if (guess.indexOf("*") == -1 && guess.indexOf(" ") == -1 && guess.length() == 1 ){
                clearScreen();
                if (secret.indexOf(guess) != -1) {
                    while(secret.indexOf(guess) != -1){
                        //check for any of the guessed letter in the secret word until there is no more, then acknowledge the correct guess
                        hidden = hidden.substring(0,secret.indexOf(guess)) + guess + hidden.substring(secret.indexOf(guess)+1);
                        secret = secret.substring(0,secret.indexOf(guess)) + "*" + secret.substring(secret.indexOf(guess)+1);
                    }
                    hit = true;
                }
                if (hit == false){
                    //check if letter was already guessed
                    if (misses.indexOf(guess) != -1 || hidden.indexOf(guess) != -1){
                        System.out.println("Already guessed " + guess + "!");
                    }
                    //add the miss if the player was incorrect
                    else{
                        misses += guess + " ";
                        missedNum = (misses.replaceAll("\\s","")).length();
                    }
                }
                //create the hangman, set hit to false, and print any misses
                makeMan(missedNum);
                hit = false;
                System.out.println("Misses: " + misses);

                //win condition
                if (hidden.indexOf("*") == -1){
                    win=true;
                    scan.close();
                    System.out.println("You Win!");
                    System.out.println("The word was: " + answer);
                }
                //lose condition
                if (missedNum >5){
                    lose = true;
                    scan.close();
                    System.out.println("You Lose!");
                    System.out.println("The word was: " + answer);
                }
            }
            else{
                clearScreen();
                makeMan(missedNum);
                System.out.println("Please guess a valid character");
            }
        }
    }
    public static void makeMan(int parts){
        //prints a different hanged man depending on misses
        System.out.println("--------");
        switch (parts){
            case 0:
                System.out.println("        |");
                System.out.println("        |");
                System.out.println("        |");
                break;
            case 1:
                System.out.println(" O      |");
                System.out.println("        |");
                System.out.println("        |");
                break;
            case 2:
                System.out.println(" O      |");
                System.out.println(" |      |");
                System.out.println("        |");
                break; 
            case 3:
                System.out.println(" O      |");
                System.out.println("/|      |");
                System.out.println("        |");
                break; 
            case 4:
                System.out.println(" O      |");
                System.out.println("/|\\     |");
                System.out.println("        |");
                break; 
            case 5:
                System.out.println(" O      |");
                System.out.println("/|\\     |");
                System.out.println("/       |");
                break; 
            case 6:
                System.out.println(" O      |");
                System.out.println("/|\\     |");
                System.out.println("/ \\     |");
                break; 
        }
        System.out.println("        |");
        System.out.println("     ------");
    }
    public static void clearScreen() {  
        //clears the screen, from https://stackoverflow.com/questions/2979383/how-to-clear-the-console
        System.out.print("\033[H\033[2J");  
        System.out.flush();  
    }  
}