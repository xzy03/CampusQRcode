package system;
import org.bouncycastle.crypto.BufferedBlockCipher;
import org.bouncycastle.crypto.engines.SM4Engine;
import org.bouncycastle.crypto.modes.CBCBlockCipher;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.util.encoders.Hex;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.Security;
public class SM4Util {
    static {
        Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
    }
    //生成一个SM4加密算法的密钥并且是一个全局的密钥
    private static byte[] key = Hex.decode("0123456789abcdeffedcba9876543210");
    //加密
    public static String encrypt(String input) {
        try {
            byte[] inputData = input.getBytes(StandardCharsets.UTF_8);
            Cipher cipher = Cipher.getInstance("SM4/ECB/PKCS5Padding", "BC");
            cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(key, "SM4"));
            byte[] outputData = cipher.doFinal(inputData);
            return Hex.toHexString(outputData);
        } catch (Exception e) {
            e.printStackTrace();
        }
            return null;
        }
    //解密
    public static String decrypt(String input) {
        try {
            byte[] inputData = Hex.decode(input);
            Cipher cipher = Cipher.getInstance("SM4/ECB/PKCS5Padding", "BC");
            cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(key, "SM4"));
            byte[] outputData = cipher.doFinal(inputData);
            return new String(outputData, StandardCharsets.UTF_8);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        String input = "330104200311185019";
        String encrypt = encrypt(input);
        System.out.println("加密后：" + encrypt);
        System.out.println("解密后：" + decrypt(encrypt));
    }

}
