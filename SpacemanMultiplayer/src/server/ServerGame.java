package server;

import mayflower.Actor;
import mayflower.Direction;
import mayflower.MayflowerHeadless;
import mayflower.World;
import mayflower.net.Server;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ServerGame extends MayflowerHeadless
{
    private Map<Integer, ArrayList<Actor>> actors;
    private ServerGameWorld world;

    public ServerGame(Server server)
    {
        super("Server", 800, 600);
        actors = new HashMap<Integer, ArrayList<Actor>>();

        world = new ServerGameWorld(server);
        this.setWorld(world);
    }

    public void process(int i, String s)
    {
        ArrayList<Actor> actor = actors.get(i);
    }

    public void join(int i)
    {
        ServerSpaceshipActor actor = new ServerSpaceshipActor();
        ServerWeaponsActor actor2 = new ServerWeaponsActor();
        ServerCrosshair actor3 = new ServerCrosshair();
        ServerEnergyBar actor4 = new ServerEnergyBar();
        int x = (int)(Math.random() * 700) + 50;
        int y = (int)(Math.random() * 500) + 50;
        world.addClient(actor);
        world.addWeapons(actor3,actor2);
        world.addEnergyBar(actor4);
        ArrayList<Actor> actorList = new ArrayList<Actor>();
        actorList.add(actor);
        actorList.add(actor2);
        actorList.add(actor3);
        actors.put(i, actorList);

    }

    public void leave(int i)
    {
        ArrayList<Actor> actor = actors.get(i);
        for(Actor a : actor)
        {
            world.removeObject(a);
        }

    }

    @Override
    public void init()
    {
    }
}
