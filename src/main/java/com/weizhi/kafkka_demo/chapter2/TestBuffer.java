package com.weizhi.kafkka_demo.chapter2;

import java.nio.ByteBuffer;

public class TestBuffer {
    public static void main(String[] args){
        System.out.println("测试ByteBuffer");
        System.out.println("before allocate:"+Runtime.getRuntime().freeMemory());
        //如果分配内存过小，调用Runtime.getRuntime().freeMemory()内存大小会不会变化？
        //超过多少内存JVM才能感受到
        ByteBuffer buffer=ByteBuffer.allocate(102400);
        System.out.println("buffer:"+buffer);
        System.out.println("after allocate:"+Runtime.getRuntime().freeMemory());

        //直接分配内存，对JVM没有影响
        ByteBuffer directBuffer=ByteBuffer.allocateDirect(102400);
        System.out.println("directBuffer:"+directBuffer);
        System.out.println("after directBuffer:"+Runtime.getRuntime().freeMemory());

        byte[] bytes=new byte[32];
        buffer=ByteBuffer.wrap(bytes);
        System.out.println("buffer:"+buffer);

        buffer=ByteBuffer.wrap(bytes,10,10);
        System.out.println("buffer:"+buffer);
    }
}
