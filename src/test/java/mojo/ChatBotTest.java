package mojo;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.*;
import mojo.bot.*;
import mojo.Setup;
import mojo.risk.*;
import mojo.*;
import mojo.bot.RiskyBot;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;


public class ChatBotTest {

    @Test
    public void chatbotMessage() {
        RiskyBot r = new RiskyBot();

        // System.out.println(r.checkMessage(111111,"1234"));

        System.out.println("\n\n\n\n\n\n\n");

        assertTrue("testing continentTest", r.checkMessage(111111, "1234").equals("You're in!"));
        System.out.println("\n\n\n\n\n\n\n");

        // assertTrue("testing chabot",true);
    }

    @Test
    public void chatbotName() {
        RiskyBot r = new RiskyBot();

        assertTrue("testing continentTest", r.getBotUsername().equals("RiskyBot"));
        // assertTrue("testing chabot",true);
    }

    @Test
    public void timerTest() {
        RiskyBot r = new RiskyBot();

        r.startTimer(5);
        try {
            Thread.sleep(5000);
        } catch (Exception e) {

        }

        assertTrue("testing continentTest", r.getBotUsername().equals("RiskyBot"));
        // assertTrue("testing chabot",true);
    }

    // test id 601063221

    public void botTest() {
        RiskyBot r = new RiskyBot();
        // ApiContextInitializer.init();

		// // Instantiate Telegram Bots API
		// TelegramBotsApi botsApi = new TelegramBotsApi();

		// // Register our bot
		// try {
		// 	botsApi.registerBot(r);
		// } catch (TelegramApiException e) {
		// 	e.printStackTrace();
        // }
        
        r.notifyPlayer(601063221, "this is a test ");
        r.notifyNextPlayer(601063221);
        Player p=new Player(601063221);
        r.controller("attack", p, "attack");
        r.controller("trade", p, "trade");
        r.controller("forify", p, "forify");
        r.controller("menu", p, "menu");
        r.controller("end", p, "end");
        r.controller("summery", p, "summery");
        r.controller("surrender", p, "surrender");
        


        assertTrue("testing continentTest", r.getBotUsername().equals("RiskyBot"));
        // assertTrue("testing chabot",true);
    }

    @Test
    public void chatbotController() {
        RiskyBot r = new RiskyBot();

        Player p = new Player(32344);
        Territory t = new Territory("Alaska", "North America", 32344, 6);
        Territory alberta = new Territory("Alberta", "North America", 11111, 4);
        List<Territory> nTl = new ArrayList<>();
        nTl.add(alberta);
        t.setNeighboringTerritories(nTl);
        p.addTerritory(t);
        p.receivedUnits = true;

        System.out.println("PLAYER ID: " + p.getId());
        String messages = "";
        messages = "Here is the list of territories you can attack with:\n";
        messages += "Territory Name: " + "Alaska" + "\n";
        messages += "Army Count: " + "6" + "\n";
        messages += "Enemy Territories:\n";
        messages += "Target Territory Name: " + "Alberta" + "\n";
        messages += "Armies Count: " + "4" + "\n";
        messages += "Owned by: " + "11111" + "\n";
        messages += "\n";
        messages += "How would you like to proceed?\n";
        messages += "Format: Attack yourTerritory targetTerritory unitCount\n";
        messages += "youTerritory - The territory you want to attack with.\n";
        messages += "targetTerritory - The territory you would like to target.\n";
        messages += "unitCount - The amount of armies you would like to attack with.\n";

        assertTrue("testing Chat Bot Controller ", !r.controller("attack", p, "").equals(null));
        // assertTrue("testing chabot",true);
    }

}