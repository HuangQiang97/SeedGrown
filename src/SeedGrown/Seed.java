package SeedGrown;

import java.util.Stack;

/**
 * @创建人：黄强
 * @时间 ：2018/7/25 11:28
 * @描述 ：
 */
public class Seed {
    private int x,y;
    private int  RGB;
    private int bestThreshold;
    Stack<Seed> sameClassSeed=new Stack<>();

    public Seed(int x, int y, int RGB) {
        this.x = x;
        this.y = y;
        this.RGB = RGB;
    }
    public Seed (){}

    public int  getRed(){
        return (int)((RGB&0xff0000)>>16);
    }
    public int getGreen(){
        return (int)((RGB&0xff00)>>8);
    }
    public int getBlue(){
        return  (int)(RGB&0xff);
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

    public long getRGB() {
        return RGB;
    }

    public void setRGB(int  RGB) {
        this.RGB = RGB;
    }

    public double getBestThreshold() {
        return bestThreshold;
    }

    public void setBestThreshhold(int bestThreshhold) {
        this.bestThreshold = bestThreshhold;
    }
}
