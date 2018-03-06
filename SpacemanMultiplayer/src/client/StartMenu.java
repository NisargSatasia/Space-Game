package client;

import mayflower.*;

import java.util.HashMap;
import java.util.Map;

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
            String ip = Mayflower.ask("Connect to what IP address?");
            if("".equals(ip))
                ip = "localhost";

            GameClient client = new GameClient(ip);
            InputManager im = new InputManager(client);

            Map<Integer, String> keys = new HashMap<Integer, String>();
            keys.put(Keyboard.KEY_W, "up");
            keys.put(Keyboard.KEY_A, "left");
            keys.put(Keyboard.KEY_S, "down");
            keys.put(Keyboard.KEY_D, "right");
            im.setKeyMap(keys);
            GameWorld world = new GameWorld(im);
            client.setWorld(world);
            Mayflower.setWorld(world);
        }
    }
}
