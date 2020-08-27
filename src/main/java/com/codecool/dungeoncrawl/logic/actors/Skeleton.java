package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;

public class Skeleton extends Actor {
    private int health = 6;
    private int damage = 2;

    public Skeleton(Cell cell) {
        super(cell);
    }

    public int getDamage(){ return damage; }
    public int getHealth(){ return health; }

    public void damageTaken(int dmg){ health -= dmg; }

    @Override
    public String getTileName() {
        return "skeleton";
    }
}
