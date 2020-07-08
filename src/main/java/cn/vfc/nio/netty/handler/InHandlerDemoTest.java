package cn.vfc.nio.netty.handler;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.embedded.EmbeddedChannel;

public class InHandlerDemoTest {
    public static void main(String[] args) {
        InHandlerDemo inHandlerDemo = new InHandlerDemo();
        ChannelInitializer<EmbeddedChannel> channelInitializer = new ChannelInitializer<EmbeddedChannel>() {
            @Override
            protected void initChannel(EmbeddedChannel embeddedChannel) throws Exception {
                embeddedChannel.pipeline().addLast(inHandlerDemo);
            }
        };
        EmbeddedChannel embeddedChannel = new EmbeddedChannel(channelInitializer);
        ByteBuf buffer = Unpooled.buffer();
        buffer.writeByte(1);
        embeddedChannel.writeInbound(buffer);
        embeddedChannel.flush();
        embeddedChannel.writeInbound(buffer);
        embeddedChannel.flush();
        embeddedChannel.close();
        try {
            Thread.sleep(Integer.MAX_VALUE);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
