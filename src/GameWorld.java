import javafx.scene.input.KeyCode;
import mayflower.Mayflower;
import mayflower.World;
import mayflower.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
    private long currentTime = System.currentTimeMillis();
    private boolean wasHit;

    private Random rand = new Random(System.currentTimeMillis()+32);
    private long asteroidTimer = 2000;
    private long asteroidTimerStart;

    public GameWorld()
    {
        asteroidTimerStart = currentTime;
        energy = new EnergyBar();
        energyBar = energy.getEnergyBar();
        addEnergyBar();
        setBackground("img/background.jpg");
        addObject(test,100,100);
        addEnergyBar();
        addObject(spaceship,500,350);
        control = new SpaceshipController(spaceship);
        setPaintOrder(Asteroids.class);


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

        // make the actors bound.
        //if(control.getPosX()>= (1008-32) || control.getPosX()<=(0+32) || control.getPosY()>= (728-32) || control.getPosY()>= (0+32) ){
        //   control.setPos();
        //}
        //System.out.println(control.getPosX() + "        " + control.getPosY());



        currentTime = System.currentTimeMillis();
        List<Actor> objects = this.getObjects();
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
        if(currentTime-asteroidTimerStart >= asteroidTimer)
        {
            int a = rand.nextInt(4);
            if(a == 0)
            {
                int randX = rand.nextInt(1024);
                int type = rand.nextInt(2);
                Asteroids ast;
                if(type == 0)
                {
                   ast = new SmallAsteroid();
                }
                else if(type == 1)
                {
                    ast = new LargeAsteroids();
                }
                else ast = new LargeAsteroids();
                addObject(ast,randX,-100);
                int locX = rand.nextInt(808)+100;
                int locY = rand.nextInt(528)+100;
                ast.turnTowards(locX,locY);
            }
            else if(a == 1)
            {
                int randY = rand.nextInt(768);
                int type = rand.nextInt(2);
                Asteroids ast;
                if(type == 0)
                {
                    ast = new SmallAsteroid();
                }
                else if(type == 1)
                {
                    ast = new LargeAsteroids();
                }
                else ast = new LargeAsteroids();
                addObject(ast,1124,randY);
                int locX = rand.nextInt(808)+100;
                int locY = rand.nextInt(528)+100;
                ast.turnTowards(locX,locY);
            }
            else if(a == 2)
            {
                int randX = rand.nextInt(1024);
                int type = rand.nextInt(2);
                Asteroids ast;
                if(type == 0)
                {
                    ast = new SmallAsteroid();
                }
                else if(type == 1)
                {
                    ast = new LargeAsteroids();
                }
                else ast = new LargeAsteroids();
                addObject(ast,randX,868);
                int locX = rand.nextInt(808)+100;
                int locY = rand.nextInt(528)+100;
                ast.turnTowards(locX,locY);
            }
            else
            {
                int randY = rand.nextInt(768);
                int type = rand.nextInt(2);
                Asteroids ast;
                if(type == 0)
                {
                    ast = new SmallAsteroid();
                }
                else if(type == 1)
                {
                    ast = new LargeAsteroids();
                }
                else ast = new LargeAsteroids();
                addObject(ast,-100,randY);
                int locX = rand.nextInt(808)+100;
                int locY = rand.nextInt(528)+100;
                ast.turnTowards(locX,locY);
            }
            asteroidTimerStart = currentTime;
        }
        List<Actor> intersecting = spaceship.getIntersection();

        for(Actor a : intersecting)
        {
            if((a instanceof Laser || a instanceof Asteroids) && !wasHit) {
                gameOver = energy.remove(this);
                startCooldown = currentTime;
                wasHit = true;
                control.hit(true);
            }
            if(currentTime-startCooldown>=damageCooldown && wasHit)
            {
                wasHit = false;
                control.hit(false);
            }

        }

        for(int i = 0;i<objects.size();i++)
        {
            Actor a = objects.get(i);
            if(a instanceof LargeAsteroids)
            {
                List<Actor> temp = ((LargeAsteroids) a).getIntersection();
                for(Actor b : temp)
                {
                    if(b instanceof SpaceshipActor)
                    {
                        int j = a.getX();
                        int k = a.getY();
                        removeObject(a);
                        SmallAsteroid tempA = new SmallAsteroid();
                        SmallAsteroid tempB = new SmallAsteroid();
                        SmallAsteroid tempC = new SmallAsteroid();
                        addObject(tempA,j,k);
                        addObject(tempB,j,k);
                        addObject(tempC,j,k);
                        Random r = new Random(System.currentTimeMillis()+49);
                        int tempAX = r.nextInt(1024);
                        int tempAY = r.nextInt(768);
                        int tempBX = r.nextInt(1024);
                        int tempBY = r.nextInt(768);
                        int tempCX = r.nextInt(1024);
                        int tempCY = r.nextInt(768);
                        tempA.turnTowards(tempAX,tempAY);
                        tempB.turnTowards(tempBX,tempBY);
                        tempC.turnTowards(tempCX,tempCY);
                    }
                    else if(b instanceof Laser)
                    {
                        removeObject(b);
                        int j = a.getX();
                        int k = a.getY();
                        removeObject(a);
                        SmallAsteroid tempA = new SmallAsteroid();
                        SmallAsteroid tempB = new SmallAsteroid();
                        SmallAsteroid tempC = new SmallAsteroid();
                        addObject(tempA,j,k);
                        addObject(tempB,j,k);
                        addObject(tempC,j,k);
                        Random r = new Random(System.currentTimeMillis()+509);
                        int tempAX = r.nextInt(1024);
                        int tempAY = r.nextInt(768);
                        int tempBX = r.nextInt(1024);
                        int tempBY = r.nextInt(768);
                        int tempCX = r.nextInt(1024);
                        int tempCY = r.nextInt(768);
                        tempA.turnTowards(tempAX,tempAY);
                        tempB.turnTowards(tempBX,tempBY);
                        tempC.turnTowards(tempCX,tempCY);
                    }
                    else if(b instanceof Asteroids)
                    {
                        Random r = new Random(System.currentTimeMillis()+977465);
                        int tempAX = r.nextInt(1024);
                        int tempAY = r.nextInt(768);
                        int tempBX = r.nextInt(1024);
                        int tempBY = r.nextInt(768);
                        a.turnTowards(tempAX,tempAY);
                        b.turnTowards(tempBX,tempBY);
                    }
                }
            }
            if(a instanceof SmallAsteroid)
            {
                List<Actor> temp = ((SmallAsteroid) a).getIntersection();
                for(Actor b : temp)
                {
                    if(b instanceof Laser)
                    {
                        removeObject(b);
                        removeObject(a);
                    }
                    else if(b instanceof Asteroids)
                    {
                        Random r = new Random(System.currentTimeMillis()+977465);
                        int tempAX = r.nextInt(1024);
                        int tempAY = r.nextInt(768);
                        int tempBX = r.nextInt(1024);
                        int tempBY = r.nextInt(768);
                        a.turnTowards(tempAX,tempAY);
                        b.turnTowards(tempBX,tempBY);
                    }
                }
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
