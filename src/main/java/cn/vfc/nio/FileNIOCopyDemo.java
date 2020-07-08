package cn.vfc.nio;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class FileNIOCopyDemo {
    public static void main(String[] args) {
        String sourcePath = "F:/idea.txt";
        String destPath = "F:/idea1.txt";
        nioCopyFile(sourcePath,destPath);
    }

    private static void nioCopyFile(String sourcePath, String destPath) {
        File oldFile = new File(sourcePath);
        File newFile = new File(destPath);
        if(!newFile.exists()){
            try {
                newFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        FileInputStream fis = null;
        FileOutputStream fos = null;
        FileChannel inChannel = null;
        FileChannel outChannel = null;
        try {
            fis = new FileInputStream(oldFile);
            fos = new FileOutputStream(newFile);
            inChannel = fis.getChannel();
            outChannel = fos.getChannel();
            ByteBuffer buff = ByteBuffer.allocate(1024);
            int length = -1;
            while ((length = inChannel.read(buff)) != -1){
                buff.flip();
                int outLength = 0;
                while ((outLength = outChannel.write(buff)) != 0){
                    System.out.println("输出的字节数:" + outLength);
                }
                buff.clear();
            }
            outChannel.force(true);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                outChannel.close();
                inChannel.close();
                fos.close();
                fis.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
