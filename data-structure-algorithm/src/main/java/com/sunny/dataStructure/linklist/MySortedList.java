package com.sunny.dataStructure.linklist;

/**
 * 有序链表
 * @author JiaChang
 * @date 2019/1/22
 */
public class MySortedList {

    private MyNode first;

    public MySortedList() {
    }


    /**
     * 插入数据,相对于单链表区别在于插入，
     * 因为它要找到对应的插入位置
     * @param insertNode
     */
    public void insert(MyNode insertNode){
        MyNode current = first;
        MyNode previous = null;

        if(isEmpty()){
            first = insertNode;
            return;
        }

        while (current != null){
            // 条件符合，插入到前面
            if(current.getKey().compareTo(insertNode.getKey()) >0){
                insertNode.setNextNode(current);
                previous.setNextNode(insertNode);
                return;
            }else{
                // 条件不符合，继续循环
                previous = current;
                current = current.getNextNode();
            }
        }
        // 已经到最后了
        previous.setNextNode(insertNode);
    }

    public void display(){
        MyNode current = first;

        while (current != null){
            current.displayInfo();
            current = current.getNextNode();
        }

    }


    public boolean isEmpty() {
        return first == null;
    }

}
