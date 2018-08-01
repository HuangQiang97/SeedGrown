package SeedGrown.Tests;

import java.io.*;

/**
 * @创建人：黄强
 * @时间 ：2018/7/25 12:46
 * @描述 ：
 */
public class TestFunction {
    public static void main(String[] args) {
        try {
            FileOutputStream file=new FileOutputStream(".\\Result\\abc.txt");
            file.write("abc".getBytes());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
