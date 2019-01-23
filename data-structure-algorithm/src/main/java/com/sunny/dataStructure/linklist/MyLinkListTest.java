package com.sunny.dataStructure.linklist;

/**
 * @author JiaChang
 * @date 2019/1/22
 */
public class MyLinkListTest {

    public static void main(String[] args) {

        MyLinkList myLinkList = new MyLinkList();

        System.out.println("\n------插入开始------\n");

        myLinkList.insertNode("key1","value1");
        myLinkList.insertNode("key5","value5");
        myLinkList.insertNode("key4","value4");
        myLinkList.insertNode("key2","value2");
        myLinkList.insertNode("key3","value3");
        myLinkList.insertNode("key13","value13");
        myLinkList.insertNode("key23","value23");

        myLinkList.displayLinkListInfo();


        System.out.println("\n------删除首节点开始------");
        MyNode deleteFirstNode = myLinkList.deleteFirstNode();
        if(deleteFirstNode != null){
            deleteFirstNode.displayInfo();
        }else{
            System.out.println("\n------删除首节点失败------");
        }
        myLinkList.displayLinkListInfo();



        System.out.println("\n------删除指定节点开始------");
        MyNode deleteNode = myLinkList.deleteNode("key24","value2");
        if(deleteNode != null){
            deleteNode.displayInfo();
        }else {
            System.out.println("------删除指定节点失败------");
        }
        myLinkList.displayLinkListInfo();


        System.out.println("\n------查找指定节点开始------");
        MyNode findNode = myLinkList.findNode("key4","value4q");
        if(findNode != null){
            findNode.displayInfo();
        }else {
            System.out.println("------查找指定节点开始失败------");
        }
    }

}
