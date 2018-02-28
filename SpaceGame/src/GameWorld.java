import javafx.scene.input.KeyCode;
import mayflower.Mayflower;
import mayflower.World;
import mayflower.*;

import java.util.ArrayList;
import java.util.List;

public class GameWorld extends World
{
    private ArrayList<Actor> energyBar;
    private EnergyBar energy;
    private boolean gameOver;
    private Collectable test = new Collectable();
    private SpaceshipActor spaceship = new SpaceshipActor();
    private SpaceshipController control;
    private SmallAsteroid smallAsteroid = new SmallAsteroid();
    private LargeAsteroids largeAsteroids = new LargeAsteroids();

    private Crosshair crosshair;
    private WeaponsActor weapons;
    private WeaponsController controlWeapons;

    private long damageCooldown = 1000;
    private long startCooldown;
    private long currentTime;
    private boolean wasHit;

    public GameWorld()
    {
        energy = new EnergyBar();
        energyBar = energy.getEnergyBar();
        addEnergyBar();
        setBackground("img/background.jpg");
        addObject(test,100,100);
        addEnergyBar();
        addObject(spaceship,500,350);
        control = new SpaceshipController(spaceship);



        crosshair = new Crosshair();
        addObject(crosshair,0,0);
        weapons = new WeaponsActor();
        addObject(weapons,500,350);
        controlWeapons = new WeaponsController(weapons,crosshair,control);

    }
    public void addEnergyBar()
    {

        for(int i=0; i<5;i++)
        {
            int y = 728;
            int x = 1006;
            addObject(energyBar.get(i),x,y);
            System.out.println("Added energy piece @ "+x+" "+y);
            System.out.println(energyBar.get(i));
            x -= 11;
        }
    }
    @Override
    public void act()
    {

        // make the actors bound.
        //if(control.getPosX()>= (1008-32) || control.getPosX()<=(0+32) || control.getPosY()>= (728-32) || control.getPosY()>= (0+32) ){
        //   control.setPos();
        //}
        //System.out.println(control.getPosX() + "        " + control.getPosY());



        currentTime = System.currentTimeMillis();

        if(gameOver)
        {
            World startingWorld = new StartMenu();
            Mayflower.setWorld(startingWorld);
        }
        if(Mayflower.isKeyDown(Keyboard.KEY_A))
        {
            control.rotateLeft();
        }
        if(Mayflower.isKeyDown(Keyboard.KEY_D))
        {
            control.rotateRight();
        }
        if(Mayflower.isKeyDown(Keyboard.KEY_W))
        {
            control.increaseThrust();
        }
        if(Mayflower.isKeyDown(Keyboard.KEY_S))
        {
            control.decreaseThrust();
        }
        List<Actor> intersecting = spaceship.getIntersection();
        for(Actor a : intersecting)
        {
            if((a instanceof Laser || a instanceof Asteroids) && !wasHit) {
                gameOver = energy.remove(this);
                startCooldown = currentTime;
                wasHit = true;
            }
            if(currentTime-startCooldown>=damageCooldown && wasHit)
            {
                wasHit = false;
            }

        }
        spaceship.move(control.getThrust());
        controlWeapons.resetRotation();
        controlWeapons.updateCrosshairPos();
        controlWeapons.updateGunPos();
        controlWeapons.updateGunRotation();
        if(Mayflower.mouseClicked(crosshair))
        {
            Laser l = new Laser();
            addObject(l,controlWeapons.getXPos()+16,controlWeapons.getYPos()+24);
            l.setRotation(controlWeapons.currentAngle());
            l.move(64);
        }
    }
}
