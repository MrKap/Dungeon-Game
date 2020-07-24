package unsw.dungeon;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
/**
 * Game Over Screen goes here.
 * Controller to set the GameOverScreen
 * @author z5177103
 *
 */


public class GameOverController {

	private StartScreen startScreen;
    private PlayScreen playScreen;
    private PauseScreen pauseScreen;
    private GameOverScreen gameOverScreen;
    private GoalScreen goalScreen;
    private Dungeon dungeon;
    
    @FXML
    private Pane pane;
    
    @FXML
    private Text text;

    @FXML
    private Text gameOver;

    @FXML
    private Button restartButton;
    
    @FXML
    private Button homeButton;

    @FXML
    void handleHomeButton(ActionEvent event) {
    	startScreen.start();
    }

    /**
     * handle the restart button to restart the game
     * @param event
     * @throws IOException
     */
    @FXML
    void handleRestartButton(ActionEvent event) throws IOException {
        playScreen.restart(gameOverScreen, pauseScreen, goalScreen);
    }
    
    @FXML
    /**
     * initialize the text and game over tex
     */
    public void initialize() {
    	// TODO change the text to game over/win
    	text.textProperty().bindBidirectional(dungeon.gameOverText());
    	if(dungeon.areGoalsSolved()) {
    		gameOver.setText("GAME COMPLETED ;)");
    	}
    }
    
    /**
     * GameOverController constructor
     * @param dungeon
     */
    public GameOverController(Dungeon dungeon) {
    	this.dungeon = dungeon;
    }
    
    // SETTERS
    public void setGameOverScreen(GameOverScreen gameOverScreen) {
    	
        this.gameOverScreen = gameOverScreen;
    }

    public void setPlayScreen(PlayScreen playScreen) {

        this.playScreen = playScreen;
    }
    public void setStartScreen(StartScreen startScreen) {

        this.startScreen = startScreen;
    }
    
    public void setPauseScreen(PauseScreen pauseScreen) {
        this.pauseScreen = pauseScreen;
    }
    
    public void setGoalScreen(GoalScreen goalScreen) {
        this.goalScreen = goalScreen;
    }
    

}
