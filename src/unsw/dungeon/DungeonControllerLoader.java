package unsw.dungeon;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

/**
 * A DungeonLoader that also creates the necessary ImageViews for the UI,
 * connects them via listeners to the model, and creates a controller.
 * @author Robert Clifton-Everest
 *
 */
public class DungeonControllerLoader extends DungeonLoader {

	private final String COUNTER_STYLE = "-fx-pref-width: 2em; -fx-text-fill: black; -fx-font-weight: bold; -fx-font-size:1.35em";
    private List<ImageView> entities;
    
    private List<Node> others;

    //Images
    private Image playerImage;
    private Image wallImage;
    private Image boulderImage;
    private Image treasureImage;
    private Image exitImage;
    private Image doorImage;
    private Image openDoorImage;
    private Image keyImage;
    private Image switchImage;
    private Image portalImage;
    private Image enemyImage;
    private Image enemy2Image;
    private Image swordImage;
    private Image invincibilityImage;
    private Image inventoryImage;
    

    public DungeonControllerLoader(String filename)
            throws FileNotFoundException {
        super(filename);
        entities = new ArrayList<>();
        others = new ArrayList<>();
        playerImage = new Image("/human_new.png");
        wallImage = new Image("/brick_brown_0.png");
        boulderImage = new Image("/boulder.png");
        treasureImage = new Image("/gold_pile.png");
        exitImage = new Image("/exit.png");
        doorImage = new Image("/closed_door.png");
        openDoorImage = new Image("/open_door.png");
        keyImage = new Image("/key.png");
        switchImage = new Image("/pressure_plate.png");
        portalImage = new Image("/portal.png");
        enemyImage = new Image("/gnome.png");
        enemy2Image = new Image("/enemy2.png");
        swordImage = new Image("/greatsword_1_new.png");
        invincibilityImage = new Image("/brilliant_blue_new.png");
        inventoryImage = new Image("/inventory_single.png");
    }

    @Override
    public void onLoad(Entity player) {
        ImageView view = new ImageView(playerImage);
        view.setFitHeight(50);
        view.setFitWidth(50);
        addEntity(player, view);
    }

    @Override
    public void onLoad(Wall wall) {
        ImageView view = new ImageView(wallImage);
        view.setFitHeight(50);
        view.setFitWidth(50);
        addEntity(wall, view);
    }
    
    @Override
    public void onLoad(Boulder boulder) {
        ImageView view = new ImageView(boulderImage);
        view.setFitHeight(50);
        view.setFitWidth(50);
        addEntity(boulder, view);
    }

    @Override
	public void onLoad(Treasure treasure) {
        ImageView view = new ImageView(treasureImage);
        view.setFitHeight(50);
        view.setFitWidth(50);
        view.setPreserveRatio(true);
        addEntity(treasure, view);
    	
	}

	@Override
	public void onLoad(ExitSquare exit) {
        ImageView view = new ImageView(exitImage);
        view.setFitHeight(50);
        view.setFitWidth(50);
        addEntity(exit, view);
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onLoad(Door door) {
		ImageView view = new ImageView(doorImage);
        view.setFitHeight(50);
        view.setFitWidth(50);
		
		door.frontendDoorThing().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable,
                    Boolean oldValue, Boolean newValue) {
                if (newValue) {
                    view.setImage(openDoorImage);
                } else {
                    view.setImage(doorImage);
                } 
            }
        });
		
		addEntity(door, view);
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onLoad(Key key) {
        ImageView view = new ImageView(keyImage);
        view.setFitHeight(50);
        view.setFitWidth(50);
        addEntity(key, view);
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onLoad(FloorSwitch floorSwitch) {
        ImageView view = new ImageView(switchImage);
        view.setFitHeight(50);
        view.setFitWidth(50);
        view.setPreserveRatio(true);
        addEntity(floorSwitch, view);
		// TODO Auto-generated method stub
		
	}
	@Override
	public void onLoad(Portal portal) {
        ImageView view = new ImageView(portalImage);
        view.setFitHeight(50);
        view.setFitWidth(50);
        
        addEntity(portal, view);
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onLoad(Enemy enemy) {
		
		if(enemy.getEnemy2()!=0) {
	        ImageView view = new ImageView(enemy2Image);
	        view.setFitHeight(50);
	        view.setFitWidth(50);
	        view.setPreserveRatio(true);
	        addEntity(enemy, view);
			
		}
		
		else {
        ImageView view = new ImageView(enemyImage);
        view.setFitHeight(50);
        view.setFitWidth(50);
        view.setPreserveRatio(true);
        addEntity(enemy, view);
		// TODO Auto-generated method stub
		}
	}
	
	

	/**
	 * Each sword has a counter binds into it
	 * The counter will show after the sword is picked up
	 */
	@Override
	public void onLoad(Sword sword) {
        ImageView view = new ImageView(swordImage);
        view.setFitHeight(50);
        view.setFitWidth(50);
        TextField n = new TextField("0");
        n.setStyle(COUNTER_STYLE);
        n.textProperty().bindBidirectional(sword.hitsLeft());
        n.visibleProperty().bindBidirectional(sword.hitsVisibility());
        n.setBackground(Background.EMPTY);
        n.setId("sword");
        others.add(n);
        addEntity(sword, view);
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * Each Invincibility Potion has a counter binds into it
	 * The counter will show after the potion is picked up
	 */

	@Override
	public void onLoad(Invincibility invincibility) {
        ImageView view = new ImageView(invincibilityImage);
        view.setFitHeight(50);
        view.setFitWidth(50);
        TextField n = new TextField("0");
        n.setStyle(COUNTER_STYLE);
        n.textProperty().bindBidirectional(invincibility.timeLeft());
        n.visibleProperty().bindBidirectional(invincibility.timeVisibility());
        n.setBackground(Background.EMPTY);
        n.setId("potion");
        others.add(n);
        
        addEntity(invincibility, view);
		// TODO Auto-generated method stub
		
	}

	/**
	 * add Entity to entities list. enties will be set to track position first
	 * @param entity entity to be set
	 * @param view ImageView of current entity
	 */
	private void addEntity(Entity entity, ImageView view) {
        trackPosition(entity, view);
        entity.getOnMap().bindBidirectional(view.visibleProperty());
        
        entities.add(view);
    }

    /**
     * Set a node in a GridPane to have its position track the position of an
     * entity in the dungeon.
     *
     * By connecting the model with the view in this way, the model requires no
     * knowledge of the view and changes to the position of entities in the
     * model will automatically be reflected in the view.
     * @param entity
     * @param node
     */
    private void trackPosition(Entity entity, Node node) {
        GridPane.setColumnIndex(node, entity.getX());
        GridPane.setRowIndex(node, entity.getY());
       
        entity.x().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable,
                    Number oldValue, Number newValue) {
                GridPane.setColumnIndex(node, newValue.intValue());
            }
        });
        entity.y().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable,
                    Number oldValue, Number newValue) {
                GridPane.setRowIndex(node, newValue.intValue());
            }
        });

    }

    /**
     * Create a controller that can be attached to the DungeonView with all the
     * loaded entities.
     * @return
     * @throws FileNotFoundException
     */
    public DungeonController loadController() throws FileNotFoundException {
        return new DungeonController(load(), entities, others);
    }


}
