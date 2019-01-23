package com.sunny.dataStructure.linklist;

/**
 * 双向引用的节点
 * @author JiaChang
 * @date 2019/1/23
 */
public class MyDoubleNode extends MyNode{

    /**
     * 上一个节点的引用
     */
    private MyDoubleNode previous;

    /**
     * 下一个节点的引用
     */
    private MyDoubleNode nextNode;


    public MyDoubleNode() {
    }

    public MyDoubleNode(String key, String value) {
        super(key, value);
    }

    public MyDoubleNode getPrevious() {
        return previous;
    }

    public void setPrevious(MyDoubleNode previous) {
        this.previous = previous;
    }

    @Override
    public MyDoubleNode getNextNode() {
        return nextNode;
    }

    public void setNextNode(MyDoubleNode nextNode) {
        this.nextNode = nextNode;
    }
}
