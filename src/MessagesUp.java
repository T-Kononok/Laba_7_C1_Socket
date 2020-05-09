import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

class MessagesUp {

    private JPanel messagesUpPanel = new JPanel();
    private User user;
    private MainFrameClient mainFrameClient;

    MessagesUp(MainFrameClient mainFrameClient0) {
        mainFrameClient = mainFrameClient0;
        messagesUpPanel.setLayout(new BoxLayout(messagesUpPanel, BoxLayout.X_AXIS));
        Box messagesBoxH = Box.createHorizontalBox();
        JButton buttonSearch = new JButton((new ImageIcon(new ImageIcon("D:/Джава/Laba_7_C1_Socket/searchIm.png").getImage().
                getScaledInstance(35, 35, Image.SCALE_DEFAULT))));
        buttonSearch.setBorder(BorderFactory.createEmptyBorder());
        buttonSearch.setFocusPainted(false);
        JTextField searchTextField = new JTextField("Поиск", 10);
        searchTextField.setFont(new Font("Tahoma",Font.PLAIN,12));
        searchTextField.setForeground(new Color(153,145,146));
        searchTextField.setBorder(BorderFactory.createEmptyBorder());
        searchTextField.addFocusListener(new LoginDown.TextFieldFocusAdapter("Поиск",false));
        searchTextField.addActionListener(ev -> {
                try {
                    search(searchTextField.getText());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                searchTextField.setText("");
            }
        );
        buttonSearch.addActionListener(ev -> {
                try {
                    search(searchTextField.getText());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                searchTextField.setText("");
            }
        );
        messagesBoxH.add(buttonSearch);
        messagesBoxH.add(searchTextField);
        messagesUpPanel.add(messagesBoxH);
    }

    void setUser(User user) {
        this.user = user;
    }

    private void search(String name) throws IOException {
        Scanner scanner = new Scanner(new File("D:/Джава/Laba_7_C1_Socket/messages.txt"));
        String line;
        boolean chatNew = true;
        while(scanner.hasNextLine()){
            line = scanner.nextLine();
            if(line.equals(user.getName() + " " + user.getSurname() + "_" + name) ||
                    line.equals(name + "_" + user.getName() + " " + user.getSurname())) {
                mainFrameClient.setCardChat(name);
                chatNew = false;
                break;
            }
        }
        if (chatNew) {
            FileWriter writer = new FileWriter("D:/Джава/Laba_7_C1_Socket/messages.txt", true);
            writer.write("\n" + user.getName() + " " + user.getSurname() + "_" + name);
            writer.flush();
            File f = new File("D:/Джава/Laba_7_C1_Socket/" +
                    user.getName() + " " + user.getSurname() + "_" + name + ".txt");
            f.createNewFile();
            mainFrameClient.setCardChat(name);
        }

    }

    JPanel getMessagesUpPanel() {
        return messagesUpPanel;
    }
}