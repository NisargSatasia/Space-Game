import mayflower.Actor;
import mayflower.Mayflower;

public class startButton extends Actor{

    public startButton()
    {
        setImage("img/startbutton.png");
    }
    public void act()
    {
        if(Mayflower.mouseClicked(this))
        {
            System.out.println("Always Grow Mode");
            GameWorld gameWorld = new GameWorld();
            Mayflower.setWorld(gameWorld);
        }

    }

}
