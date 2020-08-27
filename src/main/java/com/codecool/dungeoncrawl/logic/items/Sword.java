package com.codecool.dungeoncrawl.logic.items;


import com.codecool.dungeoncrawl.logic.Cell;

public class Sword extends Weapons{


    public Sword(Cell cell) {
        super(cell);
        this.setRange(1);
        this.setDamage(4);

    }

}
