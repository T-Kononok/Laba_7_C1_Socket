import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.Scanner;

class MessagesUp {

    private JPanel messagesUpPanel = new JPanel();
    private User user;
    private MainFrameClient mainFrameClient;
    private JTextField searchTextField = new JTextField("Поиск", 10);

    MessagesUp(MainFrameClient mainFrameClient0) {
        mainFrameClient = mainFrameClient0;
        messagesUpPanel.setLayout(new BoxLayout(messagesUpPanel, BoxLayout.X_AXIS));
        Box messagesBoxH = Box.createHorizontalBox();
        JButton buttonSearch = new JButton((new ImageIcon(new ImageIcon("D:/Джава/Laba_7_C1_Socket/searchIm.png").getImage().
                getScaledInstance(35, 35, Image.SCALE_DEFAULT))));
        buttonSearch.setBorder(BorderFactory.createEmptyBorder());
        buttonSearch.setFocusPainted(false);
        searchTextField.setFont(new Font("Tahoma",Font.PLAIN,12));
        searchTextField.setForeground(new Color(153,145,146));
        searchTextField.setBorder(BorderFactory.createEmptyBorder());
        searchTextField.addFocusListener(new LoginDown.TextFieldFocusAdapter("Поиск",false));
        searchTextField.addActionListener(ev -> {
                    try {
                        search(searchTextField.getText());
                    } catch (InterruptedException | IOException e) {
                        e.printStackTrace();
                    }
                }
        );
        buttonSearch.addActionListener(ev -> {
                    try {
                        search(searchTextField.getText());
                    } catch (InterruptedException | IOException e) {
                        e.printStackTrace();
                    }
                }
        );
        messagesBoxH.add(buttonSearch);
        messagesBoxH.add(searchTextField);
        messagesUpPanel.add(messagesBoxH);
    }

    void setUser(User user) {
        this.user = user;
    }

    private void search(String name) throws InterruptedException, IOException {
        Scanner scanner = new Scanner(mainFrameClient.readFile("D:/Джава/Laba_7_C1_Socket/messages.txt"));
        String line;
        boolean chatNew = true;
        while(scanner.hasNextLine()){
            line = scanner.nextLine();
            if(line.equals(user.getName() + " " + user.getSurname() + "_" + name) ||
                    line.equals(name + "_" + user.getName() + " " + user.getSurname())) {
                mainFrameClient.setCardChat(name);
                searchTextField.setText("");
                chatNew = false;
                break;
            }
        }
        if (chatNew) {
            System.out.println("+");
            Scanner scanner2 = new Scanner(mainFrameClient.readFile("D:/Джава/Laba_7_C1_Socket/login.txt"));
            boolean nameInLogin = false;
            while(scanner2.hasNextLine()){
                String line2 = scanner2.nextLine();
                if(line2.equals("%"+name)) {
                    mainFrameClient.writeToFile("D:/Джава/Laba_7_C1_Socket/messages.txt",
                            "\n" + user.getName() + " " + user.getSurname() + "_" + name);
                    mainFrameClient.createFile("D:/Джава/Laba_7_C1_Socket/" +
                            user.getName() + " " + user.getSurname() + "_" + name + ".txt");
                    mainFrameClient.setCardChat(name);
                    searchTextField.setText("");
                    nameInLogin = true;
                    break;
                }
            }
            if (!nameInLogin)
                searchTextField.setText("Пользователь не найден");
        }

    }

    JPanel getMessagesUpPanel() {
        return messagesUpPanel;
    }
}