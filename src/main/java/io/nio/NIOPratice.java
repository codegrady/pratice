package io.nio;

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by Grady on 2017/9/16.
 */
public class NIOPratice {
    public static void main(String[] args) {
        pathExite();
    }
    static void pathTest(){
        Path path = Paths.get("I:\\Desktop\\开发者文档\\","pssh用法.txt");
        path.getParent();
        System.out.println("now: "+path.toAbsolutePath());
        System.out.println(path.getParent());
        //  "/"是指向当前驱动器的根  "." 当前目录  ".." 上级目录  toAbsolutePath()获得绝对路径
        Path path1 = Paths.get("/");
        System.out.println("path1 = " + path1.toAbsolutePath());

        //normalize--标准路径
        Path originalPath = Paths.get("I:\\Desktop\\开发者文档\\","..\\开发者文档\\pssh用法.txt");
        System.out.println(originalPath.normalize());
    }

    static void pathExite(){
        Path path = Paths.get("I:\\Desktop\\开发者文档\\test\\");
        System.out.println("path = " + path);
    }

}
