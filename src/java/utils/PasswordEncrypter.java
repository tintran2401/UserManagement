/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.DatatypeConverter;

/**
 *
 * @author TiTi
 */
public class PasswordEncrypter {

    public static String encrypt(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
            
            String sha256 = DatatypeConverter.printHexBinary(hash).toLowerCase();
            return sha256;
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(PasswordEncrypter.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "";
    }
}
