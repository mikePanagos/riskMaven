package mojo;

import java.util.*;
import mojo.risk.*;

public class Setup {
    // set up borad
    private static Setup init = null;
    private List<Territory> board = new ArrayList<>();
    private List<Continent> continents = new ArrayList<>();
    private List<Player> playersList = new ArrayList<>();
    private List<Card> deck = new ArrayList<>();

    /*
    *
    *
    */
    private Setup() {
        makeTerritory();
        makeDeck();

    }

    private Setup(int numPlayers) {
        makeTerritory();
        makePLayers(numPlayers);
        makeDeck();

    }

    public static Setup getInstances() {
        if (init != null) {
            return init;
        } else {
            init = new Setup();
            return init;
        }

    }

    /**
     * this is for testing reasons only
     * @param num a dummy variable
     * @return returns a singleton instance
     */
    public static Setup getInstances(int num) {
        // this is for testing reasons only

        init = new Setup();
        return init;

    }
    /**
     * This sets up the players list
     * @param numOfPlayers the amount of players in game
     */
    public static void SetupPLayers(int numOfPlayers) {

        init.makePLayers(numOfPlayers);

    }
    /**
    * for bot
    * @param playerList the player list used to start the game
    */
    public static void setupPlayerWithList(List<Player> playerList) {

        init.makePlayerList(playerList);

    }
    

    /**
     * assimbles a deck of cards
     * 
     * 
     */
    private void makeDeck() {
        Card card1 = new Card("Alaska", "Infantry");
        deck.add(card1);
        Card card2 = new Card("Alberta", "Cavalry");
        deck.add(card2);
        Card card3 = new Card("Central America", "Artillery");
        deck.add(card3);
        Card card4 = new Card("Eastern United States", "Infantry");
        deck.add(card4);
        Card card5 = new Card("Greenland", "Cavalry");
        deck.add(card5);
        Card card6 = new Card("Northwest", "Artillery");
        deck.add(card6);
        Card card7 = new Card("Ontario", "Infantry");
        deck.add(card7);
        Card card8 = new Card("Quebec", "Cavalry");
        deck.add(card8);
        Card card9 = new Card("Western United States", "Artillery");
        deck.add(card9);
        Card card10 = new Card("Argentina", "Infantry");
        deck.add(card10);
        Card card11 = new Card("Brazil", "Cavalry");
        deck.add(card11);
        Card card12 = new Card("Peru", "Artillery");
        deck.add(card12);
        Card card13 = new Card("Venexuela", "Infantry");
        deck.add(card13);
        Card card14 = new Card("Western Australia", "Cavalry");
        deck.add(card14);
        Card card15 = new Card("Britain", "Artillery");
        deck.add(card15);
        Card card16 = new Card("Iceland", "Infantry");
        deck.add(card16);
        Card card17 = new Card("Northern Europe", "Cavalry");
        deck.add(card17);
        Card card18 = new Card("Scandinavia", "Artillery");
        deck.add(card18);
        Card card19 = new Card("Southern Europe", "Infantry");
        deck.add(card19);
        Card card20 = new Card("Ukraine", "Cavalry");
        deck.add(card20);
        Card card21 = new Card("Western Europe", "Artillery");
        deck.add(card21);
        Card card22 = new Card("Congo", "Infantry");
        deck.add(card22);
        Card card23 = new Card("East Africa", "Cavalry");
        deck.add(card23);
        Card card24 = new Card("Egypt", "Artillery");
        deck.add(card24);
        Card card25 = new Card("Madagascar", "Infantry");
        deck.add(card25);
        Card card26 = new Card("North Africa", "Cavalry");
        deck.add(card26);
        Card card27 = new Card("South Africa", "Artillery");
        deck.add(card27);
        Card card28 = new Card("Afganistan", "Infantry");
        deck.add(card28);
        Card card29 = new Card("China", "Cavalry");
        deck.add(card29);
        Card card30 = new Card("India", "Artillery");
        deck.add(card30);
        Card card31 = new Card("Irkutsk", "Infantry");
        deck.add(card31);
        Card card32 = new Card("Japan", "Cavalry");
        deck.add(card32);
        Card card33 = new Card("Kamchatka", "Artillery");
        deck.add(card33);
        Card card34 = new Card("Middle East", "Infantry");
        deck.add(card34);
        Card card35 = new Card("Mongolia", "Cavalry");
        deck.add(card35);
        Card card36 = new Card("Slam", "Artillery");
        deck.add(card36);
        Card card37 = new Card("Siberia", "Infantry");
        deck.add(card37);
        Card card38 = new Card("Ural", "Cavalry");
        deck.add(card38);
        Card card39 = new Card("Yakutsk", "Artillery");
        deck.add(card39);
        Card card40 = new Card("Eastern Australia", "Infantry");
        deck.add(card40);
        Card card41 = new Card("Indonesia", "Cavalry");
        deck.add(card41);
        Card card42 = new Card("New Guinea", "Artillery");
        deck.add(card42);

    }

