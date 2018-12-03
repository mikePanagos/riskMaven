package mojo;
import java.util.List;
import java.util.ArrayList;
import mojo.risk.*;
import mojo.notification.*;

public class NotificationCenter {
	private List<Player> observers;
	private Notification notification;
	Setup setup;
	
	/**
	 * Constructor used to initiate the notification center.
	 */
	NotificationCenter() {
		// This will grab the game setup singleton.
        setup = Setup.getInstances();

	}
	
	/**
	 * This method will add a player to the list of observers kept.
	 * @param player The player that will be added to the observer list.
     * @return Returns Success if the player was added correctly, otherwise returns the exception.
	 */
	public String add(Player player) {
		try {
			observers.add(player);
			return "Success!";
		} catch (Exception e) {
			return e.toString();
		}
	}
	
	/**
	 * This method will set the notification to be sent.
	 * @param notification The notification that was created by the game
	 * @return boolean Returns whether setting the notification was successful or if it failed. True: successful. False: failed.
	 */
	public boolean setNotification(Notification notification) {
		if ((this.notification = notification) != null) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * This method will update the player who is supposed to receive the notification.
	 * @return boolean Returns whether the update was successful or if it failed. True: successful. False: failed.
	 */
	public boolean updatePlayer() {
		System.out.println("Player is in the process of being notified.");
		Player playerToNotify = null;
		for (int i = 0; i < setup.getPlayers().size(); i++) {
			if (setup.getPlayers().get(i).getId() == notification.getReceiver()) {
				System.out.println("Found the player!");
				playerToNotify = setup.getPlayers().get(i);
				System.out.println(playerToNotify);
				break;
			}
		}
		System.out.println("Sending the notification to player id " + playerToNotify.getId() + ".");
		playerToNotify.sendNotification(notification);
		return true;
	}
}
