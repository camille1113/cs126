/**
 * Created by zhanglanxin on 1/30/17.
 */
public class movieCollection {
    private int page;
    private movie[] results;
    private int total_results;
    private int total_pages;
    public String MovieTitle = " ";
    public String genreMovieTitle = " ";
    public String voteAveMovieTitle = " ";
    public String popularityMovieTitle = " ";

    public movie[] getResult() {
        return results;
    }

    public int getPage() {

        return page;
    }

    public int getTotal_pages() {
        return total_pages;
    }

    public int getTotal_results() {

        return total_results;
    }


    /**
     *
     * @return All of the films in the list
     */


    public String allMovieTitles(){

        for(int i = 0; i < results.length; i++){
            MovieTitle += results[i].getTitle() + "\n";
        }
        return MovieTitle;
    }

    /**
     *
     * @return Films of a specified genre id (i.e., the movies whose genre_ids includes a specific number)
     */

    public String genre(int id){

        for(int i = 0; i < results.length; i++){
            for(int j = 0; j< results[i].getGenre_ids().length; j++)
            if(results[i].getGenre_ids()[j]==id) {
                genreMovieTitle += results[i].getTitle() + "\n";
            }
        }
        return genreMovieTitle;
    }

    /**
     *
     * @return Films whose vote_average exceeds a specified threshold
     */
    public String voteAverage(double voteAve){

        for(int i = 0; i < results.length; i++){
            if(results[i].getVote_average() > voteAve) {
                voteAveMovieTitle += results[i].getTitle() + "\n";
            }
        }
        return voteAveMovieTitle;
    }

    /**
     *
     * @return Films whose popularity exceeds a specified threshold
     */
    public String popularity(double popularity){

        for(int i = 0; i < results.length; i++){
            if(results[i].getPopularity() > popularity) {
                popularityMovieTitle += results[i].getTitle() + "\n";
            }
        }
        return popularityMovieTitle;
    }

}
