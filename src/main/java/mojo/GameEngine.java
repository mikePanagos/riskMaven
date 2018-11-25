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
		Player attackingPlayer = null;
		Player defendingPlayer = null;

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
		long idOfAttPlayer = 0;
		long idOfDefPlayer = 0;

		for(Player player : players) {
		    if (player.getId() == act.getOwner()) {
                idOfAttPlayer = act.getOwner();
            }
            if (player.getId() == def.getOwner()) {
                idOfDefPlayer = def.getOwner();
            }
        }
//		long idOfAttPlayer = act.getOwner();
		Player attPlayer = s.getPlayerById(idOfAttPlayer);
//		long idOfDefPlayer = def.getOwner();
		Player defPlayer = s.getPlayerById(idOfDefPlayer);
		
		// Create Attack Notification
		Notification notification = new AttackNotification(idOfAttPlayer, idOfDefPlayer, def.getName());
		
		// Send Notification to Notification Center
		// TODO create publicly accessible notification center
		notificationCenter.setNotification(notification);
		notificationCenter.updatePlayer();

//		players.get(idOfAttPlayer - 1).rollDices(attackNumOfRolls);
//		players.get(idOfDefPlayer - 1).rollDices(defendNumOfRolls);
//		List<Integer> attDice = players.get(idOfAttPlayer - 1).getDice();
//		List<Integer> defDices = players.get(idOfDefPlayer - 1).getDice();

        attPlayer.rollDices(attackNumOfRolls);
        defPlayer.rollDices(defendNumOfRolls);

        List<Integer> attDice = attPlayer.getDice();
        List<Integer> defDice = defPlayer.getDice();

		Collections.sort(attDice);
		Collections.reverse(attDice);

		Collections.sort(defDice);
		Collections.reverse(defDice);


		int defLostUnits = 0;
		int attLostUnits = 0;
		int biggerSize = 0;

		if (attDice.size() > defDice.size()) {
			// System.out.println("more attacking dice");
			biggerSize = defDice.size();
		} else {
			biggerSize = attDice.size();
		}
		for (int i = 0; i < biggerSize; i++) {
			System.out.println("Attack rolled a " + attDice.get(i)+".        the defense rolled a " + defDice.get(i));

			if (attDice.get(i) > defDice.get(i)) {

				System.out.println("Attacking won defense will lose one unit");
				System.out.println(" ");
				System.out.println(" ");
				defLostUnits++;
			} else if (attDice.get(i) < defDice.get(i)) {
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
	 * @param p pass in the current player
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
	 */
	public void shuffleCards() {
		Collections.shuffle(deck);
	}

    /**
     * This method verifies if the move selected by the Player was a legitimate move. It will check if they have
     * the necessary resources/ownership to perform the move.
     * @param move the action that the player is intending to do
     * @param command the complete command given by the player
     * @return true if the command is valid; false if the command is invalid
     */
	public static boolean verifyCommand(String move, String[] command, Player player) {
	    boolean isValid = false;

	    switch (move) {
            case "attack":
                // Check if the owner has access to the attacking territory
                // We will create a null Territory reference. If still null after going through all the territories
                // that means the territory was invalid.
                Territory attackingTerritory = null;
                Territory defendingTerritory = null;

                for (Territory t : player.getTerritoryList()) {
                    // Check to see if the territory can attack
                    if (t.getName().toLowerCase().equals(command[1]) && t.getNumOfUnits() > 2) {
                        attackingTerritory = t;
                    }
                }

                // If the reference is still null that means we weren't able to find a territory with that name owned by
                // the current player. Automatically returns false
                if (attackingTerritory == null) {
                    isValid = false;
                    break;
                }

                // Check to see the defending Territory is valid for the chosen attacking territory.
                for (Territory t : attackingTerritory.getSurroundingEnemies()) {
                    if (t.getName().toLowerCase().equals(command[2])) {
                        defendingTerritory = t;
                    }
                }

                // If the defending territory reference is still null then the target selection was not valid
                // Returns false
                if (defendingTerritory == null) {
                    isValid = false;
                    break;
                }

                // If the attacking territory has less units than the ones described by the player
                // then this move was invalid.
                if (attackingTerritory.getNumOfUnits() < Integer.parseInt(command[3])) {
                    isValid = false;
                    break;
                }

                System.out.println("The attack command submitted was found to be valid.");
                isValid = true;
                break;
            case "fortify":

                //return true;
                break;
            case "trade":

                //return true;
                break;
            default:
                //return false;
                break;
        }
        return isValid;
    }
}
