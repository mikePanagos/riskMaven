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
		observers=new ArrayList<Player>();
		Setup s=Setup.getInstances();

		/**
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * this needs to be talked about this wasnt here i added it bc observers was never getteing filled i dont know where it gets filled -michael
		 */
		for (int i = 0; i < s.getPlayers().size(); i++) {
			add(s.getPlayers().get(i));
		}
		 
	}
	
	/**
	 * This method will add a player to the list of observers kept.
	 * @param player The player that will be added to the observer list.
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
		System.out.println("got here");
		Player playerToNotify = null;
		for (int i = 0; i < observers.size(); i++) {
			if (observers.get(i).getId() == notification.getReceiver()) {
				System.out.println("found");
				playerToNotify = observers.get(i);
				break;
			}
		}
		playerToNotify.sendNotification(notification);
		return true;
	}
}
