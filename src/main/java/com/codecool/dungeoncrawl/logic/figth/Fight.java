package com.codecool.dungeoncrawl.logic.figth;

import com.codecool.dungeoncrawl.logic.GameMap;
import com.codecool.dungeoncrawl.logic.actors.Actor;
import com.codecool.dungeoncrawl.logic.figth.FightPanel;

public class Fight {

    public String fight(GameMap map, Actor xEnemy){
        FightPanel fightPanel = new FightPanel(map, xEnemy);
        Actor enemy = fightPanel.startPanel();
        return determineResult(enemy, map.getPlayer());
    }

    public String determineResult(Actor enemy, Actor player){
        if (enemy.getHealth() <= 0) {
            return "WIN";
        } else if (player.getHealth() <= 0) {
            return "LOSE";
        } else {
            return "RUN";
        }
    }


}
