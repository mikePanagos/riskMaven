// package mojo;

// import java.util.*;
// import mojo.risk.*;
// import mojo.twitter.*;
// import twitter4j.*;

// public class gui {
//     GameEngine game = GameEngine.getInit();
//     Setup s = Setup.getInstances();
//     Scanner key = new Scanner(System.in);
//     // Territory territory = new Territory();
//     List<Territory> territory;
//     boolean gameOver = false;
//     boolean surrender = false;
//     boolean endTurn = false;
//     List<Player> playerList;
//     GameLogger log = new GameLogger(); // Game logging
//     //WebConnection link = new WebConnection();
// 	TwitterClient twitterAccount = new TwitterClient();

//     public void start() {
//         twitterAccount.setTweet("The game for conquest has started!");
//         System.out.println(twitterAccount.postTweet());
//         playerList = setupGui();
//         game.shuffleCards();

//         while (gameOver != true) {

//             for (int x = 0; x < playerList.size(); x++) {
//                 System.out.println("\nPlayer " + playerList.get(x).getId() + "'s turn:\n");
//                 System.out.println("\n\n these are your cards\n " + playerList.get(x).printCards());
//                 log.updateLog("Player " + playerList.get(x).getId() + " is now starting their turn.");
//                 log.updateLog("Player " + playerList.get(x).getId() + " received the following cards.");
//                 log.updateLog(playerList.get(x).printCards());
//                 // User choice
//                 newunits(playerList.get(x));
//                 checkIfHandIsFull(playerList.get(x));
//                 endTurn = false;
//                 while (endTurn != true) {
//                     System.out.println(
//                             "Please Choose: \n1.Trade\n2.Attack\n3.Fortify\n4.Show Territories\n5.End turn\n6.Surrender");
//                     // Scanner choose = new Scanner(System.in);
//                     int choice = key.nextInt();
//                     try {
//                         // trade option
//                         if (choice == 1) {
//                             trade(playerList.get(x));
//                         }
//                         // attack option
//                         if (choice == 2) {
//                             attack(playerList.get(x));
//                         }

//                         // fortify option
//                         if (choice == 3) {
//                             fortify(playerList.get(x));
//                         }
//                         // display owned territories
//                         if (choice == 4) {
//                             System.out.println(playerList.get(x).printableTerritories());
//                         }

//                         // endturn option
//                         if (choice == 5) {
//                             // System.out.println(playerList);
//                             endTurn = true;
//                         }
//                         if (choice == 6) {
//                             surrender = surrender(playerList.get(x));
//                             endTurn = true;
//                         }
//                     } catch (InputMismatchException echoice) {
//                         System.out.println("Please enter a valid answer: (y/n)");
//                         choice = key.nextInt();
//                     }
//                 }
//                 if (playerList.get(x).getAttackedAtLeastOnces()) {
//                     game.handOutCard(playerList.get(x));
//                     playerList.get(x).setAttackedAtLeastOnces(false);

//                 }
//             }
//             // System.out.println("this is a test for: end of turn");

//         }
//         System.out.println("Player" + playerList.get(0).getId() + " won!!!");
//     }

//     /**
//      * this method will assign the first 42 territorys out
//      * 
//      * @param pList list of all players
//      */
//     public void handOutTerr(List<Player> pList) {
//         // Scanner m = new Scanner(System.in);
//         boolean done = false;
//         int count = 0;
//         String choice = null;
//         int choiceInt = 0;
//         boolean inRange = false;
//         // Random rand = new Random();

//         System.out.println("Would you like to:\n1. Pick a random territory for all \n2. Pick a territory");
//         choice = key.next();
//         choiceInt = askingForANumber(choice);
//         while (!inRange) {
//             if (choiceInt == 1 || choiceInt == 2) {
//                 choiceInt = askingForANumber(choice);
//                 inRange = true;
//             } else {
//                 System.out.println("PLEASE ENTER 1 0R 2");
//                 choice = key.next();
//                 choiceInt = askingForANumber(choice);
//             }
//         }

