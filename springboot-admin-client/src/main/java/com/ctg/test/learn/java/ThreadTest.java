package com.ctg.test.learn.java;

import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.concurrent.Callable;

/**
 * @author liuyue
 * @date 2021/3/23 10:01
 * @Description: (1) thread.setDaemon(true)必须在thread.start()之前设置，
 * 否则会跑出一个IllegalThreadStateException异常。
 * 你不能把正在运行的常规线程设置为守护线程。
 * (2) 在Daemon线程中产生的新线程也是Daemon的。
 * (3) 不要认为所有的应用都可以分配给Daemon来进行服务，
 * 比如读写操作或者计算逻辑。
 * 因为你不可能知道在所有的User完成之前，
 * Daemon是否已经完成了预期的服务任务。
 * 一旦User退出了，可能大量数据还没有来得及读入或写出，
 * 计算任务也可能多次运行结果不一样。这对程序是毁灭性的。
 * 造成这个结果理由已经说过了：一旦所有User Thread离开了，虚拟机也就退出运行了。
 */

/*
实现线程有几种方法：
1.继承Thread类

2.实现Runnable接口

3.使用Callable和Future
 */
    @Transactional
public class ThreadTest {
    public static void main(String[] args) {

//        ThreadTest daemonThread = new ThreadTest();
//        // 设定 daemonThread 为 守护线程，default false(非守护线程)
//        daemonThread.setDaemon(true);
//        // 验证当前线程是否为守护线程，返回 true 则为守护线程
//        boolean daemon = daemonThread.isDaemon();
//        System.out.println("当前线程是否为守护线程：" + daemon);
//        daemonThread.start();


        /**
         * 测试守护线程
         */
//        Runnable tr = new TestRunnable();
//        Thread thread = new Thread(tr);
//        thread.setDaemon(true); //设置守护线程
//        thread.start(); //开始执行分进程

        /**
         *
         */
        Thread t1 = new MyCommon();
        Thread t2 = new Thread(new MyDaemon());
        t2.setDaemon(true); //设置为守护线程
        t2.start();
        t1.start();
        //前台线程是保证执行完毕的，后台线程还没有执行完毕就退出了。 　　
        //实际上：JRE判断程序是否执行结束的标准是所有的前台执线程行完毕了，而不管后台线程的状态，因此，在使用后台县城时候一定要注意这个问题。

        /**
         * 测试三种运行方法
         */
//        new CreateThread().start();
//        new CreateThread().start();
//
//        RunnableThread runnableTest = new RunnableThread();
//        new Thread(runnableTest, "RunnableThread-1").start();
//        new Thread(runnableTest, "RunnableThread-2").start();
//
//        CallableThread callableTest = new CallableThread();
//        FutureTask<Integer> futureTask = new FutureTask<>(callableTest);
//        new Thread(futureTask).start();
//        try {
//            System.out.println("子线程的返回值: " + futureTask.get());
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } catch (ExecutionException e) {
//            e.printStackTrace();
//        }


    }

}

class TestRunnable implements Runnable {
    @Override
    public void run() {
        try {
            Thread.sleep(1000);//守护线程阻塞1秒后运行
            File f = new File("daemon.txt");
            FileOutputStream os = new FileOutputStream(f, true);
            os.write("daemon".getBytes());
        } catch (IOException e1) {
            e1.printStackTrace();
        } catch (InterruptedException e2) {
            e2.printStackTrace();
        }
    }
}

class MyCommon extends Thread {
    public void run() {
        for (int i = 0; i < 5; i++) {
            System.out.println("线程1第" + i + "次执行！");
            try {
                Thread.sleep(7);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}

class MyDaemon implements Runnable {
    public void run() {
        for (long i = 0; i < 9999999L; i++) {
            System.out.println("后台线程第" + i + "次执行！");
            try {
                Thread.sleep(7);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

/**
 * （1）继承Thread类并重写run方法
 * <p>
 * （2）创建线程对象
 * <p>
 * （3）调用该线程对象的start()方法来启动线程
 */
class CreateThread extends Thread {
    @Override
    public void run() {
        int i;
        for (i = 0; i < 3; i++) {
            System.out.println(Thread.currentThread().getName() + " is running: " + i);
        }
    }
}

/**
 * （1）定义一个类实现Runnable接口，并重写该接口的run()方法
 * <p>
 * （2）创建 Runnable实现类的对象，作为创建Thread对象的target参数，此Thread对象才是真正的线程对象
 * <p>
 * （3）调用线程对象的start()方法来启动线程
 */
class RunnableThread implements Runnable {

    @Override
    public void run() {
        int i;
        for (i = 0; i < 10; i++) {
            System.out.println(Thread.currentThread().getName() + " is running: " + i);
        }
    }
}

/**
 * （1）定义一个类实现Callable接口，并重写call()方法，该call()方法将作为线程执行体，并且有返回值
 * <p>
 * （2）创建Callable实现类的实例，使用FutureTask类来包装Callable对象
 * <p>
 * （3）使用FutureTask对象作为Thread对象的target创建并启动线程
 * <p>
 * （4）调用FutureTask对象的get()方法来获得子线程执行结束后的返回值
 */
class CallableThread implements Callable {

    @Override
    public Integer call() throws Exception {
        int sum = 0;
        for (int i = 1; i < 101; i++) {
            sum += i;
        }
        System.out.println(Thread.currentThread().getName() + " is running: " + sum);
        return sum;
    }
}
