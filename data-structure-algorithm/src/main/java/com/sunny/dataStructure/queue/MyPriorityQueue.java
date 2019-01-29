package com.sunny.dataStructure.queue;

/**
 * 优先级队列
 *
 * @author JiaChang
 * @date 2019/1/25
 */
public class MyPriorityQueue {


    private int[] queArray;

    private int maxSize;

    /**
     * 队列长度
     */
    private int length;

    /**
     * 基准点
     */
    private int referencePoint;

    /**
     * 构造方法，初始化队列
     * @param maxSize
     * @param referencePoint
     */
    public MyPriorityQueue(int maxSize, int referencePoint) {
        this.maxSize = maxSize;
        this.referencePoint = referencePoint;
        queArray = new int[maxSize];
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

        //如果队列为空，插入到数组的第一个位置
        if (length == 0) {
            queArray[length++] = elem;
        } else {
            int i;
            for (i = length; i > 0; i--) {

                //待插入元素的距离
                int dis = Math.abs(elem - referencePoint);

                //当前元素的距离
                int curDis = Math.abs(queArray[i - 1] - referencePoint);

                //将比插入元素优先级高的元素后移一位
                if (dis >= curDis) {
                    queArray[i] = queArray[i - 1];
                } else {
                    break;
                }
            }
            queArray[i] = elem;
            length++;
        }
    }

    /**
     * 移除
     * @return
     * @throws Exception
     */
    public int remove() throws Exception {
        if (isEmpty()) {
            throw new Exception("队列为空，不能进行移除操作！");
        }
        int elem = queArray[--length];
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
        return queArray[length - 1];
    }

    /**
     * 返回队列长度
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


}
