package com.example.mytest;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.HashMap;

public class QRCodeGenerator {

    private static final int WIDTH = 300;
    private static final int HEIGHT = 300;

    public static void main(String[] args) {
        // 用户表单数据
        HashMap<String, String> formData = new HashMap<>();
        formData.put("name", "张三");
        formData.put("age", "25");

        // 生成二维码
        generateQRCode(formData);
    }

    public static void generateQRCode(HashMap<String, String> formData) {
        try {
            // 创建一个矩阵对象，用于表示生成的二维码图像
            BitMatrix bitMatrix = new MultiFormatWriter().encode(formData.toString(), BarcodeFormat.QR_CODE, WIDTH, HEIGHT, null);

            BufferedImage bufferedImage = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);

            // 迭代矩阵的每个像素点，设置颜色值
            for (int x = 0; x < WIDTH; x++) {
                for (int y = 0; y < HEIGHT; y++) {
                    bufferedImage.setRGB(x, y, bitMatrix.get(x, y) ? Color.BLACK.getRGB() : Color.WHITE.getRGB());
                }
            }

            // 将图像保存为文件
            File outputFile = new File("mytest_war_exploded/src/main/webapp/QRCode/qrcode.png");
            ImageIO.write(bufferedImage, "png", outputFile);
            System.out.println(outputFile.getParentFile());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void generateQRCode(HashMap<String,String> formData,String path){
        try {
            // 创建一个矩阵对象，用于表示生成的二维码图像
            BitMatrix bitMatrix = new MultiFormatWriter().encode(formData.toString(), BarcodeFormat.QR_CODE, WIDTH, HEIGHT, null);

            BufferedImage bufferedImage = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);

            // 迭代矩阵的每个像素点，设置颜色值
            for (int x = 0; x < WIDTH; x++) {
                for (int y = 0; y < HEIGHT; y++) {
                    bufferedImage.setRGB(x, y, bitMatrix.get(x, y) ? Color.BLACK.getRGB() : Color.WHITE.getRGB());
                }
            }

            // 将图像保存为文件
            File outputFile = new File(path);
            ImageIO.write(bufferedImage, "png", outputFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
