package mojo.undo;

import java.util.ArrayList;
import java.util.List;

import mojo.risk.*;
import mojo.*;

public class Undo {
    static  Undo init = null;
    private int count = 1;
    Setup s = Setup.getInstances();
    private List<Territory> tlist = s.getTerritories();
    private List<Player> pListPrev = new ArrayList<>();
    private List<StateOfGame> stateList = new ArrayList<>();

    public static Undo init() {
        if (init == null) {
            init = new Undo();
            return init;
        } else {
            return init;
        }
    }

    /**
     * 
     * @param i this is strictly for testting 
     * @return this returns a new init everytime needed for multiple test in same file
     */
    public static Undo init(int i) {

            init = new Undo();
            return init;
     
    }

    private Undo() {
        // System.out.println("here");

    }

    /**
     * 
     * @param p list of players to save there state this will make an obj that
     *          stores the turn number and list of players at current time
     * 
     */
    public void saveState(List<Player> p) {
        List<Player> save = new ArrayList<>();
        for (int i = 0; i < p.size(); i++) {
            save.add(new Player(p.get(i)));
        }
        // System.out.println(save.size()+" prev player list size is ");
        stateList.add(new StateOfGame(count, save));
        count++;
    }

    /**
     * 
     * @param pl   player list that need to be reverted
     * @param turn turn to revert to
     * @return true or false baside on success of function
     */
    public boolean undo(List<Player> pl, int turn) {
        System.out.println(turn + " turn ");
        // System.out.println(stateList.size()+" how many states there are ");

        for (int i = 0; i < stateList.size(); i++) {
            if (stateList.get(i).getTurn() == stateList.size()) {
                // System.out.println("found the state");
                pListPrev = stateList.get(i).getPlayerListPrev();
                // System.out.println(pListPrev.size()+" size of player list of past");
            }
        }

        // System.out.println(stateList);
        if (pListPrev.size() > 0) {
            for (int i = 0; i < pl.size(); i++) {

                pl.get(i).setArmiesCount(pListPrev.get(i).getArmiesCount());
                pl.get(i).setTerritoryCount(pListPrev.get(i).getTerritoryCount());
                pl.get(i).setCardCount(pListPrev.get(i).getCardCount());
                pl.get(i).setTerritoryList(resetTerList(pListPrev.get(i)));
                // pl.get(i).setCarList()
            }
            return true;
        }else{
            return false;
        }
    }

    /**
     * resets the ter list for each player.
     * 
     * @param p
     * @return
     */
    private List<Territory> resetTerList(Player p) {
        List<Territory> newList = new ArrayList<>();
        List<Territory> changing = p.getTerritoryListCopy();
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
     * finds a terr by name and returns that terr
     * 
     * @param name of territory 
     * @return the territory obj
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