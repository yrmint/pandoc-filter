import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class RulesWindow extends JFrame {
    private Image background;

    public RulesWindow() {
        try {
            background = ImageIO.read(new File("src\\main\\images\\rules.png"));
        } catch(IOException e) {}

        setTitle("Genshin Impact");
        setBounds(0, 0, 800, 620);
        setContentPane(new JLabel(new ImageIcon(background)));
        setFocusable(true);
        setVisible(true);
        setResizable(false);
        Container con = this.getContentPane();
        JButton back = new JButton("Back");
        back.setBounds(220, 520, 360, 40);
        con.add(back);
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                setEnabled(false);
            }
        });

    }
}
