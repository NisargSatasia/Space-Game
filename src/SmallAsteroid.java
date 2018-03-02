import mayflower.Actor;

import java.util.List;

public class SmallAsteroid extends Asteroids{

    public SmallAsteroid()
    {
        setImage("img/SmallAsteroid.png");
    }
    public List<Actor> getIntersection()
    {
        return getIntersectingObjects(Actor.class);
    }
    public void act()
    {
        move(10);

    }


}
