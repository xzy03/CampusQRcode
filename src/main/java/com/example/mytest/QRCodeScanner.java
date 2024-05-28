package com.example.mytest;

import com.google.zxing.*;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.QRCodeReader;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class QRCodeScanner {

    public static void main(String[] args) {
        // 扫描二维码
        scanQRCode("qrcode.png");
    }

    public static void scanQRCode(String imagePath) {
        try {
            // 读取二维码图像
            BufferedImage bufferedImage = ImageIO.read(new File(imagePath));
            RGBLuminanceSource source = new RGBLuminanceSource(bufferedImage.getWidth(), bufferedImage.getHeight(), getImagePixels(bufferedImage));
            BinaryBitmap binaryBitmap = new BinaryBitmap(new HybridBinarizer(source));

            // 创建一个二维码阅读器
            Reader reader = new QRCodeReader();

            // 解码二维码图像
            Result result = reader.decode(binaryBitmap);

            // 输出解码结果
            System.out.println(result.getText());
        } catch (IOException | NotFoundException | ChecksumException | FormatException e) {
            e.printStackTrace();
        }
    }

    private static int[] getImagePixels(BufferedImage bufferedImage) {
        int width = bufferedImage.getWidth();
        int height = bufferedImage.getHeight();
        int[] pixels = new int[width * height];
        bufferedImage.getRGB(0, 0, width, height, pixels, 0, width);
        return pixels;
    }
}