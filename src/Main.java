import javafx.application.Application;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.SceneAntialiasing;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class Main extends Application {
    Stage primaryStage;
    private Image image;
    private static final int SCENE_WIDTH = 1400;
    private static final int SCENE_HEIGHT = 800;
    StackPane mainPane = new StackPane();
    StackPane gamePane = new StackPane();
    StackPane settingsPane = new StackPane();
    Group mainGroup = new Group();
    Scene mainScene = new Scene(mainGroup,SCENE_WIDTH,SCENE_HEIGHT,true, SceneAntialiasing.BALANCED);
    private static final String PENGUIN_ICON_PATH = "/penguinIcon.png";
    private static final String PLAY_BUTTON_PATH = "/playButton.png";

    public static void main(String[] args)
    {
//        Windows windows = new Windows();
//        windows.initialize();
        launch(args);

    }

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        primaryStage.setScene(mainScene);
        primaryStage.show();
        setMainScene();


    }
    public void setMainScene() throws FileNotFoundException
    {
        Image penguinIcon = new Image(new FileInputStream("src/Sprites/PenguinIcon.png"));
        ImageView penguinIconView = new ImageView(penguinIcon);
        Image playButton = new Image(new FileInputStream("src/Sprites/PenguinIcon.png"));
        ImageView playButtonView = new ImageView(playButton);
        penguinIconView.setFocusTraversable(true);
        penguinIconView.setFitHeight(500);
        penguinIconView.setFitWidth(700);
        penguinIconView.setTranslateY(50);
        penguinIconView.setTranslateX(360);
        penguinIconView.setPreserveRatio(true);
        playButtonView.setTranslateX(360);
        playButtonView.setTranslateY(400);
        playButtonView.setFitHeight(200);
        playButtonView.setFitWidth(400);
        penguinIconView.setPreserveRatio(true);
        mainPane.getChildren().addAll(penguinIconView,playButtonView);
        mainGroup.getChildren().add(mainPane);
        playButtonView.setOnMouseClicked(mouseEvent ->
        {
            setGameScene();
        });

        mainScene.setOnKeyPressed((KeyEvent e) -> {
            KeyCode code = e.getCode();
            switch (code) {
                case ESCAPE:
                    setSettingsScene();
                default:
                    break;
            }
        });
    }

    public void setGameScene()
    {
        mainGroup.getChildren().remove(mainPane);
        mainGroup.getChildren().add(gamePane);
    }

    public void setSettingsScene()
    {
        mainGroup.getChildren().remove(mainPane);
        mainGroup.getChildren().remove(gamePane);
        mainGroup.getChildren().add(settingsPane);

    }




}