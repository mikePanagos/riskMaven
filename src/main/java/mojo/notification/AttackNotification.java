package mojo.notification;

public class AttackNotification implements Notification {
	private String message;
	private String territory;
	private int attackingPlayer;
	private int defendingPlayer;
	
	AttackNotification(int attackingPlayer, int defendingPlayer, String territoryUnderAttack) {
		this.territory = territoryUnderAttack;
		setSender(attackingPlayer);
		setReceiver(defendingPlayer);
		setContent();
	}
	
	@Override
	public void setContent() {
		// TODO Auto-generated method stub
		message = "Player " + attackingPlayer + " is attacking your territory "
				+ territory + "!";
		return;
	}

	@Override
	public void setReceiver(int defendingPlayer) {
		// TODO Auto-generated method stub
		this.defendingPlayer = defendingPlayer;
		return;
	}

	@Override
	public void setSender(int attackingPlayer) {
		// TODO Auto-generated method stub
		this.attackingPlayer = attackingPlayer;
	}
	
	@Override
	public int getReceiver() {
		// TODO Auto-generated method stub
		return this.defendingPlayer;
	}
	
	@Override
	public int getSender() {
		// TODO Auto-generated method stub
		return this.attackingPlayer;
	}
}
