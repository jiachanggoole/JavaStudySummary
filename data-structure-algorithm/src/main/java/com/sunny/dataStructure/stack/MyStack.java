package com.sunny.dataStructure.stack;

import com.alibaba.fastjson.JSON;

/**
 * 栈
 * @author JiaChang
 * @date 2019/1/25
 */
public class MyStack {

    /**
     * 栈的大小
     */
    private int size;

    /**
     * 栈顶元素的下标
     */
    private int top;

    /**
     * 栈的容器
     */
    private char[] stackArray;

    /**
     * 构造函数
     * @param size
     */
    public MyStack(int size) {
        stackArray = new char[size];
        //初始化栈的时候，栈内无元素，栈顶下标设为-1
        top = -1;
        this.size = size;
    }

    /**
     * 入栈，同时，栈顶元素的下标加一
     * @param elem
     */
    public void push(char elem) {
        //插入栈顶
        stackArray[++top] = elem;
    }

    /**
     * 出栈，删除栈顶元素，同时，栈顶元素的下标减一
     * @return
     */
    public char pop() {
        char element = stackArray[top];
        top-- ;
        return element;
    }

    /**
     * 查看栈顶元素，但不删除
     * @return
     */
    public char peek() {
        return stackArray[top];
    }

    /**
     * 判空
     * @return
     */
    public boolean isEmpty() {
        return (top == -1);
    }

    /**
     * 判满
     * @return
     */
    public boolean isFull() {
        return (top == size - 1);
    }

    public void displayInfo(){
        System.out.println(JSON.toJSONString(stackArray));
    }

}
