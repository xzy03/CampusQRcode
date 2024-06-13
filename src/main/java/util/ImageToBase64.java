package util;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;

public class ImageToBase64 {
    public static byte[] encodeImageToBase64(String imagePath) throws IOException {
        byte[] fileContent = Files.readAllBytes(Paths.get(imagePath));
        System.out.println(fileContent);
        System.out.println(Base64.getEncoder().encode(fileContent));
        System.out.println(Base64.getEncoder().encodeToString(fileContent));
        return Base64.getEncoder().encode(fileContent);
    }

    // 示例的 main 方法，展示如何使用 encodeImageToBase64 方法
    public static void main(String[] args) {
        try {
            String imagePath = "C:\\Users\\86173\\IdeaProjects\\mytest\\src\\main\\webapp\\QRCode_Mybooking\\qrcode_login.jpg";
            byte[] base64String = encodeImageToBase64(imagePath);
            for (byte b : base64String) {
                // 判断是否为可打印字符
                if (b >= 32 && b <= 126) {
                    System.out.print((char) b);
                } else {
                    System.out.print('.'); // 非打印字符用.代替
                }
            }
            System.out.println(base64String);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
