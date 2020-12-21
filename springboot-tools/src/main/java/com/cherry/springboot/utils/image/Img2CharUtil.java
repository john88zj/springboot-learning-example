package com.cherry.springboot.utils.image;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;
import java.io.*;

/**
 * @Description 图片工具类，完成图片的截取   所有方法返回值均未boolean型
 * @Author zhujun
 * @Email
 * @Date 2020/3/17  10:00 AM
 * @Version
 **/
public class Img2CharUtil {

    final static String base = "@#&$%*o!;.";// 字符串由复杂到简单


    /**
     * @param imgPath 图片路径
     * @param savePath 存放路径
     */
    public static String createAsciiPic(String imgPath,String savePath) throws Exception {
        String ret = "";
        final String base = " @#X&$%*o!;.";// 字符串由复杂到简单
//        final String base = "#8XOHLTI)i=+;:,. ";// 字符串由复杂到简单
        try {
            BufferedImage image = ImageIO.read(new File(imgPath));  //读取图片
            //输出到指定文件中
            BufferedWriter fos = new BufferedWriter(new FileWriter(savePath,false));   //true表示是否追加
            for (int y = 0; y < image.getHeight(); y += 2) {
                for (int x = 0; x < image.getWidth(); x++) {
                    int pixel = image.getRGB(x, y);
                    int r = (pixel & 0xff0000) >> 16, g = (pixel & 0xff00) >> 8, b = pixel & 0xff;
                    float gray = 0.299f * r + 0.578f * g + 0.114f * b;
                    int index = Math.round(gray * (base.length() + 1) / 255);
                    String s = index >= base.length() ? " " : String.valueOf(base.charAt(index));
                    System.out.print(s);
                    fos.write(s);
                    ret +=s;
                }
                fos.newLine();
                System.out.println();
                ret +="\n";
            }
            fos.close();
        } catch (final IOException e) {
            e.printStackTrace();
        }
        return ret;
    }



    public static void main(String[] args) {
        String path = "/Users/zhujun/Downloads/logo.png";
        String txtPath = "/Users/zhujun/Downloads/logo.txt";
        String s = "";
        try {
//            s = transfer(path);
            s = createAsciiPic(path,txtPath);
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("========");
        System.out.println(s);
        System.out.println("========");
    }
}
