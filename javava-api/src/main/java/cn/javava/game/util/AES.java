package cn.javava.game.util;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 * 供数据库加密解密用，加密解密对维护也会带来不便，请酌情使用。
 *
 * @author piggy.zhu
 *
 */
public final class AES
{
    static final String SECRET = "12345678901234561234567890123456";

    static final SecureRandom SECURE_RANDOM = new SecureRandom();

    public static String decrypt(final String input) throws AESException
    {
        if (StringUtils.isBlank(input))
        {
            return null;
        }
        try
        {
            byte[] encryptedBytes = Base64.decodeBase64(input);

            byte[] ivBytes = new byte[16];
            System.arraycopy(encryptedBytes, 0, ivBytes, 0, 16);

            byte[] contentBytes = new byte[encryptedBytes.length - 16];
            System.arraycopy(encryptedBytes, 16, contentBytes, 0, encryptedBytes.length - 16);

            SecretKeySpec key = new SecretKeySpec(AES.SECRET.getBytes(StandardCharsets.US_ASCII), "AES");
            IvParameterSpec iv = new IvParameterSpec(ivBytes);

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, key, iv);
            return new String(cipher.doFinal(contentBytes), Charset.forName("utf-8"));
        }
        catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | BadPaddingException | IllegalBlockSizeException | InvalidAlgorithmParameterException e)
        {
            throw new AESException(e);
        }
    }

    public static String encrypt(final String input) throws AESException
    {
        if (StringUtils.isBlank(input))
        {
            return null;
        }
        try
        {
            //如果使用/dev/random获取种子数，可能会导致很慢
            byte[] newSeed = SECURE_RANDOM.generateSeed(16);
            SECURE_RANDOM.setSeed(newSeed);
            byte[] ivBytes = new byte[16];
            SECURE_RANDOM.nextBytes(ivBytes);

            IvParameterSpec iv = new IvParameterSpec(ivBytes);
            SecretKeySpec key = new SecretKeySpec(AES.SECRET.getBytes(), "AES");

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, key, iv);
            byte[] contentBytes = cipher.doFinal(input.getBytes(StandardCharsets.UTF_8));

            byte[] encryptedBytes = new byte[ivBytes.length + contentBytes.length];
            System.arraycopy(ivBytes, 0, encryptedBytes, 0, ivBytes.length);
            System.arraycopy(contentBytes, 0, encryptedBytes, ivBytes.length, contentBytes.length);

            return Base64.encodeBase64String(encryptedBytes);
        }
        catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | BadPaddingException | IllegalBlockSizeException | InvalidAlgorithmParameterException e)
        {
            throw new AESException(e);
        }
    }

    public static void main(String[] args) throws Exception
    {
        String data = AES.encrypt("helloworld");
        System.out.println("加密：" + data);
        System.out.println("解密：" + decrypt(data));
    }

    private AES()
    {
    }

}
