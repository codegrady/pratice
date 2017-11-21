package io.nio;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**
 * Created by Grady on 2017/10/3.
 */
public class ServerSocketChannelPratice {

    static void serverSocketChannel() throws Exception{
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.socket().bind(new InetSocketAddress(999));
        serverSocketChannel.configureBlocking(false);//non-blockding mode

        while (true){
            SocketChannel socketChannel = serverSocketChannel.accept();
            //do sth with socketChannel.....
            ByteBuffer buffer = ByteBuffer.allocate(48);
            socketChannel.read(buffer);
        }

//        serverSocketChannel.close();
    }
}
