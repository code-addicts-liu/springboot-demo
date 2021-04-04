package com.ctg.test.learn.java;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author liuyue
 * @date 2021/3/16 11:19
 * @Description:合并两个list，并去重
 */
public class ListMeger {
    /*
   给两个List a,b，实现两个List两个List的并集，即合并两个List，重复的元素只保留一个。
   不可以调用现成的集合方法
   有空闲限制，a的长度是m，b的长度是n，额外空间最多用m+n (不是O(m+n))
   尝试优化下，最少的时间复杂度是多少，在代码注释里给出最终的时间复杂度
    */
    public static class ListUnion {

        //快排进行排序O(nlogn)
        //双指针将重复元素移到末尾，删除末尾所有重复元素O(n)
        static List<Integer> union(List<Integer> a, List<Integer> b){
            //在这儿实现
            List<Integer> result = new ArrayList<>();
            /**
             * 将两个list的元素放到一个list中
             */
            result.addAll(a);
            result.addAll(b);
            /**
             * 使用快速排序对list进行排序，平均情况时间复杂度O(nlogn)
             */
            sort(result, 0, result.size()-1);
            /**
             * 使用双指针将重复的元素移动到list的末尾，时间复杂度O(n)
             * 再将list末尾的重复元素删除，时间复杂度O(1)
             */
            delete(result);
            return result;

        }

        /**
         * 每调用一次该方法，确实下标right的位置，然后递归地去确定
         * 下标right左、右两个区间的元素
         * @param target
         * @param left
         * @param right
         */
        static void sort(List<Integer> target, int left, int right) {
            if (left >= right) {
                return;
            }
            int tmpNum;
            int low = left;
            int high = left;
            int rightNum = target.get(right);
            // 将小于下标right的元素放左区间，大于的放右区间
            while (high < right) {
                if (target.get(high) <= rightNum) {
                    tmpNum = target.get(low);
                    target.set(low,target.get(high));
                    target.set(high,tmpNum);
                    low++;
                }
                high++;
            }
            // 左区间末尾+1的位置即为下标right元素的位置
            tmpNum = target.get(low);
            target.set(low,rightNum);
            target.set(right,tmpNum);

            sort(target,left,low-1);
            sort(target,low+1,high);
        }

        static void delete(List<Integer> target) {
            int left = 0;
            int right = 0;
            int high = target.size()-1;
            // 左指针记录的是不重复区间末尾
            while (right < high) {
                if (!target.get(right).equals(target.get(right+1))) {
                    left++;
                    target.set(left,target.get(right+1));
                }
                right++;
            }
            // 最后删除左指针以后的全部元素
            if (high >= left + 1) {
                target.subList(left + 1, high + 1).clear();
            }
        }

        /**
         * 最终的时间复杂度是O(nlogn)，空间复杂度是O(1)
         * @param args
         */
        public static void main(String[] args) {
            // union([2,1,3],[2,3,4]) == [1,2,3,4];
            System.out.println(union(Arrays.asList(2,1,3),Arrays.asList(2,3,4)));
        }
    }
}
