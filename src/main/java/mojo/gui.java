package mojo;

import java.lang.reflect.Array;
import mojo.*;
import java.util.*;
import mojo.risk.*;

public class gui {
    GameEngine game = new GameEngine();
    Setup s = new Setup();
    Scanner key = new Scanner(System.in);
    // Territory territory = new Territory();
    List<Territory> territory;

    public void start() {
        boolean win = false;
        boolean lose = false;
        do {
            int counter = 0;
            List<Player> playerList = setup();
            startArmies(playerList.size());
            handOutTerr(playerList);
            // List<Player> playerList = s.getPlayers(); //playerList.get()
            for (int x = 0; win != true; x++) {
                boolean endturn = false;
                boolean endturn2 = false;
                boolean surrender = false;
                do {
                    System.out.println("Player " + playerList.get(x).getId() + "'s turn:\n");
                    int curArmies = playerList.get(x).getArmiesCount();
                    while (endturn2 != true || surrender != true) {
                        newunits(playerList.get(x));
                        System.out.println(
                                "Please Choose: \n1.Trade\n2.Attack\n3.Fortify\n4.Show Territories\n5.End turn\n6.Surrender");
                        Scanner choose = new Scanner(System.in);
                        int choice = choose.nextInt();
                        try {
                            // trade option
                            if (choice == 1) {
                                trade(counter, playerList.get(x));
                            }
                            // attack option
                            if (choice == 2) {
                                attack(playerList.get(x));
                                endturn2 = false;
                            }

                            // fortify option
                            if (choice == 3) {
                                fortiy(playerList.get(x));
                                endturn2 = false;
                            }

                            // display owned territories
                            if (choice == 4) {
                                playerList.get(x).displayterritory();
                                endturn2 = false;
                            }

                            // endturn option
                            if (choice == 5) {
                                endtrun2 = endturn();
                            }

                            // game surrender
                            if (choice == 6) {
                                surrender = surrender();
                            }
                        } catch (InputMismatchException echoice) {
                            System.out.println("Please enter a valid answer: (y/n)");
                            choice = choose.nextInt();
                        }
                        choose.close(); // Closing Scanner to lower resource usage - Oscar
                    }
                    System.out.println("this is a test for: end of turn");
                    if (playerList.size() == 1) {
                        System.out.println("Player " + playerList.get(x).getId() + "wins!");
                        win = true;
                    }
                    if (surrender = true) {
                        playerList.remove(x);
                    }
                    endturn = true;
                } while (endturn != true || surender != true);
                if (x == 6) {
                    x = 0;
                }
            }
            System.out.println("This is a test for: Win/lose check");
        } while (win != true);

    }

