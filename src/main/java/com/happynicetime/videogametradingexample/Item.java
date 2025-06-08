/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.happynicetime.videogametradingexample;

/**
 *
 * @author evane
 */
class Item {
    int id = 0;
    String name = "";
    Player owner = null;
    Item(Player owner, String itemString) {
        id = getItemIdFromString(itemString);
        name = getItemNameFromString(itemString);
        this.owner = owner;
    }
    Item(Player owner, int itemId) {
        id = itemId;
        name = getItemNameFromID(itemId);
        this.owner = owner;
    }
    private static int getItemIdFromString(String itemString) {
        int id = -1;
        switch(itemString){
            case "nectarine":
                id = 0;
                break;
            case "avocado":
                id = 1;
                break;
            case "orange":
                id = 2;
                break;
        }
        return id;
    }

    private static String getItemNameFromString(String itemString) {
        String name = "";
        switch(itemString){
            case "nectarine":
                name = "nectarine";
                break;
            case "avocado":
                name = "avocado";
                break;
            case "orange":
                name = "orange";
                break;
        }
        return name;
    }

    private static String getItemNameFromID(int itemId) {
        String name = "";
        switch(itemId){
            case 0:
                name = "nectarine";
                break;
            case 1:
                name = "avocado";
                break;
            case 2:
                name = "orange";
                break;
        }
        return name;
    }
}
