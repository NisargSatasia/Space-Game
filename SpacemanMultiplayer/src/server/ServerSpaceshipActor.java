package server;

import mayflower.*;

import java.util.List;

public class ServerSpaceshipActor extends Actor
{
    public ServerSpaceshipActor()
    {
        setImage("img/SpaceshipNoCannon.png");
    }
    public List<Actor> getIntersection()
    {
        return getIntersectingObjects(Actor.class);
    }
    @Override
    public void act()
    {


    }
}
