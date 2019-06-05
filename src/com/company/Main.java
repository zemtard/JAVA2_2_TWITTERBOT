package com.company;

import twitter4j.TwitterFactory;


public class Main {

    public static void main(String[] args){


        Keys auth = new Keys();
        RSSreader reader = new RSSreader();
        Tweeter tweeter = new Tweeter(new TwitterFactory(auth.build()),reader);

        var t1 = new Thread(reader);
        var t2 = new Thread(tweeter);

        t1.start();
        t2.start();

        while(true) {
            tweeter.getIfContains("@BotJanis", 1);
            t1.run();
            t2.run();

            try {
                Thread.sleep(30000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }








    }
}
