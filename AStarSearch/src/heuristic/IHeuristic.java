package heuristic;
import twoDimensionalCoordinate.Coordinate;

/**
 * IHeuristic   Specifies the functionality a heuristic 
 *              should provide.
 * @author      Mohammed Nabee
 */
public interface IHeuristic 
{
	public float calculateHeuristic(Coordinate start, Coordinate goal);
}
