package org.example;

import java.io.*;
import java.net.*;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Client {
    private static final Logger logger = Logger.getLogger(Client.class.getName());
    public Client(String address, int port)
    {
        try
        {
            Socket socket  = new Socket(address, port);
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());

            String line = (String) in.readObject();
            logger.log(Level.INFO, "Server message:  " + line);
            //System.out.println(line);

            Scanner scanner = new Scanner(System.in);
            int n = scanner.nextInt();
            out.writeObject(n);

            line = (String) in.readObject();
            //System.out.println(line);
            logger.info("Server message:  " + line);

            for(int i=1; i<=n; i++)
            {
                Message message = new Message();
                message.setNumber(i);
                message.setContent("message " + i);
                out.writeObject(message);
            }

            line = (String) in.readObject();
            //System.out.println(line);
            logger.log(Level.INFO, "Server message:  " + line);
            out.close();
            socket.close();
        }
        catch(IOException | ClassNotFoundException i)
        {
            //System.out.println(i);
            logger.log(Level.WARNING, "Warning: " + i);
        }
    }

    public static void main(String[] args) {
        Client client = new Client("localhost", 5000);
    }
}


/*
public Client(String address, int port)
    {
        try
        {
            Socket socket  = new Socket(address, port);
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());

            System.out.println("Connected");

            Scanner scanner = new Scanner(System.in);
            String line = "";

            while(!line.equals("Over"))
            {
                    line = scanner.nextLine();
                    out.writeObject(line);
            }

            out.writeObject("Finnish");

            out.close();
            socket.close();
        }
        catch(IOException i)
        {
            System.out.println(i);
        }
    }

 */