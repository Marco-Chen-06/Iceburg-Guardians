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
import java.io.*;

public class Main extends Application {
    boolean escPressed=false;
    boolean isMainScene=false;
    boolean isGameScene=false;
    boolean isSettingScene=false;
    private static Audio activeClip = null;
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

        playBackground();




    }
    public void setMainScene() throws FileNotFoundException
    {
        isMainScene=true;
        isGameScene=false;
        isSettingScene=false;
        Image penguinIcon = new Image(new FileInputStream("src/Sprites/penguinIcon.png"));
        ImageView penguinIconView = new ImageView(penguinIcon);
        Image playButton = new Image(new FileInputStream("src/Sprites/playButton.png"));
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
            try
            {
                setGameScene();
            }
            catch(Exception A)
            {
                System.out.println("File Not Found: GameScene");
                System.out.println(A);
            }
        });

        mainScene.setOnKeyPressed((KeyEvent e) -> {
            KeyCode code = e.getCode();
            switch (code) {
                case ESCAPE:

                    try
                    {
                        setSettingsScene();
                    }
                    catch(Exception B)
                    {
                        System.out.println("File Not Found: settingScene");
                    }

                default:
                    break;
            }
        });
    }

    public void setGameScene() throws FileNotFoundException
    {
        isMainScene=false;
        isGameScene=true;
        isSettingScene=false;
        Image iceburgImage = new Image(new FileInputStream("src/Sprites/iceburg.png"));
        ImageView iceburgImageView = new ImageView(iceburgImage);
        Image gsbackButtonImage = new Image(new FileInputStream("src/Sprites/backButton.png"));
        ImageView gsbackButtonImageView = new ImageView(gsbackButtonImage);
        Image settingsButton = new Image(new FileInputStream("src/Sprites/settingsButton.png"));
        ImageView settingsButtonView = new ImageView(settingsButton);
        Image iglooImage = new Image(new FileInputStream("src/Sprites/igloo.png"));
        ImageView iglooImageView = new ImageView(iglooImage);
        Image papaPenguinImage = new Image(new FileInputStream("src/Sprites/papaPenguin.png"));
        ImageView papaPenguinImageView = new ImageView(papaPenguinImage);
        Image mamaPenguinImage = new Image(new FileInputStream("src/Sprites/mamaPenguin.png"));
        ImageView mamaPenguinImageView = new ImageView(mamaPenguinImage);
        Image babyPenguinImage = new Image(new FileInputStream("src/Sprites/babyPenguin.png"));
        ImageView babyPenguinImageView = new ImageView(babyPenguinImage);
        Image papaPortraitImage = new Image(new FileInputStream("src/Sprites/papaPortrait.png"));
        ImageView papaPortraitImageView = new ImageView(papaPortraitImage);
        Image mamaPortraitImage = new Image(new FileInputStream("src/Sprites/mamaPortrait.png"));
        ImageView mamaPortraitImageView = new ImageView(mamaPortraitImage);
        Image babyPortraitImage = new Image(new FileInputStream("src/Sprites/babyPortrait.png"));
        ImageView babyPortraitImageView = new ImageView(babyPortraitImage);
        Image textBubbleImage = new Image(new FileInputStream("src/Sprites/textBubble.png"));
        ImageView textBubbleImageView = new ImageView(textBubbleImage);


        mainGroup.getChildren().remove(mainPane);
        gamePane.getChildren().addAll(iceburgImageView, gsbackButtonImageView, settingsButtonView,iglooImageView,papaPenguinImageView,mamaPenguinImageView,babyPenguinImageView,textBubbleImageView);
        mainGroup.getChildren().add(gamePane);

        iceburgImageView.setFitHeight(500);
        iceburgImageView.setFitWidth(1000);
        iceburgImageView.setTranslateY(270);
        iceburgImageView.setTranslateX(200);
        iceburgImageView.setPreserveRatio(true);

        gsbackButtonImageView.setFitHeight(100);
        gsbackButtonImageView.setFitWidth(200);
        gsbackButtonImageView.setTranslateY(-200);
        gsbackButtonImageView.setTranslateX(-400);
        gsbackButtonImageView.setPreserveRatio(true);

        settingsButtonView.setFitHeight(90);
        settingsButtonView.setFitWidth(90);
        settingsButtonView.setTranslateY(-200);
        settingsButtonView.setTranslateX(855);
        settingsButtonView.setPreserveRatio(true);

        iglooImageView.setFitHeight(170);
        iglooImageView.setFitWidth(170);
        iglooImageView.setTranslateY(140);
        iglooImageView.setTranslateX(200);
        iglooImageView.setPreserveRatio(true);

        papaPenguinImageView.setFitHeight(180);
        papaPenguinImageView.setFitWidth(180);
        papaPenguinImageView.setTranslateY(190);
        papaPenguinImageView.setTranslateX(400);
        papaPenguinImageView.setPreserveRatio(true);

        mamaPenguinImageView.setFitHeight(200);
        mamaPenguinImageView.setFitWidth(200);
        mamaPenguinImageView.setTranslateY(205);
        mamaPenguinImageView.setTranslateX(0);
        mamaPenguinImageView.setPreserveRatio(true);

        babyPenguinImageView.setFitHeight(150);
        babyPenguinImageView.setFitWidth(150);
        babyPenguinImageView.setTranslateY(300);
        babyPenguinImageView.setTranslateX(200);
        babyPenguinImageView.setPreserveRatio(true);

        textBubbleImageView.setFitHeight(200);
        textBubbleImageView.setFitWidth(400);
        textBubbleImageView.setTranslateY(-140);
        textBubbleImageView.setTranslateX(250);


        gsbackButtonImageView.setOnMouseClicked(mouseEvent ->
        {
            backMain();
        });

        settingsButtonView.setOnMouseClicked(mouseEvent ->
                {
                    try
                    {
                        setSettingsScene();
                    }
                    catch(Exception C)
                    {
                        System.out.println("File Not Found:sertingsButton");
                    }
                }
        );


    }

    public void setSettingsScene() throws FileNotFoundException
    {
        isMainScene=false;
        isGameScene=false;
        isSettingScene=true;
        Image ssbackButtonImage = new Image(new FileInputStream("src/Sprites/backButton.png"));
        ImageView ssbackButtonImageView = new ImageView(ssbackButtonImage);
        Image backgroundTextImage = new Image(new FileInputStream("src/Sprites/backgroundMusicText.png"));
        ImageView backgroundTextImageView = new ImageView(backgroundTextImage);
        Image blankOnImage = new Image(new FileInputStream("src/Sprites/blankOn.png"));
        ImageView blankOnImageView = new ImageView(blankOnImage);
        Image blankOffImage = new Image(new FileInputStream("src/Sprites/blankOff.png"));
        ImageView blankOffImageView = new ImageView(blankOffImage);
        Image greenOnImage = new Image(new FileInputStream("src/Sprites/greenOn.png"));
        ImageView greenOnImageView = new ImageView(greenOnImage);
        Image greenOffImage = new Image(new FileInputStream("src/Sprites/greenOff.png"));
        ImageView greenOffImageView = new ImageView(greenOffImage);
        mainGroup.getChildren().remove(mainPane);
        mainGroup.getChildren().remove(gamePane);
        mainGroup.getChildren().add(settingsPane);

        ssbackButtonImageView.setFitHeight(100);
        ssbackButtonImageView.setFitWidth(200);
        ssbackButtonImageView.setTranslateY(0);
        ssbackButtonImageView.setTranslateX(-100);
        ssbackButtonImageView.setPreserveRatio(true);

        backgroundTextImageView.setTranslateX(360);
        backgroundTextImageView.setTranslateY(100);
        backgroundTextImageView.setFitHeight(200);
        backgroundTextImageView.setFitWidth(400);
        backgroundTextImageView.setPreserveRatio(true);

        greenOnImageView.setTranslateX(610);
        greenOnImageView.setTranslateY(100);
        greenOnImageView.setFitHeight(100);
        greenOnImageView.setFitWidth(100);
        greenOnImageView.setPreserveRatio(true);

        blankOnImageView.setTranslateX(610);
        blankOnImageView.setTranslateY(100);
        blankOnImageView.setFitHeight(100);
        blankOnImageView.setFitWidth(100);
        blankOnImageView.setPreserveRatio(true);

        greenOffImageView.setTranslateX(710);
        greenOffImageView.setTranslateY(100);
        greenOffImageView.setFitHeight(100);
        greenOffImageView.setFitWidth(100);
        greenOffImageView.setPreserveRatio(true);

        blankOffImageView.setTranslateX(710);
        blankOffImageView.setTranslateY(100);
        blankOffImageView.setFitHeight(100);
        blankOffImageView.setFitWidth(100);
        blankOffImageView.setPreserveRatio(true);

        settingsPane.getChildren().addAll(ssbackButtonImageView, backgroundTextImageView, greenOnImageView, blankOffImageView);

        ssbackButtonImageView.setOnMouseClicked(mouseEvent ->
        {
            backGame();
        });

        blankOnImageView.setOnMouseClicked(mouseEvent ->
        {
            settingsPane.getChildren().removeAll(greenOffImageView,blankOnImageView);
            settingsPane.getChildren().addAll(greenOnImageView,blankOffImageView);
            playBackground();
        });

        blankOffImageView.setOnMouseClicked(mouseEvent ->
        {
            settingsPane.getChildren().removeAll(greenOnImageView,blankOffImageView);
            settingsPane.getChildren().addAll(greenOffImageView,blankOnImageView);
            stopBackground();
        });


    }

    public void backMain()
    {
        mainGroup.getChildren().remove(gamePane);
        mainGroup.getChildren().add(mainPane);
    }

    public void backGame()
    {
        mainGroup.getChildren().remove(settingsPane);
        mainGroup.getChildren().add(gamePane);
    }

    public void playBackground()
    {
        try
        {
            if (activeClip != null)
            {
                activeClip.stop();
            }
            activeClip = new Audio("src/Audio/Love119.wav");
            activeClip.play();
        }
        catch (Exception E)
        {
            System.out.println("ERROR: Audio");
        }
    }

    public void stopBackground()
    {
        try {
            if (activeClip != null) {
                activeClip.stop();
            }
        }
        catch(Exception E)
        {
            System.out.println("ERROR: Audio");
        }
    }



}