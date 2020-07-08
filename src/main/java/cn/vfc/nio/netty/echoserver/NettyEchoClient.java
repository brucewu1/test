package cn.vfc.nio.netty.echoserver;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.io.UnsupportedEncodingException;
import java.util.Scanner;

public class NettyEchoClient {
    private int serverPort;
    private String ip;
    Bootstrap b = new Bootstrap();

    public NettyEchoClient(int serverPort, String ip) {
        this.serverPort = serverPort;
        this.ip = ip;
    }

    public void runClient(){
        NioEventLoopGroup work = new NioEventLoopGroup();
        b.group(work);
        b.channel(NioSocketChannel.class);
        b.remoteAddress(ip,serverPort);
        b.option(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT);
        b.handler(new ChannelInitializer<SocketChannel>() {
            @Override
            protected void initChannel(SocketChannel socketChannel) throws Exception {
                socketChannel.pipeline().addLast(NettyEchoClientHandler.INSTANCE);
            }
        });
        ChannelFuture f = b.connect();
        f.addListener((ChannelFuture futureListen) ->{
            if(futureListen.isSuccess()){
                System.out.println("客户端连接成功");
            }else {
                System.out.println("客户端连接失败");
            }
        });
        try {
            f.sync();
            Channel channel = f.channel();
            Scanner scanner =new Scanner(System.in);
            System.out.println("请输入发送内容");
            while (scanner.hasNext()){
                String next = scanner.next();
                byte[] bytes = next.getBytes("UTF-8");
                ByteBuf buffer = channel.alloc().buffer();
                buffer.writeBytes(bytes);
                channel.writeAndFlush(buffer);
                System.out.println("请输入发送内容");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }finally {
            work.shutdownGracefully();
        }
    }

    public static void main(String[] args) throws Exception{
        NettyEchoClient client = new NettyEchoClient(7005,"127.0.0.1");
        client.runClient();
    }
}
