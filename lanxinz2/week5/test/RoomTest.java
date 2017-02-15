import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by zhanglanxin on 2/13/17.
 */
public class RoomTest {

    /**
     * this function test the directionToString method
     * @throws Exception
     */
    @Test
    public void directionToStringTest() throws Exception {
        Layout siebelLayout = siebelMap.getLayout("https://courses.engr.illinois.edu/cs126/resources/siebel.json");
        List<Room> siebelRoom = siebelLayout.getRooms();

        Room testRoom0 = siebelRoom.get(0);
        assertEquals("East", testRoom0.DirectionToString());

        Room testRoom1 = siebelRoom.get(1);
        assertEquals("West, Northeast, North, or East", testRoom1.DirectionToString());
    }

}