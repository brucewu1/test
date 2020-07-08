package cn.vfc.nio.netty;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class NettyDidcardHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf buf = (ByteBuf)msg;
        try {
            while (buf.isReadable()){
                System.out.print(buf.readByte());
            }
            System.out.println();
        }finally {
            //buf.release(buf.readByte());
        }
    }
}
