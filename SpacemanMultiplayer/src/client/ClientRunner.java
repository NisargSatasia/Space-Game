package client;

import mayflower.Mayflower;
import mayflower.*;
import mayflower.net.Client;
public class ClientRunner extends Mayflower
{

    private ClientRunner()
    {
        super("Spaceman", 1024, 768);
    }

    @Override
    public void init()
    {

        Mayflower.setFullScreen(false);
        World startingWorld = new StartMenu();
        Mayflower.setWorld(startingWorld);
    }

    public static void main(String[] args)
    {
        new ClientRunner();
    }
}
