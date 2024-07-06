package utils;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Hash functions utility class.
 * @author www.codejava.net
 *
 */
public class HashGeneratorUtils {
    private HashGeneratorUtils() {

    }

    public static String generateMD5(String message) throws HashGenerationException {
        return hashString(message, "MD5");
    }

    public static String generateSHA1(String message) throws HashGenerationException {
        return hashString(message, "SHA-1");
    }

    public static String generateSHA256(String message) throws HashGenerationException {
        return hashString(message, "SHA-256");
    }

    private static String hashString(String message, String algorithm)
            throws HashGenerationException {

        try {
            MessageDigest digest = MessageDigest.getInstance(algorithm);
            byte[] hashedBytes = digest.digest(message.getBytes(StandardCharsets.UTF_8));

            return convertByteArrayToHexString(hashedBytes);
        } catch (NoSuchAlgorithmException ex) {
            throw new HashGenerationException(
                    "Could not generate hash from String", ex);
        }
    }

    private static String convertByteArrayToHexString(byte[] arrayBytes) {
        StringBuilder stringBuffer = new StringBuilder();
        for (byte arrayByte : arrayBytes) {
            stringBuffer.append(Integer.toString((arrayByte & 0xff) + 0x100, 16)
                    .substring(1));
        }
        return stringBuffer.toString();
    }
}