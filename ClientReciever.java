import java.io.*;
import java.net.*;

public class ClientReciever extends Thread {
    private Socket client;
    BufferedReader serverIn = null;

    public ClientReciever(Socket clientSocket) {
        this.client = clientSocket; // showing client and clientSocket are the same
    }

    @Override
    public void run() {
        while (true) {
            try {
                serverIn = new BufferedReader(new InputStreamReader(client.getInputStream()));
                String a = serverIn.readLine(); // reads what the server sends
                System.out.println(a); // prints out what the server sends
            } catch (IOException e) {
            }
        }
    }
}
