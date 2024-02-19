import javafx.animation.FadeTransition;
import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.SceneAntialiasing;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.StackPane;
import javafx.scene.text.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Main extends Application {
    private static boolean hasNeo = false;
    private static boolean hasRockStar = false;
    private static boolean hasAstronaut = false;
    private static boolean hasCyborg = false;
    private static boolean hasPopStar = false;
    private static boolean hasTux = false;
    private static boolean hasSaxophone = false;
    private static boolean hasKing = false;
    private static int flippedOverCount = 0;
    private static boolean wonMemory = false;
    private static int winCheckerCount = 0;
    private static ArrayList<ImageView> flippedOverCardArr = new ArrayList<>();

    Image commonImage = new Image(new FileInputStream("src/MinigameSprites/Shop/common.png"));
    ImageView commonImageView1 = new ImageView(commonImage);
    ImageView commonImageView2 = new ImageView(commonImage);
    Image rareImage = new Image(new FileInputStream("src/MinigameSprites/Shop/rare.png"));
    ImageView rareImageView1 = new ImageView(rareImage);
    ImageView rareImageView2 = new ImageView(rareImage);
    Image mythicImage = new Image(new FileInputStream("src/MinigameSprites/Shop/mythic.png"));
    ImageView mythicImageView1 = new ImageView(mythicImage);
    ImageView mythicImageView2 = new ImageView(mythicImage);
    Image legendaryImage = new Image(new FileInputStream("src/MinigameSprites/Shop/legendary.png"));
    ImageView legendaryImageView1 = new ImageView(legendaryImage);
    ImageView legendaryImageView2 = new ImageView(legendaryImage);
    Image majesticImage = new Image(new FileInputStream("src/MinigameSprites/Shop/majestic.png"));
    ImageView majesticImageView = new ImageView(majesticImage);
    Image sgsbackButtonImage = new Image(new FileInputStream("src/Sprites/backButton.png"));
    ImageView sgsbackButtonImageView = new ImageView(sgsbackButtonImage);

    Image neoSamuraiCardImage = new Image(new FileInputStream("src/MinigameSprites/Shop/neoSamuraiCard.png"));
    ImageView neoSamuraiCardImageView = new ImageView(neoSamuraiCardImage);
    Image popStarCardImage = new Image(new FileInputStream("src/MinigameSprites/Shop/popStarCard.png"));
    ImageView popStarCardImageView = new ImageView(popStarCardImage);
    Image astronautCardImage = new Image(new FileInputStream("src/MinigameSprites/Shop/astronautCard.png"));
    ImageView astronautCardImageView = new ImageView(astronautCardImage);
    Image cyborgCardImage = new Image(new FileInputStream("src/MinigameSprites/Shop/cyborgCard.png"));
    ImageView cyborgCardImageView = new ImageView(cyborgCardImage);
    Image kingCardImage = new Image(new FileInputStream("src/MinigameSprites/Shop/kingCard.png"));
    ImageView kingCardImageView = new ImageView(kingCardImage);
    Image poity1CardImage = new Image(new FileInputStream("src/MinigameSprites/Shop/poity1Card.png"));
    ImageView poity1CardImageView = new ImageView(poity1CardImage);
    Image rockStarCardImage = new Image(new FileInputStream("src/MinigameSprites/Shop/rockStarCard.png"));
    ImageView rockStarCardImageView = new ImageView(rockStarCardImage);
    Image saxophoneCardImage = new Image(new FileInputStream("src/MinigameSprites/Shop/saxophoneCard.png"));
    ImageView saxophoneCardImageView = new ImageView(saxophoneCardImage);
    Image tuxCardImage = new Image(new FileInputStream("src/MinigameSprites/Shop/tuxCard.png"));
    ImageView tuxCardImageView = new ImageView(tuxCardImage);
    TextField codeEnter = new TextField("ENTER PROMO CODE HERE:");
    Text pulltext = new Text("What did you get?");
    Image tuxPenguinImage = new Image(new FileInputStream("src/Sprites/tuxPenguinPlush.png"));
    ImageView tuxPenguinImageView = new ImageView(tuxPenguinImage);
    Image astronautPenguinImage = new Image(new FileInputStream("src/Sprites/astronautPenguinPlush.png"));
    ImageView astronautPenguinImageView = new ImageView(astronautPenguinImage);
    Image cyborgPenguinImage = new Image(new FileInputStream("src/Sprites/cyborgPenguinPlush.png"));
    ImageView cyborgPenguinImageView = new ImageView(cyborgPenguinImage);
    Image kingPenguinImage = new Image(new FileInputStream("src/Sprites/kingPenguinPlush.png"));
    ImageView kingPenguinImageView = new ImageView(kingPenguinImage);
    Image rockStarPenguinImage = new Image(new FileInputStream("src/Sprites/rockStarPenguinPlush.png"));
    ImageView rockStarPenguinImageView = new ImageView(rockStarPenguinImage);
    Image saxophonePenguinImage = new Image(new FileInputStream("src/Sprites/saxophonePenguinPlush.png"));
    ImageView saxophonePenguinImageView = new ImageView(saxophonePenguinImage);
    Image neoSamuraiPenguinImage = new Image(new FileInputStream("src/Sprites/neoSamuraiPenguinPlush.png"));
    ImageView neoSamuraiPenguinImageView = new ImageView(neoSamuraiPenguinImage);
    Image popStarPenguinImage = new Image(new FileInputStream("src/Sprites/popStarPenguinPlush.png"));
    ImageView popStarPenguinImageView = new ImageView(popStarPenguinImage);
    String pullName = "";
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
    StackPane pullPane = new StackPane();
    StackPane inventoryPane = new StackPane();
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
    String fishingSpeech3="Can you help us sort out between compost, plastic, and paper? ";
    String fishingSpeech4="Mama is in charge of plastic.";
    String fishingSpeech5="Baby is in charge of paper";
    String fishingSpeech6="....and I'm in charge of compost!";
    String fishingSpeech7="Click the pond to fish!";

    String[] fishingSpeechArr = {fishingSpeech1,fishingSpeech2,fishingSpeech3,fishingSpeech4,fishingSpeech5,fishingSpeech6,fishingSpeech7};

    public Main() throws FileNotFoundException {
    }

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
        setPullScene();
        mainGroup.getChildren().remove(pullPane);
        setInventoryScene();
        mainGroup.getChildren().remove(inventoryPane);

        pulltext.setTranslateY(-150);
        pulltext.setTranslateX(400);

        pulltext.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 40));

        popStarPenguinImageView.setFitWidth(400);
        popStarPenguinImageView.setFitHeight(600);
        popStarPenguinImageView.setTranslateX(350);
        popStarPenguinImageView.setTranslateY(250);
        popStarPenguinImageView.setPreserveRatio(true);

        tuxPenguinImageView.setFitWidth(400);
        tuxPenguinImageView.setFitHeight(600);
        tuxPenguinImageView.setTranslateX(350);
        tuxPenguinImageView.setTranslateY(250);
        tuxPenguinImageView.setPreserveRatio(true);

        kingPenguinImageView.setFitWidth(400);
        kingPenguinImageView.setFitHeight(600);
        kingPenguinImageView.setTranslateX(350);
        kingPenguinImageView.setTranslateY(250);
        kingPenguinImageView.setPreserveRatio(true);

        rockStarPenguinImageView.setFitWidth(400);
        rockStarPenguinImageView.setFitHeight(600);
        rockStarPenguinImageView.setTranslateX(350);
        rockStarPenguinImageView.setTranslateY(250);
        rockStarPenguinImageView.setPreserveRatio(true);

        neoSamuraiPenguinImageView.setFitWidth(400);
        neoSamuraiPenguinImageView.setFitHeight(600);
        neoSamuraiPenguinImageView.setTranslateX(350);
        neoSamuraiPenguinImageView.setTranslateY(250);
        neoSamuraiPenguinImageView.setPreserveRatio(true);

        astronautPenguinImageView.setFitWidth(400);
        astronautPenguinImageView.setFitHeight(600);
        astronautPenguinImageView.setTranslateX(350);
        astronautPenguinImageView.setTranslateY(250);
        astronautPenguinImageView.setPreserveRatio(true);

        saxophonePenguinImageView.setFitWidth(400);
        saxophonePenguinImageView.setFitHeight(600);
        saxophonePenguinImageView.setTranslateX(350);
        saxophonePenguinImageView.setTranslateY(250);
        saxophonePenguinImageView.setPreserveRatio(true);

        cyborgPenguinImageView.setFitWidth(400);
        cyborgPenguinImageView.setFitHeight(600);
        cyborgPenguinImageView.setTranslateX(350);
        cyborgPenguinImageView.setTranslateY(250);
        cyborgPenguinImageView.setPreserveRatio(true);

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
        Image inventoryImage = new Image(new FileInputStream("src/Sprites/inventory.png"));
        ImageView inventoryImageView = new ImageView(inventoryImage);
        Image enterCodeImage = new Image(new FileInputStream("src/Sprites/enterCode.png"));
        ImageView enterCodeImageView = new ImageView(enterCodeImage);

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

        inventoryImageView.setTranslateX(250);
        inventoryImageView.setTranslateY(200);
        inventoryImageView.setFitHeight(100);
        inventoryImageView.setFitWidth(200);
        inventoryImageView.setPreserveRatio(true);

        enterCodeImageView.setTranslateX(250);
        enterCodeImageView.setTranslateY(300);
        enterCodeImageView.setFitHeight(100);
        enterCodeImageView.setFitWidth(200);
        enterCodeImageView.setPreserveRatio(true);

        codeEnter.setTranslateX(550);
        codeEnter.setTranslateY(300);

        settingsPane.getChildren().addAll(ssbackButtonImageView, backgroundTextImageView, greenOnImageView, blankOffImageView,enterCodeImageView,inventoryImageView,codeEnter);

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

        enterCodeImageView.setOnMouseClicked(mouseEvent ->
        {
            enterCode();
        });

        inventoryImageView.setOnMouseClicked(mouseEvent ->
        {
            openInventoryScene();
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
            if (quizGacha == 1) //why recycle question (incorrect)
            {
                updateQuestion(questionText, firstChoiceText, secondChoiceText, thirdChoiceText, fourthChoiceText);
            }
            if (quizGacha == 2) //why recycle question (incorrect)
            {
                updateQuestion(questionText, firstChoiceText, secondChoiceText, thirdChoiceText, fourthChoiceText);
            }
            if (quizGacha == 3) //why recycle question (incorrect)
            {
                iceCubes += 5;
                updateQuestion(questionText, firstChoiceText, secondChoiceText, thirdChoiceText, fourthChoiceText);
                startQuizConfettiAnimation(confettiGIFImageView);
            }
            if (quizGacha == 4) //why recycle question (incorrect)
            {
                updateQuestion(questionText, firstChoiceText, secondChoiceText, thirdChoiceText, fourthChoiceText);
            }
            if (quizGacha == 5) //why recycle question (incorrect)
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
            if (quizGacha == 1) //why recycle question (incorrect)
            {
                updateQuestion(questionText, firstChoiceText, secondChoiceText, thirdChoiceText, fourthChoiceText);
            }
            if (quizGacha == 2) //why recycle question (incorrect)
            {
                iceCubes += 5;
                updateQuestion(questionText, firstChoiceText, secondChoiceText, thirdChoiceText, fourthChoiceText);
                startQuizConfettiAnimation(confettiGIFImageView);
            }
            if (quizGacha == 3) //why recycle question (incorrect)
            {
                updateQuestion(questionText, firstChoiceText, secondChoiceText, thirdChoiceText, fourthChoiceText);
            }
            if (quizGacha == 4) //why recycle question (incorrect)
            {
                updateQuestion(questionText, firstChoiceText, secondChoiceText, thirdChoiceText, fourthChoiceText);
            }
            if (quizGacha == 5) //why recycle question (incorrect)
            {
                updateQuestion(questionText, firstChoiceText, secondChoiceText, thirdChoiceText, fourthChoiceText);
            }
        });
        thirdChoiceImageView.setOnMouseClicked(mouseEvent ->
        {
            if (quizGacha == 0) //why recycle question (incorrect)
            {
                updateQuestion(questionText, firstChoiceText, secondChoiceText, thirdChoiceText, fourthChoiceText);
            }
            if (quizGacha == 1) //why recycle question (incorrect)
            {
                iceCubes += 5;
                updateQuestion(questionText, firstChoiceText, secondChoiceText, thirdChoiceText, fourthChoiceText);
                startQuizConfettiAnimation(confettiGIFImageView);
            }
            if (quizGacha == 2) //why recycle question (incorrect)
            {
                updateQuestion(questionText, firstChoiceText, secondChoiceText, thirdChoiceText, fourthChoiceText);
            }
            if (quizGacha == 3) //why recycle question (incorrect)
            {
                updateQuestion(questionText, firstChoiceText, secondChoiceText, thirdChoiceText, fourthChoiceText);
            }
            if (quizGacha == 4) //why recycle question (incorrect)
            {
                updateQuestion(questionText, firstChoiceText, secondChoiceText, thirdChoiceText, fourthChoiceText);
            }
            if (quizGacha == 5) //why recycle question (incorrect)
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
            if (quizGacha == 1) //why recycle question (incorrect)
            {
                updateQuestion(questionText, firstChoiceText, secondChoiceText, thirdChoiceText, fourthChoiceText);
            }
            if (quizGacha == 2) //why recycle question (incorrect)
            {
                updateQuestion(questionText, firstChoiceText, secondChoiceText, thirdChoiceText, fourthChoiceText);
            }
            if (quizGacha == 3) //why recycle question (incorrect)
            {
                updateQuestion(questionText, firstChoiceText, secondChoiceText, thirdChoiceText, fourthChoiceText);
            }
            if (quizGacha == 4) //why recycle question (incorrect)
            {
                iceCubes += 5;
                updateQuestion(questionText, firstChoiceText, secondChoiceText, thirdChoiceText, fourthChoiceText);
                startQuizConfettiAnimation(confettiGIFImageView);
            }
            if (quizGacha == 5) //why recycle question (incorrect)
            {
                iceCubes += 5;
                updateQuestion(questionText, firstChoiceText, secondChoiceText, thirdChoiceText, fourthChoiceText);
                startQuizConfettiAnimation(confettiGIFImageView);
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
            System.out.println("closing babyGameScene and opening gameScene");
            openGameScene();
            //backGame();
        });

    }
    private void updateQuestion(Text questionText, Text firstChoiceText, Text secondChoiceText, Text thirdChoiceText, Text fourthChoiceText)
    {
        quizGacha = getRandomNumber(0, 5); //rolls a random number from 0 to 0
        if (quizGacha == 0)
        {
            questionText.setText("Why is it important to recycle?");
            firstChoiceText.setText("Only because papa penguin said to!");
            secondChoiceText.setText("To help keep the Earth safe and clean!");
            thirdChoiceText.setText("Recycling is not important!");
            fourthChoiceText.setText("To build more factories!!!!!!!!!");
        }
        if (quizGacha == 1)
        {
            questionText.setText("What are three categories for things that should be recycled?");
            firstChoiceText.setText("Compost, Paper, Confetti");
            secondChoiceText.setText("Water, Garbage, Landfill");
            thirdChoiceText.setText("Plastic, Paper, Compost");
            fourthChoiceText.setText("apple, plastic bottle, newspaper");
        }
        if (quizGacha == 2)
        {
            questionText.setText("The term 'Reduce' refers to: ");
            firstChoiceText.setText("Use something over and over again");
            secondChoiceText.setText("Use less of something, creating less waste.");
            thirdChoiceText.setText("Make something into something new.");
            fourthChoiceText.setText("Make something ugly into something beautiful.");
        }
        if (quizGacha == 3)
        {
            questionText.setText("The term 'recycle' means:  ");
            firstChoiceText.setText("Make something into something new.");
            secondChoiceText.setText("Throwing out Trash.");
            thirdChoiceText.setText("Ride your bike");
            fourthChoiceText.setText("Use something again.");
        }
        if (quizGacha == 3)
        {
            questionText.setText("The term 'reuse' means:  ");
            firstChoiceText.setText("Make something into something new.");
            secondChoiceText.setText("Throwing out Trash.");
            thirdChoiceText.setText("Ride your bike");
            fourthChoiceText.setText("Use something again.");
        }
        if (quizGacha==5)
        {
            questionText.setText("Which of the following is not recyclable material?");
            firstChoiceText.setText("Plastic.");
            secondChoiceText.setText("Paper.");
            thirdChoiceText.setText("Metal");
            fourthChoiceText.setText("Food.");
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
        Text paperText = new Text("Paper");
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
                fishingCategoryGacha = getRandomNumber(0, 3); //generates number from 0 to 3
                fishingItemGacha = getRandomNumber(0, 1); //generates number from 0 to 2
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
        Image confettiGIF = new Image(new FileInputStream("src/Effects/confetti.gif"));
        ImageView confettiGIFImageView = new ImageView(confettiGIF);
        //hides the confetti initially
        //the real translate values are in the startConfettiAnimation() method
        confettiGIFImageView.setTranslateY(-2000);
        confettiGIFImageView.setTranslateX(-2000);
        confettiGIFImageView.setFitHeight(350);
        confettiGIFImageView.setFitWidth(500);

        Image iceCubeImage = new Image(new FileInputStream("src/Sprites/icecubes.png"));
        ImageView iceCubeImageView = new ImageView(iceCubeImage);
        Text mgsIceCubeTextHolder = new Text("");
        Text mamaEncouragementText = new Text("Click on the ice cubes to reveal the cards! Can you match them all?");
        mamaEncouragementText.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 15));
        mamaEncouragementText.setTranslateY(-50);
        mamaEncouragementText.setTranslateX(330);

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
        ImageView card1 = new ImageView(iceCard);
        ImageView card2 = new ImageView(iceCard);
        ImageView card3 = new ImageView(iceCard);
        ImageView card4 = new ImageView(iceCard);
        ImageView card5 = new ImageView(iceCard);
        ImageView card6 = new ImageView(iceCard);
        ImageView card7 = new ImageView(iceCard);
        ImageView card8 = new ImageView(iceCard);
        ImageView card9 = new ImageView(iceCard);
        ImageView card10 = new ImageView(iceCard);
        ImageView card11 = new ImageView(iceCard);
        ImageView card12 = new ImageView(iceCard);
        ImageView card13 = new ImageView(iceCard);
        ImageView card14 = new ImageView(iceCard);
        ImageView card15 = new ImageView(iceCard);
        ImageView card16 = new ImageView(iceCard);
