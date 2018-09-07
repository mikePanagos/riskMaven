package mojo;
import java.util.*;
import risk.*;

// public class gui {
// 	static boolean win = false;
// 	static boolean lose = false;
// 	// static Territory territory;
// 	static GameEngine game;
// 	// static Player player;
// 	Setup s = new Setup();

// 	List <Player> players=new ArrayList<>(s.getPlayers());
	
// 	public void displayTerritory(int player){
// 		System.out.println("This is a test for: displayTerritory");
// 	}
	
// 	public static void main(String[] args){
// 		boolean playrange = false;
// 		System.out.println("Welcome to Risk!");
// 		System.out.println("How many players are there?");
// 		Scanner input = new Scanner(System.in);//Scan for input
// 		int numberofplayers = input.nextInt();
// 		do {
// 			try{
// 				if (numberofplayers <= 6 && numberofplayers >= 2){
// 					playrange = true; //if number is in range then continue
// 				} else{
// 					System.out.println("Not in range. Please enter 2-6 players");
// 					numberofplayers = input.nextInt();
// 				}
// 			}catch(InputMismatchException exception){ //catch non-number error
// 				System.out.println("Not a Number. Please enter 2-6 players: ");
// 				numberofplayers = input.nextInt();
// 			}
// 		}while (!(playrange)); //end loop when returned true
		
// 		//Listofplayers.get(i).addTerritory(territoryOBJ);
// 	    //territoryOBJ.setOwner(Listofplayers.get(i).getName());
// 		//setup.getterritories(numberofplayers); //Assign players territories
// 		//setup.giveunits(numberofplayers); //Give players starting units
// 		Random rand = new Random();
// 		 ArrayList<Integer> Listofplayers = new ArrayList<Integer>(numberofplayers);
// 		for(int i = 1; i <= numberofplayers; i++){
// 			int x = rand.nextInt(numberofplayers+1); //return random int dependent on number of players
// 			if(Listofplayers.contains(x)){
// 				i--; //if already in ArrayList then start over
// 			}else{
// 				if(x == 0){
// 					i--; //if already in ArrayList then start over
// 				}else{
// 					Listofplayers.add(x); // if not in ArrayList then put in "first"
// 				}
// 			}
// 		}
// 		System.out.println("The order of players is:");
// 		for(int i = 0; i < Listofplayers.size(); i++)
// 			System.out.println("Player " + Listofplayers.get(i)); //print player order
		
// 		do{
// 			for(int x = 0; win!=true; x++){
// 				//Setup s = new Setup();
// 				//s.setup(numberofplayers);
// 				//s.getPlayers();
// 				//List<Player> players = new ArrayList(s.getPlayers());
// 				//List<String> territory= new ArrayList( s.getTerritoryForDisplay());		    
// 				//s.getTerritoryList() will return a list of all territory as a string for  
// 				//List<Territory> territory= new ArrayList( s.getTerritoryList());  
// 				//List<Card> deck =new ArrayList(s.getDecK()); // get the deck
				
				
// 				/*
// 				    for(int i=0;i<Listofplayers.size();i++){
// 					    //display all the name of all territories  
// 					    String onScreen = displayTerritory()//make this method in this class
// 					    //display it and ask the user for a number then add it to player territory
// 					    //get the territory form TerritoryList jsut go through it until it finds it make this into sep method later
// 				    }
// 				*/
				    
				    
// 				    //go through arraylist make it into one string with a number infront
// 				    //return 
// 				    // and territory owner
// 				    //Listofplayer.get(i).addTerritory(territoryOBJ);
// 				    //territoryOBJ.setOwner(playerorder.get(i).getName());
// 				int counter = 0;
// 				do{
// 					boolean surrender = false;
// 					boolean trade = false;
// 					boolean attack = false;
// 					boolean fortify = false;
// 					boolean endturn = false;
// 					boolean endturn2 = false;
// 					System.out.println("Player " + Listofplayers.get(x) + "'s turn:\n");
					
// 					//recieve units and cards
// 					//look for new units to be given
// 					int units = player.getTerritoryCount()/3;
// 					if(units < 3){
// 						units = 3;
// 					}
// 					System.out.println("You are getting" +  "units\n");
// 					System.out.println("Your territories:" + player.getTerritories() + "Where would you like to put them?");
// 					Scanner area = new Scanner(System.in);
// 					int put = area.nextInt();
// 					try{
// 						int total = put + territory.getNumOfUnits();
// 						territory.setNumOfUnits(total);
// 						System.out.println(territory.getNumOfUnits() + "place in territory" + player.getTerritories());
// 					}catch(InputMismatchException eplace){
// 					    System.out.println("Please choose a number in the territories available:" + territory.getNeighboringTerritories());
// 					    put = area.nextInt();
// 						}
					
