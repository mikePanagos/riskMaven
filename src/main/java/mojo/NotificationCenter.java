package mojo;
import java.util.List;
import java.util.ArrayList;
import mojo.risk.*;
import mojo.notification.*;

public class NotificationCenter {
	private List<Player> observers;
	private Notification notification;
	
	/**
	 * Constructor used to initiate the notification center.
	 */
	NotificationCenter() {
		observers = new ArrayList<Player>();
		return;
	}
	
	/**
	 * This method will add a player to the list of observers kept.
	 * @param player
	 */
	public void add(Player player) {
		observers.add(player);
		return;
	}
	
	/**
	 * This method will set the notification to be sent.
	 * @param notification The notification that was created by the game
	 */
	public void setNotification(Notification notification) {
		this.notification = notification;
		return;
	}
	
	/**
	 * This method will update the player who is supposed to receive the notification.
	 */
	public void updatePlayer() {
		Player playerToNotify = null;
		for (int i = 0; i < observers.size(); i++) {
			if (observers.get(i).getId() == notification.getReceiver()) {
				playerToNotify = observers.get(i);
				break;
			}
		}
		playerToNotify.sendNotification(notification);
		return;
	}
}
