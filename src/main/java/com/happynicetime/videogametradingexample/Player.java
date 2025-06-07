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
    LinkedList<Item> items = new LinkedList<>();
    void createItem(String itemString) {
        items.add(new Item(this, itemString));
    }

    void printItems() {
        for(Item item:items){
            System.out.printf("%s, ",item.name);
        }
        System.out.printf("%n");
    }

    void take(Item item) {
        item.owner = null;
        for(Iterator<Item> iterator = items.iterator();iterator.hasNext();){
            Item item2 = iterator.next();
            if(item == item2){
                iterator.remove();
                break;
            }
        }
    }

    void give(Item item) {
        item.owner = this;
        items.add(item);
    }
    
}
