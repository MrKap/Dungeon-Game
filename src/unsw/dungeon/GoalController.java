package unsw.dungeon;

import java.io.IOException;

import javax.swing.GroupLayout.Alignment;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

/**
 * controller to view all goals for goal Screens
 * sets the FXML
 * @author z5177103
 *
 */
public class GoalController {

	PlayScreen playScreen;
	GoalScreen goalScreen;
	GameOverScreen gameOverScreen;
	PauseScreen pauseScreen;
	
	private Dungeon dungeon;
	
	@FXML
    private Pane pane;
	
	@FXML
    private GridPane goalGrid;
	@FXML
	private Button continueButton;
	
	@FXML
	void handleContinueButton(ActionEvent event) throws IOException {
		playScreen.start();
	}
	public GoalController(Dungeon dungeon) {
		this.dungeon = dungeon;
	}
	@FXML
	public void initialize() {
		//TODO set the goals here
		// add into grid for each goals
		// dynamiccally update checkbox
		Goal goal = dungeon.getGoal();
		Text goalText = new Text(goal.getDesc());
		goalText.setX(5);
		goalText.setY(200);
		//pane.prefWidth(1000);
		pane.getChildren().add(goalText);
		//goalGrid.add(goalText, 0, 0);
		
		
	}
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
	public double getWidth() {
		return pane.getWidth();
	
	}
	public double getHeight() {
		// TODO Auto-generated method stub
		return pane.getHeight();
	}
}
