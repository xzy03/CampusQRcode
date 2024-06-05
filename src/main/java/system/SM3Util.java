package system;

import org.bouncycastle.crypto.digests.SM3Digest;
import org.bouncycastle.util.encoders.Hex;

public class SM3Util {

//加密后字符串十六进制表示
    public static String encrypt(String input) {
        byte[] inputData = input.getBytes();
        SM3Digest digest = new SM3Digest();
        digest.update(inputData, 0, inputData.length);
        byte[] hash = new byte[digest.getDigestSize()];
        digest.doFinal(hash, 0);
        return Hex.toHexString(hash);
    }
}
