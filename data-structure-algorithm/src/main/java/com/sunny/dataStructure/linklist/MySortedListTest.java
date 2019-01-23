package com.sunny.dataStructure.linklist;

/**
 * @author JiaChang
 * @date 2019/1/22
 */
public class MySortedListTest {


    public static void main(String[] args) {
        MySortedList mySortedList = new MySortedList();

        mySortedList.insert(new MyNode("a","value-a"));
        mySortedList.insert(new MyNode("f","value-f"));
        mySortedList.insert(new MyNode("b","value-b"));
        mySortedList.insert(new MyNode("c","value-c"));

        mySortedList.display();

    }

}
