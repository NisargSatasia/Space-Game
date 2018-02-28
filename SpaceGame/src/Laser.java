import mayflower.*;

public class Laser extends Actor
{
    public Laser()
    {
        setImage("img/Laser.png");
    }
    @Override public void act()
    {
        move(10);
    }
}
