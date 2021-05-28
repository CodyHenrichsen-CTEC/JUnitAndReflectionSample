package demo.tests;

/**
 * Project imports
 */

import demo.controller.OutputAndInputDemo;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

/**
 * JUnit imports
 */
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class OutputAndInputDemoTest 
{
	private OutputAndInputDemo testedDemo;
	private final PrintStream realOutput = System.out;
	private final InputStream realInput = System.in;

	@BeforeEach
	public void setUp() throws Exception 
	{
		testedDemo = new OutputAndInputDemo();
	}

	@AfterEach
	public void tearDown() throws Exception 
	{
		testedDemo = null;
		System.setIn(realInput);
		System.setOut(realOutput);
	}

	@Test
	public void testBasicOutput() 
	{
		String testSequence = "First line\nsecond\nThird Line!";
		
		ByteArrayOutputStream outputContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outputContent));
		testedDemo.basicOutput();
		String output = outputContent.toString();

		assertEquals(output, testSequence, "Your output for the method should be:\n" + testSequence + "\nBut it was:\n" + output);

	}
	
	@Test
	public void testBasicInput()
	{
		String testedInput = "car";
		String prompt = "Noun";
		
		String testedOutput = prompt + "\n" + testedInput  + " is a " + prompt + "\n";
		
		ByteArrayOutputStream outputContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outputContent));
		ByteArrayInputStream inputContent = new ByteArrayInputStream(testedInput.getBytes());
		System.setIn(inputContent);
		//Reinitialize tested object since the System.in has been changed from the one in the setUp init
		testedDemo = new OutputAndInputDemo();
		testedDemo.basicInput(prompt);
		
		String output = outputContent.toString();
		assertEquals(output, testedOutput, "Your output for the method should be:\n" + testedOutput + "\nBut it was:\n" + output);
	}
	
	@Test
	public void testInputAndOutput()
	{
		String testedInput = "Cody";
		String testedOutput = "What is your name?\nHello, " + testedInput + "\n";
		
		ByteArrayOutputStream outputContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outputContent));
		ByteArrayInputStream inputContent = new ByteArrayInputStream(testedInput.getBytes());
		System.setIn(inputContent);
		//Reinitialize tested object since the System.in has been changed from the one in the setUp init
		testedDemo = new OutputAndInputDemo();

		testedDemo.inputAndOutput();
		
		String output = outputContent.toString();
		assertEquals(output, testedOutput, "Your output for the method should be:\n" + testedOutput + "\nBut it was:\n" + output);
	}
	
	@Test
	public void testConversation()
	{
		String testingInput = "Cody Henrichsen\nCody Henrichsen\n6";
		String testedOutput = "What is your full name?\nYou typed: Cody\nType your full name with spaces!\n"
				+ "You typed: Cody Henrichsen\nWhat is the answer to 2 + 4?\nYou typed: 6\n";
		
		ByteArrayOutputStream outputContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outputContent));
		ByteArrayInputStream inputContent = new ByteArrayInputStream(testingInput.getBytes());
		System.setIn(inputContent);
		
		//Reinitialize tested object since the System.in has been changed from the one in the setUp init
		testedDemo = new OutputAndInputDemo();
		testedDemo.conversationDemo();
				
		String output = outputContent.toString();
		assertEquals(output, testedOutput, "Check your use of .nextLine() Your output for the method should be:\n" 
										+ testedOutput + "\nBut it was:\n" + output);

	}

}
