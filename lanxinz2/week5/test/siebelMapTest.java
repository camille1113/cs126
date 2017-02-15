import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by zhanglanxin on 2/14/17.
 */
public class siebelMapTest {
    Layout siebelLayout = siebelMap.getLayout("https://courses.engr.illinois.edu/cs126/resources/siebel.json");
    List<Room> siebelRoom = siebelLayout.getRooms();

    public siebelMapTest() throws IOException {
    }

    /**
     * test if current room is in the next room's direction scope
     * @throws Exception
     */
    @Test
    public void isInNextRoom() throws Exception {
        Room testRoom0 = siebelRoom.get(0);
        assertTrue(siebelMap.isInNextRoom(testRoom0, "SiebelEntry"));

        Room testRoom1 = siebelRoom.get(1);
        assertTrue(siebelMap.isInNextRoom(testRoom1, "SiebelNorthHallway"));

        Room testRoom2 = siebelRoom.get(2);
        assertFalse(siebelMap.isInNextRoom(testRoom2, "AcmOffice"));

    }

}