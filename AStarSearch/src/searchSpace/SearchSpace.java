package searchSpace;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import terrain.TerrainInfo;
import twoDimensionalCoordinate.*;

/**
 * SearchSpace   representation of a 2D search space, made up of 
 *               Nodes, for which a shortest path from two specified
 *               Nodes can be calculated.
 * @author       Mohammed Nabee
 */
public class SearchSpace implements ISearchSpace
{
	private static SearchSpace singleton = null;	
	private ArrayList<ArrayList<Node>> nodeMatrix = new ArrayList<ArrayList<Node>>();
	private NodeFactory nodeFactory = new NodeFactory();
	private Node startNode = null;
	private Node goalNode = null;
	private int noOfMoves;

	/**
	 * Constructor made private to enforce singleton.
	 */
	private SearchSpace(){}

	/**
	 * @function getInstance Gets the singleton instance
	 * of this search space.
	 * 
 	 * @param None.
	 * @exception None.
	 * 
	 * @return singleton.
	 ***********************************************/ 
	public static SearchSpace getInstance()
	{
		if (singleton == null)
		{
			singleton = new SearchSpace();
		}

		return singleton;
	}

	/**
	 * @function loadSearchSpace Sets up the search 
	 * space by scanning an input file representing 
	 * the search space.
	 * 
 	 * @param filePath The file to used.
 	 * 
	 * @exception IOException.
	 * 
	 * @return None.
	 ***********************************************/ 
	public void loadSearchSpace(String filePath) throws IOException
	{
		Scanner scanner = new Scanner(new FileInputStream(filePath));
		try
		{
			while (scanner.hasNextLine())
			{
				String line = scanner.nextLine();
				int lineLength = line.length();
				ArrayList<Node> list = new ArrayList<Node>();
				int y = 0;
				for (int x = 0; x < lineLength; x++) 
				{
					Node node = nodeFactory.makeNode(line.charAt(x));

					if (node != null)
					{
						node.setCoordinate(new Coordinate(x,y));
						list.add(node);

						/* Check if start of goal state */
						if (node.getRepresentation() == TerrainInfo.Start.REPRESENTATION)
						{
							this.startNode = node;
						}
						else if (node.getRepresentation() == TerrainInfo.Goal.REPRESENTATION)
						{
							this.goalNode = node;
						}
					}

				}
				/* Add the row to the matrix */
				nodeMatrix.add(list);
			}
		}
		finally
		{
			scanner.close();
		}
		
		/* Lastly generate the surrounding Nodes for each Node */
		generateSurroundings();
	}

	/**
	 * Helper function to generate the surrounding Nodes.
	 */ 
	private void generateSurroundings()
	{
		int yLen = nodeMatrix.size();
		for (int y = 0; y < yLen; y++)
		{
			int xLen = nodeMatrix.get(y).size();
			for (int x = 0; x < xLen; x++) 
			{
				/* Generate the surrounding coordinates */
				ArrayList<Coordinate> surroundings = new ArrayList<Coordinate>();
				for (int tempY = y-1; tempY <= y+1; tempY++)
				{
					Node node = getNode(new Coordinate(x-1,tempY));
					
					if (node != null)
					{
						if (node.isWalkable())
							surroundings.add(new Coordinate(x-1,tempY));
					}
					/* Don't generate the same node as its own surrounding */
					if (tempY != y)
					{
						node = getNode(new Coordinate(x,tempY));
						if (node != null)
						{
							if (node.isWalkable())
								surroundings.add(new Coordinate(x,tempY));
						}
					}

					node = getNode(new Coordinate(x+1,tempY));
					if (node != null)
					{
						if (node.isWalkable())
							surroundings.add(new Coordinate(x+1,tempY));
					}
				}
				/* Add the Nodes surroundings */
				nodeMatrix.get(y).get(x).setSurroundings(surroundings);
			}
		}
	}

	/**
	 * @function getNode Gets the Node from the search
	 * space at the specified coordinates.
	 * 
 	 * @param coordinate The coordinates of the requested
 	 * Node.
 	 * 
	 * @exception None.
	 * 
	 * @return The Node at given coordinates.
	 ***********************************************/ 
	public Node getNode(Coordinate coordinate)
	{
		if (coordinate.getY() < 0 || coordinate.getY() >= nodeMatrix.size())
		{
			return null;
		}
		else if (coordinate.getX() < 0 || 
				coordinate.getX() >= nodeMatrix.get(coordinate.getY()).size())
		{
			return null;
		}
		else
		{
			return this.nodeMatrix.get(coordinate.getY()).get(coordinate.getX());
		}
	}

