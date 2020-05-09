import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class MainFrameClient extends JFrame {

    private static final int FRAME_WIDTH = 565;
    private static final int FRAME_HEIGHT = 565;
    private static final Color WHITEVK = new Color(237,238,240);

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

    private MainFrameClient(){

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

    void setCards(String card) {
        cardsUp.show(cardsUpPanel,card);
        cardsDown.show(cardsDownPanel,card);
        menuVK.setUser(user);
        menuVK.setAlogin();
        messagesDown.setUser(user);
        messagesUp.setUser(user);
        try {
            messagesDown.readChatsInData();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void setCardChat(String name) {
        cardsUp.show(cardsUpPanel,"chat");
        cardsDown.show(cardsDownPanel,"chat");
        chatDown.setUser(user);
        chatDown.setInterlocutor(name);
        chatUp.setInterlocutor(name);
        try {
            chatDown.readOneChatInData();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void setUser(User user) {
        this.user = user;
    }

    void createFile(String fileName) throws IOException {
        File f = new File(fileName);
        f.createNewFile();
    }

    void writeToFile(String fileName, String text, boolean append) throws IOException {
        FileWriter writer = new FileWriter(fileName, append);
        writer.write(text);
        writer.flush();
    }

    String readFile(String fileName) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(fileName));
        String text = "";
        while (scanner.hasNextLine())
            text += scanner.nextLine() + "\n";
        return text;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            final MainFrameClient frame = new MainFrameClient();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }
}
