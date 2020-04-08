import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

class ConditionButton extends Circle
{
    private Types colorFill;
    ConditionButton(Types color)
    {
        this.setRadius(15);
        colorFill = color;
        setColors();
    }

    Types getColor()
    {
        return this.colorFill;
    }

    void setColors()
    {
        switch(colorFill)
        {
            case YELLOW:
                this.setFill(Color.YELLOW);
                break;
            case BLUE:
                this.setFill(Color.BLUE);
                break;
            case GREEN:
                this.setFill(Color.GREEN);
                break;
            case DAMAGE:
                this.setFill(Color.RED);
                break;
            case MAGENTA:
                this.setFill(Color.MAGENTA);
                break;
            case CUSTOM:
                this.setFill(Color.NAVY);
                break;
            default:
                this.setFill(Color.DARKMAGENTA);
        }
    }
}
