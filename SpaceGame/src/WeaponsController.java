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
    public void updateRotation()
    {
        weapon.turnTowards(cross);
    }
    public void updatePos()
    {
        cross.setLocation(Mayflower.getMouseInfo().getX(),Mayflower.getMouseInfo().getY());
        weapon.setLocation(spaceship.getPosX(),spaceship.getPosY());
    }
}
