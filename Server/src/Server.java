import java.io.IOException;
import java.net.ServerSocket;
import java.util.concurrent.ConcurrentHashMap;

public class Server {


    public static void main(String[] args) {
        Config config = new Config("Server/res/config.yaml");
        int threadCount = 0;

        ConcurrentHashMap<String, ServerThread> connectedClients = new ConcurrentHashMap<>();

        System.out.println("Server started on port " + config.getPort());

        // Attempt to create a server socket for server
        try (ServerSocket serverSocket = new ServerSocket(config.getPort())) {
            while (true) {
                // Create new thread to manage client, pass it socket to client and list of online clients
                // Check if client contains correct info and client userID is unique
                threadCount++;
                ServerThread serverThread = new ServerThread(serverSocket.accept(), threadCount);

                // Branch off thread to deal with new client
                (new Thread(serverThread)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("<ERROR> Could not initiate server.");
            System.exit(-1);
        }
    }
}