    /**
     * this function makes all the territory all makes the Continent then calles a
     * funtion that will make the neighbors
     */
    private void makeTerritory() {
        // String[] NorthA = {"Alaska", "Alberta", "Central America", "Eastern United
        // States",
        // "Greenland", "Northwest", "Ontario", "Quebec", "Western United States"};

        List<Territory> northAmericaList = new ArrayList<>();

        Territory alaska = new Territory("Alaska", "North America", 0, 0);
        northAmericaList.add(alaska);
        board.add(alaska);
        Territory alberta = new Territory("Alberta", "North America", 0, 0);
        northAmericaList.add(alberta);
        board.add(alberta);
        Territory centralAmerica = new Territory("Central_America", "North America", 0, 0);
        northAmericaList.add(centralAmerica);
        board.add(centralAmerica);
        Territory easternUnitedStates = new Territory("Eastern_United_States", "North America", 0, 0);
        northAmericaList.add(easternUnitedStates);
        board.add(easternUnitedStates);
        Territory greenland = new Territory("Greenland", "North America", 0, 0);
        northAmericaList.add(greenland);
        board.add(greenland);
        Territory northwest = new Territory("Northwest", "North America", 0, 0);
        northAmericaList.add(northwest);
        board.add(northwest);
        Territory ontario = new Territory("Ontario", "North America", 0, 0);
        northAmericaList.add(ontario);
        board.add(ontario);
        Territory quebec = new Territory("Quebec", "North America", 0, 0);
        northAmericaList.add(quebec);
        board.add(quebec);
        Territory westernUnitedStates = new Territory("Western_United_States", "North America", 0, 0);
        northAmericaList.add(westernUnitedStates);
        board.add(westernUnitedStates);

        Continent northAmerica = new Continent("North America", northAmericaList);
        continents.add(northAmerica);

        // String[] SouthA = {"Argentina", "Brazil", "Peru", "Venexuela"};

        List<Territory> southAmericaList = new ArrayList<>();
        Territory argentina = new Territory("Argentina", "South America", 0, 0);
        southAmericaList.add(argentina);
        board.add(argentina);
        Territory brazil = new Territory("Brazil", "South America", 0, 0);
        southAmericaList.add(brazil);
        board.add(brazil);
        Territory peru = new Territory("Peru", "South America", 0, 0);
        southAmericaList.add(peru);
        board.add(peru);
        Territory venexuela = new Territory("Venezuela", "South America", 0, 0);
        southAmericaList.add(venexuela);
        board.add(venexuela);

        Continent southAmerica = new Continent("South America", southAmericaList);
        continents.add(southAmerica);

        // String[] Europe = {"Britain", "Iceland", "Northern Europe", "Scandinavia",
        // "Southern Europe", "Ukraine", "Western Europe"};

        List<Territory> europeList = new ArrayList<>();
        Territory britain = new Territory("Britain", "Europe", 0, 0);
        europeList.add(britain);
        board.add(britain);
        Territory iceland = new Territory("Iceland", "Europe", 0, 0);
        europeList.add(iceland);
        board.add(iceland);
        Territory northernEurope = new Territory("Northern_Europe", "Europe", 0, 0);
        europeList.add(northernEurope);
        board.add(northernEurope);
        Territory scandinavia = new Territory("Scandinavia", "Europe", 0, 0);
        europeList.add(scandinavia);
        board.add(scandinavia);
        Territory southernEurope = new Territory("Southern_Europe", "Europe", 0, 0);
        europeList.add(southernEurope);
        board.add(southernEurope);
        Territory ukraine = new Territory("Ukraine", "Europe", 0, 0);
        europeList.add(ukraine);
        board.add(ukraine);
        Territory westernEurope = new Territory("Western_Europe", "Europe", 0, 0);
        europeList.add(westernEurope);
        board.add(westernEurope);

        Continent europe = new Continent("Europe", europeList);
        continents.add(europe);

        // String[] Africa = {"Congo", "East Africa", "Egypt", "Madagascar", "North
        // Africa", "South Africa"};
        List<Territory> afticaList = new ArrayList<>();

        Territory congo = new Territory("Congo", "Africa", 0, 0);
        afticaList.add(congo);
        board.add(congo);
        Territory eastAfrica = new Territory("East_Africa", "Africa", 0, 0);
        afticaList.add(eastAfrica);
        board.add(eastAfrica);
        Territory egypt = new Territory("Egypt", "Africa", 0, 0);
        afticaList.add(egypt);
        board.add(egypt);
        Territory madagascar = new Territory("Madagascar", "Africa", 0, 0);
        afticaList.add(madagascar);
        board.add(madagascar);
        Territory northAfrica = new Territory("North_Africa", "Africa", 0, 0);
        afticaList.add(northAfrica);
        board.add(northAfrica);
        Territory southAfrica = new Territory("South_Africa", "Africa", 0, 0);
        afticaList.add(southAfrica);
        board.add(southAfrica);

        Continent africa = new Continent("Africa", afticaList);
        continents.add(africa);

        // String[] Asia = {"Afganistan", "China", "India", "Irkutsk", "Japan",
        // "Kamchatka", "Middle East",
        // "Mongolia", "Slam", "Siberia", "ural", "Yakutsk"};

        List<Territory> asiaList = new ArrayList<>();

        Territory afganistan = new Territory("Afganistan", "Asia", 0, 0);
        asiaList.add(afganistan);
        board.add(afganistan);
        Territory china = new Territory("China", "Asia", 0, 0);
        asiaList.add(china);
        board.add(china);
        Territory india = new Territory("India", "Asia", 0, 0);
        asiaList.add(india);
        board.add(india);
        Territory irkutsk = new Territory("Irkutsk", "Asia", 0, 0);
        asiaList.add(irkutsk);
        board.add(irkutsk);
        Territory japan = new Territory("Japan", "Asia", 0, 0);
        asiaList.add(japan);
        board.add(japan);
        Territory kamchatka = new Territory("Kamchatka", "Asia", 0, 0);
        asiaList.add(kamchatka);
        board.add(kamchatka);
        Territory middleEast = new Territory("Middle_East", "Asia", 0, 0);
        asiaList.add(middleEast);
        board.add(middleEast);
        Territory mongolia = new Territory("Mongolia", "Asia", 0, 0);
        asiaList.add(mongolia);
        board.add(mongolia);
        Territory slam = new Territory("Slam", "Asia", 0, 0);
        asiaList.add(slam);
        board.add(slam);
        Territory siberia = new Territory("Siberia", "Asia", 0, 0);
        asiaList.add(siberia);
        board.add(siberia);
        Territory ural = new Territory("Ural", "Asia", 0, 0);
        asiaList.add(ural);
        board.add(ural);
        Territory yakutsk = new Territory("Yakutsk", "Asia", 0, 0);
        asiaList.add(yakutsk);
        board.add(yakutsk);

        Continent asia = new Continent("Asia", asiaList);
        continents.add(asia);

        // String[] Australia = {"Eastern Australia", "Indonesia", "New Guinea",
        // "Western Australia"};

        List<Territory> australiaList = new ArrayList<>();
        Territory easternAustralia = new Territory("Eastern_Australia", "Australia", 0, 0);
        australiaList.add(easternAustralia);
        board.add(easternAustralia);
        Territory indonesia = new Territory("Indonesia", "Australia", 0, 0);
        australiaList.add(indonesia);
        board.add(indonesia);
        Territory newGuinea = new Territory("New Guinea", "Australia", 0, 0);
        australiaList.add(newGuinea);
        board.add(newGuinea);
        Territory westernAustralia = new Territory("Western_Australia", "Australia", 0, 0);
        australiaList.add(westernAustralia);
        board.add(westernAustralia);
        Continent australia = new Continent("Australia", asiaList);
        continents.add(australia);

        /**
         * this fills each territory with its surrounding neighbors
         */

        // north america
        List<Territory> alaskaList = new ArrayList<>();
        alaskaList.add(kamchatka);
        alaskaList.add(northwest);
        alaskaList.add(alberta);
        alaska.setNeighboringTerritories(alaskaList);

        List<Territory> albertaList = new ArrayList<>();
        albertaList.add(alaska);
        albertaList.add(northwest);
        albertaList.add(ontario);
        albertaList.add(westernUnitedStates);
        alberta.setNeighboringTerritories(albertaList);

        List<Territory> centralAmericaList = new ArrayList<>();
        centralAmericaList.add(easternUnitedStates);
        centralAmericaList.add(westernUnitedStates);
        centralAmericaList.add(venexuela);
        centralAmerica.setNeighboringTerritories(centralAmericaList);

        List<Territory> easternUnitedStatesList = new ArrayList<>();
        easternUnitedStatesList.add(quebec);
        easternUnitedStatesList.add(ontario);
        easternUnitedStatesList.add(westernUnitedStates);
        easternUnitedStatesList.add(centralAmerica);
        easternUnitedStates.setNeighboringTerritories(easternUnitedStatesList);

        List<Territory> greenlandList = new ArrayList<>();
        greenlandList.add(iceland);
        greenlandList.add(northwest);
        greenlandList.add(ontario);
        greenlandList.add(quebec);
        greenland.setNeighboringTerritories(greenlandList);

        List<Territory> northwestList = new ArrayList<>();
        northwestList.add(greenland);
        northwestList.add(ontario);
        northwestList.add(alaska);
        northwestList.add(alberta);
        northwest.setNeighboringTerritories(northwestList);

        List<Territory> ontarioList = new ArrayList<>();
        ontarioList.add(greenland);
        ontarioList.add(quebec);
        ontarioList.add(easternUnitedStates);
        ontarioList.add(westernUnitedStates);
        ontarioList.add(alberta);
        ontarioList.add(northwest);
        ontario.setNeighboringTerritories(ontarioList);

        List<Territory> quebecList = new ArrayList<>();
        quebecList.add(greenland);
        quebecList.add(ontario);
        quebecList.add(easternUnitedStates);
        quebec.setNeighboringTerritories(quebecList);

        List<Territory> westernUnitedStatesList = new ArrayList<>();
        westernUnitedStatesList.add(ontario);
        westernUnitedStatesList.add(alberta);
        westernUnitedStatesList.add(easternUnitedStates);
        westernUnitedStatesList.add(centralAmerica);
        westernUnitedStates.setNeighboringTerritories(westernUnitedStatesList);

        // south america
        List<Territory> argentinaList = new ArrayList<>();
        argentinaList.add(brazil);
        argentinaList.add(peru);
        argentina.setNeighboringTerritories(argentinaList);

        List<Territory> peruList = new ArrayList<>();
        peruList.add(brazil);
        peruList.add(argentina);
        peruList.add(venexuela);
        peru.setNeighboringTerritories(peruList);

        List<Territory> venexuelaList = new ArrayList<>();
        venexuelaList.add(centralAmerica);
        venexuelaList.add(brazil);
        venexuelaList.add(peru);
        venexuela.setNeighboringTerritories(venexuelaList);

        List<Territory> brazilList = new ArrayList<>();
        brazilList.add(northAfrica);
        brazilList.add(peru);
        brazilList.add(argentina);
        brazilList.add(venexuela);
        brazil.setNeighboringTerritories(brazilList);

        // eurpoe
        List<Territory> britainList = new ArrayList<>();
        britainList.add(iceland);
        britainList.add(westernEurope);
        britainList.add(northernEurope);
        britainList.add(scandinavia);
        britain.setNeighboringTerritories(britainList);

        List<Territory> icelandList = new ArrayList<>();
        icelandList.add(greenland);
        icelandList.add(britain);
        icelandList.add(scandinavia);
        iceland.setNeighboringTerritories(icelandList);

        List<Territory> northernEuropeList = new ArrayList<>();
        northernEuropeList.add(ukraine);
        northernEuropeList.add(southernEurope);
        northernEuropeList.add(westernEurope);
        northernEuropeList.add(britain);
        northernEuropeList.add(scandinavia);
        northernEurope.setNeighboringTerritories(northernEuropeList);

        List<Territory> scandinaviaList = new ArrayList<>();
        scandinaviaList.add(ukraine);
        scandinaviaList.add(britain);
        scandinaviaList.add(northernEurope);
        scandinaviaList.add(iceland);
        scandinavia.setNeighboringTerritories(scandinaviaList);

        List<Territory> southernEuropeList = new ArrayList<>();
        southernEuropeList.add(northernEurope);
        southernEuropeList.add(ukraine);
        southernEuropeList.add(westernEurope);
        southernEuropeList.add(northAfrica);
        southernEuropeList.add(middleEast);
        southernEuropeList.add(egypt);
        southernEurope.setNeighboringTerritories(southernEuropeList);

        List<Territory> ukraineList = new ArrayList<>();
        ukraineList.add(ural);
        ukraineList.add(afganistan);
        ukraineList.add(middleEast);
        ukraineList.add(southernEurope);
        ukraineList.add(northernEurope);
        ukraineList.add(scandinavia);
        ukraine.setNeighboringTerritories(ukraineList);

        List<Territory> westernEuropeList = new ArrayList<>();
        westernEuropeList.add(britain);
        westernEuropeList.add(northernEurope);
        westernEuropeList.add(southernEurope);
        westernEuropeList.add(northAfrica);
        westernEurope.setNeighboringTerritories(westernEuropeList);

        // africa
        List<Territory> congoList = new ArrayList<>();
        congoList.add(northAfrica);
        congoList.add(eastAfrica);
        congoList.add(southAfrica);
        congo.setNeighboringTerritories(congoList);

        List<Territory> eastAfricaList = new ArrayList<>();
        eastAfricaList.add(middleEast);
        eastAfricaList.add(madagascar);
        eastAfricaList.add(northAfrica);
        eastAfricaList.add(congo);
        eastAfricaList.add(southAfrica);
        eastAfricaList.add(egypt);
        eastAfrica.setNeighboringTerritories(eastAfricaList);

        List<Territory> egyptList = new ArrayList<>();
        egyptList.add(middleEast);
        egyptList.add(eastAfrica);
        egyptList.add(northAfrica);
        egyptList.add(southernEurope);
        egypt.setNeighboringTerritories(egyptList);

        List<Territory> madagascarList = new ArrayList<>();
        madagascarList.add(eastAfrica);
        madagascarList.add(southAfrica);
        madagascar.setNeighboringTerritories(madagascarList);

        List<Territory> northAfricaList = new ArrayList<>();
        northAfricaList.add(congo);
        northAfricaList.add(egypt);
        northAfricaList.add(southernEurope);
        northAfricaList.add(westernEurope);
        northAfricaList.add(brazil);
        northAfricaList.add(eastAfrica);
        northAfrica.setNeighboringTerritories(northAfricaList);

        // asia
        List<Territory> afganistanList = new ArrayList<>();
        afganistanList.add(ural);
        afganistanList.add(china);
        afganistanList.add(india);
        afganistanList.add(middleEast);
        afganistanList.add(ukraine);
        afganistan.setNeighboringTerritories(afganistanList);

        List<Territory> chinaList = new ArrayList<>();
        chinaList.add(mongolia);
        chinaList.add(slam);
        chinaList.add(india);
        chinaList.add(afganistan);
        chinaList.add(ural);
        chinaList.add(siberia);
        china.setNeighboringTerritories(chinaList);

        List<Territory> indiaList = new ArrayList<>();
        indiaList.add(china);
        indiaList.add(slam);
        indiaList.add(middleEast);
        indiaList.add(afganistan);
        india.setNeighboringTerritories(indiaList);

        List<Territory> irkutskList = new ArrayList<>();
        irkutskList.add(mongolia);
        irkutskList.add(siberia);
        irkutskList.add(yakutsk);
        irkutskList.add(kamchatka);
        irkutsk.setNeighboringTerritories(irkutskList);

        List<Territory> japanaList = new ArrayList<>();
        japanaList.add(kamchatka);
        japanaList.add(mongolia);
        japan.setNeighboringTerritories(japanaList);

        List<Territory> kamchatkaList = new ArrayList<>();
        kamchatkaList.add(japan);
        kamchatkaList.add(alaska);
        kamchatkaList.add(mongolia);
        kamchatkaList.add(yakutsk);
        kamchatkaList.add(irkutsk);
        kamchatka.setNeighboringTerritories(kamchatkaList);

        List<Territory> middleEastList = new ArrayList<>();
        middleEastList.add(india);
        middleEastList.add(eastAfrica);
        middleEastList.add(egypt);
        middleEastList.add(southernEurope);
        middleEastList.add(ukraine);
        middleEast.setNeighboringTerritories(middleEastList);

        List<Territory> mongoliaList = new ArrayList<>();
        mongoliaList.add(japan);
        mongoliaList.add(kamchatka);
        mongoliaList.add(irkutsk);
        mongoliaList.add(siberia);
        mongoliaList.add(china);
        mongolia.setNeighboringTerritories(mongoliaList);

        List<Territory> slamList = new ArrayList<>();
        slamList.add(china);
        slamList.add(india);
        slamList.add(indonesia);
        slam.setNeighboringTerritories(slamList);

        List<Territory> siberiaList = new ArrayList<>();
        siberiaList.add(ural);
        siberiaList.add(china);
        siberiaList.add(mongolia);
        siberiaList.add(irkutsk);
        siberiaList.add(yakutsk);
        siberia.setNeighboringTerritories(siberiaList);

        List<Territory> uralList = new ArrayList<>();
        uralList.add(ukraine);
        uralList.add(siberia);
        uralList.add(afganistan);
        uralList.add(china);
        ural.setNeighboringTerritories(uralList);

        List<Territory> yakutskaList = new ArrayList<>();
        yakutskaList.add(kamchatka);
        yakutskaList.add(irkutsk);
        yakutskaList.add(siberia);
        yakutsk.setNeighboringTerritories(yakutskaList);

        // australia
        List<Territory> easternAustraliaList = new ArrayList<>();
        easternAustraliaList.add(newGuinea);
        easternAustraliaList.add(westernAustralia);
        easternAustralia.setNeighboringTerritories(easternAustraliaList);

        List<Territory> indonesiaList = new ArrayList<>();
        indonesiaList.add(slam);
        indonesiaList.add(newGuinea);
        indonesiaList.add(westernAustralia);
        indonesia.setNeighboringTerritories(indonesiaList);

        List<Territory> newGuineaList = new ArrayList<>();
        newGuineaList.add(indonesia);
        newGuineaList.add(easternAustralia);
        newGuineaList.add(westernAustralia);
        newGuinea.setNeighboringTerritories(newGuineaList);

        List<Territory> westernAustraliaList = new ArrayList<>();
        westernAustraliaList.add(newGuinea);
        westernAustraliaList.add(indonesia);
        westernAustraliaList.add(easternAustralia);
        westernAustralia.setNeighboringTerritories(westernAustraliaList);

    }

