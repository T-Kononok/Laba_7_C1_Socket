import javax.swing.*;
import java.awt.*;

class ChatDown {

    private JPanel chatDownPanel = new JPanel();
    private User user = new User();
    private String interlocutor;

    ChatDown() {
        chatDownPanel.setLayout(new BoxLayout(chatDownPanel, BoxLayout.X_AXIS));
        chatDownPanel.setBackground(Color.WHITE);
        JTextArea readChatTextArea = new JTextArea(10,0);
        readChatTextArea.setEditable(false);
        readChatTextArea.setLineWrap(true);
        readChatTextArea.setWrapStyleWord(true);
        JScrollPane scrollReadChatTextArea = new JScrollPane(readChatTextArea);
        scrollReadChatTextArea.setPreferredSize(new Dimension(585,400));
        JTextArea sendChatTextArea = new JTextArea();
        sendChatTextArea.setLineWrap(true);
        sendChatTextArea.setWrapStyleWord(true);
//        sendChatTextArea.addActionListener(e -> System.out.println("++"));
        JScrollPane scrollSendChatTextArea = new JScrollPane(sendChatTextArea);
        scrollSendChatTextArea.setPreferredSize(new Dimension(585,90));
        JButton buttonSend = new JButton((new ImageIcon(new ImageIcon("D:/Джава/Laba_7_C1_Socket/sendIm.png").getImage().
                getScaledInstance(35, 35, Image.SCALE_DEFAULT))));
        buttonSend.setRolloverIcon(new ImageIcon(new ImageIcon("D:/Джава/Laba_7_C1_Socket/sendIm2.png").getImage().
                getScaledInstance(35, 35, Image.SCALE_DEFAULT)));
        buttonSend.setBorder(BorderFactory.createEmptyBorder());
        buttonSend.setFocusPainted(false);
//        buttonSend.addActionListener(ev ->);
        Box sendBoxH = Box.createHorizontalBox();
        sendBoxH.add(Box.createHorizontalStrut(20));
        sendBoxH.add(scrollSendChatTextArea);
        sendBoxH.add(Box.createHorizontalStrut(20));
        sendBoxH.add(buttonSend);
        sendBoxH.add(Box.createHorizontalStrut(20));
        Box chatDownBoxV = Box.createVerticalBox();
        chatDownBoxV.add(scrollReadChatTextArea);
        chatDownBoxV.add(Box.createVerticalStrut(10));
        chatDownBoxV.add(sendBoxH);
        chatDownBoxV.add(new JLabel(new ImageIcon("D:/Джава/Laba_7_C1_Socket/kostyl6.png")));
        chatDownPanel.add(chatDownBoxV);
    }

    JPanel getChatDownPanel() {
        return chatDownPanel;
    }

    void setUser(User user) {
        this.user = user;
    }

    void setInterlocutor(String interlocutor) {
        this.interlocutor = interlocutor;
    }
}
