import mayflower.Actor;

import java.util.ArrayList;

public class EnergyBar
{
    private GameWorld world;
    private ArrayList<Actor> energyLevel;
    int removeThis;
    public EnergyBar(GameWorld w)
    {
        world = w;
        energyLevel = new ArrayList<Actor>();
        removeThis = 4;
        for(int i = 0; i<5;i++)
        {
            energyLevel.add(new EnergyPiece());
        }
    }
    public void addEnergyBar()
    {
        int y = 758;
        int x = 1014;
        for(int i=0; i<5;i++)
        {
            world.addObject(energyLevel.get(i),x,y);
            x -= 11;
        }
    }
    public boolean remove()
    {
        if(removeThis < 0)
        {
            energyLevel.remove(removeThis);
            removeThis--;
            return false;
        }
        return true;
    }
}
