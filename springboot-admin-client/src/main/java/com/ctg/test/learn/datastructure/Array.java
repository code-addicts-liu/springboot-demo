package com.ctg.test.learn.datastructure;

import java.util.Arrays;

/**
 * @author liuyue
 * @date 2021/3/31 13:23
 * @Description:数组
 */
public class Array {
    /**
     * 数组采用一段连续的存储单元存储数据
     * 数组的特性：查询快O（1），插入、删除慢O（n）
     */
    public static void main(String[] args) {
        Integer integer[] = new Integer[10];
        integer[0] = 0;
        integer[1] = 1;
        integer[9] = 9;
        integer[9] = 10;
        System.out.println("Array:" + Arrays.asList(integer));
    }
}
