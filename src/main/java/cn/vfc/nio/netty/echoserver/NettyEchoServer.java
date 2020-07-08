package cn.vfc.nio.netty.echoserver;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class NettyEchoServer {
    private final int serverPort;
    ServerBootstrap b = new ServerBootstrap();

    public NettyEchoServer(int serverPort) {
        this.serverPort = serverPort;
    }
    public void runServer(){
        NioEventLoopGroup boss = new NioEventLoopGroup(1);
        NioEventLoopGroup work = new NioEventLoopGroup();
        try {
            b.group(boss,work);
            b.channel(NioServerSocketChannel.class);
            b.localAddress(serverPort);
            b.option(ChannelOption.SO_KEEPALIVE,true);
            b.option(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT);
            b.childHandler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel socketChannel) throws Exception {
                    socketChannel.pipeline().addLast(new NettyEchoServerHandler());
                }
            });
            ChannelFuture channelFuture = b.bind().sync();
            System.out.println("服务启动成功，监听端口：" + channelFuture.channel().localAddress());
            ChannelFuture closeFuture = channelFuture.channel().closeFuture();
            closeFuture.sync();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            boss.shutdownGracefully();
            work.shutdownGracefully();
        }
    }

    public static void main(String[] args) {
        NettyEchoServer nettyEchoServer = new NettyEchoServer(7005);
        nettyEchoServer.runServer();
    }
}
