package com.ctg.test.learn.arithmetic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author liuyue
 * @date 2021/3/15 14:06
 * @Description:/给两个List a, b，实现两个List两个List的并集，即合并两个List，重复的元素只保留一个。
 * 不可以调用现成的集合方法
 * 有空闲限制，a的长度是m，b的长度是n，额外空间最多用m+n (不是O(m+n))
 * 尝试优化下，最少的时间复杂度是多少，在代码注释里给出最终的时间复杂度
 */
public class ListTest {
    public static void main(String[] args) {// union([ 2,1,3],[ 2,3,4])== [1,2,3,4]
        System.out.println(union(Arrays.asList(2, 1, 3), Arrays.asList(2, 3, 4)));

    }


    static List<Integer> union(List<Integer> a, List<Integer> b) {
        //在这儿实现
        List<Integer> rsp = new ArrayList<>(a.size()+b.size());

        System.out.println(rsp.size());
        rsp.addAll(a);
        System.out.println(rsp.size());
        System.out.println(Arrays.asList(rsp));

        return rsp;
    }


}
