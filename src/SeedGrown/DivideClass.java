package SeedGrown;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.LinkedList;

/**
 * @创建人：黄强
 * @时间 ：2018/7/25 11:41
 * @描述 ：
 */
public class DivideClass {



    static int T1;
    static int T2;

    public DivideClass(int T1,int T2) {
        this.T1=T1;
        this.T2=T2;
    }
    /**
    *@描述 :
    *@参数 :
    *@返回值:
    *@创建人 : 黄强
    *@创建时间  14:40
    *@修改人和其它信息：
    *@版本：
    */

    public LinkedList<Seed> divideClass(Color[][]colorArray){
        int index=0;
        int width=Util.width;
        int height=Util.height;
        LinkedList <Seed> seedList=new LinkedList<>();
        while (index<width*height){
            int y=index/width;
            int x=index%width;
            Color popColor=colorArray[x][y];
            if (seedList.isEmpty()){
                seedList.add(new Seed(x,y,popColor.getRGB()));
            }else
                breakPoint: {
                    byte belongCanopyFlag = 0;
                    for (Seed seed:seedList
                            ) {
                        int distance = (int)(Math.pow(seed.getRed() - popColor.getRed(), 2) +
                                Math.pow(seed.getGreen() - popColor.getGreen(), 2) +
                                Math.pow(seed.getBlue() - popColor.getBlue(), 2));
                        if (distance < T2) {
                            seed.sameClassSeed.add(new Seed(x,y,popColor.getRGB()));
                            break breakPoint;
                        } else if (distance < T1) {
                            belongCanopyFlag = 1;
                        }
                    }
                        if (belongCanopyFlag==0){
                            seedList.add(new Seed(x,y,popColor.getRGB()));
                        }
                }
            index++;
        }
        System.out.println("原始种子点数量:"+seedList.size()+"\n种子点信息:x y RGB");

        BufferedImage outputImage=new BufferedImage(100*seedList.size(),1000,BufferedImage.TYPE_3BYTE_BGR);
        for (int k=0;k<seedList.size();k++){
            for (int p=100*k;p<100*k+100;p++){
                for (int q=0;q<1000;q++){
                    outputImage.setRGB(p,q,(int)seedList.get(k).getRGB());
                }
            }
        }
        try {
            ImageIO.write(outputImage,"jpg",new File(".\\Result\\sortColor\\sortPic"+
                    new Date().getHours()+new Date().getMinutes()+".jpg"
            ));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return seedList;


    }
}
