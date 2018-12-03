package mojo.bot;

import java.util.*;

import mojo.GameEngine;
import mojo.Setup;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.generics.LongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import mojo.risk.*;
import mojo.proxy.*;
import mojo.twitter.TwitterClient;

/*
 * This class will be responsible for connecting to Telegram. It will take the player's
 * commands and execute them in game.
 */
public class RiskyBot extends TelegramLongPollingBot {
    List<Long> ids = new ArrayList<>();
    List<Player> playersList = new ArrayList<>();
    String token = System.getenv("telegramToken");
    int[][] price = { { 5, 1 }, { 10, 2 }, { 25, 5 }, { 75, 7 }, { 100, 10 }, { 1000, 100 } };
    PaymentInterface proxy = new PaymentProxy(price);
    Player theCurrentPlayer = null;
    boolean started = false; // Has the game been signaled to start? This will only turn true if everyone's
                             // ready

    // Risk Core
    GameEngine game = GameEngine.getInit();
    Setup setup = Setup.getInstances();

    // Timer used for timing out a players actions.
    Timer time = new Timer();

    // TwitterClient used to signal start of the game
    TwitterClient twitterClient = new TwitterClient();

    boolean done;

    /**
     * This function checks the message and determines the action to take. If the
     * action is legitimate a.k.a. the player is allowed to make that move it will
     * do so on the players behalf.
     * 
     * @param id      the players chat id
     * @param message the message returned by the player
     * @return the message to send back to the player
     */

    public String checkMessage(long id, String message) {
        int count = 3; // Hard coded due to requirement
        Player player = null; // Create a player object to reference

        for (int i = 0; i < playersList.size(); i++) {
            if (playersList.get(i).getId() == id) {
                player = playersList.get(i);
                break;
            }
        }

        if (player == null) {
            System.out.println("The player id was not found in the player list.");
        }

        String returnMess = null;
        if (ids.contains(id)) {

            if (ids.size() == count) {
                // Log the total amount of players to the console. This helps to debug problems.
                System.out.println("The total amount of players is: " + ids.size());
                if (!started && message.equals("ready")) {
                    player.ready = true;
                    // Check if everyone is ready.
                    // If one person is not ready then stop checking if anyone else is not ready.
                    // We need to only know if one person is not ready to wait for them
                    for (int i = 0; i < playersList.size(); i++) {
                        if (!(started = playersList.get(i).ready)) {
                            break;
                        }
                    }

                    if (started) {
                        /*
                         * We only want to initialize the game if the game hasn't been already. A
                         * scenario may include the following. 1. We receive a message. The game has
                         * 'started' (the boolean is already set to true) 2. We do not want to
                         * initialize the game every single time we receive message. 3. To avoid this,
                         * we can check if the game has already been setup.
                         */

                        Setup.setupPlayerWithList(playersList);
                        playersList = setup.getPlayers();

                        returnMess = "The game's starting...prepare for battle. Leeeerrrroooyy Jenkinssssss!";
                        twitterClient.setTweet(returnMess);
                        twitterClient.postTweet();
                        for (int i = 0; i < playersList.size(); i++) {
                            // Send the starting player a message stating that they're first in queue
                            if (playersList.get(i).getItsMyTurn()) {
                                theCurrentPlayer = playersList.get(i);
                                notifyPlayer(playersList.get(i).getId(), "You've been selected to be first."
                                        + " Submit 'menu' when the game starts to view your options.");
                            } else {
                                if (playersList.get(i).getId() != id)
                                    notifyPlayer(playersList.get(i).getId(), returnMess);
                            }

                        }
                    }

                    // If the game is not ready set the message reply to alert the player that we're
                    // still waiting on people
                    // to confirm that they're ready.
                    else {
                        returnMess = "We're waiting on other players to get ready";
                    }

                } else if (started) {
                    if (game == null)
                        System.out.println("Game was not initialized!");
                    if (setup == null)
                        System.out.println("Setup was not initialized!");

                    if (player.getItsMyTurn()) {
                        String move = player.getSelectedMove();
                        String[] command = move.split("\\s+");
                        System.out.println("Player " + id + "'s command:");
                        for (String s : command) {
                            System.out.println(s);
                        }
                        move = command[0];
                        System.out.println("move is " + move);

                        returnMess = controller(move, player, message);
                    } else {
                        returnMess = "Hold your horses! It's not your turn yet. We'll let you know when you can get back into the action.";
                    }
                } else {
                    // TODO: Rewrite this portion since it only returns for player's who are in but
                    // not ready.
                    returnMess = "We support the following commands.\n" + "attack\n" + "fortify\n" + "quit";
                }
            }

            else if (ids.size() > count) {
                returnMess = "Woah! There's too many players in here.";
            }

            else {
                returnMess = "We're waiting on more players to join!";
            }
        }

        else {

            if (message.equals("1234") && !started) {
                ids.add(id);
                playersList.add(new Player(id));
                System.out.println("Added player " + id);
                returnMess = "You're in!";
            } else if (message.equals("1234") && started) {
                System.out.println("Player #" + id + " tried to enter the game, but it's already in progress.");
                returnMess += "Sorry, this game is already in progress.";
            } else {
                System.out.println("Player #" + id + " entered the wrong password.");
                returnMess = "Sorry, if you want to enter a game you need to give me the games id.";
            }
        }

        return returnMess;
    }

