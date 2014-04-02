package display;

import javax.swing.*;

import searchSpace.*;

/**
 * Display      Specifies the minimum functionality required to display 
 *              the search space.
 * @author      Mohammed Nabee
 */
public abstract class Display extends JFrame
{
	private static final long serialVersionUID = 1L;
	
	protected ISearchSpace displayableSpace;
	protected int SearchSpaceWidth;
	protected String inputfile;
	protected String outputFile;

	/**
	 * Constructor Initializes the search space for the
	 * the shortest path search to be performed.
	 */ 
	public Display(ISearchSpace displayableSpace)
	{
		this.displayableSpace = displayableSpace;
	}
	
	/**
	 * Enforce sub classes to implement this method
	 * which will display the search space.
	 */ 
	public abstract void displaySearchSpace(Object arg);
}
