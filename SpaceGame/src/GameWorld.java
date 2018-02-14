import javafx.scene.input.KeyCode;
import mayflower.Mayflower;
import mayflower.World;
import mayflower.*;

public class GameWorld extends World
{
    private EnergyBar bar;
    private boolean gameOver;
    public GameWorld()
    {
        bar = new EnergyBar(this);
        bar.addEnergyBar();
    }
    @Override
    public void act()
    {
        if(Mayflower.wasKeyDown(Keyboard.KEY_SPACE))
        {
            gameOver = bar.remove();
        }
        if(gameOver)
        {
            World startingWorld = new StartMenu();
            Mayflower.setWorld(startingWorld);
        }
    }
}
