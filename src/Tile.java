import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

enum Types {YELLOW, GREEN, DAMAGE, CUSTOM, BLUE, MAGENTA}

public class Tile extends StackPane
{
    private Label label;
    private Rectangle tile;
    private Types m_type;
    private String prefSize;
    private TextField damage;
    private FlowPane flow;
    Tile(Types color)
    {
        prefSize = "-fx-font: 18 arial";
        flow = new FlowPane();
        label = new Label("Tile");
        damage  = new TextField();
        damage.setStyle(prefSize);
        damage.setPrefWidth(165);
        damage.setStyle(prefSize);
        //-fx-control-inner-background: transparent;
        tile = new Rectangle();
        tile.setStroke(Color.BLACK);
        tile.setHeight(60);
        tile.setWidth(300);
        m_type = color;
        label.setStyle("-fx-text-fill: black; " + prefSize);
        flow.setTranslateY(20);
        flow.setTranslateX(130);
        configureM_type();
        this.getChildren().addAll(tile, flow);
    }

    void configureM_type()
    {
        switch(m_type)
        {
            case YELLOW:
                tile.setFill(Color.YELLOW);
                label.setText("Damage Dealt + 1");
                flow.getChildren().add(label);
                break;
            case BLUE:
                tile.setFill(Color.BLUE);
                label.setStyle("-fx-text-fill: white; " + prefSize);
                label.setText("Damage Dealt - 1");
                flow.getChildren().add(label);
                break;
            case GREEN:
                tile.setFill(Color.GREEN);
                label.setStyle("-fx-text-fill: white; " + prefSize);
                label.setText("Damage Taken - 1");
                flow.getChildren().add(label);
                break;
            case DAMAGE:
                tile.setFill(Color.RED);
                label.setText("All Damage ");
                damage.setPromptText("Damage Type");
                flow.setTranslateX(75);
                flow.setTranslateY(13);
                flow.getChildren().addAll(label, damage);
                break;
            case CUSTOM:
                tile.setFill(Color.NAVY);
                damage.setPromptText("Custom Condition");
                flow.setTranslateY(13);
                flow.getChildren().add(damage);
                break;
            case MAGENTA:
                tile.setFill(Color.MAGENTA);
                label.setText("Damage Taken + 1");
                flow.getChildren().add(label);
                break;
            default:
                tile.setFill(Color.DARKMAGENTA);
                label.setText("I don't have all of the tiles");
                flow.getChildren().add(label);
        }
    }
}
