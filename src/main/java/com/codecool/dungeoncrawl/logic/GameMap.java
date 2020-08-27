package com.codecool.dungeoncrawl.logic;

import com.codecool.dungeoncrawl.logic.actors.Player;

public class GameMap {
    private int width;
    private int height;
    private Cell[][] cells;
    private int level;



    private Player player;

    public GameMap(int width, int height, CellType defaultCellType) {
        this.width = width;
        this.height = height;
        level = 0;
        cells = new Cell[width][height];
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                cells[x][y] = new Cell(this, x, y, defaultCellType);
            }
        }
    }
    public Cell[][] getCells() {
        return cells;
    }
    public void setLevel(int level) { this.level = level; }

    public int getLevel() { return level; }

    public Cell getCell(int x, int y) {
        return cells[x][y];
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }

    public void clearCell(int x, int y, GameMap map){
        cells[x][y] = new Cell(map,x,y, CellType.FLOOR);
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
