import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by zhanglanxin on 2/21/17.
 */
public class MapTest {

    @Test
    public void getGridTest() throws Exception {
        Grid newGrid = Map.getGrid();
        assertEquals(newGrid.getStart().getX(),0);
        assertEquals(newGrid.getStart().getY(),0);
        assertEquals(newGrid.getEnd().getX(),3);
        assertEquals(newGrid.getEnd().getY(),2);
        assertEquals(newGrid.getDimension(),10);
    }
    @Test
    public void createMapTest() throws Exception {
        Grid newGrid = Map.getGrid();
        Position[][] newMap = Map.createMap(newGrid);
        //test if the gScore of obstacles is set to -1
        assertEquals(newMap[1][0].getgScore(),-1);
        assertEquals(newMap[2][3].getgScore(),-1);
        assertEquals(newMap[4][3].getgScore(),-1);
        assertEquals(newMap[5][4].getgScore(),-1);
        //test the start and the end in the new map
        assertEquals(newMap[0][0].getX(),newGrid.getStart().getX());
        assertEquals(newMap[3][2].getY(),newGrid.getEnd().getY());
    }

}