//
//
//        ImageView placeholderView1 = new ImageView(iceCard);
//        ImageView placeholderView2 = new ImageView(iceCard);
//        ImageView placeholderView3 = new ImageView(iceCard);
//        ImageView placeholderView4 = new ImageView(iceCard);
//        ImageView placeholderView5 = new ImageView(iceCard);
//        ImageView placeholderView6 = new ImageView(iceCard);
//        ImageView placeholderView7 = new ImageView(iceCard);
//        ImageView placeholderView8 = new ImageView(iceCard);
//        ImageView placeholderView9 = new ImageView(iceCard);
//        ImageView placeholderView10 = new ImageView(iceCard);
//        ImageView placeholderView11 = new ImageView(iceCard);
//        ImageView placeholderView12 = new ImageView(iceCard);
//        ImageView placeholderView13 = new ImageView(iceCard);
//        ImageView placeholderView14 = new ImageView(iceCard);
//        ImageView placeholderView15 = new ImageView(iceCard);
//        ImageView placeholderView16 = new ImageView(iceCard);

        Image appleImage1 = new Image(new FileInputStream("src/MinigameSprites/Compost/apple.png"));
        Image bananaImage1 = new Image(new FileInputStream("src/MinigameSprites/Compost/banana.png"));
        Image cardboardImage1 = new Image(new FileInputStream("src/MinigameSprites/Landfill/cardboard.png"));
        Image newspaperImage1 = new Image(new FileInputStream("src/MinigameSprites/Landfill/newspaper.png"));
        Image milkImage1 = new Image(new FileInputStream("src/MinigameSprites/Plastic/milk.png"));
        Image waterBottleImage1 = new Image(new FileInputStream("src/MinigameSprites/Plastic/waterBottle.png"));
        Image fishImage1 = new Image(new FileInputStream("src/MinigameSprites/Bonus/fish.png"));
        Image krillImage1 = new Image(new FileInputStream("src/MinigameSprites/Bonus/krill.png"));

        ArrayList<Image> currentArr = new ArrayList<>();
        currentArr.add(appleImage1);
        currentArr.add(appleImage1);
        currentArr.add(bananaImage1);
        currentArr.add(bananaImage1);
        currentArr.add(cardboardImage1);
        currentArr.add(cardboardImage1);
        currentArr.add(newspaperImage1);
        currentArr.add(newspaperImage1);
        currentArr.add(milkImage1);
        currentArr.add(milkImage1);
        currentArr.add(waterBottleImage1);
        currentArr.add(waterBottleImage1);
        currentArr.add(fishImage1);
        currentArr.add(fishImage1);
        currentArr.add(krillImage1);
        currentArr.add(krillImage1);

        ArrayList<ImageView> cardArr = new ArrayList<>();
        cardArr.add(card1);
        cardArr.add(card2);
        cardArr.add(card3);
        cardArr.add(card4);
        cardArr.add(card5);
        cardArr.add(card6);
        cardArr.add(card7);
        cardArr.add(card8);
        cardArr.add(card9);
        cardArr.add(card10);
        cardArr.add(card11);
        cardArr.add(card12);
        cardArr.add(card13);
        cardArr.add(card14);
        cardArr.add(card15);
        cardArr.add(card16);

        Collections.shuffle(currentArr);

        mainGroup.getChildren().add(mamaGamePane);
        mamaGamePane.getChildren().add(textBubbleImageView);
