import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Config {
    private int port;

    public Config(String fileName) {
        File file = new File(fileName);
        try {
            Scanner scanner = new Scanner(file);
            if (scanner.hasNext()) {
                // Reads port number
                port = Integer.parseInt(scanner.next());
            }
        } catch (FileNotFoundException e) {
            // Default port number on error
            port = 23;
        }
    }

    public int getPort() {
        return port;
    }
}
