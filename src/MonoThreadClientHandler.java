import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class MonoThreadClientHandler implements Runnable {

    private static Socket clientDialog;
    private static MultiThreadServer server;

    MonoThreadClientHandler(Socket client, MultiThreadServer server) {
        clientDialog = client;
        MonoThreadClientHandler.server = server;
    }

    public void run() {

        while (true) {
            try {
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(
                                clientDialog.getInputStream()));
                PrintWriter out = new PrintWriter(
                        new BufferedWriter(new OutputStreamWriter(
                                clientDialog.getOutputStream())), true);
                while (true) {
                    String str = in.readLine();
                    System.out.println("MonoServer->in.readLine->" + str);
                    String[] subStr = str.split("//");
                    switch (subStr[0]) {
                        case ("createFile"):
                            server.createFile(subStr[1]);
                            out.flush();
                            break;
                        case ("writeToFile"):
                            server.writeToFile(subStr[1], subStr[2]);
                            out.flush();
                            break;
                        case ("readFile"):
                            out.println(server.readFile(subStr[1]));
                            out.flush();
                            break;
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    clientDialog.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
