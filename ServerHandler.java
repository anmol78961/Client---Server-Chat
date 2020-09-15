import java.io.*;
import java.net.*;
import java.util.*;

public class ServerHandler extends Thread {
    private Socket clnt;
    private BufferedReader serverIn;
    private PrintWriter serverOut;
    private ArrayList<ServerHandler> clients; // an array list of clients

    public ServerHandler(Socket clientSocket, ArrayList<ServerHandler> clients) {
        this.clients = clients;
        try {
            this.clnt = clientSocket; // showing that the clnt and clientSocket are the same
            serverIn = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            serverOut = new PrintWriter(clientSocket.getOutputStream(), true);
        } catch (IOException ignored) {
        }
    }

    String userInput;

    @Override
    public void run() {
        try {
            while (true) {
                userInput = serverIn.readLine(); // reads the user input
                if (userInput.equals("-")) {
                    clnt.close(); // the client exits when the user enters "-"
                    break;
                } else {
                    send_to_all(userInput); // send the message what the user types to all the other users
                }
            }
        } catch (IOException ignored) {
        } finally {
            try {
                serverIn.close(); // closes the BofferedReaded when done
            } catch (IOException ignored) {
            }
        }
    }

    public void send_to_all(String message) {
        for (ServerHandler client : clients) {
            client.serverOut.println(message);
        }
    }
}
