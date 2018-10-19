package mojo;

import mojo.notification.*;
import java.util.*;
import mojo.risk.*;

public class GameEngine {
	// Territory territory;
	// Player player;
	private static GameEngine init = new GameEngine();
	Setup s = Setup.getInstances();
	private int cardSet = 0;
	private List<Card> deck = s.getDeck();
	NotificationCenter notificationCenter = new NotificationCenter();

	private GameEngine() {

	}

	public static GameEngine getInit() {
		return init;
	}

	public int giveunits(Territory terr, int addingUnits) {
		int units = terr.getNumOfUnits();
		units = units + addingUnits;
		terr.setNumOfUnits(units);
		return terr.getNumOfUnits();
	}

	public void attack(Territory act, Territory def, int attackingUnits) {
		// Scanner key = new Scanner(System.in);

		int attUnits = attackingUnits;
		int defUnits = def.getNumOfUnits();

		int attackNumOfRolls = 0;
		int defendNumOfRolls = 0;
		// boolean attackAgain = true;
		// while (attackAgain) {
		if (attUnits >= 3) {
			attackNumOfRolls = 3;
		} else if (attUnits == 2) {
			attackNumOfRolls = 2;
		} else {
			attackNumOfRolls = 1;
		}

		if (defUnits >= 2) {
			defendNumOfRolls = 2;
		} else {
			defendNumOfRolls = 1;
		}

		List<Player> players = s.getPlayers();
		int idOfAttPlayer = act.getOwner();
		Player attPlayer = s.getPlayerById(idOfAttPlayer);
		int idOfDefPlayer = def.getOwner();
		Player defPlayer = s.getPlayerById(idOfDefPlayer);
		
		// Create Attack Notification
		Notification notification = new AttackNotification(idOfAttPlayer, idOfDefPlayer, def.getName());
		
		// Send Notification to Notification Center
		// TODO create publicly accessible notification center
		notificationCenter.setNotification(notification);
		notificationCenter.updatePlayer();

		players.get(idOfAttPlayer - 1).rollDices(attackNumOfRolls);
		players.get(idOfDefPlayer - 1).rollDices(defendNumOfRolls);
		List<Integer> attDice = players.get(idOfAttPlayer - 1).getDice();
		List<Integer> defDices = players.get(idOfDefPlayer - 1).getDice();

		Collections.sort(attDice);
		Collections.reverse(attDice);

		Collections.sort(defDices);
		Collections.reverse(defDices);


		int defLostUnits = 0;
		int attLostUnits = 0;
		int biggerSize = 0;

		if (attDice.size() > defDices.size()) {
			// System.out.println("more attacking dice");
			biggerSize = defDices.size();
		} else {
			biggerSize = attDice.size();
		}
		for (int i = 0; i < biggerSize; i++) {
			System.out.println("Attack rolled a " + attDice.get(i)+".        the defense rolled a " + defDices.get(i));

			if (attDice.get(i) > defDices.get(i)) {

				System.out.println("Attacking won defense will lose one unit");
				System.out.println(" ");
				System.out.println(" ");
				defLostUnits++;
			} else if (attDice.get(i) < defDices.get(i)) {
				System.out.println(" defense won attacking will lose one unit");
				System.out.println(" ");
				System.out.println(" ");

				attLostUnits++;
			} else {
				System.out.println(" tie so attacking will lose one unit");
				System.out.println(" ");
				System.out.println(" ");


				attLostUnits++;
			}
		}

		if (def.getNumOfUnits() - defLostUnits <= 0) {
			System.out.println("PLAYER " + def.getOwner() + " lost " + def.getName() + ".\n It now belongs to player "
					+ act.getOwner() + " and has " + (attackingUnits - attLostUnits) + " units on it");
				System.out.println(" ");
				System.out.println(" ");

			//remove territory from previous owner
			defPlayer.removeTerritory(def);
			//set new owner
			def.setOwner(act.getOwner());
			//set number of units now
			def.setNumOfUnits(attackingUnits - attLostUnits);
			act.setNumOfUnits(act.getNumOfUnits() - attackingUnits);
			//add territory to list
			attPlayer.addTerritory(def);

			attPlayer.printableTerritories();
			attPlayer.setAttackedAtLeastOnces(true);
		}
		 else
		{
			def.setNumOfUnits(def.getNumOfUnits() - defLostUnits);
			act.setNumOfUnits(act.getNumOfUnits() - attLostUnits);
		}
	}

	/**
	 *
	 * @param player pass in the current player
	 * @return A string saying how many more units they have and how much they have
	 *         now
	 */
	public String trade(Player p) {
		int temp = p.getArmiesCount();
		//
		List<Card> pCards=p.getCards();

		for(int i = 0; i<pCards.size();i++)
		{
			System.out.println(pCards.get(i).getTerritoryName()+" "+pCards.get(i).getType());
			deck.add(pCards.get(i));
			p.remvoeCard(pCards.get(i));
		}




		int[] cardgroup = { 3, 5, 8, 10, 12, 15, 20, 25, 30, 35, 40, 45 };
		p.setArmiesCount(temp + cardgroup[cardSet]);
		String returnable = "You got " + cardgroup[cardSet] + " more units. You now have " + p.getArmiesCount()
				+ "in total.";
		if (cardSet != 12) {
			cardSet++;
		}

		return returnable;
	}

	public void fortify(Territory from, Territory to, int numUnits) {
		int units = from.getNumOfUnits() - numUnits;
		from.setNumOfUnits(units);
		to.setNumOfUnits(to.getNumOfUnits()+numUnits);
	}


	public void handOutCard(Player p){
		System.out.println("Player "+p.getId()+" is getting card "+deck.get(0).getTerritoryName()+" "+deck.get(0).getType());
		p.addCard(deck.get(0));
		deck.remove(0);

	}

	/**
	 * This method will return a list of cards shuffled
	 *
	 * @param deck the card list to be shuffled
	 */
	public  void shuffleCards() {
		Collections.shuffle(deck);
	}
}
