/**
 * Created by zhanglanxin on 1/30/17.
 */
public class movieCollection {
    public static void main(String[] args){

    }
    private int page;
    private movie[] results;
    private int total_results;
    private int total_pages;

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
     * print all movie titles
     */

    public String allMovieTitles(){
        String MovieTitle = " ";
        for(int i = 0; i < results.length; i++){
            MovieTitle += results[i].getTitle() + "\n";
        }
        System.out.println(MovieTitle);
        return MovieTitle;
    }

    //print a list of movies whose genre include 14
    public String genre14(){
        String genre14MovieTitle = " ";
        for(int i = 0; i < results.length; i++){
            if(results[i].genre()) {
                genre14MovieTitle += results[i].getTitle() + "\n";
            }
        }
        System.out.println(genre14MovieTitle);
        return genre14MovieTitle;
    }

    //print a list of movies whose vote average is larger than 5
    public String vote5(){
        String vote5MovieTitle = " ";
        for(int i = 0; *i < results.length; i++){
            if(results[i].voteAve()) {
                vote5MovieTitle += results[i].getTitle() + "\n";
            }
        }
        System.out.println(vote5MovieTitle);
        return vote5MovieTitle;
    }

    ////print a list of movies whose popularity is larger than 100
    public String pop1(){
        String pop1MovieTitle = " ";
        for(int i = 0; i < results.length; i++){
            if(results[i].pop()) {
                pop1MovieTitle += results[i].getTitle() + "\n";
            }
        }
        System.out.println(pop1MovieTitle);
        return pop1MovieTitle;
    }




}
