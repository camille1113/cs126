import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by zhanglanxin on 2/14/17.
 */
public class mainTest {

    Layout siebelLayout = siebelMap.getLayout("https://courses.engr.illinois.edu/cs126/resources/siebel.json");
    List<Room> siebelRoom = siebelLayout.getRooms();

    public mainTest() throws IOException {
    }

    @Test
    public void getCurrentRoomNameTest() throws Exception {
        Room testRoom0 = siebelRoom.get(0);
        String expectedRoomName0 = testRoom0.getDirections().get(0).getRoom();
        String direct0 = "East";
        assertEquals(expectedRoomName0,main.getCurrentRoomName(direct0, testRoom0.getDirections().size(), testRoom0));

        //test for different case
        Room testRoom1 = siebelRoom.get(1);
        String expectedRoomName1 = testRoom1.getDirections().get(0).getRoom();
        String direct1 = "WeSt";
        assertEquals(expectedRoomName1,main.getCurrentRoomName(direct1, testRoom1.getDirections().size(), testRoom1));

        Room testRoom2 = siebelRoom.get(2);
        String direct2 = "dancing all night";
        assertEquals("I can't go dancing all night",
                main.getCurrentRoomName(direct2, testRoom2.getDirections().size(), testRoom2));

    }

    @Test
    public void findRoomTest() throws Exception {
        Room testRoom0 = siebelRoom.get(0);
        Room expectedRoom0 = siebelRoom.get(1);
        assertEquals(expectedRoom0,main.findRoom(siebelRoom,testRoom0, siebelRoom.size(), "SiebelEntry"));

        Room testRoom1 = siebelRoom.get(1);
        Room expectedRoom1 = siebelRoom.get(2);
        assertEquals(expectedRoom1,main.findRoom(siebelRoom,testRoom1, siebelRoom.size(), "AcmOffice"));

    }

    @Test
    public void isValidUrlTest() throws Exception {

        //test for "http"
        String testURL1 = "www.google.com";
        assertFalse(main.isValidUrl(testURL1));

        //test for "json"
        String testURL2 = "https://courses.engr.illinois.edu/cs126/resources/siebel";
        assertFalse(main.isValidUrl(testURL2));

        //test for space
        String testURL3 = "https://courses.engr.illinois.ed  u/cs126/resources/siebel.json";
        assertFalse(main.isValidUrl(testURL3));

        //test for valid url
        String testURL4 = "https://courses.engr.illinois.edu/cs126/resources/siebel.json";
        assertTrue(main.isValidUrl(testURL4));
    }

}