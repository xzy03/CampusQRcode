import util.QRCodeUtil;

/**
 * @author bai <br/>
 * @date 2020/7/1 9:31<br/>
 */
public class QRCodeApplication {
    public static void main(String[] args) throws Exception {
        // 存放在二维码中的内容
        // 二维码中的内容可以是文字，可以是链接等ip地址可能会变
//        String text = "http://10.81.11.132:8080/mytest_war_exploded/chose.jsp";
        String text = "http://192.168.16.246:8080/mytest_war_exploded/chose.jsp";
        // 嵌入二维码的图片路径
        //String imgPath = "C:\\Users\\Administrator\\Pictures\\img\\dog.jpg";
        // 生成的二维码的路径及名称
        String destPath = "C:\\Users\\86173\\IdeaProjects\\mytest\\src\\main\\webapp\\QRCode_Mybooking\\" + "qrcode_login" + ".jpg";
        //生成二维码
        QRCodeUtil.encode(text, null, destPath, true);
        // 解析二维码
        String str = QRCodeUtil.decode(destPath);
        // 打印出解析出的内容
        System.out.println(str);
    }
}