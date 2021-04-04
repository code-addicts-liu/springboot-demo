package com.ctg.test.learn.datastructure;


/**
 * @author liuyue
 * @date 2021/3/31 13:22
 * @Description:链表
 */

public class Node {
    /**
     * 链表是一个物理存储单元上非连续、非顺序的存储结构
     * 链表的特性：查询慢O(N)，插入删除快O(1),
     */
    private Object data;
    public Node next;

    public Node getNext() {
        return next;
    }

    public Object getData() {
        return data;
    }

    public Node(Object data) {
        this.data = data;
    }

    public void setNext(Node next) {
        this.next = next;
    }

    @Override
    public String toString() {
        return "Node{" +
                "data=" + data +
                ", next=" + next +
                '}';
    }

    public static void main(String[] args) {
        Node node = new Node("liu");
        node.next = new Node("yue");
        System.out.println(node);
    }
}
