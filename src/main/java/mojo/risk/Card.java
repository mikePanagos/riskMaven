/**
 * @author Oscar A. Tovar
 */
package mojo.risk;

public class Card {
	private String territoryName; // Name of the territory
	private String type; // The type that the card holds i.e. artillery, cavalry, infantry
	
	/**
	 * Default constructor for Card class.
	 */
	public Card ( ) {
		this.setTerritoryName("");
		this.setType("");
	}
	
	public Card (Card c) {
		this.territoryName = c.getTerritoryName();
		this.type = c.getType();
	}
	
	/**
	 * Constructor that sets the territory name and type.
	 * @param territoryName territory name that the card carries
	 * @param type the picture type that the card carries
	 */
	public Card ( String territoryName, String type ) {
		this.setTerritoryName(territoryName);
		this.setType(type);
	}
	
	/**
	 * Getter for the territory name on the card.
	 * @return the cards territory name
	 */
	public String getTerritoryName ( ) {
		return this.territoryName;
	}
	
	/**
	 * Setter for the territory name on the card.
	 * @param territoryName territory name to set on the card
	 */
	public void setTerritoryName ( String territoryName ) {
		this.territoryName = territoryName;
		return;
	}
	
	/**
	 * Getter for card unit type.
	 * @return the card unit type
	 */
	public String getType ( ) {
		return this.type;
	}
	
	/**
	 * Setter for the card unit type
	 * @param type the card unit type
	 */
	public void setType ( String type ) {
		this.type = type;
		return;
	}

	public String printCard(){
		String print=getType()+" "+getTerritoryName();
		return print;
	}
}
