package io.nio;

import java.nio.channels.Channel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;

/**
 * Selector（选择器）是Java NIO中能够检测一到多个NIO通道，并能够知晓通道是否为诸如读写事件做好准备的组件。
 * 这样，一个单独的线程可以管理多个channel，从而管理多个网络连接。
 * 仅用单个线程来处理多个Channels的好处是，只需要更少的线程来处理通道。
 * 事实上，可以只用一个线程处理所有的通道。
 * 与Selector一起使用时，Channel必须处于非阻塞模式下。
 * 这意味着不能将FileChannel与Selector一起使用，因为FileChannel不能切换到非阻塞模式。而套接字通道都可以。
 * Created by Grady on 2017/9/16.
 */
public class SelectorPratice {
    public static void main(String[] args) {

    }

    static void startAndRegister()throws Exception{
        SocketChannel clientChannel = SocketChannel.open();

        /**
         * 通过调用Selector.open()方法创建一个Selector
         */
        Selector selector = Selector.open();

        /**
         *注意register()方法的第二个参数。这是一个“interest集合”，意思是在通过Selector监听Channel时对什么事件感兴趣。可以监听四种不同类型的事件：
         * Connect -- SelectionKey.OP_CONNECT
         * Accept  -- SelectionKey.OP_ACCEPT
         * Read    -- SelectionKey.OP_READ
         * Write   -- SelectionKey.OP_WRITE
         * 如果你对不止一种事件感兴趣，那么可以用“位或”操作符将常量连接起来，如下：
         * int interestSet = SelectionKey.OP_READ | SelectionKey.OP_WRITE;
         */
        clientChannel.configureBlocking(false);
        /**
         * interest集合是你所选择的感兴趣的事件集合
         */
        SelectionKey selectionKey = clientChannel.register(selector,SelectionKey.OP_READ);

        int interestSet = selectionKey.interestOps();

        boolean isInterestedInAccept  = (interestSet & SelectionKey.OP_ACCEPT) == SelectionKey.OP_ACCEPT;

        boolean isInterestedInConnect = (interestSet & SelectionKey.OP_CONNECT) == SelectionKey.OP_CONNECT;

        boolean isInterestedInRead    = (interestSet & SelectionKey.OP_READ)==SelectionKey.OP_READ;

        boolean isInterestedInWrite   = (interestSet & SelectionKey.OP_WRITE)==SelectionKey.OP_WRITE;

        /**
         * ready 集合是通道已经准备就绪的操作的集合。
         * 在一次选择(Selection)之后，你会首先访问这个ready set。
         */
        int readySet = selectionKey.readyOps();
        selectionKey.isAcceptable();
        selectionKey.isConnectable();
        selectionKey.isReadable();
        selectionKey.isWritable();

        /**
         * 从SelectionKey访问Channel和Selector很简单。
         */
        Channel channel  = selectionKey.channel();
        selector = selectionKey.selector();

        /**
         * 附加对象
         * 可以将一个对象或者更多信息附着到SelectionKey上，这样就能方便的识别某个给定的通道。
         */
        Object theObject = new Object();
        selectionKey.attach(theObject);

        //还可以在用register()方法向Selector注册Channel的时候附加对象
        SelectionKey key = clientChannel.register(selector, SelectionKey.OP_READ, theObject);
        //获取附加信息
        Object attachedObj = selectionKey.attachment();

        /**
         * 通过Selector选择Channel
         * 一旦向Selector注册了一或多个通道，就可以调用几个重载的select()方法。这些方法返回你所感兴趣的事件（如连接、接受、读或写）已经准备就绪的那些通道
         * select()方法返回的int值表示有多少通道已经就绪。亦即，自上次调用select()方法后有多少通道变成就绪状态。
         * 如果调用select()方法，因为有一个通道变成就绪状态，返回了1，若再次调用select()方法，如果另一个通道就绪了，它会再次返回1。
         * 如果对第一个就绪的channel没有做任何操作，现在就有两个就绪的通道，但在每次select()方法调用之间，只有一个通道就绪了。
        */
        selector.select();//阻塞到至少有一个通道在你注册的事件上就绪了。
        selector.select(1000);//和select()一样，除了最长会阻塞timeout毫秒(参数)。
        selector.selectNow();//不会阻塞，不管什么通道就绪都立刻返回（译者注：此方法执行非阻塞的选择操作。如果自从前一次选择操作后，没有通道变成可选择的，则此方法直接返回零。）。
    }
}
