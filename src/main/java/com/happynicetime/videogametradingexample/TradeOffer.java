/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.happynicetime.videogametradingexample;

import java.util.LinkedList;

/**
 *
 * @author evane
 */
class TradeOffer {
    Player player1;
    Player player2;
    LinkedList<Item> player1Offers = new LinkedList<>();
    LinkedList<Item> player2Offers = new LinkedList<>();
    boolean player1Accepted = false;
    boolean player2Accepted = false;
    TradeOffer(Player player1,Player player2){
        System.out.printf("TradeOffer initialized%n");
        this.player1 = player1;
        this.player2 = player2;
    }

    void playerOneOffer(Item offeredItem) {
        System.out.printf("Player 1 offers %s%n",offeredItem.name);
        player1Offers.add(offeredItem);
    }

    void playerTwoOffer(Item offeredItem) {
        System.out.printf("Player 2 offers %s%n",offeredItem.name);
        player2Offers.add(offeredItem);
    }

    void player1Accept() {
        System.out.printf("Player 1 accepts trade.%n");
        player1Accepted = true;
        checkMutualAgreement();
    }

    void player2Accept() {
        System.out.printf("Player 2 accepts trade.%n");
        player2Accepted = true;
        checkMutualAgreement();
    }

    private void checkMutualAgreement() {
        if(player1Accepted && player2Accepted){
            System.out.printf("Found mutual agreement. Completing trade.%n");
            //complete the trade
            //first take the items from bothe players
            for(Item item : player1Offers){
                player1.take(item);
            }
            for(Item item : player2Offers){
                player2.take(item);
            }
            //now swap the items
            for(Item item : player2Offers){
                player1.give(item);
            }
            for(Item item : player1Offers){
                player2.give(item);
            }
        }
    }
}
