package com.ifeng_tech.treasuryyitong.utils;

import android.util.Base64;

import java.io.IOException;
import java.net.URLEncoder;
import java.security.Key;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.IvParameterSpec;

/**
 * MD5加密  与 DES加密/解密
 */

public class SignUtils {

    public static String getSignature(Map<String, String> params, String key) throws IOException {
        // 先将参数以其参数名的字典序升序进行排序
        Map<String, String> sortedParams = new TreeMap<String, String>(params);
        Set<Map.Entry<String, String>> entrys = sortedParams.entrySet();

        // 遍历排序后的字典，将所有参数按"key=value"格式拼接在一起
        StringBuilder basestring = new StringBuilder();
        int indx = 0;
        for (Map.Entry<String, String> param : entrys) {

            if (param.getValue() != null && !"".equals(param.getValue())) {
                basestring.append(param.getKey()).append("=").append(URLEncoder.encode(param.getValue(),"utf-8"));
            }
            if (indx < (params.size() - 1)) {
                basestring.append("&");
            }
            indx++;
        }

        //basestring.append("&key=" + key);
        basestring.append(key);
//        System.out.println("MD5加密前=" + basestring.toString());
        String sign = getMD5(basestring.toString());
//        System.out.println("MD5加密后="+sign.toUpperCase().toString());
        return sign.toUpperCase();
    }

    private static final char hexDigits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
            'A', 'B', 'C', 'D', 'E', 'F'};

    public static String getMD5(String inStr) {
        byte[] inStrBytes = inStr.getBytes();
        try {
            MessageDigest MD = MessageDigest.getInstance("MD5");
            MD.update(inStrBytes);
            byte[] mdByte = MD.digest();
            char[] str = new char[mdByte.length * 2];
            int k = 0;
            for (int i = 0; i < mdByte.length; i++) {
                byte temp = mdByte[i];
                str[k++] = hexDigits[temp >>> 4 & 0xf];
                str[k++] = hexDigits[temp & 0xf];
            }
            return new String(str).toUpperCase();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }


    // 密钥 长度不得小于24
    private final static String secretKey = "123456789012345678901234" ;

    // 向量 可有可无 终端后台也要约定
    private final static String iv = "01234567" ;
    // 加解密统一使用的编码方式
    private final static String encoding = "utf-8" ;

    /**
     * 3DES加密
     *
     * @param plainText
     *            普通文本
     * @return
     * @throws Exception
     */
    public static String encode(String plainText) throws Exception {
        Key deskey = null ;
        DESedeKeySpec spec = new DESedeKeySpec(secretKey .getBytes());
        SecretKeyFactory keyfactory = SecretKeyFactory.getInstance( "desede");
        deskey = keyfactory.generateSecret(spec);

        Cipher cipher = Cipher.getInstance( "desede/CBC/PKCS5Padding");
        IvParameterSpec ips = new IvParameterSpec( iv.getBytes());
        cipher.init(Cipher. ENCRYPT_MODE , deskey, ips);
        byte [] encryptData = cipher.doFinal(plainText.getBytes(encoding ));
        return Base64.encodeToString(encryptData,Base64. DEFAULT );
    }

    /**
     * 3DES解密
     *
     * @param encryptText 普通文本
     *
     * @return
     * @throws Exception
     */
    public static String decode(String encryptText) throws Exception {
        Key deskey = null ;
        DESedeKeySpec spec = new DESedeKeySpec( secretKey.getBytes());
        SecretKeyFactory keyfactory = SecretKeyFactory.getInstance( "desede" );
        deskey = keyfactory. generateSecret(spec);
        Cipher cipher = Cipher.getInstance( "desede/CBC/PKCS5Padding" );
        IvParameterSpec ips = new IvParameterSpec( iv.getBytes());
        cipher. init(Cipher. DECRYPT_MODE, deskey, ips);

        byte [] decryptData = cipher.doFinal(Base64.decode(encryptText, Base64. DEFAULT));

        return new String (decryptData, encoding);
    }
}
