package cn.vfc.nio.reactormode;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

public class EchoClient {
    public void start()throws Exception{
        InetSocketAddress inetSocketAddress = new InetSocketAddress("127.0.0.1", 7005);
        SocketChannel socketChannel = SocketChannel.open(inetSocketAddress);
        socketChannel.configureBlocking(false);
        while (! socketChannel.finishConnect()){

        }
        System.out.println("客户端启动成功");
        //启动接受线程
        Proccesor processer = new Proccesor(socketChannel);
        new Thread(processer).start();
    }

    public static void main(String[] args) throws Exception{
        new EchoClient().start();
    }

    static class Proccesor implements Runnable{
        final Selector selector;
        final SocketChannel socketChannel;

        public Proccesor(SocketChannel socketChannel) throws Exception{
            this.socketChannel = socketChannel;
            selector = Selector.open();
            socketChannel.register(selector, SelectionKey.OP_WRITE | SelectionKey.OP_READ);
        }

        @Override
        public void run() {
            try {
                while (!Thread.interrupted()){
                    selector.select();
                    Set<SelectionKey> selectionKeys = selector.selectedKeys();
                    Iterator<SelectionKey> iterator = selectionKeys.iterator();
                    while (iterator.hasNext()){
                        SelectionKey sk = iterator.next();
                        if(sk.isWritable()){
                            ByteBuffer buffer = ByteBuffer.allocate(1024);
                            Scanner scanner = new Scanner(System.in);
                            System.out.println("请输入内容");
                            while (scanner.hasNext()){
                                SocketChannel socketChannel = (SocketChannel)sk.channel();
                                String next = scanner.next();
                                buffer.put(next.getBytes());
                                buffer.flip();
                                socketChannel.write(buffer);
                                buffer.clear();
                            }
                        }
                        if(sk.isReadable()){
                            ByteBuffer buffer = ByteBuffer.allocate(1024);
                            SocketChannel socketChannel = (SocketChannel) sk.channel();
                            int length = 0;
                            while ((length = socketChannel.read(buffer)) > 0){
                                buffer.flip();
                                System.out.println("server echo:" + new String(buffer.array(),0,length));
                                buffer.clear();
                            }
                        }
                    }
                    selectionKeys.clear();
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
