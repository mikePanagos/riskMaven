package mojo;
import java.lang.reflect.Array;
import java.util.*;
import mojo.risk.*;

public class gui {
    GameEngine game = new GameEngine();
    Setup s = new Setup();
    Player player = new Player();
    Territory territory = new Territory();
	
	public void main(String[] args){
        boolean win = false;
        boolean lose = false;
		do{
            int counter = 0;
            setup();
            List<Player> playerlist = s.getPlayers();
			for(int x = 0; win!=true; x++){
                boolean endturn = false;
                boolean surrender = false;
				do{
                    System.out.println("Player " + playerlist.get(x) + "'s turn:\n");
					//User choice
					while(endturn != true) {
                        newunits(playerlist.get(x));
						System.out.println("Please Choose: \n1.Trade\n2.Attack\n3.Fortify\n4.Show Territories\n5.End turn\n6.Surrender");
						Scanner choose = new Scanner(System.in);
						int choice = choose.nextInt();
						try {
                            //game.trade();
                            if(choice == 1){
                                trade(counter, player);
                            }
                            
                            //game.attack();
                            if(choice == 2){}		
                            
                            //game.fortify();
                            if(choice == 3){}
                            
                            //display owned territories
                            if(choice == 4){}
                            
                            //endturn option
                            if(choice == 5){
                                endturn = true;
                            }
                            
                            //game surrender
                            if(choice == 6){
                                surrender = surrender();
                            }
                        } catch(InputMismatchException echoice){
                            System.out.println("Please enter a valid answer: (y/n)");
                            choice = choose.nextInt();
                        }
						choose.close(); // Closing Scanner to lower resource usage - Oscar
                    }
                    System.out.println("this is a test for: end of turn");
                    if(playerlist.size() == 1){
                        System.out.println("Player " + playerlist.get(x) + "wins!");
                        win = true;
                    }
                    endturn = true;
                }while(endturn != true);
                if(x == 6){
                    x = 0;
                }
            }
            System.out.println("This is a test for: Win/lose check");
        } while (win != true);
		
    }
    
    public int newunits(Player player){
        int units = player.getTerritoryCount()/3;
        if (units < 3){
            units = 3;
        }
        System.out.println("You are getting" + units + "units\n");
        System.out.println("Your territories:" + player.getTerritories() + "Where would you like to put them?");
        Scanner area = new Scanner(System.in);
        int put = area.nextInt();
        try {
            int total = put + territory.getNumOfUnits();
            territory.setNumOfUnits(total);
            System.out.println(territory.getNumOfUnits() + "place in territory" + player.getTerritories());
        } catch (InputMismatchException eplace){
            System.out.println("Please choose aw number in the territories available:" + territory.getNeighboringTerritories());
            put = area.nextInt();
        }
        area.close(); // Closing Scanner to lower resource usage - Oscar
        return units; // Previously not returning a type - Oscar
    }
    
    public void displayterritory(){
        return;
    }
    
    public String endturn(){
        return null;
    }
    
    public ArrayList<Integer> setup(){
        boolean playrange = false;
        System.out.println("Welcome to Risk!");
        System.out.println("How many players are there?");
        Scanner input = new Scanner(System.in);//Scan for input
        int numberofplayers = input.nextInt();
        do {
            try{
                if (numberofplayers <= 6 && numberofplayers >= 2){
                    s.setup(numberofplayers);
                    playrange = true; //if number is in range then continue
                } else{
                    System.out.println("Not in range. Please enter 2-6 players");
                    numberofplayers = input.nextInt();
                }
            }catch(InputMismatchException exception){ //catch non-number error
                System.out.println("Not a Number. Please enter 2-6 players: ");
                numberofplayers = input.nextInt();
            }
            input.close();
        }while (!(playrange)); //end loop when returned true
        ArrayList<Integer> Listofplayers = new ArrayList<Integer>(numberofplayers);
        List<Player> players = s.getPlayers();    
        List<Territory> territory = new ArrayList<Territory>(s.getTerritories());    
        s.setup(numberofplayers);
        Random rand = new Random();
        for(int temp = 1; temp <= numberofplayers; temp++){
            int x = rand.nextInt(numberofplayers+1); //return random int dependent on number of players
            if(Listofplayers.contains(x)){
                temp--; //if already in ArrayList then start over
            }else{
                if(x == 0){
                    temp--; //if already in ArrayList then start over
                }else{
                    Listofplayers.add(x); // if not in ArrayList then put in "first"
                }
            }
        }
        for(int i=0;i<=numberofplayers;i++){
            player.setArmiesCount(s.numUnitAtStart(i));
        }  
        System.out.println("The order of players is:");
        for(int i = 0; i < Listofplayers.size(); i++){
            System.out.println("Player " + Listofplayers.get(i)); //print player order
        }
        return Listofplayers;
    }
    