    @Override
    public void onUpdateReceived(Update update) {

        // We check if the update has a message and the message has text
        if (update.hasMessage() && update.getMessage().hasText()) {
            // Set variables
            String message_text = update.getMessage().getText();
            message_text = message_text.toLowerCase();
            long chat_id = update.getMessage().getChatId();
            String response = "";
            if (started && chat_id == theCurrentPlayer.getId()) {
                stopTimer();
                startTimer(30);
            }
            // String name= update.getMessage().getName();
            // System.out.println("name is"+name);
            // This part might need to be refactored.
            if (message_text.equals("hello")) {
                if (!ids.contains(chat_id)) {
                    response = "Hello. To enter a game, type in the game id. Example: 1234";
                } else if (ids.contains(chat_id) && ids.size() < 3) {
                    response = "Sit tight. There are currently " + ids.size() + " players waiting in the lobby.";
                } else {
                    // Should never happen! Either they're in the game or they're not.
                    response = "Oops! Something went wrong. We're sorry :(";
                }
            } else if (message_text.equals("players")) {
                response = "Current players are:\n";
                for (int i = 0; i < playersList.size(); i++) {
                    response += "Player #" + playersList.get(i).getId() + "\n";
                }

            } else {
                response = checkMessage(chat_id, message_text);
            }

            // REFACTORING: Created this method to make it easier to reuse the
            // reply functionality. Replaces code below it.
            notifyPlayer(chat_id, response);
            // stopTimer();
            // Remove all players who have quit
            
            // SendMessage message = new SendMessage() // Create a message object object
            // .setChatId(chat_id).setText(response);
            // try {
            // execute(message); // Sending our message object to user
            // } catch (TelegramApiException e) {
            // e.printStackTrace();
            // }
        }
    }

    @Override
    public String getBotUsername() {
        // Return bot username
        // If bot username is @MyAmazingBot, it must return 'MyAmazingBot'
        return "RiskyBot";
    }

    @Override
    public String getBotToken() {
        // Return bot token from BotFather
        return token;
    }

