package com.sunny.dataStructure.linklist;

import java.util.Objects;

/**
 * 链表数据节点
 * @author JiaChang
 * @date 2019/1/22
 */
public class MyNode {

    private String key;

    private String value;

    /**
     * 下一个节点的引用
      */
    private MyNode nextNode;

    public MyNode() {
    }

    public MyNode(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public MyNode getNextNode() {
        return nextNode;
    }

    public void setNextNode(MyNode nextNode) {
        this.nextNode = nextNode;
    }

    public void displayInfo(){
        System.out.println("key:["+key+"],value:["+value+"]");
    }


    public boolean dataEquals(String key,String value){
        return Objects.equals(key,this.getKey()) && Objects.equals(value,this.getValue());
    }

}
