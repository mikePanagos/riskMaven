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
        boolean gameOver = false;
        boolean surrender = false;
        boolean endTurn=false;
        int counter = 0;
        List<Player> playerList = setup();

            while(gameOver!=true){
            
            for (int x = 0; x<playerList.size(); x++) {
                    System.out.println("\nPlayer " + playerList.get(x).getId() + "'s turn:\n");
                    // User choice
                        newunits(playerList.get(x));
                        endTurn=false;
                        while(endTurn!=true){
                        System.out.println("Please Choose: \n1.Trade\n2.Attack\n3.Fortify\n4.Show Territories\n5.End turn\n6.Surrender");
                        // Scanner choose = new Scanner(System.in);
                        int choice = key.nextInt();
                        try {
                            // game.trade();
                            if (choice == 1) {
                                trade(counter, playerList.get(x));
                            }

                            if (choice == 2) {
                                attack(playerList.get(x));
                            }
                            if (choice == 3) {
                                // fortiy(playerList.get(x));
                            }
                            // display owned territories
                            if (choice == 4) {
                                // playerList.get(x).displayterritory();
                            }

                            // endturn option
                            if (choice == 5) {
                                // System.out.println(playerList);
                                endTurn=true;
                            }
                            if (choice == 6) {
                                surrender = surrender();
                            }
                        } catch (InputMismatchException echoice) {
                            System.out.println("Please enter a valid answer: (y/n)");
                            choice = key.nextInt();
                        }
                    }
                        // choose.close(); // Closing Scanner to lower resource usage - Oscar
                        // System.out.println("the number of players playing is "+playerList.size());
                        // if (playerList.size() == 1) {
                        //     System.out.println("Player " + playerList.get(x).getId() + "wins!");
                        //     gameOver = true;
                        // }
                        // if (surrender = true) {
                        //     playerList.remove(x);
                        // }
                
                    }
                    // System.out.println("this is a test for: end of turn");
                    
            // System.out.println("This is a test for: Win/lose check");
        }

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
        while (!done) {
            for (int i = 0; i < pList.size(); i++) {

                // System.out.println("Player " + pList.get(i).getId()
                //         + " would you like to 1. pick a random territory or 2. pick a territory?");
                // int choice = key.nextInt();

                // switch (choice) {
                // case 1: {

                    pList.get(i).addTerritory(territory.get(count));
            //         break;
            //     }
            //     case 2: {
            //         pList.get(i).addTerritory(territory.get(count));
            //         break;
            //     }
            //     }

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
        int units = player.getTerritoryCount() / 3;
        if (units < 3) {
            units = 3;
        }
        System.out.println("You are getting " + units + " units\n");
        System.out
                .println("Your territories:\n" + player.printableTerritories() + "\nWhere would you like to put them?");
        // Scanner area = new Scanner(System.in);
        int put = key.nextInt();
        try {
            player.getTerritory(put);
            System.out.println("how many units would you liek to place on " + player.getTerritory(put).getName());
            int unitsAdding = key.nextInt();
            player.getTerritory(put).setNumOfUnits(player.getTerritory(put).getNumOfUnits() + unitsAdding);
            System.out.println("you have " + player.getTerritory(put).getNumOfUnits() + " units on territory"
                    + player.getTerritory(put).getName());
        } catch (InputMismatchException eplace) {
            System.out.println("Please choose aw number in the territories available:" + player.printableTerritories());
            put = key.nextInt();
        }
        // area.close(); // Closing Scanner to lower resource usage - Oscar
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
        for (int i = 0; i < randPlayerOrder.size(); i++) {
            System.out.println("Player " + randPlayerOrder.get(i).getId()); // print player order
        }

        // assign terr to all
        // input.close();
        handOutTerr(randPlayerOrder);
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
            // Scanner attfrom = new Scanner(System.in);
            // do a check for range
            int indexOfTerr = key.nextInt();
            Territory attackfrom = player.getTerritoryThatCanAttack(indexOfTerr);
            // Territory defendingTerritoriesList = ;
            String defendingTerritories = attackfrom.printableListOfAttackableNeighboringTerritories(player.getId());
            System.out.println("Where do you want to attack?" + defendingTerritories);
            // Scanner attackTo = new Scanner(System.in);
            int attackToIndex = key.nextInt();
            Territory defendingTerr = attackfrom.getAttackableNeighboringTerritory(player.getId(), attackToIndex);
            game.attack(attackfrom, defendingTerr);
            System.out.println("Do you want to attack again? (y/n)");
            // Scanner attagain = new Scanner(System.in);
            key.nextLine();
            attackagain = key.nextLine();
            try {
                if (attackagain == "y") {
                    attack = false;
                }
                if (attackagain == "n") {
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
    public String fortify(Player player) {
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
        return "Hello";
    }

    public boolean surrender() {
        boolean surrender = false;
        System.out.println("Are you sure you wish to surrender? (y/n)");
        // Scanner giveup = new Scanner(System.in);
        key.nextLine();
        String surr = key.nextLine();
        try {
            if (surr == "y") {
                surrender = true;
            }
            if (surr == "n") {
                surrender = false;
            }
        } catch (InputMismatchException esurrender) {
            System.out.println("Please enter a valid answer: (y/n)");
            surr = key.nextLine();
        }
        
        return surrender;
    }
}
