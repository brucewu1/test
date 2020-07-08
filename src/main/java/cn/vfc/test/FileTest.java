package cn.vfc.test;

import java.io.*;

public class FileTest {
    public static void main(String[] args) throws Exception{
       String path = "F:/test/input.txt";
        FileReader fileReader = new FileReader(path);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String line;
        while((line = bufferedReader.readLine()) != null){
            System.out.println(line);
        }
        File file = new File(path);
        System.out.println(file.getName());
    }
}