//        mamaGamePane.getChildren().addAll(placeholderView1,placeholderView2,placeholderView3,placeholderView4,placeholderView5,placeholderView6,placeholderView7,placeholderView8,placeholderView9,placeholderView10,placeholderView11,placeholderView12,placeholderView13,placeholderView14,placeholderView15,placeholderView16);
        mamaGamePane.getChildren().addAll(mgsbackButtonImageView, mamaPenguinImageView,mamaPortraitImageView, confettiGIFImageView, card1,card2,card3,card4,card5,card6,card7,card8,card9,card10,card11,card12,card13,card14,card15,card16, mgsIceCubeTextHolder, iceCubeImageView, mamaEncouragementText);

        mamaPortraitImageView.setFitHeight(200);
        mamaPortraitImageView.setFitWidth(200);
        mamaPortraitImageView.setTranslateY(-50);
        mamaPortraitImageView.setTranslateX(-100);

        textBubbleImageView.setFitHeight(250);
        textBubbleImageView.setFitWidth(1000);
        textBubbleImageView.setTranslateY(-50);
        textBubbleImageView.setTranslateX(250);

        mgsbackButtonImageView.setFitHeight(100);
        mgsbackButtonImageView.setFitWidth(200);
        mgsbackButtonImageView.setTranslateY(-130);
        mgsbackButtonImageView.setTranslateX(-400);
        mgsbackButtonImageView.setPreserveRatio(true);

        mamaPenguinImageView.setFitHeight(150);
        mamaPenguinImageView.setFitWidth(150);
        mamaPenguinImageView.setTranslateY(50);
        mamaPenguinImageView.setTranslateX(-400);
        mamaPenguinImageView.setPreserveRatio(true);

        iceCubeImageView.setFitHeight(75);
        iceCubeImageView.setFitWidth(75);
        iceCubeImageView.setTranslateY(565);
        iceCubeImageView.setTranslateX(-450);
        iceCubeImageView.setPreserveRatio(true);

        mgsIceCubeTextHolder.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 30));
        mgsIceCubeTextHolder.setTranslateY(560);
        mgsIceCubeTextHolder.setTranslateX(-400);

        // calculates the initial width and set the WrappingWidth accordingly
        double initialWidth = mgsIceCubeTextHolder.getLayoutBounds().getWidth();
        mgsIceCubeTextHolder.setWrappingWidth(initialWidth - 200);

        mgsIceCubeTextHolder.setTranslateX(-510);

        //creates a thread that constantly updates the text of gsIceCubeRunnable
        IceCubeRunnable mgsIceCubeRunnable = new IceCubeRunnable(mgsIceCubeTextHolder);
        Thread mgsThread = new Thread(mgsIceCubeRunnable);
        mgsThread.start();

