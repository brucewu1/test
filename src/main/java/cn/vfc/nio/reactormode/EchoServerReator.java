package cn.vfc.nio.reactormode;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class EchoServerReator implements Runnable {
    Selector selector;
    ServerSocketChannel serverSocketChannel;
    EchoServerReator() throws Exception{
        selector = Selector.open();
        serverSocketChannel = ServerSocketChannel.open();
        InetSocketAddress inetSocketAddress = new InetSocketAddress("127.0.0.1", 7005);
        serverSocketChannel.socket().bind(inetSocketAddress);
        serverSocketChannel.configureBlocking(false);
        SelectionKey sk = serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        sk.attach(new AcceptorHandler());
    }
    @Override
    public void run() {
        try {
            while (!Thread.interrupted()){
                selector.select();
                Set<SelectionKey> selected = selector.selectedKeys();
                Iterator<SelectionKey> it = selected.iterator();
                while (it.hasNext()){
                    //Reactor负责dispatch收到的事件
                    SelectionKey sk = it.next();
                    dispatcher(sk);
                }
                selected.clear();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void dispatcher(SelectionKey sk) {
        //调用之前attach绑定到选择键的handler处理器对象
        Runnable handler = (Runnable)sk.attachment();
        if(handler != null){
            handler.run();
        }
    }

    //新连接处理器
    class AcceptorHandler implements Runnable{
        @Override
        public void run() {
            try {
                SocketChannel channel = serverSocketChannel.accept();
                if(channel != null) new EchoHandler(channel,selector);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws Exception{
        new Thread(new EchoServerReator()).start();
    }
}
