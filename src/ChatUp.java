import javax.swing.*;
import java.awt.*;

class ChatUp {

    private JPanel chatUpPanel = new JPanel();
    private  JLabel nameChatLabel = new JLabel("Анонимус");

    ChatUp(MainFrameClient mainFrameClient) {
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
        buttonBack.addActionListener(ev -> {
            try {
                mainFrameClient.setCards("messages");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        buttonKostyl.setBorder(BorderFactory.createEmptyBorder());
        buttonKostyl.setFocusPainted(false);
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

    void setInterlocutor(String interlocutor) {
        nameChatLabel.setText(interlocutor);
    }
}
