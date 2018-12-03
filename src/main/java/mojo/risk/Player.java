/**
* 
*/
package mojo.risk;

import mojo.notification.*;
import java.util.*;

/**
 * @author oscartovar
 *
 */
public class Player {
	private long id;
	private int armiesCount;
	private int cardCount;
	private int pointCount;
	private int territoryCount;
	private int prevTerritoryCount;
	private int continentCount;
	private int terrThatCanAttack;
	private boolean attackedAtLeastOnce = false;
	private boolean itsMyTurn = false;
	public boolean ready = false;
	public boolean receivedUnits = true;
	private String selectedMove = "menu";
	public boolean quit = false;
	private int credit=100;

    private List<Notification> notifications = new ArrayList<Notification>(); // Holds the notifications for the player
	private List<Dice> dice = new ArrayList<>();

	// change type to Territory and for the getters and setters
	private List<Territory> tList = new ArrayList<Territory>();
	private List<String> cList = new ArrayList<String>();
	private List<Card> cardList = new ArrayList<>();

    /**
     * Creates a player with everything set to default
     * @param id the Player ID to assign
     */
	public Player(long id) {
		this.id = id;
		setArmiesCount(0);
		setCardCount(0);
		setPointCount(0);
		setTerritoryCount(0);
		setContinentCount(0);
	}

    /**
     * This is the default constructor for the player class. It will set the army
     * and card count based off of the values provided. This allows the constructor
     * to be flexible. The main game engine should make sure to pass the correct
     * amount of units and cards to this constructor. All other values will be set
     * to zero.
     *
     * @param id     The player id. I.E. Player 1, Player 2 etc.
     * @param armies Amount of armies to start the player off with.
     * @param cards  Amount of cards to pass to the player.
     */
	public Player(long id, int armies, int cards) {
		this.id = id;
		setArmiesCount(armies);
		setCardCount(cards);
		setPointCount(0);
		setTerritoryCount(0);
		setContinentCount(0);
	}

    /**
     * Used to make a deep copy of a Player
     * @param p the player to create a copy from
     */
	public Player(Player p) {
		this.id = p.getId();
		this.armiesCount = p.getArmiesCount();
		this.cardCount = p.getCardCount();
		this.pointCount = p.getPointCount();
		this.prevTerritoryCount = p.getPrevTerritoryCount();
		this.continentCount = p.getContinentCount();
		this.territoryCount = p.getTerritoryCount();
		this.tList=p.getTerritoryListCopy();
		this.cardList=p.getCardList();
	}

	/**
	 * This constructor will initiate a Player class with the default values for all
	 * members.
	 *
	 */

	public Player() {
		this.id = 0;
		this.armiesCount = 0;
		this.cardCount = 0;
		this.pointCount = 0;
		this.territoryCount = 0;
		this.continentCount = 0;
		

	}
	public int getCredit(){
		return this.credit;
	}
	public void setCredit(int newCredit){
		this.credit=newCredit;

	}
	public List<Territory> getTerritoryList(){
	
		return tList;
	}

	public List<Territory> getTerritoryListCopy(){
		
		List<Territory> copy=new ArrayList<>();
		for (int i = 0; i < tList.size(); i++) {
			copy.add(new Territory(tList.get(i)));
		}


		return copy;
	}
	public void setTerritoryList(List<Territory> t){
		this.tList=t;

	}
	
	public List<Card> getCardList(){
		List<Card> copy=new ArrayList<>();
		for (int i = 0; i < cardList.size(); i++) {
			copy.add(new Card(cardList.get(i)));
		}


		return copy;
	}
	/**
	 * This is the getter for the id.
	 * 
	 * @return int player id
	 */
	public long getId() {
		return this.id;
	}

	/**
	 * Sets the amount of armies/units that the player has.
	 * 
	 * @param n The amount of armies to give the player.
	 */
	public void setArmiesCount(int n) {
		this.armiesCount = n;
		return;
	}

	/**
	 * @return Returns the amount of armies/units the player currently has.
	 */
	public int getArmiesCount() {
		return this.armiesCount;
	}

	/**
	 * Sets the amount of armies/units that the player has.
	 * 
	 * @param n the amount of cards
	 */
	public void setCardCount(int n) {
		this.cardCount = n;
		return;
	}

	/**
	 * This is the getter for the card count.
	 * 
	 * @return int current card count.
	 */
	public int getCardCount() {
		return this.cardCount;
	}

	/**
	 * This is the setter for the point count.
	 * 
	 * @param n The amount of points to set the player too.
	 */
	public void setPointCount(int n) {
		this.pointCount = n;
		return;
	}

	/**
	 * This method will set the point count for the player by adding the total
	 * amount of territories and continents owned.
	 */
	public void setPointCount() {
		this.pointCount = (this.territoryCount + this.continentCount);
	}

