import java.lang.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.PriorityQueue;

/**
 * Created by zhanglanxin on 2/28/17.
 */
public interface AStarInterface<node> {

    HashMap<node, Double> gScore();
    HashMap<node, Double> fScore();
    node getStart();
    node getEnd();
    double heuristicCost(node a, node b);
    Set<node> findNeighbor(node current);


}
