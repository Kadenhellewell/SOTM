import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;

class Conditions extends VBox
{
    Conditions()
    {
        FlowPane buttons = new FlowPane();
        buttons.setHgap(5);
        buttons.getChildren().addAll(createButton(Types.YELLOW), createButton(Types.BLUE),
                createButton(Types.GREEN), createButton(Types.MAGENTA), createButton(Types.DAMAGE), createButton(Types.CUSTOM));
        this.getChildren().add(buttons);
        this.setSpacing(5);
    }

    Tile createTile(Types color)
    {
        Tile tile = new Tile(color);

        tile.setOnMouseClicked(e -> {
            this.getChildren().remove(tile);
        });
        return tile;
    }

    ConditionButton createButton(Types color)
    {
        ConditionButton button = new ConditionButton(color);
        button.setOnMouseClicked(e -> {
            this.getChildren().add(createTile(button.getColor()));
        });
        return button;
    }
}