	/**
	 * The getter for the point count.
	 * 
	 * @return int the current amount of points the player has.
	 */
	public int getPointCount() {
		return this.pointCount;
	}

	/**
	 * The setter for the territory count.
	 * 
	 * @param n The amount of territories to set the count to.
	 */
	public void setTerritoryCount(int n) {
		this.territoryCount = n;
		return;
	}

	/**
	 * This the setter for the territory count. It will set the territory count
	 * based off the territory list size.
	 */
	public void setTerritoryCount() {
		this.territoryCount = tList.size();
	}

	/**
	 * This is the getter for the territory count.
	 * 
	 * @return int the current territory count for the player
	 */
	public int getTerritoryCount() {
		return this.territoryCount;
	}

	/**
	 * This is the setter for the continent count.
	 * 
	 * @param n the amount of continents to set the count towards
	 */
	public void setContinentCount(int n) {
		this.continentCount = n;
		return;
	}

	/**
	 * This is the setter for the continent count. It will set the continent count
	 * based of the continent list size.
	 */
	public void setContinentCount() {
		this.continentCount = cList.size();
	}

	/**
	 * This is the getter for the continent count.
	 * 
	 * @return int total amount of continents owned by player
	 */
	public int getContinentCount() {
		return this.continentCount;
	}

	/**
	 * This will add a territory to the list of territories owned by the player.
	 * 
	 * @param territory object of the territory claimed
	 */
	public void addTerritory(Territory territory) {
		this.tList.add(territory);
		setTerritoryCount(tList.size());
		

		return;
	}

	/**
	 * This will remove a territory owned by the player from the territory list.
	 * 
	 * @param territory object of the territory lost
	 */
	public void removeTerritory(Territory territory) {
		this.tList.remove(territory);
		setTerritoryCount(getTerritoryCount() - 1);
		// setPrevTerritoryCount(tList.size());
	}

	public int removeTerritoryByName(String name) {
		int result=0;
		for (int i = 0; i < this.tList.size(); i++) {
			if (name.equals(this.tList.get(i).getName())) {
				this.tList.remove(i);
				result=1;

			}
		}
		return result;
	}

    /**
     *
     * @return String with territory names
     */
	public String printableTerritories() {
		String terr = "";
		for (int i = 0; i < this.tList.size(); i++) {
			terr += i + ". " + this.tList.get(i).getName() + " army on " + this.tList.get(i).getNumOfUnits() + ". ";
		}
		return terr;
	}

	/**
	 * michael added this
	 * 
	 * @return String returns list of territories that can attack
	 */
	public String getPrintableListOfTerritoryThatCanAttack() {
		String terr = "";
		int count = 0;
		for (int i = 0; i < this.tList.size(); i++) {
			if (this.tList.get(i).getNumOfUnits() >= 2) {
				// if (this.tList.get(i).checkNeighbors(id)) {
					System.out.println(this.tList.get(i).getName());
					terr += count + ". " + this.tList.get(i).getName() + " ";
					count++;
				// }
			}
		}
		setAttackableTerritoryCount();
		return terr;

	}

	/**
	 * 
	 * @param index index of attacking territories
	 * @return pointer to territories
	 */
	public Territory getTerritoryThatCanAttack(int index) {
		int count = 0;
		int found = 0;
		for (int i = 0; i < this.tList.size(); i++) {
			if (this.tList.get(i).getNumOfUnits() >= 2) {

				if (count == index) {
					// System.out.println("looking at terr " + this.tList.get(i).getName());
					found = i;
					break;
				} else {
					count++;
				}
			}
		}
		
		return this.tList.get(found);

	}

	public int getAttackableTerritoryCount(){
		setAttackableTerritoryCount();
		// System.out.println("count is "+this.terrThatCanAttack);
		return this.terrThatCanAttack;

	}
	public void setAttackableTerritoryCount(){
		int count = 0;
		for (int i = 0; i < this.tList.size(); i++) {
			// System.out.println((this.tList.get(i).getNumOfUnits() >= 2	));
			if (this.tList.get(i).getNumOfUnits() >= 2) {
					count++;
			}
		}
		this.terrThatCanAttack=count;

	}

	// make return list
	public Territory getTerritory(int i) {
		return this.tList.get(i);
	}

	/**
	 * This will add a claimed continent to the players continent list.
	 * 
	 * @param continent name of the continent claimed
	 */
	public void addContinent(String continent) {
		this.cList.add(continent);
		// i added this just for testing can be taken out later if not wanted
		setContinentCount();
		return;
	}

	/**
	 * This will remove a continent lost from the players continent list.
	 * 
	 * @param continent name of the continent to be removed
	 */
	public void removeContinent(String continent) {
		cList.remove(continent);
		// i added this just for testing can be taken out later if not wanted
		setContinentCount();
	}

