package mojo;

import java.util.*;

import mojo.risk.*;

public class GameEngine {
	// Territory territory;
	// Player player;
	private static GameEngine init = new GameEngine();
	Setup s = Setup.getInstances();
	private int cardSet = 0;
	private List<Card> deck = s.getDeck();

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

	public String attack(Territory act, Territory def, int attackingUnits) {

		int attUnits = attackingUnits;
		int defUnits = def.getNumOfUnits();

		int attackNumOfRolls = 0;
		int defendNumOfRolls = 0;
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
		int idOfDefPLayer = def.getOwner();
		// System.out.println("attacking units allow this amount of rolls" + attackNumOfRolls + " def this amount"
		// 		+ defendNumOfRolls);
		players.get(idOfAttPlayer - 1).rollDices(attackNumOfRolls);
		players.get(idOfDefPLayer - 1).rollDices(defendNumOfRolls);
		List<Integer> attDice = players.get(idOfAttPlayer - 1).getDice();
		List<Integer> defDices = players.get(idOfDefPLayer - 1).getDice();

		Collections.sort(attDice);
		Collections.reverse(attDice);
		Collections.sort(defDices);
		Collections.reverse(defDices);
		// System.out.println(
		// 		"the amount of rolls for attact is " + attDice.size() + " and the number of def is " + defDices.size());

		int defLostUnits = 0;
		int attLostUnits = 0;

		if (attDice.size() > defDices.size()) {
			// System.out.println("more attacking dice");

			for (int i = 0; i < defDices.size(); i++) {
				System.out.println("attack rolled a " + attDice.get(i));
				System.out.println("the defence rolled a " + defDices.get(i));

				if (attDice.get(i) > defDices.get(i)) {

					System.out.println("attacking won defence will lose one unit");
					defLostUnits++;
				} else if (attDice.get(i) < defDices.get(i)) {
					System.out.println(" defence won attacking  will lose one unit");

					attLostUnits++;
				} else {
					System.out.println(" tie so attacking  will lose one unit");

					attLostUnits++;
				}
			}

		} else {

			for (int i = 0; i < attDice.size(); i++) {
				System.out.println("attack rolled a " + attDice.get(i));
				System.out.println("the defence rolled a " + defDices.get(i));
				// System.out.println("more def dice");

				if (attDice.get(i) > defDices.get(i)) {
					System.out.println("attacking won defence will lose one unit");
					defLostUnits++;
				} else if (attDice.get(i) < defDices.get(i)) {
					System.out.println(" defence won attacking  will lose one unit");

					attLostUnits++;
				} else {
					System.out.println(" tie so attacking  will lose one unit");

					attLostUnits++;
				}
			}

		}

		if (def.getNumOfUnits() - defLostUnits <= 0) {
			System.out.println("Player " + def.getOwner() + " lost " + def.getName() + "\n it now belongs to player "
					+ act.getOwner() + " and has " + (attackingUnits - attLostUnits) + " units on it");
			players.get(def.getOwner()-1).removeTerritory(def);
			def.setOwner(act.getOwner());
			System.out.println("new territory have "+((attackingUnits - attLostUnits)));
			def.setNumOfUnits(attackingUnits - attLostUnits);
			act.setNumOfUnits(act.getNumOfUnits() - attackingUnits);
			players.get(act.getOwner()-1).addTerritory(def);




			System.out.println(players.get(act.getOwner()-1).printableTerritories());
		} else {
			def.setNumOfUnits(def.getNumOfUnits() - defLostUnits);
			act.setNumOfUnits(act.getNumOfUnits() - attLostUnits);


		}

		// if (attack < defence) {
		// System.out.println("Defeat!");
		// attnewunits = attunits - attack;
		// act.setNumOfUnits(attnewunits);//added this here so it would be correct
		// } else if (attack > defence) {
		// System.out.println("Victory!");
		// defnewunits = defUnits - defence;
		// if(def.getNumOfUnits()-attrolls<1)
		// {
		// System.out.println("you gained a new territory");

		// players.get(def.getOwner()-1).removeTerritoryByName(def.getName());

		// def.setOwner(act.getOwner());
		// System.out.println("new onwer of def is "+def.getOwner());
		// players.get(act.getOwner()-1).addTerritory(def);

		// //-1 bc its in a list with index 0-numberofplayers
		// System.out.println(players.get(act.getOwner()-1).printableTerritories());

		// System.out.println("player "+act.getOwner()+" just took control of "+
		// def.getName());
		// act.setNumOfUnits(act.getNumOfUnits()-1);
		// def.setNumOfUnits(1);
		// newCard(players.get(act.getOwner()-1));

		// }
		// } else {
		// // what happens is there is a tie
		// System.out.println("Defeat!");
		// attnewunits = attunits - defence;
		// if(attackingUnits!=0)
		// {
		// act.setNumOfUnits(act.getNumOfUnits()-1);
		// }

		// }
		// return null;
		// }

		// /**
		// * gives one card to player who won an attack
		// * @param player
		// */
		// public void newCard(Player player){
		// System.out.println("player "+player.getId()+" got "+deck.get(0).printCard());
		// player.addCard(deck.get(0));
		// deck.remove(0);
		return "0";
	}

	/**
	 * 
	 * @param player pass in the current player
	 * @return A string saying how many more units they have and how much they have
	 *         now
	 */
	public String trade(Player player) {
		int temp = player.getArmiesCount();
		//
		int[] cardgroup = { 3, 5, 8, 10, 12, 15, 20, 25, 30, 35, 40, 45 };
		player.setArmiesCount(temp + cardgroup[cardSet]);
		String returnable = "you got " + cardgroup[cardSet] + " more  units now you have " + player.getArmiesCount()
				+ "in total";
		if (cardSet != 12) {
			cardSet++;
		}

		return returnable;
	}

	public int fortify(Territory from, Territory to, int numUnits) {
		int units = from.getNumOfUnits() - numUnits;
		from.setNumOfUnits(units);
		to.setNumOfUnits(numUnits);
		return 0; // zero for now
	}

	/**
	 * This method will return a list of cards shuffled
	 * 
	 * @param deck the card list to be shuffled
	 */
	public static void shuffleCards(List<Card> deck) {
		Collections.shuffle(deck);
	}
}
