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
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.*;
import java.util.ArrayList;

public class Main extends Application {
    private static int speechIndex;
    boolean escPressed=false;
    boolean isMainScene=false;
    boolean isGameScene=false;
    boolean isSettingScene=false;
    boolean isBabyGameScene=false;
    boolean isPapaGameScene=false;
    boolean isMamaGameScene=false;
    String previousScene = ""; //Refers to the previous scene, only used for going back correctly with the setting screen
    Group textGroup = new Group();
    private static Audio activeClip = null;
    Stage primaryStage;
    private Image image;
    private static final int SCENE_WIDTH = 1400;
    private static final int SCENE_HEIGHT = 800;
    StackPane mainPane = new StackPane();
    StackPane gamePane = new StackPane();
    StackPane settingsPane = new StackPane();
    StackPane babyGamePane = new StackPane();
    StackPane papaGamePane = new StackPane();
    StackPane mamaGamePane = new StackPane();
    Group mainGroup = new Group();
    Scene mainScene = new Scene(mainGroup,SCENE_WIDTH,SCENE_HEIGHT,true, SceneAntialiasing.BALANCED);
    private static final String PENGUIN_ICON_PATH = "/penguinIcon.png";
    private static final String PLAY_BUTTON_PATH = "/playButton.png";

    //Dialogue Lines
    String papaSpeech1 = "Hello again, friend!";
    String papaSpeech2 = "...";
    String papaSpeech3 = "play my game now i am papa";
    String[] papaSpeech = {papaSpeech1, papaSpeech2, papaSpeech3};

    String mamaSpeech1 = "i am mama hello little boy, im mama";
    String mamaSpeech2 = "with this mama i summon";
    String mamaSpeech3 = "placeholder mom text";
    String[] mamaSpeech = {mamaSpeech1, mamaSpeech2, mamaSpeech3};

    String babySpeech1 = "hello! i am baby penguin!";
    String babySpeech2 = "i found so many cool items in the ocean, but I don't know what they are!";
    String babySpeech3 = "i need your help to decide if i should recycle or throw away what i found!";
    String babySpeech4 = "if you think the item i found is recyclable, click on papa!";
    String babySpeech5 = "if you think the item should not be recycled, click on mama!";
    String babySpeech6 = "you will have 30 seconds to sort the treasures! you're going to do great!";
    String babySpeech7 = "when you are ready, click the right arrow to begin!";
    String[] babySpeech = {babySpeech1, babySpeech2, babySpeech3, babySpeech4, babySpeech5, babySpeech6, babySpeech7};


    String speech1="Hi there! I'm papa penguin. We have been through a lot of trouble this year.";
    String speech2="Global warming has been melting our icebergs";
    String speech3="....and it has been harder to find krill, our main source of food.";
    String speech4="Only you can help us!";
    String speech5="By recycling you can prevent global warming and save our homes!";
    String speech6="Click on us penguins to learn more!";
    boolean papaSpeaking = false;
    boolean mamaSpeaking = false;
    boolean babySpeaking = false;


    String[] speechArr = {speech1,speech2,speech3,speech4,speech5,speech6};

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
        setSettingsScene();
        mainGroup.getChildren().remove(settingsPane);
        setGameScene();
        mainGroup.getChildren().remove(gamePane);
        setBabyGameScene();
        mainGroup.getChildren().remove(babyGamePane);
        setPapaGameScene();
        mainGroup.getChildren().remove(papaGamePane);
        setMamaGameScene();
        mainGroup.getChildren().remove(mamaGamePane);
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
//                setGameScene();
                openGameScene();
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
                        if (isSettingScene)
                        {
                            goBackFromSettings(previousScene);
                        }
                        else {
//                        setSettingsScene();
                            updatePreviousScene(); //sets the previousScene variable to whatever scene ESCAPE was pressed on
                            System.out.println("Updated previousScene to " + previousScene);
                            openSettingsScene();
                        }
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
//        isMainScene=false;
//        isGameScene=true;
//        isSettingScene=false;
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
        Image leftArrowImage = new Image(new FileInputStream("src/Sprites/leftArrow.png"));
        ImageView leftArrowImageView = new ImageView(leftArrowImage);
        Image rightArrowImage = new Image(new FileInputStream("src/Sprites/rightArrow.png"));
        ImageView rightArrowImageView = new ImageView(rightArrowImage);
        Image shopImage = new Image(new FileInputStream("src/Sprites/shop.png"));
        ImageView shopImageView = new ImageView(shopImage);

