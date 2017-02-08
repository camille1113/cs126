import com.google.gson.Gson;
/**
 * Created by zhanglanxin on 1/30/17.
 */
public class movie {

    //set all of the variables private

    private String poster_path;
    private boolean adult;
    private String overview;
    private String release_date;
    private int[] genre_ids;
    private int id;
    private String original_title;
    private String original_language;
    private String title;
    private String backdrop_path;
    private double popularity;
    private int vote_count;
    private boolean video;
    private double vote_average;

    //generate getters for each private member variable

    public String getPoster_path() {

        return poster_path;
    }

    public String getOverview() {
        return overview;
    }

    public double getVote_average() {

        return vote_average;
    }

    public boolean isVideo() {

        return video;
    }

    public int getVote_count() {

        return vote_count;
    }

    public double getPopularity() {

        return popularity;
    }

    public String getBackdrop_path() {

        return backdrop_path;
    }

    public String getTitle() {

        return title;
    }

    public String getOriginal_language() {

        return original_language;
    }

    public String getOriginal_title() {

        return original_title;
    }

    public int getId() {

        return id;
    }

    public int[] getGenre_ids() {

        return genre_ids;
    }

    public String getRelease_date() {

        return release_date;
    }

    public boolean isAdult() {

        return adult;
    }

    /**
     *
     * @return a string of genre Ids
     */

    public String toStringID(){
        String tempid = "";
        int[] ids = this.getGenre_ids();
        for(int i=0; i<ids.length;i++){
            tempid += ids[i]+",";
        }
        return tempid;
    }

    /**
     *
     * @return a string that has all the attributes of a movie
     */
    public String toString(){
        String temp="";
        temp+=this.getPoster_path()+" "+this.isAdult()+" "+this.getOverview()+" "+
                this.getRelease_date()+" "+this.toStringID()+" "+
                this.getId()+" "+this.getOriginal_title()+" "+
                this.getOriginal_language()+" "+this.getTitle()+" "+
                this.getBackdrop_path()+" "+this.getPopularity()+" "+
                this.getVote_count()+" "+this.isVideo()+" "+this.getVote_average();
        return temp;
    }


}
