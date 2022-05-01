package com.example.cryptoking;



import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import static java.lang.Math.pow;

public class decode {

    public static String dec(String s) {
        String inv = "This code was not encriptedv by Criptoking";
        String initializer = "11111111";
        Boolean flag = true;
        for (int i = 0; i < 8; i++) {
            if (initializer.charAt(i) != s.charAt(i)) {
                flag = false;
                break;
            }

        }
        String data = "";
        for (int i = 8; i < s.length(); i++) {
            char temp = s.charAt(i);
            data = data.concat(String.valueOf(temp));
        }
        int asc[][] = new int[11101][8];
        int idx = -1;
        int idx2 = 0;
        for (int i = 0; i < data.length(); i++) {
            if (i % 7 == 0) {
                idx++;
                idx2 = 0;
                char temp = data.charAt(i);
                asc[idx][idx2] = temp - '0';
                idx2++;

            } else {
                char temp = data.charAt(i);
                asc[idx][idx2] = temp - '0';
                idx2++;
            }
        }
        int num[] = new int[11111];
        int nidx = 0;
        int temp = 0;
        int ct = 0;
        for (int i = 0; i <= idx; i++) {

            ct = 0;
            temp = 0;
            for (int j = 6; j >= 0; j--) {

                int temp1 = (int) pow(2, ct);
                temp += (asc[i][j] * temp1);
                ct++;
            }
            num[nidx++] = temp;
        }
        String rv = "";
        char ch;
        for (int i = 0; i < nidx; i++) {
            ch = (char) num[i];
            rv = rv.concat(String.valueOf(ch));
        }
        if (data.length() % 7 == 0 && flag == true) {
            return rv;
        } else {
            return inv;
        }

    }
    private static SecretKeySpec secretKey;
    private static byte[] key;
    public static void setKey(String myKey)
    {
        MessageDigest sha = null;
        try {
            key = myKey.getBytes("UTF-8");
            sha = MessageDigest.getInstance("SHA-1");
            key = sha.digest(key);
            key = Arrays.copyOf(key, 16);
            secretKey = new SecretKeySpec(key, "AES");
        }
        catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public static String decrypt(String strToDecrypt, String secret) {
        try {
            setKey(secret);
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
            cipher.init(Cipher.DECRYPT_MODE, secretKey);

            return new String(cipher.doFinal(Base64.getDecoder().decode(strToDecrypt)));

        } catch (Exception e) {
            System.out.println("Error while decrypting: " + e.toString());
        }
        return null;
    }
}





