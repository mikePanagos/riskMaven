package mojo.risk;

import java.util.List;
import java.util.ArrayList;

public class Territory{
    private String name;
    private String continentName;
    private String owner;
    private int numOfUnits;
    private List<Territory> neighboringTerritories=new ArrayList<>();;
    
    // baisc constructor 
    public Territory(String name,String continentName, String owner,int numOfUnits){
        this.name=name;
        this.continentName=continentName;
        this.owner=owner;
        this.numOfUnits=numOfUnits;
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
    }
    
    
    
    
}