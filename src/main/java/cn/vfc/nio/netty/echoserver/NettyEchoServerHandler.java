package cn.vfc.nio.netty.echoserver;

import io.netty.buffer.ByteBuf;
import io.netty.channel.*;

@ChannelHandler.Sharable
public class NettyEchoServerHandler extends ChannelInboundHandlerAdapter {
    public static final NettyEchoServerHandler INSTANCE = new NettyEchoServerHandler();
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf in = (ByteBuf)msg;
        System.out.println("msg type:" + (in.hasArray()?"堆内存" : "直接内存"));
        int len = in.readableBytes();
        byte[] arr =new byte[len];
        in.getBytes(0,arr);
        System.out.println("server received：" + new String(arr,"utf-8"));
        System.out.println("写回前,msg.refCnt：" + ((ByteBuf) msg).refCnt());
        //写回数据，异步任务
        ChannelFuture f = ctx.writeAndFlush(msg);
        f.addListener((ChannelFuture futureListen) -> {
            System.out.println("写回后，msg.refCnt：" + ((ByteBuf) msg).refCnt());
        });
    }
}
