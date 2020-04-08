import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;



public class SOTM extends Application
{
    private GameField main;
    private Button reset;
    private Button start;
    private ImageView background;
    private String[] places = {"Tomb of Anubis", "Time Cataclysm", "Rook City", "Ruins of Atlantis", "Insula Primalis",
        "Megalopolis", "Realm of Discord", "Pike Industrial Complex", "Mobile Defense Platform", "The Block", "Silver Gulch",
        "Final Wasteland", "Omnitron IV", "Wagner Mars Base", "Freedom Tower", "Dok'Thorath", "Enclave of Endlings",
        "Celestial Tribunal", "Magmaria", "Temple of Zhu Long", "Court of Blood", "Fantastical Festival", "Nexus of the Void",
        "Mordengrad", "Champion Studios", "Fort Adamant", "Maerynian Refuge"};

    private String[] villainChoices = {"Baron Blade", "Omnitron", "Gloomweaver", "Plague Rat", "Iron Legacy",
        "Dreamer", "La capitan", "The Matriarch", "Progeny", "Akash'bhuta", "Abuscade", "Set Back", "Citizen Dawn",
        "Grand Warlord Voss", "Spite", "The Chairman", "Apostate", "The Enhead", "Kismet", "Miss Information", "Fright Train",
        "Friction", "Ermine", "Proletariat"};

    private ImageView[] placePics = {new ImageView("https://sentinelsofthemultiverse.com/sites/sentinelsofthemultiverse.com/files/cards/images/Tomb%20of%20Anubis.png"),
        new ImageView("https://sentinelsofthemultiverse.com/sites/sentinelsofthemultiverse.com/files/cards/images/Time%20Cataclysm.png"),
        new ImageView("https://sentinelsofthemultiverse.com/sites/sentinelsofthemultiverse.com/files/cards/images/Rook%20City_0.png"),
            new ImageView("https://sentinelsofthemultiverse.com/sites/sentinelsofthemultiverse.com/files/cards/images/Ruins%20of%20Atlantis.png"),
        new ImageView("https://sentinelsofthemultiverse.com/sites/sentinelsofthemultiverse.com/files/cards/images/Insula%20Primalis.png"),
        new ImageView("https://sentinelsofthemultiverse.com/sites/sentinelsofthemultiverse.com/files/cards/images/Megalopolis_1.png"),
        new ImageView("https://sentinelsofthemultiverse.com/sites/sentinelsofthemultiverse.com/files/cards/images/Realm%20of%20Discord.png"),
        new ImageView("https://sentinelsofthemultiverse.com/sites/sentinelsofthemultiverse.com/files/cards/images/Pike%20Industrial%20Complex.png"),
        new ImageView("https://sentinelsofthemultiverse.com/sites/sentinelsofthemultiverse.com/files/cards/images/Mobile%20Defense%20Platform.png"),
        new ImageView("https://sentinelsofthemultiverse.com/sites/sentinelsofthemultiverse.com/files/cards/images/The%20Block.png"),
        new ImageView("https://www.planetongames.com/7670-thickbox_default/silver-gulch-1883-environment-sentinels-of-the-multiverse.jpg"),
        new ImageView("https://sentinelsofthemultiverse.com/sites/sentinelsofthemultiverse.com/files/cards/images/The%20Final%20Wasteland.png"),
        new ImageView("https://cf.geekdo-images.com/opengraph/img/kj9jKGZADpABczxCyjn-z549jX4=/fit-in/1200x630/pic2228573.png"),
        new ImageView("https://sentinelsofthemultiverse.com/sites/sentinelsofthemultiverse.com/files/cards/images/Wagner%20Mars%20Base.png"),
        new ImageView("https://sentinelsofthemultiverse.com/sites/sentinelsofthemultiverse.com/files/cards/images/Freedom%20Tower.png"),
        new ImageView("https://f4.bcbits.com/img/a2263223896_10.jpg"),
        new ImageView("https://i.ytimg.com/vi/-EYRCHLJXDg/maxresdefault.jpg"),
        new ImageView("https://www.gtsdistribution.com/images/GTMSOTMCLTB_l.jpg"),
        new ImageView("https://cf.geekdo-images.com/medium/img/qAKxw4__ygQO7X6KfU-4qA2OXn8=/fit-in/500x500/filters:no_upscale()/pic3849350.jpg"),
        new ImageView("https://static.tvtropes.org/pmwiki/pub/images/a835ea84_fb25_46ce_b5e6_65c9843a9d0a.png"),
        new ImageView("setUp.jpg"),
        new ImageView("setUp.jpg"),
        new ImageView("setUp.jpg"),
        new ImageView("setUp.jpg"),
        new ImageView("setUp.jpg"),
        new ImageView("setUp.jpg"),
        new ImageView("setUp.jpg")};
    private ComboBox<String> environments = new ComboBox<>();
    private ComboBox<String> villainMenu =  new ComboBox<>();

    public void start(Stage st)
    {
        StackPane setUpStack = new StackPane();
        StackPane gameStack = new StackPane();
        FlowPane flow = new FlowPane();
        flow.setRowValignment(VPos.TOP);

        Scene setUpScene = new Scene(setUpStack, 1500, 700);
        Scene gameScene = new Scene(gameStack, 1500, 700);

        Spinner<Integer> numHeroes = new Spinner<>(2, 8, 5);
        Label heroes = new Label("Number of Heroes", numHeroes);
        heroes.setContentDisplay(ContentDisplay.BOTTOM);

        Spinner<Integer> numVillains = new Spinner<>(1, 5, 1);
        Label villains = new Label("Number of Villains", numVillains);
        villains.setContentDisplay(ContentDisplay.BOTTOM);

        environments.setPrefWidth(200);
        environments.setValue("Tomb of Anubis");
        ObservableList<String> items =
                FXCollections.observableArrayList(places);
        environments.getItems().addAll(items);
        Label placeList = new Label("Environment", environments);
        placeList.setContentDisplay(ContentDisplay.BOTTOM);

        villainMenu.setPrefWidth(200);
        villainMenu.setValue("Baron Blade");
        ObservableList<String> vItems = FXCollections.observableArrayList(villainChoices);
        villainMenu.getItems().addAll(vItems);


        start = new Button("Start");
        reset = new Button("Reset");
        reset.translateYProperty().bind(setUpStack.heightProperty().divide(2).subtract(20));
        reset.translateXProperty().bind(setUpStack.widthProperty().divide(2).subtract(30));

        background = new ImageView("setUp.jpg");
        start.setOnAction(e -> {
            this.main = new GameField(numHeroes.getValue(), numVillains.getValue(), placePics[environments.getSelectionModel().getSelectedIndex()]);
            gameStack.getChildren().addAll(main, reset);
            st.setScene(gameScene);
        });

        reset.setOnAction(e -> {
            gameStack.getChildren().removeAll(main, reset);
            st.setScene(setUpScene);
        });

        flow.getChildren().addAll(heroes, villains, placeList, start);
        setUpStack.getChildren().addAll(background, flow);
        st.setScene(setUpScene);
        st.show();
    }
}