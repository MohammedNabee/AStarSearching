package searchSpace;
import heuristic.*;
import terrain.*;

/**
 * NodeFactory  Produces Nodes by cloning then configures the Node 
 *              for the specified terrain.
 * @author      Mohammed Nabee
 */
public class NodeFactory 
{
	private Node masterNode;

	/**
	 * Constructor Creates the master Node that 
	 * others will be cloned from.
	 */ 
	public NodeFactory()
	{
		this.masterNode = new Node();
	}

	/**
	 * @function makeNode Produces a Node by cloning the 
	 * master, then sets the terrain info the produced
	 * node will represent.
	 * 
 	 * @param representation The terrain representation, 
 	 * the type of Node to produce.
	 * 
	 * @exception None.
	 * 
	 * @return Node The Node representing the terrain.
	 ***********************************************/ 
	public Node makeNode(char representation)
	{
		Node node = masterNode.makeCopy();
		
		if (node != null)
		{
			node .setHeuristic(new ManhattanHeuristic());

			if (representation == TerrainInfo.Start.REPRESENTATION)
			{
				FlatlandsTerrain start = new FlatlandsTerrain();
				start.setRepresentation(TerrainInfo.Start.REPRESENTATION);
				start.setDescription(TerrainInfo.Start.DESCRIPTION);
				node.setTerrain(start);
			}
			else if (representation == TerrainInfo.Goal.REPRESENTATION)
			{
				FlatlandsTerrain goal = new FlatlandsTerrain();
				goal.setRepresentation(TerrainInfo.Goal.REPRESENTATION);
				goal.setDescription(TerrainInfo.Goal.DESCRIPTION);
				node.setTerrain(goal);
			}
			else if (representation == TerrainInfo.FlatLands.REPRESENTATION)
			{
				node.setTerrain(new FlatlandsTerrain());
			}
			else if (representation == TerrainInfo.Forest.REPRESENTATION)
			{
				node.setTerrain(new ForestTerrain());
			}
			else if (representation == TerrainInfo.Mountain.REPRESENTATION)
			{
				node.setTerrain(new MountainTerrain());
			}
			else if (representation == TerrainInfo.Water.REPRESENTATION)
			{
				node.setTerrain(new WaterTerrain());
			}
			else
			{
				node = null;
			}
		}

		return node;
	}
}
