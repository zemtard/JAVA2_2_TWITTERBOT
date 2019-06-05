package com.company;

import twitter4j.*;

import java.util.ArrayList;
import java.util.List;

public class Tweeter implements Runnable{
    private Twitter twitter;
    private List<Status> statuses;
    private RSSreader reader;

    public Tweeter(TwitterFactory tf, RSSreader reader){
        this.statuses = new ArrayList<>();
        this.twitter = tf.getInstance();
        this.reader = reader;
    }

    //POSTS A TWEET

    public void postTweet(String tweet){
        try {
            twitter.updateStatus(tweet);
            System.out.println("You just tweeted: "+ tweet);
        } catch (TwitterException e) {
            e.printStackTrace();
        }
    }


    //GETS TWEETS WHICH CONTAINS THE STRING PASSED TO THE METHOD

    public void getIfContains(String text, int count){
        Query query = new Query(text);
        try {
            query.count(count);
            QueryResult result = this.twitter.search(query);
            System.out.println(result.getTweets().size());
            for(var tweet : result.getTweets()){
                    System.out.println(">>USER<<: -"+tweet.getUser().getName()+ "- >>TWEETED<< /"+tweet.getText()+"/");
            }

        } catch (TwitterException e) {
            e.printStackTrace();
        }
    }

    //REPLIES TO TWEET

    public void reply(Status tweet, String text){
        try {
            StatusUpdate stat = new StatusUpdate(text);
            stat.setInReplyToStatusId(tweet.getInReplyToStatusId());
            this.twitter.updateStatus("@"+tweet.getUser().getScreenName()+" "+text);
            //twitter.updateStatus(new StatusUpdate("@" + tweet.getUser().getScreenName()+" " + text));
        } catch (TwitterException e) {
            e.printStackTrace();
        }
    }


    //CHECKS IF TWEET CONTAINS BOTS NAME AND IF THE TWEET CONTAINS NEWS AND SENDS THE LATEST WORLD NEWS AND TAGS THE USER WHO ASKED
    public void replyWorldNews(RSSreader reader){
        Query query = new Query("@BotJanis");
        try {
            query.count(1);
            QueryResult result = this.twitter.search(query);
            for(var tweet : result.getTweets()){
                if (tweet.getText().contains("news")) {
                    System.out.println("Sending newest world news article");
                    reply(tweet, reader.getTweet());
                }
            }

        } catch (TwitterException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        Query query = new Query("@BotJanis");
        try {
            query.count(1);
            QueryResult result = this.twitter.search(query);
            for(var tweet : result.getTweets()){
                if (tweet.getText().contains("world news")) {
                    System.out.println("Sending newest world news article");
                    reply(tweet, reader.getTweet());
                }
            }

        } catch (TwitterException e) {
            e.printStackTrace();
        }
    }
}
