package com.codecool.dungeoncrawl.logic.items;

import com.codecool.dungeoncrawl.logic.Cell;

public class Axe extends Weapons{
    public Axe(Cell cell) {

        super(cell);
        this.setDamage(5);
        this.setType(getClass().getName());
        this.setRange(1);
    }
}
