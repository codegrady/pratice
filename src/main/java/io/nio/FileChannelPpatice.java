package io.nio;

import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Created by Grady on 2017/9/16.
 */
public class FileChannelPpatice {
    public static void main(String[] args) throws Exception{
        transferTo();
    }

    static void readFile()throws Exception{
        RandomAccessFile aFile = new RandomAccessFile("E:\\Workspaces\\Practice\\pratice\\nioPath\\nioTest.txt","rw");
        FileChannel inChannel = aFile.getChannel();
        ByteBuffer buffer = ByteBuffer.allocate(48);//分配字节缓冲区
        int bytesRead = inChannel.read(buffer);//将字节序列从此通道读入给定的缓冲区。此通道的当前文件位置开始读取字节，然后使用实际读取的字节数更新该文件位置。
        while (bytesRead!= -1){
            System.out.println("bytesRead = " + bytesRead);
            buffer.flip();//使缓冲区为一系列新的通道写入或相对获取 操作做好准备：它将限制设置为当前位置，然后将位置设置为 0。

            while (buffer.hasRemaining()){ //告知在当前位置和限制之间是否有元素。
                System.out.print((char)buffer.get());
            }
            buffer.clear();//清空buffer 停止循环
            bytesRead = inChannel.read(buffer);
            System.out.println("\nbytesRead = " + bytesRead);
        }
        aFile.close();
    }

    static void transferFrom()throws Exception{
        RandomAccessFile fromFile = new RandomAccessFile("E:\\Workspaces\\Practice\\pratice\\nioPath\\from.txt","rw");
        RandomAccessFile toFile = new RandomAccessFile("E:\\Workspaces\\Practice\\pratice\\nioPath\\to.txt","rw");

        FileChannel fromChannel = fromFile.getChannel();
        FileChannel toChannel = toFile.getChannel();
        ByteBuffer buffer = ByteBuffer.allocate((int)toChannel.size());
        toChannel.read(buffer);
        fromChannel.transferTo(0,fromChannel.size(),toChannel);

    }

    static void transferTo()throws Exception{
        RandomAccessFile fromFile = new RandomAccessFile("E:\\Workspaces\\Practice\\pratice\\nioPath\\from.txt","rw");
        RandomAccessFile toFile = new RandomAccessFile("E:\\Workspaces\\Practice\\pratice\\nioPath\\to.txt","rw");

        FileChannel fromChannel = fromFile.getChannel();
        FileChannel toChannel = toFile.getChannel();
        ByteBuffer buffer = ByteBuffer.allocate((int)toChannel.size());
        toChannel.read(buffer);
        toChannel.transferFrom(fromChannel,toChannel.size(),fromChannel.size());
    }
}
