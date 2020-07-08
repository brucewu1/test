package cn.vfc.dh;

import java.math.BigInteger;

public class Util64 {
    public static String strg = "2";
    public static String stra = "EFF6AA653C9AC2581BFD743E5F39D3E8711DD466E3F2895DB81CCC1D38D5EEAB";
    public static String strb = "37B2C8DDFA3E547AF97BFFFCF27283CCD1B1EACBC79839DD6F3B8CB29C8B883C";
    public static String strp = "FFFFFFFFFFFFFFFFC90FDAA22168C234C4C6628B80DC1CD129024E088A67CC74020BBEA63B139B22514A08798E3404DDEF9519B3CD3A431B302B0A6DF25F14374FE1356D6D51C245E485B576625E7EC6F44C42E9A637ED6B0BFF5CB6F406B7EDEE386BFB5A899FA5AE9F24117C4B1FE649286651ECE45B3DC2007CB8A163BF0598DA48361C55D39A69163FA8FD24CF5F83655D23DCA3AD961C62F356208552BB9ED529077096966D670C354E4ABC9804F1746C08CA237327FFFFFFFFFFFFFFFF";
    public static String[] arr = {"0","1","2","3","4","5","6","7","8","9","A","B","C","D","E","F"};

    public static String ya = "fca953535ac5e63e84e813310d66ceecf51800aa182bc4a0418c60fb857b4a96ad0db034661bbdd21534ac7a61c60c0c0e43e4fa687e22bc5f7c558d6e30b5169e9f4613719a11d5a2c6fd1a0d628348b393c2af3068a6f12089f61a6618f7ca310bf93a0f911828ec47ff4c74ee2d67ad52664984b6a72d1d065c11b7672427b8adef7d31c9d3e34acf6004edb27e25634ac30fb4740b5423f86f91dd80e16a5a12866e56baf202fee9e29d74fcbaab1a0e03495ade0422f8c40fd2f4d3ad85";
    public static String yb = "d12a2257ef01f41fa84c3e5d702735c675ba693497ab06f6fc5ec9f21969b19fb9fb64923b618ab0650806d133c4cafa81a6df184b3cf441ca630d09b483cc32a36523bc1506173a6b6579469c6e9198d248b2121c841eaaa68c59e8f3f4a4b90028192c73982da61b033e9a7a71fa60daffce359b68b809fa0f78c201fdf1d2d6e86de45bd23c5f817b362a91c17c2728c65e73423fa1e0a36fbbc61d6c234f11c34f0da9ad3a49813466d7b52b555c35aacbce558be25c97ba45143e95fb4f";

    public static String gen64Str(){
        StringBuilder sb = new StringBuilder();
        for (int j=0;j<64;j++){
            int i = (int)(Math.random()*15+1);
            sb.append(arr[i]);
        }
        return sb.toString();
    }

    public static BigInteger getResult(){
        //BigInteger a = new BigInteger(stra,16);
        BigInteger b = new BigInteger(stra,16);
        BigInteger p = new BigInteger(strp,16);
        BigInteger g = new BigInteger(yb,16);
        BigInteger bigInteger = g.modPow(b, p);
        return bigInteger;
    }

    public static void main(String[] args) {
        BigInteger result = getResult();
        String s = result.toString(16);
        System.out.println(s);
    }
}
