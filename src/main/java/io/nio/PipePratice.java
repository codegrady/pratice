package io.nio;

import java.nio.ByteBuffer;
import java.nio.channels.Pipe;

/**
 * @Description:
 *  Java NIO 管道是2个线程之间的单向数据连接。
 *  Pipe有一个source通道和一个sink通道。数据会被写到sink通道，从source通道读取。
 * @Todo:
 * Created by Grady on 2017/10/3.
 */
public class PipePratice {

    public static void main(String[] args)throws Exception {
        pipe();
    }


    static void pipe() throws Exception{
        //Create a pipe
        Pipe pipe = Pipe.open();
        //write data into sinkchannel
        Pipe.SinkChannel sinkChannel = pipe.sink();
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        buffer.clear();
        buffer.put("new data".getBytes());
        buffer.flip();
        while (buffer.hasRemaining()){
            sinkChannel.write(buffer);
        }
        //read data from sourcechannel
        Pipe.SourceChannel sourceChannel = pipe.source();
        ByteBuffer readBuffer = ByteBuffer.allocate(1024);
        int readNum =sourceChannel.read(readBuffer);
        System.out.println("readNum = " + readNum);
        readBuffer.flip();
        while (readBuffer.hasRemaining()){
            System.out.print((char)readBuffer.get());
        }
    }

}
