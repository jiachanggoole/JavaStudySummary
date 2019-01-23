package com.sunny.dataStructure.linklist;

/**
 * 双端链表
 * 同时维护第一个节点和最后一个节点的信息
 * @author JiaChang
 * @date 2019/1/22
 */
public class MyDoubleEndList {

    /**
     * 第一个节点信息
     */
    private MyNode first;

    /**
     * 最后一个节点信息
     */
    private MyNode last;

    public MyDoubleEndList() {
    }

    /**
     * 在链表前端插入
     * @param insertNode
     */
    public void insertFirst(MyNode insertNode){
        if(isEmpty()){
            last = insertNode;
        }
        insertNode.setNextNode(first);
        first = insertNode;
    }

    /**
     * 在链表末端插入数据
     * @param insertNode
     */
    public void insertLast(MyNode insertNode){
        if(isEmpty()){
            first = insertNode;
        }else {
            last.setNextNode(insertNode);
        }
        last = insertNode;
    }

    /**
     * 删除首节点
     * @return
     */
    public MyNode deleteFirst(){
        if(isEmpty()){
            return null;
        }
        MyNode temp = first;
        first = first.getNextNode();
        // 只有一个节点
        if(temp.getNextNode() == null){
            last = null;
        }
        return temp;
    }

    public void displayInfo(){
        MyNode current = first;
        while (current != null){
            current.displayInfo();
            current = current.getNextNode();
        }

    }

    public boolean isEmpty(){
        return first == null;
    }


}
