package BUS;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;

public class AES {
    public TextAES textAES;
    public FileAES fileAES;
    public AES(){
        textAES = new TextAES();
        fileAES = new FileAES();
    }

    static class TextAES{
        private final String keySecret;

        public TextAES() {
            this.keySecret = "AES-text";
        }

        public String encrypt(String strToEncrypt) {
            try {
                MessageDigest sha = MessageDigest.getInstance("SHA-1");
                byte[] key = keySecret.getBytes(StandardCharsets.UTF_8);
                    key = sha.digest(key);
                key = Arrays.copyOf(key, 16);
                SecretKeySpec secretKey = new SecretKeySpec(key, "AES");
                Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
                cipher.init(Cipher.ENCRYPT_MODE, secretKey);
                return Base64.getEncoder().encodeToString(cipher.doFinal(strToEncrypt.getBytes("UTF-8")));
            } catch (Exception e) {
                System.out.println(e.toString());
            }
            return null;
        }

        public String decrypt(String strToDecrypt) {
            try {
                MessageDigest sha = MessageDigest.getInstance("SHA-1");
                byte[] key = keySecret.getBytes(StandardCharsets.UTF_8);
                key = sha.digest(key);
                key = Arrays.copyOf(key, 16);
                SecretKeySpec secretKey = new SecretKeySpec(key, "AES");
                Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
                cipher.init(Cipher.DECRYPT_MODE, secretKey);
                return new String(cipher.doFinal(Base64.getDecoder().decode(strToDecrypt)));
            } catch (Exception e) {
                System.out.println(e.toString());
            }
            return null;
        }
    }

    static class FileAES{
        private final String ALGORITHM = "AES";
        private final String TRANSFORMATION = "AES/ECB/PKCS5PADDING";
        private String keySecret;
        public FileAES(){
            this.keySecret = "AES-file";
        }

        public void encrypt(File inputFile, File outputFile){
            doCrypto(Cipher.ENCRYPT_MODE,inputFile,outputFile);
        }

        public void decrypt(File inputFile, File outputFile){
            doCrypto(Cipher.DECRYPT_MODE, inputFile,outputFile);
        }

        private void doCrypto(int cipherMode, File inputFile, File outputFile){
            try (FileInputStream inputStream = new FileInputStream(inputFile);
                 FileOutputStream outputStream = new FileOutputStream(outputFile)){

                MessageDigest sha = MessageDigest.getInstance("SHA-1");
                byte[] key = keySecret.getBytes(StandardCharsets.UTF_8);
                key = sha.digest(key);
                key = Arrays.copyOf(key,16);
                SecretKeySpec secretKeySpec = new SecretKeySpec(key,ALGORITHM);
                Cipher cipher = Cipher.getInstance(TRANSFORMATION);
                cipher.init(cipherMode,secretKeySpec);
                byte[] inputBytes = new byte[(int) inputFile.length()];
                inputStream.read(inputBytes);

                byte[] outputBytes = cipher.doFinal(inputBytes);

                outputStream.write(outputBytes);

            } catch (IOException | NoSuchPaddingException | NoSuchAlgorithmException | InvalidKeyException |
                     IllegalBlockSizeException | BadPaddingException e) {
                throw new RuntimeException(e);
            }
        }
    }
}