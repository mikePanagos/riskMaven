package mojo.bot;

import java.net.*;
import java.io.*;
import org.json.*;
import javax.net.*;
import javax.net.ssl.HttpsURLConnection;

/*
 * This class will be responsible for connecting to Telegram. It will take the player's
 * commands and execute them in game.
 */
public class RiskyBot {
    int gameID = 0;
    String token = System.getenv("telegramToken");
//    URL sendMessage = new URL("https://api.telegram.org/bot796705800:AAG4coiT3xqeHmbTfLbPRvMo5K6hEOzzipA/sendMessage?");

    public RiskyBot() {
        this.gameID = 1;
    }

    public void getUpdates() {
        try {
            URL getUpdates = new URL("https://api.telegram.org/bot" + token + "/getupdates");
            HttpsURLConnection apiRequest = (HttpsURLConnection)getUpdates.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    apiRequest.getInputStream()
            ));
            String currentLine = "";
            String response = "";
            while ((currentLine = in.readLine()) != null) {
                // No need to print out anything.
                response = response + currentLine;
            }
            //System.out.println(response);
            JSONObject jsonResponse = new JSONObject(response);
            Object results = jsonResponse.get("result");
            System.out.println(results);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void reply() {

    }
}
