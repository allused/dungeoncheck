package com.codecool.dungeoncrawl.logic;

import com.codecool.dungeoncrawl.logic.actors.Actor;
import com.codecool.dungeoncrawl.logic.actors.Player;

import com.codecool.dungeoncrawl.logic.figth.Fight;
//import javafx.scene.media.Media;
//import javafx.scene.media.MediaPlayer;
import java.nio.file.Paths;

import com.codecool.dungeoncrawl.logic.items.Item;
import com.codecool.dungeoncrawl.logic.items.Key;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicBoolean;

public class Utility {

    public static Boolean isItem(Player player, GameMap map) {
        return !map.getCell(player.getX(), player.getY()).getItem().equals(null);
    }

        public static void monsterMove(ArrayList<Actor> monsters, GameMap map) {
            try {
                for (Actor monster : monsters) {
                    if (monsterAroundPlayer(monster.getX(), monster.getY(), map)) {
                        Fight fight = new Fight();
                        String result = fight.fight(map, monster);
                        switch (result) {
                            case "WIN":
                                map.clearCell(monster.getX(), monster.getY(), map);
                                monsters.remove(monster);
                            case "LOSE":
                                System.out.println("YOU LOSE");
                            case "RUN":
                                System.out.println("RUN");
                        }
                    } else {
                        map.clearCell(monster.getX(), monster.getY(), map);
                        monster.monsterMove(map);
                    }


                }
            } catch (Exception e) {
//                        System.out.println("dirr");
            }
        }
    public static Boolean isGate(Player player, GameMap map){
        return map.getCell(player.getX(), player.getY()).getType().equals(CellType.OPENGATE);
    }

    public static void clearItem(GameMap map) {
        map.getCell(map.getPlayer().getX(), map.getPlayer().getY()).setType(CellType.FLOOR);
    }

    public static void refreshInventory() {

    }


    public static boolean hasAKey(Actor actor) {

        for (Item item: actor.getInventory()){
            if (item instanceof Key) {
                return true;}
        }
        return false;
    }




        public static void clearMonster(GameMap map, Cell nextCell){ map.getCell(nextCell.getActor().getX(), nextCell.getActor().getY()).setType(CellType.FLOOR); }

    public static void changeGate(GameMap map, Actor actor) {
        if (hasAKey(actor)) {
            Cell[][] cells = map.getCells();
            for (int i = 0; i < map.getWidth(); i++) {
                for (int j = 0; j < map.getHeight(); j++) {
                    if (cells[i][j].getType().equals(CellType.GATE)) {
                        cells[i][j].setType(CellType.OPENGATE);
                        System.out.println("DURRRRRR");
                    }
                }


            }
        }
    }


        public static boolean monsterAroundPlayer(int monsterX, int monsterY, GameMap map){
                if (checkCoords(monsterX, map.getPlayer().getX()) && checkCoords(monsterY, map.getPlayer().getY())){
                        return true;
                }
                return false;
        }

        public static boolean checkCoords(int monsterCoord, int playerCoord){
                if (monsterCoord-1 == playerCoord || monsterCoord+1 == playerCoord || monsterCoord == playerCoord){
                        return true;
                }
                return false;
        }

//        public static Hashtable getMonsterCoords(int playerX, int playerY, GameMap map){
//                Hashtable monsterCoords = new Hashtable();
//                int[] xCoords = {playerX-1, playerX, playerX+1, playerX, playerX};
//                int[] yCoords = {playerY, playerY, playerY, playerY+1, playerY-1};
//                for (int i=0; i < xCoords.length; i++){
//                        if (isMonsterAround(xCoords[i], yCoords[i], map)){
//                                Hashtable newMonster = new Hashtable();
//                                newMonster.put("X", xCoords[i]);
//                                newMonster.put("Y", yCoords[i]);
//                                monsterCoords.put(String.format("Monster%d", i), newMonster);
//                        }
//                }
//                return monsterCoords;
//        }
//
//        public static boolean isMonsterAround(int xCoord, int yCoord, GameMap map){
//                if (map.getCell(xCoord, yCoord).getType().equals(CellType.MONSTER)){
//                        return true;
//                }
//                return false;
//        }





    public static int randomNum() {
        return ThreadLocalRandom.current().nextInt(-1, 1 + 1);
    }

}
