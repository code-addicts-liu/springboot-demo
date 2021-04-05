package com.ctg.test.learn.java.Concurrent;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author liuyue
 * @date 2021/4/4 17:12
 * @Description:
 */
public class CasTest {
    private AtomicInteger atomicInteger = new AtomicInteger(0);
    private AtomicInteger num = new AtomicInteger(0);
    private int i = 0;

    /**
     * i             =981363
     * atomicInteger =1000000
     * 证实了当多个线程进行读写时，会出现结果不一致。
     * 所以在多线程运行时要使用原子操作，保证数据得一致性
     * <p>
     * cas操作 ：输入两个数值，一个旧值（期望操作前得值）和一个新值，在操作期间先比较旧值有没有发生变化，
     * 没有发生变化才进行替换成新值，发生变化了就不交换。
     * <p>
     * cas虽然解决很搞笑得解决了原子性操作，但是存在三大问题：
     * 1.ABA问题：
     * cas进行旧值对比得时候如果原来是A，后面变成了B，然后又变成了A，这样就欺骗了cas检查。
     * 解决办法：引入版本号，在变更变更前追加上版本号，每次变更得时候把版本加1，
     * 那么A->B->A就会变成1A->2B->3C
     * <p>
     * 从jdk1.5之后atomic包中提供了AtmiocStampedReference
     * 2.循环时间长开销大
     * 3.只能保证一个共享变量得原子操作
     * <p>
     * <p>
     * 执行时间：50
     *
     * @param args
     */
    public static void main(String[] args) {

        final CasTest casTest = new CasTest();
        List<Thread> threads = new ArrayList<Thread>(600);
        long startTime = System.currentTimeMillis();
        for (int j = 0; j < 100; j++) {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int i = 0; i < 100; i++) {
                        casTest.count();
                        casTest.safeCount();
                    }
                }
            });
            threads.add(thread);
        }
        for (Thread t : threads) {
            t.start();
        }

        //等待所有线程执行完成
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println(casTest.i);
        System.out.println(casTest.atomicInteger.get());
        long endTime = System.currentTimeMillis();
        System.out.println("执行时间：" + (endTime - startTime));
    }

    /**
     * 使用cas实现线程安全计数器
     */

    private void safeCount() {
        for (; ; ) {
            int i = atomicInteger.get();
            boolean suc = atomicInteger.compareAndSet(i, ++i);
            if (suc) {
                break;
            }
        }
    }



    /**
     * 给atmoic设定一个值
     */
    private void setCount(int newValue) {
        int i = atomicInteger.get();
        System.out.println("变更之前：" + i + "," + newValue);
        atomicInteger.set(0);
    }


    /**
     * 非线程安全计数器
     */
    private void count() {
        i++;
    }
}
