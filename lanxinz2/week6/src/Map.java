import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.Charset;
/**
 * Created by zhanglanxin on 2/20/17.
 */
public class Map {
    public static Grid getGrid() throws IOException {
        URL url = new URL("https://courses.engr.illinois.edu/cs126/resources/grid.json");
        InputStream inStream = url.openStream();
        InputStreamReader reader = new InputStreamReader(inStream, Charset.forName("UTF-8"));
        JsonReader jsonReader = new JsonReader(reader);
        Gson gson = new Gson();
        Grid grid = gson.fromJson(jsonReader, Grid.class);
        return grid;
    }
    /**
     * this method establish a 2d array to represent the grid
     * @param newGrid
     * @return a 2d array of type Position
     */
    public static Position[][] createMap(Grid newGrid){
        Position[][] newMap = new Position[10][10];
        for(int i = 0; i < 10; i++){
            for(int j = 0; j < 10; j++){
                newMap[i][j] = new Position();
                newMap[i][j].setX(i);
                newMap[i][j].setY(j);
            }
        }
        //put the start and end in this new map
        newMap[newGrid.getStart().getX()][newGrid.getStart().getY()] = newGrid.getStart();
        newMap[newGrid.getEnd().getX()][newGrid.getEnd().getY()] = newGrid.getEnd();
        //put obstacles in this new map and set the gScore of the obstacles to -1
        for(Position positions: newGrid.getObstacles()) {
            newMap[positions.getX()][positions.getY()] = positions;
            newMap[positions.getX()][positions.getY()].setgScore(-1);
        }
        return newMap;
    }
}
