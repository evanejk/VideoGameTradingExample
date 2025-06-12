/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.happynicetime.videogametradingexample;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author evane
 */
public class VideogameTradingExample {
    static LinkedList<Player> players = new LinkedList<>();
    static LinkedList<TradeOffer> tradeOffers = new LinkedList<>();
    private static void addUser(String username) {
        Player player = findPlayer(username);
        if(player == null){
            //didn't find user so add them
            players.add(new Player(username));
            System.out.printf("added user %s%n",username);
        }
    }
    private static void tradeOffer(String usernameFrom, String usernameTo) {
        TwoPlayers twoPlayers = findTwoPlayers(usernameFrom,usernameTo);
        if(twoPlayers == null)return;
        Player player1 = twoPlayers.player1;
        Player player2 = twoPlayers.player2;

        boolean foundExistingTradeOffer = false;
        for(TradeOffer tradeOffer:tradeOffers){
            if(tradeOffer.player1 == player1 && tradeOffer.player2 == player2){
                foundExistingTradeOffer = true;
            }
            else if(tradeOffer.player1 == player2 && tradeOffer.player2 == player1){
                foundExistingTradeOffer = true;
            }
        }
        if(!foundExistingTradeOffer){
            tradeOffers.add(new TradeOffer(player1,player2));
            System.out.printf("added new trade offer%n");
        }else{
            System.out.printf("trade offer already exists between those two players%n");
        }
    }
    private static void offerItem(String usernameFrom, String usernameTo, String item) {
        TwoPlayers twoPlayers = findTwoPlayers(usernameFrom,usernameTo);
        if(twoPlayers == null){
            System.out.printf("syntax error 1%n");
            return;
        }
        Player playerFrom = twoPlayers.player1;                
        Player playerTo = twoPlayers.player2;                
        
        //find the trade offer
        TradeOffer tradeOffer = findTradeOffer(playerFrom,playerTo);
        if(tradeOffer == null){
            System.out.printf("syntax error 2%n");
            return;
        }
        //add item to offers
        if(playerFrom == tradeOffer.player1){
            tradeOffer.playerOneOffer(new Item(item));
        }else if(playerFrom == tradeOffer.player2){
            tradeOffer.playerTwoOffer(new Item(item));
        }
        
    }
    private static void accept(String usernameFrom, String usernameTo, String usernameThatAccepted) {
        TwoPlayers twoPlayers = findTwoPlayers(usernameFrom,usernameTo);
        Player playerFrom = twoPlayers.player1;
        Player playerTo = twoPlayers.player2;
        TradeOffer tradeOffer = findTradeOffer(playerFrom,playerTo);
        Player playerThatAccepted = findPlayer(usernameThatAccepted);
        if(tradeOffer.player1 == playerThatAccepted){
            tradeOffer.player1Accept();
        }else if(tradeOffer.player2 == playerThatAccepted){
            tradeOffer.player2Accept();
        }
    }
    public static void main(String[] args) {
        help();
        // Enter data using BufferReader
        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        boolean quit = false;
        while(!quit){
            try {
                String s = r.readLine();
                String[] commands = s.split(" ");
                switch(commands[0]){
                    case "help":
                        help();
                    break;
                    case "adduser":
                        if(commands.length == 2)
                        addUser(commands[1]);
                    break;
                    case "tradeoffer":
                        if(commands.length == 3)
                        tradeOffer(commands[1],commands[2]);
                    break;
                    case "offeritem":
                        if(commands.length == 4)
                        offerItem(commands[1],commands[2],commands[3]);
                    break;
                    case "accept":
                        if(commands.length == 4)
                        accept(commands[1],commands[2],commands[3]);
                    break;
                    case "deletetradeoffer":
                        if(commands.length == 3)
                        deleteTradeOffer(commands[1],commands[2]);
                    break;
                    case "removeuser":
                        if(commands.length == 2)
                        removeUser(commands[1]);
                    break;                        
                    case "print":
                        print();
                    break;
                    case "quit":
                        quit = true;
                    break;
                }
            } catch (IOException ex) {
                System.out.printf("error IOException%n");
            }
        }
    }

