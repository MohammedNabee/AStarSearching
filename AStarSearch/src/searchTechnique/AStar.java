package searchTechnique;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

import searchSpace.*;
import twoDimensionalCoordinate.Coordinate;

/**
 * AStar              Provides implementation of A* Search algorithm
 * @author            Mohammed Nabee
 */
public class AStar implements ISearchTechnique 
{
	private ISearchSpace searchSpace = null;
	private Node startState = null;
	private Node goalState = null;
	
	/**
	 * Constructor Gets the singleton search space.
	 */ 
	public AStar()
	{
		this.searchSpace = SearchSpace.getInstance();
		this.startState = searchSpace.getStartNode();
		this.goalState = searchSpace.getGoalNode();
	}
	
	public AStar(ISearchSpace searchSpace)
	{
		this.searchSpace = searchSpace;
		this.startState = searchSpace.getStartNode();
		this.goalState = searchSpace.getGoalNode();
	}
	
	/**
	 * @function search Searches the search space using 
	 * the A* technique to find the shortest path.
	 * 
 	 * @param None.
	 * @exception None.
	 * @return None.
	 ***********************************************/ 
	public void search() 
	{	 
        Set<Node> explored = new HashSet<Node>();
        PriorityQueue<Node> queue = new PriorityQueue<Node>();

        /* cost from start */
        startState.setScore(0);
        queue.add(startState);
        boolean found = false;

        while((!queue.isEmpty())&&(!found)){

           /* the node having the lowest score */
           Node current = queue.poll();
           explored.add(current);

           /* goal found */
           if(current.getRepresentation() == searchSpace.getGoalNode().getRepresentation())
           {
               found = true;
           }

           /* check surroundings of current node */
           for(Coordinate coordinate : current.getSurroundings())
           {
               Node child = searchSpace.getNode(coordinate);
               double temp_g_scores = current.getScore() + child.getCost();
               double temp_f_scores = temp_g_scores + child.getHeuristic(this.goalState.getCoordinate());

               /* ship if child node has been evaluated and the newer score is higher */            
               if((explored.contains(child)) && (temp_f_scores >= child.getScore()))
               {
                   continue;
               }
               /*else if child node is not in queue or newer score is lower*/
               else if((!queue.contains(child)) || (temp_f_scores < child.getScore()))
               {
            	   child.setParentNode(current);
                   child.setScore(temp_f_scores);

                   if(queue.contains(child))
                   {
                       queue.remove(child);
                   }

                   queue.add(child);
               }
            }
        }
	}
}
