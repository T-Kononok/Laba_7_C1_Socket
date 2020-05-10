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

    MessagesDown(User user0, MainFrameClient mainFrameClient0) {
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
            if (!evt.getValueIsAdjusting()) {
                if (messagesList.getSelectedIndex() != -1) {
                    String val = messagesList.getSelectedValue();
                    String[] subStr = val.split("_");
                    mainFrameClient.setCardChat(subStr[0]);
                }
            }
        });
    }

    JPanel getMessagesDownPanel() {
        return messagesDownPanel;
    }

    void setUser(User user) {
        this.user = user;
    }

    void readChatsInData() throws InterruptedException, IOException {
        Scanner scanner = new Scanner(mainFrameClient.readFile("D:/Джава/Laba_7_C1_Socket/messages.txt"));
        String line;
        vectorMessages.removeAllElements();
        while(scanner.hasNextLine()){
            line = scanner.nextLine();
            if(line.contains(user.getName() + " " + user.getSurname())) {
                Scanner scanner1Text = new Scanner(mainFrameClient.readFile("D:/Джава/Laba_7_C1_Socket/" + line + ".txt"));
                line = line.replace(user.getName() + " " + user.getSurname(),"");
                line = line.replace("_","");
                StringBuilder text = new StringBuilder();
                while(scanner1Text.hasNextLine())
                    text.append(scanner1Text.nextLine()).append("\n");
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
    }
}
