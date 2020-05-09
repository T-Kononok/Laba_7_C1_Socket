import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.awt.event.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;

class LoginDown {

    private static final Color WHITEVK = new Color(237,238,240);
    private static final Color GREYVK = new Color(153,145,146);

    private User user = new User();
    private Box fon = Box.createVerticalBox();

    LoginDown(MainFrameClient mainFrameClient){
        JPanel loginDownPanel = new JPanel();
        loginDownPanel.setBackground(Color.WHITE);
        JTextField kostylNameLoginField = new JTextField("",1);
        kostylNameLoginField.setBorder(BorderFactory.createEmptyBorder());
        kostylNameLoginField.setBackground(Color.WHITE);
        kostylNameLoginField.setCaretColor(Color.WHITE);
        kostylNameLoginField.setForeground(Color.WHITE);
        JLabel vhodLabel = new JLabel("Вход пародии ВКонтакте");
        vhodLabel.setFont(new Font("Tahoma",Font.PLAIN,20));
        JTextField nameLoginField = new JTextField("Имя",10);
        nameLoginField.setFont(new Font("Tahoma",Font.PLAIN,12));
        nameLoginField.setForeground(GREYVK);
        nameLoginField.addFocusListener(new TextFieldFocusAdapter("Имя",false));
        JTextField surnameLoginField = new JTextField("Фамилия", 10);
        surnameLoginField.setFont(new Font("Tahoma",Font.PLAIN,12));
        surnameLoginField.setForeground(GREYVK);
        surnameLoginField.addFocusListener(new TextFieldFocusAdapter("Фамилия",false));
        JPasswordField passwordField = new JPasswordField("Пароль", 12);
        passwordField.setFont(new Font("Tahoma",Font.PLAIN,12));
        passwordField.setForeground(GREYVK);
        passwordField.setEchoChar((char) 0);
        passwordField.addFocusListener(new TextFieldFocusAdapter("Пароль",true));
        MaskFormatter ipFormatter = null;
        try {
            ipFormatter = new MaskFormatter("###.###.##.##");
            ipFormatter.setPlaceholderCharacter('-');
        }
        catch (ParseException e) {
            e.printStackTrace();
        }
        JFormattedTextField ipFormatTextField = new JFormattedTextField(ipFormatter);
        ipFormatTextField.addFocusListener(new FormatFieldFocusAdapter());
        ipFormatTextField.setFont(new Font("Tahoma",Font.PLAIN,12));
        ipFormatTextField.setColumns(13);
        ipFormatTextField.setEnabled(false);
        JCheckBox regCheckBox = new JCheckBox();
        regCheckBox.setBackground(Color.WHITE);
        Image checkBoxIm = new ImageIcon("D:/Джава/Laba_7_C1_Socket/checkBoxIm.png").getImage().
                getScaledInstance(17, 17, Image.SCALE_DEFAULT);
        regCheckBox.setIcon(new ImageIcon(checkBoxIm));
        regCheckBox.addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                Image checkBoxIm2 = new ImageIcon("D:/Джава/Laba_7_C1_Socket/checkBoxImSelect.png").getImage().
                        getScaledInstance(17, 17, Image.SCALE_DEFAULT);
                regCheckBox.setIcon(new ImageIcon(checkBoxIm2));
                ipFormatTextField.setEnabled(true);
                passwordField.setEnabled(true);
            } else {
                Image checkBoxIm2 = new ImageIcon("D:/Джава/Laba_7_C1_Socket/checkBoxIm.png").getImage().
                        getScaledInstance(17, 17, Image.SCALE_DEFAULT);
                regCheckBox.setIcon(new ImageIcon(checkBoxIm2));
                ipFormatTextField.setEnabled(false);
                passwordField.setEnabled(false);
            }
        });
        JLabel regLabel = new JLabel("Регистрация");
        regLabel.setFont(new Font("Tahoma",Font.PLAIN,12));
        JButton vhodButton = new JButton(new ImageIcon(new ImageIcon("D:/Джава/Laba_7_C1_Socket/buttonIm.png").getImage().
                getScaledInstance(110, 40, Image.SCALE_DEFAULT)));
        vhodButton.setBorder(BorderFactory.createEmptyBorder());
        vhodButton.setRolloverIcon(new ImageIcon(new ImageIcon("D:/Джава/Laba_7_C1_Socket/buttonImSelect.png").getImage().
                getScaledInstance(110, 40, Image.SCALE_DEFAULT)));
        vhodButton.addActionListener( actionEvent -> {
            user.setName(nameLoginField.getText());
            user.setSurname(surnameLoginField.getText());
            user.setPassword(passwordField.getPassword());
            try {
                if (!regCheckBox.isSelected()) {
                    switch (user.checkInData()) {
                        case ("Вход"):
                            System.out.println("Вход");
                            mainFrameClient.setUser(user);
                            mainFrameClient.setCards("messages");
                            break;
                        case ("Не верный пароль"):
                            passwordField.setText("Не верный пароль");
                            passwordField.setForeground(Color.RED);
                            passwordField.setEchoChar((char) 0);
                            break;
                        case ("Зарегистрируйтесь"):
                            regCheckBox.setSelected(true);
                            break;
                    }
                } else {
                    if(user.checkInData().equals("Зарегистрируйтесь")) {
                        user.setIp(ipFormatTextField.getText());
                        if(user.checkIPInData().equals("Регистрация")) {
                            mainFrameClient.setUser(user);
                            mainFrameClient.setCards("messages");
                        } else {
                            ipFormatTextField.setForeground(Color.RED);
                        }
                    } else {
                        nameLoginField.setText("Имя и фамилия уже заняты");
                        nameLoginField.setForeground(Color.RED);
                        surnameLoginField.setText("");
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        Box loginVBox = Box.createVerticalBox();
        loginVBox.add(kostylNameLoginField);
        Box vhodLabelBox = Box.createHorizontalBox();
        vhodLabelBox.add(Box.createHorizontalGlue());
        vhodLabelBox.add(vhodLabel);
        vhodLabelBox.add(Box.createHorizontalGlue());
        loginVBox.add(vhodLabelBox);
        loginVBox.add(Box.createVerticalStrut(10));
        loginVBox.add(nameLoginField);
        loginVBox.add(Box.createVerticalStrut(10));
        loginVBox.add(surnameLoginField);
        loginVBox.add(Box.createVerticalStrut(10));
        loginVBox.add(passwordField);
        loginVBox.add(Box.createVerticalStrut(10));
        loginVBox.add(ipFormatTextField);
        loginVBox.add(Box.createVerticalStrut(10));
        Box regBox = Box.createHorizontalBox();
        regBox.add(vhodButton);
        regBox.add(Box.createHorizontalGlue());
        regBox.add(regCheckBox);
        regBox.add(regLabel);
        loginVBox.add(regBox);
        loginVBox.add(Box.createVerticalStrut(200));
        loginDownPanel.add(loginVBox);
        Box fonHorLogin = Box.createHorizontalBox();
        JPanel fonLoginPanel2 = new JPanel();
        fonLoginPanel2.setBackground(WHITEVK);
        JPanel fonLoginPanel3 = new JPanel();
        fonLoginPanel3.setBackground(WHITEVK);
        JPanel fonLoginPanel4 = new JPanel();
        fonLoginPanel4.setBackground(Color.WHITE);
        fonLoginPanel4.add(loginVBox);
        fonHorLogin.add(fonLoginPanel2);
        fonHorLogin.add(fonLoginPanel4);
        fonHorLogin.add(fonLoginPanel3);

        fon.add(fonHorLogin);
        JPanel fonLoginPanel5 = new JPanel();
        fonLoginPanel5.setBackground(WHITEVK);
        JLabel im2 = new JLabel(new ImageIcon("D:/Джава/Laba_7_C1_Socket/kostyl3.png"));
        fonLoginPanel5.add(im2);
        fon.add(fonLoginPanel5);
    }

    Box getLoginFonBox() {
        return fon;
    }

    public static class TextFieldFocusAdapter extends FocusAdapter {
        private String text;
        private boolean password;

        TextFieldFocusAdapter(String text, boolean password) {
            this.text = text;
            this.password = password;
        }

        public void focusGained(FocusEvent e) {
            if (!password) {
                JTextField field = (JTextField) e.getSource();
                if (field.getText().equals(text) || field.getText().equals("Имя и фамилия уже заняты")) {
                    field.setText("");
                    field.setForeground(Color.BLACK);
                }
            } else {
                JPasswordField field = (JPasswordField) e.getSource();
                if (field.getText().equals(text) || field.getText().equals("Не верный пароль")) {
                    field.setText("");
                    field.setForeground(Color.BLACK);
                    field.setEchoChar('●');
                }
            }
        }
        public void focusLost(FocusEvent e) {
            if (!password) {
                JTextField field = (JTextField) e.getSource();
                if (field.getText().isEmpty()) {
                    field.setText(text);
                    field.setForeground(GREYVK);
                }
            } else {
                JPasswordField field = (JPasswordField) e.getSource();
                if (field.getText().isEmpty()) {
                    field.setText(text);
                    field.setForeground(GREYVK);
                    field.setEchoChar((char) 0);
                }
            }
        }
    }

    public static class FormatFieldFocusAdapter extends FocusAdapter {
        public void focusGained(FocusEvent e) {
            JFormattedTextField field = (JFormattedTextField) e.getSource();
            field.setForeground(Color.BLACK);
        }
    }

}


