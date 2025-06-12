/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.happynicetime.videogametradingexample;

import java.util.Iterator;
import java.util.LinkedList;

/**
 *
 * @author evane
 */
class Player {
    String username = "";
    Player(String username1) {
        username = username1;
    }
    void take(Item item) {
        System.out.printf("Take item %s from username %s .%n",item.name,username);//extra space intended
    }
    void give(Item item) {
        System.out.printf("Give item %s to username %s .%n",item.name,username);//extra space intended
    }
    
}
