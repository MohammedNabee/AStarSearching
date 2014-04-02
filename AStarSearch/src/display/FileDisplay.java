package display;

import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import searchSpace.*;

/**
 * FileDisplay    Displays the search space in its current state
 *                to a text file.
 * @author        Mohammed Nabee
 */
public class FileDisplay extends Display 
{
	private static final long serialVersionUID = 1L;
	
	private double time;
	private int moves;
	private double cost;

	/**
	 * Constructor Initializes its search space and sets
	 * the statistic fields to defaults.
	 */ 
	public FileDisplay(ISearchSpace searchSpace) 
	{
		super(searchSpace);
		
		this.time = 0.0;
		this.moves = 0;
		this.cost = 0.0;
	}

	/**
	 * @function displaySearchSpace Displays the search space
	 * in its current state, to a text file with statistics.
	 * 
 	 * @param arg Used to get the file path to write the file.
 	 * 
	 * @exception IOException, FileNotFoundException will be 
	 * caught.
	 * @return None.
	 ***********************************************/ 
	public void displaySearchSpace(Object arg) 
	{
		outputFile = (String)arg;
		File file = new File(outputFile);
		PrintWriter writer = null;
		
		try 
		{
			writer = new PrintWriter(file);
			writer.println();
			writer.println("A* Optimal Path");
			writer.println("=============================");
			writer.println();
			
			ArrayList<ArrayList<Node>> matrix = displayableSpace.getNodeMatrix();
			for (ArrayList<Node> list: matrix)
			{
				for (Node node: list)
				{
					writer.print(node.getRepresentation());
				}
				writer.println();
			}
	        
			writer.println();
			writer.println("=============================");
			writer.println("Time (ms) : "+this.time);
			writer.println("Number of moves: "+this.moves);
			writer.println("Total cost of path: "+this.cost);
			writer.println("=============================");
		} 
		catch (Exception e) 
		{
			JOptionPane.showMessageDialog(new JPanel(), e.getMessage(), "File Error", JOptionPane.ERROR_MESSAGE);
		}
		finally
		{
            writer.close();
		}
	}

	/**
	 * @function setStatistics Sets the statistics for this 
	 * display.
	 * 
 	 * @param time The time taken to calculate the shortest 
 	 * path.
 	 * 
 	 * @param moves The number of moves taken to reach the goal
 	 * in the shortest path.
 	 * 
 	 * @param cost The cost of the shortest path.
 	 * 
	 * @exception None.
	 * @return None.
	 ***********************************************/ 
	public void setStatistics (double time, int moves, double cost)
	{
		this.time = time;
		this.moves = moves;
		this.cost = cost;
	}
}
