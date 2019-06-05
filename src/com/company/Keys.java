package com.company;

import twitter4j.conf.Configuration;
import twitter4j.conf.ConfigurationBuilder;

public class Keys {

    private String apiKey = "zd6nFfyE9m0n65iaFNijkGmk4";
    private String apiKeySecret = "D2lwyFOGrY3Y0Woytt4ZaSgBw31nsjQD5p76VpJuEHNHipJWLK";
    private String aToken = "1135617470943977473-F5g6nwrz6TJOesX8cj42bbp1Huv6G0";
    private String aTokenSecret = "e0lZMv1UOrqc0o4A37ZQysYEhHzndaONj4MYOld1nH0a7";
    private ConfigurationBuilder cb;

    public Keys(){
        this.cb = new ConfigurationBuilder();
        this.cb.setDebugEnabled(true)
                .setOAuthConsumerKey(this.apiKey)
                .setOAuthConsumerSecret(this.apiKeySecret)
                .setOAuthAccessToken(this.aToken)
                .setOAuthAccessTokenSecret(this.aTokenSecret);
    }

    public Configuration build(){
        return cb.build();
    }
}
