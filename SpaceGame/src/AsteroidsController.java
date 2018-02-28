public class AsteroidsController {
    private SmallAsteroid smallAsteroid;
    private LargeAsteroids largeAsteroids;
    private int thrust;

    public AsteroidsController(SmallAsteroid small){
        smallAsteroid = small;
        thrust = 0;
        smallAsteroid.setRotation(0);
    }

    public AsteroidsController(LargeAsteroids large) {
        largeAsteroids = large;
        thrust = 0;
        largeAsteroids.setRotation(0);
    }
    public void rotateSmall(String x)
    {
        x = x.toLowerCase();
        if(x.equals("right")){
            smallAsteroid.turn(90);
        }else if(x.equals("left")){
            smallAsteroid.turn(-90);
        }
    }
    public void rotateLarge(String x)
    {
        x = x.toLowerCase();
        if(x.equals("right")){
            largeAsteroids.turn(90);
        }else if(x.equals("left")){
            largeAsteroids.turn(-90);
        }

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
    public String getPosSmall()
    {
        return "" + smallAsteroid.getX() + "," + smallAsteroid.getY();
    }
    public String getPosLarge()
    {
        return "" + largeAsteroids.getX() + "," + largeAsteroids.getY();
    }
}
