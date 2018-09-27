package mojo;
import java.util.*;

import mojo.risk.*;

public class GameEngine {
	// Territory territory;
	// Player player;
	Setup s =new Setup();
	
	public void GameEngine () {
		System.out.println("Game Engine Initiated.");
	}
	
	public int giveunits(Territory terr, int addingUnits) {
		int units = terr.getNumOfUnits();
		units = units + addingUnits;
		terr.setNumOfUnits(units);
		return terr.getNumOfUnits();
	}
	
	public String attack(Territory act, Territory def,int attachingUnits) {
		int[] dice = new int[5];
		int attnewunits=0;
		int defnewunits=0;
		int attunits = attachingUnits;
		int defunits = def.getNumOfUnits();
		int attrolls=0;
		int defrolls=0;
		int atttotal = 0;
		int deftotal = 0;
		for (int i = 0; i < 5; i++) {
			dice[i] = diceroll();
			System.out.println(dice[i]);
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

		// this was all wrong 
		// for (int j = 0; j <5 - defrolls; j++) {
		// 	dice[j] = atttotal;
			
		// }
		// for (int d = attrolls + 1; d < 5; d++) {
		// 	dice[d] = deftotal;
		// }
		for (int j = 0; j <5 - defrolls; j++) {
			  atttotal+=dice[j];
			
		}
		for (int d = attrolls + 1; d < 5; d++) {
			deftotal+=dice[d];
		}
		int attack = atttotal / 3;
		int defence = deftotal / 3;
		if (attack < defence) {
			System.out.println("Defeat!");
			attnewunits = attunits - attack;
			act.setNumOfUnits(attnewunits);//added this here so it would be correct
		} else if (attack > defence) {
			System.out.println("Victory!");
			defnewunits = defunits - defence;
			if(def.getNumOfUnits()-attrolls<1)
			{
				System.out.println("you gained a new territory");
				List<Player> players=s.getPlayers();
				players.get(def.getOwner()-1).removeTerritoryByName(def.getName());

				def.setOwner(act.getOwner());
				System.out.println("new onwer of def is "+def.getOwner());
				players.get(act.getOwner()-1).addTerritory(def);
				System.out.println(players.get(act.getOwner()-1).printableTerritories());

				System.out.println("player "+act.getOwner()+" just took control of "+ def.getName());
				act.setNumOfUnits(act.getNumOfUnits()-1);
				def.setNumOfUnits(1);
				// need to figure out what to do here 
				// we need to take out the territory form the def list and add it to the player attacks list but we do not have them here

			}
		} else {
			// what happens is there is a tie
			System.out.println("Defeat!");
			attnewunits = attunits - defence;
			if(attachingUnits!=0)
			{
				act.setNumOfUnits(attnewunits);
			}
			
		}

		// this was wrong too
		// act.setNumOfUnits(attnewunits);
		// def.setNumOfUnits(defnewunits);
		// if (defnewunits < 1) {
		// 	def.setOwner(act.getOwner());
		// 	def.setNumOfUnits(1);
		// 	act.setNumOfUnits(attnewunits - 1);
		// } else if (attnewunits < 2) {
		// 	System.out.println("Attacking territory only has 1 army");
		// } else {
		// 	act.setNumOfUnits(attnewunits);
		// 	def.setNumOfUnits(defnewunits);
		// }


		return null;
	}
	
	public int diceroll() {
		Random rand = new Random();
		int x = rand.nextInt(6); // return random int
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
     * This method will return a list of cards shuffled
     * @param deck the card list to be shuffled
     */
    public static void shuffleCards(List<Card> deck) {
    	Collections.shuffle(deck);
    }
}
