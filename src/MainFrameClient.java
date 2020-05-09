import javax.swing.*;
import javax.swing.plaf.BorderUIResource;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.text.ParseException;

public class MainFrameClient extends JFrame {

    private static final int FRAME_WIDTH = 565;
    private static final int FRAME_HEIGHT = 565;
    private static final Color WHITEVK = new Color(237,238,240);

    private CardLayout cardsUp = new CardLayout();
    private CardLayout cardsDown = new CardLayout();
    private JPanel cardsUpPanel = new JPanel(cardsUp);
    private JPanel cardsDownPanel = new JPanel(cardsDown);
    private User user = new User();
    private MenuVK menuVK = new MenuVK(user);
    private MessagesDown messagesDown = new MessagesDown(user,this);
    private ChatDown chatDown = new ChatDown();
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

        MessagesUp messagesUp = new MessagesUp();
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
    }

    void setUser(User user) {
        this.user = user;
    }

    User getUser() {
        return user;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            final MainFrameClient frame = new MainFrameClient();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }
}
