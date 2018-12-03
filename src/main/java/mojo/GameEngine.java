package mojo;

import mojo.notification.*;
import java.util.*;
import mojo.risk.*;
import mojo.twitter.TwitterClient;
import mojo.undo.*;

public class GameEngine {
	private static GameEngine init = new GameEngine();
	Setup s = Setup.getInstances();
	private int cardSet = 0;
	private List<Card> deck = s.getDeck();
	private NotificationCenter notificationCenter = new NotificationCenter();
	private TwitterClient twitterClient = new TwitterClient();
    private GameLogger log = new GameLogger();
    Undo un=Undo.init();

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
        un.saveState(s.getPlayers());
		String messageReturnable="";
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
                System.out.println("found act "+player.getId());

                idOfAttPlayer = act.getOwner();
            }
            if (player.getId() == def.getOwner()) {
                System.out.println("found def "+player.getId());
            

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
			// messageReturnable+="more attacking dice");
			biggerSize = defDice.size();
		} else {
			biggerSize = attDice.size();
		}
		for (int i = 0; i < biggerSize; i++) {
            // Formatting the string to be more descriptive.
            // It will now have both players ids.
            messageReturnable += "Player #" + act.getOwner() + " attacked and rolled a " + attDice.get(i) + "\n";
            messageReturnable += "Player #" + def.getOwner() + " fought back and rolled a " + defDice.get(i) + "\n";

//			messageReturnable+="Attacker rolled a " + attDice.get(i)+".        the defense rolled a " + defDice.get(i)+"\n";

			if (attDice.get(i) > defDice.get(i)) {
                messageReturnable += "Attacking Player #" + act.getOwner() + " won!\n";
                messageReturnable += "Defending Player #" + def.getOwner() + " lost and will lose a unit.\n";
//				messageReturnable+="Attacker won defense will lose one unit\n\n";

				defLostUnits++;
			} else if (attDice.get(i) < defDice.get(i)) {
			    messageReturnable += "Defending Player #" + def.getOwner() + " won!\n";
			    messageReturnable += "Attacking Player #" + act.getOwner() + " lost and will lose a unit.\n";
//				messageReturnable+=" Defense won attacking will lose one unit\n\n";


				attLostUnits++;
			} else {
			    messageReturnable += "Tie. Attacking Player #" + act.getOwner() + " will lose a unit.\n";
//				messageReturnable+=" tie so Attacker will lose one unit\n\n";


				attLostUnits++;
			}
		}

		if (def.getNumOfUnits() - defLostUnits <= 0) {
			messageReturnable+="Player # " + def.getOwner() + " lost " + def.getName() + ".\n ";
			messageReturnable += "It now belongs to Player #"
					+ act.getOwner() + " and has " + (attackingUnits - attLostUnits) + " units on it\n\n";


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

		log.updateLog(messageReturnable);

		twitterClient.setTweet(messageReturnable);
		twitterClient.postTweet();
		return messageReturnable;
	}

	/**
	 *
	 * @param p pass in the current player
	 * @return A string saying how many more units they have and how much they have
	 *         now
	 */
	public String trade(Player p) {
        un.saveState(s.getPlayers());

		int temp = p.getArmiesCount();
		//
		List<Card> pCards=p.getCards();

		for(int i = 0; i<pCards.size();i++)
		{
			// messageReturnable+=pCards.get(i).getTerritoryName()+" "+pCards.get(i).getType());
			deck.add(pCards.get(i));
			p.removeCard(pCards.get(i));
		}




		int[] cardgroup = { 3, 5, 8, 10, 12, 15, 20, 25, 30, 35, 40, 45 };
		p.setArmiesCount(temp + cardgroup[cardSet]);
		String returnable = "You got " + cardgroup[cardSet] + " more units. You now have " + p.getArmiesCount()
				+ " in total.\n";
		if (cardSet != 12) {
			cardSet++;
		}
        log.updateLog(returnable);
		twitterClient.setTweet(returnable);
		twitterClient.postTweet();
		return returnable;
	}

	public String fortify(Territory from, Territory to, int numUnits) {
        un.saveState(s.getPlayers());

		int units = from.getNumOfUnits() - numUnits;
		from.setNumOfUnits(units);
		to.setNumOfUnits(to.getNumOfUnits()+numUnits);
		String message = "Fortifying Action:\n";
		message += "Now "+to.getName()+" has "+to.getNumOfUnits()+" and "+from.getName()+" has "+from.getNumOfUnits();
		log.updateLog(message);
		twitterClient.setTweet(message);
		twitterClient.postTweet();
		return message;
	}


	public String handOutCard(Player p){
		String returnable="Player "+p.getId()+" is getting card "+deck.get(0).getTerritoryName()+" "+deck.get(0).getType();
		p.addCard(deck.get(0));
		deck.remove(0);
		log.updateLog(returnable);
        twitterClient.setTweet(returnable);
        twitterClient.postTweet();
		return returnable;
	}

	/**
	 * This method will return a list of cards shuffled
	 */
	public void shuffleCards() {
	    log.updateLog("Cards have been shuffled.");
	    // No need to tweet this.
		Collections.shuffle(deck);
	}

    /**
     * This method verifies if the move selected by the Player was a legitimate move. It will check if they have
     * the necessary resources/ownership to perform the move.
     * @param move the action that the player is intending to do
     * @param command the complete command given by the player
     * @param player the player who is issuing the command
     * @return true if the command is valid; false if the command is invalid
     */
	public static boolean verifyCommand(String move, String[] command, Player player) {
		boolean isValid;
//		System.out.println("move is "+move);
		// System.out.println("commands are"+command[0]++++);

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
                /*
                    Format of command: fortify TerrA TerrB Units
                    Units - The amount of units to fortify with.
                    We're going to try and fortify a territory in the following fashion.
                    TerrA -> sends units -> TerrB
                    1. TerrA and TerrB must not be the same
                    2. TerrA and TerrB must both be owned by the player
                    3. TerrA must have more than one army before AND after the fortification
                 */
                Territory terrA = null;
                Territory terrB = null;

                if (command[1].equals(command[2])) {
                    isValid = false;
                    break;
                }

                for (Territory t : player.getTerritoryList()) {
                    if (t.getName().toLowerCase().equals(command[1])) {
                        terrA = t;
                    }
                    if (t.getName().toLowerCase().equals(command[2])) {
                        terrB = t;
                    }
                }

                if (terrA == null || terrB == null) {

                    isValid = false;
                    break;
                }

                if (terrA.getNumOfUnits() <= 1 || (terrA.getNumOfUnits() - Integer.parseInt(command[3])) < 1) {

                    isValid = false;
                    break;
                }

                System.out.println("The trade command was found to be valid.");
                isValid = true;
                break;
            case "trade":

                // This will currently always return true but game logic needs to be added here as well.
                isValid = true;
                break;
            case "distribute":
                /*
                    1. We need to check to see if the player has enough units to move there.
                    2. We need to check to see if the player owns the territory. Prevents bugs later :)
                 */

                int unitsRequested = Integer.parseInt(command[1]); // Store the units the player requested to place
                Territory territory = null; //Store the territory reference if found here.

                // Check to see if this number is greater than the armies they have on hand.
                if (unitsRequested > player.getArmiesCount()) {
                    isValid = false;
                    break;
                }

                // Search for the territory in the players territory list.
                for (Territory t : player.getTerritoryList()) {
                    if (t.getName().toLowerCase().equals(command[2])) { // All commands lowercase must lowercase name too.
                        territory = t;
                    }
                }

                // If the territory reference is not null then it was valid
                // returns true
                if (territory != null) {
                    System.out.println("The distribute command was found to be valid");
                    isValid = true;
                    break;
                }
            default:
                isValid = true;
                break;
        }

        return isValid;
    }

    public void surrender(Player player) throws Exception {
	    // Update log and Twitter
	    String update = "Player #" + player.getId() + " has quit the game.";
	    log.updateLog(update);
	    twitterClient.setTweet(update);
	    twitterClient.postTweet();

		for (Territory t : player.getTerritoryList()) {
			t.setOwner(0); // Reset the territory to the default: 0
			t.setNumOfUnits(0); // Reset the unit count back to 0
		}

	}

	public boolean initialPhase(Player player) {
//	    if (player.getNotifications().size() > 0) {
////            if (player.getNotifications().size() > 0) {
////                // Read notifications one by one to the player.
////                String returnMess = "Here is the breakdown of what happened outside of your turn.\n";
////                for (int i = 0; i < player.getNotifications().size(); i++) {
////                    returnMess += "Notification " + (i + 1) + ":\n";
////                    returnMess += player.getNotifications().get(i).getMessage() + "\n";
////                }
////                player.getNotifications().clear(); // Clear all notifications once they have been read to the player
////                returnMess += "-------------------\n";
////                // Notify the player with the breakdown of attacks.
////                RiskyBot.notifyPlayer(player.getId(), returnMess);
////            }
//            return true;
//        }
        // Give the player new units
        if (player.receivedUnits == false) {
            unitDistribution(player);
            // Let the game know that they player
            // has received their new units
            player.receivedUnits = true;
        }

        if (player.getArmiesCount() > 0) {
            return true;
        } else {
            return false;
        }

    }

    public void endingPhase(Player player) {
        if (player.getAttackedAtLeastOnces()) {
            // Hand out card.
            player.addCard(deck.get(0));
            // Rotate the deck so the next player
            // gets something different
            Collections.rotate(deck, 1);
            // Set it back to false so that it does
            // not carry over each turn.
            player.setAttackedAtLeastOnces(false);
        }
        player.receivedUnits = false;
        String update = "Player #" + player.getId() + " has ended their turn.";
        log.updateLog(update);
        twitterClient.setTweet(update);
        twitterClient.postTweet();
        // Need to check if the player owns a continent here.
    }

    public void unitDistribution(Player player) {
        un.saveState(s.getPlayers());
	    Integer units = player.getTerritoryCount() / 3;

	    // Minimum new units is three.
	    if (units < 3)
	        units = 3;

	    // An additional amount of units will be given
        // for each Continent owned. We will hold the
        // player's continents and distribute based off
        // the Continent's value.
        
        List<String> playerContinents = player.getContinentList();

	    if (playerContinents.contains("Asia")) {
	        units += 7;
        }

        if (playerContinents.contains("North America")) {
            units += 5;
        }

        if (playerContinents.contains("Europe")) {
            units += 5;
        }

        if (playerContinents.contains("Africa")) {
            units += 3;
        }

        if (playerContinents.contains("South America")) {
            units += 2;
        }

        if (playerContinents.contains("Australia")) {
            units += 2;
        }

	    // Distribute units.
        String update = "Player #" + player.getId() + "received " + units + " units at turn start.";
        log.updateLog(update);
        twitterClient.setTweet(update);
        twitterClient.postTweet();
        player.setArmiesCount(units);
    }

    public void allocateUnits(String[] command, Player player) {
	    String territory = command[2];
	    Integer units = Integer.parseInt(command[1]);

	    for (Territory t : player.getTerritoryList()) {
	        if (t.getName().toLowerCase().equals(territory)) {
	            t.setNumOfUnits(t.getNumOfUnits() + units); // Add units to this territory
	            player.setArmiesCount(player.getArmiesCount() - units); // Remove them from player
                break;
            }
        }

        String update = "Player #" + player.getId() + " allocated " + units + " to " + territory + ".";
	    twitterClient.setTweet(update);
	    twitterClient.postTweet();
        log.updateLog(update);
    }

    /*
    TODO: implement a method checking if player owns entire continent
     */
}
