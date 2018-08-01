package SeedGrown;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.LinkedList;
public class Main {
    public static void main(String[] args) {
        String filePath="inputImage\\Pic_4.png";

        Color[][] colorArray=Util.getColorArray(filePath);

        LinkedList<Seed> tempSeedList=new DivideClass(20000,10000).divideClass(colorArray);
        long startTime=System.currentTimeMillis();
        LinkedList<Seed> seedList_1=new LinkedList<>();
        LinkedList<Seed> seedList_2=new LinkedList<>();
        LinkedList<Seed> seedList_3=new LinkedList<>();
        LinkedList<Seed> seedList_0=new LinkedList<>();
        for (int i=0;i<tempSeedList.size();i++){
            if (i%4==0){
                seedList_0.add(tempSeedList.get(i));
            }else {
                if (i%4==1){
                    seedList_1.add(tempSeedList.get(i));
                }else {
                    if (i%4==2){
                        seedList_2.add(tempSeedList.get(i));
                    }else {
                       seedList_3.add(tempSeedList.get(i));
                    }
                }
            }
        }
        Thread thread_0=new Thread(new GetThreshold(seedList_0,colorArray));
        Thread thread_1=new Thread(new GetThreshold(seedList_1,colorArray));
        Thread thread_2=new Thread(new GetThreshold(seedList_2,colorArray));
        Thread thread_3=new Thread(new GetThreshold(seedList_3,colorArray));
        thread_0.start();
        thread_1.start();
        thread_2.start();;
        thread_3.start();
        try {
            thread_0.join();
            thread_1.join();
            thread_2.join();
            thread_3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
            throw new RuntimeException("多线程处理阈值时异常！");
        }
        for (Seed seed:tempSeedList
             ) {
            System.out.println(seed.getX()+" "+seed.getY()+" "+seed.getBestThreshold());
        }
        System.out.println("选取阈值耗时："+(System.currentTimeMillis()-startTime)+"ms");
        int width=Util.width;
        int height=Util.height;
        BufferedImage outputImage=Util.inputImage;
        Date date=new Date();
        byte [][]processMap=new byte[width][height];
        byte[][]sameClassPeocessMap=new byte[width][height];

        DPFGrown dpfGrown=new DPFGrown(0x0,colorArray,sameClassPeocessMap,outputImage);
        for (Seed seed:tempSeedList
             ) {
            dpfGrown.DPFGrown(seed,processMap);
        }
        try {
            ImageIO.write(outputImage,"jpg",new File(".\\Result\\DividedPic\\"+date.getHours()+date.getMinutes()+".jpg"));
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("生长文件无法写出！");
        }
    }
}
