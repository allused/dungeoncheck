package com.codecool.dungeoncrawl.logic.items;

import com.codecool.dungeoncrawl.logic.Cell;

import java.util.ArrayList;
import java.util.Collections;

public  class Weapons extends Item{
    static public String[] weapons = {"Sword", "Axe", "Bow"};
    private  String type;
    private  int damage;
    private  int range;

    public Weapons(Cell cell) {
        super(cell);
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public void setRange(int range) {
        this.range = range;
    }

    public int getDamage() {
        return damage;
    }

    public int getRange() {
        return range;
    }

    public static boolean inWeaponList(String isWep){
        for (String weapon:weapons){
            if (isWep.equals(weapon)) {
            return true;
            }
        }
        return false;
    }
}
