/**
 * Created by zhanglanxin on 2/12/17.
 */
import java.io.IOException;
import java.util.Scanner;
import java.util.List;

public class main {

    public static void main(String[] args) throws IOException {
        siebelMap.floorPlanValidator();
        Scanner in = new Scanner(System.in);
        repl(in);//read evaluate print loop
    }

    public static void repl(Scanner scanner) throws IOException {

        //ask whether the user want to put alternate url
        //if not the program will provide it's own url
        System.out.println("Do you want to put your own url?" + "\n" +
        "please enter yes or no");
        String anwser = scanner.nextLine();
        String url = "https://courses.engr.illinois.edu/cs126/resources/siebel.json";
        if(anwser.equals("yes")){
            System.out.println("please enter your url");
            url = scanner.nextLine();
            while(!isValidUrl(url)){
                System.out.println("the url you entered is invalid" + "\n" +
                "please enter again");
                url = scanner.nextLine();
            }
        }

        //get the siebel layout from siebel map
        Layout siebelLayout = siebelMap.getLayout(url);
        List<Room> siebelRoom = siebelLayout.getRooms();
        Room currentRoom = new Room();
        int RoomSize = siebelRoom.size();
        int numOfDirections = 0;

        for(int i = 0; i < siebelLayout.getRooms().size(); i++){
            if(siebelRoom.get(i).getName().equals(siebelLayout.getInitialRoom())){
                currentRoom = siebelRoom.get(i);
                System.out.println(currentRoom.getDescription());
                System.out.println("From here you can go: " +
                        currentRoom.DirectionToString());
                break;
            }
        }

        while(true){

            String line= scanner.nextLine();

            if (line.equals("exit")) {
                break;
            }

            String [] input = line.split(" ");
            if(input.length<2){
                System.out.println("I don't understand " + line + "\n" +
                "please enter again");
                line = scanner.nextLine();
                input = line.split(" ");
            }
            String command = input[0].toLowerCase();
            String direct = input[1].toLowerCase();
            numOfDirections = currentRoom.getDirections().size();

            if(command.equals("go")) {
                //get the name of the room the user is going
                String currentRoomName = getCurrentRoomName(direct, numOfDirections, currentRoom);
                if(currentRoomName.equals("I can't go " + direct)){
                    System.out.println("I can't " + line);
                }
                else{
                    //set the room the user is going to current room
                    currentRoom = findRoom(siebelRoom, currentRoom, RoomSize, currentRoomName);
                    System.out.println(currentRoom.getDescription() + "\n" +
                            "From here you can go: " + currentRoom.DirectionToString());
                }
            }
            else{
                System.out.println("I don't undertand " + "'" + line + "'");
            }
        }
    }

    /**
     * this is a helper to find whether the direction the user entered is valid
     * if there's no this direction it returns a String "I can't go " + direction
     * else it will return the room name at that direction
     * @param direct
     * @param numOfdirections
     * @param currentRoom
     * @return the room name at the direction the user entered
     */
    public static String getCurrentRoomName(String direct, int numOfdirections, Room currentRoom){
        for(int i = 0; i < numOfdirections; i++){
            if(direct.toLowerCase().equals(currentRoom.getDirections().get(i).getDirection().toLowerCase())){
                return currentRoom.getDirections().get(i).getRoom();
            }
        }
        return "I can't go " + direct;
    }

    /**
     * this is a helper function for find the room at the direction the user entered
     * @param siebelRoom
     * @param currentRoom
     * @param roomSize
     * @param currentRoomName
     * @return the room user is going to
     */
    public static Room findRoom(List<Room> siebelRoom, Room currentRoom, int roomSize, String currentRoomName) {
        for(int i = 0; i < roomSize; i++){
            if(currentRoomName.equals(siebelRoom.get(i).getName())){
                currentRoom = siebelRoom.get(i);
                return currentRoom;
            }
        }
        return null;
    }

    /**
     * this method test whether the url the user put is valid
     */
    public static boolean isValidUrl(String url){
        boolean checker = true;
        if(!url.contains("http")){
            checker = false;
        }
        if(!url.contains("json")){
            checker = false;
        }
        if(url.contains(" ")){
            checker = false;
        }
        return checker;
    }
}

