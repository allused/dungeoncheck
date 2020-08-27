package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.*;

import com.codecool.dungeoncrawl.logic.figth.Fight;
import com.codecool.dungeoncrawl.logic.figth.FightPanel;

import com.codecool.dungeoncrawl.logic.items.Item;
import javafx.stage.Stage;

import java.util.ArrayList;

public abstract class Actor implements Drawable {
    private Cell cell;
    private int health = 10;

    private ArrayList<Item> inventory = new ArrayList<>();

    private int damage = 0;

    public Actor(Cell cell) {
        this.cell = cell;
        this.cell.setActor(this);
    }
    public void monsterMove(GameMap map){
        try {
            Cell nextCell = cell.getNeighbor(Utility.randomNum(), Utility.randomNum());
            while(!nextCell.getType().equals(CellType.FLOOR)  ){
                nextCell = cell.getNeighbor(Utility.randomNum(), Utility.randomNum());
            }
            cell.setActor(null);
            nextCell.setActor(this);
            cell = nextCell;
        } catch (Exception e){
            System.out.println("dirr");
        }
    }

    public void move(int dx, int dy, GameMap map) {
        Cell nextCell = cell.getNeighbor(dx, dy);
        if(!nextCell.getType().equals(CellType.WALL) && !nextCell.getType().equals(CellType.MONSTER)) {
            cell.setActor(null);
            nextCell.setActor(this);
            cell = nextCell;
        }

//        }else if(nextCell.getType().equals(CellType.MONSTER)) {
//            Fight fight = new Fight();
//            String result = fight.fight(map, nextCell.getActor());
//            switch (result) {
//                case "WIN":
//                    map.clearCell(nextCell.getActor().getX(), nextCell.getActor().getY(), map);
//                    Utility.clearMonster(map, nextCell);
//                case "LOSE":
//                    System.out.println("YOU LOSE");
//                case "RUN":
//                    System.out.println("RUN");
//            }
//        }
    }

    public int getHealth() {
        return health;
    }

    public Cell getCell() {
        return cell;
    }

    public void damageTaken(int dmg){ health -= dmg; }

//    Positions

    public int getX() {
        return cell.getX();
    }

    public int getY() {
        return cell.getY();
    }
  
//    Inventory

    public void addItemToInventory(Item item){ inventory.add(item); }

    public void removeItemFromInventory(Item item){ inventory.remove(item); }


    public ArrayList<Item> getInventory(){ return inventory; }

//    Items

    public void setDamage(int itemDamage){
        damage += itemDamage;
    }

    public int getDamage(){ return damage; }

}
