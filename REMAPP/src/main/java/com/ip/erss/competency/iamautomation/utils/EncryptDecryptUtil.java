package com.ip.erss.competency.iamautomation.utils;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

public class EncryptDecryptUtil {

    private static final String key = AppConstants.KEY; // 128 bit key
    private static final String initVector = AppConstants.INITVECTOR; // 16 bytes IV

    public static String doEncrypt(String value) {
        try {
            IvParameterSpec iv = new IvParameterSpec(initVector.getBytes(AppConstants.CHAR_TYPE));
            SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes(AppConstants.CHAR_TYPE), "AES");

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);

            byte[] encrypted = cipher.doFinal(value.getBytes());

            return Base64.encodeBase64String(encrypted);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public static String doDecrypt(String encrypted) {
        try {
            IvParameterSpec iv = new IvParameterSpec(initVector.getBytes(AppConstants.CHAR_TYPE));
            SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes(AppConstants.CHAR_TYPE), "AES");

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);

            byte[] original = cipher.doFinal(Base64.decodeBase64(encrypted));

            return new String(original);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

}
