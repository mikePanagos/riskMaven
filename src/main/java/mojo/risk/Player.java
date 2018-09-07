/**
 * 
 */
package risk;

import java.util.*;

/**
 * @author oscartovar
 *
 */
public class Player {
	private int id;
	private int armiesCount;
	private int cardCount;
	private int pointCount;
	private int territoryCount;
	private int continentCount;
	private List<String> tList = new ArrayList<String>();
	private List<String> cList = new ArrayList<String>();
	
	
	/**
	 * This is the default constructor for the player class. It will set the army and card count based off of the
	 * values provided. This allows the constructor to be flexible. The main game engine should make sure to pass
	 * the correct amount of units and cards to this constructor. All other values will be set to zero.
	 * @param id The player id. I.E. Player 1, Player 2 etc.
	 * @param armies Amount of armies to start the player off with.
	 * @param cards Amount of cards to pass to the player.
	 * @return new instance of the Player class.
	 */
	public Player ( int id, int armies, int cards) {
		this.id = id;
		setArmiesCount( armies );
		setCardCount( cards );
		setPointCount(0);
		setTerritoryCount(0);
		setContinentCount(0);
	}
	
	/**
	 * This is the getter for the id.
	 * @return player id
	 */
	public int getId ( ) {
		return this.id;
	}
	
	/**
	 * Sets the amount of armies/units that the player has.
	 * @param n
	 */
	public void setArmiesCount ( int n ) {
		this.armiesCount = n;
		return;
	}
	
	
	/**
	 * @return Returns the amount of armies/units the player currently has.
	 */
	public int getArmiesCount ( ) {
		return this.armiesCount;
	}
	
	
	/**
	 * Sets the amount of armies/units that the player has.
	 * @param n
	 */
	public void setCardCount ( int n) {
		this.cardCount = n;
		return;
	}
	
	
	/**
	 * This is the getter for the card count.
	 * @return current card count.
	 */
	public int getCardCount ( ) {
		return this.cardCount;
	}
	
	
	/**
	 * This is the setter for the point count.
	 * @param n The amount of points to set the player too.
	 */
	public void setPointCount ( int n ) {
		this.pointCount = n;
		return;
	}
	
	
	/**
	 * This method will set the point count for the player by adding the total amount of territories and continents owned.
	 */
	public void setPointCount ( ) {
		this.pointCount = ( this.territoryCount + this.continentCount );
	}
	
	
	/**
	 * The getter for the point count.
	 * @return the current amount of points the player has.
	 */
	public int getPointCount ( ) {
		return this.pointCount;
	}
	
	
	/**
	 * The setter for the territory count.
	 * @param n The amount of territories to set the count to.
	 */
	public void setTerritoryCount ( int n ) {
		this.territoryCount = n;
		return;
	}
	
	/**
	 * This the setter for the territory count. It will set the territory count based off the territory list size.
	 */
	public void setTerritoryCount ( ) {
		this.territoryCount = tList.size();
	}
	
	/**
	 * This is the getter for the territory count.
	 * @return the current territory count for the player
	 */
	public int getTerritoryCount ( ) {
		return this.territoryCount;
	}
	
	
	/**
	 * This is the setter for the continent count.
	 * @param n the amount of continents to set the count towards
	 */
	public void setContinentCount ( int n ) {
		this.continentCount = n;
		return;
	}
	
	/**
	 * This is the setter for the continent count. It will set the continent count based of the continent list size.
	 */
	public void setContinentCount ( ) {
		this.continentCount = cList.size();
	}
	
	
	/**
	 * This is the getter for the continent count.
	 * @return total amount of continents owned by player
	 */
	public int getContinentCount ( ) {
		return this.continentCount;
	}
	
	
	/**
	 * This will add a territory to the list of territories owned by the player.
	 * @param territory name of the territory claimed
	 */
	public void addTerritory ( String territory ) {
		tList.add( territory );
		return;
	}
	
	
	/**
	 * This will remove a territory owned by the player from the territory list.
	 * @param territory name of the territory lost
	 */
	public void removeTerritory ( String territory ) {
		tList.remove( territory );
		return;
	}
	
	
	/**
	 * This will print a list of the territories owned by the player.
	 */
	public void getTerritories ( ) {
		for ( int i = 0; i < tList.size(); i++ ) {
			System.out.println( tList.get(i) + " " );
		}
		return;
	}
	
	
	/**
	 * This will add a claimed continent to the players continent list.
	 * @param continent name of the continent claimed
	 */
	public void addContinent ( String continent ) {
		cList.add( continent );
		return;
	}
	
	
	/**
	 * This will remove a continent lost from the players continent list.
	 * @param continent name of the continent to be removed
	 */
	public void removeContinent ( String continent ) {
		cList.remove( continent );
	}
	
	
	/**
	 * This will print a list of all the continents owned by the player.
	 */
	public void getContinents ( ) {
		for ( int i = 0; i < cList.size(); i++ ) {
			System.out.println( cList.get(i) + " " );
		}
		return;
	}
	
	
}
