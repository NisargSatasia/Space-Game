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
        if(X >= (1008-32)){
            X = 0;
            spaceship.setLocation((double)X, (double)Y);
        }else if(X <= (0+32)){
            X = 1008;
            spaceship.setLocation((double)X, (double)Y);
        }else if(Y >= (728-32)){
            Y = 0;
            spaceship.setLocation((double)X, (double)Y);
        }else if(Y <= (0+32)){
            Y = 32;
            spaceship.setLocation((double)X, (double)Y);
        }
        spaceship.setLocation((double)X, (double)Y);
    }
}
