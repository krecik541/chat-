package org.example;
import java.io.*;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;

class ClientHandler implements Runnable
{
    private final Socket socket;
    private static final Logger logger = Logger.getLogger(Server.class.getName());

    public ClientHandler(Socket socket)
    {
        this.socket = socket;
    }

    @Override
    public void run()
    {
        try
        {
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
            out.writeObject("ready");

            int n = (int) in.readObject();
            logger.log(Level.INFO, "User message " + n);

            out.writeObject("ready for messages");

            for(int i=1; i<=n; i++)
            {
                Message message = (Message) in.readObject();
                logger.log(Level.INFO, message.getContent());
                //System.out.println(message.getContent());
            }

            out.writeObject("finished");
            in.close();
            out.close();
            socket.close();
        }
        catch (ClassNotFoundException | IOException e)
        {
            logger.log(Level.WARNING, "Warning: " + e);
            //System.out.println(e);
        }
    }
}