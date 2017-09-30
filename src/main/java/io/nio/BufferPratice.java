package io.nio;

import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Buffer用于和NIO通道进行交互。如你所知，数据是从通道读入缓冲区，从缓冲区写入到通道中的。
 * 使用Buffer读写数据一般遵循以下四个步骤：
 * 1、写入数据到Buffer
 * 2、调用flip()方法
 * 3、从Buffer中读取数据
 * 4、调用clear()方法或者compact()方法
 */
public class BufferPratice {
    public static void main(String[] args)throws Exception {

        gather();
    }

    /**
     * Buffer的基本用法
     * 读文件
     * @throws Exception
     */
    static void bufferBasic()throws Exception{
        RandomAccessFile aFile = new RandomAccessFile("E:\\Workspaces\\Practice\\pratice\\nioPath\\nioTest.txt","rw");
        FileChannel inChannel = aFile.getChannel();
        ByteBuffer buffer = ByteBuffer.allocate(100);//分配字节缓冲区
        int bytesRead = inChannel.read(buffer);//将字节序列从此通道读入给定的缓冲区。此通道的当前文件位置开始读取字节，然后使用实际读取的字节数更新该文件位置。
        while (bytesRead!= -1){
            System.out.println("bytesRead = " + bytesRead);
            buffer.flip();//flip方法将Buffer从写模式切换到读模式。调用flip()方法会将position设回0，并将limit设置成之前position的值。
            System.out.println(new String(buffer.array(),0,bytesRead,"UTF-8"));

            System.out.println("====================");
            byte[] dist = new byte[bytesRead];
            buffer.get(dist,0,bytesRead);//buffer get后position=limit-》没有可读数据
            System.out.println(new String(buffer.array(),0,bytesRead,"UTF-8"));
            System.out.println("====================");
            buffer.rewind();//buffer 重读 position=0
            while (buffer.hasRemaining()){ //告知在当前位置和限制之间是否有元素。
                System.out.print((char)buffer.get());// read 1 byte at a time
            }
            buffer.clear();//清空buffer 
            bytesRead = inChannel.read(buffer);//继续写buffer
//            System.out.println(inChannel.write(buffer));
        }
        aFile.close();
    }

    static void writeFile()throws Exception{
        RandomAccessFile aFile = new RandomAccessFile("E:\\Workspaces\\Practice\\pratice\\nioPath\\write.txt","rws");
        FileChannel writeChannel = aFile.getChannel();
        String str = "我叫宫宇！ \n" +
                "我在用nio写文件！";
        byte[] bytes = str.getBytes("UTF-8");
        System.out.println(bytes.length);
        ByteBuffer buffer = ByteBuffer.allocate(bytes.length);
//        writeChannel.read(buffer);
        System.out.println("buffer = " + buffer);

        buffer.put(bytes);//buffer默认是写模式！
        buffer.flip();

        int w=writeChannel.write(buffer);
        System.out.println("w = " + w);

        aFile.close();
    }

    /**
     * 分散 将channel中的数据分别放到多个buffer中，第一个满了放到下一个buffer中
     */
    static void scatter()throws Exception{
        RandomAccessFile file = new RandomAccessFile("E:\\Workspaces\\Practice\\pratice\\nioPath\\nioTest.txt","rw");
        FileChannel channel = file.getChannel();
        ByteBuffer head = ByteBuffer.allocate(20);
        ByteBuffer body = ByteBuffer.allocate(80);
        ByteBuffer[] buffers = {head,body};
        channel.read(buffers);
        head.flip();
        body.flip();
        System.out.println(new String(head.array(),0,head.array().length,"UTF-8"));

        int size = body.limit() - body.position();
        System.out.println(new String(body.array(),0,size,"UTF-8"));
        file.close();
    }

    /**
     * 聚集  将多个buffer中的数据放到一个channel中
     */
    static void gather()throws Exception{
        RandomAccessFile aFile = new RandomAccessFile("E:\\Workspaces\\Practice\\pratice\\nioPath\\write.txt","rws");
        FileChannel writeChannel = aFile.getChannel();
        int len = (int)writeChannel.size();
        System.out.println("channel size = " + len);
        //读出原有数据
        ByteBuffer buffer = ByteBuffer.allocate(len);
        writeChannel.read(buffer);//读channel数据会保存原有数据

//        buffer.flip();

        String headStr ="这是NIO的聚集操作！\n" +
                "现在正在写入第一个buffer的数据！\n\n";
        String bodyStr = "现在正在写入第二个buffer的数据！\n" +
                "我现在正在写第二个bugger的数据！\n\n";
        byte[] headByte = headStr.getBytes("utf-8");
        byte[] bodyByte = bodyStr.getBytes("utf-8");
        ByteBuffer head = ByteBuffer.allocate(headByte.length);
        ByteBuffer body = ByteBuffer.allocate(bodyByte.length);
        head.put(headByte);
        body.put(bodyByte);
        ByteBuffer[] buffers = {head,body};
        head.flip();
        body.flip();
        writeChannel.write(buffers);
        System.out.println("channel size = " + writeChannel.size());
        aFile.close();
    }
}
