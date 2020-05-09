import javax.swing.*;
import java.awt.*;
import java.util.Vector;

public class MessagesDown {

    private JPanel messagesDownPanel = new JPanel();

    MessagesDown() {
        Vector<String> vectorMessages = new Vector<>();
        vectorMessages.addElement("Сергей Кровельщиков_Посмотри Гатари");
        vectorMessages.addElement("Росшер Бывалый_Росшер Бывалый");
        vectorMessages.addElement("Клим Самгин_Клим Самгин");
        vectorMessages.addElement("Дмитрий Алёхин_Дмитрий Алёхин");
        vectorMessages.addElement("Артём Крылов_Артём Крылов");
        vectorMessages.addElement("Юрий Ревякин_Юрий Ревякин");
        vectorMessages.addElement("Павел Романов_Павел Романов");
        vectorMessages.addElement("Дмитрий Шизкалев_Дмитрий Шизкалев");
        vectorMessages.addElement("Никита Иванчиков_Никита Иванчиков");
        vectorMessages.addElement("Сергей Агафонов_Сергей Агафонов");
        vectorMessages.addElement("Александр Лихварь_Александр Лихварь");

        DefaultListModel<String> messagesListModel = new DefaultListModel<>();
        vectorMessages.forEach(messagesListModel::addElement);

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
                    System.out.println("выбрали " + subStr[1]);
                }
            }
        });
    }

    JPanel getMessagesDownPanel() {
        return messagesDownPanel;
    }
}
