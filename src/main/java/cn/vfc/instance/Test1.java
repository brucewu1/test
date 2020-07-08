package cn.vfc.instance;

public class Test1 {
    public static void main(String[] args) {
        Stack<String> stack = new Stack<>();
        String[] arrs = new String[]{"a","b","c","d"};
        for (String s : arrs){
            stack.push(s);
        }
        while (!stack.isEmpty()){
            System.out.println(stack.pop().toUpperCase());
        }
    }
}
