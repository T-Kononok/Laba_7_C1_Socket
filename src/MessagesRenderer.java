import javax.swing.*;
import java.awt.*;

public class MessagesRenderer implements ListCellRenderer<String> {

    private JPanel fonPanel = new JPanel();
    private JLabel name = new JLabel();
    private JLabel messages = new JLabel();

    MessagesRenderer() {

        name.setFont(new Font("Tahoma",Font.BOLD,12));
        messages.setFont(new Font("Tahoma",Font.PLAIN,12));
        messages.setHorizontalAlignment(SwingConstants.LEFT);
        fonPanel.setBackground(Color.WHITE);
        fonPanel.setLayout(new BoxLayout(fonPanel, BoxLayout.X_AXIS));
        Box labelBoxV = Box.createVerticalBox();
        labelBoxV.add(name);
        labelBoxV.add(messages);
        Box labelBoxH = Box.createHorizontalBox();
        labelBoxH.add(Box.createHorizontalStrut(20));
        labelBoxH.add(labelBoxV);
        fonPanel.add(labelBoxH);
        fonPanel.setPreferredSize(new Dimension(565,50));
        fonPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
    }

    public Component getListCellRendererComponent(JList<? extends String> list, String string, int index,
                                                  boolean isSelected, boolean cellHasFocus) {
        String[] subStr;
        String delimeter = "_";
        subStr = string.split(delimeter);

        name.setText(subStr[0]);
        messages.setText(subStr[1]);

        return fonPanel;
    }
}


