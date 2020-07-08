package cn.vfc.nio;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class NIOReceiveServer {
    public static void main(String[] args) throws Exception{
        NIOReceiveServer nioReceiveServer = new NIOReceiveServer();
        nioReceiveServer.startServer();
    }
    private Charset charset = Charset.forName("UTF-8");
    static class Client{
        String fileName;
        long fileLength;
        //客户端的地址
        InetSocketAddress socketAddress;
        FileChannel outChannel;
    }
    private ByteBuffer byteBuffer = ByteBuffer.allocate(100);
    Map<SelectableChannel,Client> map = new HashMap<>();
    public void startServer() throws Exception{
        Selector selector = Selector.open();
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        ServerSocket serverSocket = serverSocketChannel.socket();
        serverSocketChannel.configureBlocking(false);
        InetSocketAddress inetSocketAddress = new InetSocketAddress(7002);
        serverSocket.bind(inetSocketAddress);
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        System.out.println("serverSocketChanner is listening");
        while (selector.select() > 0){
            Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
            while (iterator.hasNext()){
                SelectionKey key = iterator.next();
                if(key.isAcceptable()){
                    ServerSocketChannel serverSocketChannel1 = (ServerSocketChannel)key.channel();
                    SocketChannel socketChannel  = serverSocketChannel1.accept();
                    if(socketChannel == null) continue;
                    socketChannel.configureBlocking(false);
                    socketChannel.register(selector,SelectionKey.OP_READ);
                    Client client = new Client();
                    client.socketAddress = (InetSocketAddress)socketChannel.getRemoteAddress();
                    map.put(socketChannel,client);
                    System.out.println(socketChannel.getRemoteAddress() + "链接成功");
                }else if(key.isReadable()){
                    processData(key);
                }
                iterator.remove();
            }
        }
    }

    /**
     * 处理客户端传输过来的数据
     * @param key
     */
    private void processData(SelectionKey key) {
        Client client = map.get(key.channel());
        SocketChannel socketChannel = (SocketChannel)key.channel();
        int num = 0;
        try {
            byteBuffer.clear();
            while ((num = socketChannel.read(byteBuffer)) > 0){
                byteBuffer.flip();
                if(null == client.fileName){
                    String fileName = charset.decode(byteBuffer).toString();
                    String destPath = "F:/b";
                    File dectory = new File(destPath);
                    if(!dectory.exists()){
                        dectory.mkdirs();
                    }
                    client.fileName = fileName;
                    String fullName = dectory.getAbsolutePath() + "\\" + fileName;
                    System.out.println("NIO 传输目标文件:" + fullName);
                    File file = new File(fullName);
                    FileChannel outChannel = new FileOutputStream(file).getChannel();
                    client.outChannel = outChannel;
                }else if(0 == client.fileLength){
                    long length = byteBuffer.getLong();
                    client.fileLength = length;
                    System.out.println("传输开始 长度为：" + length);
                }else {
                    client.outChannel.write(byteBuffer);
                }
                byteBuffer.clear();
            }
            key.cancel();
        }catch (Exception e){
            e.printStackTrace();
            key.cancel();
            return;
        }
        if(num == -1){
            try {
                client.outChannel.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("上传完毕");
            key.cancel();
            System.out.println("接收成功fileName:" + client.fileName);
        }
    }
}

