package terrain;

/**
 * ITerrain       Specifies the functionality a terrain
 *                should provide, at the minimum.
 * @author        Mohammed Nabee
 */
public interface ITerrain 
{  
	public double getCost();
	public void setCost(double cost);
	public char getRepresentation();
	public void setRepresentation(char representation);
	public String getDescription();
	public void setDescription(String description);
	public boolean getIsWalkable();	
	public void setIsWalkable(boolean isWalkable);
	public String toString();
}
