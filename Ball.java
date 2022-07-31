package ThunderFighter;

import java.awt.*;
import java.util.Random;
public class Ball {
    private int x;  //坐标
    private int y;
    private int w;  //宽高
    private int h;
    private Image img;
    Random rd = new Random();
    private boolean flag = rd.nextBoolean();
    Ball() {

    }
    Ball(int x, int y, Image img) {
        this.x = x;
        this.y = y;
        this.img = img;
    }
    Ball(int x, int y, int w, int h, Image img) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.img = img;
    }
    /**
     * 俩小球碰撞判定 碰撞返回true 否则返回false
     * @param ball
     * @return
     */
    public boolean isT(Ball ball) {
        int r1 = this.w / 2; //第一个球的半径

        int r2 = ball.getW() / 2; //第二个球的半径
        int x2 = ball.getX();   //第二个球的坐标
        int y2 = ball.getY();
        double s = Math.sqrt(Math.pow(x2 - x, 2) + Math.pow(y2 - y, 2));
        double sr = r1 + r2;
        return s <= sr;
    }
    //敌机移动
    public void enemyPlaneMove() {
        this.y++;
        if (flag) {
            this.x = this.x + rd.nextInt(3);
        } else {
            this.x = this.x - rd.nextInt(3);
        }
        flag = rd.nextBoolean();
    }
    //我机子弹移动
    public void minusY(int shotSpeed) {
        this.y-=shotSpeed;
    }
    //boss子弹移动
    public void addY() {
        this.y+=2;
    }
    //画的方法
    public void drawImage(Graphics g) {
        g.drawImage(img, x, y, w, h, null);
    }
    public int getX() {
        return x;
    }
    public void setX(int x) {
        this.x = x;
    }
    public int getY() {
        return y;
    }
    public void setY(int y) {
        this.y = y;
    }
    public int getW() {
        return w;
    }
    public void setW(int w) {
        this.w = w;
    }
    public int getH() {
        return h;
    }
    public void setH(int h) {
        this.h = h;
    }
    public Image getImg() {
        return img;
    }
    public void setImg(Image img) {
        this.img = img;
    }
}
