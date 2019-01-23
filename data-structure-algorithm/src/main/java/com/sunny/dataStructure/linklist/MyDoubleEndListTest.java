package com.sunny.dataStructure.linklist;

/**
 * @author JiaChang
 * @date 2019/1/22
 */
public class MyDoubleEndListTest {


    public static void main(String[] args) {

        MyDoubleEndList myDoubleEndList = new MyDoubleEndList();
        myDoubleEndList.insertFirst(new MyNode("key3","value3"));
        myDoubleEndList.insertFirst(new MyNode("key1","value1"));
        myDoubleEndList.insertLast(new MyNode("key5","value5"));
        myDoubleEndList.insertFirst(new MyNode("key6","value6"));


        myDoubleEndList.displayInfo();


        myDoubleEndList.deleteFirst();
    }

}
