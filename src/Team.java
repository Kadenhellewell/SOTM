import javafx.scene.layout.VBox;
import java.util.Vector;

class Team extends VBox
{
    private Teams team;
    private Vector<Character> members;
    Team(int startTargets, Teams team)
    {
        members = new Vector<Character>();
        this.team = team;
        int startHP = 30;
        switch(team)
        {
            case ENVIRONMENT:
                startHP = 10;
                break;
            case HERO:
                startHP = 30;
                break;
            case VILLAIN:
                startHP = 100;
                break;
        }
        for(int i = 0; i < startTargets; i++)
        {
            addTarget(true, startHP);
        }
    }

    void addTarget(boolean main, int startHP)
    {
        Character toAdd = new Character(this.team, main, startHP);
        this.getChildren().add(toAdd);
        members.add(toAdd);
        addListeners(toAdd);
        showMax_MinHP();
    }

    private void addListeners(Character newCharacter)
    {
        if(!newCharacter.isMain())
        {
            newCharacter.deathProperty.addListener(ov ->
            {
                if(newCharacter.isDead())
                {
                    this.getChildren().remove(newCharacter);
                    members.remove(newCharacter);
                    if(!members.isEmpty())
                    {
                        showMax_MinHP();
                    }
                }
            });
        }
        newCharacter.HPproperty.addListener(ov ->
        {
            showMax_MinHP();
            newCharacter.HPproperty.getValue(); //If I don't include this function call, this listener is only sometimes called. I don't know why.
        });
    }

    void showMax_MinHP()
    {
        Character currMax = members.get(0);
        Character currMin = members.get(0);
        for(Character target : members)
        {
            target.setMax(false);
            target.setMin(false);
            if(target.getHP() == 0)
            {
                continue;
            }
            if (target.getHP() > currMax.getHP())
            {
                currMax = target;
            }
            if(target.getHP() < currMin.getHP())
            {
                currMin = target;
            }
        }
        currMax.setMax(true);
        currMin.setMin(true);
        for(Character target : members)
        {
            if(target.getHP() == currMax.getHP())
            {
                target.setMax(true);
            }
            if(target.getHP() == currMin.getHP())
            {
                target.setMin(true);
            }
        }
    }
}
