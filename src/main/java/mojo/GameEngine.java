package mojo;
import java.util.*;

import mojo.risk.*;

public class GameEngine {
	// Territory territory;
	// Player player;
	
	public int giveunits(Territory terr, int addingUnits) {
		int units = terr.getNumOfUnits();
		units = units + addingUnits;
		terr.setNumOfUnits(units);
		return terr.getNumOfUnits();
	}
	
	public String attack(Territory act, Territory def) {
		int[] dice = new int[5];
		int attnewunits=0;
		int defnewunits=0;
		int attunits = act.getNumOfUnits();
		int defunits = def.getNumOfUnits();
		int attrolls=0;
		int defrolls=0;
		int atttotal = 0;
		int deftotal = 0;
		for (int i = 0; i <= dice.length; i++) {
			dice[i] = diceroll(1);
		}
		if (attunits > 3) {
			attrolls = 3;
		} else if (attunits == 2) {
			attrolls = 2;
		} else {
			attrolls = 1;
		}
		if (defunits > 2) {
			defrolls = 2;
		} else {
			defrolls = 1;
		}
		for (int j = 0; j <= dice.length - defrolls; j++) {
			dice[j] = atttotal;
		}
		for (int d = attrolls + 1; d <= dice.length; d++) {
			dice[d] = deftotal;
		}
		int attack = atttotal / 3;
		int defence = deftotal / 3;
		if (attack < defence) {
			System.out.println("Defeat!");
			attnewunits = attunits - attack;
		} else if (attack > defence) {
			System.out.println("Victory!");
			defnewunits = defunits - defence;
		} else {
			System.out.println("Defeat!");
			attnewunits = attunits - defence;
		}
		act.setNumOfUnits(attnewunits);
		def.setNumOfUnits(defnewunits);
		if (defnewunits < 1) {
			def.setOwner(act.getOwner());
			def.setNumOfUnits(1);
			act.setNumOfUnits(attnewunits - 1);
		} else if (attnewunits < 2) {
			System.out.println("Attacking territory only has 1 army");
		} else {
			act.setNumOfUnits(attnewunits);
			def.setNumOfUnits(defnewunits);
		}
		return null;
	}
	
	public int diceroll(int temp) {
		Random rand = new Random();
		int x = rand.nextInt(temp); // return random int
		return x;
	}
	
	/**
	* 
	* @param cardset the amount of trade that has already happen and the according amont of units given for that 
	* @param player pass in the current player 
	* @return A string saying how many more units they have and how much they have now
	*/
	public String trade(int cardset, Player player){
		int temp = player.getArmiesCount();
		//
		int[] cardgroup = {3, 5, 8, 10, 12, 15, 20, 25, 30, 35, 40, 45};
		player.setArmiesCount(temp+cardgroup[cardset]);
		return "you got "+cardgroup[cardset]+" more  units now you have "+player.getArmiesCount()+"in total";
	}
	
	public int fortify(Territory from, Territory to, int numUnits) {
		int units = from.getNumOfUnits() - numUnits;
		from.setNumOfUnits(units);
		to.setNumOfUnits(numUnits);
		return 0; //zero for now
	}


	/**
     * 
     * @param player current player options for attacking 
     * @return
     */
    public List<Territory> getAttackingTerritories(Player player){
		//    String namesOFTerritories= player.getTerritories();
			// List<Territory> terr=  player.getTerritories();
			List<Territory> ableToAttack = new ArrayList<>();

	
	
			// for(int i = 0; i<terr.size();i++){
	
			// 	//checkNeighbors will return true if it can attack flase if the current player owns all neighbors
			// 	if(terr.get(i).getNumOfUnits()>=2&&terr.get(i).checkNeighbors())
			// 	{
			// 		ableToAttack.add(terr.get(i));
			// 	}
			// }
	
			return ableToAttack;
	}
    
    
    /**
     * This method will return a list of cards shuffled
     * @param deck the card list to be shuffled
     */
    public static void shuffleCards(List<Card> deck) {
    	Collections.shuffle(deck);
    }
}
