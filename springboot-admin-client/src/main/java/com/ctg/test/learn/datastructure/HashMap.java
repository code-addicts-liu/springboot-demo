package com.ctg.test.learn.datastructure;


/**
 * @author liuyue
 * @date 2021/3/31 13:38
 * @Description:
 */

/**
 * hashmap
 * 数据结构：数组+链表 jdk1.8之后加入红黑树
 * 算法：hash算法 hashcode：取字节码  进行取模
 * hash算法带来了hash冲突、hash碰撞
 */

public class HashMap<K, V> implements Map<K, V> {
    Entry<K, V> table[] = null;
    int size = 0;

    public HashMap() {
        table = new Entry[16];
    }

    @Override
    public int size() {
        return size;
    }

    /**
     * 1.首先对key进行hash得到index
     * 2.判断数据index对象是否为空
     * 3.为空直接加入
     * 4.不为空，链表存储
     * 5.返回
     *
     * @param key
     * @param value
     * @return
     */
    @Override
    public V put(K key, V value) {
        int index = hash(key);
        Entry<K, V> kvEntry = table[index];
        if (null == kvEntry) {
            table[index] = new Entry(key, value, index, null);
            size++;
        } else {
            table[index] = new Entry(key, value, index, kvEntry);
        }
        return table[index].getValue();
    }


    private int hash(K key) {
        int index = key.hashCode() % 16;
        //注意对于key的hashcode可能是负值,但是数组的下标是从0开始的
        return index >= 0 ? index : -index;
    }


    /**
     * 1.通过key的hash得到index
     * 2.看index对象是否为空
     * 3.为空直接返回
     * 4.不为空，对比当前key是否相等，直到相等为止
     *
     * @param key
     * @return
     */
    @Override
    public V get(K key) {
        int index = hash(key);
        Entry<K, V> entry = findValue(table[index], key);
        return null == entry ? null : entry.getValue();
    }

    private Entry<K, V> findValue(Entry<K, V> kvEntry, K key) {
        if (key.equals(kvEntry.getKey()) || key == kvEntry.getKey()) {
            return kvEntry;
        } else {
            if (kvEntry.next != null) {
                return findValue(kvEntry.next, key);
            }
        }
        return null;
    }


    class Entry<K, V> implements Map.Entry<K, V> {
        K k;
        V v;
        int hash;
        Entry next;

        public Entry(K k, V v, int hash, Entry next) {
            this.k = k;
            this.v = v;
            this.hash = hash;
            this.next = next;
        }

        @Override
        public K getKey() {
            return k;
        }

        @Override
        public V getValue() {
            return v;
        }
    }

    public static void main(String[] args) {
        HashMap hashMap = new HashMap();
        hashMap.put("1", "liuyue");
        System.out.println("hash值：" + "1".hashCode() % 16 + hashMap.get("1"));
        int num = 32;
        int nums = num >> 1;//>>表示右移. 右移一位表示除2.相当于除2^n
        int nums2 = num << 1;//<<表示左移, 左移一位表示原来的值乘2^n
        System.out.println(nums);
        System.out.println(1 << 30);
        System.out.println(2^30);
//        java.util.HashMap map = new java.util.HashMap();
//        map.put();
    }

}