	/**
	 * This will print a list of all the continents owned by the player.
	 */
	public void getContinents() {
		for (int i = 0; i < this.cList.size(); i++) {
			System.out.println(this.cList.get(i) + " ");
		}
		return;
	}

	public List<String> getContinentList() {
	    return this.cList;
    }

	public void addCard(Card c) {
		this.cardList.add(c);
		setCardCount(getCardCount() + 1);

	}

	public void removeCard(Card c) {

		for (int i = 0; i < this.cardList.size(); i++) {
			if (c.getTerritoryName().equals(this.cardList.get(i).getTerritoryName()))

			{
				this.cardList.remove(c);
			}
		}

	}

	public String printCards(){
		String returning="";
		for(int i=0;i<cardList.size();i++){
			returning += "["+cardList.get(i).getTerritoryName()+" "+cardList.get(i).getType()+"]\n";
		}

		return returning;
	}

	public List<Card> getCards(){
		List<Card> trading=new ArrayList<>();

		for (int i = 0; i < 3; i++) {
			trading.add(this.cardList.get(i));
			
		}

		return trading;
	}

	public void rollDices(int amount) {

		if (!this.dice.isEmpty()) {
			clearDice();
		}
		// Random r=new Random();

		for (int i = 0; i < amount; i++) {
			// System.out.println("new dice for player "+this.getId());
			this.dice.add(new Dice());
		}
	}

	public List<Integer> getDice() {
		List<Integer> print = new ArrayList<>();

		for (int i = 0; i < this.dice.size(); i++) {
			print.add(this.dice.get(i).getVal());
		}
		return print;
	}

	private void clearDice() {
		this.dice.clear();
	}

	public void setPrevTerritoryCount(int oldCount) {
		this.prevTerritoryCount = oldCount;
	}

	public int getPrevTerritoryCount() {
		return this.prevTerritoryCount;
	}

	public boolean getAttackedAtLeastOnces(){
		return this.attackedAtLeastOnce;
	}
	public void setAttackedAtLeastOnces(boolean answer){
		this.attackedAtLeastOnce=answer;
	}
	/**
	 * This method will send a notification to the player. The Notification Center uses this method to notify
	 * a player.
	 * @param n the Notification to send the player
	 */
	public void sendNotification(Notification n) {
		notifications.add(n);
	}
	public boolean getItsMyTurn(){
		return this.itsMyTurn;
	}
	public void setItsMyTurn(boolean set){
		 this.itsMyTurn=set;
	}

	/**
	 * Get the players current selection. This is to keep track of what the player last selected.
	 * This could be for example to Attack, Fortify, etc
	 * @return The players last option.
	 */
	public String getSelectedMove() {
		return this.selectedMove;
	}

	/**
	 * Set the players selected move.
	 * @param selectedMove The move selected by the player
	 */
	public void setSelectedMove(String selectedMove) {
		this.selectedMove = selectedMove;
	}

    /**
     * This returns a more verbose breakdown of the players currently owned territories.
     * @return verbose summary of the player's territories
     */
	public String printTerritoriesVerbose() {
	    String summary = "";
	    for (Territory t : tList) {
	        summary += "Territory Name: " + t.getName() + "\n";
	        summary += "Number of units allocated: " + t.getNumOfUnits() + "\n";
        }

        return summary;
    }

    public String printOffensiveTerritoriesVerbose() {
	    String summary = "";
	    for (Territory t : tList) {
	    	if (t.getNumOfUnits() > 1) {
                summary += "\n"; // Used to format if the preceding value is null;
                summary += "Territory Name: " + t.getName() + "\n";
                summary += "Army Count: " + t.getNumOfUnits() + "\n";
                summary += "Enemy Territories:\n";
                summary += t.printEnemyTerritories();
            }
        }

        return summary;
    }

    public String printFortifyTerritories() {
	    String summary = "";
	    for (Territory t : tList) {
	        if (t.getNumOfUnits() > 1) {
	            summary += "Territory Name: " + t.getName() + "\n";
	            summary += "Units: " + t.getNumOfUnits() + "\n";
	            summary += "--------------------------------------\n";
	            summary += "Can fortify the following territories:\n";
                summary += "--------------------------------------\n";

                if (t.getSurroundingAllies().size() == 0) {
                    summary += "None \n";
                }
                else {
                    for (Territory ally : t.getSurroundingAllies()) {
                        summary += "Territory Name: " + ally.getName() + "\n";
                        summary += "Units: " + ally.getNumOfUnits() + "\n";
                    }
                }
            }
        }

        return summary;
    }

    public List<Notification> getNotifications() {
	    return this.notifications;
    }

    public String printNotifications() {
	    String message = "Notifications:\n";
	    for (Notification n : notifications) {
	        message += n.getMessage() + "\n";
        }
        notifications.clear();
	    return message;
    }
}
