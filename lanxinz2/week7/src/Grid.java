import java.util.HashMap;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * Created by zhanglanxin on 2/20/17.
 */
public class Grid implements AStarInterface<Position> {

    private int dimension;
    private Position start;
    private Position end;
    private Position[] obstacles;

    public int getDimension() {
        return dimension;
    }

    public Position[] getObstacles() {
        return obstacles;
    }


    @Override
    public HashMap gScore() {
        HashMap<Position, Double> gScore = new HashMap<>();
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                gScore.put(new Position(i, j), Double.MAX_VALUE);
            }
        }
        return gScore;
    }

    @Override
    public HashMap fScore() {
        HashMap<Position, Double> fScore = new HashMap<>();
        for (int i = 0; i <= dimension; i++) {
            for (int j = 0; j <= dimension; j++) {
                fScore.put(new Position(i, j), Double.MAX_VALUE);
            }
        }
        return fScore;
    }

    @Override
    public Position getStart() {
        return start;
    }

    @Override
    public Position getEnd() {
        return end;
    }

    /**
     * find the estimated heuristic cost from current node to goal
     * by using manhattan distance
     * @param a
     * @param b
     * @return
     */
    @Override
    public double heuristicCost(Position a, Position b) {
        return Math.abs(a.getX() - b.getX()) + Math.abs(a.getY() - b.getY());
    }


    /**
     * this method investigates the four neighbors of the current node
     * if it is out of scope or is a obstable, it will not be added to the
     * neighbors to the current node
     *
     * @param current the current node being processed
     * @param
     * @return a set of neighbors
     */
    @Override
    public Set findNeighbor(Position current) {
        Set<Position> neighbors = new HashSet<>();
        int scope = getDimension();
        Position[] obstacles = getObstacles();
        if(current.getX()+1<=scope && !isObstacle(current.getX()+1, current.getY())){
            Position neighbor = new Position(current.getX()+1,current.getY());
            neighbors.add(neighbor);
        }
        if(current.getX()-1>0 && !isObstacle(current.getX()-1, current.getY())){
            Position neighbor = new Position(current.getX()-1,current.getY());
            neighbors.add(neighbor);
        }
        if(current.getY()+1<=scope && !isObstacle(current.getX(), current.getY()+1)){
            Position neighbor = new Position(current.getX(),current.getY()+1);
            neighbors.add(neighbor);
        }
        if(current.getY()-1>0 && !isObstacle(current.getX(), current.getY()-1)){
            Position neighbor = new Position(current.getX(),current.getY()-1);
            neighbors.add(neighbor);
        }
        return neighbors;
    }

    /**
     *
     * @param x
     * @param y
     * @return
     */
    public boolean isObstacle(int x, int y) {
        for(Position positions: getObstacles()){
            if(positions.getX()==x&&positions.getY()==y)
                return true;
        }
        return false;
    }
}

