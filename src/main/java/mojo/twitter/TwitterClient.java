package mojo.twitter;

import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.*;

public class TwitterClient {
	private String tweet;
	private Twitter twitter;

	public TwitterClient() {
		String consumerKey = System.getenv("twitterConsumerKey");
		String consumerSecret = System.getenv("twitterConsumerSecret");
		String accessToken = System.getenv("twitterAccessToken");
		String accessTokenSecret = System.getenv("twitterAccessTokenSecret");

		// Store the API credentials for twitter here using env variables.
		ConfigurationBuilder cb = new ConfigurationBuilder();
		cb.setApplicationOnlyAuthEnabled(false);
		cb.setOAuthConsumerKey(consumerKey)
			.setOAuthConsumerSecret(consumerSecret)
			.setOAuthAccessToken(accessToken)
			.setOAuthAccessTokenSecret(accessTokenSecret)
			.setJSONStoreEnabled(true)
			.setIncludeEntitiesEnabled(true);
		TwitterFactory tf = new TwitterFactory(cb.build());
		twitter=tf.getInstance();
	}

	/*
	 * This method will post a new tweet with the current countries owned by the
	 * player.
	 */
	public String postTweet() {
		try {
			twitter.updateStatus(tweet);
			return "Tweet posted successfully!";
		} catch (TwitterException e) {
			return e.toString();
		}
	}

	/**
	 * This will set the message that will be sent in the tweet.
     * @param tweet This is the message that will be tweeted
	 */
	public void setTweet(String tweet) {
		this.tweet = tweet;
		return;
	}

	/**
	 * Getter function for tweet message
	 * 
	 * @return the currently set tweet
	 */
	public String getTweet() {
		return this.tweet;
	}
}
