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

}



