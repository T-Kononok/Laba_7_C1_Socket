import javax.swing.*;
import javax.swing.plaf.BorderUIResource;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class MainFrameClient extends JFrame {

    private static final int FRAME_WIDTH = 565;
    private static final int FRAME_HEIGHT = 565;
    private static final Color BLUEVK = new Color(74,118,168);
    private static final Color WHITEVK = new Color(237,238,240);

    private CardLayout cardsUp = new CardLayout();
    private CardLayout cardsDown = new CardLayout();
    private JPanel menuPanel = new JPanel();
    private JPanel cardsUpPanel = new JPanel(cardsUp);
    private JPanel cardsDownPanel = new JPanel(cardsDown);

    private MainFrameClient(){

        setResizable(false);
        setSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
        Toolkit kit = Toolkit.getDefaultToolkit();
        setLocation((kit.getScreenSize().width - getWidth()) / 2,
                    (kit.getScreenSize().height - getHeight()) / 2);
        
//        Menu
        menuPanel.setLayout(new BoxLayout(menuPanel, BoxLayout.X_AXIS));
        menuPanel.setBackground(BLUEVK);
        JLabel titlLabel = new JLabel("Жалкая пародия на VK");
        titlLabel.setForeground(Color.WHITE);
        titlLabel.setFont(new Font("Tahoma",Font.BOLD,15));
        JLabel hashtagLabel = new JLabel("#лучшедома");
        hashtagLabel.setForeground(Color.WHITE);
        hashtagLabel.setFont(new Font("Tahoma",Font.BOLD,13));
        menuPanel.add(new JLabel(new ImageIcon("D:/Джава/Laba_7_C1_Socket/vkIm.png")));
        menuPanel.add(hashtagLabel);
        menuPanel.add(Box.createHorizontalGlue());
        menuPanel.add(titlLabel);
        menuPanel.add(Box.createHorizontalGlue());
        JLabel kostylLabel = new JLabel("#лучшедома");
        kostylLabel.setForeground(BLUEVK);
        kostylLabel.setFont(new Font("Tahoma",Font.BOLD,12));
        menuPanel.add(kostylLabel);
        JLabel kostylIm = new JLabel(new ImageIcon("D:/Джава/Laba_7_C1_Socket/kostyl.png"));
        menuPanel.add(kostylIm);
        JLabel profileButtonLabel = new JLabel("Анонимус");
        profileButtonLabel.setForeground(Color.WHITE);
        profileButtonLabel.setFont(new Font("Tahoma",Font.BOLD,13));
        JButton profileButton = new JButton();
        profileButton.add(profileButtonLabel);
        JLabel im = new JLabel(new ImageIcon("D:/Джава/Laba_7_C1_Socket/kostyl2.png"));
        im.setVisible(false);
        profileButton.add(im);
        profileButton.setBorderPainted(false);
        profileButton.setBackground(BLUEVK);
        profileButton.addMouseListener(new ButtonMouseListener());
        menuPanel.add(profileButton);
        
//        Up
        JPanel loginUpPanel = new JPanel();
        cardsUpPanel.add(loginUpPanel, "login");

        JPanel searchUpPanel = new JPanel();
        cardsUpPanel.add(searchUpPanel, "search");
        
        JPanel chatUpPanel = new JPanel();
        cardsUpPanel.add(chatUpPanel, "chat");

//        Down
        JPanel loginDownPanel = new JPanel();
        loginDownPanel.setBackground(WHITEVK);
        cardsDownPanel.add(loginDownPanel, "login");

        JPanel profileDownPanel = new JPanel();
        cardsDownPanel.add(profileDownPanel, "profile");

        JPanel searchDownPanel = new JPanel();
        cardsDownPanel.add(searchDownPanel, "search");

        JPanel messagesDownPanel = new JPanel();
        cardsDownPanel.add(messagesDownPanel, "messages");

        JPanel chatDownPanel = new JPanel();
        cardsDownPanel.add(chatDownPanel, "chat");

        cardsUp.show(cardsUpPanel,"login");
        cardsDown.show(cardsDownPanel,"login");

//        JPanel menuPanel = new JPanel();
//        JButton profileButton = new JButton(new ImageIcon("D:/Джава/Laba_7_C1_Socket/profileIm"));
//        //profileButton.addActionListener(e -> sendMessage());
//        JButton profileButton = new JButton(new ImageIcon("D:/Джава/Laba_7_C1_Socket/profileIm"));
//        //profileButton.addActionListener(e -> sendMessage());

        getContentPane().add(menuPanel, BorderLayout.NORTH);
        getContentPane().add(cardsUpPanel, BorderLayout.CENTER);
        getContentPane().add(cardsDownPanel, BorderLayout.SOUTH);
    }

    public static class ButtonMouseListener implements MouseListener {

        public void mouseClicked(MouseEvent e) { }

        public void mouseEntered(MouseEvent arg0) {
            JButton button = (JButton) arg0.getSource();
            button.setBackground(new Color(61,104,152));
        }

        public void mouseExited(MouseEvent arg0) {
            JButton button = (JButton) arg0.getSource();
            button.setBackground(BLUEVK);
        }

        public void mousePressed(MouseEvent e) { }

        public void mouseReleased(MouseEvent e) { }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            final MainFrameClient frame = new MainFrameClient();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }
}
