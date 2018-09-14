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
    private String owner;
    private int numOfUnits;
    // private boolean canAttack;
    private List<Territory> neighboringTerritories=new ArrayList<>();
    
    // basic constructor 
    public Territory(String name,String continentName, String owner,int numOfUnits){
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
    	this.owner = "Owner";
    	this.numOfUnits = 0;
    	return;
    }
    
    //getters
    public String getName(){
        return this.name;
        
    }
    public String getcontinentName(){
        return this.continentName;
        
    }
    public String getOwner(){
        return this.owner;
    }
    public int getNumOfUnits(){
        return this.numOfUnits;
    }
    
    public List<Territory> getNeighboringTerritories()
    {   List<Territory> list=new ArrayList<>(neighboringTerritories);
        
        return list;
        
    }
    
    //setters
    
    public void setNeighboringTerritories( List<Territory> list)
    {
        
        
        for(int i=0;i<list.size();i++)
        {   
            neighboringTerritories.add(list.get(i));
            
        }
        
    }
    public void setOwner(String owner){
        this.owner=owner;
    }
    
    public void setNumOfUnits(int numOfUnits){
        this.numOfUnits=numOfUnits;
        // if(this.numOfUnits>=2){
        //     this.canAttack=true;
        // }
    }
    
    
    
    
}