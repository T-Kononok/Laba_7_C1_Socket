import javax.swing.*;
import java.awt.*;

public class MainFrameClient extends JFrame {

    private static final String FRAME_TITLE = "Жалкая породия на нормальный мессенджер";
    private static final int FRAME_MINIMUM_WIDTH = 500;
    private static final int FRAME_MINIMUM_HEIGHT = 500;

    private CardLayout cards = new CardLayout();
    private JPanel cardsPanel = new JPanel(cards);
    private int a;


    private MainFrameClient(){

        super(FRAME_TITLE);
        setMinimumSize(new Dimension(FRAME_MINIMUM_WIDTH, FRAME_MINIMUM_HEIGHT));
        Toolkit kit = Toolkit.getDefaultToolkit();
        setLocation((kit.getScreenSize().width - getWidth()) / 2,
                    (kit.getScreenSize().height - getHeight()) / 2);

        JPanel card1 = new JPanel();
        card1.setBackground(Color.RED);
        JPanel card2 = new JPanel();
        card2.setBackground(Color.GRAY);
        JPanel card3 = new JPanel();
        card3.setBackground(Color.GREEN);

        cardsPanel.add(card1, "1");
        cardsPanel.add(card2, "2");
        cardsPanel.add(card3, "3");

        a = 1;
        final JButton button = new JButton("Поменять");
        button.addActionListener(e -> {
            cards.show(cardsPanel, "" + a);
            a++;
            if (a > 3) {
                a = 1;
            }
        });

        getContentPane().add(cardsPanel, BorderLayout.CENTER);
        getContentPane().add(button, BorderLayout.PAGE_START);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            final MainFrameClient frame = new MainFrameClient();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }
}
