package cn.vfc.test;

import java.math.BigInteger;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.RSAPublicKeySpec;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.codec.binary.Base64;

public class RsaRevertPubKey {

    private static Map<Integer,String> keyMap = new HashMap<Integer, String>();

    private static String modulus = "8B6D8CAE9C9494FC1AE3C90E5869111447FA19F66F62D904787C973862D08B0056CA891ECA0CA7CF5D38407BA7AF8FC9A83624CDC46B9A47B0FEBAD0FC730D80C7C1CA1088731D758C6D26A3A2DF7BD7634EEA107B6D752609A16C9C671758A853135214425C58B0DD25779DFB070B08817F0828C72BEF11A5D7F998D0F81D49";
    private static String publicExponet = "10001";

    /**
     * 通过模数和指数获取公钥
     * @param modulus
     * @param publicExponent
     * @return
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeySpecException
     */
    public static PublicKey getPublicKey(String modulus, String publicExponent)

            throws NoSuchAlgorithmException, InvalidKeySpecException {

        BigInteger bigIntModulus = new BigInteger(modulus,10);

        BigInteger bigIntPrivateExponent = new BigInteger(publicExponent,10);

        RSAPublicKeySpec keySpec = new RSAPublicKeySpec(bigIntModulus, bigIntPrivateExponent);

        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PublicKey publicKey = keyFactory.generatePublic(keySpec);

        return publicKey;

    }

    /**
     * 随机生成公私钥对
     * @throws Exception
     */

    public static void genKeyPair() throws Exception{
        KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("RSA");
        keyPairGen.initialize(1024);
        KeyPair keyPair = keyPairGen.generateKeyPair();
        RSAPrivateKey privateKey = (RSAPrivateKey)keyPair.getPrivate();
        System.out.println("私钥modulus:"+ privateKey.getModulus());
        System.out.println("私钥exponent:"+ privateKey.getPrivateExponent());
        RSAPublicKey publicKey = (RSAPublicKey)keyPair.getPublic();
        System.out.println("公钥modulus:"+ publicKey.getModulus());
        System.out.println("公钥exponent:"+publicKey.getPublicExponent());
        //String privateKeyString = new String(Base64.encodeBase64(privateKey.getEncoded()));
        String privateKeyString = Base64.encodeBase64String(privateKey.getEncoded());
        String pulbicKeyString = new String(Base64.encodeBase64(publicKey.getEncoded()));
        keyMap.put(0,pulbicKeyString);
        keyMap.put(1,privateKeyString);
    }
    //MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCPpVWoGMmSo9mgqPC+Qd/Dh/l0jzLYExISEu+YkFYlF+Uw7QUSZpL8/gdrex+hviXN9A59964cRjOnkAa2JcBlWFu+11DxwVOXV5klkG3hOmtii2e8Vfvu7ylix0L7ewsz5r8iqFcE6AGA6rROqWuV+1uIBO+G83xqkiReiAHbzwIDAQAB
    //MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCPpVWoGMmSo9mgqPC+Qd/Dh/l0jzLYExISEu+YkFYlF+Uw7QUSZpL8/gdrex+hviXN9A59964cRjOnkAa2JcBlWFu+11DxwVOXV5klkG3hOmtii2e8Vfvu7ylix0L7ewsz5r8iqFcE6AGA6rROqWuV+1uIBO+G83xqkiReiAHbzwIDAQAB
    public static void main(String[] args) throws Exception{
        /*RsaRevertPubKey.genKeyPair();
        System.out.println(keyMap.get(0));
        System.out.println(keyMap.get(1));*/
        String modulus = "100871538009019219963893907550181495120413112062085081473997369329489206075721830674811229189367184997107981243467893431856675624947328616576643975489087048616898234213853748781346010413904437083959536753502912352006713335515099794698516916870420580514851232216496108129139760114419447362369364508793928539087";
        String exponent = "65537";
        PublicKey publicKey = RsaRevertPubKey.getPublicKey(modulus, exponent);
        System.out.println(publicKey);
        String s = new String(Base64.encodeBase64(publicKey.getEncoded()));
        System.out.println(s);
    }
}
