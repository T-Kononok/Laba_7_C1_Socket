import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

class ChatDown {

    private JPanel chatDownPanel = new JPanel();
    private JTextArea readChatTextArea = new JTextArea(10,0);
    private User user = new User();
    private String interlocutor;
    private String writeNameFile;

    ChatDown() {
        chatDownPanel.setLayout(new BoxLayout(chatDownPanel, BoxLayout.X_AXIS));
        chatDownPanel.setBackground(Color.WHITE);
        readChatTextArea.setEditable(false);
        readChatTextArea.setLineWrap(true);
        readChatTextArea.setWrapStyleWord(true);
        JScrollPane scrollReadChatTextArea = new JScrollPane(readChatTextArea);
        scrollReadChatTextArea.setPreferredSize(new Dimension(585,400));
        JTextArea sendChatTextArea = new JTextArea();
        sendChatTextArea.setLineWrap(true);
        sendChatTextArea.setWrapStyleWord(true);
        JScrollPane scrollSendChatTextArea = new JScrollPane(sendChatTextArea);
        scrollSendChatTextArea.setPreferredSize(new Dimension(585,90));
        JButton buttonSend = new JButton((new ImageIcon(new ImageIcon("D:/Джава/Laba_7_C1_Socket/sendIm.png").getImage().
                getScaledInstance(35, 35, Image.SCALE_DEFAULT))));
        buttonSend.setRolloverIcon(new ImageIcon(new ImageIcon("D:/Джава/Laba_7_C1_Socket/sendIm2.png").getImage().
                getScaledInstance(35, 35, Image.SCALE_DEFAULT)));
        buttonSend.setBorder(BorderFactory.createEmptyBorder());
        buttonSend.setFocusPainted(false);
        buttonSend.addActionListener(ev -> {
            try {
                writeInData(sendChatTextArea);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
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

    private void writeInData(JTextArea textArea) throws IOException {
        FileWriter writer = new FileWriter(writeNameFile, true);
        writer.write("\n\n" + user.getName() + " " + user.getSurname() + ": " + "\n\t" + textArea.getText());
        writer.flush();
        textArea.setText("");
        readOneChatInData();
    }

    void readOneChatInData() throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("D:/Джава/Laba_7_C1_Socket/messages.txt"));
        String line;
        while(scanner.hasNextLine()){
            line = scanner.nextLine();
            if(line.equals(user.getName() + " " + user.getSurname() + "_" + interlocutor)) {
                writeNameFile = "D:/Джава/Laba_7_C1_Socket/" + line + ".txt";
                Scanner scanner1Text = new Scanner(new File(writeNameFile));
                String text = "";
                while(scanner1Text.hasNextLine())
                    text += scanner1Text.nextLine() + "\n";
                readChatTextArea.setText(text);
            }
            if(line.equals(interlocutor + "_" + user.getName() + " " + user.getSurname())) {
                writeNameFile = "D:/Джава/Laba_7_C1_Socket/" + line + ".txt";
                Scanner scanner1Text = new Scanner(new File(writeNameFile));
                String text = "";
                while(scanner1Text.hasNextLine())
                    text += scanner1Text.nextLine() + "\n";
                readChatTextArea.setText(text);
            }
        }
    }
}
