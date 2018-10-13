package mojo;
import java.util.List;
import java.util.ArrayList;
import mojo.risk.*;
import mojo.notification.*;

public class NotificationCenter {
	private List<Player> observers;
	private Notification notification;
	
	NotificationCenter() {
		observers = new ArrayList<Player>();
		return;
	}
	
	public void add(Player player) {
		observers.add(player);
		return;
	}
	/*
	public Notification getNotification() {
		return this.notification;
	}
	*/
	public void setNotification(Notification notification) {
		this.notification = notification;
		return;
	}
	
	public void updatePlayer() {
		
		//player.update();
		return;
	}
}
