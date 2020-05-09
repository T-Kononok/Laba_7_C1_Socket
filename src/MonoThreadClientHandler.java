import java.io.*;
import java.net.Socket;

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
                    System.out.println("введено " + str);
                    if (str.equals("read")) {
                        System.out.println("getMes " + str);
                    }
                    else {
                        out.println("#");
                        System.out.println("setMes " + str);
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
