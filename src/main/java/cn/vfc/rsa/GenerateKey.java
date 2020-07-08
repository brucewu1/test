package cn.vfc.rsa;

import org.apache.commons.codec.binary.Base64;

import java.math.BigInteger;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.SecureRandom;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.HashMap;
import java.util.Map;

public class GenerateKey {
    private static Map<Integer,String> keyMap = new HashMap<Integer, String>();
    public static void genKeyPair() throws Exception{
        KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("RSA");
        keyPairGen.initialize(1024,new SecureRandom());
        KeyPair keyPair = keyPairGen.generateKeyPair();
        RSAPrivateKey privateKey = (RSAPrivateKey)keyPair.getPrivate();
        BigInteger privateExponent = privateKey.getPrivateExponent();
        String s1 = privateExponent.toString(16).toUpperCase();
        BigInteger modulus = privateKey.getModulus();
        String s2 = modulus.toString(16).toUpperCase();
        RSAPublicKey publicKey = (RSAPublicKey)keyPair.getPublic();
        BigInteger publicExponent = publicKey.getPublicExponent();
        BigInteger modulus1 = publicKey.getModulus();
        String s3 = modulus1.toString(16).toUpperCase();
        System.out.println("私钥指数:"+privateExponent);
        System.out.println("十六进制私钥指数:"+s1);
        System.out.println("十六进制私钥模:"+s2);
        System.out.println("十六进制公钥莫:"+s3);
        System.out.println("私钥模:"+modulus);
        System.out.println("公钥指数:"+publicExponent);
        System.out.println("公钥模:"+modulus1);
        //String privateKeyString = new String(Base64.encodeBase64(privateKey.getEncoded()));
        String privateKeyString = Base64.encodeBase64String(privateKey.getEncoded());
        String pulbicKeyString = new String(Base64.encodeBase64(publicKey.getEncoded()));
        keyMap.put(0,pulbicKeyString);
        keyMap.put(1,privateKeyString);
    }

    public static void main(String[] args) throws Exception{
        genKeyPair();
        System.out.println("私钥为:"+keyMap.get(1));
        System.out.println("公钥为:"+keyMap.get(0));
    }
}
