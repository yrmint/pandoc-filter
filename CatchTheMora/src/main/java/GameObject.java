import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GameObject {
    public Image img;
    public int x, y;
    public Boolean act;
    Timer timerUpdate;

    public GameObject(Image img) {
        timerUpdate = new Timer(100, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                down();
            }
        });
        this.img = img;
        act = false;
    }

    public void start() {
        timerUpdate.start();
        y = 0;
        x = (int)(Math.random()*700);
        act = true;
    }

    public void down() {
        if (act)  y += 10;
        if ((y + img.getHeight(null)) >= 740)  timerUpdate.stop();
    }

    public void draw(Graphics gr) {
        if (act) gr.drawImage(img, x, y, null);
    }
}
