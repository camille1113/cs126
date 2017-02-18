/**
 * Created by zhanglanxin on 2/12/17.
 */
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.List;


public class siebelMap {
    public static final String SIEBEL_URL = "https://courses.engr.illinois.edu/cs126/resources/siebel.json";

    public static Layout getLayout(String siebelUrl) throws IOException {

        URL url = new URL(siebelUrl);
        InputStream inStream = url.openStream();
        InputStreamReader reader = new InputStreamReader(inStream, Charset.forName("UTF-8"));
        JsonReader jsonReader = new JsonReader(reader);
        Gson gson = new Gson();
        Layout siebelMap = gson.fromJson(jsonReader, Layout.class);

        return siebelMap;
    }

    /**
     * This method is used to check if this map is validate,
     * it go through every room in this map, and inside every room, it go through
     * every direction in this current room and get into the next room of that direction
     * to see if the next room contains the current room.
     * @return boolean
     * @throws IOException
     */
    public static boolean isfloorPlanValidate() throws IOException {
        Layout siebelLayout = siebelMap.getLayout(SIEBEL_URL);
        List<Room> siebelRoom = siebelLayout.getRooms();

        Room nextRoom = new Room();
        int RoomSize = siebelRoom.size();
        String currentRoomName = "";
        String nextRoomName = "";
        boolean checker = false;

        //check through every room in this map
        for(Room currentRoom: siebelRoom){

            currentRoomName = currentRoom.getName();
            checker = false; //set the checker false for the next room to investigate

            //go through every direction in this room to see if the next room contains this room

            for(Direction nextDirect: currentRoom.getDirections()){
                nextRoomName = nextDirect.getRoom();
                nextRoom = main.findRoom(siebelRoom, currentRoom, RoomSize, nextRoomName);

                if(!isInNextRoom(nextRoom, currentRoomName)){
                    System.out.println("Is this map validate: " + checker);
                    return checker; //if it did not find current room in the next room, just return false
                }
                else{
                    checker = true;
                }
            }
        }
        System.out.println("Is this map validate: " + checker);
        return checker;
    }
    /**
     * this method is a helper to find if the next room contains the current room
     * it will go through every room inside this next room
     * @param nextRoom
     * @param currentRoomName
     * @return boolean
     */
    public static boolean isInNextRoom(Room nextRoom, String currentRoomName) {
        for(int i = 0; i < nextRoom.getDirections().size(); i++){
            if(nextRoom.getDirections().get(i).getRoom().equals(currentRoomName)){
                return true;
            }
        }
        return false;
    }

}
