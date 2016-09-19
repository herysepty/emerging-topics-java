/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hierarchy;

import java.util.Arrays;
import java.util.List;

/**
 *
 * @author HERY
 */
public class HFTC {
    String pointers[] ;
    public static void main(String args[]){
        HFTC hftc = new HFTC();
        String terms[] ={"sun","fun","beach","surf"} ; 
        String tweet_preprocessing[] ={"sun","fun","beach","surf"} ; 
        hftc.combinationTerm(terms,tweet_preprocessing,3);
    }
    
    public void combinationTerm(String[] terms, String[] tweet_preprocessing, int minsupp)
    {
        String cluster[];
        String set[] = terms;
        String flag_minsupp[];
        for (String term : terms) {
            //System.out.println(term);
        }
        int set_size = set.length;
        for (int subset_size = 1; subset_size < set_size; subset_size++) {
            if(subset_size == 1)
            {
//                arra_chunk() in php
                String terms_single[] = set;
                System.out.println(Arrays.toString(terms_single));
            }
            else
            {
                String set_keys[] =set;
                /*array sets*/
                
                pointers = Arrays.copyOfRange(set_keys, 0, set_size);
                do {                    
                   //String combinations = getCombination(set); 
                } while (true);
            }
        }
        
    }
    public void getCombination(String[] set)
    {
       // String set_keys = set;
        String combination[][] = {};
        for (String pointer : pointers) {
            //combination[set_keys[pointer]] = set[set_keys[pointer]];
        }
        //return combination;
    }
}
