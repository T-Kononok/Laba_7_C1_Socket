import javax.swing.*;
import javax.swing.plaf.BorderUIResource;
import java.awt.*;

public class MainFrameClient extends JFrame {

    private static final int FRAME_WIDTH = 565;
    private static final int FRAME_HEIGHT = 565;
    private static final Color BLUEVK = new Color(74,118,168);
    private static final Color WHITEVK = new Color(237,238,240);

    private CardLayout cardsUp = new CardLayout();
    private CardLayout cardsDown = new CardLayout();
    private JPanel cardsUpPanel = new JPanel(cardsUp);
    private JPanel cardsDownPanel = new JPanel(cardsDown);

    private MainFrameClient(){

        setResizable(false);
        setSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
        Toolkit kit = Toolkit.getDefaultToolkit();
        setLocation((kit.getScreenSize().width - getWidth()) / 2,
                    (kit.getScreenSize().height - getHeight()) / 2);

//        Up
        JPanel loginUpPanel = new JPanel();
        loginUpPanel.setLayout(new BoxLayout(loginUpPanel, BoxLayout.X_AXIS));
        loginUpPanel.setBackground(BLUEVK);
        JLabel titlLabel = new JLabel("Жалкая пародия на VK");
        titlLabel.setForeground(Color.WHITE);
        titlLabel.setFont(new Font("Tahoma",Font.BOLD,15));
        JLabel hashtagLabel = new JLabel("#лучшедома");
        hashtagLabel.setForeground(Color.WHITE);
        hashtagLabel.setFont(new Font("Tahoma",Font.BOLD,12));
        loginUpPanel.add(new JLabel(new ImageIcon("D:/Джава/Laba_7_C1_Socket/vkIm.png")));
        loginUpPanel.add(hashtagLabel);
        loginUpPanel.add(Box.createHorizontalGlue());
        loginUpPanel.add(titlLabel);
        loginUpPanel.add(Box.createHorizontalGlue());
        JLabel kostylLabel = new JLabel("#лучшедома");
        kostylLabel.setForeground(BLUEVK);
        kostylLabel.setFont(new Font("Tahoma",Font.BOLD,12));
        loginUpPanel.add(kostylLabel);
        JLabel kostylIm = new JLabel(new ImageIcon("D:/Джава/Laba_7_C1_Socket/kostyl.png"));
        loginUpPanel.add(kostylIm);
        cardsUpPanel.add(loginUpPanel, "login");

        JPanel searchUpPanel = new JPanel();
        cardsUpPanel.add(searchUpPanel, "search");

        JPanel chatUpPanel = new JPanel();
        cardsUpPanel.add(chatUpPanel, "chat");

//        Down
        JPanel loginDownPanel = new JPanel();
        loginDownPanel.setBackground(WHITEVK);
        cardsDownPanel.add(loginDownPanel, "login");

        JPanel settingDownPanel = new JPanel();
        cardsDownPanel.add(settingDownPanel, "setting");

        JPanel searchDownPanel = new JPanel();
        cardsDownPanel.add(searchDownPanel, "search");

        JPanel messagesDownPanel = new JPanel();
        cardsDownPanel.add(messagesDownPanel, "messages");

        JPanel chatDownPanel = new JPanel();
        cardsDownPanel.add(chatDownPanel, "chat");
//        cardsDown.show(cardsDownPanel,"messages");

//        JPanel menuPanel = new JPanel();
//        JButton settingButton = new JButton(new ImageIcon("D:/Джава/Laba_7_C1_Socket/settingIm"));
//        //settingButton.addActionListener(e -> sendMessage());
//        JButton settingButton = new JButton(new ImageIcon("D:/Джава/Laba_7_C1_Socket/settingIm"));
//        //settingButton.addActionListener(e -> sendMessage());

        getContentPane().add(cardsUpPanel, BorderLayout.NORTH);
        getContentPane().add(cardsDownPanel, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            final MainFrameClient frame = new MainFrameClient();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }
}
