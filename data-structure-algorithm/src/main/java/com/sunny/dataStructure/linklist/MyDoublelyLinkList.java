package com.sunny.dataStructure.linklist;

/**
 * 双向链表
 * 每个节点维护着上一个节点引用以及下一个节点引用
 * @author JiaChang
 * @date 2019/1/22
 */
public class MyDoublelyLinkList {

    /**
     * 第一个节点信息
     */
    private MyDoubleNode first;

    /**
     * 最后一个节点信息
     */
    private MyDoubleNode last;

    public MyDoublelyLinkList() {
    }


    /**
     * 在链表前面插入节点
     * @param insertNode
     */
    public void inserFirst(MyDoubleNode insertNode){

        if(isEmpty()){
            last = insertNode;
        }else{
            first.setPrevious(insertNode);
            insertNode.setNextNode(first);
        }

        first = insertNode;
    }

    /**
     * 在链表最后插入节点
     * @param insertNode
     */
    public void insertEnd(MyDoubleNode insertNode){
        if(isEmpty()){
            first = insertNode;
        }else{
            last.setNextNode(insertNode);
            insertNode.setPrevious(last);
        }
        last = insertNode;
    }

    /**
     * 在指定节点后面插入一个节点
     * @param queryNode
     * @param insertNode
     */
    public void insertAfterOneNode(MyDoubleNode queryNode,MyDoubleNode insertNode){

        // 首先确定要找的节点是否存在
        queryNode = findNode(queryNode);

        if(queryNode != null){
            MyDoubleNode queryNodeOldNexNode = queryNode.getNextNode();
            // 结尾插入
            if(queryNodeOldNexNode == null){
                insertEnd(insertNode);
                return;
            }

            // 查询的节点和新增的节点建立联系
            queryNode.setNextNode(insertNode);
            insertNode.setPrevious(queryNode);

            // 新增的节点 和 原来的查询的节点的下一个节点建立联系
            insertNode.setNextNode(queryNodeOldNexNode);
            queryNodeOldNexNode.setPrevious(insertNode);

        }else{
            System.out.println("insertAfterOneNode 未查询到指定节点,");
            queryNode.displayInfo();
        }

    }

    /**
     * 删除首节点
     * @return
     */
    public MyDoubleNode deleteFirst(){

        if(isEmpty()){
            System.out.println("deleteFirst 链表为空...");
            return null;
        }

        MyDoubleNode temp = first;
        MyDoubleNode nextNode = first.getNextNode();

        // 只有一个节点
        if(nextNode == null){
            first = null;
            last = null;
            return temp;
        }

        nextNode.setPrevious(null);
        first = nextNode;

        return temp;
    }

    /**
     * 删除尾节点
     * @return
     */
    public MyDoubleNode deleteEnd(){

        if(isEmpty()){
            System.out.println("deleteFirst 链表为空...");
            return null;
        }

        MyDoubleNode temp = last;
        MyDoubleNode previousNode = temp.getPrevious();

        // 只有一个节点
        if(previousNode == null){
            first = null;
            last = null;
            return temp;
        }

        previousNode.setNextNode(null);
        last = previousNode;

        return temp;
    }

    /**
     * 查询指定的节点
     * @param myNode
     * @return
     */
    public MyDoubleNode findNode(MyDoubleNode myNode){

        MyDoubleNode current = first;

        while (current != null){

            boolean equalFlag = current.dataEquals(myNode.getKey(),myNode.getValue());
            if(equalFlag){
                return current;
            }

            current = current.getNextNode();

        }

        return null;
    }

    /**
     * 打印链表信息
     */
    public void displayInfo(){
        MyDoubleNode current = first;
        while (current != null){
            current.displayInfo();
            current = current.getNextNode();
        }
    }

    public boolean isEmpty(){
        return first == null;
    }
}
