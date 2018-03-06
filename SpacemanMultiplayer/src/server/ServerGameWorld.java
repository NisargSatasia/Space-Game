package server;

import javafx.scene.input.KeyCode;
import mayflower.Mayflower;
import mayflower.World;
import mayflower.*;
import mayflower.net.Server;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ServerGameWorld extends World
{
    private ArrayList<Actor> energyBar;
    private ServerEnergyBar energy;
    private boolean gameOver;
    private ServerCollectable test = new ServerCollectable();
    private ServerSpaceshipActor spaceship = new ServerSpaceshipActor();
    private ServerSpaceshipController control;
    private ServerSmallAsteroid smallAsteroid = new ServerSmallAsteroid();
    private ServerLargeAsteroids largeAsteroids = new ServerLargeAsteroids();

    private ServerCrosshair crosshair;
    private ServerWeaponsActor weapons;
    private ServerWeaponsController controlWeapons;

    private long damageCooldown = 1000;
    private long startCooldown;
    private long currentTime = System.currentTimeMillis();
    private boolean wasHit;

    private Random rand = new Random(System.currentTimeMillis()+32);
    private long asteroidTimer = 2000;
    private long asteroidTimerStart;

    public ServerGameWorld(Server server)
    {

        asteroidTimerStart = currentTime;
        setBackground("img/background.jpg");

        setPaintOrder(ServerAsteroids.class);

    }
    public void addEnergyBar(ServerEnergyBar s)
    {
        energy = s;
        energyBar = energy.getEnergyBar();
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
    public void addWeapons(ServerCrosshair s,ServerWeaponsActor d)
    {
        crosshair = s;
        addObject(crosshair,0,0);
        weapons = d;
        addObject(weapons,500,350);
        controlWeapons = new ServerWeaponsController(weapons,crosshair,control);
    }
    public void addClient(ServerSpaceshipActor s)
    {
        addObject(spaceship,500,350);
        control = new ServerSpaceshipController(spaceship);
    }
    @Override
    public void act()
    {
        try {
            // make the actors bound.
            if (control.getPosX() >= (1008 - 32) || control.getPosX() <= 0 + 32 || control.getPosY() >= 728 + 32 || control.getPosY() <= 0 + 32) {
                control.setPos();
            }
            //System.out.println(control.getPosX() + "        " + control.getPosY());


            currentTime = System.currentTimeMillis();
            List<Actor> objects = this.getObjects();
        /*if(gameOver)
        {
            World g = new GameOver();
            Mayflower.setWorld(g);
        }*/
            if (Mayflower.isKeyDown(Keyboard.KEY_A)) {
                control.rotateLeft();
            }
            if (Mayflower.isKeyDown(Keyboard.KEY_D)) {
                control.rotateRight();
            }
            if (Mayflower.isKeyDown(Keyboard.KEY_W)) {
                control.increaseThrust();
            }
            if (Mayflower.isKeyDown(Keyboard.KEY_S)) {
                control.decreaseThrust();
            }
            if (currentTime - asteroidTimerStart >= asteroidTimer) {
                int a = rand.nextInt(4);
                if (a == 0) {
                    int randX = rand.nextInt(1024);
                    int type = rand.nextInt(2);
                    ServerAsteroids ast;
                    if (type == 0) {
                        ast = new ServerSmallAsteroid();
                    } else if (type == 1) {
                        ast = new ServerLargeAsteroids();
                    } else ast = new ServerLargeAsteroids();
                    addObject(ast, randX, -100);
                    int locX = rand.nextInt(808) + 100;
                    int locY = rand.nextInt(528) + 100;
                    ast.turnTowards(locX, locY);
                } else if (a == 1) {
                    int randY = rand.nextInt(768);
                    int type = rand.nextInt(2);
                    ServerAsteroids ast;
                    if (type == 0) {
                        ast = new ServerSmallAsteroid();
                    } else if (type == 1) {
                        ast = new ServerLargeAsteroids();
                    } else ast = new ServerLargeAsteroids();
                    addObject(ast, 1124, randY);
                    int locX = rand.nextInt(808) + 100;
                    int locY = rand.nextInt(528) + 100;
                    ast.turnTowards(locX, locY);
                } else if (a == 2) {
                    int randX = rand.nextInt(1024);
                    int type = rand.nextInt(2);
                    ServerAsteroids ast;
                    if (type == 0) {
                        ast = new ServerSmallAsteroid();
                    } else if (type == 1) {
                        ast = new ServerLargeAsteroids();
                    } else ast = new ServerLargeAsteroids();
                    addObject(ast, randX, 868);
                    int locX = rand.nextInt(808) + 100;
                    int locY = rand.nextInt(528) + 100;
                    ast.turnTowards(locX, locY);
                } else {
                    int randY = rand.nextInt(768);
                    int type = rand.nextInt(2);
                    ServerAsteroids ast;
                    if (type == 0) {
                        ast = new ServerSmallAsteroid();
                    } else if (type == 1) {
                        ast = new ServerLargeAsteroids();
                    } else ast = new ServerLargeAsteroids();
                    addObject(ast, -100, randY);
                    int locX = rand.nextInt(808) + 100;
                    int locY = rand.nextInt(528) + 100;
                    ast.turnTowards(locX, locY);
                }
                asteroidTimerStart = currentTime;
            }
            List<Actor> intersecting = spaceship.getIntersection();

            for (Actor a : intersecting) {
                if ((a instanceof ServerLaser || a instanceof ServerAsteroids) && !wasHit) {


                    gameOver = energy.remove(this);


                    System.out.println("No client");

                    startCooldown = currentTime;
                    wasHit = true;
                    control.hit(true);
                }
                if (currentTime - startCooldown >= damageCooldown && wasHit) {
                    wasHit = false;
                    control.hit(false);
                }

            }

            for (int i = 0; i < objects.size(); i++) {
                Actor a = objects.get(i);
                if (a instanceof ServerLargeAsteroids) {
                    List<Actor> temp = ((ServerLargeAsteroids) a).getIntersection();
                    for (Actor b : temp) {
                        if (b instanceof ServerSpaceshipActor) {
                            int j = a.getX();
                            int k = a.getY();
                            removeObject(a);
                            ServerSmallAsteroid tempA = new ServerSmallAsteroid();
                            ServerSmallAsteroid tempB = new ServerSmallAsteroid();
                            ServerSmallAsteroid tempC = new ServerSmallAsteroid();
                            addObject(tempA, j, k);
                            addObject(tempB, j, k);
                            addObject(tempC, j, k);
                            Random r = new Random(System.currentTimeMillis() + 49);
                            int tempAX = r.nextInt(1024);
                            int tempAY = r.nextInt(768);
                            int tempBX = r.nextInt(1024);
                            int tempBY = r.nextInt(768);
                            int tempCX = r.nextInt(1024);
                            int tempCY = r.nextInt(768);
                            tempA.turnTowards(tempAX, tempAY);
                            tempB.turnTowards(tempBX, tempBY);
                            tempC.turnTowards(tempCX, tempCY);
                        } else if (b instanceof ServerLaser) {
                            removeObject(b);
                            int j = a.getX();
                            int k = a.getY();
                            removeObject(a);
                            ServerSmallAsteroid tempA = new ServerSmallAsteroid();
                            ServerSmallAsteroid tempB = new ServerSmallAsteroid();
                            ServerSmallAsteroid tempC = new ServerSmallAsteroid();
                            addObject(tempA, j, k);
                            addObject(tempB, j, k);
                            addObject(tempC, j, k);
                            Random r = new Random(System.currentTimeMillis() + 509);
                            int tempAX = r.nextInt(1024);
                            int tempAY = r.nextInt(768);
                            int tempBX = r.nextInt(1024);
                            int tempBY = r.nextInt(768);
                            int tempCX = r.nextInt(1024);
                            int tempCY = r.nextInt(768);
                            tempA.turnTowards(tempAX, tempAY);
                            tempB.turnTowards(tempBX, tempBY);
                            tempC.turnTowards(tempCX, tempCY);
                        } else if (b instanceof ServerAsteroids) {
                            Random r = new Random(System.currentTimeMillis() + 977465);
                            int tempAX = r.nextInt(1024);
                            int tempAY = r.nextInt(768);
                            int tempBX = r.nextInt(1024);
                            int tempBY = r.nextInt(768);
                            a.turnTowards(tempAX, tempAY);
                            b.turnTowards(tempBX, tempBY);
                        }
                    }
                }
                if (a instanceof ServerSmallAsteroid) {
                    List<Actor> temp = ((ServerSmallAsteroid) a).getIntersection();
                    for (Actor b : temp) {
                        if (b instanceof ServerLaser) {
                            removeObject(b);
                            removeObject(a);
                        } else if (b instanceof ServerAsteroids) {
                            Random r = new Random(System.currentTimeMillis() + 977465);
                            int tempAX = r.nextInt(1024);
                            int tempAY = r.nextInt(768);
                            int tempBX = r.nextInt(1024);
                            int tempBY = r.nextInt(768);
                            a.turnTowards(tempAX, tempAY);
                            b.turnTowards(tempBX, tempBY);
                        }
                    }
                }
            }
            spaceship.move(control.getThrust());
            controlWeapons.resetRotation();
            controlWeapons.updateCrosshairPos();
            controlWeapons.updateGunPos();
            controlWeapons.updateGunRotation();
            if (Mayflower.mouseClicked(crosshair)) {
                ServerLaser l = new ServerLaser();
                addObject(l, controlWeapons.getXPos() + 16, controlWeapons.getYPos() + 24);
                l.setRotation(controlWeapons.currentAngle());
                l.move(64);
            }
        }
        catch(NullPointerException e)
        {
            System.out.println("No client");
        }
    }
}
