import mayflower.*;

import java.util.List;

public class SpaceshipActor extends Actor
{
    public SpaceshipActor()
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
