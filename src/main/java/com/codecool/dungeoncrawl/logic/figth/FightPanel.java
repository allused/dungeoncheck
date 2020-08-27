package com.codecool.dungeoncrawl.logic.figth;

import com.codecool.dungeoncrawl.logic.GameMap;
import com.codecool.dungeoncrawl.logic.actors.Actor;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;


public class FightPanel extends Application {
    private GameMap gameMap;
    private Actor player;
    private Actor enemy;
    public Stage fightStage;

    public FightPanel(GameMap map, Actor xEnemy) {
        gameMap = map;
        player = map.getPlayer();
        enemy = xEnemy;
    }


    public Actor startPanel(){
        fightStage = new Stage();
        start(fightStage);
        return enemy;
    }

    @Override
    public void start(final Stage stage){
        refresh(fightStage);
    }

    public void playerAttack(int dmg, Button attackB){
        enemy.damageTaken(dmg);
        if (enemy.getHealth() <= 0){
            fightStage.close();
        } else {
        refresh(fightStage);
        }
    }

    public void refresh(final Stage fightStage){

        GridPane fightScene = new GridPane();
        fightScene.setPrefWidth(200);
        fightScene.setPadding(new Insets(10));
//        Player
        fightScene.add(new Label("Player: "), 0, 0);
        fightScene.add(new Label("Health: "), 1, 1);
        fightScene.add(new Label(""+player.getHealth()), 2, 1);
        fightScene.add(new Label("Damage: "), 1, 2);
        fightScene.add(new Label(""+player.getDamage()), 2, 2);
//        Enemy
        fightScene.add(new Label("Enemy: "), 0, 4);
        fightScene.add(new Label("Health: "), 1, 5);
        fightScene.add(new Label(""+enemy.getHealth()), 2, 5);
        fightScene.add(new Label("Damage: "), 1, 6);
        fightScene.add(new Label(""+enemy.getDamage()), 2, 6);

        GridPane buttonGrid = new GridPane();
        buttonGrid.setVgap(2.5);
        Button attackB = new Button("Attack");
        attackB.setOnAction(actionEvent -> playerAttack(player.getDamage(), attackB));
        buttonGrid.add(attackB, 0, 7);
        buttonGrid.add(new Button("Use Potion"), 0, 8);
        buttonGrid.add(new Button("Run"), 0, 9);
        fightScene.add(buttonGrid, 0, 7);

        Scene scene = new Scene(fightScene, 300, 300);
        fightStage.setScene(scene);
        fightStage.setTitle("FIGHT!");
        fightStage.showAndWait();

    }

    public GameMap getGameMap(){ return gameMap; }
}
