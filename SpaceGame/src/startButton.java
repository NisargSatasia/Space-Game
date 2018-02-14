import mayflower.Actor;
import mayflower.Mayflower;

public class startButton extends Actor{

    public startButton()
    {
        setImage("img/startbutton.png");
    }
    GameWorld gameWorld = new GameWorld();
    public void act()
    {
        if(Mayflower.mouseClicked(this))
        {
            System.out.println("works");
            Mayflower.setWorld(gameWorld);
        }
    }

}
