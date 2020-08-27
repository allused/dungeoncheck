package com.codecool.dungeoncrawl.logic.items;

import com.codecool.dungeoncrawl.logic.Cell;

import java.awt.*;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

public class Potion extends Item {
    private potionTypes type;
    private int health;

    public Potion(Cell cell, potionTypes type) {
        super(cell);
        if(type.equals(potionTypes.SMALL)) health = potionTypes.SMALL.value;
        else if (type.equals(potionTypes.MEDIUM)) health = potionTypes.MEDIUM.value;
        else if (type.equals(potionTypes.BIG)) health = potionTypes.BIG.value;
        this.type = type;

    }
    public static potionTypes getRandomPotion() {
        Random random = new Random();
        return potionTypes.values()[random.nextInt(potionTypes.values().length)];
    }


    public enum potionTypes{
        SMALL(2),
        MEDIUM(5),
        BIG(9);
        private final int value;

        potionTypes(int value){ this.value = value; }

        public int getValue() {
            return value;
        }
    }

}
