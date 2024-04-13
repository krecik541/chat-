package org.example;
import java.net.*;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Server {
    private static final Logger logger = Logger.getLogger(Server.class.getName());
    public Server(int port)
    {

        try
        {
            ServerSocket server = new ServerSocket(port);
            logger.log(Level.INFO, "Server started");
            //System.out.println("Server started");
            logger.log(Level.INFO, "Waiting for client ...");
            //System.out.println("Waiting for client ...");

            while (true)
            {
                Socket socket = server.accept();
                logger.log(Level.INFO, "Client accepted " + socket);
                //System.out.println("Client accepted " + socket);

                Thread thread = new Thread(new ClientHandler(socket));
                thread.start();
            }
        }
        catch (IOException i)
        {
            logger.log(Level.WARNING, "Warning: " + i);
            //System.out.println(i);
        }
    }

    public static void main(String[] args)
    {
        Server server = new Server(5000);
    }
}