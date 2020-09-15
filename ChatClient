import java.io.*;
import java.net.*;
import java.util.concurrent.TimeUnit;

public class ChatClient {
    private Socket client; // client Socket
    String address; // the address to connect top
    int port; // the port to be used to connect to the server

    public ChatClient(String ranAddress, int ranPort) {
        this.address = ranAddress; // telling that both the address and ranAddres are the same
        this.port = ranPort; // telling that both the port and ranPort are the same
        try {
            client = new Socket(address, port); // assigning the socket the address and the port
        } catch (IOException ignored) {
        }
    }

    PrintWriter serverOut = null;
    BufferedReader userIn;
    String userInput;

    public void go() { // the main place where everything is done
        try {
            while (true) {

                if (client != null) { // if the client is connected then go forward
                    userIn = new BufferedReader(new InputStreamReader(System.in));
                    serverOut = new PrintWriter(client.getOutputStream(), true);
                    ClientReciever threadServer = new ClientReciever(client);
                    System.out.println("Connected to server");
                    System.out.println("To use the Chat read this");
                    System.out.println("Run the ChatClient multiple times for multiple users to join the server");
                    System.out.println("For the client to exit the server just enter \"-\"");
                    System.out.println();

                    threadServer.start(); // starts the serverIn reading thread for sending and receiving the messages
                    // simultaneously

                    while (!client.isClosed()) { // check if the client is closed or not
                        userInput = userIn.readLine(); // reads the user input
                        if (userInput != null) { // checks if the user input is there or not
                            if (userInput.equals("-")) { // if user enters "-" then the client exits the server
                                System.out.println("Client Closed");
                                break;
                            }
                            serverOut.println(userInput); // send the userInput to the server
                        }
                    }
                    break;

                } else {
                    System.err.println("Un-able to connect to Server");
                    try {
                        TimeUnit.SECONDS.sleep(1); // time delay of 1 second
                        client = new Socket(address, port); // trying to connect to the server again and again
                    } catch (IOException ignored) {
                    } catch (InterruptedException e) {
                    }
                }
            }
        } catch (IOException e) {
        }
    }

    public static void main(String[] args) {
        String adrs = "localhost"; // default address
        int port = 14001; // default port

        try {
            if (args[0].equals("-cca")) { /* if user enters "-cca" then the args changes the default address to the address entered */
                adrs = args[1];
            } else if (args[0].equals("-ccp")) { /* if user enters "-ccp" then the args changes the default port to the port entered */
                try {
                    port = Integer.parseInt(args[1]);
                } catch (Exception e) {
                }
            } else if (args[0].equals("-cca") && args[2].equals("-ccp")) { /* if user enters "-cca" and "-ccp" then the args changes the default address and the default port to the address and the port entered respectively */
                adrs = args[1];
                try {
                    port = Integer.parseInt(args[3]);
                } catch (Exception e) {
                }
            } else if (args[0].equals("-ccp") && args[2].equals("-cca")) { /* if user enters "-ccp" and "-cca" then the args changes the default port and the default address to the port and the address entered respectively */
                try {
                    port = Integer.parseInt(args[1]);
                } catch (Exception e) {
                }
                adrs = args[3];
            }
        } catch (Exception e) {
        }

        new ChatClient(adrs, port).go();
    }
}
