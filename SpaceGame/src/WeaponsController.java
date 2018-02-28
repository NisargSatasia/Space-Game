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
        cross.setLocation(Mayflower.getMouseInfo().getX()-25,Mayflower.getMouseInfo().getY());

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
}
