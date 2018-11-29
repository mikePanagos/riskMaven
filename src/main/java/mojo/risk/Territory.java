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
    private long owner;
    private int numOfUnits;
    // private boolean canAttack;
    private List<Territory> neighboringTerritories = new ArrayList<>();


    // basic constructor
    public Territory(String name, String continentName, long owner, int numOfUnits) {
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
    * @param t this is the territory used to make a deep copy for undoing purposes
    * 
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
     */
    public Territory() {
        this.name = "Territory";
        this.continentName = "Continent";
        this.numOfUnits = 0;
        return;
    }
    /**
     * Getter for continent name
     * @return String continent name
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

    public long getOwner() {
        return this.owner;
    }

    public int getNumOfUnits() {
        return this.numOfUnits;
    }

    /**
     * 
     * @return String a string of all the territory's neighbors
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
     * @param id id of player who you would like to select
     * @return String list of neighbors that the player owns
     */
    public String getprintableListOfOwnedNeighboringTerritories(long id) {
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
     * checks whether the players with id owns all neighbors or if he can attack a neighbor
     * @param id the id of the current territory owner
     * @return boolean true if all neighbors owned, false otherwise
     */
    public boolean checkNeighbors(long id) {
        int count = 0;
        for (int i = 0; i < this.neighboringTerritories.size(); i++) {
            if (this.neighboringTerritories.get(i).getOwner() != id) {
                count++;
            }
        }
        if(count<0){
        return false;
        }else{
            return true;
        }
    }

    /**
     * 
     * @param id of player
     * @param index index of the current territory
     * @return Territory the territory that is neighboring
     */
    public Territory getOwnedNeighboringTerritory(long id, int index) {
        
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
     * Gives a string of valid target territories
     * 
     * @param id the id of the player who is querying
     * @return String string containing the territories that can be attacked
     */
    public String printableListOfAttackableNeighboringTerritories(long id) {
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
     * @param id the id of the player who is querying
     * @param index the index of the territory we are looking for
     * @return Territory returns a territory that the current player can attack
     */
    public Territory getAttackableNeighboringTerritory(long id, int index) {
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
    public void setOwner(long owner) {
        this.owner = owner;
    }

    public void setNumOfUnits(int numOfUnits) {
        this.numOfUnits = numOfUnits;
        // if(this.numOfUnits>=2){
        // this.canAttack=true;
        // }
    }

    /**
     * This method will return all the options which this territory can attack.
     * @return the formatted string containing all the options from which this
     */
    public String printEnemyTerritories() {
        // Create a new ArrayList to store all valid options for this territory to attack.
        List<Territory> options = new ArrayList<Territory>();
        // String to return with the summary
        String summary = "";

        // Add in only the territories that are not owned by the same owner.
        for (Territory t : neighboringTerritories) {
            if (t.getOwner() != getOwner()) {
                options.add(t);
            }
        }

        // Format the output so that it's human readable.
        for (Territory t : options) {
            summary += "Target Territory Name: " + t.getName() + "\n";
            summary += "Armies Count: " + t.getNumOfUnits() + "\n";
            summary += "Owned by: " + t.getOwner() + "\n";
        }

        return summary;
    }

    /*
        We may consider splitting the neighboring territories list into two lists. One with ally territories,
        and the other with enemy territories.
     */

    /**
     * Finds what the current surrounding enemy territories are.
     * @return an ArrayList containing all the surrounding enemy territories
     */
    public List<Territory> getSurroundingEnemies() {
        List<Territory> enemies = new ArrayList<Territory>();

        for (Territory t : neighboringTerritories) {
            if (t.getOwner() != owner) {
                enemies.add(t);
            }
        }

        return enemies;
    }

    /**
     * Finds what the current surrounding ally territories are.
     * @return an ArrayList containing all the surrounding ally territories.
     */
    public List<Territory> getSurroundingAllies() {
        List<Territory> allies = new ArrayList<Territory>();

        for (Territory t : neighboringTerritories) {
            if (t.getOwner() == owner) {
                allies.add(t);
            }
        }

        return allies;
    }

}