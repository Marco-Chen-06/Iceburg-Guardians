import javafx.animation.FadeTransition;
import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.SceneAntialiasing;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.text.*;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Main extends Application {
    private static int iceCubes = 0; //currency for the game
    private static int speechIndex;
    private static int quizGacha = 0;
    private static int fishingSpeechIndex=0;
    boolean escPressed=false;
    boolean isMainScene=false;
    boolean isGameScene=false;
    boolean isSettingScene=false;
    boolean isBabyGameScene=false;
    boolean isPapaGameScene=false;
    boolean isMamaGameScene=false;
    boolean isShopScene=false;
    boolean isPullScene=false;
    boolean isInventoryScene=false;
    boolean itemInLake=false;
    boolean isCompost=false;
    boolean isLandfill=false;
    boolean isPlastic=false;
    boolean isBonus=false;
    int fishingCategoryGacha=-1;
    int fishingItemGacha=-1;
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
    StackPane shopPane = new StackPane();
    Group mainGroup = new Group();
    Scene mainScene = new Scene(mainGroup,SCENE_WIDTH,SCENE_HEIGHT,true, SceneAntialiasing.BALANCED);
    private static final String PENGUIN_ICON_PATH = "/penguinIcon.png";
    private static final String PLAY_BUTTON_PATH = "/playButton.png";

    //Dialogue Lines
    String papaSpeech1 = "Hello again, friend!";
    String papaSpeech2 = "We're gonna go fishing today!";
    String papaSpeech3 = "Click the right arrow if you want to join!";
    String[] papaSpeech = {papaSpeech1, papaSpeech2, papaSpeech3};

    String mamaSpeech1 = "Hey there I'm mama. Want to practice memory with me?";
    String mamaSpeech2 = "It's recycling themed!";
    String mamaSpeech3 = "Click to right arrow to start the game!";
    String[] mamaSpeech = {mamaSpeech1, mamaSpeech2, mamaSpeech3};

    String babySpeech1 = "Hello! I am baby penguin!";
    String babySpeech2 = "I have a lot of questions about the world!";
    String babySpeech3 = "Why are our iceburgs melting? ";
    String babySpeech4 = "What's it like outside of Antartica?";
    String babySpeech5 = "Anyways, today I'm going to put my knowledge to the test.";
    String babySpeech6 = "They are about the environment themed. Want to help me?";
    String babySpeech7 = "When you are ready, click the right arrow to begin!";
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


    String fishingSpeech1="Hi there! We need to fish for food!";
    String fishingSpeech2="However, sometimes we pick up garbage.";
    String fishingSpeech3="Can you help us sort out between compost, plastic, and landfill? ";
    String fishingSpeech4="Mama is in charge of plastic.";
    String fishingSpeech5="Baby is in charge of paper";
    String fishingSpeech6="....and I'm in charge of compost!";
    String fishingSpeech7="Click the pond to fish!";

    String[] fishingSpeechArr = {fishingSpeech1,fishingSpeech2,fishingSpeech3,fishingSpeech4,fishingSpeech5,fishingSpeech6,fishingSpeech7};

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
        setShopScene();
        mainGroup.getChildren().remove(shopPane);

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
        Image iceCubeImage = new Image(new FileInputStream("src/Sprites/icecubes.png"));
        ImageView iceCubeImageView = new ImageView(iceCubeImage);

        Text gameSpeech = new Text(speech1);
        Text iceCubeTextHolder = new Text("0");


//        mainGroup.getChildren().remove(mainPane);
        gamePane.getChildren().addAll(iceburgImageView, gsbackButtonImageView, settingsButtonView,iglooImageView,papaPenguinImageView,mamaPenguinImageView,babyPenguinImageView,textBubbleImageView,papaPortraitImageView, mamaPortraitImageView, babyPortraitImageView, leftArrowImageView,rightArrowImageView, shopImageView, iceCubeImageView, iceCubeTextHolder);
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

        iceCubeImageView.setFitHeight(75);
        iceCubeImageView.setFitWidth(75);
        iceCubeImageView.setTranslateY(500);
        iceCubeImageView.setTranslateX(-450);
        iceCubeImageView.setPreserveRatio(true);

        iceCubeTextHolder.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 30));
        iceCubeTextHolder.setTranslateY(495);
        iceCubeTextHolder.setTranslateX(-400);

        gameSpeech.setTranslateY(-100);
        gameSpeech.setTranslateX(350);

        gameSpeech.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 15));

        // calculates the initial width and set the WrappingWidth accordingly
        double initialWidth = iceCubeTextHolder.getLayoutBounds().getWidth();
        iceCubeTextHolder.setWrappingWidth(initialWidth - 200);

        iceCubeTextHolder.setTranslateX(-500);

        //creates a thread that constantly updates the text of gsIceCubeRunnable
        IceCubeRunnable gsIceCubeRunnable = new IceCubeRunnable(iceCubeTextHolder);
        Thread gsThread = new Thread(gsIceCubeRunnable);
        gsThread.start();


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

        shopImageView.setOnMouseClicked(mouseEvent ->
                {
                    System.out.println("opening shopScene");
                    openShopScene();
                });







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
        Image babyPenguinImage = new Image(new FileInputStream("src/Sprites/cleanbabyPenguin.png"));
        ImageView babyPenguinImageView = new ImageView(babyPenguinImage);
        Image textBubbleImage = new Image(new FileInputStream("src/Sprites/textBubble.png"));
        ImageView textBubbleImageView = new ImageView(textBubbleImage);
        Image firstChoiceImage = new Image(new FileInputStream("src/Sprites/textBubble.png"));
        ImageView firstChoiceImageView = new ImageView(firstChoiceImage);
        Image secondChoiceImage = new Image(new FileInputStream("src/Sprites/textBubble.png"));
        ImageView secondChoiceImageView = new ImageView(secondChoiceImage);
        Image thirdChoiceImage = new Image(new FileInputStream("src/Sprites/textBubble.png"));
        ImageView thirdChoiceImageView = new ImageView(thirdChoiceImage);
        Image fourthChoiceImage = new Image(new FileInputStream("src/Sprites/textBubble.png"));
        ImageView fourthChoiceImageView = new ImageView(fourthChoiceImage);
        Image babyPortraitImage = new Image(new FileInputStream("src/Sprites/babyPortrait.png"));
        ImageView babyPortraitImageView = new ImageView(babyPortraitImage);
        Image iceCubeImage = new Image(new FileInputStream("src/Sprites/icecubes.png"));
        ImageView iceCubeImageView = new ImageView(iceCubeImage);
        Text bgsIceCubeTextHolder = new Text("");
        Image confettiGIF = new Image(new FileInputStream("src/Effects/confetti2.gif"));
        ImageView confettiGIFImageView = new ImageView(confettiGIF);

        Text firstChoiceText = new Text ("Only because papa penguin said to!");
        Text secondChoiceText = new Text ("To help keep the Earth safe and clean!");
        Text thirdChoiceText = new Text ("Recycling is not important!");
        Text fourthChoiceText = new Text ("To build more factories!!!!!!!!!");
        Text questionText = new Text("Why is it important to recycle?");


        mainGroup.getChildren().add(babyGamePane);
        babyGamePane.getChildren().addAll(bgsbackButtonImageView, babyPenguinImageView,textBubbleImageView,babyPortraitImageView, firstChoiceImageView, secondChoiceImageView, thirdChoiceImageView, fourthChoiceImageView, firstChoiceText, secondChoiceText, thirdChoiceText, fourthChoiceText, questionText, iceCubeImageView, bgsIceCubeTextHolder, confettiGIFImageView);

        //hides the confetti initially
        //the real translate values are in the startConfettiAnimation() method
        confettiGIFImageView.setTranslateY(-1000);
        confettiGIFImageView.setTranslateX(-1000);
        confettiGIFImageView.setFitHeight(350);
        confettiGIFImageView.setFitWidth(500);
        confettiGIFImageView.setPreserveRatio(true);

        textBubbleImageView.setFitHeight(250);
        textBubbleImageView.setFitWidth(1000);
        textBubbleImageView.setTranslateY(-25);
        textBubbleImageView.setTranslateX(250);

        questionText.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 15));
        questionText.setTranslateY(-25);
        questionText.setTranslateX(250);
        double initialWidth5 = questionText.getLayoutBounds().getWidth();
        questionText.setWrappingWidth(initialWidth5 - 450);
        questionText.setTranslateX(-25);

        firstChoiceImageView.setFitHeight(150);
        firstChoiceImageView.setFitWidth(525);
        firstChoiceImageView.setTranslateY(225);
        firstChoiceImageView.setTranslateX(-175);

        firstChoiceText.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 15));
        firstChoiceText.setTranslateY(225);
        firstChoiceText.setTranslateX(-175);
        // calculates the initial width and set the WrappingWidth accordingly
        double initialWidth1 = firstChoiceText.getLayoutBounds().getWidth();
        firstChoiceText.setWrappingWidth(initialWidth1 - 450);
        firstChoiceText.setTranslateX(-450);

        secondChoiceImageView.setFitHeight(150);
        secondChoiceImageView.setFitWidth(525);
        secondChoiceImageView.setTranslateY(225);
        secondChoiceImageView.setTranslateX(550);

        secondChoiceText.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 15));
        secondChoiceText.setTranslateY(225);
        secondChoiceText.setTranslateX(550);
        // calculates the initial width and set the WrappingWidth accordingly
        double initialWidth2 = secondChoiceText.getLayoutBounds().getWidth();
        secondChoiceText.setWrappingWidth(initialWidth2 - 450);
        secondChoiceText.setTranslateX(275);

        thirdChoiceImageView.setFitHeight(150);
        thirdChoiceImageView.setFitWidth(525);
        thirdChoiceImageView.setTranslateY(475);
        thirdChoiceImageView.setTranslateX(-175);

        thirdChoiceText.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 15));
        thirdChoiceText.setTranslateY(475);
        thirdChoiceText.setTranslateX(-175);
        // calculates the initial width and set the WrappingWidth accordingly
        double initialWidth3 = thirdChoiceText.getLayoutBounds().getWidth();
        thirdChoiceText.setWrappingWidth(initialWidth3 - 450);
        thirdChoiceText.setTranslateX(-450);

        fourthChoiceImageView.setFitHeight(150);
        fourthChoiceImageView.setFitWidth(525);
        fourthChoiceImageView.setTranslateY(475);
        fourthChoiceImageView.setTranslateX(550);

        fourthChoiceText.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 15));
        fourthChoiceText.setTranslateY(475);
        fourthChoiceText.setTranslateX(550);
        // calculates the initial width and set the WrappingWidth accordingly
        double initialWidth4 = fourthChoiceText.getLayoutBounds().getWidth();
        fourthChoiceText.setWrappingWidth(initialWidth4 - 450);
        fourthChoiceText.setTranslateX(275);


        iceCubeImageView.setFitHeight(75);
        iceCubeImageView.setFitWidth(75);
        iceCubeImageView.setTranslateY(600);
        iceCubeImageView.setTranslateX(-450);
