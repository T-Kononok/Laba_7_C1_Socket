import javax.swing.*;
import javax.swing.plaf.BorderUIResource;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.awt.event.*;
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

        ChatUp chatUp = new ChatUp();
        cardsUpPanel.add(chatUp.getChatUpPanel(), "chat");

//        Down
        LoginDown loginDown = new LoginDown(this);
        cardsDownPanel.add(loginDown.getLoginFonBox(), "login");

        MessagesDown messagesDown = new MessagesDown();
        cardsDownPanel.add(messagesDown.getMessagesDownPanel(), "messages");

        ChatDown chatDown = new ChatDown();
        cardsDownPanel.add(chatDown.getChatDownPanel(), "chat");

        setCardsUp("login");
        setCardsDown("login");
        menuVK.setLogin();
        Box contentBoxV = Box.createVerticalBox();
        contentBoxV.add(menuVK.getMenuPanel());
        contentBoxV.add(cardsUpPanel);
        contentBoxV.add(cardsDownPanel);
        getContentPane().add(contentBoxV, BorderLayout.CENTER);

    }

    void setCardsUp(String card) {
        cardsUp.show(cardsUpPanel,card);
        menuVK.setUser(user);
        menuVK.setAlogin();
    }
    void setCardsDown(String card) {
        cardsDown.show(cardsDownPanel,card);
        menuVK.setAlogin();
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
