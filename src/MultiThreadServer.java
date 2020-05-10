import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
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

    synchronized void createFile(String fileName) throws IOException {
        System.out.println("MultiServer->createNewFile->" + fileName);
        File f = new File(fileName);
        if(!f.createNewFile())
            System.out.println("Не удалось создать файл");
    }

    synchronized void writeToFile(String fileName, String text) throws IOException {
        System.out.println("MultiServer->writeToFile->" + fileName + "->" + text);
        text = text.replace('$','\n');
        System.out.println("MultiServer->text->"+text);
        FileWriter writer = new FileWriter(fileName, true);
        writer.write(text);
        writer.flush();
    }

    synchronized String readFile(String fileName) throws FileNotFoundException {
        System.out.println("MultiServer->readFile->" + fileName);
        Scanner scanner = new Scanner(new File(fileName));
        StringBuilder text = new StringBuilder();
        while (scanner.hasNextLine())
            text.append(scanner.nextLine()).append("$");
        System.out.println("MultiServer->text->"+text.toString());
        return text.toString();
    }

    public static void main(String[] args) {
        MultiThreadServer server = new MultiThreadServer();
    }

    private void addServer(Socket client) {
        executeIt.execute(new MonoThreadClientHandler(client, this));
    }

}
