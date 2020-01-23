import javafx.geometry.VPos;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;


enum Teams {HERO, VILLAIN, ENVIRONMENT}

class GameField extends BorderPane
{
    GameField(int numHeroes, int numVillains, ImageView pic)
    {
        //create necessary panes
        Team heroes = new Team(numHeroes, Teams.HERO);
        Team villains = new Team(numVillains, Teams.VILLAIN);
        Team environment = new Team(0, Teams.ENVIRONMENT);
        ScrollPane scroll = new ScrollPane();
        StackPane scrollStack = new StackPane();



        //Add Target columns and Buttons
        FlowPane targets = new FlowPane();
        targets.getChildren().add(heroes);
        targets.getChildren().add(addTargetButton(heroes, "Add Hero Target"));

        targets.getChildren().add(villains);
        targets.getChildren().add(addTargetButton(villains, "Add Villain Target"));

        targets.getChildren().add(environment);
        targets.getChildren().add(addTargetButton(environment, "Add Environment Target"));

        //Aesthetics
        targets.setRowValignment(VPos.TOP); //This is done so that the buttons stay at the top of the window
        targets.setHgap(50);



        //Add the news feed thing to satisfy the socket requirement
        //Weather feed = new Weather("https://w1.weather.gov/xml/current_obs/KSKA.rss");

        //Set everything up
        scroll.setFitToWidth(true);
        scrollStack.getChildren().addAll(pic, targets);
        scroll.setContent(scrollStack);
        //scroll.setStyle("-fx-control-inner-background: transparent;");

        this.setCenter(scroll);
        this.setRight(new ConditionPane());
    }

    private Button addTargetButton(Team team, String text)
    {
        Button addTarget = new Button(text);
        addTarget.setOpacity(1);
        addTarget.setOnAction(e ->
        {
            team.addTarget(false, 10);
        });
        return addTarget;
    }
}

