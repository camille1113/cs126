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




}