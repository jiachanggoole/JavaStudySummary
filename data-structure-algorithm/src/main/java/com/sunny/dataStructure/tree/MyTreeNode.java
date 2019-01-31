package com.sunny.dataStructure.tree;

/**
 * @author JiaChang
 * @date 2019/1/30
 */
public class MyTreeNode {


    /**
     * 节点值
     */
    private Integer value;

    /**
     * 节点描述
     */
    private String name;

    /**
     * 左子节点
     */
    private MyTreeNode leftChild;

    /**
     * 右子节点
     */
    private MyTreeNode rightChild;

    public MyTreeNode() {
    }

    public MyTreeNode(int value, String name) {
        this.value = value;
        this.name = name;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public MyTreeNode getLeftChild() {
        return leftChild;
    }

    public void setLeftChild(MyTreeNode leftChild) {
        this.leftChild = leftChild;
    }

    public MyTreeNode getRightChild() {
        return rightChild;
    }

    public void setRightChild(MyTreeNode rightChild) {
        this.rightChild = rightChild;
    }

    public void displayInfo() {
        System.out.println("value:[" + value + "],name:[" + name + "]");
    }

    public boolean isHaveChild(){
        return getLeftChild() != null || getRightChild() != null;
    }

}
