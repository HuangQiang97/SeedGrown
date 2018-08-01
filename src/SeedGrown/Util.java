package SeedGrown;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;

/**
 * @创建人：黄强
 * @时间 ：2018/7/25 11:11
 * @描述 ：
 */
public class Util {
    static int width;
    static int height;
    static  BufferedImage inputImage;
/*
*
 *@描述 :
 *@参数 :
 *@返回值:
 *@创建人 : 黄强
 *@创建时间  2018/7/26 9:46
 *@修改人和其它信息：
 *@版本：
 */

  
    public static Color[][]getColorArray(String path){
     
        

        try {
            inputImage=ImageIO.read(new File(path));
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("文件读取出错！请检查路径是否正确!");
        }
        width=inputImage.getWidth();
        height=inputImage.getHeight();
        Color colorArray[][]=new Color[width][height];
        for (int i=0;i<width;i++){
            for (int j=0;j<height;j++){
                colorArray[i][j]=new Color(inputImage.getRGB(i,j));
            }
        }
        return colorArray;
    }
   // public void outputResult(LinkedList<Seed>seedList,int RGB)
}
