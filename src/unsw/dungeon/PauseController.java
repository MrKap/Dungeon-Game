package unsw.dungeon;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
/**
 * Pause Screen goes here
 * controller to view pause screen
 * @author z5177103
 *
 */
public class PauseController {

	PlayScreen playScreen;
	GameOverScreen gameOverScreen;
	PauseScreen pauseScreen;
	GoalScreen goalScreen;
	@FXML
	private Pane pane;
	
	@FXML
	private Button restartButton;
	
	@FXML
	private Button continueButton;
	
	
	@FXML
	void handleContinueButton(ActionEvent event) throws IOException {
		playScreen.start();
	}
	
	@FXML
	void handleRestartButton(ActionEvent event) throws IOException {
		playScreen.restart(gameOverScreen, pauseScreen, goalScreen);
	}
	
	
	//SETTERS
    public void setPlayScreen(PlayScreen playScreen) {
        this.playScreen = playScreen;
    }
    public void setPauseScreen(PauseScreen pauseScreen) {
        this.pauseScreen = pauseScreen;
    }
    public void setGameOverScreen(GameOverScreen gameOverScreen) {
        this.gameOverScreen = gameOverScreen;
    }
    public void setGoalScreen(GoalScreen goalScreen) {
        this.goalScreen = goalScreen;
    }
}
