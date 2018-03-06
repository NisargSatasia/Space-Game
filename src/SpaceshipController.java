public class SpaceshipController
{
    private SpaceshipActor spaceship;
    private int thrust;
    private int X;
    private int Y;
    public SpaceshipController(SpaceshipActor s)
    {
        spaceship = s;
        thrust = 0;
        spaceship.setRotation(0);
        X = spaceship.getX();
        Y = spaceship.getY();
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
    public int currentAngle()
    {
        return spaceship.getRotation();
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
    public void setPos()
    {
        int x = spaceship.getX();
        int y = spaceship.getY();
        if(x >= 1008){
            spaceship.setLocation(0, y);
        }else if(x <= 0){
            spaceship.setLocation(1008, y);
        }else if(y >= 728){
            spaceship.setLocation(x, 0);
        }else if(y <= 0){
            spaceship.setLocation(x, 728);
        }
    }
    public void hit(boolean b)
    {
        if(b)
            spaceship.setImage("img/SpaceshipHit.png");
        else spaceship.setImage("img/SpaceshipNoCannon.png");
    }
}
