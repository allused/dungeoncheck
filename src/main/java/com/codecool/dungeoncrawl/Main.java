package com.codecool.dungeoncrawl;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.GameMap;
import com.codecool.dungeoncrawl.logic.MapLoader;
import com.codecool.dungeoncrawl.logic.Utility;
import com.codecool.dungeoncrawl.logic.actors.Actor;
import com.codecool.dungeoncrawl.logic.actors.Player;
import com.codecool.dungeoncrawl.logic.items.Item;
import com.codecool.dungeoncrawl.logic.items.Weapons;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.ArrayList;

public class Main extends Application {
    ArrayList<String>maps = new ArrayList<>();
    GameMap map = MapLoader.loadMap("/map.txt");
    Canvas canvas = new Canvas(
            map.getWidth() * Tiles.TILE_WIDTH,
            map.getHeight() * Tiles.TILE_WIDTH);
    GraphicsContext context = canvas.getGraphicsContext2D();
    Label healthLabel = new Label();
    Label damageLabel = new Label();
    GridPane inventoryGrid = new GridPane();
    ArrayList<Node> nodes = new ArrayList<>();


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        maps.add("/map.txt");
        maps.add("/map2.txt");
        inventoryGrid.setVgap(1.0);
        GridPane ui = new GridPane();
        ui.setPrefWidth(200);
        ui.setPadding(new Insets(10));

        ui.add(new Label("Health: "), 0, 0);
        ui.add(healthLabel, 1, 0);
        ui.add(new Label("Damage: "), 0, 1);
        ui.add(damageLabel, 1, 1);

        VBox inventoryBox = new VBox(inventoryGrid);

        ui.add(new Label("Inventory: "), 0, 2);
        ui.add(inventoryBox, 0, 3);

        BorderPane borderPane = new BorderPane();

        borderPane.setCenter(canvas);
        borderPane.setRight(ui);

        Scene scene = new Scene(borderPane);
        primaryStage.setScene(scene);
        refresh();
        scene.setOnKeyPressed(this::onKeyPressed);

        primaryStage.setTitle("Dungeon Crawl");
        primaryStage.show();


    }

    private void onKeyPressed(KeyEvent keyEvent) {
        switch (keyEvent.getCode()) {
            case UP:
                map.getPlayer().move(0, -1, map);
                refresh();
                break;
            case DOWN:
                map.getPlayer().move(0, 1, map);
                refresh();
                break;
            case LEFT:
                map.getPlayer().move(-1, 0, map);
                refresh();
                break;
            case RIGHT:
                map.getPlayer().move(1,0, map);
                refresh();
                break;
        }
    }

    private void refresh() {
        context.setFill(Color.BLACK);
        context.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        for (int x = 0; x < map.getWidth(); x++) {
            for (int y = 0; y < map.getHeight(); y++) {
                Cell cell = map.getCell(x, y);
                if (cell.getActor() != null) {
                    Tiles.drawTile(context, cell.getActor(), x, y);
                } else {
                    Tiles.drawTile(context, cell, x, y);
                }
            }

        }

        Player play = map.getPlayer();
        if (Utility.isGate(play, map)){
            map.setLevel(map.getLevel() + 1);
            map = MapLoader.loadMap(maps.get(map.getLevel()));
        }

        try {
            if ( Utility.isItem(play, map) ){
                Item currentItem = map.getCell(play.getX(), play.getY()).getItem();
                play.addItemToInventory(currentItem);
                Label item = new Label();
                item.setText(" - " + currentItem.getClass().getSimpleName());
                nodes.add(item);
                map.clearCell(play.getX(), play.getY(), map);

                inventoryGrid.add(item, 0,play.getInventory().size()+1 );
                play.setDamage(currentItem.getDamage());
            }
        } catch (Exception e){}
        healthLabel.setText("" + map.getPlayer().getHealth());
        damageLabel.setText("" + play.getDamage());
        Utility.monsterMove(MapLoader.monsters, map);
        Utility.changeGate(map, play);

    }
}