    private static TwoPlayers findTwoPlayers(String usernameFrom, String usernameTo) {//return results in same order as parameters
        TwoPlayers twoPlayers = new TwoPlayers();
        boolean foundUser1 = false;
        boolean foundUser2 = false;
        for(Player player:players){
            if(player.username.equals(usernameFrom)){
                foundUser1 = true;
                twoPlayers.player1 = player;
            }
            if(player.username.equals(usernameTo)){
                foundUser2 = true;
                twoPlayers.player2 = player;
            }
        }
        if(foundUser1 == false || foundUser2 == false){
            System.out.printf("couldn't find user%n");
            return null;
        }
        return twoPlayers;
    }

    private static Player findPlayer(String username) {
        for(Player player:players){
            if(player.username.equals(username)){
                return player;
            }
        }
        return null;
    }

    private static TradeOffer findTradeOffer(Player playerFrom, Player playerTo) {
        for(TradeOffer tradeOffer:tradeOffers){
            if(tradeOffer.player1 == playerFrom && tradeOffer.player2 == playerTo){
                return tradeOffer;
            }else if(tradeOffer.player1 == playerTo && tradeOffer.player2 == playerFrom){
                return tradeOffer;
            }
        }
        System.out.printf("couldn't find trade offer%n");
        return null;
    }

    private static void print() {
        int tradeNum = 0;
        System.out.printf("Players: ");
        for(Player player:players){
            System.out.printf("%s ",player.username);
        }
        System.out.printf(".%n");
        for(TradeOffer tradeOffer:tradeOffers){
            System.out.printf("Trade Offer #%d%n", tradeNum++);
            System.out.printf("player %s offers%n",tradeOffer.player1.username);
            for(Item item:tradeOffer.player1Offers){
                System.out.printf("%s, ",item.name);
            }
            System.out.printf("%n");
            System.out.printf("player %s offers%n",tradeOffer.player2.username);
            for(Item item:tradeOffer.player2Offers){
                System.out.printf("%s, ",item.name);
            }
            System.out.printf("%n");
        }
    }

    private static void removeUser(String username) {
        //delete tradeoffers with user
        Player removeThisPlayer = findPlayer(username);
        Iterator<TradeOffer> iteratorTO = tradeOffers.iterator();
        while(iteratorTO.hasNext()){
            TradeOffer tradeOffer = iteratorTO.next();
            if(tradeOffer.player1 == removeThisPlayer || tradeOffer.player2 == removeThisPlayer){
                iteratorTO.remove();
            }
        }
        //delete from players
        Iterator<Player> iteratorP = players.iterator();
        while(iteratorP.hasNext()){
            Player playerOn = iteratorP.next();
            if(playerOn.username.equals(username)){
                iteratorP.remove();
                break;
            }
        }
    }

    private static void deleteTradeOffer(String usernameFrom, String usernameTo) {
        TwoPlayers twoPlayers = findTwoPlayers(usernameFrom,usernameTo);
        Player playerFrom = twoPlayers.player1;
        Player playerTo = twoPlayers.player2;
        TradeOffer tradeOffer = findTradeOffer(playerFrom,playerTo);
        tradeOffer.deleteTradeOffer();
    }

    private static void help() {
        System.out.printf("help%n");
        System.out.printf("adduser [username]%n");
        System.out.printf("tradeoffer [username from] [username to]%n");
        System.out.printf("offeritem [username from] [username to] [item]%n");
        System.out.printf("accept [username from] [username to] [username that accepted]%n");
        System.out.printf("removeuser [username]%n");
        System.out.printf("deletetradeoffer [username from] [username to]%n");
        System.out.printf("print%n");
        System.out.printf("quit%n");
    }
}
