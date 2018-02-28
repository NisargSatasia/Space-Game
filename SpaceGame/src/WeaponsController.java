import mayflower.Mayflower;

public class WeaponsController
{
    private WeaponsActor weapon;
    private Crosshair cross;
    private SpaceshipController spaceship;
    public WeaponsController(WeaponsActor a,Crosshair c,SpaceshipController s)
    {
        weapon = a;
        cross = c;
        spaceship = s;
    }
    public void updateCrosshairPos()
    {
        cross.setLocation(Mayflower.getMouseInfo().getX()-24,Mayflower.getMouseInfo().getY());
    }
    public void updateGunPos()
    {
        weapon.setLocation(spaceship.getPosX(),spaceship.getPosY());
    }
    public void updateGunRotation()
    {
        weapon.turnTowards(cross);
    }
    public void resetRotation()
    {
        weapon.setRotation(spaceship.currentAngle());
    }
    public int getXPos()
    {
        return weapon.getX();
    }
    public int getYPos()
    {
        return weapon.getY();
    }
    public int currentAngle()
    {
        return weapon.getRotation();
    }
}
