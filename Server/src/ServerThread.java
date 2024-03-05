import java.io.*;
import java.net.Socket;
import java.util.concurrent.ConcurrentHashMap;

public class ServerThread implements Runnable {

    private final Socket socketToClient;
    private String userID;
    private PrintWriter outToClient;
    private BufferedReader inFromClient;

    private final int id;

    public ServerThread(Socket socket, int threadCount) {
        socketToClient = socket;
        id = threadCount;

        System.out.println("<SERVER> New client id " + id + " connected");
    }

    @Override
    public void run() {

        try {
            outToClient = new PrintWriter(socketToClient.getOutputStream(), true);
            inFromClient = new BufferedReader(new InputStreamReader(socketToClient.getInputStream()));
        } catch (IOException e) {
            System.out.println("<SERVER> Error running server thread " + id + ": " + e.getMessage());
        }

        try {
            while (true) {
                String line = inFromClient.readLine();
                System.out.println("<TELNET " + id + "> " + line);
                outToClient.println("<SERVER> Received message: " + line);

                if (line.equals("quit")) {
                    System.out.println("<SERVER> Quitting telnet");
                    System.exit(0);
                }
            }
        } catch (IOException e) {
            System.out.println("<SERVER> Error reading input from telnet thread id " + id + ": " + e.getMessage());
        }
    }

}
