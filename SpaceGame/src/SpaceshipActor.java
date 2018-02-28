import mayflower.Actor;

import java.util.List;

public class SpaceshipActor extends Actor
{
    public SpaceshipActor()
    {
        setImage("img/SpaceshipNoCannon.png");
    }
    public List<Laser> getIntersection()
    {
        return getIntersectingObjects(Laser.class);
    }
    @Override
    public void act()
    {

    }
}