//        placeholderView1.setFitHeight(80);
//        placeholderView1.setFitWidth(80);
//        placeholderView1.setTranslateY(200);
//        placeholderView1.setTranslateX(0);
//        placeholderView1.setPreserveRatio(false);

        card1.setFitHeight(125);
        card1.setFitWidth(125);
        card1.setTranslateY(150);
        card1.setTranslateX(10);
//        card1.setPreserveRatio(true);

        card2.setFitHeight(125);
        card2.setFitWidth(125);
        card2.setTranslateY(275);
        card2.setTranslateX(10);
//        card2.setPreserveRatio(true);

        card3.setFitHeight(125);
        card3.setFitWidth(125);
        card3.setTranslateY(400);
        card3.setTranslateX(10);
//        card3.setPreserveRatio(true);

        card4.setFitHeight(125);
        card4.setFitWidth(125);
        card4.setTranslateY(525);
        card4.setTranslateX(10);
//        card4.setPreserveRatio(true);

        card5.setFitHeight(125);
        card5.setFitWidth(125);
        card5.setTranslateY(150);
        card5.setTranslateX(135);
//        card5.setPreserveRatio(true);

        card6.setFitHeight(125);
        card6.setFitWidth(125);
        card6.setTranslateY(275);
        card6.setTranslateX(135);
//        card6.setPreserveRatio(true);

        card7.setFitHeight(125);
        card7.setFitWidth(125);
        card7.setTranslateY(400);
        card7.setTranslateX(135);
//        card7.setPreserveRatio(true);

        card8.setFitHeight(125);
        card8.setFitWidth(125);
        card8.setTranslateY(525);
        card8.setTranslateX(135);
//        card8.setPreserveRatio(true);

        card9.setFitHeight(125);
        card9.setFitWidth(125);
        card9.setTranslateY(150);
        card9.setTranslateX(260);
//        card9.setPreserveRatio(true);

        card10.setFitHeight(125);
        card10.setFitWidth(125);
        card10.setTranslateY(275);
        card10.setTranslateX(260);
//        card10.setPreserveRatio(true);

        card11.setFitHeight(125);
        card11.setFitWidth(125);
        card11.setTranslateY(400);
        card11.setTranslateX(260);
//        card11.setPreserveRatio(true);

        card12.setFitHeight(125);
        card12.setFitWidth(125);
        card12.setTranslateY(525);
        card12.setTranslateX(260);
//        card12.setPreserveRatio(true);

        card13.setFitHeight(125);
        card13.setFitWidth(125);
        card13.setTranslateY(150);
        card13.setTranslateX(385);
//        card13.setPreserveRatio(true);

        card14.setFitHeight(125);
        card14.setFitWidth(125);
        card14.setTranslateY(275);
        card14.setTranslateX(385);
