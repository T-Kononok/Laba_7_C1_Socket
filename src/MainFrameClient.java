import javax.swing.*;
import java.awt.*;

public class MainFrameClient extends JFrame {

    private static final String FRAME_TITLE = "Жалкая породия на нормальный мессенджер";
    private static final int FRAME_MINIMUM_WIDTH = 500;
    private static final int FRAME_MINIMUM_HEIGHT = 500;

    private CardLayout cardsUp = new CardLayout();
    private CardLayout cardsDown = new CardLayout();
    private JPanel cardsUpPanel = new JPanel(cardsUp);
    private JPanel cardsDownPanel = new JPanel(cardsDown);

    private MainFrameClient(){

        super(FRAME_TITLE);
        setMinimumSize(new Dimension(FRAME_MINIMUM_WIDTH, FRAME_MINIMUM_HEIGHT));
        Toolkit kit = Toolkit.getDefaultToolkit();
        setLocation((kit.getScreenSize().width - getWidth()) / 2,
                    (kit.getScreenSize().height - getHeight()) / 2);

//        Up
        JPanel loginUpPanel = new JPanel();
        cardsUpPanel.add(loginUpPanel, "login");
        JPanel searchUpPanel = new JPanel();
        cardsUpPanel.add(searchUpPanel, "search");
        JPanel chatUpPanel = new JPanel();
        cardsUpPanel.add(chatUpPanel, "chat");

//        Down
        JPanel loginDowmPanel = new JPanel();
        cardsDownPanel.add(loginDowmPanel, "login");
        JPanel settingDowmPanel = new JPanel();
        cardsDownPanel.add(settingDowmPanel, "setting");
        JPanel searchDownPanel = new JPanel();
        cardsDownPanel.add(searchDownPanel, "search");
        JPanel messagesDownPanel = new JPanel();
        cardsDownPanel.add(messagesDownPanel, "messages");
        JPanel chatDownPanel = new JPanel();
        cardsDownPanel.add(chatDownPanel, "chat");

//        JPanel menuPanel = new JPanel();
//        JButton settingButton = new JButton(new ImageIcon("D:/Джава/Laba_7_C1_Socket/settingIm"));
//        //settingButton.addActionListener(e -> sendMessage());
//        JButton settingButton = new JButton(new ImageIcon("D:/Джава/Laba_7_C1_Socket/settingIm"));
//        //settingButton.addActionListener(e -> sendMessage());

        getContentPane().add(cardsUpPanel, BorderLayout.SOUTH);
        getContentPane().add(cardsDownPanel, BorderLayout.NORTH);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            final MainFrameClient frame = new MainFrameClient();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }
}
