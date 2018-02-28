import mayflower.*;
import java.util.ArrayList;

public class EnergyBar
{
    private ArrayList<Actor> energyLevel;
    int removeThis;
    public EnergyBar()
    {

        energyLevel = new ArrayList<Actor>();
        removeThis = 4;
        for(int i = 0; i<5;i++)
        {
            energyLevel.add(new EnergyPiece());
            System.out.println(energyLevel.get(i));
        }
        System.out.println(this);
    }
    public ArrayList<Actor> getEnergyBar()
    {
        return energyLevel;
    }
    public boolean remove(GameWorld w)
    {
        if(removeThis >= 0)
        {
            w.removeObject(energyLevel.get(removeThis));
            removeThis--;
            System.out.println("Removed");
            return false;
        }
        return true;
    }
}
