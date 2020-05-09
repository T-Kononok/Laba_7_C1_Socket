import javax.swing.*;
import java.awt.*;

public class MessagesUp {

    private JPanel messagesUpPanel = new JPanel();

    MessagesUp() {
        messagesUpPanel.setLayout(new BoxLayout(messagesUpPanel, BoxLayout.X_AXIS));
        Box messagesBoxH = Box.createHorizontalBox();
        JButton buttonSearch = new JButton((new ImageIcon(new ImageIcon("D:/Джава/Laba_7_C1_Socket/searchIm.png").getImage().
                getScaledInstance(35, 35, Image.SCALE_DEFAULT))));
        buttonSearch.setBorder(BorderFactory.createEmptyBorder());
        buttonSearch.setFocusPainted(false);
//        buttonSearch.addActionListener(ev ->);
        JTextField searchTextField = new JTextField("Поиск", 10);
        searchTextField.setFont(new Font("Tahoma",Font.PLAIN,12));
        searchTextField.setForeground(new Color(153,145,146));
        searchTextField.setBorder(BorderFactory.createEmptyBorder());
        searchTextField.addFocusListener(new LoginDown.TextFieldFocusAdapter("Поиск",false));
//        searchTextField.addActionListener(e -> System.out.println("++"));
        messagesBoxH.add(buttonSearch);
        messagesBoxH.add(searchTextField);
        messagesUpPanel.add(messagesBoxH);
    }

    JPanel getMessagesUpPanel() {
        return messagesUpPanel;
    }
}