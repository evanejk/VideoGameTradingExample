/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.happynicetime.videogametradingexample;

/**
 *
 * @author evane
 */
public class VideogameTradingExample {
    static Player player1 = new Player();
    static Player player2 = new Player();
    public static void main(String[] args) {
        player1.createItem("nectarine");
        player1.createItem("orange");
        player2.createItem("avocado");
        
        printPlayersItems();
        
        TradeOffer tradeOffer = new TradeOffer(player1,player2);
        tradeOffer.playerOneOffer(player1.items.get(0));
        tradeOffer.playerOneOffer(player1.items.get(1));
        tradeOffer.playerTwoOffer(player2.items.get(0));
        
        tradeOffer.player1Accept();
        tradeOffer.player2Accept();
        
        printPlayersItems();

    }

    private static void printPlayersItems() {
        System.out.printf("Player1 items: %n");
        player1.printItems();
        System.out.printf("Player2 items: %n");
        player2.printItems();
    }
}
