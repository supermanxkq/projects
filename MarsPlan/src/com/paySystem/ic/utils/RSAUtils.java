package com.paySystem.ic.utils;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.interfaces.RSAPrivateKey;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.Cipher;

import sun.misc.BASE64Decoder;

/** 
 * @creator  作者 :杰
 * @version  创建时间：2011-12-20 下午03:29:38 
 * 类说明   
 */
public class RSAUtils {
	  /**
     * 算法名称
     */
    private final static String RSA = "RSA";
    
    /**
     * 加密后的字节分隔长度
     */
    private final static int encryptSepLength = 256;
    
    /**
     * 明文字节分隔长度
     */
    private final static int plainSepLneght = 100; 

    private static byte[] encrypt(byte[] text, PublicKey pubRSA)
            throws Exception {
        Cipher cipher = Cipher.getInstance(RSA);
        cipher.init(Cipher.ENCRYPT_MODE, pubRSA);
        return cipher.doFinal(text);
    }

    public final static String encrypt(String text, PublicKey uk) {
        StringBuffer sbf = new StringBuffer(200);
        try {
            text = URLEncoder.encode(text, "UTF-8");//用这个的原因是为了支持汉字、汉字和英文混排,解密方法中同理
            byte[] plainByte = text.getBytes();
            ByteArrayInputStream bays = new ByteArrayInputStream(plainByte);
            byte[] readByte = new byte[plainSepLneght];
            int n = 0;
            //这个位置很恶心人的写了一堆，是为了支持超过117字节，我每次加密100字节。
            while ((n = bays.read(readByte)) > 0) {
                if (n >= plainSepLneght) {
                    sbf.append(byte2hex(encrypt(readByte, uk)));
                } else {
                    byte[] tt = new byte[n];
                    for (int i = 0; i < n; i++) {
                        tt[i] = readByte[i];
                    }
                    sbf.append(byte2hex(encrypt(tt, uk)));
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return sbf.toString();
    }

    public final static String decrypt(String data, PrivateKey rk) {
        String rrr = "";
        StringBuffer sb = new StringBuffer(100);
        try {
            ByteArrayInputStream bais = new ByteArrayInputStream(
                    data.getBytes());
             //此处之所以是 256，而不是128的原因是因为有一个16进行的转换，所以由128变为了256
             byte[] readByte = new byte[256];
            int n = 0;
            while ((n = bais.read(readByte)) > 0) {
                if (n >= encryptSepLength) {
                    sb.append(new String(decrypt(hex2byte(readByte), rk)));
                } else {

                }
            }
            rrr = URLDecoder.decode(sb.toString(), "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rrr;
    }

    private static byte[] decrypt(byte[] src, PrivateKey rk) throws Exception {
        Cipher cipher = Cipher.getInstance(RSA);
        cipher.init(Cipher.DECRYPT_MODE, rk);
        return cipher.doFinal(src);
    }

    public static String byte2hex(byte[] b) {
        String hs = "";
        String stmp = "";
        for (int n = 0; n < b.length; n++) {
            stmp = Integer.toHexString(b[n] & 0xFF);
            if (stmp.length() == 1)
                hs += ("0" + stmp);
            else
                hs += stmp;
        }
        return hs.toUpperCase();
    }

    public static byte[] hex2byte(byte[] b) {
        if ((b.length % 2) != 0)
            throw new IllegalArgumentException("长度不是偶数");
        byte[] b2 = new byte[b.length / 2];
        for (int n = 0; n < b.length; n += 2) {
            String item = new String(b, n, 2);
            b2[n / 2] = (byte) Integer.parseInt(item, 16);
        }
        return b2;
    }

    public static PrivateKey getPrivateKey() {
        try {
            FileInputStream f = new FileInputStream("c://Skey_RSA_priv.dat");
            ObjectInputStream b = new ObjectInputStream(f);
            RSAPrivateKey prk = (RSAPrivateKey) b.readObject();
            return prk;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

   
    public static PublicKey getPublicKey(String pubKey) {
        try {
            String publicKeyStr =pubKey;
            byte[] keyBytes;
            keyBytes = new BASE64Decoder().decodeBuffer(publicKeyStr);
            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            PublicKey publicKey = keyFactory.generatePublic(keySpec);
            return publicKey;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