//         switch (choiceInt) {
// 	        case 1: {
// 	            while (!done) {
// 	                for (int i = 0; i < pList.size(); i++) {
	
// 	                    pList.get(i).addTerritory(territory.get(0));
// 	                    territory.get(0).setNumOfUnits(territory.get(0).getNumOfUnits() + 1);
// 	                    territory.get(0).setOwner(pList.get(i).getId());
// 	                    pList.get(i).setArmiesCount(pList.get(i).getArmiesCount() - 1);
// 	                    territory.remove(0);
// 	                    // s.removeTerritory(0);
// 	                    count++;
// 	                    if (count > 41) {
// 	                        done = true;
// 	                        break;
// 	                    }
// 	                }
	
// 	            }
// 	            break;
// 	        }
// 	        case 2: {
// 	            while (!done) {
// 	                for (int i = 0; i < pList.size(); i++) {
// 	                    System.out.println("What territory do you want Player " + pList.get(i).getId() + "?");
// 	                    System.out.println(printTerritories());
// 	                    inRange = false;
// 	                    choiceInt = askingForANumber(key.next());
// 	                    while (!inRange) {
	
// 	                        if (choiceInt > territory.size() - 1) {
// 	                            System.out.println("Please try again! What territory do you want Player " + pList.get(i).getId() + "?");
// 	                            choiceInt = askingForANumber(key.next());
	
// 	                        } else {
// 	                            inRange = true;
// 	                        }
// 	                    }
// 	                    if (territory.size()  >=1) {
// 	                        pList.get(i).addTerritory(territory.get(choiceInt));
// 	                        territory.get(choiceInt).setNumOfUnits(territory.get(choiceInt).getNumOfUnits() + 1);
// 	                        territory.get(choiceInt).setOwner(pList.get(i).getId());
// 	                        pList.get(i).setArmiesCount(pList.get(i).getArmiesCount() - 1);
// 	                        territory.remove(choiceInt);
// 	                    } 
// 	                }
// 	                if(territory.size()<=0) {
// 	                    done = true;
// 	                }
	
// 	            }
// 	            break;
// 	        }
//         }
//         /* Used for testing purposes.
//         System.out.println("Size is: " + territory.size());
//         System.out.println("Size is: " + s.getTerritories().size());
// 		*/
//     }

//     public String printTerritories() {
//         String list=" ";
//         for(int i=0; i<territory.size();i++){
//             list+= " "+i+". "+territory.get(i).getName()+".";
//         }
//         return list;
//     }
//     /**
//      * @author michael
//      * @param players list of all players
//      */
//     public void handOutRestOfUnits(List<Player> players) {
//     	System.out.println();
//         System.out.println("All terriories have been assigned and have one unit on each. You need to add the rest off your units to terriories.\n");

//         for (int i = 0; i < players.size(); i++) {
//             while (players.get(i).getArmiesCount() != 0) {
//                 placeUnits(players.get(i));
//             }

//         }

//     }

//     public void placeUnits(Player p) {
//         String userString = null;
//         int choice;
//         int unitsAdding = 0;
//         boolean inRange = false;
//         System.out.println("Here are your territories player " + p.getId() + "\n " + p.printableTerritories());

//         System.out.println("Where would you like to place your units");
//         userString = key.next();
//         choice = askingForANumber(userString);
//         while (!inRange) {

//             if (choice >( p.getTerritoryCount() - 1)) {
//                 System.out.println("Number out of range please pick between 0 and " + (p.getTerritoryCount()-1));
//                 userString = key.next();
//                 choice = askingForANumber(userString);

//             } else {
//                 inRange = true;
//             }
//         }
//         System.out.println();
//         System.out.println("How many units would you like to place on " + p.getTerritory(choice).getName()
//                 + ".\n You have " + p.getArmiesCount());
//         inRange = false;
//         userString = key.next();
//         unitsAdding=askingForANumber(userString);
//         while (!inRange) {

