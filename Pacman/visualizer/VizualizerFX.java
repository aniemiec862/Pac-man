package Pacman.visualizer;

import Pacman.game.*;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import javafx.scene.input.KeyEvent;

public class VizualizerFX {
    private final Pane root;
    private final Map map;
    private final Engine engine;
    private final Tile[][] grid = new Tile[28][32];

    public VizualizerFX(Stage stage, Map map, Engine engine){
        this.root = new Pane();
        this.map = map;
        this.engine = engine;

        for(int i = 0; i < 28; i++){
            for(int j = 0; j < 32; j++){
                Vector2d position = new Vector2d(i, j);
                this.grid[i][j] = new Tile(20, position, this.map.objectAt(position));
                this.root.getChildren().add(this.grid[i][j]);
            }
        }

        stage.addEventFilter(KeyEvent.KEY_PRESSED, (key) -> {
            switch (key.getCode()) {
                case UP,W -> engine.setPlayerDirection(Direction.NORTH);
                case RIGHT,D -> engine.setPlayerDirection(Direction.EAST);
                case DOWN,S -> engine.setPlayerDirection(Direction.SOUTH);
                case LEFT,A -> engine.setPlayerDirection(Direction.WEST);
                //case ESCAPE -> engine.end();
            }
        });
    }
    public Pane getRoot() {
        return root;
    }

    public void changeColor(Vector2d position, AbstractMapElement object){
        this.grid[position.x][position.y].setContent(object);
    }
}