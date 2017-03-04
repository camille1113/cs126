import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

/**
 * Created by zhanglanxin on 3/3/17.
 */
public class GridTest {
    Grid testgrid = Main.getGrid();
    Position testPosition1 = testgrid.getStart();
    Position testPosition2 = testgrid.getEnd();
    Position testObstacle3 = testgrid.getObstacles()[1];
    Position testObstacle4 = testgrid.getObstacles()[2];
    Position testObstacle5 = testgrid.getObstacles()[3];

    public GridTest() throws IOException {
    }

    @Test
    public void getDimension() throws Exception {
        assertEquals(testgrid.getDimension(),10);
    }

    @Test
    public void heuristicCost() throws Exception {
        assertEquals(testgrid.heuristicCost(testPosition1,testObstacle3),2.0);
        assertEquals(testgrid.heuristicCost(testObstacle3,testPosition2),3.0);
        assertEquals(testgrid.heuristicCost(testObstacle4,testObstacle5),1.0);
        assertEquals(testgrid.heuristicCost(testPosition2,testObstacle3),3.0);

    }

    @Test
    public void findNeighbor() throws Exception {
        //end is not neighbor og start
        assertFalse(testgrid.findNeighbor(testPosition1).contains(testPosition2));
        //obstacles cannot be neighbor
        assertFalse(testgrid.findNeighbor(testPosition2).contains(testObstacle4));
        assertFalse(testgrid.findNeighbor(testObstacle3).contains(testObstacle3));
        assertFalse(testgrid.findNeighbor(testPosition1).contains(testObstacle5));
    }

    @Test
    public void isObstacle() throws Exception {
        assertTrue(testgrid.isObstacle(1,0));
        assertTrue(testgrid.isObstacle(1,2));
        assertFalse(testgrid.isObstacle(2,6));
        assertFalse(testgrid.isObstacle(4,8));
        assertFalse(testgrid.isObstacle(0,0));
    }

}