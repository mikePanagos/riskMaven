package mojo;

/* Never used - Oscar Tovar
 import java.lang.reflect.Array;
 */
import mojo.*;
import java.util.*;
import mojo.risk.*;

public class gui {
    GameEngine game = GameEngine.getInit();
    Setup s = Setup.getInstances();
    Scanner key = new Scanner(System.in);
    // Territory territory = new Territory();
    List<Territory> territory;
    boolean gameOver = false;
    boolean surrender = false;
    boolean endTurn = false;
    List<Player> playerList;

    public void start() {
       
        playerList = setupGui();
        game.shuffleCards();

        while (gameOver != true) {

            for (int x = 0; x < playerList.size(); x++) {
                System.out.println("\nPlayer " + playerList.get(x).getId() + "'s turn:\n");
                System.out.println("\n\n these are your cards\n "+playerList.get(x).printCards());
                // User choice
                newunits(playerList.get(x));
                checkIfHandIsFull(playerList.get(x));
                endTurn = false;
                while (endTurn != true) {
                    System.out.println(
                            "Please Choose: \n1.Trade\n2.Attack\n3.Fortify\n4.Show Territories\n5.End turn\n6.Surrender");
                    // Scanner choose = new Scanner(System.in);
                    int choice = key.nextInt();
                    try {
                        // trade option
                        if (choice == 1) {
                            trade(playerList.get(x));
                        }
                        // attack option
                        if (choice == 2) {
                            attack(playerList.get(x));
                        }

                        // fortify option
                        if (choice == 3) {
                            fortify(playerList.get(x));
                        }
                        // display owned territories
                        if (choice == 4) {
                            System.out.println(playerList.get(x).printableTerritories());
                        }

                        // endturn option
                        if (choice == 5) {
                            // System.out.println(playerList);
                            endTurn = true;
                        }
                        if (choice == 6) {
                            surrender = surrender(playerList.get(x));
                            endTurn=true;
                        }
                    } catch (InputMismatchException echoice) {
                        System.out.println("Please enter a valid answer: (y/n)");
                        choice = key.nextInt();
                    }
                }
                if(playerList.get(x).getAttackedAtLeastOnces()){
                    game.handOutCard(playerList.get(x));
                    playerList.get(x).setAttackedAtLeastOnces(false);

                }
            }
            // System.out.println("this is a test for: end of turn");

            
        }
        System.out.println("player"+playerList.get(0).getId()+" won!!!");
    }


    /**
     * this method will assign the first 42 territorys out
     * 
     * @param pList list of all players
     */
    public void handOutTerr(List<Player> pList) {
        // Scanner m = new Scanner(System.in);
        boolean done = false;
        int count = 0;
        int choice = 0;
        Random rand = new Random();

        System.out.println(" would you like to 1. pick a random territory for all  or 2. pick territory?");
        choice = key.nextInt();
        while (choice != 1 && choice != 2) {
            System.out.println(" PLEASE ENTER 1 0R 2");
            choice = key.nextInt();
        }

        switch (choice) {
        case 1: {
            while (!done) {
                for (int i = 0; i < pList.size(); i++) {

                    pList.get(i).addTerritory(territory.get(count));
                    territory.get(count).setNumOfUnits(territory.get(count).getNumOfUnits() + 1);
                    territory.get(count).setOwner(pList.get(i).getId());
                    pList.get(i).setArmiesCount(pList.get(i).getArmiesCount() - 1);
                    count++;
                    if (count > 41) {
                        done = true;
                        break;
                    }
                }

            }
        }
        case 2: {
            while (!done) {
                for (int i = 0; i < pList.size(); i++) {
                    System.out.println("what territory do you want? player " + pList.get(i).getId());
                    System.out.println(s.printTerritories());

                    choice = key.nextInt();
                    try {
                        while (choice > territory.size() - 1) {

                            System.out.println("try again what territory do you want? player " + pList.get(i).getId());
                            try {
                                choice = key.nextInt();

                            } catch (InputMismatchException exception) {
                                System.out.println(
                                        "please try again what territory do you want? player " + pList.get(i).getId());
                                choice = key.nextInt();
                            }
                        }
                    } catch (InputMismatchException exception) {
                        System.out
                                .println("please try again what territory do you want? player " + pList.get(i).getId());
                        choice = key.nextInt();
                    }

                    pList.get(i).addTerritory(territory.get(choice));
                    territory.get(count).setNumOfUnits(territory.get(count).getNumOfUnits() + 1);
                    territory.get(count).setOwner(pList.get(i).getId());
                    pList.get(i).setArmiesCount(pList.get(i).getArmiesCount() - 1);
                    s.removeTerritory(choice);

                    count++;
                    if (count > 41) {
                        done = true;
                        break;
                    }
                }

            }
            break;
        }
        }

    }
  