//             if (unitsAdding > p.getArmiesCount()) {
//                 System.out.println("Sorry you dont have that many units. Please pick a lower number.");
//                 System.out.println("How many units would you like to place on " + p.getTerritory(choice).getName());
//                 userString = key.next();
//                 unitsAdding=askingForANumber(userString);
//             }
//             else{
//                 inRange=true;
//             }
//         }
//         log.updateLog("Player " + p.getId() + " placed " + unitsAdding + " units on "
//         		+ p.getTerritory(choice).getName() + ".");
//         p.getTerritory(choice).setNumOfUnits(p.getTerritory(choice).getNumOfUnits() + unitsAdding);
//         p.setArmiesCount(p.getArmiesCount() - unitsAdding);
//     }

//     /**
//      * 
//      * @param player current PLayer
//      * @return the number of new units given
//      */
//     public int newunits(Player player) {
//         List<Continent> continents;
//         int units = player.getTerritoryCount() / 3;
//         if (units < 3) {
//             units = 3;
//         }
//         if (player.getContinentCount() >= 1) {
//             // if(){
//             // if player owns Asia then units + 7
//             // }
//             // if(){
//             // if player owns Europe then units + 5
//             // }
//             // if(){
//             // if player owns North America then units + 5
//             // }
//             // if(){
//             // if player owns Africa then units +3
//             // }
//             // if(){
//             // if player owns South America then untis + 2
//             // }
//             // if(){
//             // if player owns Australia then units + 2
//             // }
//         }
//         player.setArmiesCount(units + player.getArmiesCount());
        
//         System.out.println("You are getting " + units + " units this turn now");
//         log.updateLog("Player " + player.getId() + " received " + units + " units this turn");
        
//         System.out.println("You have " + player.getArmiesCount()
//                 + " armies in total");
//         log.updateLog("Player " + player.getId() + " now has " + player.getArmiesCount() + " armies in total.");
        
//         System.out.println("Your territories:\n" + player.printableTerritories());
//         System.out.println("Where would you like to put them?");
//         // Scanner area = new Scanner(System.in);
//         int put = key.nextInt();

//         try {
//             player.getTerritory(put);
//             System.out.println("How many units would you like to place on " + player.getTerritory(put).getName());
//             int unitsAdding = key.nextInt();
//             while (unitsAdding > player.getArmiesCount()) {
//                 System.out.println("Sorry you dont have that many units. Please pick a lower number.");
//                 System.out.println("How many units would you like to place on " + player.getTerritory(put).getName());
//                 unitsAdding = key.nextInt();
//             }

//             player.getTerritory(put).setNumOfUnits(player.getTerritory(put).getNumOfUnits() + unitsAdding);
//             player.setArmiesCount(player.getArmiesCount() - unitsAdding);
//             System.out.println("You have " + player.getTerritory(put).getNumOfUnits() + " units on territory"
//                     + player.getTerritory(put).getName());
//         } catch (InputMismatchException eplace) {
//             System.out.println("Please choose a number corresponding to the territories available:" + player.printableTerritories());
//             put = key.nextInt();
//         }
//         // area.close(); // Closing Scanner to lower resource usage - Oscar
//         return units; // Previously not returning a type - Oscar
//     }

//     /**
//      * this will hand out one card for every successful attack
//      * 
//      * @param player The player who owns the hand we will be checking
//      */
//     public void checkIfHandIsFull(Player player) {
//         if (player.getCardCount() >= 5) {
//             game.trade(player);
//         }
//     }

//     public Boolean endturn() {
//         boolean end = false;
//         System.out.println("Are you sure you wish to end your turn? (y/n)");
//         Scanner turn = new Scanner(System.in);
//         String en = turn.nextLine();
//         try {
//             if (en == "y") {
//             	log.updateLog("Current player's turn has ended.");
//                 end = true;
//             }
//             if (en == "n") {
//                 end = false;
//             }
//         } catch (InputMismatchException esurrender) {
//             System.out.println("Please enter a valid answer: (y/n)");
//             en = turn.nextLine();
//         }
//         turn.close();
//         return end;
//     }