    public boolean trade(int counter, Player player){
        boolean trade = false;
        String tra;
        if(player.getCardCount() >= 3){
            do{
                System.out.println("Would you like to trade a set of 3 cards?");
                Scanner tradecard = new Scanner(System.in);
                tra = tradecard.nextLine();
                try{
                    if(tra == "y"){
                        counter++;
                        game.trade(counter, player);
                        trade = true;
                    }
                    if(tra == "n"){
                        trade = true;
                    }
                }catch(InputMismatchException etrade){ //catch non-number error
                    System.out.println("Not a Number. Please enter 2-6 players: ");
                    tra = tradecard.nextLine();
                }
                tradecard.close();
            }while(trade != true);
        }
        else{
            System.out.println("You do not have enough cards.");
            return trade;
        }
        return trade;
    }
    
    public String attack(){
        do{
            boolean attack = false;
            String attackagain;
            System.out.println("Where do you want to attack from?"); //+ player.getterritorythatcanattack);
            Scanner attfrom = new Scanner(System.in);
            int attackfrom = attfrom.nextInt();
            System.out.println("Where do you want to attack?"+ territory.getNeighboringTerritories(player.getTerritories(attackfrom)));
            Scanner attto = new Scanner(System.in);
            int attackto = attto.nextInt();
            game.attack(attackfrom, attackto);
            System.out.println("Do you want to attack again? (y/n)");
            Scanner attagain = new Scanner(System.in);
            attackagain = attagain.nextLine();
            try{
                if(attackagain == "y"){
                    attack = false;
                }
                if(attackagain == "n"){
                    attack = true;
                }
            }catch(InputMismatchException eattack){
                System.out.println("Please enter a valid answer (y/n)");
                attackagain = attagain.nextLine();
            }
            atto.close();
            attagain.close();
        }while(attack != true);
    }
    
    public String fortify(){
        boolean fortify = false;
        do{ 
            System.out.println("Which territory do you want to move units from?\n");// + territory.getNeighboringTerritories());
            Scanner from = new Scanner(System.in);
            int movefrom = from.nextInt();
            try{
                System.out.println("Which territory are you moving to?\n" + territory.getNeighboringTerritories());
                Scanner to = new Scanner(System.in);
                int moveto = to.nextInt();
                int territryUnit = territory.getNumOfUnits();
                try{
                    if( territryUnit> 1){
                        System.out.println("How many units would you like to move?\n Total: " + (territryUnit-1));
                        Scanner unitmove = new Scanner(System.in);
                        int troops = unitmove.nextInt();
                        game.fortify(movefrom, moveto, unitmove);
                        System.out.println("Do you want to fortify again? (y/n)\n");
                        Scanner fortifyagain = new Scanner(System.in);
                        String fortnite = fortifyagain.nextLine();
                        try{
                            if(fortnite == "y")
                            fortify = false;
                            if(fortnite == "n")
                            fortify = true;
                        }catch(InputMismatchException efortnight){
                            System.out.println("Please enter a valid answer (y/n)");
                            String fortnight = fortifyagain.nextLine();
                        }
                        unitmove.close();
                        fortifyagain.close();
                    }
                    else{
                        System.out.println("PLease choose a valid number");
                        fortify = false;
                    }
                }catch(InputMismatchException emoveto){
                    System.out.println("Please choose a number in the territories available:" + territory.getNeighboringTerritories());
                    moveto = to.nextInt();
                }
                to.close();
            }catch(InputMismatchException emovefrom){
                System.out.println("Please choose a number in the territories available:" + territory.getNeighboringTerritories());
                movefrom = from.nextInt();
            }
            from.close();
        }while(fortify != true);
        return "Hello";
    }
    
    public boolean surrender(){
        boolean surrender = false;
        System.out.println("Are you sure you wish to surrender? (y/n)");
        Scanner giveup = new Scanner(System.in);
        String surr = giveup.nextLine();
        try{
            if(surr == "y"){
                surrender = true;
            }
            if(surr == "n"){
                surrender = false;
            }
        }catch(InputMismatchException esurrender){
            System.out.println("Please enter a valid answer: (y/n)");
            surr = giveup.nextLine();
        }
        giveup.close();
        return surrender;
    }
}