package mojo;

import org.junit.Test;
import static org.junit.Assert.*;
import mojo.NotificationCenter;
import mojo.risk.Player;
import mojo.notification.*;

public class NotificationCenterTest {
	
	@Test
	public void addPlayerTest() {
		NotificationCenter notificationCenter = new NotificationCenter();
		Player p = new Player();
		System.out.println("Running Notification Center addPlayer() Method Test");
		System.out.println("Attempting to add a player to the list...");
		assertTrue("An exception occured while adding the player!",
				notificationCenter.add(p).equals("Success!"));
		System.out.println("The test was successful!\n");
	}
	
	@Test
	public void setNotificationTest() {
		NotificationCenter notificationCenter = new NotificationCenter();
		Notification n = new AttackNotification(0, 0, "Testing");
		System.out.println("Running Notification Center setNotification() Method Test");
		System.out.println("Attempting to set a notification...");
		assertTrue("An exception occured while setting the notification!",
				notificationCenter.setNotification(n) == true);
		System.out.println("The test was successful!\n");
	}

	public void getSenderTest() {
		Notification n = new AttackNotification(111111111, 222222222, "Testing");
		System.out.println("Running Notification Center getSender() Method Test");
		System.out.println("Attempting to set a notification...");
		assertTrue("An exception occured while setting the notification!",
				n.getSender()==222222222);
		System.out.println("The test was successful!\n");
	}
	
	@Test
	public void updatePlayerTest() {
		NotificationCenter notificationCenter = new NotificationCenter();
		Player p = new Player();
		Notification n = new AttackNotification(0, 0, "Testing");
		notificationCenter.setNotification(n);
		notificationCenter.add(p);
		System.out.println("Running Notification Center updatePlayer() Method Test");
		System.out.println("Attempting to update the player...");
		assertTrue("An exception occured",
				notificationCenter.updatePlayer() == true);
		System.out.println("The test was successful!\n");
	}

	@Test
	public void AttackNotificationTest() {
		AttackNotification notificationCenter = new AttackNotification();
		
		assertTrue("AttackNotificationTest",
		notificationCenter.getSender()==0 );
		System.out.println("The test was successful!\n");
	}

}
