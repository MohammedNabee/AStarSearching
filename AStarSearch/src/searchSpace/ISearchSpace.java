package searchSpace;

import java.io.IOException;
import java.util.ArrayList;

import twoDimensionalCoordinate.Coordinate;

/**
 * ISearchSpace   Specifies the functionality a search space
 *                should provide, at the minimum.
 * @author        Mohammed Nabee
 */
public interface ISearchSpace 
{
	public void loadSearchSpace(String filePath) throws IOException;
	public void resetSearchSpace();
	public ArrayList<ArrayList<Node>> getNodeMatrix();
	public Node getStartNode();
	public Node getGoalNode();
	public Node getNode(Coordinate coordinate);
	public void printSearchSpace();
	public int getHorizontalLength();
	public int getVerticalLength();
	public void printShortestPath();
	public int shortestPathLength();
	public double costOfShortestPath();
}
