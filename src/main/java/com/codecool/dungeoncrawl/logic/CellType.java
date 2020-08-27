package com.codecool.dungeoncrawl.logic;

public enum CellType {
    EMPTY("empty"),
    FLOOR("floor"),
    MONSTER("monster"),
    AXE("axe"),
    SWORD("sword"),
    BOW("bow"),
    POTION("potion"),
    KEY("key"),
    GATE("gate"),
    OPENGATE("opengate"),
    WALL("wall");


    private final String tileName;

    CellType(String tileName) {
        this.tileName = tileName;
    }

    public String getTileName() {
        return tileName;
    }
}
