package com.ctg.test.learn.java;

import java.util.ArrayList;

/**
 * @author liuyue
 * @date 2021/3/15 21:59
 * @Description:可通过 -Xmx -Xms 参数来分别指定最大堆和最小堆。
 */
public class JvmTest {

    public static final String VUALE = "VUALE";
    public static final JvmTest jvm = new JvmTest();

    public static void main(String[] args) throws InterruptedException {
        jvm.oomTest();
    }

    int math() {
        //1 是操作数  放在操作数栈里面
        //a 是局部变量  放在局部变更表
        //math是一个方法，放在方法区  栈中放动态链接里面  存指向地址
        //VUALE是一个常量，放在方法区
        //jvm 是一个静态变量，放在方法区 ，但new JvmTest();是一个对象，所以方法区放地址，指向堆。
        //return 返回一个值，放在方法出口
        int a = 1;
        int b = 2;
        int c = a + b;
        System.out.println(VUALE + c);
        return c;
    }

    //oom
    void oomTest() throws InterruptedException {
        ArrayList<JvmTest> oomTest = new ArrayList<>();
        //局部变更
        while (true) {
            //局部变更引用这对象
            oomTest.add(new JvmTest());
            Thread.sleep(10);
        }
    }
}
