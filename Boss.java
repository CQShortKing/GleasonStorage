package ThunderFighter;

import java.awt.*;
public class Boss extends Ball {
    private int life;
    public void setXY(){
        this.setX(rd.nextInt(1200));
        this.setY(0);
    }
    public void minusLife(int offset){
        this.life-=offset;
        //System.out.println(this.life);
    }
    public void bossMove(){
        super.enemyPlaneMove();
    }
    public boolean isShow() {
        return isShow;
    }
    public void setShow(boolean show) {
        isShow = show;
    }
    private boolean isShow;
    Boss(){
    }
    Boss(int x, int y, int w, int h, int life,Image img){
        super(x, y, w, h, img);
        this.life=life;
    }
    public int getLife() {
        return life;
    }
    public void setLife(int life) {
        this.life = life;
    }
}

