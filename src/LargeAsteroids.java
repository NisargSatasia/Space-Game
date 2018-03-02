import mayflower.*;

import java.util.List;

public class LargeAsteroids extends Asteroids{

    //private int dir;
    //private Timer timer;

    public LargeAsteroids()
    {
        setImage("img/LargeAsteroid.png");

    }
    public List<Actor> getIntersection()
    {
        return getIntersectingObjects(Actor.class);
    }
    public void act()
    {
        move(5);
    }


}
