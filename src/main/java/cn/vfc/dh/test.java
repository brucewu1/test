package cn.vfc.dh;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Arrays;

public class test {
    public static void main(String[] args) {
        String g = "2";
        String stra = "EFF6AA653C9AC2581BFD743E5F39D3E8711DD466E3F2895DB81CCC1D38D5EEAB";
        String strb = "37B2C8DDFA3E547AF97BFFFCF27283CCD1B1EACBC79839DD6F3B8CB29C8B883C";
        String p = "0xFFFFFFFFFFFFFFFFC90FDAA22168C234C4C6628B80DC1CD129024E088A67CC74020BBEA63B139B22514A08798E3404DDEF9519B3CD3A431B302B0A6DF25F14374FE1356D6D51C245E485B576625E7EC6F44C42E9A637ED6B0BFF5CB6F406B7EDEE386BFB5A899FA5AE9F24117C4B1FE649286651ECE45B3DC2007CB8A163BF0598DA48361C55D39A69163FA8FD24CF5F83655D23DCA3AD961C62F356208552BB9ED529077096966D670C354E4ABC9804F1746C08CA237327FFFFFFFFFFFFFFFF";
        String exp = "g^a%p";
        double c = Math.pow(2,3);
        System.out.println(c);
        //new BigInteger()
        SecureRandom secureRandom = new SecureRandom();
        byte[] bytes = new byte[64];
        secureRandom.nextBytes(bytes);
        System.out.println(Arrays.toString(bytes));
    }
}
