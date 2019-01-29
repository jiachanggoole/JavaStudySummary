package com.sunny.dataStructure.stack;

import com.alibaba.fastjson.JSON;

/**
 * @author JiaChang
 * @date 2019/1/25
 */
public class MyStackTest {


    public static void main(String[] args) {

        MyStack myStack = new MyStack(10);

        for (int i = 0; i < 6; i++) {
            myStack.push((i+"").charAt(0));
        }

        System.out.println("myStack isEmpty:"+myStack.isEmpty());
        System.out.println("myStack isFull:"+myStack.isFull());

        for (int i = 0; i < 5; i++) {
            System.out.println(myStack.pop());
        }


        System.out.println(myStack.peek());

        myStack.displayInfo();
    }

}
