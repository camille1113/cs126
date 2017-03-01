/**
 * Created by zhanglanxin on 2/28/17.
 */
public class JsonCity implements Node{
    private String name;
    private double latitude;
    private double longitude;

    public String getName() {
        return name;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public double distance(JsonCity otherCity) {
        double xDistance = Math.abs(this.longitude - otherCity.longitude);
        double yDistance = Math.abs(this.latitude - otherCity.latitude);
        return Math.sqrt(xDistance*xDistance + yDistance*yDistance);
    }

    @Override
    public String getString() {
        return getName() + "\n";
    }
}
