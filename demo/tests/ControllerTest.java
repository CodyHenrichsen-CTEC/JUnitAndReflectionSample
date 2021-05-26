package demo.tests;

/**
 * Project imports
 */
import demo.controller.Controller;
import java.util.Scanner;
/**
 * Reflection imports
 */
import java.lang.reflect.*;

/**
 * Testing imports
 */
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;



public class ControllerTest
{
	private Controller testedController;
	
	@BeforeEach
	public void setUp() throws Exception
	{
		testedController = new Controller();
	}

	@AfterEach
	public void tearDown() throws Exception
	{
		testedController = null;
	}

	@Test
	public void testController()
	{
		assertTrue(testedController.getClass().getTypeName().equals("demo.controller.Controller"), "Your Controller needs to be in the correct package!");
	}
	
	@Test
	public void testRequiredMethods()
	{
		Method [] methods = testedController.getClass().getDeclaredMethods();
		int requiredCount = 0;
		int requiredVisibility = 0;
		for (Method currentMethod : methods)
		{
			int currentModifier = currentMethod.getModifiers();
			if (currentMethod.getName().equals("start"))
			{
				requiredCount++;
				if(Modifier.isPublic(currentModifier))
				{
					requiredVisibility++;
				}
			}
			if (currentMethod.getName().equals("handleErrors"))
			{
				requiredCount++;
				if(Modifier.isPublic(currentModifier))
				{
					requiredVisibility++;
				}
			}
		}
		assertEquals(requiredCount == 2, "Both required methods not present");
		assertEquals(requiredVisibility == 2, "Both methods need to have public visibility");
	}
	
	@Test
	public void testRequiredDataMembers()
	{
		Field [] dataMembers = testedController.getClass().getDeclaredFields();
		int memberCount = dataMembers.length;
		int requiredVisibility = dataMembers.length * -1;
		boolean hasScanner = false;
		int stringCount = 0;
		
		for (Field currentField : dataMembers)
		{
			int modifiers = currentField.getModifiers();
			
			
			if (currentField.getType().equals(Scanner.class))
			{
				hasScanner = true;
			}
			if (currentField.getType().equals(String.class))
			{
				stringCount++;
			}
			if (Modifier.isPrivate(modifiers))
			{
				requiredVisibility++;
			}
			
		}
		assertTrue(hasScanner, "Your Scanner instance needs to be a data member not a local variable");
		assertFalse(memberCount < 2, "You need at least 3 data members for this assignment: Scanner and two strings");
		
		assertTrue(stringCount >= 2, "You need at least two String data members: and ");
		assertTrue(requiredVisibility == 0, "All data members need to be private as a rule");
	}
	
}
