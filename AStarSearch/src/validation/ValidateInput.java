package validation;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import terrain.TerrainInfo;

/**
 * ValidateInput          Validates the input file and handles any
 *                        issues encountered during the validation.
 * @author                Mohammed Nabee
 */
public class ValidateInput 
{

	private String filePath;

	public ValidateInput(String filePath)
	{
		this.filePath = filePath;
	}
	
	/**
	 * @function isValid Scans an input file checking
	 * that the file is valid.
	 * 
 	 * @param None.
	 * 
	 * @exception FileNotFoundException.
	 * 
	 * @return true if the file was valid, false otherwise.
	 ***********************************************/ 
	public boolean isValid() throws FileNotFoundException
	{
		boolean foundGoal = false;
		boolean foundStart = false;
		boolean result = true;
		
		Scanner scanner = new Scanner(new FileInputStream(filePath));
		try
		{

			while (scanner.hasNextLine())
			{
				String line = scanner.nextLine();
				int lineLength = line.length();
				
				for (int x = 0; x < lineLength; x++) 
				{
					char ch = line.charAt(x);
					
					/* Ensure no invalid terrains */
					if (ch != TerrainInfo.FlatLands.REPRESENTATION &&
					    ch != TerrainInfo.Forest.REPRESENTATION &&
						ch != TerrainInfo.Mountain.REPRESENTATION &&
						ch != TerrainInfo.Water.REPRESENTATION &&
						ch != TerrainInfo.Goal.REPRESENTATION &&
						ch != TerrainInfo.Start.REPRESENTATION)
					{
						throw new InputValidationException("Unknown Terrain found while validating input file");
					}
					
					/* Ensure only one goal state */
					if (ch == TerrainInfo.Goal.REPRESENTATION)
					{
						if (!foundGoal)
						{
							foundGoal = true;
						}
						else
						{
							throw new InputValidationException("More than one Goal states found while validating input file"); 
						}
					}
					
					/* Ensure only one Start state */
					if (ch == TerrainInfo.Start.REPRESENTATION)
					{
						if (!foundStart)
						{
							foundStart = true;
						}
						else
						{
							throw new InputValidationException("More than one Start states found while validating input file"); 
						}
					}	
				}
			}
			
			/* Ensure a goal state was found */
			if (!foundGoal)
			{
				throw new InputValidationException("No Goal state found while validating input file");
			}
			/* Ensure a start state was found */
			if (!foundStart)
			{
				throw new InputValidationException("No Start state found while validating input file");
			}
		}
		catch (InputValidationException err)
		{
			/* Show the message to the user */
			JOptionPane.showMessageDialog(new JPanel(), err.getMessage(), "Validation Error", JOptionPane.ERROR_MESSAGE);
			result = false;
		}
		finally
		{
			scanner.close();
		}

		return result;
	}
}