//        card14.setPreserveRatio(true);

        card15.setFitHeight(125);
        card15.setFitWidth(125);
        card15.setTranslateY(400);
        card15.setTranslateX(385);
//        card15.setPreserveRatio(true);

        card16.setFitHeight(125);
        card16.setFitWidth(125);
        card16.setTranslateY(525);
        card16.setTranslateX(385);
//        card16.setPreserveRatio(true);


        //Note: currentArr & cardArr should have the same indices
        for (int i = 0; i < cardArr.size(); i++) {
            final int index = i;
            Image image1 = currentArr.get(index);



            cardArr.get(i).setOnMouseClicked(mouseEvent -> {
                if (cardArr.get(index).getImage().equals(iceCard))
                {
                    winCheckerCount = 0;
                    if (flippedOverCount < 1)
                    {
                        flippedOverCardArr.add(cardArr.get(index));
                        cardArr.get(index).setImage(currentArr.get(index));
                        flippedOverCount++;
                    }
                    else if (flippedOverCount < 2)
                    {
                        flippedOverCardArr.add(cardArr.get(index));
                        cardArr.get(index).setImage(currentArr.get(index));
                        PauseTransition pause = new PauseTransition(Duration.millis(500));
                        pause.setOnFinished(e -> {
                            if (flippedOverCardArr.get(0).getImage().equals(flippedOverCardArr.get(1).getImage())) {
                                //check for win
                                for (ImageView card: cardArr){
                                    if (!(card.getImage().equals(iceCard)))
                                    {
                                        winCheckerCount++;
                                    }
                                    if (winCheckerCount == 16)
                                    {
                                        iceCubes += 300;
                                    }
                                }
                            } else {
                                flippedOverCardArr.get(0).setImage(iceCard);
                                flippedOverCardArr.get(1).setImage(iceCard);
                            }
                            flippedOverCardArr.clear();
                            flippedOverCount = 0;
                        });
                        pause.play();
                    }
                }
////                System.out.println(cardArr.get(index).get);
//                cardArr.get(index).setImage(currentArr.get(index));
////                System.out.println(cardArr.get(index));
//                System.out.println(image1);
            });
        }





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
            if(iceCubes>=100)
            {
                iceCubes-=100;
                int ranNum=lottery();
                System.out.print(ranNum);
                if(ranNum==4)
                {
                    int ran = getRandomNumber(1,2);
                    if(ran==1)
                    {
                        pullName="neo";
                    }
                    else
                    {
                        pullName="pop";
                    }
                }
                else if (ranNum==3) {
                    int ran = getRandomNumber(1,2);
                    if(ran==1)
                    {
                        pullName="rock";
                    }
                    else
                    {
                        pullName="sax";
                    }
                }
                else if (ranNum==2) {
                    int ran = getRandomNumber(1, 2);
                    if (ran == 1) {
                        pullName = "cyb";
                    }
                    else
                    {
                        pullName = "king";
                    }
                }
                else if (ranNum==1) {
                    int ran = getRandomNumber(1, 2);
                    if (ran == 1) {
                        pullName = "tux";
                    }
                    else
                    {
                        pullName = "astro";
                    }
                }
                openPullScene();
            }
            else
            {
                shopText.setText("Silly Goose! You don't have enough.");
            }

        });

    }

    public void setPullScene() throws FileNotFoundException
    {

        Image sgsbackButtonImage = new Image(new FileInputStream("src/Sprites/backButton.png"));
        ImageView sgsbackButtonImageView = new ImageView(sgsbackButtonImage);

        Image hatchAgainImage = new Image(new FileInputStream("src/Sprites/hatchAgain.png"));
        ImageView hatchAgainImageView = new ImageView(hatchAgainImage);

        Image iceCubeImage = new Image(new FileInputStream("src/Sprites/icecubes.png"));
        ImageView iceCubeImageView = new ImageView(iceCubeImage);
        Text psIceCubeTextHolder = new Text("");


        mainGroup.getChildren().add(pullPane);
        pullPane.getChildren().addAll(sgsbackButtonImageView,hatchAgainImageView, iceCubeImageView, psIceCubeTextHolder);
        pullPane.getChildren().add(pulltext);

        iceCubeImageView.setFitHeight(75);
        iceCubeImageView.setFitWidth(75);
        iceCubeImageView.setTranslateY(500);
        iceCubeImageView.setTranslateX(-230);
        iceCubeImageView.setPreserveRatio(true);

        psIceCubeTextHolder.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 30));
        psIceCubeTextHolder.setTranslateY(495);
        psIceCubeTextHolder.setTranslateX(-180);

        // calculates the initial width and set the WrappingWidth accordingly
        double initialWidth = psIceCubeTextHolder.getLayoutBounds().getWidth();
        psIceCubeTextHolder.setWrappingWidth(initialWidth - 200);

        psIceCubeTextHolder.setTranslateX(-285);

        //creates a thread that constantly updates the text of gsIceCubeRunnable
        IceCubeRunnable psIceCubeRunnable = new IceCubeRunnable(psIceCubeTextHolder);
        Thread psThread = new Thread(psIceCubeRunnable);
        psThread.start();

        sgsbackButtonImageView.setFitHeight(100);
        sgsbackButtonImageView.setFitWidth(200);
        sgsbackButtonImageView.setTranslateY(-150);
        sgsbackButtonImageView.setTranslateX(-230);
        sgsbackButtonImageView.setPreserveRatio(true);

        hatchAgainImageView.setFitHeight(100);
        hatchAgainImageView.setFitWidth(200);
        hatchAgainImageView.setTranslateY(-80);
        hatchAgainImageView.setTranslateX(-230);
        hatchAgainImageView.setPreserveRatio(true);
