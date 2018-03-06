package client;

import mayflower.*;

import java.util.List;

public class SpaceshipActor extends GameActor
{
    private String imgA;
    private int a,b,c;

    public SpaceshipActor(String img, int x, int y, int r) {
        super(img, x, y, r);
    }

    public List<Actor> getIntersection()
    {
        return getIntersectingObjects(Actor.class);
    }
    @Override
    public void act()
    {


    }
}