//        iceCubeImageView.setPreserveRatio(true);

        bgsIceCubeTextHolder.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 30));
        bgsIceCubeTextHolder.setTranslateY(600);
        bgsIceCubeTextHolder.setTranslateX(-400);

        // calculates the initial width and set the WrappingWidth accordingly
        double initialWidth = bgsIceCubeTextHolder.getLayoutBounds().getWidth();
        bgsIceCubeTextHolder.setWrappingWidth(initialWidth - 200);

        bgsIceCubeTextHolder.setTranslateX(-510);

        //creates a thread that constantly updates the text of gsIceCubeRunnable
        IceCubeRunnable bgsIceCubeRunnable = new IceCubeRunnable(bgsIceCubeTextHolder);
        Thread bgsThread = new Thread(bgsIceCubeRunnable);
        bgsThread.start();


        //I will code all the logic for quiz here and move down later possibly -----------
        firstChoiceImageView.setOnMouseClicked(mouseEvent ->
                {
                    if (quizGacha == 0) //why recycle question (incorrect)
                    {
                        updateQuestion(questionText, firstChoiceText, secondChoiceText, thirdChoiceText, fourthChoiceText);
                    }
                });
        secondChoiceImageView.setOnMouseClicked(mouseEvent ->
                {
                    if (quizGacha == 0) //why recycle question (correct)
                    {
                        iceCubes += 5;
                        updateQuestion(questionText, firstChoiceText, secondChoiceText, thirdChoiceText, fourthChoiceText);
                        startQuizConfettiAnimation(confettiGIFImageView);
                    }
                });
        thirdChoiceImageView.setOnMouseClicked(mouseEvent ->
                {
                    if (quizGacha == 0) //why recycle question (incorrect)
                    {
                        updateQuestion(questionText, firstChoiceText, secondChoiceText, thirdChoiceText, fourthChoiceText);
                    }
                });
        fourthChoiceImageView.setOnMouseClicked(mouseEvent ->
                {
                    if (quizGacha == 0) //why recycle question (incorrect)
                    {
                        updateQuestion(questionText, firstChoiceText, secondChoiceText, thirdChoiceText, fourthChoiceText);
                    }
                });

        //--------------------------------------------------------------------------------


        bgsbackButtonImageView.setFitHeight(100);
        bgsbackButtonImageView.setFitWidth(200);
        bgsbackButtonImageView.setTranslateY(-105);
        bgsbackButtonImageView.setTranslateX(-400);
        bgsbackButtonImageView.setPreserveRatio(true);

        babyPenguinImageView.setFitHeight(150);
        babyPenguinImageView.setFitWidth(150);
        babyPenguinImageView.setTranslateY(300);
        babyPenguinImageView.setTranslateX(200);
        babyPenguinImageView.setPreserveRatio(true);

        babyPortraitImageView.setFitHeight(200);
        babyPortraitImageView.setFitWidth(200);
        babyPortraitImageView.setTranslateY(-25);
        babyPortraitImageView.setTranslateX(-100);

        System.out.println("hello");

        bgsbackButtonImageView.setOnMouseClicked(mouseEvent ->
        {
            System.out.println("closing babyGameScene and opening papaGameScene");
            openGameScene();
            //backGame();
        });

    }
    private void updateQuestion(Text questionText, Text firstChoiceText, Text secondChoiceText, Text thirdChoiceText, Text fourthChoiceText)
    {
        quizGacha = getRandomNumber(0, 0); //rolls a random number from 0 to 0
        if (quizGacha == 0)
        {
            questionText.setText("Why is it important to recycle?");
            firstChoiceText.setText("Only because papa penguin said to!");
            secondChoiceText.setText("To help keep the Earth safe and clean!");
            thirdChoiceText.setText("Recycling is not important!");
            fourthChoiceText.setText("To build more factories!!!!!!!!!");
        }
    }
    public void setPapaGameScene() throws FileNotFoundException
    {
        Image pgsbackButtonImage = new Image(new FileInputStream("src/Sprites/backButton.png"));
        ImageView pgsbackButtonImageView = new ImageView(pgsbackButtonImage);
        Image papaPenguinImage = new Image(new FileInputStream("src/Sprites/cleanpapaPenguin.png"));
        ImageView papaPenguinImageView = new ImageView(papaPenguinImage);
        Image mamaPenguinImage = new Image(new FileInputStream("src/Sprites/cleanmamaPenguin.png"));
        ImageView mamaPenguinImageView = new ImageView(mamaPenguinImage);
        Image babyPenguinImage = new Image(new FileInputStream("src/Sprites/cleanbabyPenguin.png"));
        ImageView babyPenguinImageView = new ImageView(babyPenguinImage);
        Image fishingpondImage = new Image(new FileInputStream("src/Sprites/fishingPond.png"));
        ImageView fishingpondImageView = new ImageView(fishingpondImage);
        Image textBubbleImage = new Image(new FileInputStream("src/Sprites/textBubble.png"));
        ImageView textBubbleImageView = new ImageView(textBubbleImage);
        Image papaPortraitImage = new Image(new FileInputStream("src/Sprites/papaPortrait.png"));
        ImageView papaPortraitImageView = new ImageView(papaPortraitImage);
        Image leftArrowImage = new Image(new FileInputStream("src/Sprites/leftArrow.png"));
        ImageView leftArrowImageView = new ImageView(leftArrowImage);
        Image rightArrowImage = new Image(new FileInputStream("src/Sprites/rightArrow.png"));
        ImageView rightArrowImageView = new ImageView(rightArrowImage);
        Image shopImage = new Image(new FileInputStream("src/Sprites/shop.png"));
        Image iceCubeImage = new Image(new FileInputStream("src/Sprites/icecubes.png"));
        ImageView iceCubeImageView = new ImageView(iceCubeImage);
        Text pgsIceCubeTextHolder = new Text("");

        Image appleImage = new Image(new FileInputStream("src/MinigameSprites/Compost/apple.png"));
        Image bananaImage = new Image(new FileInputStream("src/MinigameSprites/Compost/banana.png"));
        Image cardboardImage = new Image(new FileInputStream("src/MinigameSprites/Landfill/cardboard.png"));
        Image newspaperImage = new Image(new FileInputStream("src/MinigameSprites/Landfill/newspaper.png"));
        Image milkImage = new Image(new FileInputStream("src/MinigameSprites/Plastic/milk.png"));
        Image waterBottleImage = new Image(new FileInputStream("src/MinigameSprites/Plastic/waterBottle.png"));
        Image fishImage = new Image(new FileInputStream("src/MinigameSprites/Bonus/fish.png"));
        Image krillImage = new Image(new FileInputStream("src/MinigameSprites/Bonus/krill.png"));
        Image confettiGIF = new Image(new FileInputStream("src/Effects/confetti.gif"));
        ImageView confettiGIFImageView = new ImageView(confettiGIF);

        Image[] compostArr = {appleImage, bananaImage};
        Image[] landfillArr = {cardboardImage, newspaperImage};
        Image[] plasticArr = {milkImage, waterBottleImage};
        Image[] bonusArr = {fishImage, krillImage};

        Image[][] fishingItemArr = {compostArr, landfillArr, plasticArr, bonusArr};

        ImageView fishingItemImageView = new ImageView(appleImage);

        fishingItemImageView.setFitHeight(175);
        fishingItemImageView.setFitWidth(175);
        fishingItemImageView.setTranslateY(-2000);
        fishingItemImageView.setTranslateX(-2000);
        fishingItemImageView.setPreserveRatio(true);

        //hides the confetti initially
        //the real translate values are in the startConfettiAnimation() method
        confettiGIFImageView.setTranslateY(-2000);
        confettiGIFImageView.setTranslateX(-2000);
        confettiGIFImageView.setFitHeight(350);
        confettiGIFImageView.setFitWidth(500);


        Text gameSpeech = new Text(fishingSpeech1);
        Text compostText = new Text("Compost");
        Text paperText = new Text("Landfill");
        Text plasticText = new Text("Plastic");

        mainGroup.getChildren().add(papaGamePane);
        papaGamePane.getChildren().addAll(pgsbackButtonImageView, papaPenguinImageView,mamaPenguinImageView,babyPenguinImageView,fishingpondImageView,textBubbleImageView,papaPortraitImageView,leftArrowImageView,rightArrowImageView, fishingItemImageView, confettiGIFImageView, iceCubeImageView, pgsIceCubeTextHolder);
        papaGamePane.getChildren().addAll(gameSpeech,compostText,paperText,plasticText);

        pgsbackButtonImageView.setFitHeight(100);
        pgsbackButtonImageView.setFitWidth(200);
        pgsbackButtonImageView.setTranslateY(-200);
        pgsbackButtonImageView.setTranslateX(-400);
        pgsbackButtonImageView.setPreserveRatio(true);

        papaPortraitImageView.setFitHeight(200);
        papaPortraitImageView.setFitWidth(200);
        papaPortraitImageView.setTranslateY(-100);
        papaPortraitImageView.setTranslateX(-100);

        textBubbleImageView.setFitHeight(250);
        textBubbleImageView.setFitWidth(1000);
        textBubbleImageView.setTranslateY(-100);
        textBubbleImageView.setTranslateX(250);

        papaPenguinImageView.setFitHeight(150);
        papaPenguinImageView.setFitWidth(150);
        papaPenguinImageView.setTranslateY(400);
        papaPenguinImageView.setTranslateX(700);
        papaPenguinImageView.setPreserveRatio(true);

        mamaPenguinImageView.setFitHeight(150);
        mamaPenguinImageView.setFitWidth(150);
        mamaPenguinImageView.setTranslateY(400);
        mamaPenguinImageView.setTranslateX(-200);
        mamaPenguinImageView.setPreserveRatio(true);

        babyPenguinImageView.setFitHeight(130);
        babyPenguinImageView.setFitWidth(130);
        babyPenguinImageView.setTranslateY(200);
        babyPenguinImageView.setTranslateX(-180);
        babyPenguinImageView.setPreserveRatio(true);

        fishingpondImageView.setFitHeight(500);
        fishingpondImageView.setFitWidth(1000);
        fishingpondImageView.setTranslateY(300);
        fishingpondImageView.setTranslateX(250);
        fishingpondImageView.setPreserveRatio(true);

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

        iceCubeImageView.setFitHeight(75);
        iceCubeImageView.setFitWidth(75);
        iceCubeImageView.setTranslateY(500);
        iceCubeImageView.setTranslateX(-450);
        iceCubeImageView.setPreserveRatio(true);

        pgsIceCubeTextHolder.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 30));
        pgsIceCubeTextHolder.setTranslateY(495);
        pgsIceCubeTextHolder.setTranslateX(-400);

        // calculates the initial width and set the WrappingWidth accordingly
        double initialWidth = pgsIceCubeTextHolder.getLayoutBounds().getWidth();
        pgsIceCubeTextHolder.setWrappingWidth(initialWidth - 200);

        pgsIceCubeTextHolder.setTranslateX(-510);

        //creates a thread that constantly updates the text of gsIceCubeRunnable
        IceCubeRunnable pgsIceCubeRunnable = new IceCubeRunnable(pgsIceCubeTextHolder);
        Thread pgsThread = new Thread(pgsIceCubeRunnable);
        pgsThread.start();



        gameSpeech.setTranslateY(-100);
        gameSpeech.setTranslateX(300);

        compostText.setTranslateY(320);
        compostText.setTranslateX(700);

        paperText.setTranslateY(140);
        paperText.setTranslateX(-180);

        plasticText.setTranslateY(325);
        plasticText.setTranslateX(-200);



        gameSpeech.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 15));
        compostText.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 15));
        paperText.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 15));
        plasticText.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 15));

        rightArrowImageView.setOnMouseClicked(mouseEvent ->
                {
                    if(fishingSpeechIndex < fishingSpeechArr.length-1)
                    {
                        fishingSpeechIndex++;
                        gameSpeech.setText(fishingSpeechArr[fishingSpeechIndex]);
                    }
//                    else if (fishingSpeechIndex == fishingSpeechArr.length-1)
//                    {
//                        System.out.println("reached end of text");
//                        if (babySpeaking)
//                        {
//                            System.out.println("opening babyGameScene");
//                            openBabyGameScene();
//                        }
//                        else if (papaSpeaking)
//                        {
//                            System.out.println("opening papaGameScene");
//                            openPapaGameScene();
//                        }
//                        else if (mamaSpeaking)
//                        {
//                            System.out.println("opening mamaGameScene");
//                            openMamaGameScene();
//                        }
//                    }
                }
        );

        leftArrowImageView.setOnMouseClicked(mouseEvent ->
                {
                    if(fishingSpeechIndex>0)
                    {
                        fishingSpeechIndex--;
                        gameSpeech.setText(fishingSpeechArr[fishingSpeechIndex]);
                    }
                }
        );



        pgsbackButtonImageView.setOnMouseClicked(mouseEvent ->
        {
            System.out.println("closing papaGameScene and opening GameScene");
            openGameScene();
            //backGame();
        });

        fishingpondImageView.setOnMouseClicked(mouseEvent ->
        {
            if (!(itemInLake)) {
                itemInLake = true;
                fishingCategoryGacha = getRandomNumber(0, 4); //generates number from 0 to 3
                fishingItemGacha = getRandomNumber(0, 2); //generates number from 0 to 2
                if (fishingCategoryGacha == 0) //refers to compostArr
                {
                    isCompost = true;
                    isLandfill = false;
                    isPlastic = false;
                    isBonus = false;
                }
                else if (fishingCategoryGacha == 1) //refers to landfillArr
                {
                    isCompost = false;
                    isLandfill = true;
                    isPlastic = false;
                    isBonus = false;
                }
                else if (fishingCategoryGacha == 2) //refers to plasticArr
                {
                    isCompost = false;
                    isLandfill = false;
                    isPlastic = true;
                    isBonus = false;
                }
                else if (fishingCategoryGacha == 3) //refers to bonusArr
                {
                    isCompost = false;
                    isLandfill = false;
                    isPlastic = false;
                    isBonus = true;
                }
                fishingItemImageView.setImage(fishingItemArr[fishingCategoryGacha][fishingItemGacha]);
//            fishingItemImageView.setImage()
                fishingItemImageView.setFitHeight(160);
                fishingItemImageView.setFitWidth(175);
                fishingItemImageView.setTranslateY(250);
                fishingItemImageView.setTranslateX(260);
                fishingItemImageView.setPreserveRatio(true);
            }
        });

        babyPenguinImageView.setOnMouseClicked(mouseEvent ->
        {
            if (isLandfill)
            {
                iceCubes += 1;
                System.out.println("landfill correct");
                startFishingConfettiAnimation(confettiGIFImageView);
//                confettiGIFImageView.setTranslateX(250);
//                confettiGIFImageView.setTranslateY(260);
            }
            else if (isBonus)
            {
                iceCubes += 5;
                System.out.println("was bonus, very good");
                startFishingConfettiAnimation(confettiGIFImageView);
//                confettiGIFImageView.setTranslateX(250);
//                confettiGIFImageView.setTranslateY(260);
            }
            else
            {
                System.out.println("not bonus or landfill");
//                confettiGIFImageView.setTranslateX(-2000);
//                confettiGIFImageView.setTranslateY(-2000);
            }
            fishingItemImageView.setTranslateY(-2000);
            fishingItemImageView.setTranslateX(-2000);
            isCompost = false;
            isLandfill = false;
            isPlastic = false;
            isBonus = false;
            itemInLake = false;
        });

        mamaPenguinImageView.setOnMouseClicked(mouseEvent ->
        {
            if (isPlastic)
            {
                iceCubes += 1;
                System.out.println("compost correct");
                startFishingConfettiAnimation(confettiGIFImageView);
            }
            else if (isBonus)
            {
                iceCubes += 5;
                System.out.println("was bonus, very good");
                startFishingConfettiAnimation(confettiGIFImageView);
            }
            else
            {
                System.out.println("not bonus or, it was not compost");
            }
            fishingItemImageView.setTranslateY(-2000);
            fishingItemImageView.setTranslateX(-2000);
            isCompost = false;
            isLandfill = false;
            isPlastic = false;
            isBonus = false;
            itemInLake = false;
            System.out.println(fishingCategoryGacha);
        });

        papaPenguinImageView.setOnMouseClicked(mouseEvent ->
        {
            if (isCompost)
            {
                iceCubes += 1;
                System.out.println("plastic correct");
                startFishingConfettiAnimation(confettiGIFImageView);
            }
            else if (isBonus)
            {
                iceCubes += 5;
                System.out.println("was bonus, very good");
                startFishingConfettiAnimation(confettiGIFImageView);
            }
            else
            {
                System.out.println("not bonus or plastic");
            }
            fishingItemImageView.setTranslateY(-2000);
            fishingItemImageView.setTranslateX(-2000);
            isCompost = false;
            isLandfill = false;
            isPlastic = false;
            isBonus = false;
            itemInLake = false;
        });

    }
    public void setMamaGameScene() throws FileNotFoundException
    {
        Image mgsbackButtonImage = new Image(new FileInputStream("src/Sprites/backButton.png"));
        ImageView mgsbackButtonImageView = new ImageView(mgsbackButtonImage);
        Image papaPenguinImage = new Image(new FileInputStream("src/Sprites/papaPenguin.png"));
        ImageView papaPenguinImageView = new ImageView(papaPenguinImage);
        Image mamaPenguinImage = new Image(new FileInputStream("src/Sprites/cleanmamaPenguin.png"));
        ImageView mamaPenguinImageView = new ImageView(mamaPenguinImage);
        Image babyPenguinImage = new Image(new FileInputStream("src/Sprites/babyPenguin.png"));
        ImageView babyPenguinImageView = new ImageView(babyPenguinImage);
        Image textBubbleImage = new Image(new FileInputStream("src/Sprites/textBubble.png"));
        ImageView textBubbleImageView = new ImageView(textBubbleImage);
        Image mamaPortraitImage = new Image(new FileInputStream("src/Sprites/mamaPortrait.png"));
        ImageView mamaPortraitImageView = new ImageView(mamaPortraitImage);
        Image defaultCardImage = new Image(new FileInputStream("src/MinigameSprites/MatchingGame/defaultCard.png"));
        Image iceCard = new Image(new FileInputStream("src/MinigameSprites/MatchingGame/iceCard.png"));
        ImageView defaultCardImageView1 = new ImageView(defaultCardImage);
        ImageView defaultCardImageView2 = new ImageView(defaultCardImage);
        ImageView defaultCardImageView3 = new ImageView(defaultCardImage);
        ImageView defaultCardImageView4 = new ImageView(defaultCardImage);
        ImageView defaultCardImageView5 = new ImageView(defaultCardImage);
        ImageView defaultCardImageView6 = new ImageView(defaultCardImage);
        ImageView defaultCardImageView7 = new ImageView(defaultCardImage);
        ImageView defaultCardImageView8 = new ImageView(defaultCardImage);
        ImageView defaultCardImageView9 = new ImageView(defaultCardImage);
        ImageView defaultCardImageView10 = new ImageView(defaultCardImage);
        ImageView defaultCardImageView11 = new ImageView(defaultCardImage);
        ImageView defaultCardImageView12 = new ImageView(defaultCardImage);
        ImageView defaultCardImageView13 = new ImageView(defaultCardImage);
        ImageView defaultCardImageView14 = new ImageView(defaultCardImage);
        ImageView defaultCardImageView15 = new ImageView(defaultCardImage);
        ImageView defaultCardImageView16 = new ImageView(defaultCardImage);


        ImageView placeholderView1 = new ImageView(iceCard);
        ImageView placeholderView2 = new ImageView(iceCard);
        ImageView placeholderView3 = new ImageView(iceCard);
        ImageView placeholderView4 = new ImageView(iceCard);
        ImageView placeholderView5 = new ImageView(iceCard);
        ImageView placeholderView6 = new ImageView(iceCard);
        ImageView placeholderView7 = new ImageView(iceCard);
        ImageView placeholderView8 = new ImageView(iceCard);
        ImageView placeholderView9 = new ImageView(iceCard);
        ImageView placeholderView10 = new ImageView(iceCard);
        ImageView placeholderView11 = new ImageView(iceCard);
        ImageView placeholderView12 = new ImageView(iceCard);
        ImageView placeholderView13 = new ImageView(iceCard);
        ImageView placeholderView14 = new ImageView(iceCard);
        ImageView placeholderView15 = new ImageView(iceCard);
        ImageView placeholderView16 = new ImageView(iceCard);

        ArrayList<ImageView> placeholderList = new ArrayList<>();
        placeholderList.addAll(Arrays.asList(placeholderView1,placeholderView2,placeholderView3,placeholderView4,placeholderView5,placeholderView6,placeholderView7,placeholderView8,placeholderView9,placeholderView10,placeholderView11,placeholderView12,placeholderView13,placeholderView14,placeholderView15,placeholderView16));

        mainGroup.getChildren().add(mamaGamePane);
        mamaGamePane.getChildren().add(textBubbleImageView);
        mamaGamePane.getChildren().addAll(placeholderView1,placeholderView2,placeholderView3,placeholderView4,placeholderView5,placeholderView6,placeholderView7,placeholderView8,placeholderView9,placeholderView10,placeholderView11,placeholderView12,placeholderView13,placeholderView14,placeholderView15,placeholderView16);
        mamaGamePane.getChildren().addAll(mgsbackButtonImageView, mamaPenguinImageView,mamaPortraitImageView,defaultCardImageView1,defaultCardImageView2,defaultCardImageView3,defaultCardImageView4,defaultCardImageView5,defaultCardImageView6,defaultCardImageView7,defaultCardImageView8,defaultCardImageView9,defaultCardImageView10,defaultCardImageView11,defaultCardImageView12,defaultCardImageView13,defaultCardImageView14,defaultCardImageView15,defaultCardImageView16);

        mamaPortraitImageView.setFitHeight(200);
        mamaPortraitImageView.setFitWidth(200);
        mamaPortraitImageView.setTranslateY(0);
        mamaPortraitImageView.setTranslateX(-100);

        textBubbleImageView.setFitHeight(250);
        textBubbleImageView.setFitWidth(1000);
        textBubbleImageView.setTranslateY(0);
        textBubbleImageView.setTranslateX(250);

        mgsbackButtonImageView.setFitHeight(100);
        mgsbackButtonImageView.setFitWidth(200);
        mgsbackButtonImageView.setTranslateY(-80);
        mgsbackButtonImageView.setTranslateX(-400);
        mgsbackButtonImageView.setPreserveRatio(true);

        mamaPenguinImageView.setFitHeight(150);
        mamaPenguinImageView.setFitWidth(150);
        mamaPenguinImageView.setTranslateY(50);
        mamaPenguinImageView.setTranslateX(-400);
        mamaPenguinImageView.setPreserveRatio(true);

        placeholderView1.setFitHeight(80);
        placeholderView1.setFitWidth(80);
        placeholderView1.setTranslateY(200);
        placeholderView1.setTranslateX(0);
        placeholderView1.setPreserveRatio(false);

        defaultCardImageView1.setFitHeight(125);
        defaultCardImageView1.setFitWidth(125);
        defaultCardImageView1.setTranslateY(200);
        defaultCardImageView1.setTranslateX(10);
        defaultCardImageView1.setPreserveRatio(true);

        defaultCardImageView2.setFitHeight(125);
        defaultCardImageView2.setFitWidth(125);
        defaultCardImageView2.setTranslateY(325);
        defaultCardImageView2.setTranslateX(10);
        defaultCardImageView2.setPreserveRatio(true);

        defaultCardImageView3.setFitHeight(125);
        defaultCardImageView3.setFitWidth(125);
        defaultCardImageView3.setTranslateY(450);
        defaultCardImageView3.setTranslateX(10);
        defaultCardImageView3.setPreserveRatio(true);

        defaultCardImageView4.setFitHeight(125);
        defaultCardImageView4.setFitWidth(125);
        defaultCardImageView4.setTranslateY(575);
        defaultCardImageView4.setTranslateX(10);
        defaultCardImageView4.setPreserveRatio(true);

        defaultCardImageView5.setFitHeight(125);
        defaultCardImageView5.setFitWidth(125);
        defaultCardImageView5.setTranslateY(200);
        defaultCardImageView5.setTranslateX(135);
        defaultCardImageView5.setPreserveRatio(true);

        defaultCardImageView6.setFitHeight(125);
        defaultCardImageView6.setFitWidth(125);
        defaultCardImageView6.setTranslateY(325);
        defaultCardImageView6.setTranslateX(135);
        defaultCardImageView6.setPreserveRatio(true);

        defaultCardImageView7.setFitHeight(125);
        defaultCardImageView7.setFitWidth(125);
        defaultCardImageView7.setTranslateY(450);
        defaultCardImageView7.setTranslateX(135);
        defaultCardImageView7.setPreserveRatio(true);

        defaultCardImageView8.setFitHeight(125);
        defaultCardImageView8.setFitWidth(125);
        defaultCardImageView8.setTranslateY(575);
        defaultCardImageView8.setTranslateX(135);
        defaultCardImageView8.setPreserveRatio(true);

        defaultCardImageView9.setFitHeight(125);
        defaultCardImageView9.setFitWidth(125);
        defaultCardImageView9.setTranslateY(200);
        defaultCardImageView9.setTranslateX(260);
        defaultCardImageView9.setPreserveRatio(true);

        defaultCardImageView10.setFitHeight(125);
        defaultCardImageView10.setFitWidth(125);
        defaultCardImageView10.setTranslateY(325);
        defaultCardImageView10.setTranslateX(260);
        defaultCardImageView10.setPreserveRatio(true);

        defaultCardImageView11.setFitHeight(125);
        defaultCardImageView11.setFitWidth(125);
        defaultCardImageView11.setTranslateY(450);
        defaultCardImageView11.setTranslateX(260);
        defaultCardImageView11.setPreserveRatio(true);

        defaultCardImageView12.setFitHeight(125);
        defaultCardImageView12.setFitWidth(125);
        defaultCardImageView12.setTranslateY(575);
        defaultCardImageView12.setTranslateX(260);
        defaultCardImageView12.setPreserveRatio(true);

        defaultCardImageView13.setFitHeight(125);
        defaultCardImageView13.setFitWidth(125);
        defaultCardImageView13.setTranslateY(200);
        defaultCardImageView13.setTranslateX(385);
        defaultCardImageView13.setPreserveRatio(true);

        defaultCardImageView14.setFitHeight(125);
        defaultCardImageView14.setFitWidth(125);
        defaultCardImageView14.setTranslateY(325);
        defaultCardImageView14.setTranslateX(385);
        defaultCardImageView14.setPreserveRatio(true);

        defaultCardImageView15.setFitHeight(125);
        defaultCardImageView15.setFitWidth(125);
        defaultCardImageView15.setTranslateY(450);
        defaultCardImageView15.setTranslateX(385);
        defaultCardImageView15.setPreserveRatio(true);

        defaultCardImageView16.setFitHeight(125);
        defaultCardImageView16.setFitWidth(125);
        defaultCardImageView16.setTranslateY(575);
        defaultCardImageView16.setTranslateX(385);
        defaultCardImageView16.setPreserveRatio(true);





        mgsbackButtonImageView.setOnMouseClicked(mouseEvent ->
        {
            System.out.println("closing mamaGameScene and opening GameScene");
            openGameScene();
            //backGame();
        });

    }

    public void setShopScene() throws FileNotFoundException
    {
        Image sgsbackButtonImage = new Image(new FileInputStream("src/Sprites/backButton.png"));
        ImageView sgsbackButtonImageView = new ImageView(sgsbackButtonImage);
        Image textBubbleImage = new Image(new FileInputStream("src/Sprites/textBubble.png"));
        ImageView textBubbleImageView = new ImageView(textBubbleImage);
        Image mamaPortraitImage = new Image(new FileInputStream("src/Sprites/mamaPortrait.png"));
        ImageView mamaPortraitImageView = new ImageView(mamaPortraitImage);
        Image eggImage = new Image(new FileInputStream("src/Sprites/mysteryEgg.png"));
        ImageView eggImageView = new ImageView(eggImage);
        Image buyButton = new Image(new FileInputStream("src/MinigameSprites/Shop/buyButton.png"));
        ImageView buyButtonView = new ImageView(buyButton);
        Image iceCubeImage = new Image(new FileInputStream("src/Sprites/icecubes.png"));
        ImageView iceCubeImageView = new ImageView(iceCubeImage);

        Text shopText = new Text("Use icecubes to hatch and win a penguin plush!");
        Text pgsIceCubeTextHolder = new Text("");

        mainGroup.getChildren().add(shopPane);
        shopPane.getChildren().add(textBubbleImageView);
        shopPane.getChildren().addAll(sgsbackButtonImageView,mamaPortraitImageView, eggImageView,buyButtonView,shopText,iceCubeImageView,pgsIceCubeTextHolder);

        mamaPortraitImageView.setFitHeight(200);
        mamaPortraitImageView.setFitWidth(200);
        mamaPortraitImageView.setTranslateY(0);
        mamaPortraitImageView.setTranslateX(-100);

        textBubbleImageView.setFitHeight(250);
        textBubbleImageView.setFitWidth(1000);
        textBubbleImageView.setTranslateY(0);
        textBubbleImageView.setTranslateX(250);

        sgsbackButtonImageView.setFitHeight(100);
        sgsbackButtonImageView.setFitWidth(200);
        sgsbackButtonImageView.setTranslateY(-130);
        sgsbackButtonImageView.setTranslateX(-400);
        sgsbackButtonImageView.setPreserveRatio(true);

        eggImageView.setFitHeight(350);
        eggImageView.setFitWidth(350);
        eggImageView.setTranslateY(300);
        eggImageView.setTranslateX(200);
        eggImageView.setPreserveRatio(true);

        buyButtonView.setFitHeight(100);
        buyButtonView.setFitWidth(200);
        buyButtonView.setTranslateY(500);
        buyButtonView.setTranslateX(195);
        buyButtonView.setPreserveRatio(true);

        iceCubeImageView.setFitHeight(75);
        iceCubeImageView.setFitWidth(75);
        iceCubeImageView.setTranslateY(570);
        iceCubeImageView.setTranslateX(-450);
        iceCubeImageView.setPreserveRatio(true);

        shopText.setTranslateY(0);
        shopText.setTranslateX(350);

        pgsIceCubeTextHolder.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 30));
        pgsIceCubeTextHolder.setTranslateY(565);
        pgsIceCubeTextHolder.setTranslateX(-400);

        // calculates the initial width and set the WrappingWidth accordingly
        double initialWidth = pgsIceCubeTextHolder.getLayoutBounds().getWidth();
        pgsIceCubeTextHolder.setWrappingWidth(initialWidth - 200);

        pgsIceCubeTextHolder.setTranslateX(-510);

        //creates a thread that constantly updates the text of gsIceCubeRunnable
        IceCubeRunnable pgsIceCubeRunnable = new IceCubeRunnable(pgsIceCubeTextHolder);
        Thread pgsThread = new Thread(pgsIceCubeRunnable);
        pgsThread.start();

        shopText.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 15));


        sgsbackButtonImageView.setOnMouseClicked(mouseEvent ->
        {
            System.out.println("closing shopScene and opening GameScene");
            openGameScene();
            //backGame();
        });

        buyButtonView.setOnMouseClicked(mouseEvent ->
        {

        });

    }

    public void setPullScene()
    {

    }

    public void setInventoryScene()
    {

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
            isShopScene = false;
            isPullScene = false;
            isInventoryScene = false;
        }
        else if (currentScene.equals("gameScene"))
        {
            isMainScene = false;
            isGameScene = true;
            isSettingScene = false;
            isBabyGameScene = false;
            isPapaGameScene = false;
            isMamaGameScene = false;
            isShopScene = false;
            isPullScene = false;
            isInventoryScene = false;
        }
        else if (currentScene.equals("settingsScene")) {
            isMainScene = false;
            isGameScene = false;
            isSettingScene = true;
            isBabyGameScene = false;
            isPapaGameScene = false;
            isMamaGameScene = false;
            isShopScene = false;
            isPullScene = false;
            isInventoryScene = false;
        }
        else if (currentScene.equals("babyGameScene")) {
            isMainScene = false;
            isGameScene = false;
            isSettingScene = false;
            isBabyGameScene = true;
            isPapaGameScene = false;
            isMamaGameScene = false;
            isShopScene = false;
            isPullScene = false;
            isInventoryScene = false;
        }
        else if (currentScene.equals("papaGameScene")) {
            isMainScene = false;
            isGameScene = false;
            isSettingScene = false;
            isBabyGameScene = false;
            isPapaGameScene = true;
            isMamaGameScene = false;
            isShopScene = false;
            isPullScene = false;
            isInventoryScene = false;
        }
        else if (currentScene.equals("mamaGameScene")) {
            isMainScene = false;
            isGameScene = false;
            isSettingScene = false;
            isBabyGameScene = false;
            isPapaGameScene = false;
            isMamaGameScene = true;
            isShopScene = false;
            isPullScene = false;
            isInventoryScene = false;
        }
        else if (currentScene.equals("shopScene"))
        {
            isMainScene = false;
            isGameScene = false;
            isSettingScene = false;
            isBabyGameScene = false;
            isPapaGameScene = false;
            isMamaGameScene = false;
            isShopScene = true;
            isPullScene = false;
            isInventoryScene = false;
        }
        else if (currentScene.equals("pullScene"))
        {
            isMainScene = false;
            isGameScene = false;
            isSettingScene = false;
            isBabyGameScene = false;
            isPapaGameScene = false;
            isMamaGameScene = false;
            isShopScene = false;
            isPullScene = true;
            isInventoryScene = false;
        }
        else if (currentScene.equals("inventoryScene"))
        {
            isMainScene = false;
            isGameScene = false;
            isSettingScene = false;
            isBabyGameScene = false;
            isPapaGameScene = false;
            isMamaGameScene = false;
            isShopScene = false;
            isPullScene = false;
            isInventoryScene = true;
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
        else if (isShopScene)
        {
            previousScene = "shopScene";
        }
        else if (isPullScene)
        {
            previousScene = "pullScene";
        }
        else if (isInventoryScene)
        {
            previousScene = "inventoryScene";
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
        else if (previousScene.equals("shopScene"))
        {
            openShopScene();
            updateIsSceneVariables("shopScene");
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
        mainGroup.getChildren().remove(shopPane);
        mainGroup.getChildren().add(gamePane);
        updateIsSceneVariables("gameScene"); //updates current scene to GameScene because GameScene is currently opened
    }
    //opens Main Scene and closes other scenes
    public void openMainScene()
    {
        mainGroup.getChildren().remove(gamePane);
        mainGroup.getChildren().remove(settingsPane);
        mainGroup.getChildren().remove(babyGamePane);
        mainGroup.getChildren().remove(papaGamePane);
        mainGroup.getChildren().remove(mamaGamePane);
        mainGroup.getChildren().remove(shopPane);
        mainGroup.getChildren().add(mainPane);
        updateIsSceneVariables("mainScene"); //updates current scene to MainScene because MainScene is currently opened
    }
    //opens Settings Scene and closes other scenes
    public void openSettingsScene()
    {
        mainGroup.getChildren().remove(mainPane);
        mainGroup.getChildren().remove(gamePane);
        mainGroup.getChildren().remove(babyGamePane);
        mainGroup.getChildren().remove(papaGamePane);
        mainGroup.getChildren().remove(mamaGamePane);
        mainGroup.getChildren().remove(shopPane);
        mainGroup.getChildren().add(settingsPane);
        updateIsSceneVariables("settingsScene"); //updates current scene to SettingsScene because SettingsScene is currently opened
    }
    //opens babyGameScene Scene and closes other scenes
    public void openBabyGameScene()
    {
        mainGroup.getChildren().remove(mainPane);
        mainGroup.getChildren().remove(gamePane);
        mainGroup.getChildren().remove(settingsPane);
        mainGroup.getChildren().remove(papaGamePane);
        mainGroup.getChildren().remove(mamaGamePane);
        mainGroup.getChildren().remove(shopPane);
        mainGroup.getChildren().add(babyGamePane);
        updateIsSceneVariables("babyGameScene"); //updates current scene to babyGameScene because babyGameScene is currently opened
    }
    //opens papaGameScene Scene and closes other scenes
    public void openPapaGameScene()
    {
        mainGroup.getChildren().remove(mainPane);
        mainGroup.getChildren().remove(gamePane);
        mainGroup.getChildren().remove(settingsPane);
        mainGroup.getChildren().remove(babyGamePane);
        mainGroup.getChildren().remove(mamaGamePane);
        mainGroup.getChildren().remove(shopPane);
        mainGroup.getChildren().add(papaGamePane);
        updateIsSceneVariables("papaGameScene"); //updates current scene to papaGameScene because papaGameScene is currently opened
    }
    //opens mamaGameScene Scene and closes other scenes
    public void openMamaGameScene()
    {
        mainGroup.getChildren().remove(mainPane);
        mainGroup.getChildren().remove(gamePane);
        mainGroup.getChildren().remove(settingsPane);
        mainGroup.getChildren().remove(babyGamePane);
        mainGroup.getChildren().remove(papaGamePane);
        mainGroup.getChildren().remove(shopPane);
        mainGroup.getChildren().add(mamaGamePane);
        updateIsSceneVariables("mamaGameScene"); //updates current scene to mamaGameScene because mamaGameScene is currently opened
    }
    //opens shopScene and closes other scenes
    public void openShopScene()
    {
        mainGroup.getChildren().remove(mainPane);
        mainGroup.getChildren().remove(gamePane);
        mainGroup.getChildren().remove(settingsPane);
        mainGroup.getChildren().remove(babyGamePane);
        mainGroup.getChildren().remove(papaGamePane);
        mainGroup.getChildren().add(shopPane);
        updateIsSceneVariables("shopScene"); //updates current scene to shopScene because shopScene is currently opened
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

    //generates random number from min to max
    public int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }

    //creates confetti animation for the fishing
    private void startFishingConfettiAnimation(ImageView confettiImageView) {
        confettiImageView.setTranslateX(250);
        confettiImageView.setTranslateY(200); //this exact y-value was meant to make the confetti look like it appears from under the text box

        //use fadeTransition to make the confetti gif fade in
        FadeTransition fadeInTransition = new FadeTransition(Duration.millis(500), confettiImageView);
        fadeInTransition.setFromValue(0.0);
        fadeInTransition.setToValue(1.0);

        //use fadeTransition to make the confetti gif fade out
        FadeTransition fadeOutTransition = new FadeTransition(Duration.millis(500), confettiImageView);
        fadeOutTransition.setFromValue(1.0);
        fadeOutTransition.setToValue(0.0);

        //use pauseTransition to make the confetti gif stay for a little bit
        PauseTransition pauseTransition = new PauseTransition(Duration.millis(500));

        //merge all the transitions together
        fadeInTransition.setOnFinished(event -> {
            // Start the pause transition before starting the fade-out
            pauseTransition.play();
        });

        pauseTransition.setOnFinished(event -> {
            // Start the fade-out transition
            fadeOutTransition.play();
        });

        //starts the actual transition
        fadeInTransition.play();
    }
    private void startQuizConfettiAnimation(ImageView confettiImageView) {
        confettiImageView.setTranslateX(200);
        confettiImageView.setTranslateY(250); //this exact y-value was meant to make the confetti look like it appears from under the text box

        //use fadeTransition to make the confetti gif fade in
        FadeTransition fadeInTransition = new FadeTransition(Duration.millis(500), confettiImageView);
        fadeInTransition.setFromValue(0.0);
        fadeInTransition.setToValue(1.0);

        //use fadeTransition to make the confetti gif fade out
        FadeTransition fadeOutTransition = new FadeTransition(Duration.millis(500), confettiImageView);
        fadeOutTransition.setFromValue(1.0);
        fadeOutTransition.setToValue(0.0);

        //use pauseTransition to make the confetti gif stay for a little bit
        PauseTransition pauseTransition = new PauseTransition(Duration.millis(500));

        //merge all the transitions together
        fadeInTransition.setOnFinished(event -> {
            // Start the pause transition before starting the fade-out
            pauseTransition.play();
        });

        pauseTransition.setOnFinished(event -> {
            // Start the fade-out transition
            fadeOutTransition.play();
        });

        //starts the actual transition
        fadeInTransition.play();
    }
    //returns total amount of iceCubes
    public static int getIceCubes()
    {
        return iceCubes;
    }

    public static void setIceCubes(int num)
    {
        iceCubes = num;
    }




}