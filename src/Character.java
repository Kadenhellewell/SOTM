import javafx.animation.FadeTransition;
import javafx.animation.Timeline;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

class Character extends VBox
{
    private TextField name = new TextField();
    private Label status = new Label();
    private Spinner<Integer> HP;
    private boolean dead;
    private boolean main;
    private Teams m_team;
    private Color prefColor;
    boolean isMain(){return main;}
    boolean isDead() {return dead;}
    int getHP() {return HP.getValue();}
    BooleanProperty deathProperty = new SimpleBooleanProperty(false);
    IntegerProperty HPproperty = new SimpleIntegerProperty();
    private FadeTransition warning = new FadeTransition(Duration.millis(500), this);
    private Circle maxCircle;
    private Circle minCircle;

    Character(Teams team, boolean Main, int startHP)
    {
        this.m_team = team;
        switch(team)
        {
            case ENVIRONMENT:
                this.status.setText("Environment");
                break;
            case HERO:
                this.status.setText("Hero");
                break;
            case VILLAIN:
                this.status.setText("Villain");
                break;
        }
        HP = new Spinner<>(-1000, 1000, startHP);
        prefColor = Color.BLACK;
        this.status.setTextFill(Color.BLACK);
        warning.setFromValue(1.0);
        warning.setToValue(0.5);
        warning.setCycleCount(Timeline.INDEFINITE);
        warning.setAutoReverse(true);
        main = Main;
        dead = false;
        getChildren().add(this.status);
        getChildren().add(name);
        getChildren().add(HP);
        name.setPrefWidth(100);
        name.setStyle("-fx-control-inner-background: white; -fx-font: 14 arial;");
        HP.setStyle("-fx-control-inner-background: white; -fx-font: 14 arial;");
        HP.setEditable(true);
        HP.setPrefWidth(100);
        HPproperty.bind(HP.valueProperty());
        deathProperty.setValue(false);
        maxCircle = new Circle(5);
        minCircle = new Circle(5);
        maxCircle.setTranslateX(103);
        minCircle.setTranslateX(103);
        maxCircle.setTranslateY(-40);
        minCircle.setTranslateY(-40);
        maxCircle.setFill(Color.BLUE);
        minCircle.setFill(Color.RED);
        this.getChildren().add(maxCircle);
        this.getChildren().add(minCircle);
        maxCircle.setVisible(false);
        minCircle.setVisible(false);
        this.setOpacity(1);

        if(!main)
        {
            this.status.setText(this.status.getText() + "  (sub)");
        }

        //Add logic for Targets that die
        HP.valueProperty().addListener(ov ->
        {
            if(isMain())
            {
                if(HP.getValue() == 0)
                {
                    this.status.setTextFill(Color.RED);
                    name.setStyle("-fx-control-inner-background: red; -fx-font: 14 arial;");
                    HP.setStyle("-fx-control-inner-background: red; -fx-font: 14 arial;");
                    dead = true;
                    deathProperty.setValue(true);

                    if(m_team == Teams.HERO)
                    {
                        status.setText("Incapacitated Hero");
                    }
                    else if(m_team == Teams.VILLAIN)
                    {
                        status.setText("You Win");
                    }
                    warning.pause();
                    this.setOpacity(1);
                }
                else if(HP.getValue() <= 5 && HP.getValue() > 0 && isMain())
                {
                    //TODO add fade transition here
                    dead = false;
                    this.status.setTextFill(Color.RED);
                    name.setStyle("-fx-control-inner-background: red; -fx-font: 14 arial;");
                    HP.setStyle("-fx-control-inner-background: red; -fx-font: 14 arial;");

                    if(m_team == Teams.HERO)
                    {
                        status.setText("Hero");
                    }
                    else if(m_team == Teams.VILLAIN)
                    {
                        status.setText("Villain");
                    }
                    warning.play();
                }
                else if(HP.getValue() > 5)//The purpose of this else statement is to return the main targets to their original form if they are brought back to life
                {
                    this.status.setTextFill(prefColor);
                    name.setStyle("-fx-control-inner-background: white; -fx-font: 14 arial;");
                    HP.setStyle("-fx-control-inner-background: white; -fx-font: 14 arial;");
                    dead = false;
                    deathProperty.setValue(false);

                    if(m_team == Teams.HERO)
                    {
                        status.setText("Hero");
                    }
                    else if(m_team == Teams.VILLAIN)
                    {
                        status.setText("Villain");
                    }
                    warning.pause();
                    this.setOpacity(1);
                }
                else
                {
                    dead = false;
                    deathProperty.setValue(false);
                    if(m_team == Teams.HERO)
                    {
                        this.status.setTextFill(Color.BLUE);
                        name.setStyle("-fx-control-inner-background: blue; -fx-font: 14 arial;");
                        HP.setStyle("-fx-control-inner-background: blue; -fx-font: 14 arial;");
                        status.setText("I cannot die!");
                    }
                    else if(m_team == Teams.VILLAIN)
                    {
                        this.status.setTextFill(Color.GREEN);
                        name.setStyle("-fx-control-inner-background: green; -fx-font: 14 arial;");
                        HP.setStyle("-fx-control-inner-background: green; -fx-font: 14 arial;");
                        status.setText("You can't beat me");
                    }
                    warning.pause();
                }
            }
            else
            {
                if(HP.getValue() == 0)
                {
                    dead = true;
                    deathProperty.setValue(true);
                }
            }
        });

        this.status.setOnMouseClicked(e -> {
            prefColor = Color.rgb((int)(Math.random()*255), (int)(Math.random()*255), (int)(Math.random()*255));
            this.status.setTextFill(prefColor);
        });
        this.setOpacity(1);
    }

    void setMax(boolean isMax)
    {
        maxCircle.setVisible(false);
        if(isMax)
        {
            maxCircle.setVisible(true);
        }
    }
    void setMin(boolean isMin)
    {
        minCircle.setVisible(false);
        {
            if(isMin)
            {
                minCircle.setVisible(true);
            }
        }
    }
}

