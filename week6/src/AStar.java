import java.io.IOException;
import java.util.*;

/**
 * Created by zhanglanxin on 2/21/17.
 */
public class AStar {
    public static List<Position> get_path() throws IOException {
        HashMap<Position,Position> cameFrom = new HashMap<>();
        Set<Position> closedSet = new HashSet<>();
        Set<Position> openSet = new HashSet<>();
        Grid newGrid = Map.getGrid();
        newGrid.getStart().setgScore(0);
        newGrid.getStart().setgScore(heuristic_cost(newGrid.getStart(),newGrid.getEnd()));
        Position current = new Position();
        openSet.add(newGrid.getStart());
        Position[][] newMap = Map.createMap(newGrid);
        //find the lowest cost path
        while(!openSet.isEmpty()){
            current = find_lowest_f((HashSet<Position>) openSet);
            if(current==newGrid.getEnd()){
                List<Position> final_path = reconstruct_path(cameFrom, current);
                //printMap(newMap,final_path);
                return final_path;
            }
            openSet.remove(current);
            closedSet.add(current);
            Set<Position> neighbor = findNeighbor(current, newMap);
            //if the neighbor is in closedSet, ignore it
            for(Position position: neighbor){
                if(closedSet.contains(position))
                    continue;
                //add 1 to the gScore of the neighbor since they are neighbors they are only 1 unit distance apart
                int tentative_gScore = current.getgScore()+1;
                //if the neigbor is not in the openSet, add it
                //and update its values
                if(!openSet.contains(position))
                    openSet.add(position);
                else if (tentative_gScore >= position.getgScore())
                    continue; // This is not a better path
                cameFrom.put(position,current);
                position.setgScore(tentative_gScore);
                position.setfScore(position.getgScore()+ heuristic_cost(position,newGrid.getEnd()));
            }
        }
        return null;
    }
    /**
     * this method investigates the four neighbors of the current node
     * if it is out of scope or is a obstable, it will not be added to the
     * neighbors to the current node
     * @param current the current node being processed
     * @param newMap a 2d array which stores the obstacles
     * @return an array of neighbors
     */
    public static Set<Position> findNeighbor(Position current, Position[][] newMap){
        Set<Position> neighbors= new HashSet<>();
        if(current.getX()+1 < 10 && newMap[current.getX()+1][current.getY()].getgScore()!=-1){
            neighbors.add(newMap[current.getX()+1][current.getY()]);
        }
        if(current.getX()-1 >= 0 && newMap[current.getX()-1][current.getY()].getgScore()!=-1){
            neighbors.add(newMap[current.getX()-1][current.getY()]);
        }
        if(current.getY()+1 < 10 && newMap[current.getX()][current.getY()+1].getgScore()!=-1){
            neighbors.add(newMap[current.getX()][current.getY()+1]);
        }
        if(current.getY()-1 >= 0 && newMap[current.getX()][current.getY()-1].getgScore()!=-1){
            neighbors.add(newMap[current.getX()][current.getY()-1]);
        }
        return neighbors;
    }
    /**
     * find the estimated heuristic cost from current node to goal
     * by using manhattan distance
     * @param current the current node being processed
     * @param goal
     * @return the estimated heuristic cost
     */
    public static int heuristic_cost(Position current,Position goal){
        return Math.abs(current.getX()-goal.getX())+Math.abs(current.getY()-goal.getY());
    }
    /**
     * find the position with lowest fScore in the openSet
     * @param openSet
     * @return position with lowest fScore
     */
    public static Position find_lowest_f(HashSet<Position> openSet){
        Position temp = new Position();
        temp.setfScore(Integer.MAX_VALUE);
        for(Position position: openSet){
            if(temp.getfScore()>position.getfScore())
                temp = position;
        }
        return temp;
    }
    /**
     * this method reconstruct the path using the values in hashmap cameFrom
     * and trace back to the origin to construct the final path which is the
     * least cost path
     * @param cameFrom
     * @param current
     * @return a list of steps
     * @throws IOException
     */
    public static List<Position> reconstruct_path(HashMap cameFrom, Position current) throws IOException {
        List<Position> total_path = new ArrayList<>();
        total_path.add(Map.getGrid().getEnd());
        while(cameFrom.containsKey(current)){
            current = (Position) cameFrom.get(current);
            total_path.add(current);
        }
        return total_path;
    }
    /**
     * this method draws the final path
     * @param newMap
     * @param final_path
     */
    public static void printMap(Position[][] newMap, List<Position> final_path){
        String[][] path = new String[10][10];
        for(int i = 0; i < 10; i++){
            for(int j = 0; j < 10; j++){
                if(newMap[i][j].getgScore()==-1)
                    path[i][j] = " O ";
                else
                    path[i][j] = " - ";
                for(Position positions: final_path){
                    if(positions.getX()==i && positions.getY()==j)
                        path[i][j] = " X ";
                }
            }
        }
        for(int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                System.out.print(path[i][j]);
            }
            System.out.println();
        }
    }
}
