import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by zhanglanxin on 3/3/17.
 */
public class PositionTest {
    Position testPosition1 = new Position(1,2);
    Position testPosition2 = new Position(2,3);
    Position testPosition3 = new Position(5,6);
    Position testPosition4 = new Position(0,0);
    @Test
    public void getX() throws Exception {
        assertEquals(testPosition1.getX(),1);
        assertEquals(testPosition2.getX(),2);
        assertEquals(testPosition3.getX(),5);
        assertEquals(testPosition4.getX(),0);
    }

    @Test
    public void getY() throws Exception {
        assertEquals(testPosition1.getY(),2);
        assertEquals(testPosition2.getY(),3);
        assertEquals(testPosition3.getY(),6);
        assertEquals(testPosition4.getY(),0);

    }

    @Test
    public void getString() throws Exception {
        assertEquals(testPosition1.getString(),"x = 1, y = 2" + "\n");
        assertEquals(testPosition2.getString(),"x = 2, y = 3" + "\n");
        assertEquals(testPosition3.getString(),"x = 5, y = 6" + "\n");
        assertEquals(testPosition4.getString(),"x = 0, y = 0" + "\n");

    }

}