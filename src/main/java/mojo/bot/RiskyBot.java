package mojo.bot;

import java.util.*;
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
        int count = 0;
        String returnMess = "";
        if (ids.size() >= 1) {
            for (int i = 0; i < ids.size(); i++) {
                if (ids.get(i) == id) {
                    returnMess = "we found you returning player " + message;
                    break;
                } else {
                    count++;

                }
            }
            if (count >= ids.size()) {
                if (message.equals("1234")) {
                    ids.add(id);
                    playersList.add(new Player((int)id));
                    System.out.println("added player " + id);
                    returnMess = "your in";
                } else {
                    returnMess = "sorry if you want to enter a game you need to give me the games id";
                }
            }
        } else {
            if (message.equals("1234")) {
                ids.add(id);
                playersList.add(new Player((int)id));
                System.out.println("added player " + id);
                returnMess = "your in";
            } else {
                returnMess = "sorry if you want to enter a game you need to give me the games id";
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
            long chat_id = update.getMessage().getChatId();
            String responce = "";
            // String name= update.getMessage().getName();
            // System.out.println("name is"+name);
            if (message_text.equals("hello")) {
                responce = "hello to enter a game you need to type that games id";
            }else if(message_text.equals("players")){
                for (int i = 0; i < playersList.size(); i++) {
                    responce+=playersList.get(i).getId()+"\n";
                }

            } else {
                responce = checkMessage(chat_id, message_text);
            }
            SendMessage message = new SendMessage() // Create a message object object
                    .setChatId(chat_id).setText(responce);
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
