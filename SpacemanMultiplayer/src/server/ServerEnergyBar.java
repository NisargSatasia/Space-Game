package server;

import mayflower.*;
import java.util.ArrayList;

public class ServerEnergyBar
{
    private ArrayList<Actor> energyLevel;
    int removeThis;
    public ServerEnergyBar()
    {

        energyLevel = new ArrayList<Actor>();
        removeThis = 4;
        for(int i = 0; i<5;i++)
        {
            energyLevel.add(new ServerEnergyPiece());
            System.out.println(energyLevel.get(i));
        }
        System.out.println(this);
    }
    public ArrayList<Actor> getEnergyBar()
    {
        return energyLevel;
    }
    public boolean remove(ServerGameWorld w)
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
