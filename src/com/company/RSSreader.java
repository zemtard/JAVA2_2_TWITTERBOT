package com.company;

import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.FeedException;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;


public class RSSreader implements Runnable {

    private String url = "http://feeds.reuters.com/Reuters/worldNews";
    private String tweet;

    @Override
    public void run() {

        try {
            URL urll = new URL(this.url);
            SyndFeedInput input = new SyndFeedInput();
            SyndFeed feed;
            feed = input.build(new XmlReader(urll));

            for(var o : feed.getEntries()){
                SyndEntry article = (SyndEntry) o;
                this.tweet = article.getTitle() + "\n" + article.getLink();
                System.out.println(tweet);
                break;
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (FeedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public String getTweet(){
        return this.tweet;
    }
}