    /**
     * The controller method will appropriately execute the actions within the game.
     * It will be used to generate the appropriate response from the bot to the
     * player.
     * 
     * @param move   The last indicated move by the player. It is essentially used
     *               to keep track of the player's state.
     * @param player The player who is executing the move.
     * @return the message that the bot will return to the player in response to the
     *         action taken
     */
    public String controller(String move, Player player, String command) {
        String returnMess = null;
        String[] args = command.split("\\s+"); // Split command into arguments
        move = args[0];
        if (game.initialPhase(player)) {
            if (player.getNotifications().size() > 0) {
                // Read notifications one by one to the player.
                String notifications = "Here is the breakdown of what happened outside of your turn.\n";
                for (int i = 0; i < player.getNotifications().size(); i++) {
                    notifications += "Notification " + (i + 1) + ":\n";
                    notifications += player.getNotifications().get(i).getMessage() + "\n";
                }
                player.getNotifications().clear(); // Clear all notifications once they have been read to the player
                notifications += "-------------------\n";
                // Notify the player with the breakdown of attacks.
                notifyPlayer(player.getId(), notifications);
            }
            if (player.getArmiesCount() > 0 && !move.equals("distribute")) {
                returnMess = "You must allocate your new units. You have a total of : " + player.getArmiesCount()
                        + "\n";
                returnMess += "How would you like to distribute them?\n";
                returnMess += "Example: distribute <units> <territory>\n";
                returnMess += "<units - amount of units you would like to place\n";
                returnMess += "<territory> - the name of the territory destination\n";
                returnMess += "Here is a list of your territories:\n";
                returnMess += player.printTerritoriesVerbose() + "\n";
            } else if (player.getArmiesCount() > 0 && move.equals("distribute") && args.length > 2) {
                if (GameEngine.verifyCommand(move, args, player)) {
                    game.allocateUnits(args, player);
                    returnMess = "We have allocated your units! :)\n";
                    returnMess += "Here is a list of your territories:\n";
                    returnMess += player.printTerritoriesVerbose() + "\n";
                } else {
                    returnMess = "We had an issue allocating your units.\n";
                }
            } else {
                returnMess = "An error occurred allocating your armies sorry :(\n";
            }
        } else {
            switch (move) {
            case "menu":
                returnMess = "What would you like to do? Submit one of the following options to proceed.\n";
                returnMess += "For example, if you would like to choose the first one, you would submit 'attack'.\n";
                returnMess += "1. Attack\n";
                returnMess += "2. Fortify\n";
                returnMess += "3. Trade - Turn your cards into armies >:)\n";
                returnMess += "4. End\n";
                returnMess += "5. Surrender\n";
                returnMess += "6. Summary\n";
                returnMess += "7. buy\n";
                returnMess += "8. give\n";
                returnMess += "9. undo\n";
                returnMess += "10. example\n";
                returnMess += "Or Submit 'Menu' at anytime to view this menu again.\n";
                break;
            case "attack":
                if (args.length < 4) {
                    returnMess = "Here is the list of territories you can attack with:\n";
                    returnMess += player.printOffensiveTerritoriesVerbose() + "\n";
                    returnMess += "How would you like to proceed?\n";
                    returnMess += "Format: Attack yourTerritory targetTerritory unitCount\n";
                    returnMess += "youTerritory - The territory you want to attack with.\n";
                    returnMess += "targetTerritory - The territory you would like to target.\n";
                    returnMess += "unitCount - The amount of armies you would like to attack with.\n";
                } else {
                    System.out.println("Player #" + player.getId() + " has chosen to attack!");

                    if (GameEngine.verifyCommand(move, args, player)) {
                        Territory attackingTerr = null, defendingTerr = null;
                        int units = Integer.parseInt(args[3]);

                        for (Territory t : player.getTerritoryList()) {
                            if (t.getName().toLowerCase().equals(args[1])) {
                                attackingTerr = t;
                                break;
                            }
                        }

                        for (Territory t : attackingTerr.getSurroundingEnemies()) {
                            if (t.getName().toLowerCase().equals(args[2])) {
                                defendingTerr = t;
                                break;
                            }
                        }

                        // Execute attack
                        game.attack(attackingTerr, defendingTerr, units);

                        // Notify player attacked.
                        Player recepient = null;
                        for (Player p : playersList) {
                            if (p.getId() == defendingTerr.getOwner()) {
                                System.out.println("Found recepient.");
                                recepient = p;
                            }
                        }

                        notifyPlayer(defendingTerr.getOwner(), recepient.printNotifications());

                        // Send a summary of the players territories after the attack
                        returnMess = "Results\n";
                        returnMess += "-----------------------------\n";
                        returnMess += "Attacking Territory\n";
                        returnMess += "Name: " + attackingTerr.getName() + "\n";
                        returnMess += "Units: " + attackingTerr.getNumOfUnits() + "\n";
                        returnMess += "Defending Territory\n";
                        returnMess += "Name: " + defendingTerr.getName() + "\n";
                        returnMess += "Units: " + defendingTerr.getNumOfUnits() + "\n";
                    } else {
                        returnMess = "Your command could not be executed.\n";
                    }
                }
                break;
            case "fortify":
                if (args.length < 4) {
                    returnMess = "Here is the list of territories you own. Fortification has the following requirements:\n";
                    returnMess += "1. The helping territory must have more than one unit before AND after fortification.\n";
                    returnMess += "2. The helping territory must be adjacent to the territory you wish to fortify.\n";
                    returnMess += player.printFortifyTerritories() + "\n";
                    returnMess += "How would you like to proceed?\n";
                } else {
                    if (GameEngine.verifyCommand(move, args, player)) {
                        Territory terrA = null, terrB = null;
                        Integer units = Integer.parseInt(args[3]);
                        // Used to reference the two territories needed for the fortify process
                        // We use the for each loop below to find the given territories
                        for (Territory t : player.getTerritoryList()) {
                            if (t.getName().toLowerCase().equals(args[1])) {
                                terrA = t;
                            }
                            if (t.getName().toLowerCase().equals(args[2])) {
                                terrB = t;
                            }
                        }
                        // Execute fortify command.
                        game.fortify(terrA, terrB, units);

                        returnMess = "We have fortified the territory selected.";
                        returnMess += "Here is the new summary of the territories you own.\n";
                        returnMess += player.printTerritoriesVerbose() + "\n";
                    } else {
                        returnMess = "Your command could not be executed.\n";
                    }
                }
                break;
            case "trade":
                /*
                 * So we need to keep track of which set this is that is being traded in. At
                 * this time it just checks to make sure that you have three cards. This is also
                 * supposed to happen at the beginning of the player's turn but we will leave it
                 * here for now.
                 */
                if (player.getCardCount() < 3) {
                    returnMess = "Sorry you don't have enough cards to perform this action.\n";
                } else {
                    // Perform trade
                    returnMess = game.trade(player); // Trade the cards
                }
                break;
            case "end":

                returnMess = "You have ended your turn.";
                player.setSelectedMove("menu");
                player.setItsMyTurn(false);
                game.endingPhase(player);
                notifyNextPlayer(player.getId()); // Notify the next player it's their turn
                break;
            case "surrender":
                returnMess = "Sorry to see you go. Better luck next time!";
                notifyPlayer(player.getId(), returnMess);
                player.quit = true;
                for (Player p : playersList) {
                    if (p.quit) {
                        playersList.remove(p);
                    }
                }
                notifyNextPlayer(player.getId());
               
                break;
            case "summary":
                returnMess = "Here is a breakdown of you currently owned territories:\n";
                returnMess += player.printTerritoriesVerbose() + "\n";
                returnMess += "You currently own " + player.getContinentCount() + " Continents.\n";
                returnMess += "You currently have: " + player.getCardCount() + " Cards.\n";
                break;
            case "undo":
                if (player.getCredit() < 50) {
                    returnMess = "undos cost 50 credits and you have" + player.getCredit()
                            + " you cant buy an undo but you can buy more credits\n";
                } else {
                    returnMess = "You have enough for a undo to buy it type Buy undo\n";
                }

                break;
            case "buy":
                if (args.length < 2) {
                    returnMess = proxy.getPriceForCredits();
                    returnMess += "to buy an undo type\n\t buy undo\nto buy a credits type:\n\tbuy credits <option>\n";
                } else {
                    if (args[1].equals("undo")) {
                        if (player.getCredit() >= 50) {
                            if (proxy.buyUndo(player, 1)) {
                                returnMess = "last thing done is how undone";
                            } else {
                                returnMess = "sorry something went wrong";
                            }
                        } else {
                            returnMess = "sorry you dont have enough credits you need to buy more";
                        }
                    } else if (args[1].equals("credits")) {
                        player.setCredit(player.getCredit() + proxy.buyCredits(Integer.parseInt(args[2]), player));
                        returnMess = "you know have " + player.getCredit() + " credits";
                    }

                }

                break;
            case "give":
                if (args.length < 2) {
                    returnMess += "to give credits type\n\tgive <playerid> <amount>\n players you can give to are\n";

                    for (Player p : playersList) {
                        if (p.getId() != player.getId()) {
                            returnMess += p.getId() + " \n";
                        }
                    }
                } else if (args.length >= 2) {
                    if (Integer.parseInt(args[2]) < player.getCredit()) {
                        for (Player p : playersList) {
                            if (p.getId() == Integer.parseInt(args[1])) {
                                if (proxy.giveCreditsTo(player, p, Integer.parseInt(args[2]))) {
                                    returnMess = "you gave them " + args[2] + " credits";
                                }
                            }
                        }
                    } else {
                        returnMess = "sorry you cant give that much credits you only have" + player.getCredit();
                    }
                }

                break;
                case"example":
                        returnMess="attack <attacking territory> <deffending territory>  <amount of units>\n Fortify <from territory> <to territory> <amount moving>\n ";
            default:
                returnMess = "Invalid Option!\n";
                returnMess += "What would you like to do? Submit one of the following options to proceed.\n";
                returnMess += "For example, if you would like to choose the first one, you would submit 'attack'.\n";
                returnMess += "1. Attack\n";
                returnMess += "2. Fortify\n";
                returnMess += "3. Trade - Turn your cards into armies >:)\n";
                returnMess += "4. End\n";
                returnMess += "5. Surrender\n";
                returnMess += "6. Summary\n";
                returnMess += "Or Submit 'Menu' at anytime to view this menu again.\n";
                break;
            }
        }
        return returnMess;

    }

