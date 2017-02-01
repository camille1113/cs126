import com.google.gson.Gson;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by zhanglanxin on 1/31/17.
 */
public class movieCollectionTest {
    public static void main(String[] args){

    }
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

    public static final String myCollection = "{\n" +
            "   \"page\":1,\n" +
            "   \"results\":[\n" +
            "      {  \n" +
            "         \"poster_path\":\"\\/WLQN5aiQG8wc9SeKwixW7pAR8K.jpg\",\n" +
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
            "      },\n" +
            "      {  \n" +
            "         \"poster_path\":\"\\/z4x0Bp48ar3Mda8KiPD1vwSY3D8.jpg\",\n" +
            "         \"adult\":false,\n" +
            "         \"overview\":\"In Ancient Polynesia, when a terrible curse incurred by Maui reaches an impetuous Chieftain's daughter's island, she answers the Ocean's call to seek out the demigod to set things right.\",\n" +
            "         \"release_date\":\"2016-11-23\",\n" +
            "         \"genre_ids\":[  \n" +
            "            12,\n" +
            "            16,\n" +
            "            35,\n" +
            "            10751,\n" +
            "            14\n" +
            "         ],\n" +
            "         \"id\":277834,\n" +
            "         \"original_title\":\"Moana\",\n" +
            "         \"original_language\":\"en\",\n" +
            "         \"title\":\"Moana\",\n" +
            "         \"backdrop_path\":\"\\/1qGzqGUd1pa05aqYXGSbLkiBlLB.jpg\",\n" +
            "         \"popularity\":230.22034,\n" +
            "         \"vote_count\":1036,\n" +
            "         \"video\":false,\n" +
            "         \"vote_average\":6.5\n" +
            "      },\n" +
            "      {  \n" +
            "         \"poster_path\":\"\\/e1mjopzAS2KNsvpbpahQ1a6SkSn.jpg\",\n" +
            "         \"adult\":false,\n" +
            "         \"overview\":\"From DC Comics comes the Suicide Squad, an antihero team of incarcerated supervillains who act as deniable assets for the United States government, undertaking high-risk black ops missions in exchange for commuted prison sentences.\",\n" +
            "         \"release_date\":\"2016-08-02\",\n" +
            "         \"genre_ids\":[  \n" +
            "            28,\n" +
            "            80,\n" +
            "            14\n" +
            "         ],\n" +
            "         \"id\":297761,\n" +
            "         \"original_title\":\"Suicide Squad\",\n" +
            "         \"original_language\":\"en\",\n" +
            "         \"title\":\"Suicide Squad\",\n" +
            "         \"backdrop_path\":\"\\/34dxtTxMHGKw1njHpTjDqR8UBHd.jpg\",\n" +
            "         \"popularity\":174.019262,\n" +
            "         \"vote_count\":4033,\n" +
            "         \"video\":false,\n" +
            "         \"vote_average\":5.9\n" +
            "      },\n" +
            "      {  \n" +
            "         \"poster_path\":\"\\/ylXCdC106IKiarftHkcacasaAcb.jpg\",\n" +
            "         \"adult\":false,\n" +
            "         \"overview\":\"Mia, an aspiring actress, serves lattes to movie stars in between auditions and Sebastian, a jazz musician, scrapes by playing cocktail party gigs in dingy bars, but as success mounts they are faced with decisions that begin to fray the fragile fabric of their love affair, and the dreams they worked so hard to maintain in each other threaten to rip them apart.\",\n" +
            "         \"release_date\":\"2016-12-01\",\n" +
            "         \"genre_ids\":[  \n" +
            "            10749,\n" +
            "            35,\n" +
            "            18,\n" +
            "            10402\n" +
            "         ],\n" +
            "         \"id\":313369,\n" +
            "         \"original_title\":\"La La Land\",\n" +
            "         \"original_language\":\"en\",\n" +
            "         \"title\":\"La La Land\",\n" +
            "         \"backdrop_path\":\"\\/fp6X6yhgcxzxCpmM0EVC6V9B8XB.jpg\",\n" +
            "         \"popularity\":124.171997,\n" +
            "         \"vote_count\":715,\n" +
            "         \"video\":false,\n" +
            "         \"vote_average\":8.1\n" +
            "      },\n" +
            "      {  \n" +
            "         \"poster_path\":\"\\/tIKFBxBZhSXpIITiiB5Ws8VGXjt.jpg\",\n" +
            "         \"adult\":false,\n" +
            "         \"overview\":\"Lynch discovers he is a descendant of the secret Assassins society through unlocked genetic memories that allow him to relive the adventures of his ancestor, Aguilar, in 15th Century Spain. After gaining incredible knowledge and skills he’s poised to take on the oppressive Knights Templar in the present day.\",\n" +
            "         \"release_date\":\"2016-12-21\",\n" +
            "         \"genre_ids\":[  \n" +
            "            28,\n" +
            "            12,\n" +
            "            14,\n" +
            "            878\n" +
            "         ],\n" +
            "         \"id\":121856,\n" +
            "         \"original_title\":\"Assassin's Creed\",\n" +
            "         \"original_language\":\"en\",\n" +
            "         \"title\":\"Assassin's Creed\",\n" +
            "         \"backdrop_path\":\"\\/r8aRipzR4wlDx0m7Bpi43Q84imc.jpg\",\n" +
            "         \"popularity\":114.52664,\n" +
            "         \"vote_count\":812,\n" +
            "         \"video\":false,\n" +
            "         \"vote_average\":5.2\n" +
            "      },\n" +
            "      {  \n" +
            "         \"poster_path\":\"\\/z09QAf8WbZncbitewNk6lKYMZsh.jpg\",\n" +
            "         \"adult\":false,\n" +
            "         \"overview\":\"Dory is reunited with her friends Nemo and Marlin in the search for answers about her past. What can she remember? Who are her parents? And where did she learn to speak Whale?\",\n" +
            "         \"release_date\":\"2016-06-16\",\n" +
            "         \"genre_ids\":[  \n" +
            "            16,\n" +
            "            10751\n" +
            "         ],\n" +
            "         \"id\":127380,\n" +
            "         \"original_title\":\"Finding Dory\",\n" +
            "         \"original_language\":\"en\",\n" +
            "         \"title\":\"Finding Dory\",\n" +
            "         \"backdrop_path\":\"\\/iWRKYHTFlsrxQtfQqFOQyceL83P.jpg\",\n" +
            "         \"popularity\":103.389825,\n" +
            "         \"vote_count\":2039,\n" +
            "         \"video\":false,\n" +
            "         \"vote_average\":6.7\n" +
            "      },\n" +
            "      {  \n" +
            "         \"poster_path\":\"\\/jjBgi2r5cRt36xF6iNUEhzscEcb.jpg\",\n" +
            "         \"adult\":false,\n" +
            "         \"overview\":\"Twenty-two years after the events of Jurassic Park, Isla Nublar now features a fully functioning dinosaur theme park, Jurassic World, as originally envisioned by John Hammond.\",\n" +
            "         \"release_date\":\"2015-06-09\",\n" +
            "         \"genre_ids\":[  \n" +
            "            28,\n" +
            "            12,\n" +
            "            878,\n" +
            "            53\n" +
            "         ],\n" +
            "         \"id\":135397,\n" +
            "         \"original_title\":\"Jurassic World\",\n" +
            "         \"original_language\":\"en\",\n" +
            "         \"title\":\"Jurassic World\",\n" +
            "         \"backdrop_path\":\"\\/dkMD5qlogeRMiEixC4YNPUvax2T.jpg\",\n" +
            "         \"popularity\":87.849639,\n" +
            "         \"vote_count\":5734,\n" +
            "         \"video\":false,\n" +
            "         \"vote_average\":6.5\n" +
            "      },\n" +
            "      {  \n" +
            "         \"poster_path\":\"\\/vR9YvUJCead23MOWwVzv9776eb1.jpg\",\n" +
            "         \"adult\":false,\n" +
            "         \"overview\":\"A teenager finds himself transported to an island where he must help protect a group of orphans with special powers from creatures intent on destroying them.\",\n" +
            "         \"release_date\":\"2016-09-28\",\n" +
            "         \"genre_ids\":[  \n" +
            "            14\n" +
            "         ],\n" +
            "         \"id\":283366,\n" +
            "         \"original_title\":\"Miss Peregrine's Home for Peculiar Children\",\n" +
            "         \"original_language\":\"en\",\n" +
            "         \"title\":\"Miss Peregrine's Home for Peculiar Children\",\n" +
            "         \"backdrop_path\":\"\\/qXQinDhDZkTiqEGLnav0h1YSUu8.jpg\",\n" +
            "         \"popularity\":71.044002,\n" +
            "         \"vote_count\":1147,\n" +
            "         \"video\":false,\n" +
            "         \"vote_average\":6.3\n" +
            "      },\n" +
            "      {  \n" +
            "         \"poster_path\":\"\\/gri0DDxsERr6B2sOR1fGLxLpSLx.jpg\",\n" +
            "         \"adult\":false,\n" +
            "         \"overview\":\"In 1926, Newt Scamander arrives at the Magical Congress of the United States of America with a magically expanded briefcase, which houses a number of dangerous creatures and their habitats. When the creatures escape from the briefcase, it sends the American wizarding authorities after Newt, and threatens to strain even further the state of magical and non-magical relations.\",\n" +
            "         \"release_date\":\"2016-11-16\",\n" +
            "         \"genre_ids\":[  \n" +
            "            12,\n" +
            "            10751,\n" +
            "            14\n" +
            "         ],\n" +
            "         \"id\":259316,\n" +
            "         \"original_title\":\"Fantastic Beasts and Where to Find Them\",\n" +
            "         \"original_language\":\"en\",\n" +
            "         \"title\":\"Fantastic Beasts and Where to Find Them\",\n" +
            "         \"backdrop_path\":\"\\/6I2tPx6KIiBB4TWFiWwNUzrbxUn.jpg\",\n" +
            "         \"popularity\":64.109831,\n" +
            "         \"vote_count\":1884,\n" +
            "         \"video\":false,\n" +
            "         \"vote_average\":7\n" +
            "      },\n" +
            "      {  \n" +
            "         \"poster_path\":\"\\/nBNZadXqJSdt05SHLqgT0HuC5Gm.jpg\",\n" +
            "         \"adult\":false,\n" +
            "         \"overview\":\"Interstellar chronicles the adventures of a group of explorers who make use of a newly discovered wormhole to surpass the limitations on human space travel and conquer the vast distances involved in an interstellar voyage.\",\n" +
            "         \"release_date\":\"2014-11-05\",\n" +
            "         \"genre_ids\":[  \n" +
            "            12,\n" +
            "            18,\n" +
            "            878\n" +
            "         ],\n" +
            "         \"id\":157336,\n" +
            "         \"original_title\":\"Interstellar\",\n" +
            "         \"original_language\":\"en\",\n" +
            "         \"title\":\"Interstellar\",\n" +
            "         \"backdrop_path\":\"\\/xu9zaAevzQ5nnrsXN6JcahLnG4i.jpg\",\n" +
            "         \"popularity\":50.358586,\n" +
            "         \"vote_count\":6772,\n" +
            "         \"video\":false,\n" +
            "         \"vote_average\":8\n" +
            "      },\n" +
            "      {  \n" +
            "         \"poster_path\":\"\\/5N20rQURev5CNDcMjHVUZhpoCNC.jpg\",\n" +
            "         \"adult\":false,\n" +
            "         \"overview\":\"Following the events of Age of Ultron, the collective governments of the world pass an act designed to regulate all superhuman activity. This polarizes opinion amongst the Avengers, causing two factions to side with Iron Man or Captain America, which causes an epic battle between former allies.\",\n" +
            "         \"release_date\":\"2016-04-27\",\n" +
            "         \"genre_ids\":[  \n" +
            "            878,\n" +
            "            28\n" +
            "         ],\n" +
            "         \"id\":271110,\n" +
            "         \"original_title\":\"Captain America: Civil War\",\n" +
            "         \"original_language\":\"en\",\n" +
            "         \"title\":\"Captain America: Civil War\",\n" +
            "         \"backdrop_path\":\"\\/m5O3SZvQ6EgD5XXXLPIP1wLppeW.jpg\",\n" +
            "         \"popularity\":48.254312,\n" +
            "         \"vote_count\":4153,\n" +
            "         \"video\":false,\n" +
            "         \"vote_average\":6.8\n" +
            "      },\n" +
            "      {  \n" +
            "         \"poster_path\":\"\\/yNsdyNbQqaKN0TQxkHMws2KLTJ6.jpg\",\n" +
            "         \"adult\":false,\n" +
            "         \"overview\":\"Extreme athlete turned government operative Xander Cage (Vin Diesel) comes out of self-imposed exile, thought to be long dead, and is set on a collision course with deadly alpha warrior Xiang (Donnie Yen) and his team in a race to recover a sinister and seemingly unstoppable weapon known as Pandora's Box. Recruiting an all-new group of thrill-seeking cohorts, Xander finds himself enmeshed in a deadly conspiracy that points to collusion at the highest levels of world governments.\",\n" +
            "         \"release_date\":\"2017-01-17\",\n" +
            "         \"genre_ids\":[  \n" +
            "            28,\n" +
            "            12,\n" +
            "            80,\n" +
            "            53\n" +
            "         ],\n" +
            "         \"id\":47971,\n" +
            "         \"original_title\":\"xXx: Return of Xander Cage\",\n" +
            "         \"original_language\":\"en\",\n" +
            "         \"title\":\"xXx: Return of Xander Cage\",\n" +
            "         \"backdrop_path\":\"\\/6AewnVY9zBgVQEuCufLvsufeRcH.jpg\",\n" +
            "         \"popularity\":46.613769,\n" +
            "         \"vote_count\":183,\n" +
            "         \"video\":false,\n" +
            "         \"vote_average\":5.4\n" +
            "      },\n" +
            "      {  \n" +
            "         \"poster_path\":\"\\/5gJkVIVU7FDp7AfRAbPSvvdbre2.jpg\",\n" +
            "         \"adult\":false,\n" +
            "         \"overview\":\"A spacecraft traveling to a distant colony planet and transporting thousands of people has a malfunction in its sleep chambers. As a result, two passengers are awakened 90 years early.\",\n" +
            "         \"release_date\":\"2016-12-21\",\n" +
            "         \"genre_ids\":[  \n" +
            "            12,\n" +
            "            18,\n" +
            "            10749,\n" +
            "            878\n" +
            "         ],\n" +
            "         \"id\":274870,\n" +
            "         \"original_title\":\"Passengers\",\n" +
            "         \"original_language\":\"en\",\n" +
            "         \"title\":\"Passengers\",\n" +
            "         \"backdrop_path\":\"\\/5EW4TR3fWEqpKsWysNcBMtz9Sgp.jpg\",\n" +
            "         \"popularity\":45.482837,\n" +
            "         \"vote_count\":896,\n" +
            "         \"video\":false,\n" +
            "         \"vote_average\":6.3\n" +
            "      },\n" +
            "      {  \n" +
            "         \"poster_path\":\"\\/4Iu5f2nv7huqvuYkmZvSPOtbFjs.jpg\",\n" +
            "         \"adult\":false,\n" +
            "         \"overview\":\"Taking place after alien crafts land around the world, an expert linguist is recruited by the military to determine whether they come in peace or are a threat.\",\n" +
            "         \"release_date\":\"2016-11-10\",\n" +
            "         \"genre_ids\":[  \n" +
            "            18,\n" +
            "            878\n" +
            "         ],\n" +
            "         \"id\":329865,\n" +
            "         \"original_title\":\"Arrival\",\n" +
            "         \"original_language\":\"en\",\n" +
            "         \"title\":\"Arrival\",\n" +
            "         \"backdrop_path\":\"\\/yIZ1xendyqKvY3FGeeUYUd5X9Mm.jpg\",\n" +
            "         \"popularity\":38.098202,\n" +
            "         \"vote_count\":1312,\n" +
            "         \"video\":false,\n" +
            "         \"vote_average\":6.6\n" +
            "      },\n" +
            "      {  \n" +
            "         \"poster_path\":\"\\/kqjL17yufvn9OVLyXYpvtyrFfak.jpg\",\n" +
            "         \"adult\":false,\n" +
            "         \"overview\":\"An apocalyptic story set in the furthest reaches of our planet, in a stark desert landscape where humanity is broken, and most everyone is crazed fighting for the necessities of life. Within this world exist two rebels on the run who just might be able to restore order. There's Max, a man of action and a man of few words, who seeks peace of mind following the loss of his wife and child in the aftermath of the chaos. And Furiosa, a woman of action and a woman who believes her path to survival may be achieved if she can make it across the desert back to her childhood homeland.\",\n" +
            "         \"release_date\":\"2015-05-13\",\n" +
            "         \"genre_ids\":[  \n" +
            "            28,\n" +
            "            12,\n" +
            "            878,\n" +
            "            53\n" +
            "         ],\n" +
            "         \"id\":76341,\n" +
            "         \"original_title\":\"Mad Max: Fury Road\",\n" +
            "         \"original_language\":\"en\",\n" +
            "         \"title\":\"Mad Max: Fury Road\",\n" +
            "         \"backdrop_path\":\"\\/phszHPFVhPHhMZgo0fWTKBDQsJA.jpg\",\n" +
            "         \"popularity\":37.512622,\n" +
            "         \"vote_count\":6383,\n" +
            "         \"video\":false,\n" +
            "         \"vote_average\":7.1\n" +
            "      },\n" +
            "      {  \n" +
            "         \"poster_path\":\"\\/qjiskwlV1qQzRCjpV0cL9pEMF9a.jpg\",\n" +
            "         \"adult\":false,\n" +
            "         \"overview\":\"A rogue band of resistance fighters unite for a mission to steal the Death Star plans and bring a new hope to the galaxy.\",\n" +
            "         \"release_date\":\"2016-12-14\",\n" +
            "         \"genre_ids\":[  \n" +
            "            28,\n" +
            "            12,\n" +
            "            14,\n" +
            "            878\n" +
            "         ],\n" +
            "         \"id\":330459,\n" +
            "         \"original_title\":\"Rogue One: A Star Wars Story\",\n" +
            "         \"original_language\":\"en\",\n" +
            "         \"title\":\"Rogue One: A Star Wars Story\",\n" +
            "         \"backdrop_path\":\"\\/tZjVVIYXACV4IIIhXeIM59ytqwS.jpg\",\n" +
            "         \"popularity\":36.820321,\n" +
            "         \"vote_count\":1942,\n" +
            "         \"video\":false,\n" +
            "         \"vote_average\":7.2\n" +
            "      },\n" +
            "      {  \n" +
            "         \"poster_path\":\"\\/nHXiMnWUAUba2LZ0dFkNDVdvJ1o.jpg\",\n" +
            "         \"adult\":false,\n" +
            "         \"overview\":\"Vampire death dealer Selene fends off brutal attacks from both the Lycan clan and the Vampire faction that betrayed her. With her only allies, David and his father Thomas, she must stop the eternal war between Lycans and Vampires, even if it means she has to make the ultimate sacrifice.\",\n" +
            "         \"release_date\":\"2016-12-01\",\n" +
            "         \"genre_ids\":[  \n" +
            "            28,\n" +
            "            27\n" +
            "         ],\n" +
            "         \"id\":346672,\n" +
            "         \"original_title\":\"Underworld: Blood Wars\",\n" +
            "         \"original_language\":\"en\",\n" +
            "         \"title\":\"Underworld: Blood Wars\",\n" +
            "         \"backdrop_path\":\"\\/PIXSMakrO3s2dqA7mCvAAoVR0E.jpg\",\n" +
            "         \"popularity\":33.001754,\n" +
            "         \"vote_count\":642,\n" +
            "         \"video\":false,\n" +
            "         \"vote_average\":4.2\n" +
            "      },\n" +
            "      {  \n" +
            "         \"poster_path\":\"\\/n8WNMt7stqHUZEE7bEtzK4FwrWe.jpg\",\n" +
            "         \"adult\":false,\n" +
            "         \"overview\":\"Rachel Watson, devastated by her recent divorce, spends her daily commute fantasizing about the seemingly perfect couple who live in a house that her train passes every day, until one morning she sees something shocking happen there and becomes entangled in the mystery that unfolds.\",\n" +
            "         \"release_date\":\"2016-10-05\",\n" +
            "         \"genre_ids\":[  \n" +
            "            53\n" +
            "         ],\n" +
            "         \"id\":346685,\n" +
            "         \"original_title\":\"The Girl on the Train\",\n" +
            "         \"original_language\":\"en\",\n" +
            "         \"title\":\"The Girl on the Train\",\n" +
            "         \"backdrop_path\":\"\\/fpq86AP0YBYUwNgDvUj5kxwycxH.jpg\",\n" +
            "         \"popularity\":31.734996,\n" +
            "         \"vote_count\":680,\n" +
            "         \"video\":false,\n" +
            "         \"vote_average\":6\n" +
            "      },\n" +
            "      {  \n" +
            "         \"poster_path\":\"\\/oFOG2yIRcluKfTtYbzz71Vj9bgz.jpg\",\n" +
            "         \"adult\":false,\n" +
            "         \"overview\":\"After waking up in a hospital with amnesia, professor Robert Langdon and a doctor must race against time to foil a deadly global plot.\",\n" +
            "         \"release_date\":\"2016-10-13\",\n" +
            "         \"genre_ids\":[  \n" +
            "            28,\n" +
            "            80,\n" +
            "            9648,\n" +
            "            878,\n" +
            "            53\n" +
            "         ],\n" +
            "         \"id\":207932,\n" +
            "         \"original_title\":\"Inferno\",\n" +
            "         \"original_language\":\"en\",\n" +
            "         \"title\":\"Inferno\",\n" +
            "         \"backdrop_path\":\"\\/anmLLbDx9d98NMZRyVUtxwJR6ab.jpg\",\n" +
            "         \"popularity\":29.189494,\n" +
            "         \"vote_count\":1025,\n" +
            "         \"video\":false,\n" +
            "         \"vote_average\":5.5\n" +
            "      },\n" +
            "      {  \n" +
            "         \"poster_path\":\"\\/z6BP8yLwck8mN9dtdYKkZ4XGa3D.jpg\",\n" +
            "         \"adult\":false,\n" +
            "         \"overview\":\"Seven gun men in the old west gradually come together to help a poor village against savage thieves.\",\n" +
            "         \"release_date\":\"2016-09-14\",\n" +
            "         \"genre_ids\":[  \n" +
            "            12,\n" +
            "            28,\n" +
            "            37\n" +
            "         ],\n" +
            "         \"id\":333484,\n" +
            "         \"original_title\":\"The Magnificent Seven\",\n" +
            "         \"original_language\":\"en\",\n" +
            "         \"title\":\"The Magnificent Seven\",\n" +
            "         \"backdrop_path\":\"\\/T3LrH6bnV74llVbFpQsCBrGaU9.jpg\",\n" +
            "         \"popularity\":28.026704,\n" +
            "         \"vote_count\":1322,\n" +
            "         \"video\":false,\n" +
            "         \"vote_average\":5.3\n" +
            "      }\n" +
            "   ],\n" +
            "   \"total_results\":19629,\n" +
            "   \"total_pages\":982\n" +
            "}";

    Gson gson =	new	Gson();

    movieCollection myClass;
    movie newMovie;

    @Before
    public void parseJSON(){
        myClass = gson.fromJson(myCollection,movieCollection.class);
        newMovie = gson.fromJson(myMovie,movie.class);
        System.out.println(myClass.allMovieTitles());
        System.out.println(myClass.vote5());
        System.out.println(myClass.pop1());
        System.out.println(myClass.genre14());
    }
    @Test
    public void getResult() throws Exception {
        movie[] movies = myClass.getResult();
        assertEquals(movies.length,20);
        assertEquals(newMovie.toString(),(movies[0].toString()));
    }

    @Test
    public void getPage() throws Exception {
        assertEquals(1,myClass.getPage());
    }

    @Test
    public void getTotal_pages() throws Exception {
        assertEquals(982,myClass.getTotal_pages());

    }

    @Test
    public void getTotal_results() throws Exception {
        assertEquals(19629,myClass.getTotal_results());

    }



}