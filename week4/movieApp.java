import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.DoubleBuffer;
import java.nio.charset.Charset;


/**
 * Created by zhanglanxin on 2/7/17.
 */
public class movieApp {

    public static void main(String[] args) throws IOException, InterruptedException {
        //to check if the first input is an integer
        try{
            int totalMovies = Integer.parseInt(args[0]);
        }catch(NumberFormatException a){
            System.out.println("the first input must be an int");
            System.exit(1);
        }
        //to check if the last input is an integer or a double

        try{
            double lastinput = Double.parseDouble(args[2]);
        }catch(NumberFormatException a){
            System.out.println("the last input must be an int or a double");
            System.exit(1);
        }

        //set the number of movies requested to the largest it can when it is too large
        int totalMovies = Integer.parseInt(args[0]);
        if (totalMovies > getMovies(1).getTotal_results()) {
            totalMovies = getMovies(1).getTotal_results();
        }

        //to count how many pages needed
        int pageNum = totalMovies / 20;
        int restMovies = totalMovies % 20;

        String queryType = args[1];

        //get the genreID or vote average or popularity
        int genreID = 0;
        double voteAveOrPopularity = 0.0;
        if(queryType.equals("genre")) {
            genreID = Integer.parseInt(args[2]);
        }
        if(queryType.equals("voteAverage")||queryType.equals("popularity")) {
            voteAveOrPopularity = Double.parseDouble(args[2]);
        }


        System.out.println(getTitles(queryType,pageNum,restMovies,genreID,voteAveOrPopularity));
    }

    public static movieCollection getMovies(int pageNum) throws IOException, InterruptedException {
        URL url = new URL(ApiKey.API_KEY + "&page=" + pageNum );
        InputStream inStream = url.openStream();
        InputStreamReader inputStreamReader =
                new InputStreamReader(inStream, Charset.forName("UTF-8"));

        JsonReader jsonReader = new JsonReader((inputStreamReader));
        Gson gson = new Gson();
        movieCollection myClass = gson.fromJson(jsonReader, movieCollection.class);

        Thread.sleep(200);
        return myClass;
    }

    /**
     *
     * @param queryType
     * @param pageNum
     * @param restMovies
     * @param genreID
     * @param voteAveOrPopularity
     * @return a list of movie titles depends on the query type
     * @throws IOException
     * @throws InterruptedException
     */
    public static String getTitles(String queryType, int pageNum, int restMovies, int genreID, double voteAveOrPopularity) throws IOException, InterruptedException {

        if(queryType.equals("allMovieTitles")){
            String movieTitles = "";
            for(int i = 1; i < pageNum; i++){
                movieCollection myClass = getMovies(i);
                movieTitles += myClass.allMovieTitles() + "\n";
            }

            movieCollection myClass = getMovies(pageNum+1);//collecting movies from the last page
            for(int i = 0; i < restMovies; i++){
                movieTitles += myClass.getResult()[i].getTitle() + "\n";
            }
            return movieTitles;
        }



        if(queryType.equals("genre")) {
            String movieTitles = "";
            for (int i = 1; i < pageNum; i++) {
                movieCollection myClass = getMovies(i);
                movieTitles += myClass.genre(genreID) + "\n";
            }

            movieCollection myClass = getMovies(pageNum + 1);//collecting movies from the last page
            for (int i = 0; i < restMovies; i++) {
                for (int j = 0; j < myClass.getResult()[i].getGenre_ids().length; j++)
                    if (myClass.getResult()[i].getGenre_ids()[j] == genreID) {
                        movieTitles += myClass.getResult()[i].getTitle() + "\n";
                    }
            }

            return movieTitles;
        }



        if(queryType.equals("voteAverage")){
            String movieTitles = "";
            for(int i = 1; i < pageNum; i++){
                movieCollection myClass = getMovies(i);
                movieTitles += myClass.voteAverage(voteAveOrPopularity) + "\n";
            }

            movieCollection myClass = getMovies(pageNum+1);//collecting movies from the last page
            for(int i = 0; i < restMovies; i++){
                if(myClass.getResult()[i].getVote_average()>voteAveOrPopularity){
                    movieTitles += myClass.getResult()[i].getTitle() + "\n";
                }
            }
            return movieTitles;
        }




        if(queryType.equals("popularity")){
            String movieTitles = "";
            for(int i = 1; i < pageNum; i++){
                movieCollection myClass = getMovies(i);
                movieTitles += myClass.popularity(voteAveOrPopularity) ;
            }

            movieCollection myClass = getMovies(pageNum+1);//collecting movies from the last page
            for(int i = 0; i < restMovies; i++){
                if(myClass.getResult()[i].getPopularity()>voteAveOrPopularity){
                    movieTitles += myClass.getResult()[i].getTitle() ;
                }
            }
            return movieTitles;
        }

        else{
            return "Invalid input, please enter again and choose from the following types: " + "\n"
                    + "1) allMovieTitles" + "\n"
                    + "2) genre" + "\n"
                    + "3) voteAverage" + "\n"
                    + "4) popularity" + "\n"; //return invalid if none of the type matches
        }

    }
}
