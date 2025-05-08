package com.inventario.inventario.utils;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.MessageDigest;
import java.util.Base64;

public class MDcinco {

    private static final String HASH = "texto";

    public static String encrypt(String clave) {
        if (clave == null) {
            throw new IllegalArgumentException("La clave a encriptar no puede ser null");
        }
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            byte[] keyBytes = md5.digest(HASH.getBytes("UTF-8"));

            // Ampliar a 24 bytes
            byte[] key24 = new byte[24];
            System.arraycopy(keyBytes, 0, key24, 0, 16);
            System.arraycopy(keyBytes, 0, key24, 16, 8);

            SecretKey secretKey = new SecretKeySpec(key24, "DESede");
            Cipher cipher = Cipher.getInstance("DESede/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);

            byte[] plainTextBytes = clave.getBytes("UTF-8");
            byte[] encryptedBytes = cipher.doFinal(plainTextBytes);

            return Base64.getEncoder().encodeToString(encryptedBytes);
        } catch (Exception e) {
            System.out.println("Error al encriptar: " + e.getMessage());
            throw new RuntimeException("Error al encriptar", e);
        }
    }

    public static String decrypt(String encryptedKey) {
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            byte[] keyBytes = md5.digest(HASH.getBytes("UTF-8"));

            SecretKey secretKey = new SecretKeySpec(keyBytes, "DESede");
            Cipher cipher = Cipher.getInstance("DESede/ECB/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, secretKey);

            byte[] encryptedBytes = Base64.getDecoder().decode(encryptedKey);
            byte[] decryptedBytes = cipher.doFinal(encryptedBytes);

            return new String(decryptedBytes, "UTF-8");
        } catch (Exception e) {
            throw new RuntimeException("Error al desencriptar", e);
        }
    }

}
