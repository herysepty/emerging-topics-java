/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backup;

import java.util.ArrayList;
import java.util.HashMap;
import org.json.simple.parser.JSONParser;
import twitter.Tweet;

/**
 *
 * @author HERY
 */
/*
    n = jumlah tweet
    m = jumlah mention
    ki = jumlah mention ke i
    
*/


public class LinkAnomaly {
    String tweet ;
    int total_tweet;
    int total_mention;
    double total_probability;
    double delta = 0.5;
    double beta = 0.5;
    
    public static void main (String[] args)
    {
//        for (Object tw : probability().values()) {
//            System.out.println(tw);
//        }
    }
   // public static HashMap probability()
    public void probability()
    {
        ArrayList<Tweet> tweets = new ArrayList<Tweet>();
        int arrayTweets[];
        HashMap<String,Integer> hashmap_tweet = new HashMap<>();
        
        JSONParser parser = new JSONParser();
        
        tweets.add(new Tweet("001", "@herysepty @ceptie @septy banjir"));
        tweets.add(new Tweet("002", "@herysepty @herysepty"));
        tweets.add(new Tweet("003", "@herysepty terjadi banjir"));
        tweets.add(new Tweet("004", "@septy @septy bencana indonesia"));
        
        //JSONParser parser = new JSONParser();

        int i = 0;
        int total_mention_per_tweet=0;
        for (Tweet tw : tweets) {
            total_mention_per_tweet=0;
            for (String t : tw.getTweet().split(" ")) {
                if(t.substring(0,1).equals("@")){
                    total_mention_per_tweet = total_mention_per_tweet + 1;
                }
                hashmap_tweet.put(tw.id_str, total_mention_per_tweet);
            }
        }
        
        total_tweet = tweets.size();
        System.out.println("Total Tweet "+total_tweet);
        
        //return hashmap_tweet;
        total_mention = 0;
        
        for (Object tw : hashmap_tweet.values()) {
            
            total_mention =  (int) tw + total_mention;
        }
        
        System.out.println("Total mention : "+total_mention);
        total_probability=0;
        double total_probability_tweet = 0;
        for (Object tw : hashmap_tweet.values()) {
            total_probability_tweet = probabilityTweet(hashmap_tweet,(int) tw,total_tweet,total_mention);
            total_probability = total_probability + total_probability_tweet;
            System.out.println("Probability tweet("+tw+") : "+total_probability_tweet);
        }
        
        System.out.println(total_probability);
    }
    
    public double probabilityTweet(HashMap hashmap_tweet, int k, int n, int m)
    {
        double total_probaility_tweet=0;
        double total_j = 0;
        int j=0;
        
        total_probaility_tweet = (n + delta) / (m + k + beta);
        
        for (Object tw : hashmap_tweet.values()) {
            total_j = (m + beta + j) / (n + m + delta + j + beta);
            total_probaility_tweet = total_probaility_tweet * total_j;
            j++;
        }
        return total_probaility_tweet;
    }
}