    // set up player

    // return list with terriroyrs arraylist in element 0 and players in elemnet 1

    public int numUnitAtStart(int num) {
        if (num == 2) {
            return 40;
        } else if (num == 3) {
            return 35;

        } else if (num == 4) {
            return 30;
        } else if (num == 5) {
            return 25;
        } else {
            return 20;
        }
    }
    /**
     * for command line
     * @param numPlayers the number of players to create for the game
     */
    public void makePLayers(int numPlayers) {

        int numUnitsPerPlayer = numUnitAtStart(numPlayers);

        Player p1 = new Player(1, numUnitsPerPlayer, 0);
        playersList.add(p1);
        Player p2 = new Player(2, numUnitsPerPlayer, 0);
        playersList.add(p2);
        if (numPlayers >= 3) {
            Player p3 = new Player(3, numUnitsPerPlayer, 0);
            playersList.add(p3);
            if (numPlayers >= 4) {
                Player p4 = new Player(4, numUnitsPerPlayer, 0);
                playersList.add(p4);
                if (numPlayers >= 5) {
                    Player p5 = new Player(5, numUnitsPerPlayer, 0);
                    playersList.add(p5);
                    if (numPlayers == 6) {
                        Player p6 = new Player(6, numUnitsPerPlayer, 0);
                        playersList.add(p6);
                    }
                }
            }
        }

    }
    /**
     * Make a player list that will be used by the bot.
     * @param pl this is the playerlist that it will be given initially. It is instantiated with all the connected players.
     */
    public void makePlayerList(List<Player> pl){
        int count=0;
        boolean done=false;
        List<Territory> territory = new ArrayList<>();
        for (Territory var : board) {
            territory.add(var);
            
        }
        
        int numUnitsPerPlayer = numUnitAtStart(pl.size()); // numUnitsAtStart returns the amount of units given to each player

        for (int i = 0; i < pl.size(); i++) {
            pl.get(i).setArmiesCount(numUnitsPerPlayer); // Assign the amount of armies that the player initially has to distribute
        }
       
        this.playersList = pl; // Assign the passed in player list to the Setup objects playerList
        Collections.shuffle(this.playersList); // Shuffle the playerList to simulate a random order every time
        this.playersList.get(0).setItsMyTurn(true); // Initiate the first players turn.
        /*
            this assigns the first territory to a player i then removes it then sets number of units to 1 then repeats until empty  
         */
        while (!done) {
            for (int i = 0; i < this.playersList.size(); i++) {

                this.playersList.get(i).addTerritory(territory.get(0));
                territory.get(0).setNumOfUnits(territory.get(0).getNumOfUnits() + 1);
                territory.get(0).setOwner(this.playersList.get(i).getId());
                this.playersList.get(i).setArmiesCount(this.playersList.get(i).getArmiesCount() - 1);
                territory.remove(0);
                // s.removeTerritory(0);
                count++;
                if (count > 41) {
                    done = true;
                    break;
                }
            }

        }
    }

    public List<Territory> getTerritories() {
        return board;
    }

    public void removeTerritory(int i) {
        board.remove(i);
    }

    public List<Continent> getContinent() {
        return continents;
    }

    public List<Player> getPlayers() {
        return this.playersList;
    }

    public List<Card> getDeck() {
        return deck;
    }

    public Player getPlayerById(long id) {
        int i = 0;
        for (i = 0; i < playersList.size(); i++) {

            if (playersList.get(i).getId() == id) {

                break;
            }

        }
        return playersList.get(i);

    }
}