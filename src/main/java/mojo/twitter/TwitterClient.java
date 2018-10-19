package mojo.twitter;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;

public class TwitterClient {
	private Twitter twitter = TwitterFactory.getSingleton();
	private String tweet;
	
	public TwitterClient() {
		
	}
	
	/*
	 * This method will post a new tweet with the current countries owned
	 * by the player.
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
	 */
	public void setTweet(String tweet) {
		this.tweet = tweet;
		return;
	}
	
	/**
	 * Getter function for tweet message
	 * @return the currently set tweet
	 */
	public String getTweet() {
		return this.tweet;
	}
}