	/**
	 * @function resetSearchSpace Resets the search space.
	 * 
 	 * @param None.
	 * @exception None.
	 * @return None.
	 ***********************************************/ 
	public void resetSearchSpace()
	{
		this.nodeMatrix = new ArrayList<ArrayList<Node>>();	
	}

	/**
	 * @function getNodeMatrix Gets the 2D Node matrix 
	 * of the search space.
	 * 
 	 * @param None.
	 * @exception None.
	 * 
	 * @return nodeMatrix the 2D Node Matrix.
	 ***********************************************/ 
	public ArrayList<ArrayList<Node>> getNodeMatrix()
	{
		return this.nodeMatrix;
	}

	/**
	 * @function printSearchSpace Prints the search space 
	 * on the console for debug.
	 * 
 	 * @param None.
	 * @exception None.
	 * @return None.
	 ***********************************************/ 
	public void printSearchSpace()
	{
		
		for (ArrayList<Node> list: nodeMatrix)
		{
			for (Node node: list)
			{
				System.out.print(node.getRepresentation());
			}
			System.out.println();
		}
	}

	/**
	 * @function getStartNode Gets the start Node.
	 * 
 	 * @param None.
	 * @exception None.
	 * 
	 * @return The start Node.
	 ***********************************************/ 
	public Node getStartNode() 
	{
		return this.startNode;
	}

	/**
	 * @function getGoalNode Gets the goal Node.
	 * 
 	 * @param None.
	 * @exception None.
	 * 
	 * @return The goal Node.
	 ***********************************************/ 
	public Node getGoalNode() 
	{
		return this.goalNode;
	}

	/**
	 * @function printShortestPath Changes the representation 
	 * of those Nodes that make up the shortest path.
	 * 
 	 * @param None.
	 * @exception Exception To handle path problems.
	 * @return None.
	 ***********************************************/ 
	public void printShortestPath()
	{
		Node node;
		Node begin = null;
		for(node = getGoalNode(); node!=null; node = node.getParentNode())
		{
			node.setRepresentation(TerrainInfo.SHORTEST_PATH_REPRESENTATION);
			this.noOfMoves ++;
			begin = node;
		}
		
		try
		{
			System.out.println(begin.getCoordinate().toString());
			if (!begin.getCoordinate().toString().equals(getStartNode().getCoordinate().toString()))
			{
				throw new Exception();
			}
		}
		catch (Exception e)
		{
			JOptionPane.showMessageDialog(new JPanel(), "Problem encountered while calculating the shortest path", "Terrain Error", JOptionPane.INFORMATION_MESSAGE);
		}
	}
	
	/**
	 * @function getHorizontalLength Gets the horizontal
	 * length of the search space.
	 * 
 	 * @param None.
	 * @exception None
	 * .
	 * @return The biggest horizontal length.
	 ***********************************************/ 
	public int getHorizontalLength()
	{
		int horizontalLen = 0;
		for (ArrayList<Node> list: this.nodeMatrix)
		{
			if (list.size() > horizontalLen)
			{
				horizontalLen = list.size();
			}
		}
		return horizontalLen;
	}

	/**
	 * @function getVerticalLength Gets the vertical
	 * length of the search space.
	 * 
 	 * @param None.
	 * @exception None
	 * .
	 * @return The vertical length.
	 ***********************************************/ 
	public int getVerticalLength()
	{
		return this.nodeMatrix.size();
	}

	/**
	 * @function shortestPathLength Gets the length
	 * of the shortest path.
	 * 
 	 * @param None.
	 * @exception None
	 * .
	 * @return The shortest path length.
	 ***********************************************/ 
	public int shortestPathLength() 
	{
		return this.noOfMoves;
	}
	
	/**
	 * @function costOfShortestPath Gets the cost
	 * of the shortest path.
	 * 
 	 * @param None.
	 * @exception None
	 * .
	 * @return The cost of shortest path.
	 ***********************************************/ 
	public double costOfShortestPath() 
	{
		return this.goalNode.getScore();
	}
}
