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


/*
 * This class will be responsible for connecting to Telegram. It will take the player's
 * commands and execute them in game.
 */
public class RiskyBot extends TelegramLongPollingBot {
    int gameID = 0;
    List<Long> ids = new ArrayList<>();
    List<Player> playersList= new ArrayList<>();
    String token = System.getenv("telegramToken");
    boolean started = false; // Has the game been signaled to start? This will only turn true if everyone's ready

    GameEngine game = null;
    Setup setup = null;


    /**
     * This function checks the message and determines the action to take. If the action is legitimate a.k.a.
     * the player is allowed to make that move it will do so on the players behalf.
     * @param id the players chat id
     * @param message the message returned by the player
     * @return the message to send back to the player
     */
    private String checkMessage(long id, String message) {
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
//                    for (int i = 0; i < playersList.size(); i++) {
//                        if (playersList.get(i).getId() == id) {
//                            playersList.get(i).ready = true;
//                        }
//                    }
                    try {
                        player.ready = true;
                        for (int i = 0; i < playersList.size(); i++) {
                            if (playersList.get(i).getId() == id) {
                                playersList.get(i).ready = true;
                                break;
                            }
                        }
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
                                We only want to initialize the game if the game hasn't been already.
                                A scenario may include the following.
                                1. We receive a message. The game has 'started' (the boolean is already set to true)
                                2. We do not want to initialize the game every single time we receive message.
                                3. To avoid this, we can check if the game has already been setup.
                             */
                            if ( game == null && setup == null ) {
                                game = GameEngine.getInit();
                                setup = Setup.getInstances();
                                Setup.setupPlayerWithList(playersList);
                            }

                            returnMess = "The game's starting...prepare for battle. Leeeerrrroooyy Jenkinssssss!";
                            for (int i = 0; i < playersList.size(); i++) {
                                if (playersList.get(i).getId() == id)
                                    continue;
                                SendMessage confirmation = new SendMessage() // Create a message object object
                                        .setChatId(playersList.get(i).getId()).setText(returnMess);
                                try {
                                    execute(confirmation); // Sending our message object to user
                                } catch (TelegramApiException e) {
                                    e.printStackTrace();
                                }
                            }
                        }

                        // If the game is not ready set the message reply to alert the player that we're still waiting on people
                        // to confirm that they're ready.
                        else {
                            returnMess = "We're waiting on other players to get ready";
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        returnMess = "Whoops an error occurred. Grab an adult...";
                    }
                }
                else if (started) {
                    if (game == null)
                        System.out.println("Game was not initialized!");
                    if (setup == null)
                        System.out.println("Setup was not initialized!");

                    if (player.getItsMyTurn()) {

                    } else {
                        returnMess = "Hold your horses! It's not your turn yet.";
                    }
                }
                else {
                    returnMess = "We support the following commands.\n" +
                            "attack\n" +
                            "fortify\n" +
                            "quit";
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

            if (message.equals("1234")) {
                ids.add(id);
                playersList.add(new Player(id));
                System.out.println("Added player " + id);
                returnMess = "You're in!";
            }
            else {
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
            } else if (message_text.equals("players")){
                response = "Current players are:\n";
                for (int i = 0; i < playersList.size(); i++) {
                    response+=playersList.get(i).getId()+"\n";
                }

            } else {
                response = checkMessage(chat_id, message_text);
            }

            SendMessage message = new SendMessage() // Create a message object object
                    .setChatId(chat_id).setText(response);
            try {
                execute(message); // Sending our message object to user
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
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

}