    /**
     * @auther michael
     * @param players list of all players
     */
    public void handOutRestOfUnits(List<Player>players){
        int choice=0;
        int unitsAdding=0;
        System.out.println("\nall terriories have been assigned and have one unit on each now you need to add the rest off your units to terriories\n");

        for(int i=0;i<players.size();i++){
            while(players.get(i).getArmiesCount()!=0){
            System.out.println("here are your territories player "+players.get(i).getId()+"\n "+players.get(i).printableTerritories());

            System.out.println("where would you like to place your units");
            choice=key.nextInt();
            while(choice<players.get(i).getTerritoryCount()-1)
            {
                System.out.println("Not vaild chocie Try again. \nwhere would you like to place your units");
            choice=key.nextInt();
            }
            System.out.println("\nhow many units would you liek to place on " + players.get(i).getTerritory(choice).getName()+"/n you have "+players.get(i).getArmiesCount());
           unitsAdding = key.nextInt();
            while (unitsAdding > players.get(i).getArmiesCount()) {
                System.out.println("sorry you dont have that many units pick a lower number");
                System.out.println("how many units would you liek to place on " + players.get(i).getTerritory(choice).getName());
                unitsAdding = key.nextInt();

            }

            players.get(i).getTerritory(choice).setNumOfUnits(players.get(i).getTerritory(choice).getNumOfUnits() + unitsAdding);
            players.get(i).setArmiesCount(players.get(i).getArmiesCount() - unitsAdding);
        }
            
        }

    }

    /**
     * 
     * @param player current PLayer
     * @return
     */
    public int newunits(Player player) {
        List<Continent> continents;
        int units = player.getTerritoryCount() / 3;
        if (units < 3) {
            units = 3;
        }
        if (player.getContinentCount() >= 1) {
            // if(){
            // if player owns Asia then units + 7
            // }
            // if(){
            // if player owns Europe then units + 5
            // }
            // if(){
            // if player owns North America then units + 5
            // }
            // if(){
            // if player owns Africa then units +3
            // }
            // if(){
            // if player owns South America then untis + 2
            // }
            // if(){
            // if player owns Australia then units + 2
            // }
        }
        player.setArmiesCount(units + player.getArmiesCount());
        System.out.println("You are getting " + units + " units this turn now\n you have " + player.getArmiesCount()
                + " in total");
        System.out
                .println("Your territories:\n" + player.printableTerritories() + "\nWhere would you like to put them?");
        // Scanner area = new Scanner(System.in);
        int put = key.nextInt();

        try {
            player.getTerritory(put);
            System.out.println("how many units would you liek to place on " + player.getTerritory(put).getName());
            int unitsAdding = key.nextInt();
            while (unitsAdding > player.getArmiesCount()) {
                System.out.println("sorry you dont have that many units pick a lower number");
                System.out.println("how many units would you liek to place on " + player.getTerritory(put).getName());
                unitsAdding = key.nextInt();
            }

            player.getTerritory(put).setNumOfUnits(player.getTerritory(put).getNumOfUnits() + unitsAdding);
            player.setArmiesCount(player.getArmiesCount() - unitsAdding);
            System.out.println("you have " + player.getTerritory(put).getNumOfUnits() + " units on territory"
                    + player.getTerritory(put).getName());
        } catch (InputMismatchException eplace) {
            System.out.println("Please choose aw number in the territories available:" + player.printableTerritories());
            put = key.nextInt();
        }
        // area.close(); // Closing Scanner to lower resource usage - Oscar
        return units; // Previously not returning a type - Oscar
    }


    /**
     * this will hand out one card for every succesful attack
     * 
     * @param player
     */
    public void checkIfHandIsFull(Player player){
        if(player.getCardCount()>=5){
            // game.trade( player);
        }
    }


    public Boolean endturn() {
        boolean end = false;
        System.out.println("Are you sure you wish to end your turn? (y/n)");
        Scanner turn = new Scanner(System.in);
        String en = turn.nextLine();
        try {
            if (en == "y") {
                end = true;
            }
            if (en == "n") {
                end = false;
            }
        } catch (InputMismatchException esurrender) {
            System.out.println("Please enter a valid answer: (y/n)");
            en = turn.nextLine();
        }
        turn.close();
        return end;
    }

    public List<Player> setupGui() {
        boolean playrange = false;
        System.out.println("Welcome to Risk!");
        System.out.println("How many players are there?");
        // Scanner input = new Scanner(System.in);// Scan for input
        int numberofplayers = key.nextInt();
        do {
            try {
                if (numberofplayers <= 6 && numberofplayers >= 2) {
                    Setup.SetupPLayers(numberofplayers);
                    playrange = true; // if number is in range then continue
                } else {
                    System.out.println("Not in range. Please enter 2-6 players");
                    numberofplayers = key.nextInt();
                }
            } catch (InputMismatchException exception) { // catch non-number error
                System.out.println("Not a Number. Please enter 2-6 players: ");
                numberofplayers = key.nextInt();
            }

        } while (!(playrange)); // end loop when returned true
       
        List<Player> players = s.getPlayers();
        territory = s.getTerritories();
    
        Collections.shuffle(players);
        System.out.println("The order of players is:");
        for (int i = 0; i < players.size(); i++) {
            System.out.println("Player " + players.get(i).getId()); // print player order
        }

        handOutTerr(players);
        handOutRestOfUnits(players);
        return players;
    }

