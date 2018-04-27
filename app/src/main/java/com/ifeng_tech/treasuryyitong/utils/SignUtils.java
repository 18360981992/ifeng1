package com.ifeng_tech.treasuryyitong.utils;

import java.io.IOException;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/**
 * MD5加密
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
}
