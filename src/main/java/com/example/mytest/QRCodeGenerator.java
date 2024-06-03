package com.example.mytest;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.HashMap;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;
import java.io.File;
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
    public static void generateInvalidQRCode(String filePath) {
        try {
            // 生成包含无效数据的二维码内容
            String invalidData = "INVALID_DATA_!@#$%^&*()";

            // 创建一个矩阵对象，用于表示生成的二维码图像
            BitMatrix bitMatrix = new MultiFormatWriter().encode(invalidData, BarcodeFormat.QR_CODE, WIDTH, HEIGHT, null);

            // 创建一个灰色背景的图像
            BufferedImage bufferedImage = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
            Graphics2D g2d = bufferedImage.createGraphics();
            g2d.setColor(Color.white); // 设置背景色为灰色
            g2d.fillRect(0, 0, WIDTH, HEIGHT);

            // 绘制二维码
            for (int x = 0; x < WIDTH; x++) {
                for (int y = 0; y < HEIGHT; y++) {
                    bufferedImage.setRGB(x, y, bitMatrix.get(x, y) ? Color.lightGray.getRGB() : Color.white.getRGB());
                }
            }

            // 绘制禁止符号
            int circleDiameter = WIDTH - 95;
            int circleX = (WIDTH - circleDiameter) / 2;
            int circleY = (HEIGHT - circleDiameter) / 2;

            g2d.setStroke(new BasicStroke(20)); // 设置线宽
            g2d.setColor(Color.DARK_GRAY); // 设置禁止符号颜色为红色
            g2d.draw(new Ellipse2D.Double(circleX, circleY, circleDiameter, circleDiameter)); // 绘制圆圈

            // 计算斜线的起点和终点，使其在圆圈内
            double lineStartX = circleX + (circleDiameter * 0.2);
            double lineStartY = circleY + (circleDiameter * 0.2);
            double lineEndX = circleX + (circleDiameter * 0.8);
            double lineEndY = circleY + (circleDiameter * 0.8);

            g2d.draw(new Line2D.Double(lineStartX, lineStartY, lineEndX, lineEndY)); // 绘制斜线

            g2d.dispose();

            // 确保目录存在
            File outputFile = new File(filePath);
            File parentDir = outputFile.getParentFile();
            if (!parentDir.exists()) {
                parentDir.mkdirs();
            }

            // 将图像保存为文件
            ImageIO.write(bufferedImage, "png", outputFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
