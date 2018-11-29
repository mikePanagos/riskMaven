package mojo;

import org.junit.Test;
import static org.junit.Assert.*;
import mojo.twitter.*;
import java.util.*;

public class TwitterClientTest {
	@Test
	public void postTweetTest() {
		TwitterClient twitterAccount = new TwitterClient();
		Random rand = new Random();
		twitterAccount.setTweet("attacking" + " " + (rand.nextInt(	1000) + 1));
		System.out.println("Twitter Tweet Test");
		System.out.println("Attempting to send Tweet...");
		assertTrue("The Tweet did not send successfully, check configuration.", twitterAccount.postTweet().equals("Tweet posted successfully!"));
		System.out.println("Tweet sent successfully!\n");
	}

	@Test
	public void setTweetTest() {
		TwitterClient twitterAccount = new TwitterClient();
		twitterAccount.setTweet("Testing the functionality");
		System.out.println("Twitter setTweet Test");
		System.out.println("Setting Tweet...");
		assertTrue("The method failed to set the Tweet contents!", twitterAccount.getTweet().equals("Testing the functionality"));
		System.out.println("Test was successful!\n");
	}

	@Test
	public void getTweetTest() {
		TwitterClient twitterAccount = new TwitterClient();
		twitterAccount.setTweet("Testing getTweet() Method");
		System.out.println("Twitter getTweet Test");
		System.out.println("Getting Tweet...");
		assertTrue("The method failed to retrieve the Tweet contents!",
				twitterAccount.getTweet().equals("Testing getTweet() Method"));
		System.out.println("Test was successful!\n");
	}
}
