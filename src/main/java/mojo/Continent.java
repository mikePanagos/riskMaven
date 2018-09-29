package mojo;
import mojo.risk.*;
import java.util.*;

/**
* @author Michael Panagos
*/
public class Continent{
    private String name;
    private List<Territory> list=new ArrayList<>();
    
    public Continent(String name, List<Territory> list)
    {
        this.list=list;
        this.name=name;
    }
    
    public List<Territory> getContinentListOfTerritories(){
        return this.list;
    }
    public String getContinentName(){
        return this.name;
    }
    //make a check for ownership 
    //if one person owns all then return true
}