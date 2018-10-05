package mojo;

import java.util.ArrayList;
import java.util.List;

import mojo.risk.*;

public class Undo {
    Undo init = null;
    Setup s = Setup.getInstances();
    private List<Territory> tlist = s.getTerritories();
    private List<Player> pListPrev = new ArrayList<>();

    public  Undo() {
        System.out.println("here");

    }
    public void saveState(List<Player> p) {

        for (int i = 0; i < p.size(); i++) {
            pListPrev.add(new Player(p.get(i)));
        }
    }

    public List<Player> undo(List<Player> pl) {
        for (int i = 0; i < pl.size(); i++) {

            pl.get(i).setArmiesCount(pListPrev.get(i).getArmiesCount());
            pl.get(i).setTerritoryCount(pListPrev.get(i).getTerritoryCount());
            pl.get(i).setCardCount(pListPrev.get(i).getCardCount());
            pl.get(i).setTerritoryList(resetTerList(pListPrev.get(i)));
            // pl.get(i).setCarList()
        }
        return pListPrev;
    }

    /**
     * 
     * @param p
     * @return
     */
    private List<Territory> resetTerList(Player p) {
        List<Territory> newList = new ArrayList<>();
        List<Territory> changing = p.getTerritoryList();
        for (int i = 0; i < changing.size(); i++) {
            newList.add(findTerritory(changing.get(i).getName()));
            newList.get(i).setNumOfUnits(changing.get(i).getNumOfUnits());
            newList.get(i).setNumOfUnits(changing.get(i).getNumOfUnits());
        }
        for (int i = 0; i < changing.size(); i++) {
            
        }
        return newList;
    }

    /**
     * 
     * @param name
     * @return
     */
    private Territory findTerritory(String name) {
        int i = 0;
        for (i = 0; i < tlist.size(); i++) {

            if (tlist.get(i).getName().equals(name)) {
                break;
            }

        }
        return tlist.get(i);
    }
}