        Text gameSpeech = new Text(speech1);


//        mainGroup.getChildren().remove(mainPane);
        gamePane.getChildren().addAll(iceburgImageView, gsbackButtonImageView, settingsButtonView,iglooImageView,papaPenguinImageView,mamaPenguinImageView,babyPenguinImageView,textBubbleImageView,papaPortraitImageView, mamaPortraitImageView, babyPortraitImageView, leftArrowImageView,rightArrowImageView, shopImageView);
        gamePane.getChildren().add(gameSpeech);
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

        textBubbleImageView.setFitHeight(250);
        textBubbleImageView.setFitWidth(1000);
        textBubbleImageView.setTranslateY(-100);
        textBubbleImageView.setTranslateX(250);

        papaPortraitImageView.setFitHeight(200);
        papaPortraitImageView.setFitWidth(200);
        papaPortraitImageView.setTranslateY(-100);
        papaPortraitImageView.setTranslateX(-100);

        mamaPortraitImageView.setFitHeight(200);
        mamaPortraitImageView.setFitWidth(200);
        mamaPortraitImageView.setTranslateY(-2000);
        mamaPortraitImageView.setTranslateX(-2000);

        babyPortraitImageView.setFitHeight(200);
        babyPortraitImageView.setFitWidth(200);
        babyPortraitImageView.setTranslateY(-2000);
        babyPortraitImageView.setTranslateX(-2000);

        leftArrowImageView.setFitHeight(60);
        leftArrowImageView.setFitWidth(60);
        leftArrowImageView.setTranslateY(-30);
        leftArrowImageView.setTranslateX(640);
        leftArrowImageView.setPreserveRatio(true);

        rightArrowImageView.setFitHeight(60);
        rightArrowImageView.setFitWidth(60);
        rightArrowImageView.setTranslateY(-30);
        rightArrowImageView.setTranslateX(700);
        rightArrowImageView.setPreserveRatio(true);

        shopImageView.setFitHeight(250);
        shopImageView.setFitWidth(250);
        shopImageView.setTranslateY(400);
        shopImageView.setTranslateX(770);
        shopImageView.setPreserveRatio(true);



        gameSpeech.setTranslateY(-100);
        gameSpeech.setTranslateX(350);

