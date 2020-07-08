package cn.java8;

public class ObjectRef {
        public static void testBasicType(int m) {
            System.out.println("m=" + m);//m=50
            m = 100;
            System.out.println("m=" + m);//m=100
        }

        public static void add(StringBuffer s) {
            s.append("_add");
        }

        public static void changeRef(StringBuffer s) {
            s.append("bbbb");
            s = new StringBuffer("Java");
            System.out.println(s);
        }

        public static void main(String[] args) {
            int i = 50;
            testBasicType(i);
            System.out.println(i);
            StringBuffer sMain = new StringBuffer("init");
            System.out.println("sMain=" + sMain.toString());
            add(sMain);
            System.out.println("sMain=" + sMain.toString());
            changeRef(sMain);
            System.out.println("sMain=" + sMain.toString());
        }
}
