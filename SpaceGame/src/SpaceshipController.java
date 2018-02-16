public class SpaceshipController
{
    private SpaceshipActor spaceship;
    private int thrust;
    public SpaceshipController(SpaceshipActor s)
    {
        spaceship = s;
        thrust = 0;
        spaceship.setRotation(0);
    }
    public void rotateRight()
    {
        spaceship.turn(5);
    }
    public void rotateLeft()
    {
        spaceship.turn(-5);
    }
    public void decreaseThrust()
    {
        thrust--;
        if(thrust<0)
            thrust++;
    }
    public void increaseThrust()
    {
        thrust++;
        if(thrust>5)
            thrust--;
    }
    public int getThrust()
    {
        return thrust;
    }
    public int getPosX()
    {
        return spaceship.getX();
    }
    public int getPosY()
    {
        return spaceship.getY();
    }
}