    public void startArmies(int numplayers){
        if(numplayers == 2){
            for(int x = 0; x <= numplayers; x++)
                player.get(x).getid(x).setArmiesCount(40);
        }
        if(numplayers == 3){
            for(int x = 0; x <= numplayers; x++)
                player.get(x).getid(x).setArmiesCount(35);
        }
        if(numplayers == 4){
            for(int x = 0; x <= numplayers; x++)
                player.get(x).getid(x).setArmiesCount(30);
        }
        if(numplayers == 5){
            for(int x = 0; x <= numplayers; x++)
                player.get(x).getid(x).setArmiesCount(25);
        }
        if(numplayers == 6){
            for(int x = 0; x <= numplayers; x++)
                player.get(x).getid(x).setArmiesCount(20);
        }
        return;
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
        Random rand = new Random();
        while (!done) {
            for (int i = 0; i < pList.size(); i++) {

                System.out.println("Player " + pList.get(i).getId()
                        + " would you like to 1. pick a random territory or 2. pick a territory?");
                int choice = key.nextInt();

                switch (choice) {
                    case 1: {
                        for (int temp = 1; temp <= 42; temp++) {
                            int x = rand.nextInt(42);
                            if (x != pList.getTerritories(x)) {
                                temp--; // if already in ArrayList then start over
                            } else {
                                pList.get(i).addTerritory(territory.get(rand));
                            }
                        }
                        break;
                    }
                    case 2: {
                        pList.get(i).addTerritory(territory.get(count));
                        break;
                    }
                }
                count++;
                if (count > 41) {
                    done = true;
                    break;
                }
            }

        }
        // m.close();

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
        if(player.getContinentCount() >= 1){
            // if(){
                //if player owns Asia then units + 7
            // }
            // if(){
                //if player owns Europe then units + 5
            // }
            // if(){
                //if player owns North America then units + 5
            // }
            // if(){
                //if player owns Africa then units +3
            // }
            // if(){
                //if player owns South America then untis + 2
            // }
            // if(){
                //if player owns Australia then units + 2
            // }
        }
        System.out.println("You are getting " + units + " units\n");
        System.out
                .println("Your territories:\n" + player.printableTerritories() + "\nWhere would you like to put them?");
        Scanner area = new Scanner(System.in);
        int put = area.nextInt();
        try {
            player.getTerritory(put);
            System.out.println("how many units would you liek to place on " + player.getTerritory(put).getName());
            int unitsAdding = area.nextInt();
            player.getTerritory(put).setNumOfUnits(player.getTerritory(put).getNumOfUnits() + unitsAdding);
            System.out.println("you have " + player.getTerritory(put).getNumOfUnits() + " units on territory"
                    + player.getTerritory(put).getName());
        } catch (InputMismatchException eplace) {
            System.out.println("Please choose aw number in the territories available:" + player.printableTerritories());
            put = area.nextInt();
        }
        area.close(); // Closing Scanner to lower resource usage - Oscar
        return units; // Previously not returning a type - Oscar
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

    public List<Player> setup() {
        boolean playrange = false;
        System.out.println("Welcome to Risk!");
        System.out.println("How many players are there?");
        // Scanner input = new Scanner(System.in);// Scan for input
        int numberofplayers = key.nextInt();
        do {
            try {
                if (numberofplayers <= 6 && numberofplayers >= 2) {
                    s.setup(numberofplayers);
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
        ArrayList<Integer> listOfIdsUsed = new ArrayList<Integer>(numberofplayers);
        List<Player> randPlayerOrder = new ArrayList<>();
        List<Player> players = s.getPlayers();
        territory = s.getTerritories();
        s.setup(numberofplayers);
        Random rand = new Random();
        for (int temp = 1; temp <= numberofplayers; temp++) {
            int x = rand.nextInt(numberofplayers + 1); // return random int dependent on number of players
            if (listOfIdsUsed.contains(x)) {
                temp--; // if already in ArrayList then start over
            } else {
                if (x == 0) {
                    temp--; // if already in ArrayList then start over
                } else {

                    listOfIdsUsed.add(x);
                    randPlayerOrder.add(players.get(x));// if not in ArrayList then put in "first"
                }
            }
        }
        for (int i = 0; i <= numberofplayers; i++) {
            players.get(i).setArmiesCount(s.numUnitAtStart(i));
        }
        System.out.println("The order of players is:");
        for (int i = 0; i < listOfIdsUsed.size(); i++) {
            System.out.println("Player " + listOfIdsUsed.get(i)); // print player order
        }

        // assign terr to all
        // input.close();
        return randPlayerOrder;
    }

    public boolean trade(int counter, Player player) {
        boolean trade = false;
        String tra;
        if (player.getCardCount() >= 3) {
            do {
                System.out.println("Would you like to trade a set of 3 cards?");
                Scanner tradecard = new Scanner(System.in);
                tra = tradecard.nextLine();
                try {
                    if (tra == "y") {
                        counter++;
                        game.trade(counter, player);
                        trade = true;
                    }
                    if (tra == "n") {
                        trade = true;
                    }
                } catch (InputMismatchException etrade) { // catch non-number error
                    System.out.println("Not a Number. Please enter 2-6 players: ");
                    tra = tradecard.nextLine();
                }
                tradecard.close();
            } while (trade != true);
        } else {
            System.out.println("You do not have enough cards.");
            return trade;
        }
        return trade;
    }

    /**
     * 
     * @param player the current player who wishes to attack
     * @return will return a string for testing void
     */
    public void attack(Player player) {
        boolean attack = false; // Boolean used to determine if player would like to attack again
        do {
            String attackagain;
            // List<Territory> terrThatCanAttack = game.getAttackingTerritories(player);
            // String printableList =
            // makeStringFromListOfAttackingTerritories(terrThatCanAttack);
            System.out.println("Where do you want to attack from?" + player.getPrintableListOfTerritoryThatCanAttack());
            Scanner attfrom = new Scanner(System.in);
            // do a check for range
            int indexOfTerr = attfrom.nextInt();
            Territory attackfrom = player.getTerritoryThatCanAttack(indexOfTerr);
            // Territory defendingTerritoriesList = ;
            String defendingTerritories = attackfrom.printableListOfAttackableNeighboringTerritories(player.getId());
            System.out.println("Where do you want to attack?" + defendingTerritories);
            Scanner attackTo = new Scanner(System.in);
            int attackToIndex = attackTo.nextInt();
            Territory defendingTerr = attackfrom.getAttackableNeighboringTerritory(player.getId(), attackToIndex);
            game.attack(attackfrom, defendingTerr);
            System.out.println("Do you want to attack again? (y/n)");
            Scanner attagain = new Scanner(System.in);
            attackagain = attagain.nextLine();
            try {
                if (attackagain == "y") {
                    attack = false;
                }
                if (attackagain == "n") {
                    attack = true;
                }
            } catch (InputMismatchException eattack) {
                System.out.println("Please enter a valid answer (y/n)");
                attackagain = attagain.nextLine();
            }
            attackTo.close();
            attagain.close();
            attfrom.close();
        } while (attack != true);
    }

    public String makeStringFromListOfAttackingTerritories(List<Territory> terrThatCanAttack) {

        String namesOfAbleToAttack = " ";
        for (int i = 0; i < terrThatCanAttack.size(); i++) {
            namesOfAbleToAttack += i + ". " + terrThatCanAttack.get(i).getName() + ", ";
        }

        return namesOfAbleToAttack;
    }

    /**
     * 
     * @param player current Player
     * @return
     */
    public void fortify(Player player) {
        boolean fortify = false;
        do {
            System.out.println("Which territory do you want to move units from?\n" + player.printableTerritories());
            Scanner from = new Scanner(System.in);
            int moveFrom = from.nextInt();
            try {
                System.out.println("Which territory are you moving to?\n"
                        + player.getTerritory(moveFrom).getprintableListOfOwnedNeighboringTerritories(player.getId()));
                Scanner to = new Scanner(System.in);
                int moveTo = to.nextInt();
                int territryUnit = player.getTerritory(moveFrom).getNumOfUnits();
                try {
                    if (territryUnit > 1) {
                        System.out.println("How many units would you like to move?\n Total: " + (territryUnit - 1));
                        Scanner unitmove = new Scanner(System.in);
                        int troops = unitmove.nextInt();

                        // List<Territory> terrThatCanAttack=game.getAttackingTerritories(player);
                        // String
                        // printableList=makeStringFromListOfAttackingTerritories(terrThatCanAttack);
                        // System.out.println("Where do you want to attack from?"+printableList );
                        // Scanner attfrom = new Scanner(System.in);
                        // //do a check for range
                        // Territory attackfrom = terrThatCanAttack.get(attfrom.nextInt());

                        game.fortify(player.getTerritory(moveFrom),
                                player.getTerritory(moveFrom).getOwnedNeighboringTerritory(player.getId(), moveTo),
                                troops);
                        System.out.println("Do you want to fortify again? (y/n)\n");
                        Scanner fortifyagain = new Scanner(System.in);
                        String fortnite = fortifyagain.nextLine();
                        try {
                            if (fortnite == "y")
                                fortify = false;
                            if (fortnite == "n")
                                fortify = true;
                        } catch (InputMismatchException efortnight) {
                            System.out.println("Please enter a valid answer (y/n)");
                            String fortnight = fortifyagain.nextLine();
                        }
                        unitmove.close();
                        fortifyagain.close();
                    } else {
                        System.out.println("PLease choose a valid number");
                        fortify = false;
                    }
                } catch (InputMismatchException emoveto) {
                    System.out.println(
                            "Please choose a number in the territories available:" + player.printableTerritories());
                    moveTo = to.nextInt();
                }
                to.close();
            } catch (InputMismatchException emovefrom) {
                System.out.println("Please choose a number in the territories available:"
                        + player.getTerritory(moveFrom).getprintableListOfOwnedNeighboringTerritories(player.getId()));
                moveFrom = from.nextInt();
            }
            from.close();
        } while (fortify != true);
        return;
    }

    public boolean surrender() {
        boolean surrender = false;
        System.out.println("Are you sure you wish to surrender? (y/n)");
        Scanner giveup = new Scanner(System.in);
        String surr = giveup.nextLine();
        try {
            if (surr == "y") {
                surrender = true;
            }
            if (surr == "n") {
                surrender = false;
            }
        } catch (InputMismatchException esurrender) {
            System.out.println("Please enter a valid answer: (y/n)");
            surr = giveup.nextLine();
        }
        giveup.close();
        return surrender;
    }
}
