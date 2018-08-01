package SeedGrown;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Stack;

/**
 * @创建人：黄强
 * @时间 ：2018/7/25 21:21
 * @描述 ：
 */
public class DPFGrown {
    int RGB;
    Color[][]colorArray;
    BufferedImage outputImage;
    byte[][]sameClassPricessMap;
    public DPFGrown(int RGB,Color[][]colorArray,byte[][]sameClassPricessMap,BufferedImage outputImage){
        this.RGB=RGB;
        this.colorArray=colorArray;
        this.outputImage=outputImage;
        this.sameClassPricessMap=sameClassPricessMap;
    }
    /**
    *@描述 :
     *@参数 :
     *@返回值:
     *@创建人 : 黄强
     *@创建时间  2018/7/25 23:43
     *@修改人和其它信息：
     *@版本：
     */

    public void DPFGrown( Seed seed,  byte[][]processMap){
        byte []direction={0,0,-1,1};
        Stack<Seed> seedStack=new Stack<>();
        seedStack.add(seed);
        while (!seedStack.isEmpty()){
            Seed popSeed=seedStack.pop();
            int x=popSeed.getX();
            int y=popSeed.getY();
            processMap[x][y]=1;
            for (int k=0;k<4;k++){
                int nextX=x+direction[k];
                int nextY=y+direction[3-k];
                if (nextX>=0&&nextX<Util.width&&nextY>=0&&nextY<Util.height&&processMap[nextX][nextY]==0){
                    if (isSimilar(nextX,nextY,seed)){
                        seedStack.add(new Seed(nextX,nextY,colorArray[nextX][nextY].getRGB()));
                    }else {
                       // processMap[nextX][nextY]=1;
                        for (int i=nextX-1;i<nextX+1;i++){
                            for (int j=nextY-1;j<nextY+1;j++){
                                if (i>=0&&i<Util.width&&j>=0&&j<Util.height){
                                    outputImage.setRGB(i,j,RGB);
                                }
                            }
                        }
                    }
                }
            }
        }
    }
    public boolean isSimilar(int x,int y,Seed seed){
        int distance=(int) (Math.pow(colorArray[x][y].getRed()-seed.getRed(),2)+
                Math.pow(colorArray[x][y].getGreen()-seed.getGreen(),2)+
                Math.pow(colorArray[x][y].getBlue()-seed.getBlue(),2));
        if (distance<DivideClass.T2){
            sameClassPricessMap[x][y]=1;
        }

        if(distance<seed.getBestThreshold()){
            return true;
        }
        else return false;
    }
}