    public void trade( Player player) {
        String tra;
        if (player.getCardCount() >= 3) {
                System.out.println("Would you like to trade a set of 3 cards?");
                key.nextLine();
                tra = key.nextLine();
                try {
                    if (tra.equals("y")) {
                        game.trade( player);
                    }
                    if (tra.equals("n")) {
                        
                    }
                } catch (InputMismatchException etrade) { // catch non-number error
                    System.out.println("Not a Number. Please enter 2-6 players: ");
                    tra = key.nextLine();
                    
                }
        } else {
            System.out.println("You do not have enough cards.");
        }
    }

    /**
     * 
     * @param player the current player who wishes to attack
     * @return will return a string for testing void
     */
    public void attack(Player player) {
        boolean attack = false; // Boolean used to determine if player would like to attack again
        do {
            System.out.println(" here");
            String attackagain;
            // List<Territory> terrThatCanAttack = game.getAttackingTerritories(player);
            // String printableList =
            // makeStringFromListOfAttackingTerritories(terrThatCanAttack);
            System.out.println("Where do you want to attack from?" + player.getPrintableListOfTerritoryThatCanAttack());
            // Scanner attfrom = new Scanner(System.in);
            // do a check for range
            int indexOfTerr = key.nextInt();
            Territory attackfrom = player.getTerritoryThatCanAttack(indexOfTerr);
            System.out.println("you are attacking from "+attackfrom.getName());
            System.out.println("with neights"+attackfrom.getNeighboringTerritories());

            // Territory defendingTerritoriesList = ;
            String defendingTerritories = attackfrom.printableListOfAttackableNeighboringTerritories(player.getId());
            System.out.println("Where do you want to attack?" + defendingTerritories);

            // Scanner attackTo = new Scanner(System.in);
            int attackToIndex = key.nextInt();
            Territory defendingTerr = attackfrom.getAttackableNeighboringTerritory(player.getId(), attackToIndex);
            System.out.println("you are attacking "+defendingTerr.getName());
            int attackingUnits=0;
            System.out.println("how many units do you want to attack with"+(attackfrom.getNumOfUnits()-1));
             attackingUnits =key.nextInt();
             while(attackingUnits>attackfrom.getNumOfUnits())
             {
                System.out.println("invailed numebr try again. how many units do you want to attack with"+(attackfrom.getNumOfUnits()-1));
                attackingUnits =key.nextInt();
             }
            game.attack(attackfrom, defendingTerr,attackingUnits);
            System.out.println("Do you want to attack again? (y/n)");
            // Scanner attagain = new Scanner(System.in);
            key.nextLine();
            attackagain = key.nextLine();
            try {
                if (attackagain.equals("y")) {
                    attack = false;
                }
                if (attackagain.equals("n")) {
                    attack = true;
                }
            } catch (InputMismatchException eattack) {
                System.out.println("Please enter a valid answer (y/n)");
                attackagain = key.nextLine();
            }
            // attackTo.close();
            // key.close();
            // attfrom.close();
        } while (attack != true);
    }

    /**
     * 
     * @param player current Player
     * @return
     */
    public void fortify(Player player) {
        System.out.println("where are you moving the armys from\n"+player.printableTerritories());
        int answer=key.nextInt(); 
        System.out.println("where do you want to fortity\n"+player.getTerritory(answer).getprintableListOfOwnedNeighboringTerritories(player.getId()));
        int num=key.nextInt();
        System.out.println("how many units are you going to move you have"+(player.getTerritory(answer).getNumOfUnits()-1));
        int unitsMoving=key.nextInt();
        game.fortify(player.getTerritory(answer),player.getTerritory(answer).getOwnedNeighboringTerritory(player.getId(), num),unitsMoving);

    //    }
    }

    public boolean surrender(Player player) {
        // boolean surrender = false;
        System.out.println("Are you sure you wish to surrender? (y/n)");
        // Scanner giveup = new Scanner(System.in);
        key.nextLine();
        String surr = key.nextLine();
        try {
            if (surr.equals("y")) {
                surrender = true;
                playerList.remove(player.getId()-1);
                if(playerList.size()<=1){
                    gameOver=true;
                }
            }
            if (surr.equals("n")) {
                surrender = false;
            }
        } catch (InputMismatchException esurrender) {
            System.out.println("Please enter a valid answer: (y/n)");
            surr = key.nextLine();
        }

        return surrender;
    }
}
