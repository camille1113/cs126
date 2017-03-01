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

}