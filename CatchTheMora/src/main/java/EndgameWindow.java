import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.*;

public class EndgameWindow extends JFrame {
private Image background;

    public EndgameWindow(String player, Integer score) {
        try {
            background = ImageIO.read(new File("src\\main\\images\\endgame.png"));
        } catch(IOException e) {}

        setTitle("Genshin Impact");
        setBounds(0, 0, 800, 620);
        setContentPane(new JLabel(new ImageIcon(background)));
        setFocusable(true);
        setVisible(true);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        Container con = this.getContentPane();

        Leaderboard leaderboard = new Leaderboard(player, score);
        Map<String, Integer> leaders = leaderboard.returnLeaderboard();
        JTextArea players = new JTextArea();
        JTextArea scores = new JTextArea();
        int i = 1;
        for (Map.Entry<String, Integer> entry : leaders.entrySet()) {
            players.append(i + ". " + entry.getKey() + "\n");
            scores.append(entry.getValue().toString() + "\n");
            i ++;
        }
        players.setBounds(20, 150, 650, 360);
        players.setFont(new Font("Times New Roman", Font.PLAIN, 50));
        con.add(players);
        scores.setBounds(690, 150, 80, 360);
        scores.setFont(new Font("Times New Roman", Font.PLAIN, 50));
        con.add(scores);

        //  начать игру заново
        JButton replay = new JButton("Start again");
        replay.setBounds(20, 520, 360, 40);
        con.add(replay);
        replay.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GameWindow window = new GameWindow(player);
                dispose();
            }
        });
        //  кнопка выхода
        JButton exit = new JButton("Exit");
        exit.setBounds(410, 520, 360, 40);
        con.add(exit);
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }
}
