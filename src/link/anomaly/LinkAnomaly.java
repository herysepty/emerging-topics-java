/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package link.anomaly;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
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
    public int total_tweet;
    int total_mention;
    double total_probability;
    double delta = 0.5;
    double beta = 0.5;

    public void probability()
    {
        HashMap<String,Integer> hashmap_tweet = new HashMap<>();
        
        JSONParser parser = new JSONParser();
        
        int i = 0;
        int total_mention_per_tweet=0;
        
        System.out.println("=============================================================");

        try {     
            Object obj = parser.parse(new FileReader("C:\\Users\\HERY\\Documents\\NetBeansProjects\\EmergingTopics\\build\\classes\\dataset\\dataset.json"));
        JSONArray tweetss = (JSONArray) obj;
        total_tweet = tweetss.size();
            for(Object tweet : tweetss)
            {
                total_mention_per_tweet=0;
                JSONObject tw = (JSONObject) tweet;
                String id_str = (String) tw.get("id_str");
                String text = (String) tw.get("text");

                
                JSONObject user = (JSONObject) tw.get("user");
                
                String screen_name = (String) user.get("screen_name");
                System.out.println("Screen name : "+screen_name+" \tText: "+text);
                
                for (String t : text.split(" ")) {
                    if(t.substring(0,1).equals("@")){
                        total_mention_per_tweet = total_mention_per_tweet + 1;
                    }
                    hashmap_tweet.put(id_str, total_mention_per_tweet);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        
        System.out.println("=============================================================");
        System.out.println("Total Tweet "+total_tweet);
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
            System.out.println("Probability tweet (total mention : "+tw+") : "+total_probability_tweet);
        }
        System.out.println("Total probability: "+total_probability);
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
