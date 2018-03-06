package client;

import mayflower.Mayflower;
import mayflower.World;

public class GameOver extends World {
    private GameOverActor a = new GameOverActor();
    public GameOver()
    {
        setBackground("img/background.jpg");
        addObject(a,0,0);
    }
    @Override
    public void act()
    {
        if(Mayflower.mouseClicked(a))
        {
            StartMenu s = new StartMenu();
            Mayflower.setWorld(s);
        }
    }
}
