import java.io.IOException;
import display.*;

import searchSpace.*;

/**
 * Display      Provides a starting point for this application.
 * @author      Mohammed Nabee
 */
public class AstarSearchApp 
{
	public static void main(String[] args) throws IOException 
	{
		SearchSpace space = SearchSpace.getInstance();
	    new GUIDisplay(space);
	}
}
