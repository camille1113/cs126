/**
 * Created by zhanglanxin on 2/20/17.
 */
public class Position implements Node {

    private int x;
    private int y;

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    Position(int x, int y){
        this.x = x;
        this.y = y;
    }

    public String getString(){
        return "x = " + x + ", " + "y = " + y + "\n";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Position position = (Position) o;

        if (x != position.x) return false;
        return y == position.y;
    }

    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result + y;
        return result;
    }
}
