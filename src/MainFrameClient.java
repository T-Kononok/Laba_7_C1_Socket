import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class MainFrameClient extends JFrame {

    private static final int FRAME_WIDTH = 565;
    private static final int FRAME_HEIGHT = 565;
    private static final Color WHITEVK = new Color(237,238,240);
    private static BufferedReader in;
    private static PrintWriter out;

    private CardLayout cardsUp = new CardLayout();
    private CardLayout cardsDown = new CardLayout();
    private JPanel cardsUpPanel = new JPanel(cardsUp);
    private JPanel cardsDownPanel = new JPanel(cardsDown);
    private User user = new User(this);
    private MenuVK menuVK = new MenuVK(user);
    private MessagesDown messagesDown = new MessagesDown(user,this);
    private MessagesUp messagesUp = new MessagesUp(this);
    private ChatDown chatDown = new ChatDown(this);
    private ChatUp chatUp = new ChatUp(this);


    private MainFrameClient() throws InterruptedException {

        setResizable(false);
        setSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
        Toolkit kit = Toolkit.getDefaultToolkit();
        setLocation((kit.getScreenSize().width - getWidth()) / 2,
                    (kit.getScreenSize().height - getHeight()) / 2);

//        Up
        JPanel loginUpPanel = new JPanel();
        loginUpPanel.setBackground(WHITEVK);
        cardsUpPanel.add(loginUpPanel, "login");

        cardsUpPanel.add(messagesUp.getMessagesUpPanel(), "messages");

        cardsUpPanel.add(chatUp.getChatUpPanel(), "chat");

//        Down
        LoginDown loginDown = new LoginDown(this);
        cardsDownPanel.add(loginDown.getLoginFonBox(), "login");

        cardsDownPanel.add(messagesDown.getMessagesDownPanel(), "messages");

        cardsDownPanel.add(chatDown.getChatDownPanel(), "chat");

        setCards("login");
        menuVK.setLogin();
        Box contentBoxV = Box.createVerticalBox();
        contentBoxV.add(menuVK.getMenuPanel());
        contentBoxV.add(cardsUpPanel);
        contentBoxV.add(cardsDownPanel);
        getContentPane().add(contentBoxV, BorderLayout.CENTER);

    }

    void setCards(String card) throws InterruptedException {
        cardsUp.show(cardsUpPanel,card);
        cardsDown.show(cardsDownPanel,card);
        menuVK.setUser(user);
        menuVK.setAlogin();
        messagesDown.setUser(user);
        messagesUp.setUser(user);
        if (card.equals("messages"))
            messagesDown.readChatsInData();
    }

    void setCardChat(String name) {
        cardsUp.show(cardsUpPanel,"chat");
        cardsDown.show(cardsDownPanel,"chat");
        chatDown.setUser(user);
        chatDown.setInterlocutor(name);
        chatUp.setInterlocutor(name);
        try {
            chatDown.readOneChatInData();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    void setUser(User user) {
        this.user = user;
    }

    void createFile(String fileName) {
        getOut().println("createFile//"+fileName);
    }

    void writeToFile(String fileName, String text) {
        getOut().println("writeToFile//"+fileName+"//"+text);
    }

    String readFile(String fileName) throws InterruptedException {
        getOut().println("readFile//"+fileName);
        Thread.sleep(20);
        Scanner scanner = new Scanner(getIn());
        StringBuilder text = new StringBuilder();
        while (scanner.hasNextLine())
            text.append(scanner.nextLine()).append("\n");
        return text.toString();
    }

    private static BufferedReader getIn() {
        return in;
    }

    private static PrintWriter getOut() {
        return out;
    }

    public static void main(String[] args) {
    SwingUtilities.invokeLater(() -> {

        final MainFrameClient frame;
        try {
            frame = new MainFrameClient();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    try {
        Socket socket = new Socket("127.0.0.1", 3345);
        in = new BufferedReader(
                new InputStreamReader(
                        socket.getInputStream()));
        out = new PrintWriter(new BufferedWriter(
                new OutputStreamWriter(
                        socket.getOutputStream())), true);
    } catch (IOException e) {
        e.printStackTrace();
    }
    });
}
}
