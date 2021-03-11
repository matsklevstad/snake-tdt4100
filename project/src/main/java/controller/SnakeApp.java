package controller;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.BoardModel;
import model.SnakeModel;
import utils.Dir;


public class SnakeApp extends Application {

	public static final int BOARD_WIDTH = 50;
	public static final int PIXEL_SIZE = 10;
	public static final int BOARD_HEIGHT = 50;
	public static final int SCOREBOARD_BANNER_HEIGHT = 30;   // Gitt at denne verdien går opp i pixel_size, hvis ikke så havner hodet litt utenfor. 
															 // BannerHeight må også være større enn pixelSize 
	
	
	@Override
	public void start(Stage primaryStage) {
		try {
			Parent parent = FXMLLoader.load(getClass().getResource("StartMenu.fxml"));
			primaryStage.setScene(new Scene(parent, BOARD_WIDTH * PIXEL_SIZE, BOARD_HEIGHT*PIXEL_SIZE));
			
			primaryStage.setTitle("Menu");
			primaryStage.show();
		}
		
		catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	//@Override
	public void startSnake(Stage primaryStage) throws Exception {
		
		BorderPane root = new BorderPane();
		Canvas canvas = new Canvas(BOARD_WIDTH*PIXEL_SIZE, BOARD_HEIGHT*PIXEL_SIZE);
		GraphicsContext graphicsContext = canvas.getGraphicsContext2D();
		root.getChildren().add(canvas);
		Scene scene = new Scene(root, BOARD_WIDTH*PIXEL_SIZE, BOARD_HEIGHT*PIXEL_SIZE);
		BoardModel board = new BoardModel(BOARD_WIDTH, BOARD_HEIGHT,PIXEL_SIZE);
		BoardController boardController = new BoardController(board);
		SnakeModel snake = new SnakeModel(5,5); 
		boardController.start(scene, graphicsContext, snake);
		
		scene.addEventFilter(KeyEvent.KEY_PRESSED, key -> {
			
			switch (key.getCode()) { 
				case W: 
				case UP:
					if (snake.getDirection() != Dir.down) {
						snake.setDirection(Dir.up);
					}
					
					System.out.println("OPP");
					break;
				case S:
				case DOWN:
					if (snake.getDirection() != Dir.up) {
						snake.setDirection(Dir.down);
					}
					System.out.println("NED");
					break;
				case A:
				case LEFT:
					if (snake.getDirection() != Dir.right) {
						snake.setDirection(Dir.left);
					}
					System.out.println("VENSTRE");
			
					break;
				case D:
				case RIGHT:
					System.out.println("HØYRE");
					if (snake.getDirection() != Dir.left) {
						snake.setDirection(Dir.right);
					}
					break;
				default:
					break;
			}
		});
		
		primaryStage.setTitle("Snake");
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}

	public static void main(String[] args) {
		launch(SnakeApp.class, args);
		System.out.println("Hei på deg!");

	}
}
