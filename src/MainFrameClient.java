import javax.swing.*;
import javax.swing.plaf.BorderUIResource;
import java.awt.*;
import java.awt.event.*;

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
        profileButton.setFocusPainted(false);
        profileButton.setBackground(BLUEVK);
        profileButton.addMouseListener(new ButtonMouseListener());
        menuPanel.add(profileButton);
        profileButton.setVisible(false);
        
//        Up
        JPanel loginUpPanel = new JPanel();
        loginUpPanel.setBackground(WHITEVK);
        cardsUpPanel.add(loginUpPanel, "login");

        JPanel profileUpPanel = new JPanel();
        cardsUpPanel.add(profileUpPanel, "profile");

        JPanel searchUpPanel = new JPanel();
        cardsUpPanel.add(searchUpPanel, "search");
        
        JPanel chatUpPanel = new JPanel();
        cardsUpPanel.add(chatUpPanel, "chat");

//        Down
        JPanel loginDownPanel = new JPanel();
        loginDownPanel.setBackground(WHITEVK);
        JTextField kostylNameLoginField = new JTextField("",1);
        kostylNameLoginField.setBorder(BorderFactory.createEmptyBorder());
        kostylNameLoginField.setBackground(WHITEVK);
        kostylNameLoginField.setCaretColor(WHITEVK);
        Box loginVBox = Box.createVerticalBox();
        JLabel vhodLabel = new JLabel("Вход пародии ВКонтакте");
        vhodLabel.setFont(new Font("Tahoma",Font.PLAIN,20));
        JTextField nameLoginField = new JTextField("Имя",25);
        nameLoginField.addFocusListener(new TextFieldFocusAdapter("Имя"));
        JTextField surnameLoginField = new JTextField("Фамилия", 25);
        surnameLoginField.addFocusListener(new TextFieldFocusAdapter("Фамилия"));
        JButton vhodButton = new JButton("Войти");
        loginVBox.add(kostylNameLoginField);
        loginVBox.add(vhodLabel);
        loginVBox.add(nameLoginField);
        loginVBox.add(surnameLoginField);
        loginVBox.add(vhodButton);
        loginDownPanel.add(loginVBox);
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

    public static class TextFieldFocusAdapter extends FocusAdapter {
        private String text;

        TextFieldFocusAdapter(String text) {
            this.text = text;
        }

        public void focusGained(FocusEvent e) {
            JTextField field = (JTextField) e.getSource();
            if (field.getText().equals(text)) {
                field.setText("");
            }
        }
        public void focusLost(FocusEvent e) {
            JTextField field = (JTextField) e.getSource();
            if (field.getText().isEmpty()) {
                field.setText(text);
            }
        }
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
