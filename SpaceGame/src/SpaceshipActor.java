import mayflower.*;

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
        if(this.isTouching(LargeAsteroids.class))
        {
            gameOver = energy.remove(this);
            System.out.println("GameOver is: "+gameOver);
        }


    }
}