//     public List<Player> setupGui() {
//         boolean playrange = false;
//         String numofplayers = null;
//         int numberofplayers = 0;
//         System.out.println("Welcome to Risk!");
//         System.out.println("How many players are there?");
//         // Scanner input = new Scanner(System.in);// Scan for input
//         while (!(playrange)) {
//             numofplayers = key.next();
//             numberofplayers = askingForANumber(numofplayers);
//             if (numberofplayers <= 6 && numberofplayers >= 2) {
//                 Setup.SetupPLayers(numberofplayers);
//                 log.updateLog("Game initiated with " + numofplayers + " players.");
//                 playrange = true; // if number is in range then continue
//             } else {
//                 System.out.println("Number not in valid range. Please pick a number from 1 to 6");
//             }
//         }
//         ; // end loop when returned true

//         List<Player> players = s.getPlayers();
//         territory = new ArrayList<>(s.getTerritories());

//         Collections.shuffle(players);
//         System.out.println("The order of players is:");
//         log.updateLog("Player order is as follows: ");
//         for (int i = 0; i < players.size(); i++) {
//             System.out.println("Player " + players.get(i).getId()); // print player order
//             log.updateLog("Player " + players.get(i).getId());
//         }

//         handOutTerr(players);
//         handOutRestOfUnits(players);
//         return players;
//     }

//     public void trade(Player player) {
//         String tra;
//         if (player.getCardCount() >= 3) {
//             System.out.println("Would you like to trade a set of 3 cards?");
//             key.nextLine();
//             tra = key.nextLine();

//             try {
//                 if (tra.equals("y")) {
//                 	log.updateLog("Player " + player.getId() + " has elected to trade in 3 cards.");
//                     game.trade(player);
//                 }
//                 if (tra.equals("n")) {

//                 }
//             } catch (InputMismatchException etrade) { // catch non-number error
//                 System.out.println("Not a Number. Please enter 2-6 players: ");
//                 tra = key.nextLine();

//             }
//         } else {
//             System.out.println("You do not have enough cards.");
//         }
//     }

//     /**
//      * This method will be used by the game engine to attack another player.
//      * @param player the current player who wishes to attack
//      */
//     public void attack(Player player) {
//         boolean inRange=false;
//         String userString=null;
//         int indexOfTerr=0;
//         boolean attack = false; // Boolean used to determine if player would like to attack again
//         do {
//             String attackagain;
            
//             System.out.println("Where do you want to attack from?" + player.getPrintableListOfTerritoryThatCanAttack());

//             // do a check for range
//             userString=key.next();
//             indexOfTerr = askingForANumber(userString);
//             while(!inRange){
//                 if(indexOfTerr>(player.getAttackableTerritoryCount()-1)){
//                     System.out.println("Invalid number. Please try again " + player.getAttackableTerritoryCount());
//                     userString=key.next();
//                     indexOfTerr = askingForANumber(userString);

//                 }
//                 else
//                 {
//                     inRange=true;
//                 }

//             }
            
//             Territory attackfrom = player.getTerritoryThatCanAttack(indexOfTerr);
//             System.out.println("You are attacking from " + attackfrom.getName());
//             // System.out.println("with neights" + attackfrom.getNeighboringTerritories());

//             // Territory defendingTerritoriesList = ;
//             String defendingTerritories = attackfrom.printableListOfAttackableNeighboringTerritories(player.getId());
//             System.out.println("Where do you want to attack?" + defendingTerritories);

//             // Scanner attackTo = new Scanner(System.in);
//             int attackToIndex = 0;

//             userString=key.next();
//             attackToIndex = askingForANumber(userString);
//             inRange=false;
//             while(!inRange){
//                 System.out.println(attackfrom.getNumOfNeighborsNotOwned()+" num of terrs");
//                 if(attackToIndex>(attackfrom.getNumOfNeighborsNotOwned()-1)){
//                     System.out.println("Invalid number try again");
//                     userString=key.next();
//                     attackToIndex = askingForANumber(userString);