    /**
     * This will send a message to a player's telegram chat.
     * 
     * @param id      the chat/player id to notify
     * @param content the message content
     */
    // This method will reduce the amount of times that we have to write the code to
    // notify a player.
    public void notifyPlayer(long id, String content) {
        /*
         * If the content length is greater than 4000 split it up into multiple
         * messages. This loop will exit out after the content length is 4000 or less
         * meaning that we must still send one last message at the end with the last
         * part of the text.
         */
        while (content.length() > 4000) {
            String text = content.substring(0, 4000);
            SendMessage message = new SendMessage();
            message.setChatId(id).setText(text);
            try {
                execute(message);
                System.out.println("Player #" + id + " alerted with message:");
                System.out.println(content);
                content = content.substring(4000);
            } catch (TelegramApiException e) {
                System.out.println("The string is " + content.length() + " characters long.");
                e.printStackTrace();
            }
        }

        SendMessage message = new SendMessage();
        message.setChatId(id).setText(content);
        try {
            execute(message);
            System.out.println("Player #" + id + " alerted with message:");
            System.out.println(content);
        } catch (TelegramApiException e) {
            System.out.println("The string is " + content.length() + " characters long.");
            e.printStackTrace();
        }

    }

    public void notifyNextPlayer(long currentId) {
        Player nextPlayer = null;
        if (playersList.size() >= 2) {// 'i' is the index for the player list
            for (int i = 0; i < playersList.size(); i++) {
                if (playersList.get(i).getId() == currentId) {
                    // We need to find the next player
                    if (i == playersList.size() - 1) {
                        nextPlayer = playersList.get(0); // If the last person in the queue went, start from the front
                                                         // again
                        // We do this to prevent index out of bounds exceptions from being thrown
                    } else {
                        nextPlayer = playersList.get(i + 1); // If not, get the next person up
                    }
                    break;
                }
            }
            nextPlayer.setItsMyTurn(true); // Set the next persons turn to be true
            // notifyPlayer(currentId,"times up sorry you took too long.");
            theCurrentPlayer = nextPlayer;
            nextPlayer.setSelectedMove("menu"); // Set their default action to be the menu
            // startTimer(30);
            // Update Twitter
            twitterClient.setTweet("It is now Player #" + currentId + "'s turn.");
            twitterClient.postTweet();
            notifyPlayer(nextPlayer.getId(), "It is now your turn! Send 'menu' to view your options.");
        }else{
            twitterClient.setTweet("Player " + playersList.get(0).getId() + " WINS");
            twitterClient.postTweet();
            notifyPlayer(playersList.get(0).getId(), "Player " + playersList.get(0).getId() + " WINS");
        }
       
    }

    public void startTimer(int seconds) {
        done = false;
        // time = new Timer();
        System.out.println(("about to start timer"));
        time.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("Time's up!");
                done = true;
                // time=new Timer();
                // currentPlayer.setItsMyTurn(false);
                notifyNextPlayer(theCurrentPlayer.getId());
                time.cancel(); // Terminate the timer thread
                time = new Timer();

            }
        }, seconds * 1000);
    }

    public void stopTimer() {
        time.cancel();
        time = new Timer();

    }
}