//
        sgsbackButtonImageView.setOnMousePressed(event->{

            pullPane.getChildren().removeAll(popStarPenguinImageView,tuxPenguinImageView,cyborgPenguinImageView,astronautPenguinImageView,kingPenguinImageView,rockStarPenguinImageView,saxophonePenguinImageView,neoSamuraiPenguinImageView);
            System.out.println("closing shopScene and opening gameScene");
            openGameScene();
        });

        hatchAgainImageView.setOnMousePressed(event->{
            if(iceCubes>=100)
            {
                iceCubes-=100;
                int ranNum=lottery();
                System.out.print(ranNum);
                if(ranNum==4)
                {
                    int ran = getRandomNumber(1,2);
                    if(ran==1)
                    {
                        pullPane.getChildren().removeAll(neoSamuraiPenguinImageView,popStarPenguinImageView,rockStarPenguinImageView,saxophonePenguinImageView,cyborgPenguinImageView,kingPenguinImageView,tuxPenguinImageView,astronautPenguinImageView);
                        pullPane.getChildren().add(neoSamuraiPenguinImageView);
                        pulltext.setText("You got a NEOSAMURAIPENGUINPLUSH");
                        inventoryPane.getChildren().remove(legendaryImageView2);
                        if (!(hasNeo))
                        {
                            inventoryPane.getChildren().add(neoSamuraiCardImageView);
                            hasNeo = true;
                        }
                    }
                    else
                    {
                        pullPane.getChildren().removeAll(neoSamuraiPenguinImageView,popStarPenguinImageView,rockStarPenguinImageView,saxophonePenguinImageView,cyborgPenguinImageView,kingPenguinImageView,tuxPenguinImageView,astronautPenguinImageView);
                        pullPane.getChildren().add(popStarCardImageView);
                        pulltext.setText("You got a POPSTARPENGUINPLUSH");
                        inventoryPane.getChildren().remove(legendaryImageView1);
                        if (!(hasPopStar))
                        {
                            inventoryPane.getChildren().add(popStarCardImageView);
                            hasPopStar = true;
                        }
                    }
                }
                else if (ranNum==3) {
                    int ran = getRandomNumber(1,2);
                    if(ran==1)
                    {
                        pullPane.getChildren().removeAll(neoSamuraiPenguinImageView,popStarPenguinImageView,rockStarPenguinImageView,saxophonePenguinImageView,cyborgPenguinImageView,kingPenguinImageView,tuxPenguinImageView,astronautPenguinImageView);
                        pullPane.getChildren().add(rockStarPenguinImageView);
                        pulltext.setText("You got a ROCKSTARPENGUINPLUSH");
                        inventoryPane.getChildren().remove(mythicImageView1);
                        if (!(hasRockStar))
                        {
                            inventoryPane.getChildren().add(rockStarCardImageView);
                            hasRockStar = true;
                        }
                    }
                    else
                    {
                        pullPane.getChildren().removeAll(neoSamuraiPenguinImageView,popStarPenguinImageView,rockStarPenguinImageView,saxophonePenguinImageView,cyborgPenguinImageView,kingPenguinImageView,tuxPenguinImageView,astronautPenguinImageView);
                        pullPane.getChildren().add(saxophonePenguinImageView);
                        pulltext.setText("You got a SAXOPHONEPENGUINPLUSH");
                        inventoryPane.getChildren().remove(mythicImageView2);
                        if (!(hasSaxophone))
                        {
                            inventoryPane.getChildren().add(saxophoneCardImageView);
                            hasSaxophone = true;
                        }
                    }
                }
                else if (ranNum==2) {
                    int ran = getRandomNumber(1, 2);
                    if (ran == 1) {
                        pullPane.getChildren().removeAll(neoSamuraiPenguinImageView,popStarPenguinImageView,rockStarPenguinImageView,saxophonePenguinImageView,cyborgPenguinImageView,kingPenguinImageView,tuxPenguinImageView,astronautPenguinImageView);
                        pullPane.getChildren().add(cyborgPenguinImageView);
                        pulltext.setText("You got a CYBORGPENGUINPLUSH");
                        inventoryPane.getChildren().remove(rareImageView1);
                        if (!(hasCyborg))
                        {
                            inventoryPane.getChildren().add(cyborgCardImageView);
                            hasCyborg = true;
                        }
                    }
                    else
                    {
                        pullPane.getChildren().removeAll(neoSamuraiPenguinImageView,popStarPenguinImageView,rockStarPenguinImageView,saxophonePenguinImageView,cyborgPenguinImageView,kingPenguinImageView,tuxPenguinImageView,astronautPenguinImageView);
                        pullPane.getChildren().add(kingPenguinImageView);
                        pulltext.setText("You got a KINGPENGUINPLUSH");
                        inventoryPane.getChildren().remove(rareImageView2);
                        if (!(hasKing))
                        {
                            inventoryPane.getChildren().add(kingCardImageView);
                            hasKing = true;
                        }
                    }
                }
                else if (ranNum==1) {
                    int ran = getRandomNumber(1, 2);
                    if (ran == 1) {
                        pullPane.getChildren().removeAll(neoSamuraiPenguinImageView,popStarPenguinImageView,rockStarPenguinImageView,saxophonePenguinImageView,cyborgPenguinImageView,kingPenguinImageView,tuxPenguinImageView,astronautPenguinImageView);
                        pullPane.getChildren().add(tuxPenguinImageView);
                        pulltext.setText("You got a TUXPENGUINPLUSH");
                        inventoryPane.getChildren().remove(commonImageView1);
                        if (!(hasTux))
                        {
                            inventoryPane.getChildren().add(tuxCardImageView);
                            hasTux = true;
                        }
                    }
                    else
                    {
                        pullPane.getChildren().removeAll(neoSamuraiPenguinImageView,popStarPenguinImageView,rockStarPenguinImageView,saxophonePenguinImageView,cyborgPenguinImageView,kingPenguinImageView,tuxPenguinImageView,astronautPenguinImageView);
                        pullPane.getChildren().add(astronautPenguinImageView);
                        pulltext.setText("You got a ASTRONAUTPENGUINPLUSH");
                        inventoryPane.getChildren().remove(commonImageView2);
                        if (!(hasAstronaut))
                        {
                            inventoryPane.getChildren().add(astronautCardImageView);
                            hasAstronaut = true;
                        }
                    }
                }
            }
            else
            {
                pulltext.setText("Silly Goose! You don't have enough.");
            }
        });






    }

    public void setInventoryScene() throws FileNotFoundException
    {


        mainGroup.getChildren().add(inventoryPane);
        inventoryPane.getChildren().addAll(commonImageView1,commonImageView2,rareImageView1,rareImageView2,mythicImageView1,mythicImageView2,legendaryImageView1,legendaryImageView2,majesticImageView,sgsbackButtonImageView);

        sgsbackButtonImageView.setFitHeight(100);
        sgsbackButtonImageView.setFitWidth(200);
        sgsbackButtonImageView.setTranslateY(-80);
        sgsbackButtonImageView.setTranslateX(0);
        sgsbackButtonImageView.setPreserveRatio(true);

        commonImageView1.setFitHeight(250);
        commonImageView1.setFitWidth(450);
        commonImageView1.setTranslateY(100);
        commonImageView1.setTranslateX(150);
        commonImageView1.setPreserveRatio(true);

        tuxCardImageView.setFitHeight(250);
        tuxCardImageView.setFitWidth(450);
        tuxCardImageView.setTranslateY(100);
        tuxCardImageView.setTranslateX(150);
        tuxCardImageView.setPreserveRatio(true);

        commonImageView2.setFitHeight(250);
        commonImageView2.setFitWidth(450);
        commonImageView2.setTranslateY(450);
        commonImageView2.setTranslateX(150);
        commonImageView2.setPreserveRatio(true);

        astronautCardImageView.setFitHeight(250);
        astronautCardImageView.setFitWidth(450);
        astronautCardImageView.setTranslateY(450);
        astronautCardImageView.setTranslateX(150);
        astronautCardImageView.setPreserveRatio(true);

        rareImageView1.setFitHeight(250);
        rareImageView1.setFitWidth(450);
        rareImageView1.setTranslateY(100);
        rareImageView1.setTranslateX(400);
        rareImageView1.setPreserveRatio(true);

        cyborgCardImageView.setFitHeight(250);
        cyborgCardImageView.setFitWidth(450);
        cyborgCardImageView.setTranslateY(100);
        cyborgCardImageView.setTranslateX(400);
        cyborgCardImageView.setPreserveRatio(true);

        rareImageView2.setFitHeight(250);
        rareImageView2.setFitWidth(450);
        rareImageView2.setTranslateY(450);
        rareImageView2.setTranslateX(400);
        rareImageView2.setPreserveRatio(true);

        kingCardImageView.setFitHeight(250);
        kingCardImageView.setFitWidth(450);
        kingCardImageView.setTranslateY(450);
        kingCardImageView.setTranslateX(400);
        kingCardImageView.setPreserveRatio(true);

        mythicImageView1.setFitHeight(250);
        mythicImageView1.setFitWidth(450);
        mythicImageView1.setTranslateY(100);
        mythicImageView1.setTranslateX(650);
        mythicImageView1.setPreserveRatio(true);

        rockStarCardImageView.setFitHeight(250);
        rockStarCardImageView.setFitWidth(450);
        rockStarCardImageView.setTranslateY(100);
        rockStarCardImageView.setTranslateX(650);
        rockStarCardImageView.setPreserveRatio(true);

        mythicImageView2.setFitHeight(250);
        mythicImageView2.setFitWidth(450);
        mythicImageView2.setTranslateY(450);
        mythicImageView2.setTranslateX(650);
        mythicImageView2.setPreserveRatio(true);

        saxophoneCardImageView.setFitHeight(250);
        saxophoneCardImageView.setFitWidth(450);
        saxophoneCardImageView.setTranslateY(450);
        saxophoneCardImageView.setTranslateX(650);
        saxophoneCardImageView.setPreserveRatio(true);

        legendaryImageView1.setFitHeight(250);
        legendaryImageView1.setFitWidth(450);
        legendaryImageView1.setTranslateY(100);
        legendaryImageView1.setTranslateX(900);
        legendaryImageView1.setPreserveRatio(true);

        popStarCardImageView.setFitHeight(250);
        popStarCardImageView.setFitWidth(450);
        popStarCardImageView.setTranslateY(100);
        popStarCardImageView.setTranslateX(900);
        popStarCardImageView.setPreserveRatio(true);

        legendaryImageView2.setFitHeight(250);
        legendaryImageView2.setFitWidth(450);
        legendaryImageView2.setTranslateY(450);
        legendaryImageView2.setTranslateX(900);
        legendaryImageView2.setPreserveRatio(true);

        neoSamuraiCardImageView.setFitHeight(250);
        neoSamuraiCardImageView.setFitWidth(450);
        neoSamuraiCardImageView.setTranslateY(450);
        neoSamuraiCardImageView.setTranslateX(900);
        neoSamuraiCardImageView.setPreserveRatio(true);


        majesticImageView.setFitHeight(250);
        majesticImageView.setFitWidth(450);
        majesticImageView.setTranslateY(250);
        majesticImageView.setTranslateX(1150);
        majesticImageView.setPreserveRatio(true);

        poity1CardImageView.setFitHeight(250);
        poity1CardImageView.setFitWidth(450);
        poity1CardImageView.setTranslateY(250);
        poity1CardImageView.setTranslateX(1150);
        poity1CardImageView.setPreserveRatio(true);

        sgsbackButtonImageView.setOnMouseClicked(event -> {
            openSettingsScene();
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
        else if (previousScene.equals("pullScene"))
        {
            openPullScene();
            updateIsSceneVariables("pullScene");
        }
        else if (previousScene.equals("inventoryScene"))
        {
            openInventoryScene();
            updateIsSceneVariables("inventoryScene");
        }

    }
    //opens Game Scene and closes other scenes
    public void openGameScene()
    {
        mainGroup.getChildren().remove(mainPane);
        mainGroup.getChildren().remove(settingsPane);
        mainGroup.getChildren().remove(babyGamePane);
        mainGroup.getChildren().remove(papaGamePane);
        mainGroup.getChildren().remove(inventoryPane);
        mainGroup.getChildren().remove(pullPane);
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
        mainGroup.getChildren().remove(inventoryPane);
        mainGroup.getChildren().remove(pullPane);
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
        mainGroup.getChildren().remove(inventoryPane);
        mainGroup.getChildren().remove(pullPane);
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
        mainGroup.getChildren().remove(inventoryPane);
        mainGroup.getChildren().remove(pullPane);
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
        mainGroup.getChildren().remove(inventoryPane);
        mainGroup.getChildren().remove(pullPane);
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
        mainGroup.getChildren().remove(inventoryPane);
        mainGroup.getChildren().remove(pullPane);
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
        mainGroup.getChildren().remove(inventoryPane);
        mainGroup.getChildren().remove(pullPane);
        mainGroup.getChildren().add(shopPane);
        updateIsSceneVariables("shopScene"); //updates current scene to shopScene because shopScene is currently opened
    }

    public void openPullScene()
    {
        mainGroup.getChildren().remove(mainPane);
        mainGroup.getChildren().remove(gamePane);
        mainGroup.getChildren().remove(settingsPane);
        mainGroup.getChildren().remove(babyGamePane);
        mainGroup.getChildren().remove(papaGamePane);
        mainGroup.getChildren().remove(shopPane);
        mainGroup.getChildren().remove(inventoryPane);
        mainGroup.getChildren().add(pullPane);
        updateIsSceneVariables("pullScene"); //updates current scene to shopScene because shopScene is currently opened

        System.out.println(pullName);
        if(pullName=="neo")
        {
            pullPane.getChildren().removeAll(neoSamuraiPenguinImageView,popStarPenguinImageView,rockStarPenguinImageView,saxophonePenguinImageView,cyborgPenguinImageView,kingPenguinImageView,tuxPenguinImageView,astronautPenguinImageView);
            pullPane.getChildren().add(neoSamuraiPenguinImageView);
            pulltext.setText("You got a NEOSAMURAIPENGUINPLUSH");
            inventoryPane.getChildren().remove(legendaryImageView2);
            inventoryPane.getChildren().add(neoSamuraiCardImageView);
            if (!(hasNeo)) {
                inventoryPane.getChildren().add(neoSamuraiCardImageView);
                hasNeo = true;
            }

        }
        if(pullName=="rock")
        {
            pullPane.getChildren().removeAll(neoSamuraiPenguinImageView,popStarPenguinImageView,rockStarPenguinImageView,saxophonePenguinImageView,cyborgPenguinImageView,kingPenguinImageView,tuxPenguinImageView,astronautPenguinImageView);
            pullPane.getChildren().add(rockStarPenguinImageView);
            pulltext.setText("You got a ROCKSTARPENGUINPLUSH");
            inventoryPane.getChildren().remove(mythicImageView1);
            if (!(hasRockStar)) {
                inventoryPane.getChildren().add(rockStarCardImageView);
                hasRockStar = true;
            }
        }
        if(pullName=="astro")
        {
            pullPane.getChildren().removeAll(neoSamuraiPenguinImageView,popStarPenguinImageView,rockStarPenguinImageView,saxophonePenguinImageView,cyborgPenguinImageView,kingPenguinImageView,tuxPenguinImageView,astronautPenguinImageView);
            pullPane.getChildren().add(astronautPenguinImageView);
            pulltext.setText("You got a ASTRONAUTPENGUINPLUSH");
            inventoryPane.getChildren().remove(commonImageView2);
            if (!(hasAstronaut)) {
                inventoryPane.getChildren().add(astronautCardImageView);
                hasAstronaut = true;
            }
        }
        if(pullName=="cyb")
        {
            pullPane.getChildren().removeAll(neoSamuraiPenguinImageView,popStarPenguinImageView,rockStarPenguinImageView,saxophonePenguinImageView,cyborgPenguinImageView,kingPenguinImageView,tuxPenguinImageView,astronautPenguinImageView);
            pullPane.getChildren().add(cyborgPenguinImageView);
            pulltext.setText("You got a CYBORGPENGUINPLUSH");
            inventoryPane.getChildren().remove(rareImageView1);
            if (!(hasCyborg)) {
                inventoryPane.getChildren().add(cyborgCardImageView);
                hasCyborg = true;
            }
        }
        if(pullName=="pop")
        {
            pullPane.getChildren().removeAll(neoSamuraiPenguinImageView,popStarPenguinImageView,rockStarPenguinImageView,saxophonePenguinImageView,cyborgPenguinImageView,kingPenguinImageView,tuxPenguinImageView,astronautPenguinImageView);
            pullPane.getChildren().add(cyborgPenguinImageView);
            pulltext.setText("You got a POPSTARPENGUINPLUSH");
            inventoryPane.getChildren().remove(legendaryImageView1);
            if (!(hasPopStar)) {
                inventoryPane.getChildren().add(popStarCardImageView);
                hasPopStar = true;
            }
        }
        if(pullName=="tux")
        {
            pullPane.getChildren().removeAll(neoSamuraiPenguinImageView,popStarPenguinImageView,rockStarPenguinImageView,saxophonePenguinImageView,cyborgPenguinImageView,kingPenguinImageView,tuxPenguinImageView,astronautPenguinImageView);
            pullPane.getChildren().add(tuxPenguinImageView);
            pulltext.setText("You got a TUXPENGUINPLUSH");
            inventoryPane.getChildren().remove(commonImageView1);
            if (!(hasTux)) {
                inventoryPane.getChildren().add(tuxCardImageView);
                hasTux = true;
            }
        }
        if(pullName=="sax")
        {
            pullPane.getChildren().removeAll(neoSamuraiPenguinImageView,popStarPenguinImageView,rockStarPenguinImageView,saxophonePenguinImageView,cyborgPenguinImageView,kingPenguinImageView,tuxPenguinImageView,astronautPenguinImageView);
            pullPane.getChildren().add(saxophonePenguinImageView);
            pulltext.setText("You got a SAXOPHONEPENGUINPLUSH");
            inventoryPane.getChildren().remove(mythicImageView2);
            if (!(hasSaxophone)) {
                inventoryPane.getChildren().add(saxophoneCardImageView);
                hasSaxophone =true;
            }
        }

        if(pullName=="king")
        {
            pullPane.getChildren().removeAll(neoSamuraiPenguinImageView,popStarPenguinImageView,rockStarPenguinImageView,saxophonePenguinImageView,cyborgPenguinImageView,kingPenguinImageView,tuxPenguinImageView,astronautPenguinImageView);
            pullPane.getChildren().add(kingPenguinImageView);
            pulltext.setText("You got a KINGPENGUINPLUSH");
            inventoryPane.getChildren().remove(rareImageView2);
            if (!(hasKing)) {
                inventoryPane.getChildren().add(kingCardImageView);
                hasKing = true;
            }
        }

//        pulltext.setTranslateY(0);
//        pulltext.setTranslateX(350);
//
//        pulltext.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 50));
//
//
//        mainGroup.getChildren().add(pullPane);
//        pullPane.getChildren().addAll(sgsbackButtonImageView);
//        pullPane.getChildren().add(pulltext);

//        popStarPenguinImageView.setFitWidth(600);
//        popStarPenguinImageView.setFitHeight(600);
//        popStarPenguinImageView.setTranslateX(450);
//        popStarPenguinImageView.setTranslateY(200);
//        popStarPenguinImageView.setPreserveRatio(true);

    }

    public void openInventoryScene()
    {
        mainGroup.getChildren().remove(mainPane);
        mainGroup.getChildren().remove(gamePane);
        mainGroup.getChildren().remove(settingsPane);
        mainGroup.getChildren().remove(babyGamePane);
        mainGroup.getChildren().remove(papaGamePane);
        mainGroup.getChildren().remove(shopPane);
        mainGroup.getChildren().add(inventoryPane);
        mainGroup.getChildren().remove(pullPane);
        updateIsSceneVariables("inventoryScene"); //updates current scene to shopScene because shopScene is currently opened
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
    public static int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min + 1)) + min);
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

    public static int lottery()
    {
        ArrayList<Integer>blank=new ArrayList<>();
        for(int a=0;a<1000;a++)
        {
            blank.add(1);
        }
        for(int b=0;b<100;b++)
        {
            blank.add(2);
        }
        for(int c=0;c<10;c++)
        {
            blank.add(3);
        }
        for(int d=0;d<1;d++)
        {
            blank.add(4);
        }
        return blank.get(getRandomNumber(0,blank.toArray().length+1));
    }

    public void enterCode() {
        String input = codeEnter.getText();
        if (input.equals("119EMERGENCYLOVE")) {
            iceCubes=119119119;
        }
        else if (input.equals("SUBTOPOITY1"))
        {
            iceCubes=2121212121;
            inventoryPane.getChildren().remove(majesticImageView);
            inventoryPane.getChildren().add(poity1CardImageView);
        }
        else if(input.equals("LEBRONSBEEEEEEHTHEGOATSSKIBIDIOHIOGYATT69"))
        {
            iceCubes=23232323;
            inventoryPane.getChildren().removeAll(majesticImageView,commonImageView1,commonImageView2,rareImageView1,rareImageView2,mythicImageView1,mythicImageView2,legendaryImageView1,legendaryImageView2);
            inventoryPane.getChildren().addAll(poity1CardImageView,neoSamuraiCardImageView,popStarCardImageView,saxophoneCardImageView,rockStarCardImageView,cyborgCardImageView,kingCardImageView,tuxCardImageView,astronautCardImageView);
        }
        else {
            codeEnter.setText("INVALID!!!");
        }
    }
}