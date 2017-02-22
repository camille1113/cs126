import org.junit.Test;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

/**
 * Created by zhanglanxin on 2/21/17.
 */
public class AStarTest {
    Grid testGrid = Map.getGrid();
    Position testNode1 = testGrid.getStart();
    Position testNode2 = testGrid.getEnd();
    Position testNode3 = testGrid.getObstacles()[0];
    public AStarTest() throws IOException {
    }

    @Test
    public void get_path() throws Exception {
        assertTrue(AStar.get_path()!=null);
        assertEquals(AStar.get_path().get(0).getX(),3);
        assertEquals(AStar.get_path().get(0).getY(),2);
        assertTrue(!AStar.get_path().contains(testGrid.getObstacles()[0]));
        assertFalse(AStar.get_path().contains(testGrid.getObstacles()[1]));
    }

    @Test
    public void findNeighbor() throws Exception {
        Grid testGrid = Map.getGrid();
        Position[][] testMap = Map.createMap(testGrid);
        assertFalse(AStar.findNeighbor(testNode1,testMap).contains(testGrid.getObstacles()[0]));
        assertTrue(!AStar.findNeighbor(testNode1,testMap).contains(testGrid.getStart()));
        assertFalse(AStar.findNeighbor(testNode2,testMap).contains(testGrid.getStart()));
        assertFalse(AStar.findNeighbor(testNode2,testMap).contains(testGrid.getObstacles()[8]));
    }

    @Test
    public void heuristic_cost() throws Exception {
        assertEquals(AStar.heuristic_cost(testNode1,testNode2),5);
        assertEquals(AStar.heuristic_cost(testNode1,testNode3),1);
        assertEquals(AStar.heuristic_cost(testNode2,testNode3),4);
    }

    @Test
    public void find_lowest_f() throws Exception {
        HashSet<Position> testSet = new HashSet<>();
        testSet.add(testNode1);
        testSet.add(testNode2);
        testSet.add(testNode3);
        assertEquals(AStar.find_lowest_f(testSet).getfScore(),testNode1.getfScore());
        assertFalse(AStar.find_lowest_f(testSet)==testNode2);

    }

}