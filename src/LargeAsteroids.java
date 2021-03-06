import mayflower.*;

import java.util.List;

public class LargeAsteroids extends Asteroids{

    //private int dir;
    //private Timer timer;

    public LargeAsteroids()
    {
        setImage("img/LargeAsteroid.png");

    }
    public List<Actor> getIntersection()
    {
        return getIntersectingObjects(Actor.class);
    }
    public void setPos()
    {
        int x = this.getX();
        int y = this.getY();
        if(x >= 1008){
            this.setLocation(0, y);
        }else if(x <= 0){
            this.setLocation(1008, y);
        }else if(y >= 728){
            this.setLocation(x, 0);
        }else if(y <= 0){
            this.setLocation(x, 728);
        }
    }
    public void act()
    {
        if(this.getX()>= (1008-32) || this.getX()<=0+32 || this.getY()>= 728+32 || this.getY()<= 0+32){
            this.setPos();
        }
        move(5);
    }


}
