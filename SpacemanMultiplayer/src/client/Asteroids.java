package client;

import mayflower.*;
public class Asteroids extends GameActor{

    public Asteroids(String img, int x, int y, int r) {
        super(img, x, y, r);
    }

    @Override
    public void act() {
        move(10);
    }
}