//                 }
//                 else
//                 {
//                     inRange=true;
//                 }

//             }
//             Territory defendingTerr = attackfrom.getAttackableNeighboringTerritory(player.getId(), attackToIndex);
//             System.out.println("You are attacking " + defendingTerr.getName());
//             int attackingUnits = 0;
//             System.out.println("How many units do you want to attack with " + (attackfrom.getNumOfUnits() - 1));
//             attackingUnits = key.nextInt();
//             while (attackingUnits > attackfrom.getNumOfUnits()) {
//                 System.out.println("Invalid number try again. How many units do you want to attack with "
//                         + (attackfrom.getNumOfUnits() - 1));

//                 userString=key.next();
//                 attackingUnits = askingForANumber(userString);
                 
//             }
//             game.attack(attackfrom, defendingTerr, attackingUnits);
            
//             twitterAccount.setTweet("An attack on a player has started!");
            
//             log.updateLog("Player " + player.getId() + "'s territory, " + attackfrom.getName()
//             	+ ", attacked Player " +  "'s territory, " + defendingTerr.getcontinentName()
//             	+ ", with " + attackingUnits + " armies.");
            
//             System.out.println("Do you want to attack again? (y/n)");
//             // Scanner attagain = new Scanner(System.in);
//             key.nextLine();
//             attackagain = key.nextLine();
//             try {
//                 if (attackagain.equals("y")) {
//                     attack = false;
//                 }
//                 if (attackagain.equals("n")) {
//                     attack = true;
//                 }
//             } catch (InputMismatchException eattack) {
//                 System.out.println("Please enter a valid answer (y/n)");
//                 attackagain = key.nextLine();
//             }
//             // attackTo.close();
//             // key.close();
//             // attfrom.close();
//         } while (attack != true);
//     }

//     /**
//      * 
//      * @param player current Player
//      */
//     public void fortify(Player player) {
//         System.out.println("Where are you moving the armys from\n" + player.printableTerritories());
//         int answer = key.nextInt();
        
//         System.out.println("Where do you want to fortity\n"
//                 + player.getTerritory(answer).getprintableListOfOwnedNeighboringTerritories(player.getId()));
//         int num = key.nextInt();
        
//         System.out.println("How many units are you going to move?");
//         System.out.println("You have " + (player.getTerritory(answer).getNumOfUnits() - 1));
//         int unitsMoving = key.nextInt();
        
//         game.fortify(player.getTerritory(answer),
//                 player.getTerritory(answer).getOwnedNeighboringTerritory(player.getId(), num), unitsMoving);

//         log.updateLog("Player " + player.getId() + " has fortified " + player.getTerritory(num).getcontinentName()
//         	+ " with " + unitsMoving + " units.");
//     }

//     public boolean surrender(Player player) {
//         // boolean surrender = false;
//         System.out.println("Are you sure you wish to surrender? (y/n)");
//         // Scanner giveup = new Scanner(System.in);
//         key.nextLine();
//         String surr = key.nextLine();
//         try {
//             if (surr.equals("y")) {
//                 surrender = true;
//                 playerList.remove(player.getId() - 1);
//                 if (playerList.size() <= 1) {
//                     gameOver = true;
//                     log.updateLog("Player " + player.getId() + " has surrendered!");
//                 }
//             }
//             if (surr.equals("n")) {
//                 surrender = false;
//             }
//         } catch (InputMismatchException esurrender) {
//             System.out.println("Please enter a valid answer: (y/n)");
//             surr = key.nextLine();
//         }

//         return surrender;
//     }

//     public boolean numberCheck(String input) {
//         try {
//             Integer.parseInt(input);
//         } catch (NumberFormatException ex) {
//             return false;
//         }
//         return true;
//     }

//     public int askingForANumber(String input) {

//         while (!numberCheck(input)) {
//             System.out.println("Invalid number try again.");
//             input = key.next();
//         }
//         return Integer.parseInt(input);

//     }
// }
