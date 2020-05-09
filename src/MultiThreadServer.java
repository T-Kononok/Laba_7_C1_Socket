import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MultiThreadServer {

    private static ExecutorService executeIt = Executors.newFixedThreadPool(2);

    private MultiThreadServer() {
        try (ServerSocket server = new ServerSocket(3345)) {

            while (!server.isClosed()) {

                Socket client = server.accept();
                addServer(client);
                System.out.println("Connection accepted " + client.getPort());
            }
            executeIt.shutdown();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        MultiThreadServer server = new MultiThreadServer();
    }

    private void addServer(Socket client) {
        executeIt.execute(new MonoThreadClientHandler(client, this));
    }

}
