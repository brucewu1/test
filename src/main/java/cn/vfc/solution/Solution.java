package cn.vfc.solution;

public class Solution {
    public static boolean isPalindrome1(int x) {
        String reversedStr = (new StringBuilder(x + "")).reverse().toString();
        return (x + "").equals(reversedStr);
    }

    public static boolean isPalindrome2(int x) {
        //边界判断  123454321
        if(x < 0){
            return false;
        }
        int div = 1;
        while(x/div >=10) div *= 10;
        while(x > 0){
            int l = x / div;
            int r = x % 10;
            if(l != r){
                return false;
            }
            x = (x % div)/10;
            //x = x / 10;
            div /= 100;
        }
        return true;
    }



    public static boolean isPalindrome3(int x) {
        //思考：这里大家可以思考一下，为什么末尾为 0 就可以直接返回 false
        if (x < 0 || (x % 10 == 0 && x != 0)) return false;
        int revertedNumber = 0;
        while (x > revertedNumber) {
            revertedNumber = revertedNumber * 10 + x % 10;
            x /= 10;
        }
        return x == revertedNumber || x == revertedNumber / 10;
    }

    /*public static boolean isPalindromel(int a){

    }*/

    public static void main(String[] args) {
        int a = 123454321;
        boolean palindrome = isPalindrome2(a);
        System.out.println(palindrome);
    }

}
