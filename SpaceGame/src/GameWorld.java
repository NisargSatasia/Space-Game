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
    private AsteroidsController asteroids;
    private SmallAsteroid smallAsteroid = new SmallAsteroid();
    private LargeAsteroids largeAsteroids = new LargeAsteroids();
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

        // small asteroids;
        addObject(smallAsteroid,300,300);
        asteroids = new AsteroidsController(smallAsteroid);
        //large asteroids;
        addObject(largeAsteroids,200,200);
        asteroids = new AsteroidsController(largeAsteroids);

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
        if(Mayflower.mouseClicked(test))
        {
            gameOver = energy.remove(this);
            System.out.println("GameOver is: "+gameOver);
        }
        // make the actors bound.
        //if(Mayflower.){

        //}
        if(gameOver)
        {
            World startingWorld = new StartMenu();
            Mayflower.setWorld(startingWorld);
        }
        if(Mayflower.isKeyDown(Keyboard.KEY_LEFT))
        {
            control.rotateLeft();
        }
        if(Mayflower.isKeyDown(Keyboard.KEY_RIGHT))
        {
            control.rotateRight();
        }
        if(Mayflower.isKeyDown(Keyboard.KEY_UP))
        {
            control.increaseThrust();
        }
        if(Mayflower.isKeyDown(Keyboard.KEY_DOWN))
        {
            control.decreaseThrust();
        }
        spaceship.move(control.getThrust());
    }
}
