/**
 * Created by zhanglanxin on 2/12/17.
 */
import java.util.List;

public class Room {

    private String name;
    private String description;
    private List<Direction> directions = null;

    public String getName() {
        return name;
    }


    public String getDescription() {
        return description;
    }

    public List<Direction> getDirections() {
        return directions;
    }

    public String DirectionToString(){
        String allDirection = "";
        if(directions.size()==1){
            return directions.get(0).getDirection();
        }
        else{
            for(int i = 0; i < directions.size()-1; i++ ){
                allDirection += directions.get(i).getDirection() + ", ";
            }
            return allDirection + "or " + directions.get(directions.size()-1).getDirection();
        }
    }

}
