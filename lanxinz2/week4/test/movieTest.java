import com.google.gson.Gson;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by zhanglanxin on 1/31/17.
 */
public class movieTest {

    public static final String myMovie = "{\n" +
            "          \"poster_path\":\"\\/WLQN5aiQG8wc9SeKwixW7pAR8K.jpg\",\n" +
            "         \"adult\":false,\n" +
            "         \"overview\":\"The quiet life of a terrier named Max is upended when his owner takes in Duke, a stray whom Max instantly dislikes.\",\n" +
            "         \"release_date\":\"2016-06-18\",\n" +
            "         \"genre_ids\":[  \n" +
            "            12,\n" +
            "            16,\n" +
            "            35,\n" +
            "            10751\n" +
            "         ],\n" +
            "         \"id\":328111,\n" +
            "         \"original_title\":\"The Secret Life of Pets\",\n" +
            "         \"original_language\":\"en\",\n" +
            "         \"title\":\"The Secret Life of Pets\",\n" +
            "         \"backdrop_path\":\"\\/lubzBMQLLmG88CLQ4F3TxZr2Q7N.jpg\",\n" +
            "         \"popularity\":289.434746,\n" +
            "         \"vote_count\":1922,\n" +
            "         \"video\":false,\n" +
            "         \"vote_average\":5.8\n" +
            "      }";
    Gson gson =	new	Gson();
    movie newMovie;
    @Before
    public void getTitles() {

        newMovie = gson.fromJson(myMovie, movie.class);
    }
    @Test
    public void getPoster_path() throws Exception {
        assertEquals("/WLQN5aiQG8wc9SeKwixW7pAR8K.jpg",newMovie.getPoster_path());
    }

    @Test
    public void getOverview() throws Exception {
        assertEquals("The quiet life of a terrier named Max is upended when his owner takes in Duke, a stray whom Max instantly dislikes.",
                newMovie.getOverview());
    }

    @Test
    public void getVote_average() throws Exception {
        assertEquals(5.8, newMovie.getVote_average(),0.001);
    }

    @Test
    public void isVideo() throws Exception {
        assertFalse(newMovie.isVideo());
    }

    @Test
    public void getVote_count() throws Exception {
        assertEquals(1922,newMovie.getVote_count());
    }

    @Test
    public void getPopularity() throws Exception {
        assertEquals(289.434746,newMovie.getPopularity(),0.0001);
    }

    @Test
    public void getBackdrop_path() throws Exception {
        assertEquals("/lubzBMQLLmG88CLQ4F3TxZr2Q7N.jpg",newMovie.getBackdrop_path());
    }

    @Test
    public void getTitle() throws Exception {
        assertEquals("The Secret Life of Pets",newMovie.getTitle());
    }

    @Test
    public void getOriginal_language() throws Exception {
        assertEquals("en",newMovie.getOriginal_language());
    }

    @Test
    public void getOriginal_title() throws Exception {
        assertEquals("The Secret Life of Pets", newMovie.getOriginal_title());
    }

    @Test
    public void getId() throws Exception {
        assertEquals(328111,newMovie.getId());
    }

    @Test
    public void getGenre_ids() throws Exception {
        assertEquals("12,16,35,10751,",newMovie.toStringID());
    }

    @Test
    public void getRelease_date() throws Exception {
        assertEquals("2016-06-18",newMovie.getRelease_date());
    }

    @Test
    public void isAdult() throws Exception {
        assertFalse(newMovie.isAdult());
    }



}