package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.net.URL;
import java.util.Random;

public class Platform {

    int dx;
    int x, y, width, height;
    URL url;
    Image plat;

    public Platform() {
        dx = -1;
        x = 450;
        y = 300;
        width = 120;
        height = 30;
    }

    public Platform(int x, int y) {
        this.x = x;
        this.y = y;
        width = 120;
        height = 30;
        dx = -1;
        plat = Pictures.platform;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void update(StartingPoint sp, Ball b) {
        x += -(Pictures.level);
        checkForCollision(b);
        if (x < 0 - width) {
            Random r = new Random();
            y = sp.getHeight() - 40 - r.nextInt(400);
            //	x = sp.getWidth() + r.nextInt(300);
        }
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    private void checkForCollision(Ball b) {
        int ballX = b.getX();
        int ballY = b.getY();
        int radius = b.getRadius();

        if (ballY + radius > y && ballY + radius < y + height) {
            if (ballX > x && ballX < x + width) {
                double newDY = b.getGameDy();
                b.setY(y - radius);
                b.setDy(newDY);
            }
        }
    }

    public void paint(Graphics g) {
        g.drawImage(plat, x, y, Pictures.sp);
    }
}

