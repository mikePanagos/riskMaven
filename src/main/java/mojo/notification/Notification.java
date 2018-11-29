package mojo.notification;

/**
 * This is the notification interface. Notifications are designed to be flexible so that
 * a player can be alerted of different events happening within the game.
 */
public interface Notification {
	void setContent();
	void setReceiver(long playerId);
	void setSender(long playerId);
	long getReceiver();
	long getSender();
	String getMessage();
}