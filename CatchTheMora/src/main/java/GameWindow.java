import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;

public class GameWindow extends JFrame {
    private final Field gameField;

    private class Listener implements KeyListener {
        public void keyPressed(KeyEvent e) {
            int key = e.getKeyCode();
            if (key == 27) System.exit(0);
            else if (key == 37) {
                if(gameField.x - 30 > -48) gameField.x -= 30;
                else gameField.x = 752;
            }
            else if (key == 39) {
                if (gameField.x + 30 < 752) gameField.x += 30;
                else gameField.x = -48;
            }
        }
        public void keyReleased(KeyEvent e) {}
        public void keyTyped(KeyEvent e) {}
    }

    private class Field extends JPanel {
        private Image character;
        private Image background;
        private Image lives3, lives2, lives1;
        public Integer x = 400;
        private int lives = 3;
        private int score = 0;
        private final GameObject[] objects;
        public Timer timerUpdate, timerDraw;
        private final String player;

        public Field (String player) {
            this.player = player;
            try {
                character = ImageIO.read(new File("src\\main\\images\\character.png"));
            } catch(IOException e) {}

            try {
                background = ImageIO.read(new File("src\\main\\images\\background.png"));
            } catch(IOException e) {}

            try {
                lives1 = ImageIO.read(new File("src\\main\\images\\lives1.png"));
                lives2 = ImageIO.read(new File("src\\main\\images\\lives2.png"));
                lives3 = ImageIO.read(new File("src\\main\\images\\lives3.png"));
            } catch(IOException e) {}

            objects = new GameObject[7];
            for (int i = 0; i < 7; i++) {
                try {
                    objects[i] = new GameObject(ImageIO.read(new File("src\\main\\images\\mora.png")));
                } catch(IOException e) {}
            }

            timerUpdate = new Timer(1000, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    updateStart();
                }
            });
            timerUpdate.start();

            timerDraw = new Timer(50, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    repaint();
                }
            });
            timerDraw.start();
        }

        public void paintComponent(Graphics gr) {
            super.paintComponent(gr);
            gr.drawImage(background, 0, 0, null);
            gr.drawImage(character, x, 400, null);
            gr.setFont(new Font("Times New Roman", Font.PLAIN, 20));
            gr.drawString("Score: " + score, 0, 580);
            if (lives == 3) gr.drawImage(lives3, 0, 0, null);
            if (lives == 2) gr.drawImage(lives2,0, 0, null);
            else if (lives == 1) gr.drawImage(lives1,0, 0, null);
            for (int i = 0; i < 7; i++) {
                objects[i].draw(gr);
                if (objects[i].act) {
                    if (objects[i].y + objects[i].img.getHeight(null) >= 600 && Math.abs(objects[i].x - x) > 130) {
                        if (lives > 1) {
                            lives -= 1;
                            objects[i].act = false;
                        }
                        else {
                            timerDraw.stop();
                            timerUpdate.stop();
                            EndgameWindow endgame = new EndgameWindow(player, score);
                            dispose();
                        }
                    } else if (Math.abs(objects[i].x - x) <= 130 && objects[i].y + objects[i].img.getHeight(null) >= 400) {
                        objects[i].act = false;
                        score += 1;
                    }
                }
            }
        }

        private void updateStart() {
            int q = 0;
            for (int i = 0; i < 7; i++) {
                if (!objects[i].act) {
                    if (q < 7) {
                        objects[i].start();
                        break;
                    }
                }
                else q ++;
            }
        }
    }

    public GameWindow(String player) {
        addKeyListener(new Listener());
        setFocusable(true);
        setBounds(0, 0, 800, 620);
        setTitle("Genshin Impact");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        gameField = new Field(player);
        Container con = getContentPane();
        con.add(gameField);
        setVisible(true);
    }
}
