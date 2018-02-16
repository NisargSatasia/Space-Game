import javafx.scene.input.KeyCode;
import mayflower.Mayflower;
import mayflower.World;
import mayflower.*;

import java.util.ArrayList;

public class GameWorld extends World
{
    private ArrayList<Actor> energyBar;
    private EnergyBar energy;
    private boolean gameOver;
    private Collectable test = new Collectable();
    private SpaceshipActor spaceship = new SpaceshipActor();
    private SpaceshipController control;
    private Crosshair crosshair;
    private WeaponsActor weapons;
    private WeaponsController controlWeapons;
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
        int y = 728;
        int x = 1006;
        for(int i=0; i<5;i++)
        {
            addObject(energyBar.get(i),x,y);
            System.out.println("Added energy piece @ "+x+" "+y);
            System.out.println(energyBar.get(i));
            x -= 11;
        }
    }
    @Override
    public void act()
    {
        controlWeapons.updatePos();
        controlWeapons.updateRotation();
        if(Mayflower.mouseClicked(test))
        {
            gameOver = energy.remove(this);
            System.out.println("GameOver is: "+gameOver);
        }
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
        spaceship.move(control.getThrust());

    }
}
