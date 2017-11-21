package io.nio;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

/**
 * @Description:
 * Java NIO中的DatagramChannel是一个能收发UDP包的通道。因为UDP是无连接的网络协议，所以不能像其它通道那样读取和写入。它发送和接收的是数据包。
 * @Todo:
 * Created by Grady on 2017/10/3.
 */
public class DatagramChannelPratice {

    static void datagramChannel() throws Exception{
        DatagramChannel datagramChannel = DatagramChannel.open();
        datagramChannel.socket().bind(new InetSocketAddress(99));

        //连接特定的地址
        datagramChannel.connect(new InetSocketAddress("jenkov.com", 80));

        //receive data
        //receive()方法会将接收到的数据包内容复制到指定的Buffer. 如果Buffer容不下收到的数据，多出的数据将被丢弃。
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        buffer.clear();
        datagramChannel.receive(buffer);

        //send data
        String sendStr = "The string send by DatagramChannel ..."+System.currentTimeMillis();
        ByteBuffer sendbuffer = ByteBuffer.allocate(1024);
        sendbuffer.clear();
        sendbuffer.put(sendStr.getBytes());
        sendbuffer.flip();
        int byteSent = datagramChannel.send(sendbuffer,new InetSocketAddress("http://localhost",80));

        //连接后可以时候read和write方法，但是在数据传送没有保障

    }
}
