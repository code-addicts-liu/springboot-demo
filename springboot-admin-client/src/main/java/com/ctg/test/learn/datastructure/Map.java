package com.ctg.test.learn.datastructure;

/**
 * @author liuyue
 * @date 2021/3/31 14:18
 * @Description:
 */
public interface Map<K, V> {
    V get(K k);

    V put(K k, V v);

    int size();

    interface Entry<K, V> {
        K getKey();

        V getValue();
    }
}
