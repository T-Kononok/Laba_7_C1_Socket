import javax.swing.*;
import javax.swing.plaf.BorderUIResource;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.awt.event.*;
import java.text.ParseException;

public class MainFrameClient extends JFrame {

    private static final int FRAME_WIDTH = 565;
    private static final int FRAME_HEIGHT = 565;
    private static final Color BLUEVK = new Color(74,118,168);
    private static final Color WHITEVK = new Color(237,238,240);
    private static final Color GREYVK = new Color(153,145,146);

    private CardLayout cardsUp = new CardLayout();
    private CardLayout cardsDown = new CardLayout();
    private JPanel menuPanel = new JPanel();
    private JPanel cardsUpPanel = new JPanel(cardsUp);
    private JPanel cardsDownPanel = new JPanel(cardsDown);

    private MainFrameClient(){

//        setResizable(false);
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
        loginDownPanel.setBackground(Color.WHITE);
        JTextField kostylNameLoginField = new JTextField("",1);
        kostylNameLoginField.setBorder(BorderFactory.createEmptyBorder());
        kostylNameLoginField.setBackground(Color.WHITE);
        kostylNameLoginField.setCaretColor(Color.WHITE);
        JLabel vhodLabel = new JLabel("Вход пародии ВКонтакте");
        vhodLabel.setFont(new Font("Tahoma",Font.PLAIN,20));
        JTextField nameLoginField = new JTextField("Имя",10);
        nameLoginField.setFont(new Font("Tahoma",Font.PLAIN,12));
        nameLoginField.setForeground(GREYVK);
        nameLoginField.addFocusListener(new TextFieldFocusAdapter("Имя"));
        JTextField surnameLoginField = new JTextField("Фамилия", 10);
        surnameLoginField.setFont(new Font("Tahoma",Font.PLAIN,12));
        surnameLoginField.setForeground(GREYVK);
        surnameLoginField.addFocusListener(new TextFieldFocusAdapter("Фамилия"));
        MaskFormatter ipFormatter = null;
        try {
            ipFormatter = new MaskFormatter("###.###.##.##");
            ipFormatter.setPlaceholderCharacter('-');
        }
        catch (ParseException e) {
            e.printStackTrace();
        }
        JFormattedTextField ipFormatTextField = new JFormattedTextField(ipFormatter);
        ipFormatTextField.setFont(new Font("Tahoma",Font.PLAIN,12));
        ipFormatTextField.setColumns(13);
        ipFormatTextField.setEnabled(false);
        JCheckBox regCheckBox = new JCheckBox();
        regCheckBox.setBackground(Color.WHITE);
        Image checkBoxIm = new ImageIcon("D:/Джава/Laba_7_C1_Socket/checkBoxIm.png").getImage().
                getScaledInstance(17, 17, Image.SCALE_DEFAULT);
        regCheckBox.setIcon(new ImageIcon(checkBoxIm));
        regCheckBox.addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                Image checkBoxIm2 = new ImageIcon("D:/Джава/Laba_7_C1_Socket/checkBoxImSelect.png").getImage().
                        getScaledInstance(17, 17, Image.SCALE_DEFAULT);
                regCheckBox.setIcon(new ImageIcon(checkBoxIm2));
                ipFormatTextField.setEnabled(true);
            } else {
                Image checkBoxIm2 = new ImageIcon("D:/Джава/Laba_7_C1_Socket/checkBoxIm.png").getImage().
                        getScaledInstance(17, 17, Image.SCALE_DEFAULT);
                regCheckBox.setIcon(new ImageIcon(checkBoxIm2));
                ipFormatTextField.setEnabled(false);
            }
        });
        JLabel regLabel = new JLabel("Регистрация");
        regLabel.setFont(new Font("Tahoma",Font.PLAIN,12));
        JButton vhodButton = new JButton(new ImageIcon(new ImageIcon("D:/Джава/Laba_7_C1_Socket/buttonIm.png").getImage().
                getScaledInstance(110, 40, Image.SCALE_DEFAULT)));
        vhodButton.setBorder(BorderFactory.createEmptyBorder());
        vhodButton.setRolloverIcon(new ImageIcon(new ImageIcon("D:/Джава/Laba_7_C1_Socket/buttonImSelect.png").getImage().
                getScaledInstance(110, 40, Image.SCALE_DEFAULT)));
        Box loginVBox = Box.createVerticalBox();
        loginVBox.add(kostylNameLoginField);
        Box vhodLabelBox = Box.createHorizontalBox();
        vhodLabelBox.add(Box.createHorizontalGlue());
        vhodLabelBox.add(vhodLabel);
        vhodLabelBox.add(Box.createHorizontalGlue());
        loginVBox.add(vhodLabelBox);
        loginVBox.add(Box.createVerticalStrut(10));
        loginVBox.add(nameLoginField);
        loginVBox.add(Box.createVerticalStrut(10));
        loginVBox.add(surnameLoginField);
        loginVBox.add(Box.createVerticalStrut(10));
        loginVBox.add(ipFormatTextField);
        loginVBox.add(Box.createVerticalStrut(10));
        Box regBox = Box.createHorizontalBox();
        regBox.add(vhodButton);
//        regBox.add(Box.createHorizontalStrut(10));
        regBox.add(Box.createHorizontalGlue());
        regBox.add(regCheckBox);
        regBox.add(regLabel);
        loginVBox.add(regBox);
        loginVBox.add(Box.createVerticalStrut(200));
        loginDownPanel.add(loginVBox);
        Box fonHorLogin = Box.createHorizontalBox();
        JPanel fonLoginPanel2 = new JPanel();
        fonLoginPanel2.setBackground(WHITEVK);
        JPanel fonLoginPanel3 = new JPanel();
        fonLoginPanel3.setBackground(WHITEVK);
        JPanel fonLoginPanel4 = new JPanel();
        fonLoginPanel4.setBackground(Color.WHITE);
        fonLoginPanel4.add(loginVBox);
        fonHorLogin.add(fonLoginPanel2);
        fonHorLogin.add(fonLoginPanel4);
        fonHorLogin.add(fonLoginPanel3);
        Box fon = Box.createVerticalBox();
        fon.add(fonHorLogin);
        JPanel fonLoginPanel5 = new JPanel();
        fonLoginPanel5.setBackground(WHITEVK);
        JLabel im2 = new JLabel(new ImageIcon("D:/Джава/Laba_7_C1_Socket/kostyl3.png"));
        fonLoginPanel5.add(im2);
        fon.add(fonLoginPanel5);
        cardsDownPanel.add(fon, "login");

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
                field.setForeground(Color.BLACK);
            }
        }
        public void focusLost(FocusEvent e) {
            JTextField field = (JTextField) e.getSource();
            if (field.getText().isEmpty()) {
                field.setText(text);
                field.setForeground(GREYVK);
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
