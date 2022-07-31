package ThunderFighter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.Random;

public class BallJPanel extends JPanel implements MouseMotionListener, MouseListener, Runnable {
    //private Ball background;
    private Ball airPlane;  //我机
    private int sum = 0; //积分
    private int num = 0; //我机能被撞的次数
    private int speed = 50; //控制敌机产生速度
    private int nandu, nandu2; //控制难度
    private boolean isOver = false; //是否运行完成
    private boolean isFire = false;  //是否发射
    private ArrayList<Ball> fireList = new ArrayList<Ball>(); //我机子弹集合 --无限
    private ArrayList<Ball> enmeyList = new ArrayList<Ball>(); //敌机集合 --无限
    private ArrayList<Ball> deathList = new ArrayList<Ball>();  //敌机死亡集合
    private ArrayList<Ball> backList = new ArrayList<Ball>();  //背景图集合
    private ArrayList<Boss> boss = new ArrayList<Boss>(); //boss
    private ArrayList<Ball> deathBoss = new ArrayList<Ball>(); //Deathboss
    private ArrayList<Ball> prop = new ArrayList<Ball>(); //道具
    private ArrayList<Ball> boss_fire = new ArrayList<Ball>(); //boss子弹集合
    private ArrayList<Ball> boss_fire_bomb = new ArrayList<Ball>(); //boss子弹爆炸集合

