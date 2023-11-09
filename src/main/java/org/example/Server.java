package org.example;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {


    public Server() throws Exception {

        ServerSocket serverSocket = new ServerSocket(8080); // Creates a port opening on 8080 so that the server can listen for incoming connections
        System.out.println("Listening for connections on port 8080 ...."); // Prints out a message to the console to show that the server is listening for connections

        // Creating a blocking method that waits for a connection from the client
        Socket socket = serverSocket.accept(); // Stops and waits for an incoming connection before continuing. If connected: server and client are now connected
        System.out.println("Client connected with ip: " + socket.getInetAddress()); // Prints out a message to the console to show that a connection has been made


        // Creating a place to store the data that is being sent from the client - I/O buffer
        BufferedReader in_socket = new BufferedReader(new InputStreamReader(socket.getInputStream())); // Creates a buffer that stores the data that is being sent from the client
        PrintWriter out_socket = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true); // Creates a buffer that stores the data that is being sent to the client
        // The true arguemnt is for autoflushing the buffer meaning because if we dont have it, we have to manually flush the buffer
        // The need for flush is because the buffer is a memory location and the data is not sent until the buffer is full. So if we dont have autoflush, we have to manually flush the buffer
        // This is true both for receiving and sending data

        out_socket.println("Hello client, you are connected to the server"); // Sends a message to the client
        String message = in_socket.readLine(); // Reads the message that is being sent from the client
        System.out.println("Client says: " + message); // Prints out the message that is being sent from the client

        // Once the communication is over, close the socket
        socket.close(); // Closes the socket
        System.out.println("Socket closed"); // Prints out a message to the console to show that the socket has been closed

    }


    public static void main(String[] args) {

        try {
            new Server(); // Creates a new instance of the Main class

        } catch (Exception e) { // Catches any exceptions that may occur
            e.printStackTrace();
        }


    }
}