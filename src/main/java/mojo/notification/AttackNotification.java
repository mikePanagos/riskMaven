package mojo.notification;

public class AttackNotification implements Notification {
	private String message;
	private String territory;
	private int attackingPlayer;
	private int defendingPlayer;
	
	/**
	 * This is the constructor for the attack notification. It will collect all the information needed
	 * so that the information will reach the necessary player.
	 * @param attackingPlayer the id of the player who has attacked
	 * @param defendingPlayer the id of the player whose territory is under attack
	 * @param territoryUnderAttack the name of the territory under attack
	 */
	AttackNotification(int attackingPlayer, int defendingPlayer, String territoryUnderAttack) {
		this.territory = territoryUnderAttack;
		setSender(attackingPlayer);
		setReceiver(defendingPlayer);
		setContent();
	}
	
	/**
	 * This method will create the message notifying the player of the attack on their territory
	 */
	@Override
	public void setContent() {
		// TODO Auto-generated method stub
		message = "Player " + attackingPlayer + " is attacking your territory "
				+ territory + "!";
		return;
	}

	/**
	 * This method will set the id of the player who is under attack. This is needed so that the notification
	 * can be routed to the appropriate player.
	 */
	@Override
	public void setReceiver(int defendingPlayer) {
		// TODO Auto-generated method stub
		this.defendingPlayer = defendingPlayer;
		return;
	}
	
	/**
	 * This method will set the id of the player who is attacking. This will allow the attack message to be
	 * customizable.
	 */
	@Override
	public void setSender(int attackingPlayer) {
		// TODO Auto-generated method stub
		this.attackingPlayer = attackingPlayer;
	}
	
	/**
	 * This will return the id of the player who is to receive the message.
	 */
	@Override
	public int getReceiver() {
		// TODO Auto-generated method stub
		return this.defendingPlayer;
	}
	
	/**
	 * This will return the id of the player who initiated this message.
	 */
	@Override
	public int getSender() {
		// TODO Auto-generated method stub
		return this.attackingPlayer;
	}
	
	/**
	 * This will print out the message of the attack notification.
	 */
	@Override
	public void getMessage() {
		// TODO Auto-generated method stub
		System.out.println(message);
	}
}
