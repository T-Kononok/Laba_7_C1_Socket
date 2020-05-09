import javax.swing.*;
import java.awt.*;

public class ChatUp {

    private JPanel chatUpPanel = new JPanel();

    ChatUp() {
        chatUpPanel.setBackground(Color.WHITE);
        chatUpPanel.setLayout(new BoxLayout(chatUpPanel, BoxLayout.X_AXIS));
        Box chatBoxH = Box.createHorizontalBox();
        JButton buttonBack = new JButton((new ImageIcon(new ImageIcon("D:/Джава/Laba_7_C1_Socket/backIm.png").getImage().
                getScaledInstance(35, 35, Image.SCALE_DEFAULT))));
        buttonBack.setRolloverIcon(new ImageIcon(new ImageIcon("D:/Джава/Laba_7_C1_Socket/backIm2.png").getImage().
                getScaledInstance(35, 35, Image.SCALE_DEFAULT)));
        JButton buttonKostyl = new JButton((new ImageIcon(new ImageIcon("D:/Джава/Laba_7_C1_Socket/kostyl5.png").getImage().
                getScaledInstance(35, 35, Image.SCALE_DEFAULT))));
        buttonBack.setBorder(BorderFactory.createEmptyBorder());
        buttonBack.setFocusPainted(false);
//        buttonBack.addActionListener(ev ->);
        buttonKostyl.setBorder(BorderFactory.createEmptyBorder());
        buttonKostyl.setFocusPainted(false);
        JLabel nameChatLabel = new JLabel("Анонимус");
        nameChatLabel.setFont(new Font("Tahoma",Font.BOLD,12));
        chatBoxH.add(buttonBack);
        chatBoxH.add(Box.createHorizontalGlue());
        chatBoxH.add(nameChatLabel);
        chatBoxH.add(Box.createHorizontalGlue());
        chatBoxH.add(buttonKostyl);
        chatUpPanel.add(chatBoxH);
    }

    JPanel getChatUpPanel() {
        return chatUpPanel;
    }
}
