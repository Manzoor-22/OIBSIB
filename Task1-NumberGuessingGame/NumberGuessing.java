import java.util.Scanner;
import java.util.Random;

class Generator{
 
    int systemInput, userInput, noOfGuesses = 0, range = 100;

    Generator(){
        Random random = new Random();
        this.systemInput = random.nextInt(100) + 1;
    }

    public boolean input(){
    
        if(noOfGuesses < 10){
        
            System.out.print("Guess the number: ");
            this.userInput = NumberGuessing.takeIntegerInput(100);
            noOfGuesses++;
            return false;
        
        } else {
            System.out.println("No more attempts left, try next time.");
            return true;
        
        }
    
    }

    public boolean isCorrect(){
       
        if(systemInput == userInput){

            System.out.println("Congratulations! you have correctly guessed the number.");
            System.out.println("Attempts = " + (noOfGuesses));
            System.out.println("Score = " + (10 - noOfGuesses) * 10);

            return true;

        } else if(noOfGuesses < 10 && userInput > systemInput){

            System.out.println("Oh no!, your guess is incorrect.");
            System.out.println("Your guess " + userInput + " is greater than answer");
            
            return false;
        
        } else if(noOfGuesses < 10 && userInput < systemInput){
            
            System.out.println("Oh no!, your guess is incorrect.");
            System.out.println("Your guess " + userInput + " is less than answer");
            
            return false;

        }

        if(noOfGuesses >= 10){
            
            System.out.println("Out of guesses! better luck next time.");
        
        }

        return false;

    }

}

public class NumberGuessing{

    public static int takeIntegerInput(int Number){

        boolean flag = false;
        int input = 0;

        while(!flag){

            try{

                Scanner sc = new Scanner(System.in);
                input = sc.nextInt();
                flag = true;

                if(flag && input > Number || input < 1){

                    System.out.println("Choose the number between 0 and " + Number);
                    flag = false;
                
                } 
            }
 
            catch(Exception e){
                    
                System.out.println("Enter a integer value!");
                flag = false;
            
            }
        }

        return input;
    
    }
    public static void main(String args[]){

        Scanner sc = new Scanner(System.in);

        System.out.println("\n\n\n\n\nHello!, Welcome to Number Guessing Game.");
        System.out.println("Your task is to guess a number between 1 and 100.");
        System.out.println("You will get 10 chances to guess the number, the less the chances used the more the score you achieve!");;
        
        System.out.println("1. Start\t2. End");
        System.out.print("Select an option: ");

        int choice = takeIntegerInput(2), round = 1;
        
        if (choice == 1) 
		{ 
			while (round == 1) 
			{ 
				Generator game = new Generator(); 
				boolean isMatched = false; 
				boolean isLimitCross = false; 
				System.out.println("\n-----***** Let's begin *****-----"); 
			 
				while (!isMatched && !isLimitCross) 
				{ 

                    isLimitCross = game.input(); 
					isMatched = game.isCorrect(); 

                } 
				
				System.out.println("1.Next Try\n2.Exit"); 
				System.out.print("Enter your choice: "); 
				
				
                round = takeIntegerInput(2); 
				
                if (round != 1) 
                { 

                    System.exit(0); 

                } 

            } 

        } 
		
		else 
		{ 

            System.exit(0); 

        } 

    }

}