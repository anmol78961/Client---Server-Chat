import java.io.*;
import java.net.*;
import java.util.*;

public class ChatServer {
    public static int defaultPort = 14001; // assigning a default port
    private ServerSocket server;
    public ArrayList<ServerHandler> clientList = new ArrayList<>(); // an array list of all the clients

    public ChatServer(int port) {
        defaultPort = port; // showing that both the defaultPort and the port are the same
        try {
            server = new ServerSocket(port);
        } catch (IOException ignored) {
        }
    }

    public void start() {
        while (true) {
            try {
                System.out.println("Server listening");
                Socket client = server.accept(); // accepts the clients every time the user wants to join the server
                System.out.println("Server accepted connection on server port = " + server.getLocalPort() + " ; " + " ; " + "client port = " + client.getLocalPort() + " ; " + client.getPort());
                ServerHandler thread = new ServerHandler(client, clientList); // connecting the ServerHandler thread to the ChatServer
                thread.start();
                clientList.add(thread); // adding the clients to the array list clientList every time a new client joins the server
                int threadsJoined = Thread.activeCount() - 2; // -1 because the server is also considered as a thread
                System.out.println("Clients Joined = " + threadsJoined); // gives us the count of the number of clients joined the server
            } catch (IOException ignored) {
            }
        }
    }

    public static void main(String[] args) {
        try {
            if (args[0].equals("-csp")) { /* if user enters "-csp" then the args changes the default port to the port entered */
                defaultPort = Integer.parseInt(args[1]);
            }
        } catch (Exception e) {
        }
        while (true) {
            new ChatServer(defaultPort).start();
        }
    }
}
