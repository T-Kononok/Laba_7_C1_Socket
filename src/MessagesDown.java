import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.Scanner;
import java.util.Vector;

class MessagesDown {

    private JPanel messagesDownPanel = new JPanel();
    private Vector<String> vectorMessages = new Vector<>();
    private DefaultListModel<String> messagesListModel = new DefaultListModel<>();
    private MainFrameClient mainFrameClient;
    private User user;
    private boolean read = true;

    MessagesDown(User user0, MainFrameClient mainFrameClient0) throws IOException {
        mainFrameClient = mainFrameClient0;
        user = user0;
        JList<String> messagesList = new JList<>(messagesListModel);
        messagesList.setCellRenderer(new MessagesRenderer());
        messagesList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane messagesListScroll = new JScrollPane(messagesList);
        messagesListScroll.setPreferredSize(new Dimension(585,450));

        messagesDownPanel.add(messagesListScroll);
        messagesDownPanel.setBackground(Color.WHITE);

        messagesList.addListSelectionListener(evt -> {
            read = true;
            if (!evt.getValueIsAdjusting()) {
                if (messagesList.getSelectedIndex() != -1) {
                    String val = messagesList.getSelectedValue();
                    String[] subStr = val.split("_");
                    mainFrameClient.setCardChat(subStr[0]);
                }
            }
            read = false;
        });
        Timer repaintTimer = new Timer(1000, ev -> {
            if (!read) {
                try {
                    readChatsInData();
                } catch (IOException e) {
                    e.printStackTrace();
                }
//                System.out.println("повторяю");
            }
        });
        repaintTimer.start();
    }

    JPanel getMessagesDownPanel() {
        return messagesDownPanel;
    }

    void setUser(User user) {
        this.user = user;
    }

    void readChatsInData() throws IOException {
        read = true;
//        System.out.print("MessagesDown->scanner->"+mainFrameClient.readFile("D:/Джава/Laba_7_C1_Socket/messages.txt")+"->");
        Scanner scanner = new Scanner(mainFrameClient.readFile("D:/Джава/Laba_7_C1_Socket/messages.txt"));
//        System.out.println("ok");
        String line;
        vectorMessages.removeAllElements();
        while(scanner.hasNextLine()){
            line = scanner.nextLine();
//            System.out.println("MessagesDown->line->"+line);
            if(line.contains(user.getName() + " " + user.getSurname())) {
//                System.out.print("MessagesDown->scanner1Text->"+mainFrameClient.readFile("D:/Джава/Laba_7_C1_Socket/" + line + ".txt")+"->");
                Scanner scanner1Text = new Scanner(mainFrameClient.readFile("D:/Джава/Laba_7_C1_Socket/" + line + ".txt"));
//                System.out.println("ok");
                line = line.replace(user.getName() + " " + user.getSurname(),"");
                line = line.replace("_","");
//                System.out.println("MessagesDown->line->"+line);
                StringBuilder text = new StringBuilder();
                while(scanner1Text.hasNextLine())
                    text.append(scanner1Text.nextLine()).append("\n");
//                System.out.println("MessagesDown->text->"+text);
                if (text.length() > 0) {
                    if (text.lastIndexOf(user.getName() + " " + user.getSurname()) >
                            text.lastIndexOf(line))
                        vectorMessages.addElement(line + "_" +
                                text.substring(text.lastIndexOf(user.getName() + " " + user.getSurname())));
                    else
                        vectorMessages.addElement(line + "_" +
                                text.substring(text.lastIndexOf(line)));
                }
            }
        }
        messagesListModel.removeAllElements();
        vectorMessages.forEach(messagesListModel::addElement);
        read = false;
    }
}
