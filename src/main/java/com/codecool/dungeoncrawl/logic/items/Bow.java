package com.codecool.dungeoncrawl.logic.items;
import com.codecool.dungeoncrawl.logic.Cell;

public class Bow extends Weapons{
    public Bow(Cell cell) {
        super(cell);
        this.setDamage(4);
        this.setType(getClass().getName());
        this.setRange(3);
    }
}
