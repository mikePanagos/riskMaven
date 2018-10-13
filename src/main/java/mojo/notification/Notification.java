package mojo.notification;

public interface Notification {
	void setContent();
	void setReceiver(int playerId);
	void setSender(int playerId);
	int getReceiver();
	int getSender();
}