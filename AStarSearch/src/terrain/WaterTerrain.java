package terrain;

/**
 * WaterTerrain          This class encapsulates information about the
 *                        Water terrain.
 * @author                Mohammed Nabee
 */
public class WaterTerrain implements ITerrain 
{
	
	private char representation;
	private double cost;
	private boolean isWalkable;
	private String description;

	/**
	 * Constructor initializing Water terrain.
	 */ 
	public WaterTerrain() 
	{
		this.representation = TerrainInfo.Water.REPRESENTATION;
		this.cost = TerrainInfo.Water.MOVECOST;
		this.isWalkable = TerrainInfo.Water.WALKABLE;
		this.description = TerrainInfo.Water.DESCRIPTION;
	}
	
	/**
	 * Accessor and mutator methods for Water
	 * fields
	 */ 
	public double getCost() 
	{
		return this.cost;
	}
	
	public void setCost(double cost)
	{
		this.cost = cost;
	}

	public char getRepresentation() 
	{	
		return this.representation;
	}

	public void setRepresentation(char representation)
	{
		this.representation = representation;
	}
	
	public boolean getIsWalkable() 
	{
		return this.isWalkable;
	}
	
	public void setIsWalkable(boolean isWalkable)
	{
		this.isWalkable = isWalkable;
	}

	public String getDescription() 
	{
		return this.description;
	}

	public void setDescription(String description) 
	{
	    this.description = description;	
	}

	/**
	 * @function toString Provides a string representation
	 * of this object
	 * 
 	 * @param None.
	 * @exception None.
	 * 
	 * @return String representation of this object.
	 ***********************************************/ 
	public String toString() 
	{
		return "WaterTerrain [representation=" + representation + ", cost="
				+ cost + ", isWalkable=" + isWalkable + ", description="
				+ description + "]";
	}

}
