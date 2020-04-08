import javafx.scene.layout.StackPane;

public class ConditionPane extends StackPane
{
    ConditionPane()
    {
        this.getChildren().add(new Conditions());
    }
}
