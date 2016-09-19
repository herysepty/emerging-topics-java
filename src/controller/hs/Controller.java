/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.hs;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import link.anomaly.LinkAnomaly;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import twitter.Tweet;

/**
 *
 * @author HERY
 */
public class Controller {
     
    public static void main(String args[])
    {
        Controller ctr = new Controller();
        ctr.arraylistHs();
        ctr.getTweetJson();
    }
    
    public void arraylistHs()
    {
        ArrayList<Tweet> tweets = new ArrayList<Tweet>();
        tweets.add(new Tweet("001", "@herysepty @ceptie @septy banjir"));
        tweets.add(new Tweet("002", "@herysepty @herysepty"));
        tweets.add(new Tweet("003", "@herysepty terjadi banjir"));
        tweets.add(new Tweet("004", "@septy @septy bencana indonesia"));
        for (Tweet tw : tweets) {
            System.out.println(tw.tweet);
        }
    }
     public void getTweetJson()
    {
        LinkAnomaly linkanomaly = new LinkAnomaly();
        linkanomaly.probability();
        JSONParser parser = new JSONParser();
        try {     
            Object obj = parser.parse(new FileReader("C:\\Users\\HERY\\Documents\\NetBeansProjects\\EmergingTopics\\build\\classes\\dataset\\dataset.json"));

    //  JSONObject jsonObject =  (JSONObject) obj;
    //  String name = (String) jsonObject.get("name");
    //  System.out.println(name);
    //  loop array
    //  JSONArray cars = (JSONArray) jsonObject.get("cars");
    //  Iterator<String> iterator = cars.iterator();
    //  while (iterator.hasNext()) {
    //      System.out.println(iterator.next());
    //  }
            
            JSONArray tweets = (JSONArray) obj;
            for(Object tweet : tweets)
            {
                JSONObject tw = (JSONObject) tweet;
                String name = (String) tw.get("text");
                System.out.println(name);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
    
}
