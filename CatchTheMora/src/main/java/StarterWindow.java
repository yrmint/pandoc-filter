import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class StarterWindow extends JFrame {     // стартовое меню
    private Image background;

    public StarterWindow() {
        try {
            background = ImageIO.read(new File("src\\main\\images\\stbackground.png"));
        } catch(IOException e) {}

        setTitle("Genshin Impact");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setBounds(0, 0, 800, 620);
        setContentPane(new JLabel(new ImageIcon(background)));
        setFocusable(true);
        setVisible(true);
        setResizable(false);
        Container con = this.getContentPane();
        // кнопка с правилами
        JButton rules = new JButton("Rules");
        rules.setBounds(20, 520, 360, 40);
        con.add(rules);
        rules.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RulesWindow rwindow = new RulesWindow();
            }
        });

        // кнопка начала игры
        JButton start = new JButton("Start");
        start.setBounds(410, 520, 360, 40);
        con.add(start);
        start.addActionListener(new ActionListener() {  // запускаем игру, закрываем стартовое меню
            @Override
            public void actionPerformed(ActionEvent e) {

                String player = JOptionPane.showInputDialog("What's your name?");
                if (!Objects.equals(player, "")) {
                    setVisible(false);
                    dispose();
                    GameWindow game = new GameWindow(player);
                }
                else JOptionPane.showMessageDialog(null, "Please enter your name!");
            }
        });
    }
}
