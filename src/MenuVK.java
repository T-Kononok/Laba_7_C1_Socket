import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Arrays;

class MenuVK extends JPanel {
    private JPanel menuPanel = new JPanel();
    private JButton profileButton = new JButton();
    private JLabel kostylIm = new JLabel(new ImageIcon("D:/Джава/Laba_7_C1_Socket/kostyl.png"));
    private JLabel kostylLabel = new JLabel("#лучшедома");
    private JLabel titlLabel = new JLabel("Жалкая пародия на VK");
    private static final Color BLUEVK = new Color(74,118,168);
    private JLabel profileButtonLabel = new JLabel("Аноним");
    private User user;

    MenuVK(User user0) {
        user = user0;
        menuPanel.setLayout(new BoxLayout(menuPanel, BoxLayout.X_AXIS));
        menuPanel.setBackground(BLUEVK);
        titlLabel.setForeground(Color.WHITE);
        titlLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
        JLabel hashtagLabel = new JLabel("#лучшедома");
        hashtagLabel.setForeground(Color.WHITE);
        hashtagLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
        menuPanel.add(new JLabel(new ImageIcon("D:/Джава/Laba_7_C1_Socket/vkIm.png")));
        menuPanel.add(hashtagLabel);
        menuPanel.add(Box.createHorizontalGlue());
        menuPanel.add(titlLabel);
        menuPanel.add(Box.createHorizontalGlue());
        kostylLabel.setForeground(BLUEVK);
        kostylLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
        menuPanel.add(kostylLabel);
        menuPanel.add(kostylIm);
        profileButtonLabel.setForeground(Color.WHITE);
        profileButtonLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
        profileButton.add(profileButtonLabel);
        JLabel im = new JLabel(new ImageIcon("D:/Джава/Laba_7_C1_Socket/kostyl2.png"));
        im.setVisible(false);
        profileButton.add(im);
        profileButton.setBorderPainted(false);
        profileButton.setFocusPainted(false);
        profileButton.setBackground(BLUEVK);
        profileButton.addMouseListener(new ButtonMouseListener());
        menuPanel.add(profileButton);
        repaint();
        profileButton.addActionListener(ev ->
                JOptionPane.showMessageDialog(MenuVK.this, new String[] {
                        "Имя: " + user.getName(),
                        "Фамилия: " + user.getSurname(),
                        "IP: " + user.getIp(),
                        "Пароль: " + Arrays.toString(user.getPassword())}, "Ваш профиль", JOptionPane.INFORMATION_MESSAGE)
        );
    }


    void setLogin () {
        profileButton.setVisible(false);
        kostylIm.setVisible(true);
        kostylLabel.setVisible(true);
        titlLabel.setVisible(true);
    }

    void setAlogin () {
        profileButton.setVisible(true);
        kostylIm.setVisible(false);
        kostylLabel.setVisible(false);
        titlLabel.setVisible(false);
        profileButtonLabel.setText(user.getName());
    }

    void setUser(User user) {
        this.user = user;
    }

    JPanel getMenuPanel() {
        return menuPanel;
    }

    static class ButtonMouseListener implements MouseListener {

        public void mouseClicked(MouseEvent e) { }

        public void mouseEntered(MouseEvent arg0) {
            JButton button = (JButton) arg0.getSource();
            button.setBackground(new Color(61,104,152));
        }

        public void mouseExited(MouseEvent arg0) {
            JButton button = (JButton) arg0.getSource();
            button.setBackground(new Color(74,118,168));
        }

        public void mousePressed(MouseEvent e) { }

        public void mouseReleased(MouseEvent e) { }
    }

}


