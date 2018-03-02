import mayflower.*;

public class StartMenu extends World {
    private startButton start;

    public StartMenu()
    {
        setBackground("img/background.jpg");
        start = new startButton();
        addObject(start,100,100);
    }
    @Override
    public void act()
    {
        if(Mayflower.mouseClicked(start))
        {
            GameWorld gameWorld = new GameWorld();
            Mayflower.setWorld(gameWorld);
        }
    }
}
