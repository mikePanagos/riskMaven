package mojo.risk;

import java.util.List;
import java.util.ArrayList;


//
//check neighbors to see current players owns all of them
//
//

public class Territory{
    private String name;
    private String continentName;
    private int owner;
    private int numOfUnits;
    // private boolean canAttack;
    public List<Territory> neighboringTerritories=new ArrayList<>();
    
    // basic constructor 
    public Territory(String name,String continentName, int owner,int numOfUnits){
        this.name=name;
        this.continentName=continentName;
        this.owner=owner;
        this.numOfUnits=numOfUnits;
        // if(this.numOfUnits>2)
        // {
        //     this.canAttack=true;
        // }
    }
    
    /**
     * Default constructor used to instantiate an empty Territory. Used for demo/beta
     * purposes.
     * @return empty instance of a territory
     */
    /*
     * Using this constructor to fix errors in gui. Strictly used for testing.
     * - Oscar
     */
    public Territory ( ) {
    	this.name = "Territory";
    	this.continentName = "Continent";
    	this.numOfUnits = 0;
    	return;
    }
    
    /**
     * Constructor used to make deep copies of territories.
     * @param t Territory to use as base copy
     * @return new instance of Territory as an exact copy
     */
    public Territory (Territory t) {
    	this.name = t.getName();
    	this.continentName = t.getcontinentName();
    	this.neighboringTerritories = t.neighboringTerritories;
    	this.numOfUnits = t.getNumOfUnits();
    	this.owner = t.getOwner();
    	return;
    }
    
    //getters
    public String getName(){
        return this.name;
        
    }
    public String getcontinentName(){
        return this.continentName;
        
    }
    public int getOwner(){
        return this.owner;
    }
    public int getNumOfUnits(){
        return this.numOfUnits;
    }
    
    public String getNeighboringTerritories(){
    // {   List<Territory> list=new ArrayList<>(neighboringTerritories);
        
    //     return list;

        String terr="";
		for ( int i = 0; i < neighboringTerritories.size(); i++ ) {
			terr+=i+". "+ neighboringTerritories.get(i).getName() + " " ;
		}
		return terr;
        
    }
    /**
     * 
     * @param id id of player 
     * @return list of neighbors that the player owns
     */
    public String getprintableListOfOwnedNeighboringTerritories(int id){
        String terr="";
        int count=0;
		for ( int i = 0; i < neighboringTerritories.size(); i++ ) {
            if(neighboringTerritories.get(i).getOwner()==id)
            {terr+=count+". "+ neighboringTerritories.get(i).getName() + " " ;}
            count++;
		}
		return terr;
    }

     /**
      * 
      * @param id of player
      * @return the territory
      */
    public Territory getOwnedNeighboringTerritory(int id,int index){
        // String terr="";
        int count=0;
        int found=0;
		for ( int i = 0; i < neighboringTerritories.size(); i++ ) {
            if(neighboringTerritories.get(i).getOwner()==id)
            { 
                if(count==index)
                {
                    found=i;
                }
            }
            count++;
        }
        return neighboringTerritories.get(found);
    }
    /**
     *  give list of attackable terrs
     * @param id
     * @return
     */
    public String printableListOfAttackableNeighboringTerritories(int id){
        String terr="";
        int count=0;
		for ( int i = 0; i < neighboringTerritories.size(); i++ ) {
            if(neighboringTerritories.get(i).getOwner()!=id)
            {terr+=count+". "+ neighboringTerritories.get(i).getName() + " " ;}
            count++;
		}
		return terr;
        
    }
    /**
     * get Neighboring Territory that player wants to attack
     * @param id
     * @param index
     * @return
     */
    public Territory getAttackableNeighboringTerritory(int id,int index){
        int count=0;
        int found=0;
		for ( int i = 0; i < neighboringTerritories.size(); i++ ) {
            if(neighboringTerritories.get(i).getOwner()!=id)
            { 
                if(count==index)
                {
                    found=i;
                }
            }
            count++;
        }
        return neighboringTerritories.get(found);
        
    }
    //setters
    
    public void setNeighboringTerritories( List<Territory> list)
    {
        
        
        for(int i=0;i<list.size();i++)
        {   
            neighboringTerritories.add(list.get(i));
            
        }
        
    }
    public void setOwner(int owner){
        this.owner=owner;
    }
    
    public void setNumOfUnits(int numOfUnits){
        this.numOfUnits=numOfUnits;
        // if(this.numOfUnits>=2){
        //     this.canAttack=true;
        // }
    }
    
    
    
    
}