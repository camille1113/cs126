import javafx.geometry.Pos;
import java.io.IOException;
import java.util.*;
import java.lang.Math;

/**
 * Created by zhanglanxin on 2/20/17.
 */
public class Main {
    public static void main(String[] args) throws IOException {
        List<Position> path = AStar.get_path();
        for(Position positions: path){
            System.out.println("x = " + positions.getX() + ", " +
            " y = " + positions.getY());
        }
    }
}