    private Image img_back = new ImageIcon("img\\t\\bg.png").getImage();
    private Image img_back2 = new ImageIcon("img\\t\\back.jpg").getImage();
    private Image img_back3 = new ImageIcon("img\\t\\back.gif").getImage();
    private Image img_hero = new ImageIcon("img\\t\\hero1.png").getImage();
    private Image img_fire = new ImageIcon("img\\t\\fire.gif").getImage();
    private Image img_enemy = new ImageIcon("img\\t\\enemy.png").getImage();
    private Image img_bomb = new ImageIcon("img\\t\\bomb_0.gif").getImage();
    private Image img_over = new ImageIcon("img\\t\\over.jpg").getImage();
    private Image img_boss = new ImageIcon("img\\t\\e2.png").getImage();
    private Image img_boss_bomb = new ImageIcon("img\\t\\bomb_1.gif").getImage();
    private Image img_prop = new ImageIcon("img\\t\\gwls1.gif").getImage();
    private Image img_boss_fire = new ImageIcon("img\\t\\el_bb_0.gif").getImage();
    private Image img_boss_fire_bomb = new ImageIcon("img\\t\\bomb_3.gif").getImage();
    BallJPanel() {
    }
    BallJPanel(int w, int h) {
        //background = new Ball(0, 0, w, h, img_back);
        //初始化背景图
        backList.add(new Ball(0, 0, w, h, img_back));
        backList.add(new Ball(0, 1080, w, h, img_back2));
        backList.add(new Ball(0, 1080 * 2, w, h, img_back3));
        //初始化我机图
        airPlane = new Ball(w / 2, h - 100, 70, 70, img_hero);
        //初始化boss
        //boss = new Boss(new Random().nextInt(1200), 0, 100, 100, img_boss);
        //初始化deatobss
        //deathBoss = new Boss(0, 0, 100, 100, img_bomb);
        //添加鼠标监听事件
        // 添加鼠标移动监听  第一个this代表当前面板  第二个this 代表鼠标
        this.addMouseMotionListener(this);
        //鼠标监听
        this.addMouseListener(this);
    }
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        // background.drawImage(g);
        //画背景
        for (int i = 0; i < backList.size(); i++) {
            backList.get(i).drawImage(g);
        }
        //画我机
        airPlane.drawImage(g);
        //画子弹
        for (int i = 0; i < fireList.size(); i++) {
            fireList.get(i).drawImage(g);
        }
        //画敌机
        for (int i = 0; i < enmeyList.size(); i++) {
            enmeyList.get(i).drawImage(g);
        }
        //画死亡敌机
        for (int i = 0; i < deathList.size(); i++) {
            deathList.get(i).drawImage(g);
        }
        //画boss
        for (int i = 0; i < boss.size(); i++) {
            boss.get(i).drawImage(g);
        }
        //画boss子弹
        for (int i = 0; i < boss_fire.size(); i++) {
            boss_fire.get(i).drawImage(g);
        }
        //画boss子弹爆炸
        for (int i = 0; i < boss_fire_bomb.size(); i++) {
            boss_fire_bomb.get(i).drawImage(g);
        }
        //画deathboss
        for (int i = 0; i < deathBoss.size(); i++) {
            deathBoss.get(i).drawImage(g);
        }
        //画道具
        for (int i = 0; i < prop.size(); i++) {
            prop.get(i).drawImage(g);
        }
        g.setColor(Color.red);
        g.setFont(new Font("楷体", Font.BOLD, 18));
        g.drawString("生命：" + String.valueOf(5 - num), 700, 30);
        g.setColor(Color.blue);
        g.drawString("积分：" + String.valueOf(sum), 700, 60);
        g.setColor(Color.black);
        g.drawString("难度：" + String.valueOf(10 - speed / 5), 700, 90);
        if (isOver) {
            g.drawImage(img_over, 200, 400, 400, 400, null);
            g.setFont(new Font("楷体", Font.BOLD, 25));
            g.setColor(Color.white);
            g.fillRect(300, 830, 110, 30);
            g.fillRect(450, 830, 70, 30);
            g.setColor(Color.red);
            g.drawString("Restart", 300, 850);
            g.drawString("Exit", 450, 850);
        }
    }
    /**
     * 鼠标拖动时执行
     *
     * @param e
     */
    @Override
    public void mouseDragged(MouseEvent e) {
        airPlane.setX(e.getX() - 35);
        airPlane.setY(e.getY() - 35);
        repaint();
    }
    @Override
    public void run() {
        int count = 0;
        int Sw = 40; //子弹大小
        int Offset = 2; //子弹威力
        int shotSpeed = 2; //子弹速度
        int generatShotSpeed = 20; //子弹产生速度
        speed = 50; //控制敌机产生速度
        nandu = 50;
        nandu2 = 2;
        Random rd = new Random();
        while (!isOver) {
            //产生子弹
            count++;
            if (count == Integer.MAX_VALUE) {
                count = 0;
            }
            if (isFire && count % generatShotSpeed == 0) {

                Ball ball = new Ball(airPlane.getX() + 17, airPlane.getY() - 20, Sw, Sw, img_fire);
                fireList.add(ball);
            }
            //子弹移动
            for (int i = 0; i < fireList.size(); i++) {
                Ball fb = fireList.get(i);
                //移动
                fb.minusY(shotSpeed);
                //清除子弹
                if (fb.getY() < -40) {
                    fireList.remove(i);
                }
            }
            //产生敌机
            if (count % speed == 0) {
                Ball el = new Ball(rd.nextInt(1000), 0, 40, 40, img_enemy);
                enmeyList.add(el);
            }
            //敌机移动
            for (int i = 0; i < enmeyList.size(); i++) {
                //移动
                enmeyList.get(i).enemyPlaneMove();
                //清除敌机
                if (enmeyList.get(i).getY() > 1300) {
                    enmeyList.remove(i);
                }
            }
            //产生boss
            if (count % 1000 == 0) {
                int w = rd.nextInt(100) + 100;
                Boss b = new Boss(new Random().nextInt(900), 0, w, w, w - 100, img_boss);
                boss.add(b);
            }
            //boss移动
            for (int i = 0; i < boss.size(); i++) {
                //boss子弹
                if (boss.size() != 0 && count % 50 == 0) {
                    Ball ball = new Ball(boss.get(i).getX() + 70, boss.get(i).getY() + 50, 40, 40, img_boss_fire);
                    boss_fire.add(ball);
                }
                //移动
                boss.get(i).bossMove();
                if (boss.get(i).getY() > 1300) {
                    boss.get(i).setXY();
                }
                //我机遇上boss
                if (airPlane.isT(boss.get(i))) {
                    Boss B = boss.get(i);
                    B.setImg(img_bomb);
                    deathBoss.add(B);
                    //boss消失
                    boss.remove(i);
                    //我机掉血
                    num += 2;
                }
            }
            //boss子弹移动
            for (int i = 0; i < boss_fire.size(); i++) {
                Ball fb = boss_fire.get(i);
                //移动
                fb.addY();
                //清除子弹
                if (fb.getY() > 1080) {
                    boss_fire.remove(i);
                }
            }
            //boss子弹遇上我机子弹
            for (int i = 0; i < boss_fire.size(); i++) {
                for (int j = 0; j < fireList.size(); j++) {
                    if (boss_fire.get(i).isT(fireList.get(j))){
                        fireList.get(j).setImg(img_bomb);
                        deathList.add(fireList.get(j));

                        fireList.remove(j);
                        boss_fire.remove(i);
                        break;
                    }
                }
            }
            //boss子弹撞上我机
            for (int i = 0; i < boss_fire.size(); i++) {
                if (boss_fire.get(i).isT(airPlane)) {
                    boss_fire.get(i).setImg(img_boss_fire_bomb);
                    boss_fire.get(i).setX(airPlane.getX()-20);
                    boss_fire.get(i).setY(airPlane.getY()-40);
                    boss_fire.get(i).setW(200);
                    boss_fire.get(i).setH(200);

                    boss_fire_bomb.add(boss_fire.get(i));
                    boss_fire.remove(i);
                    num++;
                }
            }
            //产生道具
            if (count % 2000 == 0) {
                Ball b_prop = new Ball(new Random().nextInt(900), 0, 100, 100, img_prop);
                prop.add(b_prop);
                // System.out.println("产生道具");
            }
            //道具移动
            for (int i = 0; i < prop.size(); i++) {
                prop.get(i).enemyPlaneMove();

                //我机遇到道具
                if (airPlane.isT(prop.get(0))) {
                    Offset += 2;
                    shotSpeed += 4;
                    if (generatShotSpeed != 4) {
                        generatShotSpeed -= 4;
                    }
                    if (Sw < 100) {
                        Sw += 25;
                    }
                    prop.remove(0);
                }
                if (prop.size() != 0) {
                    if (prop.get(i).getY() > 1200) {
                        prop.remove(i);
                    }
                }
            }
            //子弹遇到敌机
            for (int i = 0; i < fireList.size(); i++) {
                //子弹遇到敌机
                for (int j = 0; j < enmeyList.size(); j++) {
                    if (fireList.get(i).isT(enmeyList.get(j))) {
                        //把爆炸图加载到死亡敌机集合
                        Ball ball = enmeyList.get(j);
                        ball.setImg(img_bomb);
                        deathList.add(ball);
                        //相遇 则子弹和敌机都消失
                        fireList.remove(i);
                        enmeyList.remove(j);
                        //消灭一个敌机加10分
                        sum += 10;
                        if ((sum % nandu == 0) && (speed != 5)) {
                            //每消灭50架敌机 敌机产生速度加快一次
                            speed -= 5;
                            nandu = nandu + 50 * nandu2;
                            nandu2 += 2;
                        }
                        break;
                    }
                }
            }
            for (int i = 0; i < fireList.size(); i++) {
                Ball bom = fireList.get(i);
                //子弹遇上boss
                for (int j = 0; j < boss.size(); j++) {
                    if (bom.isT(boss.get(j))) {
                        boss.get(j).minusLife(Offset);

                        //把爆炸子弹加入死亡战机集合显示
                        bom.setImg(img_boss_bomb);
                        bom.setW(100);
                        bom.setH(100);
                        deathBoss.add(bom);
                        // System.out.println("sad   :"+boss.get(j).getLife());
                        //boss生命小于0时
                        if (boss.get(j).getLife() < 0) {
                            boss.get(j).setImg(img_boss_bomb);
                            deathBoss.add(boss.get(j));
                            boss.remove(j);
                        }
                        //移除相遇的子弹
                        fireList.remove(i);
                        break;
                    }
                }
            }
            //我机撞上敌机
            for (int i = 0; i < enmeyList.size(); i++) {
                if (airPlane.isT(enmeyList.get(i))) {
                    Ball ball = enmeyList.get(i);
                    ball.setImg(img_bomb);
                    deathList.add(ball);
                    enmeyList.remove(i);
                    num++;
                }
            }
            //隔一段时间让爆炸消失
            for (int i = 0; i < deathList.size(); i++) {
                if (count % 30 == 0) {
                    deathList.remove(i);
                }
            }
            for (int i = 0; i < deathBoss.size(); i++) {
                if (count % 30 == 0) {
                    deathBoss.remove(i);
                }
            }
            for (int i = 0; i < boss_fire_bomb.size(); i++) {
                if (count % 30 == 0) {
                    boss_fire_bomb.remove(i);
                }
            }
            //如果我机被装5次，gameover
            if (num >= 5) {

                isOver = true; //游戏结束
                //移除鼠标监听
                this.removeMouseMotionListener(this);
                num = 5;
                repaint();
                break;
            }
            //背景图移动
            for (int i = 0; i < backList.size(); i++) {
                Ball bg = backList.get(i);
                if (bg.getY() > -1080) {
                    bg.setY(bg.getY() - 1);
                } else {
                    bg.setY(1080 * (backList.size() - 1));
                }
            }
            try {
                Thread.sleep(5);
                repaint();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    @Override
    public void mouseClicked(MouseEvent e) {
        if (isOver) {
            if (e.getX() > 300 && e.getX() < 410 && e.getY() > 830 && e.getY() < 860) {
                //初始化所有参数
                this.sum = 0; //积分
                this.num = 0; //我机能被撞的次数
                this.isFire = false;  //是否发射
                this.isOver = false;  //是否over
                this.addMouseMotionListener(this); //添加鼠标监听事件
                enmeyList.clear();
                fireList.clear();
                deathList.clear();
                boss.clear();
                deathBoss.clear();
                boss_fire.clear();
                boss_fire_bomb.clear();
                this.startRun();
            }
            if (e.getX() > 450 && e.getX() < 520 && e.getY() > 830 && e.getY() < 860) {
                System.exit(0);
            }
        }
    }
    /**
     * 鼠标移动时执行
     *
     * @param e
     */
    @Override
    public void mouseMoved(MouseEvent e) {
        airPlane.setX(e.getX() - 35);
        airPlane.setY(e.getY() - 35);
        repaint();
    }
    public void startRun() {
        Thread t = new Thread(this);
        t.start();
    }
    @Override
    public void mousePressed(MouseEvent e) {
        //按下发射
        isFire = true;
    }
    @Override
    public void mouseReleased(MouseEvent e) {
        //松开停止发射
        isFire = false;
    }
    @Override
    public void mouseEntered(MouseEvent e) {
    }
    @Override
    public void mouseExited(MouseEvent e) {
    }
    public boolean isOver() {
        return isOver;
    }
    public void setOver(boolean over) {
        isOver = over;
    }
}
