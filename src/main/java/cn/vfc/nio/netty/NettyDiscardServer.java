package cn.vfc.nio.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class NettyDiscardServer {
    private final int serverPort;
    ServerBootstrap b = new ServerBootstrap();

    public NettyDiscardServer(int serverPort) {
        this.serverPort = serverPort;
    }

    public void runServer(){
        //创建反应器线程组
        NioEventLoopGroup bossEventLoop = new NioEventLoopGroup(1);
        NioEventLoopGroup workEventLoop = new NioEventLoopGroup();
        try {
            b.group(bossEventLoop,workEventLoop);
            //设置nio类型通道
            b.channel(NioServerSocketChannel.class);
            b.localAddress(serverPort);
            b.option(ChannelOption.SO_KEEPALIVE,true);
            b.option(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT);
            //装配子通道
            b.childHandler(new ChannelInitializer<SocketChannel>() {
                //有连接到达时会创建一个通道
                @Override
                protected void initChannel(SocketChannel socketChannel) throws Exception {
                    socketChannel.pipeline().addLast(new NettyDidcardHandler());
                }
            });
            ChannelFuture channelFuture = b.bind().sync();
            System.out.println("服务启动成功，监听端口：" + channelFuture.channel().localAddress());
            ChannelFuture closeFuture = channelFuture.channel().closeFuture();
            closeFuture.sync();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            workEventLoop.shutdownGracefully();
            bossEventLoop.shutdownGracefully();
        }
    }

    public static void main(String[] args) {
        int serverPort = 7005;
        new NettyDiscardServer(serverPort).runServer();
    }
}
