package cn.vfc.nio.netty.echoserver;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class NettyEchoClientHandler extends ChannelInboundHandlerAdapter {
    public static final NettyEchoClientHandler INSTANCE = new NettyEchoClientHandler();
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf buf = (ByteBuf)msg;
        int len = buf.readableBytes();
        byte[] arr = new byte[len];
        buf.getBytes(0,arr);
        System.out.println("客户端接收到的数据:" + new String(arr,"utf-8"));
        buf.release();
    }
}
