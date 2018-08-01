package SeedGrown;

import java.awt.*;
import java.util.LinkedList;

/**
 * @创建人：黄强
 * @时间 ：2018/7/25 14:49
 * @描述 ：
 */
public class GetThreshold  implements Runnable{
    LinkedList<Seed> seedList;
    Color[][]colorArray;
    public GetThreshold(LinkedList<Seed> seedList,Color[][]colorArray) {
        this.colorArray = colorArray;
        this.seedList = seedList;
    }
    public void run(){
        int width= Util.width;
        int height=Util.height;
        for (Seed seed:seedList
                ) {
            int maxVariance=0;
            boolean getDistance=false;
            int bestThreshold=0;
            int [][]distanceArray=new int[width][height];
            for (int threshold=1000;threshold<300000;threshold+=100) {
                int lowRedSum=0;
                int lowBlueSum=0;
                int lowGreenSum=0;
                int lowCount=0;
                int upRedSum=0;
                int upGreenSum=0;
                int upBlueSum=0;
                int upCount=0;
                for (int i = 0; i < width; i++) {
                    for (int j = 0; j < height; j++) {
                        Color popColor = colorArray[i][j];
                        if (!getDistance) {
                            int distance = (int) (Math.pow(seed.getRed() - popColor.getRed(), 2) +
                                    Math.pow(seed.getGreen() - popColor.getGreen(), 2) +
                                    Math.pow(seed.getBlue() - popColor.getBlue(), 2));
                            distanceArray[i][j] = distance;
                        }
                        if (distanceArray[i][j] < threshold) {
                            lowCount++;
                            lowRedSum += popColor.getRed();
                            lowGreenSum += popColor.getGreen();
                            lowBlueSum += popColor.getBlue();
                        } else {
                            upCount++;
                            upRedSum += popColor.getRed();
                            upGreenSum += popColor.getGreen();
                            upBlueSum += popColor.getBlue();
                        }
                    }
                }
                        if (lowCount!=0&&upCount!=0) {
                            int avgRed = (lowRedSum + upRedSum) / (width * height);
                            int avgGreen = (lowGreenSum + upGreenSum) / (width * height);
                            int avgBlue = (lowBlueSum + upBlueSum) / (width * height);
                            int lowAvgRed = lowRedSum / lowCount;
                            int lowAvgGreen = lowGreenSum / lowCount;
                            int lowAvgBlue = lowBlueSum / lowCount;
                            int upAvgRed = upRedSum / upCount;
                            int upAvgGreen = upGreenSum / upCount;
                            int upAvgBlue = upBlueSum / upCount;
                            int variance  = (int) (((double) lowCount / (width * height))*(Math.pow(avgRed - lowAvgRed, 2) + Math.pow(avgGreen - lowAvgGreen, 2) + Math.pow(avgBlue - lowAvgBlue, 2)) +((double)upCount/(width * height))*(Math.pow(avgRed - upAvgRed, 2) +Math.pow(avgGreen - upAvgGreen, 2) + Math.pow(avgBlue - upAvgBlue, 2)));
                            if (variance > maxVariance) {
                                maxVariance = variance;
                                bestThreshold = threshold;
                            }
                }
                getDistance=true;
            }

            seed.setBestThreshhold(bestThreshold);
        }
    }
}
