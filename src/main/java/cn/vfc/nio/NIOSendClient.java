package cn.vfc.nio;

import java.io.File;
import java.io.FileInputStream;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;

public class NIOSendClient{
    private Charset charset = Charset.forName("UTF-8");
    public static void main(String[] args) throws Exception{
        NIOSendClient nioSendClient = new NIOSendClient();
        nioSendClient.sendFile();
    }

    public void sendFile() throws Exception{
        String srcPath = "F:/idea.txt";
        String destName = "idea1.txt";
        File file = new File(srcPath);
        if(!file.exists()){
            System.out.println("文件不存在");
            return;
        }
        FileChannel fileChannel = new FileInputStream(file).getChannel();
        SocketChannel socketChannel = SocketChannel.open();

        socketChannel.socket().connect(new InetSocketAddress("127.0.0.1",7002));
        socketChannel.configureBlocking(false);
        while (!socketChannel.finishConnect()){
            System.out.println("等待与服务器链接");
        }
        System.out.println("与服务器成功链接");

        ByteBuffer fileNameBuffer = charset.encode(destName);
        socketChannel.write(fileNameBuffer);
        ByteBuffer byteBuffer = ByteBuffer.allocate(100);
        byteBuffer.putLong(file.length());
        byteBuffer.flip();
        socketChannel.write(byteBuffer);
        byteBuffer.clear();
        //发送内容
        int length = 0;
        int proccess = 0;
        while ((length = fileChannel.read(byteBuffer)) > 0){
            byteBuffer.flip();
            socketChannel.write(byteBuffer);
            proccess += length;
            System.out.println("| " + (100 * proccess / file.length()) + " %");
            byteBuffer.clear();
        }
        fileChannel.close();
        socketChannel.shutdownOutput();
        socketChannel.close();
        System.out.println("文件传输成功");
    }
}
