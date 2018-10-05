package mojo.risk;

import java.util.List;
import java.util.ArrayList;

//
//check neighbors to see current players owns all of them
//
//

public class Territory {
    private String name;
    private String continentName;
    private int owner;
    private int numOfUnits;
    // private boolean canAttack;
    private List<Territory> neighboringTerritories = new ArrayList<>();


    // basic constructor
    public Territory(String name, String continentName, int owner, int numOfUnits) {
        this.name = name;
        this.continentName = continentName;
        this.owner = owner;
        this.numOfUnits = numOfUnits;
        // if(this.numOfUnits>2)
        // {
        // this.canAttack=true;
        // }
    }


    /**
    *
    * @param Territory this is to make a deep copy for undoing purposes
    * @return
    */
    public Territory(Territory t){
        this.name = t.getName();
        this.continentName = t.getContinentName();
        this.owner = t.getOwner();
        this.numOfUnits = t.getNumOfUnits();
        this.neighboringTerritories = t.getNeighboringTerritories();
    }


    /**
     * Default constructor used to instantiate an empty Territory. Used for
     * demo/beta purposes.
     * 
     * @return empty instance of a territory
     * Using this constructor to fix errors in gui. Strictly used for testing. -
     * Oscar
     */
    public Territory() {
        this.name = "Territory";
        this.continentName = "Continent";
        this.numOfUnits = 0;
        return;
    }
    /**
     * 
     * @return continent name
     */
    public String getContinentName(){
        return this.continentName;
    }
    private List<Territory> getNeighboringTerritories(){
        return this.neighboringTerritories;
    }

    // getters
    public String getName() {
        return this.name;

    }

    public String getcontinentName() {
        return this.continentName;

    }

    public int getOwner() {
        return this.owner;
    }

    public int getNumOfUnits() {
        return this.numOfUnits;
    }

    /**
     * 
     * @return a string of all territoier neighbors
     */
    public String getStrNeighboringTerritories() {
        String terr = "";
        for (int i = 0; i < this.neighboringTerritories.size(); i++) {
            terr += i + ". " + this.neighboringTerritories.get(i).getName() + " ";
        }
        return terr;

    }

    /**
     * 
     * @param id id of player
     * @return list of neighbors that the player owns
     */
    public String getprintableListOfOwnedNeighboringTerritories(int id) {
        String terr = "";
        int count = 0;
        for (int i = 0; i < this.neighboringTerritories.size(); i++) {
            if (this.neighboringTerritories.get(i).getOwner() == id) {
                terr += count + ". " + this.neighboringTerritories.get(i).getName() + " ";
            }
            count++;
        }
        return terr;
    }


    /**
     * checks whether the players with id owns all neighbor or if he can attacka  neighbor
     * @param id
     * @return
     */
    public boolean checkNeighbors(int id) {
        int count = 0;
        for (int i = 0; i < this.neighboringTerritories.size(); i++) {
            if (this.neighboringTerritories.get(i).getOwner() != id) {
                count++;
            }
        }
        if(count<=0){
        return false;
        }else{
            return true;
        }
    }

    /**
     * 
     * @param id of player
     * @return the territory
     */
    public Territory getOwnedNeighboringTerritory(int id, int index) {
        
        // String terr="";
        int count = 0;
        int found = 0;
        for (int i = 0; i < this.neighboringTerritories.size(); i++) {
            if (this.neighboringTerritories.get(i).getOwner() == id) {
                if (count == index) {
                    found = i;
                }
            }
            count++;
        }
        return this.neighboringTerritories.get(found);
    }

    /**
     * give list of attackable terrs
     * 
     * @param id
     * @return
     */
    public String printableListOfAttackableNeighboringTerritories(int id) {
        String terr = "";
        int count = 0;
        for (int i = 0; i < this.neighboringTerritories.size(); i++) {
            if (this.neighboringTerritories.get(i).getOwner() != id) {
                terr += count + ". " + this.neighboringTerritories.get(i).getName() + " ";
                count++;

            }
        }
        return terr;

    }

    // public int getCountOfAttackableNeighbors(int id){
    //     int count = 0;
    //     for (int i = 0; i < this.neighboringTerritories.size(); i++) {
    //         if (this.neighboringTerritories.get(i).getOwner() != id) {
    //             //  count + ". " + this.neighboringTerritories.get(i).getName() + " ";
    //             count++;

    //         }
    //     }
    //     return count;

    // }

    /**
     * get Neighboring Territory that player wants to attack
     * 
     * @param id
     * @param index
     * @return
     */
    public Territory getAttackableNeighboringTerritory(int id, int index) {
        int count = 0;
        int found = 0;
        for (int i = 0; i < this.neighboringTerritories.size(); i++) {
            if (this.neighboringTerritories.get(i).getOwner() != id) {
                if (count == index) {
                    found = i;
                }
            count++;

            }
        }
        return this.neighboringTerritories.get(found);

    }
    // setters

    public void setNeighboringTerritories(List<Territory> list) {
        this.neighboringTerritories = list;
    }

   
    public int getNumOfNeighborsNotOwned(){
        int count=0;
        for (int i = 0; i < neighboringTerritories.size(); i++) {
            if(this.neighboringTerritories.get(i).getOwner()!=getOwner()){
                count++;

            }            
        }
        return count;
    }
    public void setOwner(int owner) {
        this.owner = owner;
    }

    public void setNumOfUnits(int numOfUnits) {
        this.numOfUnits = numOfUnits;
        // if(this.numOfUnits>=2){
        // this.canAttack=true;
        // }
    }

}