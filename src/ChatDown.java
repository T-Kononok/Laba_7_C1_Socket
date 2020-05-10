import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

class ChatDown {

    private JPanel chatDownPanel = new JPanel();
    private JTextArea readChatTextArea = new JTextArea(10,0);
    private User user;
    private String interlocutor;
    private String writeNameFile;
    private MainFrameClient mainFrameClient;
    private boolean read = true;

    ChatDown(MainFrameClient mainFrameClient0) throws IOException, InterruptedException {
        mainFrameClient = mainFrameClient0;
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
            read = true;
            try {
                writeInData(sendChatTextArea);
            } catch (InterruptedException | IOException e) {
                e.printStackTrace();
            }
            read = false;
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

        Timer repaintTimer = new Timer(1000, ev -> {
            if (!read) {
                try {
                    readOneChatInData();
                } catch (IOException | InterruptedException e) {
                    e.printStackTrace();
                }
//                System.out.println("повторяю");
            }
        });
        repaintTimer.start();
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

    private void writeInData(JTextArea textArea) throws InterruptedException, IOException {
//        System.out.println("ChatDown->writeInData->\n\n" + user.getName() + " " + user.getSurname() + ": " + "\n\t" + textArea.getText());
        mainFrameClient.writeToFile(writeNameFile,
                "\n\n" + user.getName() + " " + user.getSurname() + ": " + "\n\t" + textArea.getText());
        textArea.setText("");
//        System.out.println("ChatDown->readOneChatInData->");
        readOneChatInData();
    }

    void readOneChatInData() throws InterruptedException, IOException {
        read = true;
//        System.out.println("ChatDown->readOneChatInData->scanner->"+mainFrameClient.readFile("D:/Джава/Laba_7_C1_Socket/messages.txt"));
        Scanner scanner = new Scanner(mainFrameClient.readFile("D:/Джава/Laba_7_C1_Socket/messages.txt"));
        String line;
        while(scanner.hasNextLine()){
            line = scanner.nextLine();
//            System.out.println("ChatDown->readOneChatInData->line->"+line);
            if(line.equals(user.getName() + " " + user.getSurname() + "_" + interlocutor)) {
                writeNameFile = "D:/Джава/Laba_7_C1_Socket/" + line + ".txt";
                String text = mainFrameClient.readFile(writeNameFile);
//                System.out.println("ChatDown->readOneChatInData->setText->"+text+"->end");
                readChatTextArea.setText(text);
            }
            if(line.equals(interlocutor + "_" + user.getName() + " " + user.getSurname())) {
                writeNameFile = "D:/Джава/Laba_7_C1_Socket/" + line + ".txt";
                String text = mainFrameClient.readFile(writeNameFile);
//                System.out.println("ChatDown->readOneChatInData->setText->"+text+"->end");
                readChatTextArea.setText(text);
            }
        }
        read = false;
    }
}
