import java.io.IOException;
import java.util.*;
import java.util.PriorityQueue;
import java.util.Comparator;

/**
 * Created by zhanglanxin on 2/21/17.
 */
public class AStar{
    /**
     * this method accept a map and find the shortest way from
     * start to goal by using A* algorithm and returns a string that
     * represents this path.
     * @param newMap
     * @return a string that tells the shortest path
     * @throws IOException
     */
    public static String get_path(AStarInterface newMap) throws IOException  {

        HashMap<Node,Node> cameFrom = new HashMap<>();

        Set<Node> closedSet = new HashSet<>();

        HashMap<Node, Double> gScore = newMap.gScore();
        HashMap<Node, Double> fScore = newMap.fScore();

        //put the Nodes in openSet from small fScore to large fScore using Priority Queue
        Comparator<Node>compare = new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                return (int)(fScore.get(o1)-fScore.get(o2));

            }
        };
        PriorityQueue<Node> openSet = new PriorityQueue<Node>(compare);

        //getStart() and getEnd() returns an object but we need a node
        Node start = (Node)newMap.getStart();
        Node end = (Node)newMap.getEnd();

        gScore.put(start, 0.0);
        fScore.put(start, newMap.heuristicCost(start, end));

        //add the start node to the openSet
        openSet.add(start);

        //find the lowest cost path
        while(!openSet.isEmpty()){
            Node current = openSet.poll();
            if(current.equals(end)){
                String finalPath = reconstructPath(cameFrom, current);
                return finalPath;
            }
            closedSet.add(current);

            Set<Node> neighbor = newMap.findNeighbor(current);
            //if the neighbor is in closedSet, ignore it
            for(Node Node: neighbor){
                if(closedSet.contains(Node))
                    continue;
                //add 1 to the gScore of the neighbor since they are neighbors they are only 1 unit distance apart
                double tentativeGScore = gScore.get(current)+1;
                //if the neigbor is not in the openSet, add it
                //and update its values
                if(!openSet.contains(Node))
                    openSet.add(Node);
                else if (tentativeGScore >= gScore.get(Node))
                    continue; // This is not a better path
                cameFrom.put(Node,current);
                gScore.put(Node, tentativeGScore);
                fScore.put(Node, gScore.get(Node)+ newMap.heuristicCost(Node,newMap.getEnd()));
            }
        }
        // return no path available if it can not get the end from start
        return "No path available";
    }

    /**
     * this method use the hashmap cameFrom and current node
     * which is the goal to trace back to the origin and find the final
     * path and use a string to depict the path
     * @param cameFrom
     * @param current
     * @return a string that represents the final path
     * @throws IOException
     */
    public static String reconstructPath(HashMap cameFrom, Node current) throws IOException {
        List<Node> totalPath = new ArrayList<>();
        totalPath.add(current);
        while(cameFrom.containsKey(current)){
            current = (Node) cameFrom.get(current);
            totalPath.add(current);
        }
        String finalPath = "";
        for(Node node: totalPath){
            finalPath += node.getString()+" ";
        }
        return finalPath;
    }


}