// 					//User choice
// 					while(endturn != true){
// 						System.out.println("Please Choose: \n1.Trade\n2. Attack\n3.Fortify\n4.End turn\n5.Surrender");
// 						Scanner choose = new Scanner(System.in);
// 						int choice = input.nextInt();
// 						try{
// 						//game.trade();
// 						if(choice == 1){
// 							if(player.getCardCount() >= 3){
// 								do{
// 									 trade = false;
// 									System.out.println("Would you like to trade a set of 3 cards?");
// 									Scanner tradecard = new Scanner(System.in);
// 									String tradea = tradecard.nextLine();
// 									try{
// 										if(tradea == "y"){
// 											counter++;
// 											game.trade(counter);
// 											trade = true;
// 										}
// 										if(trades == "n"){
// 											trade = true;
// 										}
// 									}catch(InputMismatchException etrade){
// 										System.out.println("Please enter a valid answer.\nWould you like to trade a set of 3 cards?");
// 										String tradea = tradecard.nextLine();
// 									}
// 								}while(trade != true);
// 							}
// 						}
						
// 						//game.attack();
// 						if(choice == 2){
// 							do{
// 								boolean attack = false;
// 								System.out.println("Where do you want to attack from?"); //+ player.getterritorythatcanattack);
// 								Scanner attfrom = new Scanner(System.in);
// 								int attackfrom = attfrom.nextInt();
// 								System.out.println("Where do you want to attack?"+ territory.getNeighboringTerritories(player.getTerritories(attackfrom)));
// 								Scanner attto = new Scanner(System.in);
// 								int attackto = attto.nextInt();
// 								game.attack(attackfrom, attackto);
// 								System.out.println("Do you want to attack again? (y/n)");
// 								Scanner attagain = new Scanner(System.in);
// 								String attackagain = attagain.nextLine();
// 								try{
// 									if(attackagain == "y"){
// 										attack = false;
// 									}
// 									if(attackagain == "n"){
// 										attack = true;
// 									}
// 								}catch(InputMismatchException eattack){
// 									System.out.println("Please enter a valid answer (y/n)");
// 									String attackagain = attagain.nextLine();
// 								}
// 							}while(attack != true);
// 						}		
						
// 						//game.fortify();
// 						if(choice == 3){
// 							do{
// 								 fortify = false;
// 								System.out.println("Which territory do you want to move units from?\n");// + territory.getNeighboringTerritories());
// 								Scanner from = new Scanner(System.in);
// 								int movefrom = from.nextInt();
// 								try{
// 									System.out.println("Which territory are you moving to?\n" + territory.getNeighboringTerritories());
// 									Scanner to = new Scanner(System.in);
// 									int moveto = to.nextInt();
// 									int territryUnit=territory.getNumOfUnits();
// 									try{
// 										if( territryUnit> 1){
// 											System.out.println("How many units would you like to move?\n Total: " + (territryUnit-1));
// 											Scanner unitmove = new Scanner(System.in);
// 											int troops = unitmove.nextInt();
// 											game.fortify(movefrom, moveto, unitmove);
// 											System.out.println("Do you want to fortify again? (y/n)\n");
// 											Scanner fortifyagain = new Scanner(System.in);
// 											String fortnite = fortifyagain.nextLine();
// 											try{
// 												if(fornite = "y")
// 													fortify = false;
// 												if(fortnite = "n")
// 													fortify = true;
// 											}catch(InputMismatchException efortnight){
// 												System.out.println("Please enter a valid answer (y/n)");
// 												String fortnight = fortifying.nextLine();
// 											}
// 										}
// 										else{
// 											System.out.println("PLease choose a valid number");
// 											fortify = false;
// 										}
// 									}catch(InputMismatchException emoveto){
// 									    System.out.println("Please choose a number in the territories available:" + territory.getNeighboringTerritories());
// 									    moveto = to.nextInt();
// 									}
// 								}catch(InputMismatchException emovefrom){
// 								    System.out.println("Please choose a number in the territories available:" + territory.getNeighboringTerritories());
// 								    movefrom = from.nextInt();
// 								}
// 							}while(fortify != true);
// 						}
// 						if(choice == 4){
// 							endturn = true;
// 						}
						
// 						//game surrender
// 						if(choice == 5){
// 							System.out.println("Are you sure you wish to surrender? (y/n)");
// 							Scanner giveup = new Scanner(System.in);
// 							String surr = giveup.nextLine();
// 							try{
// 								if(surr == "y")
// 									surrender = true;
// 								if(surr == "n")
// 									surrender = false;
// 							}catch(InputMismatchException esurrender){
// 								System.out.println("Please enter a valid answer: (y/n)");
// 								surr = giveup.nextLine();
// 							}
// 						}
// 					}catch(InputMismatchException echoice){
// 						System.out.println("Please enter a valid answer: (y/n)");
// 						choice = input.nextInt();
// 					}
// 					}
					
// 					int losecount = player.getTerritoryCount();
// 					if(losecount == 0){
// 						lose = true;
// 					}
// 					if(surrender = true){
// 						System.out.println("Player" + Listofplayers.get(x) + " has surrendered!");
// 						endturn = true;
// 					}
// 					System.out.println("this is a test for: end of turn");
// 					endturn2 = true;
// 				}while(endturn2 != true);
// 				Listofplayers.remove(x);
// 				System.out.println("This is a test for: Win/lose check");
// 			}
// 		}while(win != true);
		
// 	}


// 	public void setup(){

// 	}
// 	public void turns(){

// 	}
// }
