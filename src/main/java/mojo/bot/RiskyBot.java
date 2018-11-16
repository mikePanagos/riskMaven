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
    
    private String checkMessage(long id, String message) {
        int count = 3;
        boolean started = false;
        Player player = new Player();

        for (int i = 0; i < playersList.size(); i++) {
            if (playersList.get(i).getId() == id) {
                player = playersList.get(i);
            }
        }

        String returnMess = "";
        if (ids.contains(id)) {
            if (ids.size() == count) {

                if (!started && message.equals("ready")) {
//                    for (int i = 0; i < playersList.size(); i++) {
//                        if (playersList.get(i).getId() == id) {
//                            playersList.get(i).ready = true;
//                        }
//                    }
                    try {
                        player.ready = true;
                        // Check if everyone is ready
                        for (int i = 0; i < playersList.size(); i++) {
                            if ((started = playersList.get(i).ready) == false) {
                                break;
                            }
                        }
                        if (started) {
                            GameEngine game = GameEngine.getInit();
                            Setup s = Setup.getInstances();
                            Setup.setupPlayerWithList(playersList);
                            returnMess = "The game's starting...prepare for battle. Leeeerrrroooyy Jenkinssssss!";
                            for (int i = 0; i < playersList.size(); i++) {
                                SendMessage confirmation = new SendMessage() // Create a message object object
                                        .setChatId((long)playersList.get(i).getId()).setText(returnMess);
                                try {
                                    execute(confirmation); // Sending our message object to user
                                } catch (TelegramApiException e) {
                                    e.printStackTrace();
                                }
                            }
                        } else {
                            returnMess = "We're waiting on other players to get ready";
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        returnMess = "Whoops an error occurred. Grab an adult...";
                    }
                }
                else if (started) {
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
            } else if (ids.size() > count) {
                returnMess = "Woah! There's too many players in here.";
            }
            else {
                returnMess = "We're waiting on more players to join!";
            }
        } else {
            if (message.equals("1234")) {
                ids.add(id);
                playersList.add(new Player((int)id));
                System.out.println("Added player " + id);
                returnMess = "You're in!";
            } else {
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
