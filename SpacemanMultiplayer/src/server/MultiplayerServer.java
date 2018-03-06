package server;

import mayflower.net.Server;

public class MultiplayerServer extends Server
{
    private ServerGame lobby;

    public MultiplayerServer()
    {
        super(1234);

        lobby = new ServerGame(this);

        System.out.println("Server started.");
    }

    @Override
    public void process(int i, String s)
    {
        lobby.process(i, s);
    }

    @Override
    public void onJoin(int i)
    {
        System.out.println("Joined: " + i);
        lobby.join(i);
    }

    @Override
    public void onExit(int i) {
        lobby.leave(i);
        System.out.println("Left: " + i);

    }
}
