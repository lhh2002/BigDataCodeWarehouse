package com.utils;

/**
 * Created by $(USER) on $(DATE)
 */


import cn.hutool.core.util.StrUtil;
import org.apache.tomcat.util.codec.binary.Base64;

import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SM4Utils {
//	private String secretKey = "";
//    private String iv = "";
//    private boolean hexString = false;

    public String secretKey = "";
    public String iv = "";
    public boolean hexString = false;
    public static String SM4KEY = "516881ebd97446a2bcdb83db1d462a51";

    public SM4Utils() {
    }


    public String encryptData_ECB(String plainText) {
        try {
            SM4_Context ctx = new SM4_Context();
            ctx.isPadding = true;
            ctx.mode = SM4.SM4_ENCRYPT;

            byte[] keyBytes;
            if (hexString) {
                keyBytes = Util.hexStringToBytes(secretKey);
            } else {
                //keyBytes = secretKey.getBytes();
                keyBytes = Util.hexStringToBytes(secretKey);
            }

            SM4 sm4 = new SM4();
            sm4.sm4_setkey_enc(ctx, keyBytes);
            byte[] encrypted = sm4.sm4_crypt_ecb(ctx, plainText.getBytes("UTF-8"));
            return Util.byteToHex(encrypted);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public String decryptData_ECB(String cipherText) {
        try {
            byte[] encrypted = Util.hexToByte(cipherText);
            cipherText= Base64.encodeBase64String(encrypted);;
            //cipherText = new BASE64Encoder().encode(encrypted);
            if (cipherText != null && cipherText.trim().length() > 0) {
                Pattern p = Pattern.compile("\\s*|\t|\r|\n");
                Matcher m = p.matcher(cipherText);
                cipherText = m.replaceAll("");
            }

            SM4_Context ctx = new SM4_Context();
            ctx.isPadding = true;
            ctx.mode = SM4.SM4_DECRYPT;

            byte[] keyBytes;
            if (hexString) {
                keyBytes = Util.hexStringToBytes(secretKey);
            } else {
                keyBytes = secretKey.getBytes();
            }

            SM4 sm4 = new SM4();
            sm4.sm4_setkey_dec(ctx, keyBytes);
            byte[] decrypted = sm4.sm4_crypt_ecb(ctx, Base64.decodeBase64(cipherText));
            //byte[] decrypted = sm4.sm4_crypt_ecb(ctx, new BASE64Decoder().decodeBuffer(cipherText));
            return new String(decrypted, "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
            return cipherText;
        }
    }

    public String encryptData_CBC(String plainText) {
        try {
            SM4_Context ctx = new SM4_Context();
            ctx.isPadding = true;
            ctx.mode = SM4.SM4_ENCRYPT;

            byte[] keyBytes;
            byte[] ivBytes;
            if (hexString) {
                keyBytes = Util.hexStringToBytes(secretKey);
                ivBytes = Util.hexStringToBytes(iv);
            } else {
                keyBytes = secretKey.getBytes();
                ivBytes = iv.getBytes();
            }

            SM4 sm4 = new SM4();
            sm4.sm4_setkey_enc(ctx, keyBytes);
            byte[] encrypted = sm4.sm4_crypt_cbc(ctx, ivBytes, plainText.getBytes("UTF-8"));
            return Util.byteToHex(encrypted);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public String decryptData_CBC(String cipherText) {
        try {
            byte[] encrypted = Util.hexToByte(cipherText);
            cipherText=Base64.encodeBase64String(encrypted);;
            //cipherText = new BASE64Encoder().encode(encrypted);
            if (cipherText != null && cipherText.trim().length() > 0) {
                Pattern p = Pattern.compile("\\s*|\t|\r|\n");
                Matcher m = p.matcher(cipherText);
                cipherText = m.replaceAll("");
            }
            SM4_Context ctx = new SM4_Context();
            ctx.isPadding = true;
            ctx.mode = SM4.SM4_DECRYPT;

            byte[] keyBytes;
            byte[] ivBytes;
            if (hexString) {
                keyBytes = Util.hexStringToBytes(secretKey);
                ivBytes = Util.hexStringToBytes(iv);
            } else {
                keyBytes = secretKey.getBytes();
                ivBytes = iv.getBytes();
            }

            SM4 sm4 = new SM4();
            sm4.sm4_setkey_dec(ctx, keyBytes);
            //byte[] decrypted = sm4.sm4_crypt_cbc(ctx, ivBytes, new BASE64Decoder().decodeBuffer(cipherText));
            byte[] decrypted = sm4.sm4_crypt_cbc(ctx, ivBytes, Base64.decodeBase64(cipherText));
            /*String text = new String(decrypted, "UTF-8");
            return text.substring(0,text.length()-1);*/
            return new String(decrypted, "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    //生成秘钥
    public static String generateSM4Key() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    //对称秘钥加密(ECB)
    public static String SM4EncForECB(String key,String text) {
        try {
            SM4Utils sm4 = new SM4Utils();
            sm4.secretKey = key;
            sm4.hexString = true;
            String cipherText = sm4.encryptData_ECB(text);
            return cipherText;
        }catch (Exception e){
            return text;
        }

    }
    //对称秘钥解密(ECB)
    public static String SM4DecForECB(String key,String text) {
        try {
            SM4Utils sm4 = new SM4Utils();
            sm4.secretKey = key;
            sm4.hexString = true;
            String plainText = sm4.decryptData_ECB(text);
            return plainText;
        }catch (Exception e){
            System.err.println("解密出错");
            return text;
        }

    }

    /**
     * 加密
     * @return
     */
    public static String encrypt(String data) {
        if (StrUtil.isNotEmpty(data) && !isCiphertext(data)){
            return SM4EncForECB(SM4KEY , data);
        }
        return data;
    }

    /**
     * 验证数据格式后进行解密
     * @return
     */
    public static String decode(String data) {
        if (isCiphertext(data)){
            return SM4DecForECB(SM4KEY , data);
        }
        return data;
    }

//    public static void main(String[] args) throws IOException {
//        String plainText = "I Love You Every Day";
//        String s = Util.byteToHex(plainText.getBytes());
//        System.out.println("原文" + s);
//        SM4Utils sm4 = new SM4Utils();
//        //sm4.secretKey = "JeF8U9wHFOMfs2Y8";
//        sm4.secretKey = "64EC7C763AB7BF64E2D75FF83A319918";
//        sm4.hexString = true;
//
//        System.out.println("ECB模式加密");
//        String cipherText = sm4.encryptData_ECB(plainText);
//        System.out.println("密文: " + cipherText);
//        System.out.println("");
//
//        String plainText2 = sm4.decryptData_ECB(cipherText);
//        System.out.println("明文: " + plainText2);
//        System.out.println("");
//
//        System.out.println("CBC模式加密");
//        sm4.iv = "31313131313131313131313131313131";
//        String cipherText2 = sm4.encryptData_CBC(plainText);
//        System.out.println("加密密文: " + cipherText2);
//        System.out.println("");
//
//        String plainText3 = sm4.decryptData_CBC(cipherText2);
//        System.out.println("解密明文: " + plainText3);
//
//    }

    /**
     * 是否为密文
     * @param content
     * @return
     */
    public static boolean isCiphertext(String content){
        if (StrUtil.isEmpty(content)){
            return false;
        }
        return content.length() >= 32 && content.length() % 32 == 0 && content.matches("^[0-9a-zA-Z]+$");
    }

    public static void main(String[] args) {
//        decode();
        System.err.println("phone:15942267776,加密后:" +encrypt("15942267776") );
        System.err.println("phone:18811494402,加密后:" +encrypt("18811494402") );
        System.err.println("phone:18911902291,加密后:" +encrypt("18911902291") );
        System.err.println("phone:18823831580,加密后:" +encrypt("18823831580") );
        System.err.println("phone:13716576518,加密后:" +encrypt("13716576518") );
        System.err.println("phone:13244758522,加密后:" +encrypt("13244758522") );
        System.err.println("phone:18124189885,加密后:" +encrypt("18124189885") );
        System.err.println("phone:18811494403,加密后:" +encrypt("18811494403") );
        System.err.println("phone:17783645126,加密后:" +encrypt("17783645126") );
        System.err.println("phone:15663151777,加密后:" +encrypt("15663151777") );
        System.err.println("phone:13455656000,加密后:" +encrypt("13455656000") );
        System.err.println("phone:15914395616,加密后:" +encrypt("15914395616") );
    }

//    public static void main(String[] args) {
//        String content = "029ad19bc3543858f543ace58d7ab90c";
//        System.err.println("是否为密文:" + isCiphertext(content));
////        System.out.println("--生成SM4秘钥--");
////        String sm4Key = generateSM4Key();
//        String sm4Key = "99c045eda4ec4a7d933ed726f6416c83"; //解密需要该key
////        System.out.println("sm4Key:"+sm4Key);
////        System.out.println("--生成SM4秘钥结束--");
//
////        System.out.println("--ECB加密--");
//        String s3 = SM4EncForECB(sm4Key, content);
//        System.out.println("ECB密文:"+s3);
//        System.out.println("ECB密文长度:"+s3.length());
//        System.err.println("是否为密文:" + isCiphertext(s3));
//
//        System.out.println("ECB解密");
//        String s4 = SM4DecForECB(sm4Key, s3);
//        System.out.println("ECB解密结果:"+s4);
//        System.out.println("ECB解密结果长度:"+s4.length());
//
//
////        String key = "99c045eda4ec4a7d933ed726f6416c83";
////        System.err.println(SM4DecForECB(sm4Key, "f7e6e46191e5e56f4e24869aebd4fbd6"));
//    }


}
