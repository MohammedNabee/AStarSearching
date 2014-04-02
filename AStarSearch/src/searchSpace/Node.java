package searchSpace;
import java.util.ArrayList;
import terrain.*;
import heuristic.*;
import twoDimensionalCoordinate.*;

/**
 * Node       representation of a Node on the 2D search space.
 * @author    Mohammed Nabee
 */
public class Node implements Cloneable, Comparable<Node>

{
	
	private Coordinate coordinate;
	private IHeuristic heuristic;
	private ITerrain terrain;
	private ArrayList<Coordinate> surroundings;
	private double score;
	private Node parent;

	/**
	 * Constructor initializing fields to defaults.
	 */ 
	public Node()
	{
		this.coordinate = null;
		this.heuristic = null;
		this.terrain = null;
		this.surroundings = null;
		this.parent = null;
	}

 	/**
	 * @function setCoordinate Sets the coordinates
	 * of this Node in the search space.
	 * 
 	 * @param coordinate The coordinates of this Node.
	 * 
	 * @exception None.
	 * @return None.
	 ***********************************************/ 
	public void setCoordinate(Coordinate coordinate) 
	{
		this.coordinate = coordinate;
	}
	
	/**
	 * @function getCoordinate Gets the coordinates
	 * that this Node represents in the search space.
	 * 
	 * @param None.
	 * @exception None.
	 * 
	 * @return Coordinate The coordinates of this Node.
	 ***********************************************/ 
	public Coordinate getCoordinate() 
	{
		return this.coordinate;
	}

	/**
	 * @function setHeuristic Sets the type of heuristic
	 * to be used.
	 * 
	 * @param heuristic The heuristic to set.
	 * 
	 * @exception None.
	 * @return None.
	 ***********************************************/ 
	public void setHeuristic(IHeuristic heuristic) 
	{
		this.heuristic = heuristic;
	}

	/**
	 * @function getHeuristic Gets the calculated heuristic
	 * from this Node to the given Node.
	 * 
	 * @param goal The Node to calculate the heuristic to.
	 * 
	 * @exception None.
	 * 
	 * @return float The calculated heuristic value.
	 ***********************************************/ 	
	public float getHeuristic(Coordinate goal) 
	{
		return this.heuristic.calculateHeuristic(this.coordinate, goal);
	}

	/**
	 * @function setTerrain Gets the type of terrain this
	 * Node represents.
	 * 
	 * @param terrain The terrain this Node represents.
	 * 
	 * @exception None. 
	 * @return None.
	 ***********************************************/ 	
	public void setTerrain(ITerrain terrain)
	{
		this.terrain = terrain;
	}

	/**
	 * @function getCost Gets the movement cost of the 
	 * terrain this Node represents.
	 * 
	 * @param None.
	 * @exception None. 
	 * 
	 * @return the movement cost.
	 ***********************************************/ 	
	public double getCost() 
	{
		return this.terrain.getCost();
	}

	/**
	 * @function setScore Sets the score calculated 
	 * for this Node.
	 * 
	 * @param score.
	 * 
	 * @exception None. 
	 * @return None.
	 ***********************************************/ 	
	public void setScore(double score)
	{
		this.score = score;
	}

	/**
	 * @function getScore Gets the score calculated 
	 * for this Node.
	 * 
	 * @param score. 
	 * @exception None. 
	 * 
	 * @return The score used to determine if this Node
	 * makes up the shortest path.
	 ***********************************************/ 	
	public double getScore()
	{
		return this.score;
	}

	/**
	 * @function getRepresentation Gets the terrain 
	 * representation of this Node.
	 * 
	 * @param None. 
	 * @exception None. 
	 * 
	 * @return The representation of the terrain this
	 * Node represents.
	 ***********************************************/ 	
	public char getRepresentation() 
	{
		return this.terrain.getRepresentation();
	}

	/**
	 * @function setRepresentation Sets the terrain 
	 * representation of this Node.
	 * 
	 * @param newRepresentation The new representation. 
	 * 
	 * @exception None. 
	 * @return None.
	 ***********************************************/ 	
	public void setRepresentation(char newRepresentation) 
	{
		this.terrain.setRepresentation(newRepresentation);
	}

	/**
	 * @function isWalkable Specifies if the terrain 
	 * represented by this Node is a Walkable one.
	 * 
	 * @param None. 
	 * @exception None.
	 *  
	 * @return True if walkable, false if not.
	 ***********************************************/ 	
	public boolean isWalkable()
	{
		return this.terrain.getIsWalkable();
	}
	
	/**
	 * @function setSurroundings Sets a list of Nodes this
	 * Node is surrounded by in the search space.
	 * 
	 * @param surroundings List of surrounding Nodes. 
	 * 
	 * @exception None.
	 * @return None.
	 ***********************************************/ 	
	public void setSurroundings(ArrayList<Coordinate> surroundings) 
	{
		this.surroundings = surroundings;
	}

	/**
	 * @function getSurroundings Gets the surrounding
	 * Nodes.
	 * 
	 * @param None. 
	 * @exception None.
	 *  
	 * @return List of the surrounding Nodes.
	 ***********************************************/ 	
	public ArrayList<Coordinate> getSurroundings() 
	{
		return this.surroundings;
	}
	
	/**
	 * @function setParentNode Sets the parent of this 
	 * Node if this Node is on the shortest path.
	 * 
	 * @param parent The parent of this Node. 
	 * 
	 * @exception None.
	 * @return None.
	 ***********************************************/ 	
	public void setParentNode(Node parent)
	{
		this.parent = parent;
	}

	/**
	 * @function getParentNode Gets the parent of this 
	 * Node if this Node is on the shortest path.
	 * 
	 * @param None. 
	 * @exception None.
	 * 
	 * @return The parent of this Node..
	 ***********************************************/ 	
	public Node getParentNode()
	{
		return this.parent;
	}

	/**
	 * @function makeCopy Clones this Node.
	 * 
	 * @param None. 
	 * @exception CloneNotSupportedException handled.
	 * 
	 * @return The cloned Node.
	 ***********************************************/ 	
	public Node makeCopy()
	{
		Node node = null;

		try 
		{
			node = (Node) this.clone();
		} 
		catch (CloneNotSupportedException e) 
		{
			e.printStackTrace();
		}

		return node;
	}

	/**
	 * @function compareTo Compares this Node with 
	 * another based on the score, used by the comparator.
	 * 
	 * @param Node The Node to compare this Node with. 
	 * 
	 * @exception None.
	 * 
	 * @return The result of the comparison.
	 ***********************************************/ 	
	public int compareTo(Node o) 
	{
		if(this.getScore() > o.getScore())
		{
			return 1;
		}

		else if (this.getScore() < o.getScore())
		{
			return -1;
		}

		else
		{
			/* Nodes are equal */
			return 0;
		}
	}
}
