package io.nio;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * @Description:
 * @Todo:
 * Created by Grady on 2017/10/3.
 */
public class SocketChannelPratice {
    public static void main(String[] args) {

    }

    static void socketChannel()throws Exception{
        //Open a socketchannel
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.connect(new InetSocketAddress("http://localhost",80));

        //another way to open a socketchannel
        SocketChannel socketChannel1 = SocketChannel.open(new InetSocketAddress("http://localhost",80));

        //Define a bytebuffer
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        //read data from socketchannel to buffer
        //read()方法返回的int值表示读了多少字节进Buffer里。如果返回的是-1，表示已经读到了流的末尾（连接关闭了）。
        int byteread = socketChannel.read(buffer);

        //write data to socketchannel
        String newdata = "new string to socket ..."+System.currentTimeMillis();
        ByteBuffer buf = ByteBuffer.allocate(48);
        buf.clear();
        buf.put(newdata.getBytes());
        buf.flip();
        while (buf.hasRemaining()){
            socketChannel.write(buf);
        }


        //非阻塞模式（non-blocking mode）就可以在异步模式下调用connect(), read() 和write()
        socketChannel.configureBlocking(false);
        socketChannel.connect(new InetSocketAddress("http://localhost",80));
        while (!socketChannel.finishConnect()){
            //wait or do something
        }
        // 在非阻塞模式下 read（） write（） 可能在没有任何操作的时候返回，所以要注意返回值

        //close socketchannel
        socketChannel.close();
    }
}
