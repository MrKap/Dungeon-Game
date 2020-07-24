package unsw.dungeon;


import java.io.IOException;
import javafx.application.Application;
import javafx.stage.Stage;
/**
 * All screen set up goes here
 * @author z5177103
 * @author Emanuel kap
 *
 */
public class DungeonApplication extends Application {

	@Override
    public void start(Stage primaryStage) throws IOException {
		newDungeonGame(primaryStage);
        
    }

	public void newDungeonGame(Stage primaryStage) throws IOException{
		
		StartScreen startScreen = new StartScreen(primaryStage);
        PlayScreen playScreen = new PlayScreen(primaryStage);
        GameOverScreen gameOverScreen = new GameOverScreen(primaryStage, playScreen.getController().getDungeon());
        PauseScreen pauseScreen = new PauseScreen(primaryStage);
        GoalScreen goalScreen = new GoalScreen(primaryStage, playScreen.getController().getDungeon());
        AboutScreen aboutScreen = new AboutScreen(primaryStage);
        
        // All controllers need to know about the other screen.

        startScreen.getController().setPlayScreen(playScreen);
        startScreen.getController().setPauseScreen(pauseScreen);
        startScreen.getController().setGameOverScreen(gameOverScreen);
        startScreen.getController().setGoalScreen(goalScreen);
        startScreen.getController().setAboutScreen(aboutScreen);
        
        // aboutScreen controller only needs to know startScreen
        aboutScreen.getController().setStartScreen(startScreen);

        
        playScreen.getController().setGameOverScreen(gameOverScreen);
        playScreen.getController().setPauseScreen(pauseScreen);
        playScreen.getController().setGoalScreen(goalScreen);
        
        pauseScreen.getController().setGameOverScreen(gameOverScreen);
        pauseScreen.getController().setPlayScreen(playScreen);
        pauseScreen.getController().setPauseScreen(pauseScreen);
        pauseScreen.getController().setGoalScreen(goalScreen);
        
        // game over should restart the game
        gameOverScreen.getController().setPlayScreen(playScreen);
        gameOverScreen.getController().setGameOverScreen(gameOverScreen);
        gameOverScreen.getController().setGoalScreen(goalScreen);
        gameOverScreen.getController().setStartScreen(startScreen);
        
        //goalScreen.getController().setGameOverScreen(gameOverScreen);
        //goalScreen.getController().setPauseScreen(pauseScreen);
        goalScreen.getController().setGoalScreen(goalScreen);
        goalScreen.getController().setPlayScreen(playScreen);
        
//        gameCompletedScreen.getController().setPlayScreen(playScreen);
//        gameCompletedScreen.getController().setGameCompletedScreen(gameCompletedScreen);
        
        startScreen.start();
	}
    public static void main(String[] args) {
        launch(args);
    }

}
