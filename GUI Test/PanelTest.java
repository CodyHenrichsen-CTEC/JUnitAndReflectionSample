package art.tests;

/**
 * Project imports
 */

import art.controller.ArtController;
import art.view.ArtPanel;

/**
 * Reflection imports
 */
import java.lang.reflect.*;
import javax.swing.*;

/**
 * JUnit imports
 */
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PanelTest
{
	private ArtPanel testedPanel;

	@BeforeEach
	public void setUp() throws Exception
	{
		testedPanel = new ArtPanel(new ArtController());
	}

	@AfterEach
	public void tearDown() throws Exception
	{
		testedPanel = null;
	}

	@Test
	public void testArtPanel()
	{
		assertTrue(testedPanel instanceof JPanel, "The panel needs to extend JPanel");
		Constructor [] frameConstructors = testedPanel.getClass().getConstructors();
		assertTrue(frameConstructors.length == 1, "You should only have 1 constructor for this class");
		assertTrue(frameConstructors[0].getParameterCount() == 1, "Your constructor needs to have only one parameter");
	}
	
	@Test
	public void testHelperMethods()
	{
		Method[] methods = testedPanel.getClass().getDeclaredMethods();
		assertTrue(methods.length >= 3, "There should at least 3 helper method in the panel: setupPanel, setupListeners, and setupLayout");
		int requiredMethods  = 0;
		int requiredVisibility = 0;
		for (Method currentMethod : methods)
		{
			int currentModifier = currentMethod.getModifiers();
			if (currentMethod.getName().equals("setupLayout"))
			{
				requiredMethods++;
				if(Modifier.isPrivate(currentModifier))
				{
					requiredVisibility++;
				}
			}
			if (currentMethod.getName().equals("setupListeners"))
			{
				requiredMethods++;
				if(Modifier.isPrivate(currentModifier))
				{
					requiredVisibility++;
				}
			}
			if (currentMethod.getName().equals("setupPanel"))
			{
				requiredMethods++;
				if(Modifier.isPrivate(currentModifier))
				{
					requiredVisibility++;
				}
			}
		}
		assertTrue(requiredMethods == 3, "All panel helper methods are not present");
		assertTrue(requiredVisibility == 3, "All helper methods should be private.");
		
	}
	
	@Test
	public void testDataMembers()
	{
		Field[] dataMembers = testedPanel.getClass().getDeclaredFields();
		assertTrue(dataMembers.length >= 4, "You need at least four data members");
		int buttonCount = 0;
		int visibility = dataMembers.length * -1;
		
	
		for (Field field : dataMembers)
		{
			int modifier = field.getModifiers();
			
			if (field.getType().equals(javax.swing.JButton.class))
			{
				buttonCount++;
			}
			if (Modifier.isPrivate(modifier))
			{
				visibility++;
			}
		}
		assertTrue (testedPanel.getLayout() instanceof SpringLayout, "SpringLayout should be used");
		assertTrue (buttonCount > 0, "You need at least one JButton instance");
		assertTrue (visibility == 0, "All data members must be private!");
	}

}
