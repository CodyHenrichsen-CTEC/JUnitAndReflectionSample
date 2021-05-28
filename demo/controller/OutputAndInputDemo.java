package demo.controller;

import java.util.Scanner;

public class OutputAndInputDemo 
{
	private Scanner input;
	
	public OutputAndInputDemo()
	{
		this.input = new Scanner(System.in);
	}
	
	
	public void basicOutput()
	{
		System.out.println("First line");
		System.out.print("second");
		System.out.print("\nThird Line!");
	}
	
	public void inputAndOutput()
	{
		System.out.println("What is your name?");
		String userInput = input.next();
		System.out.println("Hello, " + userInput);
	}
	
	public void basicInput(String madLibPrompt)
	{
		System.out.println(madLibPrompt);
		String userText = input.next();
		System.out.println(userText + " is a " + madLibPrompt);
	}
	
	
	/**
	 * This method should use the .next(), .nextInt(), and .nextLine() methods to interact with your user
	 * Each statement should be on a new line!
	 */
	public void conversationDemo()
	{
		System.out.println("What is your full name?");
		String userText = input.next();
		System.out.println("You typed: " + userText);
		input.nextLine();
		System.out.println("Type your full name with spaces!");
		userText = input.nextLine();
		System.out.println("You typed: " + userText);
		System.out.println("What is the answer to 2 + 4?");
		int number = input.nextInt();
		System.out.println("You typed: " + number);

	}

}
