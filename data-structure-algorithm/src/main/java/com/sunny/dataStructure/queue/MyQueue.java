package com.sunny.dataStructure.queue;

import com.alibaba.fastjson.JSON;

/**
 * 队列
 *
 * @author JiaChang
 * @date 2019/1/25
 */
public class MyQueue {

    private int[] queArray;

    private int maxSize;

    /**
     * 存储队头元素的下标
     */
    public int front;

    /**
     * 存储队尾元素的下标
     */
    public int last;

    /**
     * 队列长度
     */
    private int length;

    /**
     * 构造方法，初始化队列
     * @param maxSize
     */
    public MyQueue(int maxSize) {
        this.maxSize = maxSize;
        queArray = new int[maxSize];
        front = 0;
        last = -1;
        length = 0;
    }

    /**
     * 插入
     * @param elem
     * @throws Exception
     */
    public void insert(int elem) throws Exception {
        if (isFull()) {
            throw new Exception("队列已满，不能进行插入操作！");
        }
        //如果队尾指针已到达数组的末端，插入到数组的第一个位置
        if (last == maxSize - 1) {
            last = -1;
        }
        queArray[++last] = elem;
        length++;
    }

    /**
     * 移除 这里其实只是指针进行移动，不然所有的元素都得移动，效率低
     * @return
     * @throws Exception
     */
    public int remove() throws Exception {
        if (isEmpty()) {
            throw new Exception("队列为空，不能进行移除操作！");
        }
        int elem = queArray[front++];
        //如果队头指针已到达数组末端，则移到数组第一个位置
        if (front == maxSize) {
            front = 0;
        }
        length--;
        return elem;
    }

    /**
     * 查看队头元素
     * @return
     * @throws Exception
     */
    public int peek() throws Exception {
        if (isEmpty()) {
            throw new Exception("队列内没有元素！");
        }
        return queArray[front];
    }

    /**
     * 获取队列长度
     * @return
     */
    public int size() {
        return length;
    }

    /**
     * 判空
     * @return
     */
    public boolean isEmpty() {
        return (length == 0);
    }

    /**
     * 判满
     * @return
     */
    public boolean isFull() {
        return (length == maxSize);
    }

    public void displayInfo(){
        System.out.println(JSON.toJSONString(queArray));
    }

    public static void main(String[] args) throws Exception{


        MyQueue myQueue = new MyQueue(10);

        for (int i = 0; i < 10; i++) {
            myQueue.insert(i);
        }

        for (int i = 0; i < 5; i++) {
            myQueue.remove();
        }

        /*for (int i = 0; i < 5; i++) {
            myQueue.insert(i);
        }*/


        System.out.println("front:" + myQueue.front);
        System.out.println("last:" + myQueue.last);
        System.out.println("length:" + myQueue.length);
        myQueue.displayInfo();
    }

}
