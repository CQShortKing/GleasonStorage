package ThunderFighter;

import javax.swing.*;
public class BallJFrame extends JFrame {
    private BallJPanel jPanel;
    public BallJFrame(){
        jPanel=new BallJPanel(900,1080);
        this.add(jPanel);
    }
    public void showJF(){
        this.setSize(900,1080);
        this.setTitle("雷电游戏plus");
        this.setResizable(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jPanel.startRun();
        this.setVisible(true);
    }
    public static void main(String[] args) {
        BallJFrame jFrame=new BallJFrame();
        jFrame.showJF();
    }
}