        gameSpeech.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 15));

        gsbackButtonImageView.setOnMouseClicked(mouseEvent ->
        {
            openMainScene();
//            backMain();
        });

        settingsButtonView.setOnMouseClicked(mouseEvent ->
                {
                    try
                    {
                        updatePreviousScene(); //sets the previousScene variable to whatever scene ESCAPE was pressed on
                        System.out.println("Updated previousScene to " + previousScene);
                        openSettingsScene();
                    }
                    catch(Exception C)
                    {
                        System.out.println("File Not Found: settingsButton");
                    }
                }
        );


        rightArrowImageView.setOnMouseClicked(mouseEvent ->
                {
                    if(speechIndex<speechArr.length-1)
                    {
                        speechIndex++;
                        gameSpeech.setText(speechArr[speechIndex]);
                    }
                    else if (speechIndex == speechArr.length-1)
                    {
                        System.out.println("reached end of text");
                        if (babySpeaking)
                        {
                            System.out.println("opening babyGameScene");
                            openBabyGameScene();
                        }
                        else if (papaSpeaking)
                        {
                            System.out.println("opening papaGameScene");
                            openPapaGameScene();
                        }
                        else if (mamaSpeaking)
                        {
                            System.out.println("opening mamaGameScene");
                            openMamaGameScene();
                        }
                    }
                }
        );

        leftArrowImageView.setOnMouseClicked(mouseEvent ->
                {
                    if(speechIndex>0)
                    {
                        speechIndex--;
                        gameSpeech.setText(speechArr[speechIndex]);
                    }
                }
        );

        papaPenguinImageView.setOnMouseClicked(mouseEvent ->
                {
                    speechArr = papaSpeech;
                    speechIndex = 0;
                    papaSpeaking = true;
                    babySpeaking = false;
                    mamaSpeaking = false;
                    gameSpeech.setText(papaSpeech1);
                    papaPortraitImageView.setFitHeight(200);
                    papaPortraitImageView.setFitWidth(200);
                    papaPortraitImageView.setTranslateY(-100);
                    papaPortraitImageView.setTranslateX(-100);

                    mamaPortraitImageView.setFitHeight(200);
                    mamaPortraitImageView.setFitWidth(200);
                    mamaPortraitImageView.setTranslateY(-2000);
                    mamaPortraitImageView.setTranslateX(-2000);

                    babyPortraitImageView.setFitHeight(200);
                    babyPortraitImageView.setFitWidth(200);
                    babyPortraitImageView.setTranslateY(-2000);
                    babyPortraitImageView.setTranslateX(-2000);

                }
        );

        mamaPenguinImageView.setOnMouseClicked(mouseEvent ->
                {
                    speechArr = mamaSpeech;
                    speechIndex = 0;
                    mamaSpeaking = true;
                    papaSpeaking = false;
                    babySpeaking = false;
                    gameSpeech.setText(mamaSpeech1);
                    mamaPortraitImageView.setFitHeight(200);
                    mamaPortraitImageView.setFitWidth(200);
                    mamaPortraitImageView.setTranslateY(-100);
                    mamaPortraitImageView.setTranslateX(-100);

                    papaPortraitImageView.setFitHeight(200);
                    papaPortraitImageView.setFitWidth(200);
                    papaPortraitImageView.setTranslateY(-2000);
                    papaPortraitImageView.setTranslateX(-2000);

                    babyPortraitImageView.setFitHeight(200);
                    babyPortraitImageView.setFitWidth(200);
                    babyPortraitImageView.setTranslateY(-2000);
                    babyPortraitImageView.setTranslateX(-2000);

                }
        );

        babyPenguinImageView.setOnMouseClicked(mouseEvent ->
                {
                    speechArr = babySpeech;
                    speechIndex = 0;
                    babySpeaking = true;
                    papaSpeaking = false;
                    mamaSpeaking = false;
                    gameSpeech.setText(babySpeech1);
                    babyPortraitImageView.setFitHeight(200);
                    babyPortraitImageView.setFitWidth(200);
                    babyPortraitImageView.setTranslateY(-100);
                    babyPortraitImageView.setTranslateX(-100);

                    papaPortraitImageView.setFitHeight(200);
                    papaPortraitImageView.setFitWidth(200);
                    papaPortraitImageView.setTranslateY(-2000);
                    papaPortraitImageView.setTranslateX(-2000);

                    mamaPortraitImageView.setFitHeight(200);
                    mamaPortraitImageView.setFitWidth(200);
                    mamaPortraitImageView.setTranslateY(-2000);
                    mamaPortraitImageView.setTranslateX(-2000);
                }
        );





    }

    public void setSettingsScene() throws FileNotFoundException
    {
//        isMainScene=false;
//        isGameScene=false;
//        isSettingScene=true;
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
//        mainGroup.getChildren().remove(mainPane);
//        mainGroup.getChildren().remove(gamePane);
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
            goBackFromSettings(previousScene);
            //backGame();
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
    public void setBabyGameScene() throws FileNotFoundException
    {
        Image bgsbackButtonImage = new Image(new FileInputStream("src/Sprites/backButton.png"));
        ImageView bgsbackButtonImageView = new ImageView(bgsbackButtonImage);
        Image papaPenguinImage = new Image(new FileInputStream("src/Sprites/papaPenguin.png"));
        ImageView papaPenguinImageView = new ImageView(papaPenguinImage);
        Image mamaPenguinImage = new Image(new FileInputStream("src/Sprites/mamaPenguin.png"));
        ImageView mamaPenguinImageView = new ImageView(mamaPenguinImage);
        Image babyPenguinImage = new Image(new FileInputStream("src/Sprites/babyPenguin.png"));
        ImageView babyPenguinImageView = new ImageView(babyPenguinImage);

        mainGroup.getChildren().add(babyGamePane);
        babyGamePane.getChildren().addAll(bgsbackButtonImageView, babyPenguinImageView);

        bgsbackButtonImageView.setFitHeight(100);
        bgsbackButtonImageView.setFitWidth(200);
        bgsbackButtonImageView.setTranslateY(0);
        bgsbackButtonImageView.setTranslateX(0);
        bgsbackButtonImageView.setPreserveRatio(true);

        babyPenguinImageView.setFitHeight(150);
        babyPenguinImageView.setFitWidth(150);
        babyPenguinImageView.setTranslateY(300);
        babyPenguinImageView.setTranslateX(200);
        babyPenguinImageView.setPreserveRatio(true);

        System.out.println("hello");

        bgsbackButtonImageView.setOnMouseClicked(mouseEvent ->
        {
            System.out.println("closing babyGameScene and opening papaGameScene");
            openGameScene();
            //backGame();
        });
    }
    public void setPapaGameScene() throws FileNotFoundException
    {
        Image pgsbackButtonImage = new Image(new FileInputStream("src/Sprites/backButton.png"));
        ImageView pgsbackButtonImageView = new ImageView(pgsbackButtonImage);
        Image papaPenguinImage = new Image(new FileInputStream("src/Sprites/papaPenguin.png"));
        ImageView papaPenguinImageView = new ImageView(papaPenguinImage);
        Image mamaPenguinImage = new Image(new FileInputStream("src/Sprites/mamaPenguin.png"));
        ImageView mamaPenguinImageView = new ImageView(mamaPenguinImage);
        Image babyPenguinImage = new Image(new FileInputStream("src/Sprites/babyPenguin.png"));
        ImageView babyPenguinImageView = new ImageView(babyPenguinImage);

        mainGroup.getChildren().add(papaGamePane);
        papaGamePane.getChildren().addAll(pgsbackButtonImageView, papaPenguinImageView);

        pgsbackButtonImageView.setFitHeight(100);
        pgsbackButtonImageView.setFitWidth(200);
        pgsbackButtonImageView.setTranslateY(0);
        pgsbackButtonImageView.setTranslateX(0);
        pgsbackButtonImageView.setPreserveRatio(true);

        papaPenguinImageView.setFitHeight(150);
        papaPenguinImageView.setFitWidth(150);
        papaPenguinImageView.setTranslateY(300);
        papaPenguinImageView.setTranslateX(200);
        papaPenguinImageView.setPreserveRatio(true);

        pgsbackButtonImageView.setOnMouseClicked(mouseEvent ->
        {
            System.out.println("closing papaGameScene and opening GameScene");
            openGameScene();
            //backGame();
        });

    }
    public void setMamaGameScene() throws FileNotFoundException
    {
        Image mgsbackButtonImage = new Image(new FileInputStream("src/Sprites/backButton.png"));
        ImageView mgsbackButtonImageView = new ImageView(mgsbackButtonImage);
        Image papaPenguinImage = new Image(new FileInputStream("src/Sprites/papaPenguin.png"));
        ImageView papaPenguinImageView = new ImageView(papaPenguinImage);
        Image mamaPenguinImage = new Image(new FileInputStream("src/Sprites/mamaPenguin.png"));
        ImageView mamaPenguinImageView = new ImageView(mamaPenguinImage);
        Image babyPenguinImage = new Image(new FileInputStream("src/Sprites/babyPenguin.png"));
        ImageView babyPenguinImageView = new ImageView(babyPenguinImage);

        mainGroup.getChildren().add(mamaGamePane);
        mamaGamePane.getChildren().addAll(mgsbackButtonImageView, mamaPenguinImageView);

        mgsbackButtonImageView.setFitHeight(100);
        mgsbackButtonImageView.setFitWidth(200);
        mgsbackButtonImageView.setTranslateY(0);
        mgsbackButtonImageView.setTranslateX(0);
        mgsbackButtonImageView.setPreserveRatio(true);

        mamaPenguinImageView.setFitHeight(150);
        mamaPenguinImageView.setFitWidth(150);
        mamaPenguinImageView.setTranslateY(300);
        mamaPenguinImageView.setTranslateX(200);
        mamaPenguinImageView.setPreserveRatio(true);

        mgsbackButtonImageView.setOnMouseClicked(mouseEvent ->
        {
            System.out.println("closing mamaGameScene and opening GameScene");
            openGameScene();
            //backGame();
        });

    }
    //updates isScene variables to whatever String is inputted into the parameter
    public void updateIsSceneVariables(String currentScene)
    {
        if (currentScene.equals("mainScene"))
        {
            isMainScene = true;
            isGameScene = false;
            isSettingScene = false;
            isBabyGameScene = false;
            isPapaGameScene = false;
            isMamaGameScene = false;
        }
        else if (currentScene.equals("gameScene"))
        {
            isMainScene = false;
            isGameScene = true;
            isSettingScene = false;
            isBabyGameScene = false;
            isPapaGameScene = false;
            isMamaGameScene = false;
        }
        else if (currentScene.equals("settingsScene")) {
            isMainScene = false;
            isGameScene = false;
            isSettingScene = true;
            isBabyGameScene = false;
            isPapaGameScene = false;
            isMamaGameScene = false;
        }
        else if (currentScene.equals("babyGameScene")) {
            isMainScene = false;
            isGameScene = false;
            isSettingScene = false;
            isBabyGameScene = true;
            isPapaGameScene = false;
            isMamaGameScene = false;
        }
        else if (currentScene.equals("papaGameScene")) {
            isMainScene = false;
            isGameScene = false;
            isSettingScene = false;
            isBabyGameScene = false;
            isPapaGameScene = true;
            isMamaGameScene = false;
        }
        else if (currentScene.equals("mamaGameScene")) {
            isMainScene = false;
            isGameScene = false;
            isSettingScene = false;
            isBabyGameScene = false;
            isPapaGameScene = false;
            isMamaGameScene = true;
        }


    }
    //updates previousScene based on what scene is currently open. previousScene is used for the logic of settingScene
    public void updatePreviousScene()
    {
        if (isMainScene)
        {
            previousScene = "mainScene";
        }
        else if (isGameScene)
        {
            previousScene = "gameScene";
        }
        else if (isBabyGameScene)
        {
            previousScene = "babyGameScene";
        }
        else if (isPapaGameScene)
        {
            previousScene = "papaGameScene";
        }
        else if (isMamaGameScene)
        {
            previousScene = "mamaGameScene";
        }
    }
    //Updates the scene from settings based on where the user last was when they clicked ESC
    public void goBackFromSettings(String previousScene)
    {
        System.out.println("closed settingsScene and added " + previousScene);
        if (previousScene.equals("mainScene"))
        {
            openMainScene();
            updateIsSceneVariables("mainScene"); //updates current scene to SettingsScene because SettingsScene is currently opened
        }
        else if (previousScene.equals("gameScene"))
        {
            openGameScene();
            updateIsSceneVariables("gameScene"); //updates current scene to SettingsScene because SettingsScene is currently opened
        }
        else if (previousScene.equals("babyGameScene"))
        {
            openBabyGameScene();
            updateIsSceneVariables("babyGameScene");
        }
        else if (previousScene.equals("papaGameScene"))
        {
            openPapaGameScene();
            updateIsSceneVariables("papaGameScene");
        }
        else if (previousScene.equals("mamaGameScene"))
        {
            openMamaGameScene();
            updateIsSceneVariables("mamaGameScene");
        }

    }
    //opens Game Scene and closes other scenes
    public void openGameScene()
    {
        mainGroup.getChildren().remove(mainPane);
        mainGroup.getChildren().remove(settingsPane);
        mainGroup.getChildren().remove(babyGamePane);
        mainGroup.getChildren().remove(papaGamePane);
        mainGroup.getChildren().remove(mamaGamePane);
        mainGroup.getChildren().add(gamePane);
        updateIsSceneVariables("gameScene"); //updates current scene to GameScene because SettingsScene is currently opened
    }
    //opens Main Scene and closes other scenes
    public void openMainScene()
    {
        mainGroup.getChildren().remove(gamePane);
        mainGroup.getChildren().remove(settingsPane);
        mainGroup.getChildren().remove(babyGamePane);
        mainGroup.getChildren().remove(papaGamePane);
        mainGroup.getChildren().remove(mamaGamePane);
        mainGroup.getChildren().add(mainPane);
        updateIsSceneVariables("mainScene"); //updates current scene to MainScene because SettingsScene is currently opened
    }
    //opens Settings Scene and closes other scenes
    public void openSettingsScene()
    {
        mainGroup.getChildren().remove(mainPane);
        mainGroup.getChildren().remove(gamePane);
        mainGroup.getChildren().remove(babyGamePane);
        mainGroup.getChildren().remove(papaGamePane);
        mainGroup.getChildren().remove(mamaGamePane);
        mainGroup.getChildren().add(settingsPane);
        updateIsSceneVariables("settingsScene"); //updates current scene to SettingsScene because SettingsScene is currently opened
    }
    //opens Settings Scene and closes other scenes
    public void openBabyGameScene()
    {
        mainGroup.getChildren().remove(mainPane);
        mainGroup.getChildren().remove(gamePane);
        mainGroup.getChildren().remove(settingsPane);
        mainGroup.getChildren().remove(papaGamePane);
        mainGroup.getChildren().remove(mamaGamePane);
        mainGroup.getChildren().add(babyGamePane);
        updateIsSceneVariables("babyGameScene"); //updates current scene to SettingsScene because SettingsScene is currently opened
    }
    //opens Settings Scene and closes other scenes
    public void openPapaGameScene()
    {
        mainGroup.getChildren().remove(mainPane);
        mainGroup.getChildren().remove(gamePane);
        mainGroup.getChildren().remove(settingsPane);
        mainGroup.getChildren().remove(babyGamePane);
        mainGroup.getChildren().remove(mamaGamePane);
        mainGroup.getChildren().add(papaGamePane);
        updateIsSceneVariables("papaGameScene"); //updates current scene to SettingsScene because SettingsScene is currently opened
    }
    //opens Settings Scene and closes other scenes
    public void openMamaGameScene()
    {
        mainGroup.getChildren().remove(mainPane);
        mainGroup.getChildren().remove(gamePane);
        mainGroup.getChildren().remove(settingsPane);
        mainGroup.getChildren().remove(babyGamePane);
        mainGroup.getChildren().remove(papaGamePane);
        mainGroup.getChildren().add(mamaGamePane);
        updateIsSceneVariables("mamaGameScene"); //updates current scene to SettingsScene because SettingsScene is currently opened
    }
//
//    public void backMain()
//    {
//        mainGroup.getChildren().remove(gamePane);
//        mainGroup.getChildren().add(mainPane);
//    }
//
//    public void backGame()
//    {
//        mainGroup.getChildren().remove(settingsPane);
//        mainGroup.getChildren().add(gamePane);
//    }

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