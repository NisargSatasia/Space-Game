import mayflower.*;

public class SpaceshipActor extends Actor
{
    public SpaceshipActor()
    {
        setImage("img/SpaceshipNoCannon.png");
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
