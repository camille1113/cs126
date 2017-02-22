/**
 * Created by zhanglanxin on 2/20/17.
 */
public class Position {

    private int x;
    private int y;
    private int gScore = Integer.MAX_VALUE;
    private int fScore = Integer.MAX_VALUE;

    public int getgScore() {
        return gScore;
    }

    public int getfScore() {
        return fScore;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setgScore(int gScore) {
        this.gScore = gScore;
    }

    public void setfScore(int fScore) {
        this.fScore = fScore;
    }

}
