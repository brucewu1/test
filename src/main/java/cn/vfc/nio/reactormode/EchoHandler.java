package cn.vfc.nio.reactormode;

import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;

public class EchoHandler implements Runnable {
    final SocketChannel channel;
    final SelectionKey sk;
    final ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
    static final int RECEIVING = 0,SENDING = 1;
    int state = RECEIVING;

    public EchoHandler(SocketChannel c, Selector selector) throws Exception{
        channel = c;
        c.configureBlocking(false);
        //仅仅取得选择键，后设置感兴趣的IO事件
        sk = channel.register(selector,0);
        //将Handler作为选择键的附件
        sk.attach(this);
        sk.interestOps(SelectionKey.OP_READ);
        selector.wakeup();
    }

    @Override
    public void run() {
        try {
            if(state == SENDING){
                channel.write(byteBuffer);
                byteBuffer.clear();
                sk.interestOps(SelectionKey.OP_READ);
                state = RECEIVING;
            }else if(state == RECEIVING){
                //从通道读
                int length = 0;
                if((length = channel.read(byteBuffer)) > 0){
                    System.out.println(new String(byteBuffer.array(),0,length));
                }
                byteBuffer.flip();
                sk.interestOps(SelectionKey.OP_WRITE);
                state = SENDING;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
